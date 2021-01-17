package galerie.dao;

import galerie.entity.Galerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import galerie.dao.GalerieRepository;
import galerie.entity.Artiste;
import galerie.entity.Exposition;
import galerie.entity.Personne;
import galerie.entity.Tableau;
import galerie.entity.Transaction;
import java.time.LocalDate;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class GalerieRepositoryTest {

    @Autowired
    private GalerieRepository galerieDAO;

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table Galerie");
        int combienDansLeJeuDeTest = 5; 
        long nombre = galerieDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 5 galeries" );
    }

    @Test
    public void connaitreCAannuel(){
        Galerie g = new Galerie(1, "Louvre", "Paris");
        Exposition e = new Exposition(1, LocalDate.now(),"Slunecni kralove",52,g);
        Personne p = new Personne (1, "Client", "Foix");
        Artiste a = new Artiste (1, "Vincent Van Ghog", "Pays-Bas","Je me suis coupé une oreille");
        Tableau tab = new Tableau (1, "Two Crabs", "huile sur toile",38,47,a);
        Transaction t = new Transaction(1, LocalDate.now(), 10000, e, p, tab);
        
        assertTrue(g.getExpositions().isEmpty());
        assertTrue(e.getTransactions().isEmpty());
        g.nouvelleExpo(e);
        assertTrue(g.getExpositions().contains(e));
        e.nouvelleVente(t);
        assertTrue(e.getTransactions().contains(t));
        assertEquals(10000, g.CAannuel(LocalDate.now().getDayOfYear()));
    }
}
