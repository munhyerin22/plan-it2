package spartaclub.planit2.User.dto;

import java.time.LocalDateTime;

public record ResponseUserDto( // user정보
        Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
