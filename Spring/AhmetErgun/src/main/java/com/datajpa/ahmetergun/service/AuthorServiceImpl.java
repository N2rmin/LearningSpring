package com.datajpa.ahmetergun.service;

import com.datajpa.ahmetergun.dto.mapper;
import com.datajpa.ahmetergun.dto.requestDto.AuthorRequestDto;
import com.datajpa.ahmetergun.dto.responseDto.AuthorResponseDto;
import com.datajpa.ahmetergun.model.Author;
import com.datajpa.ahmetergun.model.Zipcode;
import com.datajpa.ahmetergun.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ZipcodeService zipcodeService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ZipcodeService zipcodeService) {
        this.authorRepository = authorRepository;
        this.zipcodeService = zipcodeService;
    }

    @Transactional
    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {

        Author author= new Author();
        author.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipcodeId()== null){
            throw  new IllegalArgumentException("author need a zipcode");
        }
        Zipcode zipcode= zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
        author.setZipcode(zipcode);
        authorRepository.save(author);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAuthors() {
        List<Author> authors = StreamSupport.stream(authorRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.authorsToAuthorResponseDtos(authors);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {

        return mapper.authorToAuthorResponseDto(getAuthor(authorId));
    }

    @Override
    public Author getAuthor(Long authorId) {
        Author author= authorRepository.findById(authorId).orElseThrow(()->new IllegalArgumentException("Athor with id : "+authorId+" could not be found"));
        return author;
    }

    @Transactional
    @Override
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto) {

        Author authorToEdits = getAuthor(authorId);
        authorToEdits.setName(authorRequestDto.getName());
        if (authorRequestDto.getZipcodeId() != null){
            Zipcode zipcode= zipcodeService.getZipcode(authorRequestDto.getZipcodeId());
            authorToEdits.setZipcode(zipcode);

        }

        return mapper.authorToAuthorResponseDto(authorToEdits);
    }

    @Override
    public AuthorResponseDto deleteAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        authorRepository.deleteById(authorId);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipCodeId) {
        Author author = getAuthor(authorId);
        Zipcode zipcode =zipcodeService.getZipcode(zipCodeId);
        if(Objects.nonNull(author.getZipcode())){
            throw new RuntimeException("author already has a zipcode");
        }
        author.setZipcode(zipcode);
        return mapper.authorToAuthorResponseDto(author);
    }

    @Transactional
    @Override
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId) {
        Author author = getAuthor(authorId);
        author.setZipcode(null);

        return mapper.authorToAuthorResponseDto(author);
    }
}
