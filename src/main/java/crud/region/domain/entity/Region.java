package crud.region.domain.entity;

public class Region {
    private String codigo_reg;
    private String nombre;
    private String codigo_pais;

    public Region(String codigo_reg, String nombre, String codigo_pais) {
        this.codigo_reg = codigo_reg;
        this.nombre = nombre;
        this.codigo_pais = codigo_pais;
    }

    public String getCodigo_reg() {
        return codigo_reg;
    }

    public void setCodigo_reg(String codigo_reg) {
        this.codigo_reg = codigo_reg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_pais() {
        return codigo_pais;
    }

    public void setCodigo_pais(String codigo_pais) {
        this.codigo_pais = codigo_pais;
    }

}
