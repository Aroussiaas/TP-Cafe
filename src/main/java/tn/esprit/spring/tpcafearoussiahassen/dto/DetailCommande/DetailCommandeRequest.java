package tn.esprit.spring.tpcafearoussiahassen.dto.DetailCommande;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)


public class DetailCommandeRequest {
    int quantiteArticle;
    float sousTotalDetailArticle;
    float sousTotalDetailArticleApresPromo;

}
