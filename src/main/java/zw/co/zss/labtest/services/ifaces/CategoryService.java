package zw.co.zss.labtest.services.ifaces;

import org.springframework.data.domain.Page;
import zw.co.zss.labtest.exceptions.CategoryNotFoundException;
import zw.co.zss.labtest.models.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    Category update(String id, Category category);
    Category findById(String id) throws CategoryNotFoundException;
    void delete(String id);
    List<Category> search(String keyword);
    List<Category> findAll();
    Page<Category> searchPaginated(String keyword, int pageNumber, int pageSize, String field);
    Page<Category> findAllPaginateAndSort(int pageNumber,int pageSize,String field);
}
