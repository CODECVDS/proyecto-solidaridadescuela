package edu.eci.cvds.services;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.Need;

public interface SolidaridadServices {

    public void registerCategory(Category c) throws ServicesException;

    public void updateCategory(int id, String name, String description, boolean status) throws ServicesException;

    public void registerNeed(Need need) throws ServicesException;
}
