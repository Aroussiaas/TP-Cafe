package tn.esprit.spring.tpcafearoussiahassen.Services;

import tn.esprit.spring.tpcafearoussiahassen.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;

import java.util.List;

public interface IPromotionService {
    Promotion addPromotion(Promotion p);
    List<Promotion> savePromotion(List<Promotion> promotions);
    Promotion selectPromotionByIdWithGet(long id);
    Promotion selectPromotionByIdWithOrElse(long id);
    List<Promotion> selectAllPromotions();
    void deletePromotion(Promotion p);
    void deleteAllPromotion();
    void deletePromotionById(long id);
    long countingPromotions();
    boolean verifyPromotionById(long id);
    // Nouvelles méthodes avec DTO
    Promotion addPromotionWithDTO(PromotionRequest promotionRequest);
    List<Promotion> savePromotionsWithDTO(List<PromotionRequest> promotionRequests);
    Promotion selectPromotionByIdWithGetWithDTO(long id);
    List<Promotion> selectAllPromotionsWithDTO();
    void deletePromotionWithDTO(PromotionRequest promotionRequest);

    /////--------------affectation promotionArticle----
    void affectPromotionAArticle(long idArticle, long idPromotion);
    void desaffectPromotionArticle(long idArticle, long idPromotion);

}
