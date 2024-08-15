package crud.laboratorio.domain.entity;

public class Laboratorio {

    private String id;
    private String nombre;
    private String cod_ciudad;

    public Laboratorio(String id, String nombre, String cod_ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.cod_ciudad = cod_ciudad;
    }

    public Laboratorio() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod_ciudad() {
        return cod_ciudad;
    }

    public void setCod_ciudad(String cod_ciudad) {
        this.cod_ciudad = cod_ciudad;
    }

}
