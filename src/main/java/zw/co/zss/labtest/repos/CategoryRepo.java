package zw.co.zss.labtest.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zw.co.zss.labtest.models.Category;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, String> {
    @Query("SELECT c FROM Category c WHERE" +
            " c.id LIKE CONCAT('%', :keyword, '%') " +
            "OR c.title LIKE CONCAT('%', :keyword, '%')  " +
            "ORDER BY c.dateCreated DESC")
    List<Category> search(@Param("keyword") String keyword);

    @Query("SELECT c FROM Category c WHERE" +
            " c.id LIKE CONCAT('%', :keyword, '%') " +
            "OR c.title LIKE CONCAT('%', :keyword, '%')  " +
            "ORDER BY c.dateCreated DESC")
    Page<Category> searchPaginated(@Param("keyword") String keyword, Pageable pageable);
}
