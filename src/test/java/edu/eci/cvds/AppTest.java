package edu.eci.cvds;


import static org.junit.Assert.assertTrue;

import edu.eci.cvds.entities.*;
import edu.eci.cvds.services.ServicesException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import edu.eci.cvds.services.SolidaridadServicesFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;

public class AppTest 
{

    @Inject
    private SqlSession sqlSession;

    SolidaridadServices solidaridadServices;

    public AppTest(){
        solidaridadServices = SolidaridadServicesFactory.getInstance().getSolidaridadServicesTesting();
    }

    private Date dateTest;

    @Before
    public void setUp(){
    }
    @Test
    public void DeberiaIniciarSesion(){}

    /*
    @Test
    public void DeberiaRegistrarCategoria(){
        try {
            int before = solidaridadServices.loadCategories().size();
            Category categorytest = new Category(999,"Categorytest","Esta es una categoria de prueba",null,true,null,true,null);
            solidaridadServices.registerCategory(categorytest);
            int after = solidaridadServices.loadCategories().size();
            Assert.assertTrue(before<after);
        } catch (ServicesException ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
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
            Category categoryTestUpdate = new Category(1,"Categoria1Test1","Test modificacion",null,true, null,true,null);
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
    }

    @Test
    public void DeberiaRegistrarOferta(){
        try {
            Offer offer = new Offer(124,2,"offertest","offer created by Apptest",null,Status.Active,null,"user");
            solidaridadServices.registerOffer(offer);
            Offer offerResult = solidaridadServices.loadOffer(124);
            Assert.assertTrue(offerResult != null);
        } catch (ServicesException ex){
            assertTrue(false);
        }
    }

    @Test
    public void DeberiaRegistrarRespuestas(){
        try {
            Answer respuesta = new Answer(1,"RespuestaTest",null,"Comentario Prueba",1,0);
            solidaridadServices.registerAnswer(respuesta);
            assertTrue(true);
        } catch (ServicesException ex){
            assertTrue(false);
        }
    }


    @Test
    public void deberiaActualizar(){
        try{
            LocalDate hoyl=LocalDate.now();
            Date hoy = new Date(String.valueOf(hoyl));
            Need necesidadTest = new Need(2,2,"prueba","miremos a ver si funciona",3,hoy,Status.Active,hoy,"user");
            solidaridadServices.registerNeed(necesidadTest);
            solidaridadServices.updateNeedStatus(Status.Closed);
            Need necesidadresult = solidaridadServices.loadNeed(2);
            Assert.assertEquals(necesidadresult.getStatus(),Status.Closed);
        }catch(ServicesException ex){
            assertTrue(false);
        }
    }
*/

}