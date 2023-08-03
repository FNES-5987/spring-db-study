package com.jjb.myapp.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// GET /posts
// 게시글 목록이 JSON으로 나오게
@RestController
@RequestMapping("/posts")
public class PostController {
    // 동시성을 위한 자료 구조
    // HashMap -> ConcurrentHashMap
    // Integer -> AtomicInteger
    Map<Long, Post> map = new ConcurrentHashMap<>();
    AtomicLong num = new AtomicLong(0);

    @Autowired
    PostRepository repo;
    public PostController(PostRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Post> getPostList() {

//        var list = new ArrayList<>(map.values());
//        // 람다식(lambda expression)
//        // 식이 1개인 함수식;
//        // 매개변수 영역과 함수 본체를 화살표로 구분함
//        // 함수 본체의 수식 값이 반환 값
//        list.sort((a,b)-> (int)(b.getNo() - a.getNo()));

        List<Post> list = repo.findAll(Sort.by("no").ascending());
        return list;
    }


    //    -- 받는 정보
//    title, content
//    -> null 또는 없으면 bad-request 응답

//    -- 서버에 생성
//    no = num.incrementAndGet();
//    creatorName = "John Doe"
//    createdTime = new Date().getTime()

    // title, content 필수 속성
    // creatorName: 서버에서 가짜데이터로 넣음
    @PostMapping
    public ResponseEntity<Map<String, Object>> addPost(@RequestBody Post post) {
//     1. 입력값 검증(title, content)
//      -> 입력값 오류(빈 값)가 있으면 400 에러 띄움
        if(post.getTitle() == null || post.getContent() == null || post.getContent().isEmpty() || post.getTitle().isEmpty()){
            Map<String, Object> response = new HashMap<>();
            response.put("data", null);
            response.put("message", "[title] and [content] is Required Field");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

//     2. 채번: 번호를 딴다(1 .. 2, 3....)
        // 데이터베이스의 auto-increment 사용할 것이므로 아래 2줄은 필요없어진다.
        long no = num.incrementAndGet();
        post.setNo(no);

//     3. 번호(no), 시간값(createdTime) 게시자이름(creatorName) 요청 객체에 설정
        post.setCreatedTime(new Date().getTime());

//     4. 맵에 추가
//        map.put(no, post);
//        System.out.println(post);
        Post savedPost = repo.save(post);

//     5. 생성된 객체를 맵에서 찾아서 반환, 201
//        Optional<Post> savedPost = repo.findById(post.getNo());
        if (savedPost != null) {
            Map<String, Object> res = new HashMap<>();
            res.put("data", savedPost.getNo());
            res.put("message", "created");

            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        }

        return ResponseEntity.ok().build();
//        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping(value = "/{no}")
    public ResponseEntity removeContent(@PathVariable Long no) {
        System.out.println(no);
//        if (map.get(no) == null) {
        if (!repo.findById(no).isPresent()) {
            // 404: Not Found, 해당 경로에 리소스가 없음.
            // DELETE /content/...
            // Response Status code : 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 객체(리소스) 삭제
//        map.remove(no);
        repo.deleteById(no);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}