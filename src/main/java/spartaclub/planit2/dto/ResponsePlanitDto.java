package spartaclub.planit2.dto;

import java.time.LocalDateTime;

public record ResponsePlanitDto(
   Long id, String title, String content, String name, LocalDateTime createAt) {}
