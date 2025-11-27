package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.tpcafearoussiahassen.entities.DetailCommande;

import java.util.List;


public interface DetailCommandeRepository extends JpaRepository<DetailCommande,Long> {

    // 1. Trouver les détails de commande par quantité exacte
    List<DetailCommande> findByQuantiteArticle(int quantite);

    // 2. Trouver les détails par sous-total exact
    List<DetailCommande> findBySousTotalDetailArticle(float sousTotal);

    // 3. Compter les détails avec plus de X quantités
    long countByQuantiteArticleGreaterThan(int quantite);

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    boolean existsBySousTotalDetailArticleGreaterThan(float minSousTotal);

    // 5. Trouver les détails avec une quantité dans une plage et un sous-total minimum
    List<DetailCommande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThanEqual(int minQuantite, int maxQuantite, float minSousTotal);

    // 6. Trouver les détails avec un sous-total dans une plage, triés par quantité
    List<DetailCommande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticle(float minSousTotal, float maxSousTotal);

    // 7. Trouver les détails avec un sous-total après promotion dans une plage
    List<DetailCommande> findBySousTotalDetailArticleApresPromoBetween(float minSousTotal, float maxSousTotal);

    // 8. Trouver les détails par quantité ou sous-total minimum
    List<DetailCommande> findByQuantiteArticleGreaterThanOrSousTotalDetailArticleGreaterThanEqual(int quantite, float sousTotal);

    // 9. Trouver les 5 détails les plus chers
    List<DetailCommande> findTop5ByOrderBySousTotalDetailArticleDesc();

    // 10. Trouver les détails sans quantité renseignée
    List<DetailCommande> findByQuantiteArticleIsNull();

    // 11. Trouver les détails avec un sous-total après promotion renseigné
    List<DetailCommande> findBySousTotalDetailArticleApresPromoIsNotNull();

    // 12. Trouver les détails avec leur commande et article
    @Query("SELECT d FROM DetailCommande d JOIN FETCH d.commande JOIN FETCH d.article WHERE d.idDetailCommande = :id")
    DetailCommande findDetailWithCommandeAndArticle(Long id);

}
