package hello.hellospring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component // 컴포넌트 등록 대신, AOP 쓴다는 것을 인지할 수 있도록 스프링 빈(SpringConfig)에 등록하는 방법을 선택해도 된다.
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // AOP 어디에 적용할 것인지 , hello.hellospring 하위 파일에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
