import java.io.Serializable;


public abstract class Empleado implements Prestaciones,Serializable {
    private String nombre;
    private String apellido;
    private String id;
    private int salario;

    public Empleado() {

    }

    public Empleado(String nombre, String apellido,String id,Integer salario) throws SalarioInvalidoException {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.salario = salario;
        setSalario(salario);

    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + " Apellido: " + apellido + " ID: " + id + " Salario: " + salario;
    }

    public void calcularBonificaciones(){
        System.out.println((((salario*6)/6)*20)/100);
        System.out.println("Se calculo la bonificacion");
        
    }

    public void calcularVacaciones(){
        int diasTrabajo = 22;
        int mesesTrabajados = 6;
        System.out.println(diasTrabajo * mesesTrabajados);
        System.out.println("Se calculo las vacaciones");
    }

    public void calcularPrestaciones(){

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) throws SalarioInvalidoException {
        if (salario <= 0) {
            throw new SalarioInvalidoException("El salario debe ser mayor que 0.");
        }
        this.salario = salario;
            
    }  
    
    

    
}