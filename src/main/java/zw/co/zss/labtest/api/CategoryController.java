package zw.co.zss.labtest.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.labtest.dtos.CategoryDto;
import zw.co.zss.labtest.mappers.CategoryMapper;
import zw.co.zss.labtest.services.ifaces.CategoryService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1.0/categories")
public class CategoryController {
    private final CategoryMapper mapstructMapper;
    private final CategoryService categoryService;

  
    public CategoryController(CategoryMapper mapstructMapper, CategoryService categoryService) {
        this.mapstructMapper = mapstructMapper;
        this.categoryService = categoryService;
    }


    @PostMapping
    @ResponseBody
    public CategoryDto registerCategory(@RequestBody CategoryDto categoryDto) {
        var category= categoryService.create(mapstructMapper.categoryDtoToCategory(categoryDto));
        return mapstructMapper.categoryToCategoryDto(category);
    }


    @GetMapping
    @ResponseBody
    public List<CategoryDto> findAllCategories() {
        return mapstructMapper.categoriesToAllCategoriesDto(categoryService.findAll());
    }


    @GetMapping("/paginated")
    @ResponseBody
    public Page<CategoryDto> findAllPaginateAndSort(@RequestParam(required=false,defaultValue="1") int pageNumber, @RequestParam(required=false,defaultValue="10") int pageSize, @RequestParam(required=false,defaultValue="id") String field) {
        return mapstructMapper.categoriesToAllCategoriesDtoWithPagingAndSorting(categoryService.findAllPaginateAndSort(pageNumber,pageSize,field));
    }

    @GetMapping("/paginated/{keyword}")
    @ResponseBody
    public Page<CategoryDto> searchPaginated(@PathVariable("keyword") String keyword,@RequestParam(required=false,defaultValue="1") int pageNumber, @RequestParam(required=false,defaultValue="10") int pageSize, @RequestParam(required=false,defaultValue="id") String field) {
        return mapstructMapper.categoriesToAllCategoriesDtoWithPagingAndSorting(categoryService.searchPaginated(keyword,pageNumber,pageSize,field));
    }

    @GetMapping("/{keyword}")
    @ResponseBody
    public List<CategoryDto> search(@PathVariable("keyword") String keyword) {
        return mapstructMapper.categoriesToAllCategoriesDto(categoryService.search(keyword));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public CategoryDto updateCategory(@PathVariable("id")  String id,@RequestBody CategoryDto categoryDto) {
        var category= categoryService.update(id,mapstructMapper.categoryDtoToCategory(categoryDto));
        return mapstructMapper.categoryToCategoryDto(category);
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id")  String id) {
        categoryService.delete(id);
    }

}
