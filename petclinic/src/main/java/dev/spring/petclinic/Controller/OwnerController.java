package dev.spring.petclinic.Controller;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller // 스프링 빈으로 등록
@RequestMapping("/owners") // 요청 경로 맵핑, localhost:8080/owners로 요청할 경우 OwnerController가 담당
//@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    // OwnerService는 인터페이스. 구현체 없이 선언해서 사용할 수 없다.
    // 그런데  ownerService.findAllByLastNameLike가 어떻게 가능?
    //
    // private OwnerService ownerService = new OwnerServiceImpl(); 스프링 없이는 이렇게 쓸 것이다.
    // OwnerServiceImpl 클래스 위에 @Service로 OwnerServiceImpl 을 스프링 빈으로 등록해준다.
    // 그러면 OwnerServiceImpl 을 사용할 때마다 new로 생성할 필요 없이 스프링 컨테이너가 꺼내서 사용한다.
    // 따라서
    // OwnerController 생성자의 인자인 ownerService는 ownerService 인터페이스가 아니라
    // ownerSerivice를 구현한 OwnerServiceImpl 객체.(빈에 등록되어 있는)
    // 그래서 processFindForm 메서드에서
    // ownerService.findAllByLastNameLike 메서드를 사용할 수 있다.


    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // GET: localhost:8080/owners/find
    @RequestMapping(path = "/find") // requestMapping은 Get, POST 둘 다의 요청에 사용 가능.
    // 따라서 인자를 구분하기 위해 path = 를 명시해줌. 여기에서는 GET인지 POST인지 써 주지는 않았다.
    // 그래서 그냥 @RequestMapping("/find") 로 써도 무방

    public String findOwners(Model model) {
        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner); // 사실상 안 쓰는 객체인데 만들어 준 이유?
        // findOwners.html 의 14줄에 field="*{lastName}" 로 lastName을 사용하는 부분이 있다.
        // 에러 나지 않게 껍데기만 만들어줬음

        return "owners/findOwners"; // owners 폴더의 findOwners.html 을 렌더링
        // findOwners.html에서 Find Owner버튼을 누르면 form 태그 안의 action의 주소로 GET 요청 보냄

    }

    @GetMapping // @RequestMapping("/owners") 의 주소로 GET 요청이 들어오면 실행되는 부분

    public String processFindForm(Owner owner, BindResult result, Model model) {
        // 별도의 파라미터값 없이 /owners로 요청할 경우에는 모든 owners 데이터를 반환하도록
        if (owner.getLastName() == null)
            owner.setLastName(""); // 빈(empty) 값으로 검색할 수 있도록 공백 문자열 값 설정

        // OwnerService 호출
        List<Owner> results =
                ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        // TODO: 한 명의 Owner만 검색될 경우 해당 Owner의 디테일 페이지로 바로 이동되도록 구현
        // TODO: 2명 이상의 Owner가 검색될 경우 전체 Owner 목록으로 조회되도록
        if (results.size() == 1) {
            // 한 명의 Owner만 검색될 경우
            // results의 첫 번째 객체를 가져와서 addAttribute를 넣어주고
            // @GetMapping("/{id}") 로 요청

            owner = results.get(0);
            Long id = owner.getId();
            model.addAttribute("owner", owner);
            return "owners/ownerDetails";
        }


        // results 객체(Model 데이터)를 ownersList.html로 전달할 것임
        // listOwners라는 이름의 key값으로 전체 Owners 목록 데이터(results)를 전달
        model.addAttribute("listOwners", results);

        return "owners/ownersList"; // owners/ownersList.html로 렌더링
        // ownersList.html 의 <a th:href="@{/owners/__${owner.id}__}" 부분
        // 주소가 동적으로 변한다. 
    }

    @GetMapping("/{id}") // owners/(동적으로변하는)id 주소 // a tag는 GET 요청을 보내니까 GetMapping으로 받음
    public String showInfo(@PathVariable Long id, Model model) {
        // 동적으로 받은 (path에 있는) id를 메서드에서 사용하기 위해 @PathVariable 어노테이션 사용

        // 받아온 id의 owner객체를 model 객체에 넣어주자.
        // findById 의 리턴 타입은 Optional
        // Optional은 nullPointerException을 방지하기 위해 객체를 한 번 감싸주는 역할
        // 일단 Optional로 받아오고 Optional을 풀어주자.
        Optional<Owner> optionalOwner = ownerService.findById(id);
        optionalOwner.ifPresent(o -> model.addAttribute("owner", o));

        return "owners/ownerDetails";
        // model.addAttribute("owner", 서비스의 리턴); // 첫 번째 인자 "owner"은 ownerDetail.html 의 12번째줄의 owner 이름을 그대로 갖고옴

    }


}
