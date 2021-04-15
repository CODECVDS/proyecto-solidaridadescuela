package edu.eci.cvds.services;

import edu.eci.cvds.entities.Category;

public interface SolidaridadServices {

    public void registerCategory(Category c) throws ServicesException;

    public void updateCategory(String name, String description, boolean status) throws ServicesException;
}
