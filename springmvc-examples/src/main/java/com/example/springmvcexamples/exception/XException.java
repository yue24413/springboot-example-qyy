package com.example.springmvcexamples.exception;

import lombok.*;

/*非受检异常*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*这是一个子类，会调父类的构造函数*/
@EqualsAndHashCode(callSuper = true)
public class XException extends RuntimeException{
    private Code code;//可以有，也可以没有，因此没有加 final 关键字。
    private int number;//通用的没有特定的异常代码（code），则使用这个通用的异常代码。则也要有对应的message
    private String message;
}
