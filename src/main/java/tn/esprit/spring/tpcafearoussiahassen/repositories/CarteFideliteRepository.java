package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafearoussiahassen.entities.CarteFidelite;
import java.time.LocalDate;
import java.util.List;

public interface CarteFideliteRepository extends JpaRepository<CarteFidelite,Long> {
 // 1. Trouver les cartes avec un nombre précis de points
 // KEYWORD : findBy, equality, exact match
 List<CarteFidelite> findByPointsAccumules(int points);

 @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules = :points")
 List<CarteFidelite> findByPointsAccumulesJPQL(@Param("points") int points);

 // 2. Trouver les cartes par date de création
 // KEYWORD : getBy, equality, date matching
 List<CarteFidelite> getByDateCreation(LocalDate date);

 @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation = :date")
 List<CarteFidelite> getByDateCreationJPQL(@Param("date") LocalDate date);

 // 3. Trouver les cartes avec plus de X points
 // KEYWORD : GreaterThan, comparison
 List<CarteFidelite> getByPointsAccumulesGreaterThan(int min);

 @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules > :min")
 List<CarteFidelite> getByPointsAccumulesGreaterThanJPQL(@Param("min") int min);

 // 4. Supprimer les cartes créées avant une date
 // KEYWORD : deleteBy, Before, date comparison
 void deleteCarteFideliteByDateCreationBefore(LocalDate date);

 @Query("DELETE FROM CarteFidelite c WHERE c.dateCreation < :date")
 void deleteCarteFideliteByDateCreationBeforeJPQL(@Param("date") LocalDate date);

 // 5. Trouver les cartes par intervalle de points et date > X
 // KEYWORD : Between, And, After
 List<CarteFidelite> findByPointsAccumulesBetweenAndDateCreationAfter(int minPoints, int maxPoints, LocalDate date);

 @Query("""
           SELECT c FROM CarteFidelite c
           WHERE c.pointsAccumules BETWEEN :minPoints AND :maxPoints
           AND c.dateCreation > :date
           """)
 List<CarteFidelite> findByPointsAccumulesBetweenAndDateCreationAfterJPQL(
         @Param("minPoints") int minPoints,
         @Param("maxPoints") int maxPoints,
         @Param("date") LocalDate date);

 // 6. Trouver les cartes créées après une date et trier DESC
 // KEYWORD : GreaterThanEqual, OrderBy, Desc
 List<CarteFidelite> findByDateCreationGreaterThanEqualOrderByDateCreationDesc(LocalDate date);

 @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation >= :date ORDER BY c.dateCreation DESC")
 List<CarteFidelite> findByDateCreationGreaterThanEqualOrderByDateCreationDescJPQL(@Param("date") LocalDate date);

 // 7. Trouver les cartes créées entre deux dates
 // KEYWORD : Between, date interval
 List<CarteFidelite> findByDateCreationBetween(LocalDate startDate, LocalDate endDate);

 @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation BETWEEN :startDate AND :endDate")
 List<CarteFidelite> findByDateCreationBetweenJPQL(
         @Param("startDate") LocalDate startDate,
         @Param("endDate") LocalDate endDate);

 // 8. Points < X OU date avant Y
 // KEYWORD : LessThan, Or, Before
 List<CarteFidelite> findByPointsAccumulesLessThanOrDateCreationBefore(int maxPoints, LocalDate date);

 @Query("""
           SELECT c FROM CarteFidelite c
           WHERE c.pointsAccumules < :maxPoints
           OR c.dateCreation < :date
           """)
 List<CarteFidelite> findByPointsAccumulesLessThanOrDateCreationBeforeJPQL(
         @Param("maxPoints") int maxPoints,
         @Param("date") LocalDate date);

 // 9. Trouver la carte ayant le plus de points
 // KEYWORD : findFirstBy, OrderBy, Desc
 CarteFidelite findFirstByOrderByPointsAccumulesDesc();

 @Query("SELECT c FROM CarteFidelite c ORDER BY c.pointsAccumules DESC LIMIT 1")
 CarteFidelite findFirstByOrderByPointsAccumulesDescJPQL();

 // 10. Cartes n’ayant pas de date de création
 // KEYWORD : IsNull
 List<CarteFidelite> findByDateCreationIsNull();

 @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation IS NULL")
 List<CarteFidelite> findByDateCreationIsNullJPQL();

 // 11. Cartes où les points ne sont pas null
 // KEYWORD : IsNotNull
 List<CarteFidelite> findByPointsAccumulesIsNotNull();

 @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules IS NOT NULL")
 List<CarteFidelite> findByPointsAccumulesIsNotNullJPQL();

 // 12. Trouver par nom + prénom du client
 // KEYWORD : And, nested properties
 CarteFidelite findByClientNomAndClientPrenom(String nom, String prenom);

 @Query("""
           SELECT c FROM CarteFidelite c
           WHERE c.client.nom = :nom AND c.client.prenom = :prenom
           """)
 CarteFidelite findByClientNomAndClientPrenomJPQL(
         @Param("nom") String nom,
         @Param("prenom") String prenom);

 // 13. Top 5 cartes avec le plus de points
 // KEYWORD : Top, OrderBy, Limit
 List<CarteFidelite> findTop5ByOrderByPointsAccumulesDesc();

 @Query("SELECT c FROM CarteFidelite c ORDER BY c.pointsAccumules DESC LIMIT 5")
 List<CarteFidelite> findTop5ByOrderByPointsAccumulesDescJPQL();

}
