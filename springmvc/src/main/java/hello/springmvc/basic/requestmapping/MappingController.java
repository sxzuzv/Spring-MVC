package hello.springmvc.basic.requestmapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    // log 사용
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청: '/mapping-get-v1', '/mapping-get-v1/' 모두 허용
     * @RequestMapping method 미지정: HTTP 메서드 모두 허용 (GET, HEAD, POST, PUT, PATCH, DELETE)
     */
    // 배열을 활용해 URL 다중 설정이 가능하다.
    // @RequestMapping({"/mapping-get-v1", "/mapping-get-v1-go"})

    // method 속성을 사용해 특정 HTTP method 지정이 가능하다.
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "OK!";   // 문자열을 HTTP Message Body에 그대로 넣는다.
    }

    /**
     * 편리한 축약 애너테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "OK!";   // 문자열을 HTTP Message Body에 그대로 넣는다.
    }

    /**
     * PathVariable 사용: 변수명이 같으면 생략할 수 있다.
     * @PathVariable("userId") String userId => @PathVariable userId
     * 요청 URL 자체에 값이 들어간 경우(userId), 이를 경로 변수라고 칭한다.
     * 메서드의 매개변수로 경로 변수를 받을 때, @PathVariable을 사용할 수 있다.
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        // 만약 매개변수 이름이 userId로 경로 변수와 동일할 시,
        // (@PathVariable String userId)로 변경 가능하다.
        log.info("mappingPath userId={}", data);
        return "OK!";
    }

    /**
     * 다중 PathVariable 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPathV2(@PathVariable("userId") String userId, @PathVariable("orderId") Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "OK!";
    }

    /**
     * 특정 파라미터로 추가 매핑: 요청 URL에 params 속성으로 지정한 파라미터가 존재해야 메서드가 정상 호출된다.
     * params="mode"
     * params="!mode" (파라미터에 mode가 없어야 한다.)
     * params="mode=debug"
     * params="mode!=debug" (mode 파라미터의 값이 debug가 아니어야 한다.)
     * params="{mode=debug", "data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "OK!";
    }

    /**
     * 특정 헤더로 추가 매핑: HTTP Header에 headers 속성으로 지정한 값이 존재해야 메서드가 정상 실행된다.
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "OK!";
    }

    /**
     * 미디어 타입 조건 매핑: HTTP request의 Content-Type을 설정한다.
     * HTTP request Header의 Content-Type이 consume 속성으로 지정한 형태일 때, 메서드가 정상 호출된다.
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICAION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsume() {
        log.info("mappingConsume");
        return "OK!";
    }

    /**
     * Accept 헤더 기반 Media Type: HTTP request Header의 Accept가 produces 속성으로 지정한 형태일 때, 메서드가 정상 호출된다.
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "OK!";
    }
}