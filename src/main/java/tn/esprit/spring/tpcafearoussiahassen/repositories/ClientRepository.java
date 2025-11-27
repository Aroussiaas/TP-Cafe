package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafearoussiahassen.entities.Client;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    // 1. Trouver tous les clients par nom de famille
    List<Client> findByNom(String nom);

    // 2. Trouver les clients par prénom
    List<Client> findByPrenom(String prenom);

    // 3. Trouver un client spécifique par nom et prénom
    Client findByNomAndPrenom(String nom, String prenom);

    // 4. Vérifier si un client existe par son nom
    boolean existsByNom(String nom);

    // 5. Compter les clients nés après une certaine date
    long countByDateNaissanceAfter(LocalDate date);

    // 6. Trouver les clients dont le nom ou le prénom contient une chaîne
    List<Client> findByNomContainingOrPrenomContaining(String nom, String prenom);

    // 7. Trouver les clients dont le nom et le prénom contient une chaîne
    List<Client> findByNomContainingAndPrenomContaining(String nom, String prenom);

    // 8. Trouver les clients nés entre deux dates
    List<Client> findByDateNaissanceBetween(LocalDate startDate, LocalDate endDate);

    // 9. Trouver les clients dont le nom commence par et nés avant une date
    List<Client> findByNomStartingWithAndDateNaissanceBefore(String nomPrefix, LocalDate date);

    // 10. Trouver les clients par ville de leur adresse (jointure implicite)
    List<Client> findByAdresseVille(String ville);

    // 11. Trouver les clients par nom contient une chaine triés les clients par prénom ASC
    List<Client> findByNomContainingOrderByPrenomAsc(String nom);

    // 12. Trouver les clients par nom contient une chaine triés les clients par prénom DESC
    List<Client> findByNomContainingOrderByPrenomDesc(String nom);

    // 13. Trouver les clients dont le nom commence par une lettre spécifique
    List<Client> findByNomStartingWith(String prefix);

    // 14. Trouver les clients dont le prénom se termine par une terminaison
    List<Client> findByPrenomEndingWith(String suffix);

    // 15. Trouver les clients sans date de naissance renseignée
    List<Client> findByDateNaissanceIsNull();

    // 16. Trouver les clients avec une adresse renseignée
    List<Client> findByAdresseIsNotNull();

    // 17. Trouver les clients des plusieurs villes
    List<Client> findByAdresseVilleIn(List<String> villes);

    // 18. Trouver les clients dont les ptsAccumules de leur carte fidélite supérieur à la valeur passée en paramètre
    List<Client> findByCarteFidelitePointsAccumulesGreaterThan(int points);

    // 19. Trouver les clients dont les ptsAccumules de leur carte fidélite supérieur ou égale à la valeur passée en paramètre
    List<Client> findByCarteFidelitePointsAccumulesGreaterThanEqual(int points);

    // 20. Trouver les clients dont les ptsAccumules de leur carte fidélite entre deux valeurs passées en paramètre
    List<Client> findByCarteFidelitePointsAccumulesBetween(int minPoints, int maxPoints);

    // 21. Trouver les clients qui ont commandés un article dont son nom est passé en paramètre
    @Query("SELECT DISTINCT c FROM Client c JOIN c.commande cmd JOIN cmd.detailCommandeList dtl JOIN dtl.article a WHERE a.nomArticle = :nomArticle")
    List<Client> findClientsByArticleNom(@Param("nomArticle") String nomArticle);

    // 22. Trouver les clients dont son nom contient une chaine passée en paramètre qui ont commandés des articles dont son type est passé en paramètre
    @Query("SELECT DISTINCT c FROM Client c JOIN c.commande cmd JOIN cmd.detailCommandeList dtl JOIN dtl.article a WHERE c.nom LIKE %:nom% AND a.typeArticle = :typeArticle")
    List<Client> findClientsByNomContainingAndArticleType(@Param("nom") String nom, @Param("typeArticle") String typeArticle);

    @Query("select c from Client c where  c.nom = :nom and c.prenom = :prenom")
    Client recupererClientByNomEtPrenom(@Param("nom") String nom , @Param("prenom") String prenom);



}
