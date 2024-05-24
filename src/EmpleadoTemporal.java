
import java.io.Serializable;

public class EmpleadoTemporal extends Empleado implements Serializable{
    private String fechacontrato;
    private Integer duracioncontrato;
    
    public EmpleadoTemporal(){
        super();
    }

    public EmpleadoTemporal(String nombre,String apellido,String id,int salario,String fechacontrato, Integer duracioncontrato) throws SalarioInvalidoException{
        super(nombre, apellido,id, salario);
        this.fechacontrato = fechacontrato;
        this.duracioncontrato = duracioncontrato;
    }

    @Override
    public String toString(){
        return super.toString() + "Fecha contrato: " + fechacontrato + "Duracion Contrato: " + duracioncontrato; 
    }

    public String toString(){
        return "La fecha del contrato" + fechacontrato + "La duracion del contrato" + duracioncontrato;
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