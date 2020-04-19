package edu.xpu.fileserver;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResultVO uploadException(MaxUploadSizeExceededException e) throws IOException {
        return ResultVOUtil.error(1, "文件大小超出限制");
    }

    @ExceptionHandler(Exception.class)
    public void myexce(Exception e) {
        System.out.println("myexce>>>"+e.getMessage());
    }
}