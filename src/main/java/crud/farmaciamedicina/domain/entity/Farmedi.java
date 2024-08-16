package crud.farmaciamedicina.domain.entity;

public class Farmedi {
    private String id_farmacia;
    private String id_medicina;
    private Float prince;

    public Farmedi(String id_farmacia, String id_medicina, Float prince) {
        this.id_farmacia = id_farmacia;
        this.id_medicina = id_medicina;
        this.prince = prince;
    }

    public String getId_farmacia() {
        return id_farmacia;
    }

    public void setId_farmacia(String id_farmacia) {
        this.id_farmacia = id_farmacia;
    }

    public String getId_medicina() {
        return id_medicina;
    }

    public void setId_medicina(String id_medicina) {
        this.id_medicina = id_medicina;
    }

    public Float getPrince() {
        return prince;
    }

    public void setPrince(Float prince) {
        this.prince = prince;
    }
}
