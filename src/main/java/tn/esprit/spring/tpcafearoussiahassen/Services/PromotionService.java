package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafearoussiahassen.entities.Article;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;
import tn.esprit.spring.tpcafearoussiahassen.mappers.PromotionMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.ArticleRepository;
import tn.esprit.spring.tpcafearoussiahassen.repositories.PromotionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Component
@AllArgsConstructor

public class PromotionService implements IPromotionService{

    PromotionRepository repo;
    PromotionMapper mapper;
    ArticleRepository articleRepository;

    @Override
    public Promotion addPromotion(Promotion p) {
        return repo.save(p);
    }

    @Override
    public List<Promotion> savePromotion(List<Promotion> promotions) {
        return repo.saveAll(promotions);
    }

    @Override
    public Promotion selectPromotionByIdWithGet(long id) {
        return repo.findById(id).get();
    }

    @Override
    public Promotion selectPromotionByIdWithOrElse(long id) {
        Promotion fakePromotion = Promotion.builder().pourcentagePromo("25")
                .dateDebutPromo(LocalDate.ofEpochDay(25/12/25))
                .dateFinPromo(LocalDate.ofEpochDay(85/78/5879)).build();
        return repo.findById(id).orElse(fakePromotion);
    }

    @Override
    public List<Promotion> selectAllPromotions() {
        return repo.findAll();
    }

    @Override
    public void deletePromotion(Promotion p) {
        repo.delete(p);

    }

    @Override
    public void deleteAllPromotion() {
        repo.deleteAll();

    }

    @Override
    public void deletePromotionById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingPromotions() {
        return repo.count();
    }

    @Override
    public boolean verifyPromotionById(long id) {
        return repo.existsById(id);
    }

    // -----------------------Méthodes avec DTO-----------

    @Override
    public Promotion addPromotionWithDTO(PromotionRequest promotionRequest) {
        Promotion promotion = mapper.fromDTOToEntity(promotionRequest);
        Promotion savedPromotion = repo.save(promotion);
        return mapper.fromEntityToDTO(savedPromotion);
    }

    @Override
    public List<Promotion> savePromotionsWithDTO(List<PromotionRequest> promotionRequests) {
        // Convertir DTOs en entités
        List<Promotion> promotions = promotionRequests.stream()
                .map(mapper::fromDTOToEntity)
                .collect(Collectors.toList());

        // Persister les entités
        List<Promotion> promotionsAdded = repo.saveAll(promotions);

        // Convertir les entités sauvegardées en DTOs de réponse
        return promotionsAdded.stream()
                .map(mapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Promotion selectPromotionByIdWithGetWithDTO(long id) {
        Promotion promotion = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion non trouvée avec l'ID: " + id));
        return mapper.fromEntityToDTO(promotion);
    }

    @Override
    public List<Promotion> selectAllPromotionsWithDTO() {
        List<Promotion> promotions = repo.findAll();
        return promotions.stream()
                .map(mapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePromotionWithDTO(PromotionRequest promotionRequest) {
        Promotion promotion = mapper.fromDTOToEntity(promotionRequest);
        repo.delete(promotion);
    }
    // -----------------------affectPromoAuArticle-----------

    @Override
    public void affectPromotionAArticle(long idArticle, long idPromotion) {
        //findById (keyword)--> Article (parent)
        Promotion promo = repo.findById(idPromotion).get();
        // findById(JPQL) --> Promotion(child:une liste de promotion)
        Article article = articleRepository.findById(idArticle).get();
        //Appliquer la regle : on affecte le child au parent ==> article.getPromotions().add(promotion)
        article.getPromotion().add(promo);
        //persister les modifs ->parentRepository.save(instance du parent)
        articleRepository.save(article);

    }

    @Override
    public void desaffectPromotionArticle(long idArticle, long idPromotion) {
        //findById (keyword)--> Article (parent)
        Promotion promo = repo.findById(idPromotion).get();
        // findById(JPQL) --> Promotion(child:une liste de promotion)
        Article article = articleRepository.findById(idArticle).get();
        //Appliquer la regle : on desaffecte le child au parent ==> article.getPromotions().remove(promotion)
        article.getPromotion().remove(promo);
        //persister les modifs ->parentRepository.save(instance du parent)
        articleRepository.save(article);
    }

    //////findById (keyword)--> Article (parent)
    //        Promotion promo = repo.findById(idPromotion).get();
    //        // findById(JPQL) --> Promotion(child:une liste de promlotion)
    //        Article article = articleRepository.findById(idArticle).get();
    //        //Appliquer la regle : on affecte le child au parent ==> article.getPromotions().add(promotion)
    //        article.getPromotion().add(promo);
    //        //persister les modifs ->parentRepository.save(instance du parent)
    //        articleRepository.save(article);
}
