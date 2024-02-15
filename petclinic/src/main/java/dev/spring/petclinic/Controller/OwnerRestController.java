package dev.spring.petclinic.Controller;

import dev.spring.petclinic.dto.OwnerResponse;
import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/owners") // localhost:8080/api/owners 로 요청할 경우 동작할 핸들러
@RestController
@RequiredArgsConstructor
public class OwnerRestController {

    // OwnerService 의존성 주입
    private final OwnerService ownerService;

    // 1. 기본적인 Owner CRUD API 구현

    // (1) 전체 Owner 목록 데이터를 JSON 형태로 반환
    @GetMapping
//        public List<Owner> listOwners() { // OwnerResponse로 순환 참조 에러 해결
    public List<OwnerResponse> listOwners() {
        List<Owner> owners = ownerService.findAll();

        // Owner -> OwnerResponse 형태로 변환
        List<OwnerResponse> ownerResponse = owners.stream()
                .map(OwnerResponse::from)
                .collect(Collectors.toList());
        return ownerResponse;
    }
    // (2) id로 Owner 조회
    @GetMapping("/{id}")
    public OwnerResponse idOwner(@PathVariable Long id){

        List<Owner> owners = ownerService.findAll();

        // Owner -> OwnerResponse 형태로 변환
        List<OwnerResponse> ownerResponse = owners.stream()
                .map(OwnerResponse::from)
                .collect(Collectors.toList());

        // ownerResponse에서 id가 파라미터id와 일치하는 OwnerResponse를 찾자
        for(int i=0; i<ownerResponse.size(); i++){
            if(ownerResponse.get(i).getId() == id){
                return ownerResponse.get(i);
            }
        }

        return null;
    }


    // (3) 한 명의 Owner 등록 처리
    @PostMapping
    public Owner addOwner(@RequestBody Owner owner) {
        System.out.println("Owner = " + owner);

        ownerService.save(owner);
        return owner;
    }

    // (4) Owner 정보 수정 // firstName 바꾸기
    @PatchMapping("/{id}") // PATCH: localhost:8080/1 - PUT과 PATCH의 차이?
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner){

        Owner targetOwner = ownerService.findById(id).get();
        targetOwner.setFirstName(owner.getFirstName());
        ownerService.save(targetOwner);

        return targetOwner;
    }

    // (5) Owner 정보 제거
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id){
        Owner target = ownerService.findOwnerById(id);
        ownerService.delete(target);
    }

//     TODO
//
//2. Owner 등록 시 아래의 라이브러리를 활용하여 입력값에 대한 유효성을 검증하는
//    OwnerRequest DTO 생성 및 활용
//            (ex. firstName은 1글자 이상 30글자 이내 등)
//    활용 라이브러리 - https://beanvalidation.org/
//
//    build.gradle에 아래의 라이브러리가 설치되어 있는지 확인
//    implementation 'org.springframework.boot:spring-boot-starter-validation'

}
