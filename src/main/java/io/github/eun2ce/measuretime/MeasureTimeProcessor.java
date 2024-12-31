package io.github.eun2ce.measuretime;

import java.lang.reflect.Method;

public class MeasureTimeProcessor {

  public static void process(Class<?> clazz) {
    Method[] methods = clazz.getDeclaredMethods();

    for (Method method : methods) {
      if (method.isAnnotationPresent(MeasureTime.class)) {
        if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
          System.err.println("Skipping non-static method: " + method.getName());
          continue;
        }

        try {
          System.out.println("Measuring method: " + method.getName());
          long startTime = System.nanoTime();
          method.setAccessible(true);
          method.invoke(null); // Static method execution
          long endTime = System.nanoTime();

          double executionTimeMs = (endTime - startTime) / 1_000_000.0;
          System.out.println(method.getName() + " executed in " + executionTimeMs + " ms");
        } catch (Exception e) {
          System.err.println("Failed to execute method: " + method.getName());
          e.printStackTrace();
        }
      }
    }
  }
}
