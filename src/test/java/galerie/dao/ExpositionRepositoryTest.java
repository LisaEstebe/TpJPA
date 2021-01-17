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
import galerie.entity.Galerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import galerie.dao.GalerieRepository;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest

public class ExpositionRepositoryTest {
    @Autowired
    private GalerieRepository expositionDAO;
    
    @Test
    @Sql("test-data.sql")//On peut charger les données spécifiques pour un test
    public void onSaitCompterLesEnregistrements(){
        log.info("On compte les enregistrements de la table 'Exposition'");
        int combienDansLeJeuDeTest = 1; 
        long nombre = expositionDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 exposition" );
    }
}
