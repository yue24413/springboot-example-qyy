package com.example.backendexamples.vo;

import com.example.backendexamples.exception.Code;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String message;
    private Object data;


    //每次都builder返回一个resultVO的对象，麻烦,内部进行封装
    public static ResultVO success(Object data) {
       return ResultVO.builder()
               .code(200)
               .data(data)
               .build();
    }
    //对于ok,每次都返回一个200，没有数据传入，如果每次创建对象，不妥
   //建一个独立的空对象处理ok
    /*
    public static ResultVO ok() {
        return ResultVO
                .builder()
                .code(200)
                .build();
    }
    */
    public static final ResultVO ok = ResultVO.builder()
            .code(200)
            .build();

    //通用error
    public static ResultVO error(Code code) {
        return ResultVO.builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();
    }
    //加自定义error
    public static ResultVO error(int code,String message) {
        return ResultVO.builder()
                .code(code)
                .message(message)
                .build();
    }
}
