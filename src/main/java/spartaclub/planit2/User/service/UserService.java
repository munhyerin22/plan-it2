package spartaclub.planit2.User.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spartaclub.planit2.User.dto.*;
import spartaclub.planit2.User.entity.User;
import spartaclub.planit2.User.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 생성
//    @Transactional
//    public ResponseUserDto create(RequestUserDto request) {
//        User username = new User(
//                request.username(),
//                request.email()
//        );
//        User saved = userRepository.save(username);
//        return new ResponseUserDto(
//                saved.getId(),
//                saved.getUser(),
//                saved.getEmail(),
//                saved.getCreatedAt(),
//                saved.getModifiedAt());
//    }

    //회원가입..?  (유저 생성)
    @Transactional
    public ResponseUserDto register(RequestUserDto request) { // 이 값을 받으면 우리는 작동한다.-> 반환값을 보내줄게
        if(userRepository.existsByEmail(request.email())) throw new IllegalArgumentException("이미 존재하는 사용자 입니다.");
        User user = new User(
                request.username(),
                request.email(),
                request.password()
        );
        // 저장할때 id 생성.
        // userRepository.save(user);
        // 다시 받아와서 사용. 저장하는 시점에서 savadUser로 받아와서 사용할 준비
        User savedUser = userRepository.save(user);
        return new ResponseUserDto(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    // 로그인
    public void signIn(RequestSignInDto signInDto, HttpSession session){
        // 유저가 있는존재하는지 확인
        User signIn = userRepository.findByEmail(signInDto.email()).orElseThrow(
                () -> new IllegalArgumentException("유저가 없습니다.")
        );
        // 비밀번호가 같은지 확인
        if(!signIn.getPassword().equals(signInDto.password())){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }
        // 세션에 등록
        session.setAttribute("LogInUser", new LoginResponseUserDto(
                signIn.getId(), signIn.getUsername(), signIn.getEmail()));

    }



    // 단건 조회
    @Transactional(readOnly = true)
    public GetOneResponseUserDto getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException(("일정이 없습니다.")));

        return new GetOneResponseUserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetOneResponseUserDto> getAll(String name) {
        List<User> users = (name != null)
                ? userRepository.findAllByUsernameOrderByModifiedAtDesc(name)
                : userRepository.findAllByOrderByModifiedAtDesc();

        return users.stream()
                .map(user -> new GetOneResponseUserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getCreatedAt(),
                        user.getModifiedAt()
                ))
                .toList();
    }

    // 수정(update)
    @Transactional
    public UpdateResponseUserDto update(Long id, RequestUserDto request) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException(("일정이 없습니다."))
        ); // 수정 시 id요청
        user.update( // 수정시 입력할 값+수정일(시간)
                request.username());
        return new UpdateResponseUserDto(
                user.getId(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    //삭제(delete)
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                        -> new IllegalArgumentException(("일정이 없습니다.")));
        userRepository.delete(user);
    }
}
