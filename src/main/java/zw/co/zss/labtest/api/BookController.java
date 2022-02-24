package zw.co.zss.labtest.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.labtest.dtos.BookDto;
import zw.co.zss.labtest.mappers.BookMapper;
import zw.co.zss.labtest.services.ifaces.BookService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/books")
public class BookController {

    private final BookMapper mapstructMapper;
    private final BookService bookService;

    public BookController(BookMapper mapstructMapper, BookService bookService) {
        this.mapstructMapper = mapstructMapper;
        this.bookService = bookService;
    }


    @PostMapping
    @ResponseBody
    public BookDto registerBook(@RequestBody BookDto bookDto) {
        log.info("Book in controller {}",bookDto);
        var book= bookService.create(mapstructMapper.bookDtoToBook(bookDto));
        return mapstructMapper.bookToBookDto(book);
    }


    @GetMapping
    @ResponseBody
    public List<BookDto> findAllBooks() {
        return mapstructMapper.booksToAllBooksDto(bookService.findAll());
    }


    @GetMapping("/paginated")
    @ResponseBody
    public Page<BookDto> findAllPaginateAndSort(@RequestParam(required=false,defaultValue="1") int pageNumber, @RequestParam(required=false,defaultValue="10") int pageSize, @RequestParam(required=false,defaultValue="id") String field) {
        return mapstructMapper.booksToAllBooksDtoWithPagingAndSorting(bookService.findAllPaginateAndSort(pageNumber,pageSize,field));
    }

    @GetMapping("/paginated/{keyword}")
    @ResponseBody
    public Page<BookDto> searchPaginated(@PathVariable("keyword") String keyword,@RequestParam(required=false,defaultValue="1") int pageNumber, @RequestParam(required=false,defaultValue="10") int pageSize, @RequestParam(required=false,defaultValue="id") String field) {
        return mapstructMapper.booksToAllBooksDtoWithPagingAndSorting(bookService.searchPaginated(keyword,pageNumber,pageSize,field));
    }

    @GetMapping("/{keyword}")
    @ResponseBody
    public List<BookDto> search(@PathVariable("keyword") String keyword) {
        return mapstructMapper.booksToAllBooksDto(bookService.search(keyword));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public BookDto updateBook(@PathVariable("id")  String id,@RequestBody BookDto bookDto) {
        var book= bookService.update(id,mapstructMapper.bookDtoToBook(bookDto));
        return mapstructMapper.bookToBookDto(book);
    }

    @PostMapping("purchase/{id}")
    @ResponseBody
    public String purchaseBook(@PathVariable("id")  String id) {
        return bookService.purchaseBook(id);
    }


    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id")  String id) {
        bookService.delete(id);
    }

}
