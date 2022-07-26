package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // url로 파라미터를 받는다.
        model.addAttribute("name", name); // url로 받은 파라미터를 model의 "name" Key의 Value로 넣는다.
        return "hello-template"; // hello-template 반환
        //컨트롤러에서 hello-template을 반환했고, 뷰 리졸버가 화면을 찾아서 처리한다.
        //'resources:templates/' + {hello-template} + '.html'
        //${name}은 model에서 값을 가져와 치환하는 것이므로 model의 name 키의 값이 들어간다.
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 head부와 body부 중 body에 데이터를 직접 넣어준다
    // 페이지 소스를 보면 html 태그가 전혀 없고, 딱 hello + name만 있는 것을 확인할 수 있다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
