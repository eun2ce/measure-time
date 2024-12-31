package measuretime;

import org.springframework.stereotype.Service;

@Service
public class SampleService {
  @MeasureTime
  public void fastTask() {
    int sum = 0;
    for (int i = 0; i < 1000; i++) {
      sum += i;
    }
  }

  @MeasureTime
  public void slowTask() throws InterruptedException {
    Thread.sleep(500);
  }
}
