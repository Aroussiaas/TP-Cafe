package tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DetailCommandeResponse {
    Long idDetailCommande;
    int quantiteArticle;
    float sousTotalDetailArticle;
    float sousTotalDetailArticleApresPromo;

    public Long getIdDetailCommande() {
        return idDetailCommande;
    }

    public void setIdDetailCommande(Long idDetailCommande) {
        this.idDetailCommande = idDetailCommande;
    }
}
