package com.jjb.myapp.contact;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter 자동으로 만들어줌, 컴파일 시점에
@Builder
@AllArgsConstructor // 전체 필드 초기화 생성자
@NoArgsConstructor // 빈 생성자
@Entity

public class Contact {
    // key
    @Id
    private String email;

    private String name;
    private String phone;
    // 파일을 base64 data-url 문자열로 저장한다.
    private String image;
}
