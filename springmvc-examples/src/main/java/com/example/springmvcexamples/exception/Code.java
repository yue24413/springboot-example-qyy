package com.example.springmvcexamples.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Code {
    LOGIN_ERROR(400, "用户名密码错误"),
    BAD_REQUEST(400, "请求错误"),
    UNAUTHORIZED(401, "未登录"),
    TOKEN_EXPIRED(403, "过期请重新登录"),
    FORBIDDEN(403, "无权限");
    public static final int ERROR = 400;
    private final int code;
    private final String message;
}
/**

 以下是一些Lombok常用的注解及其在Spring Boot中的作用：

 @Data：这个注解会为类生成getter、setter、toString、equals和hashCode方法，通常用于bean类。它相当于同时使用了@ToString, @EqualsAndHashCode, @Getter on all non-static fields, and @Setter on all non-final fields。
 @AllArgsConstructor：这个注解会为类生成包含所有成员变量的构造函数，对于需要通过构造函数来初始化所有属性的情况非常有用。
 @NoArgsConstructor：这个注解会为类生成一个无参构造函数，这对于一些框架（如Hibernate）来说是必需的，因为它们需要一个默认构造函数来创建实例。
 @Builder：此注解来自Lombok的ModelMapper扩展，它允许使用流畅的API来构建对象。例如，你可以用它来创建一个“builder”模式而不需要手动编写。
 @Getter 和 @Setter：分别用于生成getter和setter方法。可以应用于特定的字段或整个类。
 @ToString：生成toString方法，通常用于调试目的，可以方便地查看对象的状态。
 @EqualsAndHashCode：用于生成equals和hashcode方法，这对于确保对象在集合中的正确行为非常重要。
 @@RequiredArgsConstructor 是 Lombok 库提供的一个注解，用于生成一个包含所有 final 或者 @NonNull 注解的字段的构造器。
    不可变对象：当对象的属性被声明为 final 时，意味着这些属性必须在构造函数中设置，之后不能更改。@RequiredArgsConstructor 自动生成这样一个构造函数。
    依赖注入：在 Spring 框架中，经常使用构造器注入来提供 bean 的依赖。对于那些必须有某些依赖才能正常工作的类，使用 @RequiredArgsConstructor 可以简化构造器的编写。
 */
