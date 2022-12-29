package com.datajpa.ahmetergun.service;

import com.datajpa.ahmetergun.dto.requestDto.AuthorRequestDto;
import com.datajpa.ahmetergun.dto.responseDto.AuthorResponseDto;
import com.datajpa.ahmetergun.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService  {
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);
    public List<AuthorResponseDto> getAuthors();
    public AuthorResponseDto getAuthorById(Long authorId);
    public Author getAuthor(Long authorId);
    public AuthorResponseDto editAuthor(Long authorId, AuthorRequestDto authorRequestDto);
    public AuthorResponseDto deleteAuthor(Long authorId);
    public AuthorResponseDto addZipcodeToAuthor(Long authorId, Long zipCodeId);
    public AuthorResponseDto deleteZipcodeFromAuthor(Long authorId);

}
