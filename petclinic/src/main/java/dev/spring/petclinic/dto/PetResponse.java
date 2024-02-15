package dev.spring.petclinic.dto;

import dev.spring.petclinic.model.Pet;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PetResponse {
    private Long id;
    private String name;
    // 여기는 Owner 없음


    public PetResponse(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static PetResponse from(Pet pet){
        // Pet 엔티티에 담긴 개별 값 추출
        final Long id = pet.getId();
        final String name = pet.getName();

        return new PetResponse(id, name);
    }
}
