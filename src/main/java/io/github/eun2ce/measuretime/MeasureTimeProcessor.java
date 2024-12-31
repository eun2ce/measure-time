package io.github.eun2ce.measuretime;

import java.lang.reflect.Method;

public class MeasureTimeProcessor {

  /**
   * 모든 메서드를 실행하면서 @MeasureTime 애노테이션이 붙은 메서드의 실행 시간을 출력합니다.
   */
  public static void process(Class<?> clazz) {
    for (Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(MeasureTime.class)) {
        try {
          long startTime = System.nanoTime();
          method.setAccessible(true);
          method.invoke(null);  // static 메서드 호출
          long endTime = System.nanoTime();
          double executionTimeMs = (endTime - startTime) / 1_000_000.0;
          System.out.println(method.getName() + " executed in " + executionTimeMs + " ms");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
}
