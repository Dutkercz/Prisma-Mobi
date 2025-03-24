package com.PrismaMobi.Prisma_Mobi.entities.driver;

import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import com.PrismaMobi.Prisma_Mobi.entities.vehicle.Vehicle;
import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
}
