package edu.eci.cvds;

/**

import edu.eci.cvds.entities.*;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import edu.eci.cvds.services.SolidaridadServicesFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    //Esta es la clase principal, donde debemos probar los servicios
    public static void main(String[] args) throws ServicesException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        SolidaridadServicesFactory factory = SolidaridadServicesFactory.getInstance();
        SolidaridadServices services = factory.getSolidaridadServicios();

        //Category c = new Category("Category1", "Description");
        //System.out.println(services.loadOffers().size());
        //Answer a = new Answer(12,"Carlos",null,"los que sea manda decir kevin",0,5);
        //services.registerAnswer(a);
        //System.out.println(services.loadAnswers().get(0).getName());
        //Need n = services.loadNeed(5);
        //n.setStatus(Status.Closed);
        //services.updateNeedStatus(n);
        Category c = services.loadCategory(9);
        services.deleteCategory(c);
        Category k = services.loadCategory(9);
        System.out.println(k.getStatus());
        sqlss.commit();
        sqlss.close();
    }
}
**/