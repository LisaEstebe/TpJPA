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
    
    public Transaction(int id, LocalDate vendule, float prixVente, Exposition expo, Personne personne, Tableau oeuvre){
        this.id=id;
        this.exposition=expo;
        this.oeuvre=oeuvre;
        this.prixVente=prixVente;
        this.venduLe=venduLe;
        this.personne=personne;
    }
    
   
}
