/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;
import java.time.LocalDate;
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
public class Exposition {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    @Column(unique=true)
    @NonNull
    private LocalDate debut;
    
    @Column(unique=true)
    @NonNull
    private String intitule;
    
    @Column(unique=true)
    @NonNull
    private int duree;
    
    @ManyToOne
    private Galerie galerie;
    
    @OneToMany (mappedBy = "exposition")
    private List<Transaction> transactions;
    
    
    @ManyToMany
    @JoinTable(name="expo_tableau",
            joinColumns =
                    @JoinColumn(name = "exposition_id", referencedColumnName="id"),
            inverseJoinColumns =
                    @JoinColumn(name = "tableau_id",referencedColumnName="id")
        )
        List<Tableau> oeuvres = new LinkedList<>();
    
    public float CA(){
        float total = 0;
        for (Transaction t : transactions){
            total += t.getPrixVente();
        }
        return total;
    }

    
}
