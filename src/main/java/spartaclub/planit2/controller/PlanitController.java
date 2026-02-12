package spartaclub.planit2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spartaclub.planit2.dto.ResponsePlanitDto;
import spartaclub.planit2.dto.RequestPlanitDto;
import spartaclub.planit2.service.PlanitService;

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

}
