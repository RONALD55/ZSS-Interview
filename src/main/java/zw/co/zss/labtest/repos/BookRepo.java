package zw.co.zss.labtest.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.co.zss.labtest.models.Book;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, String> {
    @Query("SELECT b FROM Book b WHERE" +
            " b.id LIKE CONCAT('%', :keyword, '%') " +
            "OR b.title LIKE CONCAT('%', :keyword, '%')  " +
            "OR b.description LIKE CONCAT('%', :keyword, '%')  " +
            "OR b.category.id LIKE CONCAT('%', :keyword, '%')  " +
            "OR b.category.title LIKE CONCAT('%', :keyword, '%')  " +
            "ORDER BY b.dateCreated DESC")
    List<Book> search(@Param("keyword") String keyword);

    @Query("SELECT b FROM Book b WHERE" +
            " b.id LIKE CONCAT('%', :keyword, '%') " +
            "OR b.title LIKE CONCAT('%', :keyword, '%')  " +
            "OR b.description LIKE CONCAT('%', :keyword, '%')  " +
            "OR b.category.id LIKE CONCAT('%', :keyword, '%')  " +
            "OR b.category.title LIKE CONCAT('%', :keyword, '%')  " +
            "ORDER BY b.dateCreated DESC")
    Page<Book> searchPaginated(@Param("keyword") String keyword, Pageable pageable);
}
