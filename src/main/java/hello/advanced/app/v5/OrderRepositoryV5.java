package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){
        template.execute("OrderRepositoryV5.save()", () -> {
            //저장로직
            if (itemId.equals("ex")){
                throw new IllegalStateException("예외발생");
            }
            sleep(1000);
            return null;
        });

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
