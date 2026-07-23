package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(@RequestParam("itemId") String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderControllerV2.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "okey v2";
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }

    }
}
