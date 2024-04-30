package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController {
    // log 사용
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청: '/hello-basic', '/hello-basic/' 모두 허용
     * HTTP 메서드 모두 허용: GET, HEAD, POST, PUT, PATCH, DELETE
     */
    // 배열을 활용해 URL 다중 설정이 가능하다.
    // @RequestMapping({"/hello-basic", "/hello-go"})

    // method 속성을 사용해 특정 HTTP method 지정이 가능하다.
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic() {
        log.info("hello-basic");
        return "OK!";   // 문자열을 HTTP Message Body에 그대로 넣는다.
    }
}