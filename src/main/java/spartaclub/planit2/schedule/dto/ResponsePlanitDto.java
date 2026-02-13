package spartaclub.planit2.schedule.dto;

import java.time.LocalDateTime;

public record ResponsePlanitDto(
        Long id, String title, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {}
