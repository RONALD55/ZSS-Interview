package zw.co.zss.labtest.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import zw.co.zss.labtest.enums.EntityStatus;
import zw.co.zss.labtest.exceptions.CategoryNotFoundException;
import zw.co.zss.labtest.models.Category;
import zw.co.zss.labtest.repos.CategoryRepo;
import zw.co.zss.labtest.services.ifaces.CategoryService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepo categoryRepo;
    
    @Override
    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(String id, Category update) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        category.setTitle(update.getTitle());
        return categoryRepo.save(category);
    }

    @Override
    public Category findById(String id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepo.findById(id);
        return category.orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public void delete(String id) {
        Optional<Category> check = categoryRepo.findById(id);

        check.ifPresentOrElse(
                category -> {
                    category.setStatus(EntityStatus.DELETED);
                    categoryRepo.save(category);
                }
                ,() -> { throw new CategoryNotFoundException("Category not found"); }
        );

    }

    @Override
    public List<Category> search(String keyword) {
        return categoryRepo.search(keyword);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Page<Category> searchPaginated(String keyword, int pageNumber, int pageSize, String field) {
        return categoryRepo.searchPaginated(keyword, PageRequest.of(pageNumber-1,pageSize).withSort(Sort.by(field).descending()));
    }

    @Override
    public Page<Category> findAllPaginateAndSort(int pageNumber, int pageSize, String field) {
        return categoryRepo.findAll(PageRequest.of(pageNumber-1,pageSize).withSort(Sort.by(field).descending()));
    }
}
