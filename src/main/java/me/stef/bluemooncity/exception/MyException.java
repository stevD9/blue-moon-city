package me.stef.bluemooncity.exception;

import me.stef.bluemooncity.MyErrorCode;

public class MyException extends RuntimeException {

    private final int code;

    public MyException(MyErrorCode errorCode) {
        super(errorCode.getMessage());
        code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
