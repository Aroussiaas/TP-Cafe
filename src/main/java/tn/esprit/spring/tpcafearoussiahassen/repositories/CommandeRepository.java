package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafearoussiahassen.entities.Commande;
import tn.esprit.spring.tpcafearoussiahassen.entities.StatusCommande;

import java.time.LocalDate;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande,Long> {

    // 1. Trouver les commandes par statut
    // KEYWORD : findBy, equality, enum match
    List<Commande> findByStatusCommande(StatusCommande status);

    @Query("SELECT c FROM Commande c WHERE c.statusCommande = :status")
    List<Commande> findByStatusCommandeJPQL(@Param("status") StatusCommande status);

    // 2. Trouver les commandes par date exacte
    // KEYWORD : findBy, equality
    List<Commande> findByDateCommande(LocalDate dateCommande);

    @Query("SELECT c FROM Commande c WHERE c.dateCommande = :date")
    List<Commande> findByDateCommandeJPQL(@Param("date") LocalDate date);

    // 3. Compter les commandes par statut
    // KEYWORD : countBy
    long countByStatusCommande(StatusCommande status);

    @Query("SELECT COUNT(c) FROM Commande c WHERE c.statusCommande = :status")
    long countByStatusCommandeJPQL(@Param("status") StatusCommande status);

    // 4. Supprimer les commandes antérieures à une date
    // KEYWORD : deleteBy, Before
    void deleteByDateCommandeBefore(LocalDate date);

    @Query("DELETE FROM Commande c WHERE c.dateCommande < :date")
    void deleteByDateCommandeBeforeJPQL(@Param("date") LocalDate date);

    // 5. Trouver commandes entre 2 dates + statut
    // KEYWORD : Between, And
    List<Commande> findByDateCommandeBetweenAndStatusCommande(
            LocalDate startDate,
            LocalDate endDate,
            StatusCommande status
    );

    @Query("""

            SELECT c FROM Commande c
            WHERE c.dateCommande BETWEEN :start AND :end
            AND c.statusCommande = :status
           """)
    List<Commande> findByDateCommandeBetweenAndStatusCommandeJPQL(
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("status") StatusCommande status);

    // 6. Total > montant ET statut différent
    // KEYWORD : GreaterThan, And, Not
    List<Commande> findByTotalCommandeGreaterThanAndStatusCommandeNot(
            float montant,
            StatusCommande status);

    @Query("""

            SELECT c FROM Commande c
           WHERE c.totalCommande > :montant
           AND c.statusCommande <> :status
           """)
    List<Commande> findByTotalCommandeGreaterThanAndStatusCommandeNotJPQL(
            @Param("montant") float montant,
            @Param("status") StatusCommande status);

    // 7. Trouver commandes par liste de statuts + tri par date
    // KEYWORD : In, OrderBy
    List<Commande> findByStatusCommandeInOrderByDateCommande(List<StatusCommande> statusList);

    @Query("""
           SELECT c FROM Commande c
           WHERE c.statusCommande IN :statuses
           ORDER BY c.dateCommande
           """)
    List<Commande> findByStatusCommandeInOrderByDateCommandeJPQL(
            @Param("statuses") List<StatusCommande> statuses);

    // 8. Commandes avant une date avec total dans plage
    // KEYWORD : Before, And, Between
    List<Commande> findByDateCommandeBeforeAndTotalCommandeBetween(
            LocalDate date,
            float minTotal,
            float maxTotal);

    @Query("""
           SELECT c FROM Commande c
           WHERE c.dateCommande < :date
           AND c.totalCommande BETWEEN :min AND :max
           """)
    List<Commande> findByDateCommandeBeforeAndTotalCommandeBetweenJPQL(
            @Param("date") LocalDate date,
            @Param("min") float min,
            @Param("max") float max);

    // 9. Statut se terminant par une lettre
    // KEYWORD : EndingWith, LIKE, suffix
    List<Commande> findByStatusCommandeEndingWith(String suffix);

//    @Query("""
//           SELECT c FROM Commande c
//           WHERE c.statusCommande LIKE CONCAT('%', :suffix)
//           """)
//    List<Commande> findByStatusCommandeEndingWithJPQL(@Param("suffix") String suffix);

    // 10. Commandes sans statut
    // KEYWORD : IsNull
    List<Commande> findByStatusCommandeIsNull();

    @Query("SELECT c FROM Commande c WHERE c.statusCommande IS NULL")
    List<Commande> findByStatusCommandeIsNullJPQL();

    // 11. Total non null
    // KEYWORD : IsNotNull
    List<Commande> findByTotalCommandeIsNotNull();

    @Query("SELECT c FROM Commande c WHERE c.totalCommande IS NOT NULL")
    List<Commande> findByTotalCommandeIsNotNullJPQL();

    // 12. Commande + ses détails + client
    // KEYWORD : JOIN FETCH
    @Query("""
           SELECT c FROM Commande c
           JOIN FETCH c.detailCommandeList
           JOIN FETCH c.client
           WHERE c.idCommande = :id
           """)
    Commande findCommandeWithDetailsAndClient(@Param("id") Long id);

    // 13. Top 3 commandes les plus récentes
    // KEYWORD : Top, OrderBy, Desc
    List<Commande> findTop3ByOrderByDateCommandeDesc();

    @Query("""
           SELECT c FROM Commande c
           ORDER BY c.dateCommande DESC
           LIMIT 3
           """)
    List<Commande> findTop3ByOrderByDateCommandeDescJPQL();
}