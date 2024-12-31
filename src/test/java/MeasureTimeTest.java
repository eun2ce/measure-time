
import measuretime.MeasureTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeasureTimeTest {

  // 실행 시간을 측정할 메소드에 @MeasureTime 어노테이션을 사용
  @Test
  @MeasureTime
  public void testMethodExecutionTime() {
    try {
      // 가상의 시간 소모 작업 (예시: 2초 대기)
      Thread.sleep(2000);  // 2초 대기
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // 테스트가 잘 실행되는지 확인
    assertTrue(true);  // 성공적인 실행을 위한 assert
  }
}
