package tn.esprit.spring.tpcafearoussiahassen.Services;


import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Article;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;

import java.util.List;

public interface IArticleService {
    Article addArticle(Article art);
    List<Article> saveArticle(List<Article> articles);
    Article selectArticleByIdWithGet(long id);
    Article selectArticleByIdWithOrElse(long id);
    List<Article> selectAllArticles();
    void deleteArticle(Article art);
    void deleteAllArticle();
    void deleteArticleById(long id);
    long countingArticles();
    boolean verifyArticleById(long id);

    // -----------------------Méthodes avec DTO-----------
    ArticleResponse addArticleWithDTO(ArticleRequest articleRequest);
    List<ArticleResponse> saveArticlesWithDTO(List<ArticleRequest> articleRequests);
    ArticleResponse selectArticleByIdWithGetWithDTO(long id);
    ArticleResponse selectArticleByIdWithOrElseWithDTO(long id);
    List<ArticleResponse> selectAllArticlesWithDTO();
    void deleteArticleWithDTO(ArticleRequest articleRequest);

    void ajouterPromotionEtAffecterArticle(long idArticle, Promotion promo);
}
