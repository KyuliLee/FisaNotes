package dev.spring.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{
    private LocalDate birthDate;

    private String name;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    public Pet(Long id) {
        super(id);
    }

//    @Builder
//    public Pet(String name, LocalDate birthDate) {
//        this.name = name;
//        this.birthDate = birthDate;
//    }
}
