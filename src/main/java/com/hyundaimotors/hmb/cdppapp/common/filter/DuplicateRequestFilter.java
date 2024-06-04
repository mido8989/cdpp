package com.hyundaimotors.hmb.cdppapp.common.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
public class DuplicateRequestFilter implements Filter {

    private ThreadLocal<String> previousRequestUuid = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String currentRequestUuid = UUID.randomUUID().toString();

        if (currentRequestUuid.equals(previousRequestUuid.get())) {
            // 중복 요청이라면 처리하지 않고 넘김
            return;
        }

        previousRequestUuid.set(currentRequestUuid);

        // 중복이 아니라면 요청 처리 진행
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 작업 수행
    }

    @Override
    public void destroy() {
        // 필터 파괴 시의 작업 수행
    }
}