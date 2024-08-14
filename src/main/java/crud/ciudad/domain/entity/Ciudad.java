package crud.ciudad.domain.entity;

public class Ciudad {
    private String codigo_ciudad;
    private String nombre;
    private String cod_region;

    public Ciudad(String codigo_ciudad, String nombre, String cod_region) {
        this.codigo_ciudad = codigo_ciudad;
        this.nombre = nombre;
        this.cod_region = cod_region;
    }

    public String getCodigo_ciudad() {
        return codigo_ciudad;
    }

    public void setCodigo_ciudad(String codigo_ciudad) {
        this.codigo_ciudad = codigo_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod_region() {
        return cod_region;
    }

    public void setCod_region(String cod_region) {
        this.cod_region = cod_region;
    }

}
