package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderContrllerV4 {
    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(@RequestParam("itemId") String itemId){
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call(){
                orderService.orderItem(itemId);
                return "OK!";
            }
        };
        return template.execute("OrderControllerV4.request()");
    }
}
