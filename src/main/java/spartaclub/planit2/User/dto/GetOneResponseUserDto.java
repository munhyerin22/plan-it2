package spartaclub.planit2.User.dto;

import java.time.LocalDateTime;

public record GetOneResponseUserDto( //단건조회 응답
        Long id, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
