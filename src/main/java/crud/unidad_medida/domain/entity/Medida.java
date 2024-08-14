package crud.unidad_medida.domain.entity;

public class Medida {
    private int id;
    private String nombre;
    public Medida() {
    }

    public Medida(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
