package tn.esprit.spring.tpcafearoussiahassen.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Article;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;
import tn.esprit.spring.tpcafearoussiahassen.entities.TypeArticle;
import tn.esprit.spring.tpcafearoussiahassen.mappers.ArticleMapper;
import tn.esprit.spring.tpcafearoussiahassen.repositories.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
//@Component
@AllArgsConstructor

public class ArticleService implements IArticleService {

    ArticleRepository repo;
    ArticleMapper mapper;
    PromotionService promoService;

    @Override
    public Article addArticle(Article art) {
        return repo.save(art);
    }

    @Override
    public List<Article> saveArticle(List<Article> articles) {

        return repo.saveAll(articles);
    }

    @Override
    public Article selectArticleByIdWithGet(long id) {

        return repo.findById(id).get();
    }

    @Override
    public Article selectArticleByIdWithOrElse(long id) {
        Article fakeArticle = Article.builder().nomArticle("koujina").prixArticle(2500)
                .typeArticle(TypeArticle.BOISSON).build();
        return  repo.findById(id).orElse(fakeArticle);
    }

    @Override
    public List<Article> selectAllArticles() {

        return repo.findAll();
    }

    @Override
    public void deleteArticle(Article art) {
        repo.delete(art);

    }

    @Override
    public void deleteAllArticle() {
        repo.deleteAll();

    }

    @Override
    public void deleteArticleById(long id) {
        repo.deleteById(id);

    }

    @Override
    public long countingArticles() {
        return repo.count();
    }

    @Override
    public boolean verifyArticleById(long id) {
        return repo.existsById(id);
    }

    // -----------------------Méthodes avec DTO-----------

    @Override
    public ArticleResponse addArticleWithDTO(ArticleRequest articleRequest) {
        Article article = mapper.fromDTOToEntity(articleRequest);
        Article articleSaved = repo.save(article);
        return mapper.fromEntityToDTO(articleSaved);
    }

    @Override
    public List<ArticleResponse> saveArticlesWithDTO(List<ArticleRequest> articleRequests) {
        // Convertir DTOs en entités
        List<Article> articles = new ArrayList<>();
        for (ArticleRequest req : articleRequests) {
            articles.add(mapper.fromDTOToEntity(req));
        }

        // Persister les entités
        List<Article> articlesAdded = repo.saveAll(articles);

        // Convertir les entités sauvegardées en DTOs de réponse
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articlesAdded) {
            articleResponses.add(mapper.fromEntityToDTO(article));
        }
        return articleResponses;
    }

    @Override
    public ArticleResponse selectArticleByIdWithGetWithDTO(long id) {
        Article article = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Article non trouvé avec l'ID: " + id));
        return mapper.fromEntityToDTO(article);
    }

    @Override
    public ArticleResponse selectArticleByIdWithOrElseWithDTO(long id) {
        Article fakeArticle = Article.builder()
                .nomArticle("Article par défaut")
                .prixArticle(0)
                .typeArticle(TypeArticle.BOISSON)
                .build();

        Article article = repo.findById(id).orElse(fakeArticle);
        return mapper.fromEntityToDTO(article);
    }

    @Override
    public List<ArticleResponse> selectAllArticlesWithDTO() {
        List<Article> articles = repo.findAll();
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            articleResponses.add(mapper.fromEntityToDTO(article));
        }
        return articleResponses;
    }

    @Override
    public void deleteArticleWithDTO(ArticleRequest articleRequest) {
        Article article = mapper.fromDTOToEntity(articleRequest);
        repo.delete(article);
    }

    @Override
    public void ajouterPromotionEtAffecterArticle(long idArticle, Promotion promo) {
        Article article = repo.findById(idArticle).get();
        article.getPromotion().add(promo);
        repo.save(article);
    }


}
