package measuretime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect to measure execution time of methods annotated with @MeasureTime.
 */
@Aspect
@Component
public class TimerAspect {
  @Around("@annotation(measuretime.MeasureTime)")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.nanoTime();
    Object result = joinPoint.proceed();
    long end = System.nanoTime();
    double executionTimeMs = (end - start) / 1_000_000.0;
    System.out.println(joinPoint.getSignature() + " executed in " + executionTimeMs + " ms");
    return result;
  }
}
