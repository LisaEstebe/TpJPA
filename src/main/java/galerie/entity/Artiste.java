/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.entity;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Lisa
 */
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Artiste {
     @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
     
     @Column(unique=true)
    @NonNull
    private String biographie;
}
