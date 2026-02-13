package spartaclub.planit2.schedule.dto;

import java.time.LocalDateTime;

public record UpdateResponsePlanitDto (
        Long id, String title, String content, String name, LocalDateTime modifiedAt) {}
