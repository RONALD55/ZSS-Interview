package zw.co.zss.labtest.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import zw.co.zss.labtest.dtos.BookDto;
import zw.co.zss.labtest.models.Book;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {CategoryMapper.class}
)

public interface BookMapper {
    @Mapping(source = "book.category", target = "categoryDto")
    BookDto bookToBookDto(Book book);

    @Mapping(source = "bookDto.categoryDto", target = "category")
    Book bookDtoToBook(BookDto bookDto);

    @Mapping(source = "book.category", target = "categoryDto")
    List<BookDto> booksToAllBooksDto(List<Book> book);

    @Mapping(source = "book.category", target = "categoryDto")
    default Page<BookDto> booksToAllBooksDtoWithPagingAndSorting(Page<Book> page) {
        List<BookDto> dtoList = booksToAllBooksDto(page.getContent());
        return new PageImpl(dtoList, page.getPageable(), page.getTotalElements());
    }
}
