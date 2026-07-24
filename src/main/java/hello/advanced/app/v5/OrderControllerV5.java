package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 service;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.template = new TraceTemplate(logTrace);
        this.service = orderService;
    }

    @GetMapping("/v5/request")
    public String request(@RequestParam("itemId") String itemId){
        return template.execute("OrderControllerV5.request()", new TraceCallback<>() {
            @Override
            public String call() {
                service.orderItem(itemId);
                return "OK@@";
            }
        });
    }
}
