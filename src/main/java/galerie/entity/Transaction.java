/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Lisa
 */

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Transaction {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private LocalDate venduLe;
    
    @Column(unique=true)
    private float prixVente;
    
    @ManyToOne
    private Exposition exposition;
    
    @ManyToOne
    private Personne personne;
    
    @OneToOne
    private Tableau oeuvre;
}
