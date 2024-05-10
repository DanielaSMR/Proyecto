public class Gerente extends Empleado implements Serializable{
    private String departamento;
    private int nivelJerarquico;

    public Gerente(){
        super();
    }

    public Gerente(String nombre,String apellido,String id,int salario,String departamento,int nivelJerarquico){
        super(nombre, apellido,id,salario);
        this.departamento = departamento;
        this.nivelJerarquico = nivelJerarquico;
    }

    @Override
    public void calcularPrestaciones(){

    }

    public String toString(){
        return "Pertenece al departamento: " + departamento + "Su nivel jerarquico" + nivelJerarquico;
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