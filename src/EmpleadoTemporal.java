public class EmpleadoTemporal extends Empleado{
    private String fechacontrato;
    private Integer duracioncontrato;
    
    public EmpleadoTemporal(){
        super();
    }

    public EmpleadoTemporal(String nombre,String apellido,int id,int salario,String fechacontrato, Integer duracioncontrato){
        super(nombre, apellido, id, salario);
        this.fechacontrato = fechacontrato;
        this.duracioncontrato = duracioncontrato;
    }

    public String getFechacontrato() {
        return fechacontrato;
    }

    public void setFechacontrato(String fechacontrato) {
        this.fechacontrato = fechacontrato;
    }

    public Integer getDuracioncontrato() {
        return duracioncontrato;
    }

    public void setDuracioncontrato(Integer duracioncontrato) {
        this.duracioncontrato = duracioncontrato;
    }

    

}