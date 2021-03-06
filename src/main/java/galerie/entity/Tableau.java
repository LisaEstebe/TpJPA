/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Lisa
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Tableau {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private String titre;
    
    @Column(unique=true)
    @NonNull
    private String support;
    
    @Column(unique=true)
    @NonNull
    private int largeur;
    
    @Column(unique=true)
    @NonNull
    private int hauteur;
    
   
    @ManyToOne
    private Artiste artiste;
    
    @OneToOne (mappedBy = "oeuvre")
    private Transaction vendu;
    
    @ManyToMany(mappedBy = "oeuvres")
    List<Exposition> accrochages = new LinkedList<>();
    
    public Tableau(int id, String titre, String support, int largeur, int hauteur, Artiste artiste){
        this.artiste=artiste;
        this.hauteur=hauteur;
        this.id=id;
        this.largeur=largeur;
        this.support=support;
        this.titre=titre;
    }
}
