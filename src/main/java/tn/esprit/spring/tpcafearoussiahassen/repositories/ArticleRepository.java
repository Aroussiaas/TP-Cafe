package tn.esprit.spring.tpcafearoussiahassen.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafearoussiahassen.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Long> {
    // 1. Trouver les articles par nom exact
    // KEYWORD : exact, equals, nomArticle
    List<Article> findByNomArticle(String nomArticle);

    @Query("SELECT a FROM Article a WHERE a.nomArticle = ?1")
    List<Article> findByNomArticleJPQL(String nomArticle);


    // 2. Trouver les articles par type
    // KEYWORD : typeArticle, filtering, equals
    @Query("SELECT a FROM Article a WHERE a.typeArticle = ?1")
    List<Article> findByTypeArticleJPQL(String typeArticle);


    // 3. Trouver les articles par prix exact
    // KEYWORD : price, exact match
    List<Article> findByPrixArticle(float prix);

    @Query("SELECT a FROM Article a WHERE a.prixArticle = ?1")
    List<Article> findByPrixArticleJPQL(float prix);


    // 4. Vérifier existence d’un article par nom
    // KEYWORD : exists, boolean
    boolean existsByNomArticle(String nomArticle);


    // 5. Compter les articles par type
    // KEYWORD : count, aggregation
    @Query("SELECT COUNT(a) FROM Article a WHERE a.typeArticle = ?1")
    long countByTypeArticleJPQL(String typeArticle);


    // 6. Nom contenant + type spécifique
    // KEYWORD : contains, AND, text search
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE %?1% AND a.typeArticle = ?2")
    List<Article> findByNomArticleContainingAndTypeArticleJPQL(String nom, String typeArticle);


    // 7. Prix entre min et max + type
    // KEYWORD : between, range, filtering
    @Query("SELECT a FROM Article a WHERE a.prixArticle BETWEEN ?1 AND ?2 AND a.typeArticle = ?3")
    List<Article> findByPrixArticleBetweenAndTypeArticleInJPQL(float minPrix, float maxPrix, String typeArticle);


    // 8. Nom commence par (insensible casse) + tri prix
    // KEYWORD : startsWith, ignoreCase, orderBy
    List<Article> findByNomArticleStartingWithIgnoreCaseOrderByPrixArticle(String prefix);

    @Query("SELECT a FROM Article a WHERE UPPER(a.nomArticle) LIKE UPPER(CONCAT(:prefix, '%')) ORDER BY a.prixArticle")
    List<Article> findByNomArticleStartingWithIgnoreCaseOrderByPrixArticleJPQL(@Param("prefix") String prefix);


    // 9. Article le plus cher par type
    // KEYWORD : max, subquery, highestPrice
    @Query("""
           SELECT a FROM Article a 
           WHERE a.typeArticle = :type 
           AND a.prixArticle = (
               SELECT MAX(a2.prixArticle) FROM Article a2 WHERE a2.typeArticle = :type
           )
           """)
    Article findArticleByTypeWithMaxPriceJPQL(@Param("type") String typeArticle);


    // 10. Nom contient ou type, tri décroissant
    // KEYWORD : OR, contains, orderingDesc
//    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE %?1% OR a.typeArticle LIKE %?2% ORDER BY a.prixArticle DESC")
//    List<Article> findByNomArticleContainingOrTypeArticleOrderByPrixArticleDescJPQL(String nom, String typeArticle);
//

    // 11. Nom commence par
    // KEYWORD : startsWith, prefix, LIKE
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT(:prefix, '%')")
    List<Article> findByNomArticleStartingWithJPQL(@Param("prefix") String prefix);


    // 12. Nom se termine par
    // KEYWORD : endsWith, suffix, LIKE
    List<Article> findByNomArticleEndingWith(String suffix);

    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT('%', :suffix)")
    List<Article> findByNomArticleEndingWithJPQL(@Param("suffix") String suffix);


    // 13. Type null
    // KEYWORD : isNull, missingType
    List<Article> findByTypeArticleIsNull();

    @Query("SELECT a FROM Article a WHERE a.typeArticle IS NULL")
    List<Article> findByTypeArticleIsNullJPQL();


    // 14. Prix non null
    // KEYWORD : isNotNull, filtering
    List<Article> findByPrixArticleIsNotNull();

    @Query("SELECT a FROM Article a WHERE a.prixArticle IS NOT NULL")
    List<Article> findByPrixArticleIsNotNullJPQL();


    // 15. Promotions actives
    // KEYWORD : join, dateRange, activePromotions
    @Query("""
           SELECT a FROM Article a 
           JOIN a.promotion p 
           WHERE CURRENT_DATE BETWEEN p.dateDebutPromo AND p.dateFinPromo
           """)
    List<Article> findArticlesWithActivePromotionsJPQL();


    // 16. Nom contient + prix entre min et max
    // KEYWORD : contains, priceRange, AND
    @Query("""
           SELECT a FROM Article a 
           WHERE a.nomArticle LIKE %:nom% 
           AND a.prixArticle BETWEEN :minPrix AND :maxPrix
           """)
    List<Article> findByNomArticleContainingAndPrixArticleBetweenJPQL(
            @Param("nom") String nom,
            @Param("minPrix") float minPrix,
            @Param("maxPrix") float maxPrix
    );
}
