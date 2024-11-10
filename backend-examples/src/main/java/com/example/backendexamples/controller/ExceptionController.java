package com.example.backendexamples.controller;

import com.example.backendexamples.exception.Code;
import com.example.backendexamples.exception.XException;
import com.example.backendexamples.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ExceptionController    {
    /*处理自定义异常，一旦捕获到XException类异常，会把异常类型的对象当作方法参数注入*/
    @ExceptionHandler(XException.class)
    public ResultVO handleXexception(XException e) {
        /*有两类，通用异常码以及其他数字及信息*/
        if(e.getCode()!=null){
            return ResultVO.error(e.getCode());
        }
        return ResultVO.error(e.getNumber(),e.getMessage());
    }
    //兜底的异常类，任何异常都是这个异常的子类，非受检异常未处理
    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e) {
        /*没有异常码，统一按error400处理*/
      return ResultVO.error(Code.ERROR,e.getMessage());
    }

}
