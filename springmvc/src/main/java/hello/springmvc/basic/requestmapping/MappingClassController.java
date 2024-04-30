package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping")
public class MappingClassController {
    /**
     * (데이터 관련 작업 제외) 회원 관리 HTTP API (prefix: '/mapping')
     * 회원 목록 조회: GET '/users'
     * 회원 등록: POST '/users'
     * 회원 조회: GET '/users/{userId}'
     * 회원 수정: PATCH '/users/{userId}'
     * 회원 삭제: DELETE '/users/{userId}'
     */

    // 회원 목록 조회
    @GetMapping("/users")
    public String user() {
        return "get users";
    }

    // 회원 등록
    @PostMapping("/users")
    public String addUser() {
        return "post user";
    }

    // 회원 조회
    @GetMapping("/users/{userId}")
    public String findUser(@PathVariable("userId") String userId) {
        return "get userId = " + userId;
    }

    // 회원 수정
    @PatchMapping("/users/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        return "update userId = " + userId;
    }

    // 회원 삭제
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete userId = " + userId;
    }
}