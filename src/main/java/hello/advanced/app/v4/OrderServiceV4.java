package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId){
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call(){
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderServiceV4.orderItem()");

    }
}
