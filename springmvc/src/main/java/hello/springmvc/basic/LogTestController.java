package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController: 반환 값이 String 형태일 경우, 문자열 그대로 HTTP Message Body에 입력한다.
// @Controller: 반환 값이 String 형태일 경우, view 이름으로 간주된다.
@RestController
@Slf4j  // Lombok 제공 애너테이션을 선언해주면 private final ~ .. 문장 없이도 로깅이 가능하다.
public class LogTestController {
    // slf4j 패키지의 Logger 사용!
    // private final Logger log = LoggerFactory.getLogger(getClass()); // 클래스 지정

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // {}에 name의 값이 들어간 형태로 출력된다. => info log=Spring
        // 로그는 5개의 레벨로 구분되며, 설정한 레벨의 하위 로그까지 모두 확인할 수 있다.
        // info로 설정 시, info, warn, error 모두 확인 가능하다.
        log.trace("trace log={}", name);    // 가장 상세한 로그 레벨, 디버깅 시 사용
        log.debug("debug log={}", name);    // 개발 단계에서의 상세 정보 기록, 디버깅 목적으로 사용
        log.info("info log={}", name);      // 정보성 메시지 기록, 실제 운영 서버에서 사용
        log.warn("warn log={}", name);      // 경고성 메시지 기록, 애플리케이션이 정상적으로 동작하지만 주의가 필요한 상황을 알린다.
        log.error("error log={}", name);    // 오류 메시지 기록, 애플리케이션의 동작을 중단시킬 수 있는 치명적인 오류를 나타낸다.

        return "OK!";
    }
}