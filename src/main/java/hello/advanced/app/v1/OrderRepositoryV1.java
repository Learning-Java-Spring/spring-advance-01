package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepositoryV1.save()");
            //저장로직
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
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
