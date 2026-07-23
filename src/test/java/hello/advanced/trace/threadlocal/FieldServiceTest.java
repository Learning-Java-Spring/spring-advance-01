package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field(){
        log.info("main start");
        Runnable UserA = () ->{
            fieldService.logic("userA");
        };

        Runnable UserB = () ->{
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(UserA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(UserB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000);
        threadB.start();
        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
