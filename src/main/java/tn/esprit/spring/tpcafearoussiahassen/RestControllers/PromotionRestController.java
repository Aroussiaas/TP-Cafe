package tn.esprit.spring.tpcafearoussiahassen.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.PromotionService;
import tn.esprit.spring.tpcafearoussiahassen.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;

import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("promotion")


public class PromotionRestController {

    private final PromotionService promotionService;


//    @GetMapping
//    public List<Promotion> findAll() {
//
//        return promotionService.selectAllPromotions();
//
//    }

    @PostMapping
    public Promotion addPromotion(@RequestBody Promotion promotion) {
        return promotionService.addPromotion(promotion);
    }

    @PostMapping("/batch")
    public List<Promotion> savePromotions(@RequestBody List<Promotion> promotions) {
        return promotionService.savePromotion(promotions);
    }

    // READ
    @GetMapping("/{id}/get")
    public Promotion getPromotionByIdWithGet(@PathVariable long id) {
        return promotionService.selectPromotionByIdWithGet(id);
    }

    @GetMapping("/{id}/orelse")
    public Promotion getPromotionByIdWithOrElse(@PathVariable long id) {
        return promotionService.selectPromotionByIdWithOrElse(id);
    }

    @GetMapping
    public List<Promotion> getAllPromotions() {
        return promotionService.selectAllPromotions();
    }

    @GetMapping("/count")
    public long countPromotions() {
        return promotionService.countingPromotions();
    }

    @GetMapping("/{id}/exists")
    public boolean verifyPromotionExists(@PathVariable long id) {
        return promotionService.verifyPromotionById(id);
    }

    // DELETE
    @DeleteMapping
    public void deletePromotion(@RequestBody Promotion promotion) {
        promotionService.deletePromotion(promotion);
    }

    @DeleteMapping("/{id}")
    public void deletePromotionById(@PathVariable long id) {
        promotionService.deletePromotionById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllPromotions() {
        promotionService.deleteAllPromotion();
    }

    //------------------ DTOs -----------------------

    @PostMapping("/addPromotionWithDTO")
    public Promotion addPromotionWithDTO(PromotionRequest promotionRequest) {
        return promotionService.addPromotionWithDTO(promotionRequest);
    }

    @GetMapping("/savePromotionsWithDTO")
    public List<Promotion> savePromotionsWithDTO(List<PromotionRequest> promotionRequests) {
        return promotionService.savePromotionsWithDTO(promotionRequests);
    }


    @GetMapping("/selectPromotionByIdWithGetWithDTO/{id}")
    public Promotion selectPromotionByIdWithGetWithDTO(long id) {
        return promotionService.selectPromotionByIdWithGetWithDTO(id);
    }

    @GetMapping("/selectAllPromotionsWithDTO")
    public List<Promotion> selectAllPromotionsWithDTO() {
        return promotionService.selectAllPromotionsWithDTO();
    }

    @DeleteMapping("/deletePromotionWithDTO")
    public void deletePromotionWithDTO(PromotionRequest promotionRequest) {
        promotionService.deletePromotionWithDTO(promotionRequest);
    }

    @GetMapping("/affectPromotionAArticle/{idArticle}/{idPromotion}")
    public void affectPromotionAArticle(@PathVariable long idArticle, @PathVariable long idPromotion) {
        promotionService.affectPromotionAArticle(idArticle, idPromotion);
    }

    @GetMapping("/desaffectPromotionArticle/{idArticle}/{idPromotion}")
    public void desaffectPromotionArticle(@PathVariable long idArticle, @PathVariable long idPromotion) {
        promotionService.desaffectPromotionArticle(idArticle, idPromotion);
    }


}
