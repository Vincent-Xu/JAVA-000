package io.vincent.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.stereotype.Service;

@Service
public class SignFilter implements IFilter {
    public static final String SIGN_KEY = "nio";
    public static final String SIGN_VALUE = "XUDESHENG";
    public static String TIME_STAMP = "TIME_STAMP";

    @Override
    public void run(FullHttpRequest fullHttpRequest) {
        fullHttpRequest.headers().add(SIGN_KEY, SIGN_VALUE);
        fullHttpRequest.headers().add(TIME_STAMP, System.currentTimeMillis() + Math.random());
    }
}
