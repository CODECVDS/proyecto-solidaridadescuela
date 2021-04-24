package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Offer;

public interface OfferDAO {
    public void registerOffer(Offer offer) throws PersistenceException;
}
