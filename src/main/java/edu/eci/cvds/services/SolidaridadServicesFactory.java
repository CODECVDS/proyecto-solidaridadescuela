package edu.eci.cvds.services;

import com.google.inject.Injector;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.persistence.OfferDAO;
import edu.eci.cvds.persistence.UserDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisCategoryDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisNeedDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisOfferDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUserDAO;
import edu.eci.cvds.services.impl.SolidaridadServicesImpl;
import org.mybatis.guice.XMLMyBatisModule;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;

public class SolidaridadServicesFactory {

    private static SolidaridadServicesFactory instance = new SolidaridadServicesFactory();
    private static Optional<Injector> optInjector;

    private SolidaridadServicesFactory() {
        optInjector = Optional.empty();
    }

    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                bind(CategoryDAO.class).to(MyBatisCategoryDAO.class);
                bind(UserDAO.class).to(MyBatisUserDAO.class);
                bind(NeedDAO.class).to(MyBatisNeedDAO.class);
                bind(OfferDAO.class).to(MyBatisOfferDAO.class);
                bind(SolidaridadServices.class).to(SolidaridadServicesImpl.class);
            }
        });
    }

    public SolidaridadServices getSolidaridadServicios(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
        }

        return optInjector.get().getInstance(SolidaridadServices.class);
    }


    public SolidaridadServices getSolidaridadServicesTesting(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(SolidaridadServices.class);
    }


    public static SolidaridadServicesFactory getInstance(){
        return instance;
    }
}
