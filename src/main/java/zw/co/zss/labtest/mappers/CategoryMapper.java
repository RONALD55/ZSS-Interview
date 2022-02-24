package zw.co.zss.labtest.mappers;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import zw.co.zss.labtest.dtos.CategoryDto;
import zw.co.zss.labtest.models.Category;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);
    Category categoryDtoToCategory(CategoryDto category);

    List<CategoryDto> categoriesToAllCategoriesDto(List<Category> categorys);
    default Page<CategoryDto> categoriesToAllCategoriesDtoWithPagingAndSorting(Page<Category> page) {
        List<CategoryDto> dtoList = categoriesToAllCategoriesDto(page.getContent());
        return new PageImpl(dtoList, page.getPageable(), page.getTotalElements());
    }
}
