package com.example.teamsnstest.dto;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestDto {
    private Long testId;
    private String testString;
    private List<String> imageLinks;
}
