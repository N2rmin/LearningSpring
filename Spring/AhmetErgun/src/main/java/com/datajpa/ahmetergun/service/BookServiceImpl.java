package com.datajpa.ahmetergun.service;

import com.datajpa.ahmetergun.dto.mapper;
import com.datajpa.ahmetergun.dto.requestDto.BookRequestDto;
import com.datajpa.ahmetergun.dto.responseDto.BookResponseDto;
import com.datajpa.ahmetergun.model.Author;
import com.datajpa.ahmetergun.model.Book;
import com.datajpa.ahmetergun.model.Category;
import com.datajpa.ahmetergun.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorsIds().isEmpty()){
            throw  new IllegalArgumentException("you need at least one author");
        }else {
            List<Author> authors = new ArrayList<>();
            for (Long authorId: bookRequestDto.getAuthorsIds()){
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId()==null){
            throw  new IllegalArgumentException(" book at leat one category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        Book book1= bookRepository.save(book);
        return mapper.bookToBookResponseDto(book1);
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);

        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->
                new IllegalArgumentException(" cannot find book with id:" + bookId));
        return book;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport
                .stream(bookRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.booksToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);

        return mapper.bookToBookResponseDto(book);
    }

    @Transactional
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book bookEdit = getBook(bookId);
        bookEdit.setName(bookRequestDto.getName());
        if(!bookRequestDto.getAuthorsIds().isEmpty()){
            List<Author> authors= new ArrayList<>();
            for(Long authorId: bookRequestDto.getAuthorsIds()){
                Author author=authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookEdit.setAuthors(authors);
        }
        if (bookRequestDto.getCategoryId()!=null){
            Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
            bookEdit.setCategory(category);
        }
        return mapper.bookToBookResponseDto(bookEdit);
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author= authorService.getAuthor(authorId);
        if(author.getBooks().contains(author)){
            throw new IllegalArgumentException("this author has already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);

        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book=getBook(bookId);
        Author author= authorService.getAuthor(authorId);
        if(!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("book does not have this author");

        }
        author.removeBook(book);
        book.deleteAuthor(author);

        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book=getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if(Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book already has a category ");
        }
        book.setCategory(category);
        category.addBook(book);
        return mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book =getBook(bookId);
        Category category =categoryService.getCategory(categoryId);
        if(!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book does not has a category");
        }
        book.setCategory(null);
        category.removeBook(book);
        return mapper.bookToBookResponseDto(book);
    }
}
