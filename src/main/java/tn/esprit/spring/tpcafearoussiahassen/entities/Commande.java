package tn.esprit.spring.tpcafearoussiahassen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "commande")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private LocalDate dateCommande;
    @Temporal(TemporalType.DATE)
    private float totalCommande;
    @Enumerated(EnumType.STRING)
    private StatusCommande statusCommande;

    @ManyToOne
    Client client;
    @OneToMany(mappedBy = "commande")
    List<DetailCommande> detailCommandeList;

}