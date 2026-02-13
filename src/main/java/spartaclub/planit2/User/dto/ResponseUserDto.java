package spartaclub.planit2.User.dto;

import java.time.LocalDateTime;

public record ResponseUserDto(
        Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
// 이렇게 받으면 우리는 작동을 한다.