package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // @Component 어노테이션의 직관적 네이밍용 어노테이션
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    // 만약 OwnerService를 구현한 구현체가 2개 이상이면 클래스 위에 @Qualifier 어노테이션을 넣어준다.


    //JPA 의존성
    private final OwnerRepository ownerRepository;
    // CrudRepository<Owner, Long> 를 extends 한 OwnerRepository 인터페이스를 선언하면
    // 스프링에서 @RequiredArgsConstructor 에 의해
    // ownerRepository = new ownerRepository의 구현체
    // 처럼 ownerRepository를 초기화할 구현체를 자동으로 넣어준다.
    // 그래서 @RequiredArgsConstructor 를 쓰고 인터페이스를 선언하면 ownerRepository의 구현체를 만들지 않고도
    // ownerRepository의 구현체를 사용할 수 있다.

    // 전체 Owner 데이터 조회 요청 예시
   public List<Owner> findAll() {
        // 기존 JPA manager.find(), manager.createQuery("select o from Owners as o").getResultList();
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);

        return owners;
    }
    @Override
    public Owner findByLastName(String lastName){
        // LastName으로 검색 처리를 수행할 로직 작성

        // 전체 데이터 조회 요청 예시
        // 기존 JPA : manager.find(), manager.createQuery("select o from Owners as o").getResultList();
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add); // 콜론 2개 있는 문법 : 메소드 참조 표현식
        System.out.println(owners);
        return null;
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

        @Override
        public Optional<Owner> findById(Long id){ // 구현체는 인터페이스에 없는 메서드를 사용할 수 없으므로 OwnerService 에서도 만들어줬음.
            return ownerRepository.findById(id);
        // CRUDRepository 인터페이스에 findById 메서드가 이미 있어서
        // ownerRepository.findById를 사용 가능
    }


    @Override
    public Owner findOwnerById(Long id) {
        return ownerRepository.findOwnerById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

   public void delete(Owner owner){
        ownerRepository.delete(owner);
   }

}
