package galerie.entity;
import java.util.List;
import javax.persistence.*;
import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Galerie {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;
    
    @Column(unique=true)
    @NonNull
    private String adresse;
    
    // TODO : Mettre en oeuvre la relation oneToMany vers Exposition
    @OneToMany(mappedBy = "galerie")
    public List<Exposition> expositions;
    
    public float CAannuel(int annee){
        float total = 0;
        
        for (Exposition e : expositions){
            if (e.getDebut().getYear() == annee && (e.getDebut().getDayOfYear()+e.getDuree())<365) {
            total += e.CA();
         }
        }
        return total;
    }
    
}
