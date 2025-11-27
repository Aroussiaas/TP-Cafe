package tn.esprit.spring.tpcafearoussiahassen.mappers;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Article;

@Mapper(componentModel = "spring",uses = {PromotionMapper.class})

public interface ArticleMapper {
    Article fromDTOToEntity(ArticleRequest article);
    Article fromDTOToEntity2(ArticleResponse article);
    ArticleResponse fromEntityToDTO(Article article);
}
