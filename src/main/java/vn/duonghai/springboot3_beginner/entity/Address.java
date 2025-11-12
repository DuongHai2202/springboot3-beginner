package vn.duonghai.springboot3_beginner.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_address")
public class Address extends AbstractEntity {

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name = "floor")
    private String floor;

    @Column(name = "building")
    private String building;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "address_type")
    private Integer addressType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
