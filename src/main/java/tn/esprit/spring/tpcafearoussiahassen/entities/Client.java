package tn.esprit.spring.tpcafearoussiahassen.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;


    @OneToOne(cascade = CascadeType.ALL)
    Adresse adresse;
    @OneToOne(cascade = CascadeType.ALL)
    CarteFidelite carteFidelite;
    @OneToMany(mappedBy = "client" )
    List<Commande> commande;


}
