package spartaclub.planit2.User.dto;

import java.time.LocalDateTime;

public record UpdateResponseUserDto( // 수정할 때 돌려주는 폼
        Long id, String username, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
