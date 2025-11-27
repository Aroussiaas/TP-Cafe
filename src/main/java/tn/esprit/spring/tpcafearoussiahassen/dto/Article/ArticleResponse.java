package tn.esprit.spring.tpcafearoussiahassen.dto.Article;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.tpcafearoussiahassen.entities.TypeArticle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ArticleResponse {
    Long idArticle;
    String nomArticle;
    float prixArticle;
    TypeArticle typeArticle;
}
