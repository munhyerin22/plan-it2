package spartaclub.planit2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spartaclub.planit2.dto.ResponsePlanitDto;
import spartaclub.planit2.dto.RequestPlanitDto;
import spartaclub.planit2.entity.Planit;
import spartaclub.planit2.repository.PlanitRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlanitService {
    private final PlanitRepository planitRepository;

    // 생성
    @Transactional
    public ResponsePlanitDto create(RequestPlanitDto request) {
        Planit planit = new Planit(
                request.title(),
                request.content(),
                request.name(),
                LocalDateTime.now());
        Planit saved = planitRepository.save(planit);
        return new ResponsePlanitDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getName(),
                saved.getCreateAt());
    }


}
