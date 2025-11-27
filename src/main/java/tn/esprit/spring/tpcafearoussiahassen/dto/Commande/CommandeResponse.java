package tn.esprit.spring.tpcafearoussiahassen.dto.Commande;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.tpcafearoussiahassen.entities.StatusCommande;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CommandeResponse {
    Long idCommande;
    LocalDate dateCommande;
    float totalCommande;
    StatusCommande statusCommande;
}
