package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Offer;

import java.util.List;

public interface OfferDAO {
    public void registerOffer(Offer offer) throws PersistenceException;
    public void updateOffer(Offer offer) throws PersistenceException;
    public Offer loadOffer(int offerId) throws PersistenceException;
    public List<Offer> loadAllOffers() throws PersistenceException;
    public List<Offer> loadAllOffersWS() throws PersistenceException;
    public int loadParamNOffer() throws PersistenceException;
}
