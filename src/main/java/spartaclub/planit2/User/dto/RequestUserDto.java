package spartaclub.planit2.User.dto;

public record RequestUserDto( // user 정보 입력 -> 회원가입
        String username, String email, String password) {}
