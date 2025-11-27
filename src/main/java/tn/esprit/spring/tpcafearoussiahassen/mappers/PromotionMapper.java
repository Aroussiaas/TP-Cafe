package tn.esprit.spring.tpcafearoussiahassen.mappers;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafearoussiahassen.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Promotion.PromotionResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;


@Mapper(componentModel = "spring", uses = {ArticleMapper.class,ClientMapper.class})

public interface PromotionMapper {
    Promotion fromDTOToEntity(PromotionRequest promotion);
    Promotion fromDTOToEntity2(PromotionResponse promotion);
    Promotion fromEntityToDTO(Promotion promotion);
}
