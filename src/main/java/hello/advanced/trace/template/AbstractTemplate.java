package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;


//AbstractTemplate<T> 는 AbstractTemplate를 생성할때 T를 String으로 지정하면 String으로 반환받는다.
//반환타입을 뒤로 미뤄둔다.
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message){
        TraceStatus status = null;
        try{
            status = trace.begin(message);
            //로직호출
            T result =  call();
            trace.end(status);
            return result;
        } catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
