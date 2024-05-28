import java.io.Serializable;
/**
* Gerente 
* La clase Gerente que extendera de Empleado
* @author Daniela Perez / Empresa
* @version 0.1, 
*/
public class Gerente extends Empleado implements Serializable{
    private String departamento;
    private int nivelJerarquico;

    public Gerente(){
        super();
    }

    public Gerente(String nombre,String apellido,String id,int salario,String departamento,int nivelJerarquico) throws SalarioInvalidoException{
        super(nombre, apellido,id,salario);
        this.departamento = departamento;
        this.nivelJerarquico = nivelJerarquico;
    }

    @Override
    public String toString(){
        return super.toString() + " Departamento: " + departamento + " Nivel Jerarquico: " + nivelJerarquico;
    }


    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getNivelJerarquico() {
        return nivelJerarquico;
    }

    public void setNivelJerarquico(int nivelJerarquico) {
        this.nivelJerarquico = nivelJerarquico;
    }

    
}