package tn.esprit.spring.tpcafearoussiahassen.dto.Commande;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafearoussiahassen.entities.DetailCommande;
import tn.esprit.spring.tpcafearoussiahassen.entities.StatusCommande;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CommandeRequest1 {
    Long idCommande;
    LocalDate dateCommande;
    float totalCommande;
    StatusCommande statusCommande;
    List<DetailCommandeRequest> detailCommandes;

}
