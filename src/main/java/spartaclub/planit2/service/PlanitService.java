package spartaclub.planit2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spartaclub.planit2.dto.GetOneResponsePlanitDto;
import spartaclub.planit2.dto.ResponsePlanitDto;
import spartaclub.planit2.dto.RequestPlanitDto;
import spartaclub.planit2.dto.UpdateResponsePlanitDto;
import spartaclub.planit2.entity.Planit;
import spartaclub.planit2.repository.PlanitRepository;

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
                request.name());
        Planit saved = planitRepository.save(planit);
        return new ResponsePlanitDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getName(),
                saved.getCreatedAt(),
                saved.getModifiedAt());
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
                planit.getCreatedAt(),
                planit.getModifiedAt());
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetOneResponsePlanitDto> getAll(String name) {
        List<Planit> planits = (name != null)
                ? planitRepository.findAllByNameOrderByModifiedAtDesc(name)
                : planitRepository.findAllByOrderByModifiedAtDesc();

        return planits.stream()
                .map(planit -> new GetOneResponsePlanitDto(
                        planit.getId(),
                        planit.getTitle(),
                        planit.getContent(),
                        planit.getName(),
                        planit.getCreatedAt(),
                        planit.getModifiedAt()
                ))
                .toList();
    }

    // 수정(update)
    @Transactional
    public UpdateResponsePlanitDto update(Long id, RequestPlanitDto request) {
        Planit planit = planitRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException(("일정이 없습니다."))
        ); // 수정 시 id요청
        planit.update( // 수정시 입력할 값+수정일(시간)
                request.title(),
                request.name());
        return new UpdateResponsePlanitDto(
                planit.getId(),
                planit.getTitle(),
                planit.getContent(),
                planit.getName(),
                planit.getModifiedAt());
    }
}
