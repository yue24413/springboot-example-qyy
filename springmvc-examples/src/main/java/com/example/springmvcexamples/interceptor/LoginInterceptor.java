package com.example.springmvcexamples.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springmvcexamples.component.JWTComponent;
import com.example.springmvcexamples.exception.Code;
import com.example.springmvcexamples.exception.XException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
   /*对token进行解密校验，注入组件*/
    private final JWTComponent jwtComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null) {
            throw XException.builder().code(Code.UNAUTHORIZED).build();
        }
        DecodedJWT decode = jwtComponent.decode(token);
        String uid = decode.getClaim("uid").asString();
        String role = decode.getClaim("role").asString();
        /*控制层或者其他拦截器需要用到真实的id和role,其他地方需要拿，请求对象和响应对象贯穿整个生命周期，附着到它身上*/
        request.setAttribute("uid", uid);
        request.setAttribute("role", role);
        return true;
    }
}
