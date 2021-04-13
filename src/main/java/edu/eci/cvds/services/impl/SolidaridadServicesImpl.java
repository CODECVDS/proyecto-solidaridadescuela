package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.services.SolidaridadServices;

import javax.inject.Inject;

public class SolidaridadServicesImpl implements SolidaridadServices {

    @Inject
    private CategoryDAO categoryDAO;
    
}
