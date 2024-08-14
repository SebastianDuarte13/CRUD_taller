package crud.principio_activo.domain.entity;

public class Activo {
    private int id;
    private String nombre;

    public Activo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Activo() {
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
