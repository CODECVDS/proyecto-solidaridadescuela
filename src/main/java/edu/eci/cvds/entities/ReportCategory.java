package edu.eci.cvds.entities;

public class ReportCategory {
    private String category;
    private int needs;
    private int offers;
    private int total;

    public ReportCategory() {
    }

    public ReportCategory(String category, int needs, int offers, int total) {
        this.category = category;
        this.needs = needs;
        this.offers = offers;
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNeeds() {
        return needs;
    }

    public void setNeeds(int needs) {
        this.needs = needs;
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

