package io.github.eun2ce.measuretime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy  // AOP 활성화
public class MeasureTimeConfig {

  @Bean
  public MeasureTimerAspect measureTimeAspect() {
    return new MeasureTimerAspect();
  }
}
