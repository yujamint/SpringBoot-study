package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")// hello를 GET 방식으로 요청 받으면(localhost:8080/hello) 이 메소드 실행
    public String hello(Model model) { // 스프링이 모델을 만들어서 넣어준다.
        model.addAttribute("data", "hello!!"); // 만든 모델에 Key는 data 값은 hello!! 를 넣어놓고
        return "hello"; // resources/templates의 hello.html을 찾아가서 렌더링(해당 화면을 실행하라)
        //컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다.
        //스프링부트 템플릿엔진 기본 viewName 매핑
        // 'resources:templates/' + {viewName} + '.html' -> 여기선 viewName이 hello가 되는 것
        // hello.html에 있는 data는 위에서 만든 Model의 키 값, 키의 값을 꺼내면서 hello!! 가 나오게 되는 것
    }
}
