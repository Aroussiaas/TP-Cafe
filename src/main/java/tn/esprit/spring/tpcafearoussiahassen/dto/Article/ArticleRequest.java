package tn.esprit.spring.tpcafearoussiahassen.dto.Article;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.tpcafearoussiahassen.entities.TypeArticle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ArticleRequest {
    String nomArticle;
    float prixArticle;
    TypeArticle typeArticle;
}
