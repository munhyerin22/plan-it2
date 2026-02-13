package spartaclub.planit2.User.dto;

import java.time.LocalDateTime;

public record UpdateResponseUserDto(
        Long id, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
