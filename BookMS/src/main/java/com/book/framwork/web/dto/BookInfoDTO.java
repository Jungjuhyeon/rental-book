package com.book.framwork.web.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class BookInfoDTO {
    private String title;
    private String description;
    private String author;
    private String isbn;
    private LocalDate publicationDate;
    private String source;
    private String classfication;
    private String location;
}
