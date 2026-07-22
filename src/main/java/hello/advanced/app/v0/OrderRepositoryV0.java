package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {

    public void save(String itemId){
        //저장로직
        if (itemId.equals("ex")){
            throw new IllegalStateException("예외발생");
        }
        sleep(1000);
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
            log.info("저장됨");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
