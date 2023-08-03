package com.jjb.myapp.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long no; // 번호
    @Column(nullable = false)
    private String title; // 제목
    @Column(nullable = false)
    private String content; // 내용
//    private String creatorName; // 게시자
    private Long createdTime;

    // database의 auto-increment를 사용함
    // auto-increment: 레코드가 추가될때 자동으로 증가되는 값을 사용
    // 1,2,3,...
    // created_time bigint not null,
    // primitive type: int, char, long, double
    // nullable 불가, long 기본값이 0
    // 데이터베이스에 NOT NULL로 세팅해줌
//    private long createdTime; // 생성시간
    // 데이터베이스에 NULL을 넣고싶으면 Class 타입을 써야함.
}
