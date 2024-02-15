package dev.spring.petclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "owners")
public class Owner extends BaseEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;

//    @JsonIgnore // Postman에서 전체 Owner 목록 조회힐 떼 에러나서 @OneToMany, @ManyToOne 있는 곳 둘 다에 @JsonIgnore 넣어주면 됨
    // 하지만 DTO를 만드는 것이 좋음
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, List<Pet> pets) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }


}
