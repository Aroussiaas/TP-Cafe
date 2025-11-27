package tn.esprit.spring.tpcafearoussiahassen.dto.CarteFidelite;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CarteFideliteResponse {
    Long idCarteFidelite;
    int pointsAccumules;
    LocalDate dateCreation;
}

