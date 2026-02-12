package spartaclub.planit2.dto;

import java.time.LocalDateTime;

public record UpdateResponsePlanitDto (
        Long id, String title, String content, String name, LocalDateTime modifiedAt) {}
