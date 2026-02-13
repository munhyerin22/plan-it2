package spartaclub.planit2.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spartaclub.planit2.User.entity.User;
import spartaclub.planit2.User.repository.UserRepository;
import spartaclub.planit2.schedule.dto.GetOneResponsePlanitDto;
import spartaclub.planit2.schedule.dto.RequestPlanitDto;
import spartaclub.planit2.schedule.dto.ResponsePlanitDto;
import spartaclub.planit2.schedule.dto.UpdateResponsePlanitDto;
import spartaclub.planit2.schedule.entity.Planit;
import spartaclub.planit2.schedule.repository.PlanitRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanitService {
    private final PlanitRepository planitRepository; //빈
    private final UserRepository userRepository;
    // 자동으로 생성..

    // 생성
    @Transactional
    public ResponsePlanitDto create(RequestPlanitDto request) {
        User user = userRepository.findByUsername(request.name()).orElseThrow(
                () -> new IllegalArgumentException("user가 없습니다.")
        ); // 작성자명

        Planit planit = new Planit(
                request.title(),
                request.content(),
                user); // user
        Planit saved = planitRepository.save(planit);
        return new ResponsePlanitDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getUser().getUsername(),
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
                planit.getUser().getUsername(),
                planit.getCreatedAt(),
                planit.getModifiedAt());
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetOneResponsePlanitDto> getAll(String name) {
        List<Planit> planits = (name != null)
                ? planitRepository.findAllByUserUsername(name)
                : planitRepository.findAllByOrderByModifiedAtDesc();

        return planits.stream()
                .map(planit -> new GetOneResponsePlanitDto(
                        planit.getId(),
                        planit.getTitle(),
                        planit.getContent(),
                        planit.getUser().getUsername(),
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
                request.title());
        return new UpdateResponsePlanitDto(
                planit.getId(),
                planit.getTitle(),
                planit.getContent(),
                planit.getUser().getUsername(),
                planit.getModifiedAt());
    }

    //삭제(delete)
    @Transactional
    public void delete(Long id) {
        Planit planit = planitRepository.findById(id).orElseThrow(()
                        -> new IllegalArgumentException(("일정이 없습니다.")));
        planitRepository.delete(planit);
    }
}
