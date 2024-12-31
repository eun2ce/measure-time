package io.github.eun2ce.measuretime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MeasureTimerAspect {

  @Around("@annotation(io.github.eun2ce.measuretime.MeasureTime)")  // @MeasureTime 어노테이션을 가진 메서드만 적용
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.nanoTime();
    Object result = joinPoint.proceed();  // 실제 메서드 실행
    long end = System.nanoTime();
    double executionTimeMs = (end - start) / 1_000_000.0;
    System.out.println(joinPoint.getSignature() + " executed in " + executionTimeMs + " ms");
    return result;
  }
}
