package spartaclub.planit2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spartaclub.planit2.dto.GetOneResponsePlanitDto;
import spartaclub.planit2.dto.ResponsePlanitDto;
import spartaclub.planit2.dto.RequestPlanitDto;
import spartaclub.planit2.dto.UpdateResponsePlanitDto;
import spartaclub.planit2.service.PlanitService;

import java.util.List;

@RestController
@RequestMapping("/planit2")
@RequiredArgsConstructor
public class PlanitController {
    private final PlanitService planitService;

    //생성
    @PostMapping
    public ResponseEntity<ResponsePlanitDto> create (@RequestBody RequestPlanitDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(planitService.create(request));
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetOneResponsePlanitDto> getOne (@PathVariable Long id) {
        return ResponseEntity.ok(planitService.getOne(id));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetOneResponsePlanitDto>> getAll(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(planitService.getAll(name));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateResponsePlanitDto> update(@PathVariable Long id, @RequestBody RequestPlanitDto request) {
        return ResponseEntity.ok(planitService.update(id, request));
    }

}
