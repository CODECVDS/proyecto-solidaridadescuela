package edu.eci.cvds;

/*
import static org.junit.Assert.assertTrue;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
 */

import edu.eci.cvds.services.SolidaridadServices;
import edu.eci.cvds.services.SolidaridadServicesFactory;
import org.apache.ibatis.session.SqlSession;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Inject
    private SqlSession sqlSession;

    SolidaridadServices solidaridadServices;

    public AppTest(){
        solidaridadServices = SolidaridadServicesFactory.getInstance().getSolidaridadServicios();
    }

    private Date dateTest;

    /*
    @Before
    public void setUp(){
    }

    @Test
    public void DeberiaIniciarSesion(){}

    @Test
    public void DeberiaRegistrarCategoria(){
        try {
            Category categorytest = new Category("CategoryTest","Esta es la categoria de prueba");
            solidaridadServices.registerCategory(categorytest);
            Category categoryresult = solidaridadServices.loadCategory(1);
            dateTest = categoryresult.getCreationDate();
            Assert.assertTrue(categoryresult != null);
        } catch (ServicesException ex){
            assertTrue(false);
        }
    }

    @Test
    public void DeberiaActualizarCategoria(){
        try {
            Category category = solidaridadServices.loadCategory(1);
            category.setName("Categoria1Test1");
            category.setDescription("Test modificacion");
            category.setStatus(true);
            solidaridadServices.updateCategory(category);
            Date creationDate = dateTest;
            Category categoryTestUpdate = new Category(1,"Categoria1Test1","Test modificacion",creationDate,true, new Date());
            Assert.assertEquals(category.getName(),categoryTestUpdate.getName());
            Assert.assertEquals(category.getDescription(),categoryTestUpdate.getDescription());
            Assert.assertEquals(category.getCreationDate(),categoryTestUpdate.getCreationDate());
            Assert.assertEquals(category.getStatus(),categoryTestUpdate.getStatus());
            Assert.assertEquals(category.getModificationDate(),categoryTestUpdate.getModificationDate());
            Assert.assertEquals(category,categoryTestUpdate);
        } catch (ServicesException ex){
            assertTrue(false);
        }
    }

    @Test
    public void DeberiaRegistrarNecesidad(){
        try {
            Need necesidadtest = new Need(1,"Necesidad Test","Esta es la prueba de creacion de la necesidad",1, Status.Active);
            solidaridadServices.registerNeed(necesidadtest);
            Need necesidadresult = solidaridadServices.loadNeed(1);
            Assert.assertTrue(necesidadresult != null);
        } catch (ServicesException ex){
            assertTrue(false);
        }
    }*/
}
