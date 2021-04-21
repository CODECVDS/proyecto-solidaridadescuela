package edu.eci.cvds;


import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;
import edu.eci.cvds.entities.Status;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.SolidaridadServices;
import edu.eci.cvds.services.SolidaridadServicesFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        //services.registerCategory(c);

        //Need n = new Need(3,"prueba","lo que sea", 4, Status.Closed);
        //services.registerNeed(n);
        System.out.println(services.loadNeed(5).getDescription());

        sqlss.commit();
        sqlss.close();
    }
}
