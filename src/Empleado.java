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

    
    
}