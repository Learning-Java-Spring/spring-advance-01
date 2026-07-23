package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;

    public void save(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call(){
                //저장로직
                if (itemId.equals("ex")){
                    throw new IllegalStateException("예외발생");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepositoryV4.save()");

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
