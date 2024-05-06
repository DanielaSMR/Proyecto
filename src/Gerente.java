public class Gerente extends Empleado{
    private String departamento;
    private int nivelJerarquico;

    public Gerente(){
        super();
    }

    public Gerente(String nombre,String apellido,int id,int salario,String departamento,int nivelJerarquico){
        super(nombre, apellido,id,salario);
        this.departamento = departamento;
        this.nivelJerarquico = nivelJerarquico;
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