package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.tpcafearoussiahassen.entities.Adresse;
import java.util.List;


public interface AdresseRepository extends JpaRepository<Adresse,Long> {

    @Override
    List<Adresse> findAll();

    // 1. Trouver toutes les adresses d'une ville spécifique
    List<Adresse> findByVille(String ville);

    // 2. Trouver les adresses par code postal exact
    List<Adresse> findByCodePostal(int codePostal);

    // 3. Compter le nombre d'adresses dans une ville
    long countByVille(String ville);

    // 4. Supprimer toutes les adresses d'une ville
    void deleteByVille(String ville);

    // 5. Trouver les adresses d'une ville avec un code postal spécifique
    List<Adresse> findByVilleAndCodePostal(String ville, int codePostal);

    // 6. Trouver les adresses dont la rue contient un mot, insensible à la casse de la ville
    List<Adresse> findByRueContainingIgnoreCaseAndVille(String rue, String ville);

    // 7. Trouver les adresses situées dans une liste de villes
    List<Adresse> findByVilleIn(List<String> villes);

    // 8. Trouver les adresses avec un code postal dans une plage spécifique
    List<Adresse> findByCodePostalBetween(int minCodePostal, int maxCodePostal);

    // 9. Trouver les adresses avec un code postal supérieur au code postal passé en paramètre
    List<Adresse> findByCodePostalGreaterThan(int codePostal);

    // 10. Trouver les adresses avec un code postal supérieur ou égal au code postal passé en paramètre
    List<Adresse> findByCodePostalGreaterThanEqual(int codePostal);

    // 11. Trouver les adresses avec un code postal inférieur au code postal passé en paramètre
    List<Adresse> findByCodePostalLessThan(int codePostal);

    // 12. Trouver les adresses avec un code postal inférieur ou égal au code postal passé en paramètre
    List<Adresse> findByCodePostalLessThanEqual(int codePostal);

    // 13. Trouver les adresses dont la rue commence par, dans une ville, triées par code postal
    List<Adresse> findByRueStartingWithAndVilleOrderByCodePostal(String ruePrefix, String ville);

    // 14. Trouver les adresses dont le nom de rue commence par une chaîne spécifique
    List<Adresse> findByRueStartingWith(String ruePrefix);

    // 15. Trouver les adresses dont le nom de ville se termine par une terminaison spécifique
    List<Adresse> findByVilleEndingWith(String villeSuffix);

    // 16. Trouver les adresses où le champ rue est nul
    List<Adresse> findByRueIsNull();

    // 17. Trouver les adresses où la ville n'est pas nul
    List<Adresse> findByVilleIsNotNull();
    //----recherche rue par adresse
    Adresse findByRue(String rue);

}
