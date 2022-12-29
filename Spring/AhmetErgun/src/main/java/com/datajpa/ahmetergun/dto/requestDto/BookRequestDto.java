package com.datajpa.ahmetergun.dto.requestDto;

import lombok.Data;

import java.util.List;

@Data
public class BookRequestDto {
    private String  name;
    private List<Long> authorsIds;
    private Long categoryId;

}
