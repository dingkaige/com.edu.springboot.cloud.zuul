package com.edu.springboot.cloud.zuul;


import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 容错与回退
 * @program: cloud-zuul
 * @author: dingkaige
 * @date: 2019-12-25 18:43
 **/
@Component
public class DefaultFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        // 表明是为哪个微服务提供回退 *表示匹配所有
        return "*";
    }



    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //当fallback时返回给调用者的状态码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                //状态码的文本形式
                return getStatusCode().getReasonPhrase();

            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                //响应体
                return new ByteArrayInputStream("cloud-client Service is not available, please try again later."
                        .getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                //设定headers
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_HTML);
                return headers;
            }
        };
    }
}
