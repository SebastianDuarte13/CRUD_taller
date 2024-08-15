package crud.medicina.domain.entity;

public class Medicina {
    private String id;
    private String proceedings;
    private String nombre;
    private String healthregister;
    private String descripcion;
    private String descripcionshort;
    private String nombrerol;
    private String cod_admin;
    private String cod_activo;
    private String cod_medida;
    private String cod_lab;

    public Medicina(String id, String proceedings, String nombre, String healthregister, String descripcion,
            String descripcionshort, String nombrerol, String cod_admin, String cod_activo, String cod_medida,
            String cod_lab) {
        this.id = id;
        this.proceedings = proceedings;
        this.nombre = nombre;
        this.healthregister = healthregister;
        this.descripcion = descripcion;
        this.descripcionshort = descripcionshort;
        this.nombrerol = nombrerol;
        this.cod_admin = cod_admin;
        this.cod_activo = cod_activo;
        this.cod_medida = cod_medida;
        this.cod_lab = cod_lab;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProceedings() {
        return proceedings;
    }

    public void setProceedings(String proceedings) {
        this.proceedings = proceedings;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHealthregister() {
        return healthregister;
    }

    public void setHealthregister(String healthregister) {
        this.healthregister = healthregister;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionshort() {
        return descripcionshort;
    }

    public void setDescripcionshort(String descripcionshort) {
        this.descripcionshort = descripcionshort;
    }

    public String getNombrerol() {
        return nombrerol;
    }

    public void setNombrerol(String nombrerol) {
        this.nombrerol = nombrerol;
    }

    public String getCod_admin() {
        return cod_admin;
    }

    public void setCod_admin(String cod_admin) {
        this.cod_admin = cod_admin;
    }

    public String getCod_activo() {
        return cod_activo;
    }

    public void setCod_activo(String cod_activo) {
        this.cod_activo = cod_activo;
    }

    public String getCod_medida() {
        return cod_medida;
    }

    public void setCod_medida(String cod_medida) {
        this.cod_medida = cod_medida;
    }

    public String getCod_lab() {
        return cod_lab;
    }

    public void setCod_lab(String cod_lab) {
        this.cod_lab = cod_lab;
    }

}
