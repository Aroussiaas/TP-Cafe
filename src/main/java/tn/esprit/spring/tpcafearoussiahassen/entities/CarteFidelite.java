package tn.esprit.spring.tpcafearoussiahassen.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Entity
@Table(name = "carteFidelite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarteFidelite;
    private int pointsAccumules;
    private LocalDate dateCreation;
    @OneToOne(mappedBy = "carteFidelite")
    Client client;
}
