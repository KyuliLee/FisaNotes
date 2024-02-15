package dev.spring.petclinic.repository;

import dev.spring.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
    CrudRepository - CRUD 위주의 기능을 제공하는 인터페이스
    CrudRepository<Entity Type, Id> // 타입은 기본 타입으로 못 넣음. 무조건 참조 타입. 그래서 long 안 되고 Long으로.

    참고 : JpaRepository - CRUD + 페이징 기능을 제공하는 인터페이스
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
        List<Owner> findAllByLastNameLike(String lastName);
        Owner findOwnerById(Long id);// optional 안쓰고 하는 방법 이라기보단 정해진 규칙을 갖는 이름을 통해 메소드를 만들면 쿼리가 자동생성됨
}
