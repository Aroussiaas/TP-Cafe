package tn.esprit.spring.tpcafearoussiahassen.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "article")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @Column(name = "nom_article")
    private String nomArticle;
    private float prixArticle;

    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle;

    @ManyToMany
    List<Promotion> promotion;

    @OneToMany(mappedBy = "article")
    List<DetailCommande> detailCommande;

}
