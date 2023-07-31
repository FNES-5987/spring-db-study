package com.jjb.myapp.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    private long no; // 번호
    private String title; // 제목
    private String  content; // 내용
    private String creatorName; // 게시자
    private long createdTime; // 생성시간
}
