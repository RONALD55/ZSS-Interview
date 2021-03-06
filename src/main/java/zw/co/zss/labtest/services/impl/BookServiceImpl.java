package zw.co.zss.labtest.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import zw.co.zss.labtest.enums.EntityStatus;
import zw.co.zss.labtest.exceptions.BookNotFoundException;
import zw.co.zss.labtest.models.Book;
import zw.co.zss.labtest.models.Category;
import zw.co.zss.labtest.repos.BookRepo;
import zw.co.zss.labtest.repos.CategoryRepo;
import zw.co.zss.labtest.services.ifaces.BookService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    
    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final WebClient webClient;
    @Value("${zss.transactions.api}")
    private String url;



    @Override
    public Book create(Book book) {
        log.info("book : {}",book);
        categoryRepo.save(book.getCategory());
        return bookRepo.save(book);
    }

    @Override
    public Book update(String id, Book update) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        book.setStatus(update.getStatus());
        book.setTitle(update.getTitle());
        book.setDescription(update.getDescription());
        book.setPrice(update.getPrice());
    

        Optional<Category> category=categoryRepo.findById(update.getCategory().getId());
        category.ifPresent(data->{
            data.setStatus(update.getCategory().getStatus());
            data.setTitle(update.getCategory().getTitle());
            categoryRepo.save(data);
        });

        return bookRepo.save(book);
    }

    @Override
    public Book findById(String id) throws BookNotFoundException {
        Optional<Book> book = bookRepo.findById(id);
        return book.orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public void delete(String id) {
        Optional<Book> check = bookRepo.findById(id);
        check.ifPresentOrElse(
                book -> {
                    book.setStatus(EntityStatus.DELETED);
                    bookRepo.save(book);
                }
                ,() -> { throw new BookNotFoundException("Book not found"); }
        );
    }

    @Override
    public List<Book> search(String keyword) {
        return bookRepo.search(keyword);
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Page<Book> searchPaginated(String keyword, int pageNumber, int pageSize, String field) {
        return bookRepo.searchPaginated(keyword, PageRequest.of(pageNumber-1,pageSize).withSort(Sort.by(field).descending()));
    }

    @Override
    public Page<Book> findAllPaginateAndSort(int pageNumber, int pageSize, String field) {
        return bookRepo.findAll(PageRequest.of(pageNumber-1,pageSize).withSort(Sort.by(field).descending()));
    }

    @Override
    public String purchaseBook(String id) throws JsonProcessingException {

        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        String data= "{\n" +
                "  \"type\" : \"PURCHASE\",\n" +
                "  \"extendedType\" : \"NONE\",\n" +
                "  \"amount\" : "+book.getPrice().toString()+",\n" +
                "  \"created\" : \""+ dateTimeFormatter.format(currentTime)+"\",\n" +
                "  \"card\" : {\n" +
                "    \"id\" : \"1234560000000001\",\n" +
                "    \"expiry\" : \"2020-01-01\"\n" +
                "  },\n" +
                "  \"reference\" : \"33d0893a-4c0e-49cf-a373-7e67a64ce036\",\n" +
                "  \"narration\" : \"Test Narration\",\n" +
                "  \"additionalData\" : {\n" +
                "    \"SampleKey\" : \"This is a sample value\"\n" +
                "  }\n" +
                "}";
        log.info("data to API {}",data);

        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer 9ca3d5ed-dc04-4700-8dd6-7d60c3cdf0fa")
                .bodyValue(data)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
