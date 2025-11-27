package tn.esprit.spring.tpcafearoussiahassen.dto.Promotion;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PromotionRequest {
    String pourcentagePromo;
    LocalDate dateDebutPromo;
    LocalDate dateFinPromo;

}
