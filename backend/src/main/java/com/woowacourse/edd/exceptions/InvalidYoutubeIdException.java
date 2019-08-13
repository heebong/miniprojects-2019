package com.woowacourse.edd.exceptions;

public class InvalidYoutubeIdException extends RuntimeException {
    private static String message = "유투브 아이디는 필수로 입력해야합니다.";
    public InvalidYoutubeIdException() {
        super(message);
    }
}
