package com.woowacourse.edd.exceptions;

public class InvalidTitleException extends RuntimeException {
    private static String message = "제목은 한 글자 이상이어야합니다";
    public InvalidTitleException() {
        super(message);
    }
}
