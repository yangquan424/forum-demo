package com.cheer.forumDemo.web.controller;


import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

//全局处理异常
@ControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler    //此标签后带括号的话，括号里表示能处理的异常；不带则处理所以异常
    public String handler(Exception e, Model model){
        model.addAttribute("message",e.getMessage());
        model.addAttribute("cause",e);
        return "error/error";
    }

    /**
     * 404异常
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class})
    public String NoHandlerFound(Exception e,Model model){
        model.addAttribute("message", e.getMessage());
        model.addAttribute("cause", e.getCause());
        return "error/404";
    }

    /*//空对象
    @ExceptionHandler(NullPointerException.class)
    public String nullPointer(NullPointerException e,Model model){
        return null;
    }

    //接受非法参数
    @ExceptionHandler(IllegalAccessException.class)
    public String illegalAccess(){
        return null;
    }

    //算术异常
    @ExceptionHandler(ArithmeticException.class)
    public String arithmetic(){
        return null;
    }

    //格式化数据异常
    @ExceptionHandler(NumberFormatException.class)
    public String numberFormat(){
        return null;
    }

    //文件读写异常
    @ExceptionHandler(IOException.class)
    public String ioe(){
        return null;
    }*/


}
