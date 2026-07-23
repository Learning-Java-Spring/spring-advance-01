package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId,"OrderRepositoryV2.save()");
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
