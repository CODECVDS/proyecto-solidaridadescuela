package edu.eci.cvds.services.impl;

import edu.eci.cvds.persistence.NeedDAO;
import edu.eci.cvds.services.SolidaridadServices;

import javax.inject.Inject;

public class SolidaridadServicesImpl implements SolidaridadServices {

    @Inject
    private NeedDAO needDAO;
}
