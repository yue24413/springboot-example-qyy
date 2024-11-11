package com.example.backendexamples.exception;

import com.example.backendexamples.exception.Code;
import lombok.*;

/*非受检异常*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*这是一个子类，会调父类的构造函数*/
@EqualsAndHashCode(callSuper=true)
public class XException extends RuntimeException{
    private Code code;//可以有，也可以没有，所以没有加finnal
    private int number;//通用的没有的异常code,则也要有对应的message
    private String message;
}
