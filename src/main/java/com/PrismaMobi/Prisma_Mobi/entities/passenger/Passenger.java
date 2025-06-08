package com.PrismaMobi.Prisma_Mobi.entities.passenger;

import com.PrismaMobi.Prisma_Mobi.entities.enums.Gender;
import com.PrismaMobi.Prisma_Mobi.entities.users.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Table
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    private String cpf;
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;

    public void setInactive() {
        this.active = false;
    }
}
