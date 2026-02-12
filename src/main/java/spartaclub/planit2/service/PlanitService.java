package spartaclub.planit2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spartaclub.planit2.dto.GetOneResponsePlanitDto;
import spartaclub.planit2.dto.ResponsePlanitDto;
import spartaclub.planit2.dto.RequestPlanitDto;
import spartaclub.planit2.entity.Planit;
import spartaclub.planit2.repository.PlanitRepository;

import java.time.LocalDateTime;
import java.util.List;

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

    // 단건 조회
    @Transactional(readOnly = true)
    public GetOneResponsePlanitDto getOne(Long id) {
        Planit planit = planitRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException(("일정이 없습니다.")));

        return new GetOneResponsePlanitDto(
                planit.getId(),
                planit.getTitle(),
                planit.getContent(),
                planit.getName(),
                planit.getCreateAt());
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetOneResponsePlanitDto> getAll(String name) {
        List<Planit> planits = (name != null)
                ? planitRepository.findAllByNameOrderByCreateAtDesc(name)
                : planitRepository.findAllByOrderByCreateAtDesc();

        return planits.stream()
                .map(planit -> new GetOneResponsePlanitDto(
                        planit.getId(),
                        planit.getTitle(),
                        planit.getContent(),
                        planit.getName(),
                        planit.getCreateAt()
                ))
                .toList();
    }
}
