package me.stef.bluemooncity;

public class MyErrorCode {

    private final int code;
    private final String message;

    public MyErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final MyErrorCode USER_ALREADY_EXISTS = new MyErrorCode(10000, "Already exists");
    public static final MyErrorCode USER_NOT_FOUND = new MyErrorCode(10001, "Not found");
    public static final MyErrorCode JSON_IO = new MyErrorCode(10002, "Json IO");
    public static final MyErrorCode INVALID_EMAIL_TOKEN = new MyErrorCode(10003, "Invalid Email Token");
    public static final MyErrorCode EXPIRED_EMAIL_TOKEN = new MyErrorCode(10004, "Invalid Email Token");

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
