package com.PrismaMobi.Prisma_Mobi.entities.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Origin {

    private Double latitudeOrigin;
    private Double longitudeOrigin;
}
