package com.example.backendexamples.component;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backendexamples.exception.Code;
import com.example.backendexamples.exception.XException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JWTComponent {
    @Value("${my.secretkey}")
    private  String secretkey;
    private Algorithm algorithm ;
    /*组件创建完了之后，(属性初始化完了之后)，在可用状态之前(构造函数创建完了之后），
    *再完成这个属性的初始化。一般是属性先初始化，再构造函数
    * */
    @PostConstruct
    private void init() {
        algorithm = Algorithm.HMAC256(secretkey);}


    //方法签名payload
    public String encode(Map<String, Object> map) {
        // 当前时间，1ds过期
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        return JWT.create()
                .withPayload(map)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm);
    }
    //解密，返回DecodedJWT
    public DecodedJWT decode(String token) {
        try {
            return JWT.require(algorithm).build().verify(token);
        } catch (TokenExpiredException | SignatureVerificationException e) {//token过期与签名校验失败(被篡改等
            // )
            if (e instanceof SignatureVerificationException) {
                throw XException.builder().code(Code.FORBIDDEN).build();
            }
            throw XException.builder().code(Code.TOKEN_EXPIRED).build();
        }
    }
}


