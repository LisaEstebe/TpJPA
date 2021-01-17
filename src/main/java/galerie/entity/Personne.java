/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Lisa
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor 
@ToString(callSuper = true )
@Inheritance(strategy = InheritanceType.JOINED)
@Entity // Une entité JPA
public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private String nom;
    
    @Column(unique=true)
    private String adresse;
    
    public Personne (int id, String nom, String adresse){
        this.nom = nom;
        this.adresse = adresse;
        this.id=id;
    }
     
     @OneToMany (mappedBy = "personne")
    public List<Transaction> transactions;
     
    public float BudgetArt(int annee){
        float budget = 0;
        for (Transaction t : transactions){
            if(t.getVenduLe().getYear() == annee){
                budget += t.getPrixVente();
            }
        }
        return budget;
    }
    
    public void nouvelAchat(Transaction t){
        this.transactions.add(t);
    }
}
