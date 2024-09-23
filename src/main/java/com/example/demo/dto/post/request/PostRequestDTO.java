package com.example.demo.dto.post.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class PostRequestDTO {
    private final String title;
    private final String content;
    private final String memberName;
}
