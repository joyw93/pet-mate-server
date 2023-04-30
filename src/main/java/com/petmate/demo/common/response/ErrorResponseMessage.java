package com.petmate.demo.common.response;

public class ErrorResponseMessage {
    /** 공통 */
    public static final String VALID_ERROR = "유효성 검사 실패";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부에러 발생";

    /** 유저 */
    public static final String EMAIL_ALREADY_EXISTS = "이미 존재하는 이메일입니다.";
    public static final String NICKNAME_ALREADY_EXISTS = "이미 존재하는 닉네임입니다.";
    public static final String USER_SIGN_UP_FAILED = "회원가입에 실패했습니다.";

}
