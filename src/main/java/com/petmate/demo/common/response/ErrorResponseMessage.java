package com.petmate.demo.common.response;

public class ErrorResponseMessage {
    /** 공통 */
    public static final String VALID_ERROR = "유효성 검사 실패";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부에러 발생";
    public static final String UNAUTHORIZED_ACCESS = "인가되지 않은 접근입니다.";
    /** 유저 */
    public static final String EMAIL_ALREADY_EXISTS = "이미 존재하는 이메일입니다.";
    public static final String NICKNAME_ALREADY_EXISTS = "이미 존재하는 닉네임입니다.";
    public static final String USER_SIGN_UP_FAILED = "회원가입에 실패했습니다.";
    public static final String EMAIL_NOT_FOUND = "존재하지 않는 이메일입니다.";
    public static final String USER_NOT_FOUND = "유저가 존재하지 않습니다.";
    public static final String LOGIN_FAILED = "로그인 실패";

    /** 커뮤니티 */
    public static final String POST_FAILED = "게시물 등록 실패";
    public static final String POST_NOT_FOUND = "게시물 조회 실패";

}
