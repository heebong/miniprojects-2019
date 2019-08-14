package com.woowacourse.edd.presentation.exceptionhandler;

import com.woowacourse.edd.exceptions.InvalidTitleException;
import com.woowacourse.edd.exceptions.InvalidYoutubeIdException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = {"com.woowacourse.edd.presentation.controller"})
public class VideoExceptionHandler {
    @ResponseBody
    @ExceptionHandler(InvalidTitleException.class)
    public Error handleTitleError(InvalidTitleException e) {
        return new Error("FAIL", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InvalidYoutubeIdException.class)
    public Error handleYoutubeIdError(InvalidYoutubeIdException e) {
        return new Error("FAIL", e.getMessage());
    }
}
