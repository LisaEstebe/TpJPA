/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galerie.dao;

/**
 *
 * @author Lisa
 */


import galerie.entity.Artiste;
import galerie.entity.Exposition;
import galerie.entity.Galerie;
import galerie.entity.Personne;
import galerie.entity.Tableau;
import galerie.entity.Transaction;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import org.springframework.test.context.jdbc.Sql;


@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest

public class PersonneRepositoryTest {
     @Autowired
    private GalerieRepository personneDAO;
    
    @Test
    @Sql("test-data.sql")//On peut charger les données spécifiques pour un test
            
    public void onSaitCompterLesEnregistrements(){
        log.info("On compte les enregistrements de la table 'Personne'");
        int combienDansLeJeuDeTest = 7; 
        long nombre = personneDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 7 personnes" );
    }
    
    @Test
    public void calculBudgetArt(){
        Galerie g = new Galerie(1, "Louvre", "Paris");
        Exposition e = new Exposition(1, LocalDate.now(),"Slunecni kralove",52,g);
        Personne p = new Personne (1, "Client", "Foix");
        Artiste a = new Artiste (1, "Vincent Van Ghog", "Pays-Bas","Je me suis coupé une oreille");
        Tableau tab = new Tableau (1, "Two Crabs", "huile sur toile",38,47,a);
        Transaction t = new Transaction(1, LocalDate.now(), 10000, e, p, tab);
        
        assertTrue(p.getTransactions().isEmpty());
        p.nouvelAchat(t);
        assertTrue(p.getTransactions().contains(t));
        assertEquals(10000, p.BudgetArt(LocalDate.now().getYear()));
    }
    
}
