package spartaclub.planit2.User.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spartaclub.planit2.User.dto.*;
import spartaclub.planit2.User.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/planit2")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //생성
//    @PostMapping
//    public ResponseEntity<ResponsePlanitDto> create (@RequestBody RequestPlanitDto request){
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
//    }

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseUserDto> register(@Valid @RequestBody RequestUserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }

    // 로그인
    @PostMapping("/login")// 조회 get은 바디를 쓸 수 없기 때문에 Post로 한다.
    public ResponseEntity<String> signIn(@RequestBody RequestSignInDto signInDto, HttpSession session){
        userService.signIn(signInDto, session);
        return ResponseEntity.ok("로그인 성공");
    }
    //세션을 활용해서 getA~ 사용하기. 학습하기

    // 타입과 변수명 구분 @RequiredArgsConstructor를 선언함으로 21번줄

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetOneResponseUserDto> getOne (@PathVariable Long id) {
        return ResponseEntity.ok(userService.getOne(id));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetOneResponseUserDto>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(userService.getAll(name));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponseUserDto> update(@PathVariable Long id, @RequestBody RequestUserDto request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
