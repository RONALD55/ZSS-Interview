package zw.co.zss.labtest.services.ifaces;

import org.springframework.data.domain.Page;
import zw.co.zss.labtest.exceptions.BookNotFoundException;
import zw.co.zss.labtest.models.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);
    Book update(String id, Book book);
    Book findById(String id) throws BookNotFoundException;
    void delete(String id);
    List<Book> search(String keyword);
    List<Book> findAll();
    Page<Book> searchPaginated(String keyword, int pageNumber, int pageSize, String field);
    Page<Book> findAllPaginateAndSort(int pageNumber,int pageSize,String field);
    String purchaseBook(String id);
}
