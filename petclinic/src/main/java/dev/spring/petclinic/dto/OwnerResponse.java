package dev.spring.petclinic.dto;

/*
    owner에 대한 응답 데이터 전달용 DTO 클래스
 */

import dev.spring.petclinic.model.Owner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class OwnerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private List<PetResponse> pets;

    @Builder
    private OwnerResponse(Long id, String firstName, String lastName, String address, String city, String telephone, List<PetResponse> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    // 정적 팩토리 메서드

    public static OwnerResponse from(Owner owner) {
        // Owner 엔티티에 담긴 개별 값 추출
        final Long id = owner.getId();
        final String firstName = owner.getFirstName();
        final String lastName = owner.getLastName();
        final String address = owner.getAddress();
        final String city = owner.getCity();
        final String telephone = owner.getTelephone();
        final List<PetResponse> pets = owner.getPets().stream()
                .map(PetResponse::from)
                .collect(Collectors.toList());

        return new  OwnerResponse(id, firstName, lastName, address, city, telephone, pets);

    }
}

