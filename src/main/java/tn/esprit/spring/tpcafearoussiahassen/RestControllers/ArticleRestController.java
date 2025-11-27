package tn.esprit.spring.tpcafearoussiahassen.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafearoussiahassen.Services.ArticleService;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafearoussiahassen.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafearoussiahassen.entities.Article;
import tn.esprit.spring.tpcafearoussiahassen.entities.Promotion;

import java.util.List;

@RestController
//@Component ou bien @Controller + @RestController
@AllArgsConstructor
@RequestMapping("article")
public class ArticleRestController {

    private final ArticleService articleService;


    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.selectAllArticles();
    }

    @PostMapping
    public Article addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PostMapping("/batch")
    public List<Article> saveArticles(@RequestBody List<Article> articles) {
        return articleService.saveArticle(articles);
    }

    @GetMapping("/{id}")
    public Article getArticleByIdWithGet(@PathVariable long id) {
        return articleService.selectArticleByIdWithGet(id);
    }

    @GetMapping("/{id}/orelse")
    public Article getArticleByIdWithOrElse(@PathVariable long id) {
        return articleService.selectArticleByIdWithOrElse(id);
    }

    @GetMapping("/count")
    public long countArticles() {
        return articleService.countingArticles();
    }

    @GetMapping("/{id}/exists")
    public boolean verifyArticleExists(@PathVariable long id) {
        return articleService.verifyArticleById(id);
    }

    @DeleteMapping
    public void deleteArticle(@RequestBody Article article) {
        articleService.deleteArticle(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleService.deleteArticleById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllArticles() {
        articleService.deleteAllArticle();
    }

    // ----------------------- DTO endpoints -----------------------

    @PostMapping("/dto")
    public ArticleResponse addArticleWithDTO(@RequestBody ArticleRequest articleRequest) {
        return articleService.addArticleWithDTO(articleRequest);
    }

    @PostMapping("/dto/batch")
    public List<ArticleResponse> saveArticlesWithDTO(@RequestBody List<ArticleRequest> articleRequests) {
        return articleService.saveArticlesWithDTO(articleRequests);
    }

    @GetMapping("/dto/{id}")
    public ArticleResponse getArticleByIdWithDTO(@PathVariable Long id) {
        return articleService.selectArticleByIdWithGetWithDTO(id);
    }

    @GetMapping("/dto")
    public List<ArticleResponse> getAllArticlesWithDTO() {
        return articleService.selectAllArticlesWithDTO();
    }

    @PostMapping("/ajouterPromotionEtAffecterArticle")
    public void ajouterPromotionEtAffecterArticle(@RequestParam long idArticle, @RequestBody Promotion promo) {
        articleService.ajouterPromotionEtAffecterArticle(idArticle, promo);
    }


}
