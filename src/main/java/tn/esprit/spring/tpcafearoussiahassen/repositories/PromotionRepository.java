package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository <Promotion, Long> {

    // 1. Trouver les promotions par pourcentage exact
    List<Promotion> findByPourcentagePromo(String pourcentage);

    // 2. Trouver les promotions par date de début
    List<Promotion> findByDateDebutPromo(LocalDate Debut);

    // 3. Trouver les promotions par date de fin
    List<Promotion> findByDateFinPromo(LocalDate dateFin);

    // 4. Vérifier l'existence d'une promotion par pourcentage
    boolean existsByPourcentagePromo(String pourcentage);

    // 5. Compter les promotions débutant après une date
    long countByDateDebutPromoAfter(LocalDate date);

    // 6. Trouver les promotions actives à une date donnée
    @Query("SELECT p FROM Promotion p WHERE p.dateDebutPromo <= :date AND (p.dateFinPromo IS NULL OR p.dateFinPromo >= :date)")
    List<Promotion> findActivePromotionsAtDate(@Param("date") LocalDate date);

    // 7. Trouver les promotions avec un pourcentage spécifique débutant dans une période
    List<Promotion> findByPourcentagePromoAndDateDebutPromoBetween(String pourcentage, LocalDate startDate, LocalDate endDate);

    // 8. Trouver les promotions valides à une date spécifique
    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.dateDebutPromo AND p.dateFinPromo")
    List<Promotion> findValidPromotionsAtDate(@Param("date") LocalDate date);

    // 9. Trouver les promotions avec certains pourcentages, triées par date de début
    List<Promotion> findByPourcentagePromoInOrderByDateDebutPromo(List<String> pourcentages);

    // 10. Trouver les promotions actives triées par pourcentage
    @Query("SELECT p FROM Promotion p WHERE CURRENT_DATE BETWEEN p.dateDebutPromo AND p.dateFinPromo ORDER BY p.pourcentagePromo")
    List<Promotion> findActivePromotionsOrderByPourcentage();

    // 11. Trouver les promotions sans date de fin
    List<Promotion> findByDateFinPromoIsNull();

    // 12. Trouver les promotions avec un pourcentage renseigné
    List<Promotion> findByPourcentagePromoIsNotNull();

    // 13. Trouver les promotions avec leurs articles associés
    @Query("SELECT p FROM Promotion p JOIN FETCH p.articles WHERE p.idPromotion = :id")
    Promotion findPromotionWithArticles(@Param("id") Long id);

    // 14. Trouver les promotions expirées
    @Query("SELECT p FROM Promotion p WHERE p.dateFinPromo < CURRENT_DATE")
    List<Promotion> findExpiredPromotions();

}
