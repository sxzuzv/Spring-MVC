package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}