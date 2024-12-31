package io.github.eun2ce.measuretime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MeasureTimeAspect {

    @Around("@annotation(io.github.eun2ce.measuretime.MeasureTime)")  // @MeasureTime 어노테이션이 붙은 메서드 실행 전후로 동작
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();  // 시작 시간 측정
        try {
            return joinPoint.proceed();   // 메서드 실행
        } finally {
            long end = System.nanoTime();  // 종료 시간 측정
            System.out.println("Execution time of " + joinPoint.getSignature() + ": " + (end - start) + " ns");
        }
    }
}
