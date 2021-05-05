package edu.eci.cvds.entities;

public class CountNeeds {
    private String status;
    private int conteo;

    public CountNeeds(String status, int conteo) {
        this.status = status;
        this.conteo = conteo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public CountNeeds(){
        super();
    }
}
