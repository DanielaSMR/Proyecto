public abstract class Empleado implements Presentaciones {
    private String nombre;
    private String apellido;
    private Integer id;
    private Integer salario;

    public Empleado() {

    }

    public Empleado(String nombre, String apellido, Integer id, Integer salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.salario = salario;

    }

    @Override
    public String toString(){
        return "";
    }

    public void calcularBonificaciones(){
        
    }

    public void calcularVacaciones(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    
    

    
}