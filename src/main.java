import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
* Main
* Donde se iniciara la base de datos y se llamara a las funcioens del GestorEmpleados
* @author Daniela Perez / Empresa
* @version 0.1, 
*/
public class main {

    public static ArrayList<Empleado> empleados = new ArrayList<>();
    public static HashMap<String,Empleado> ordEmpleados = new HashMap<String,Empleado>(); 
    public static final String EMPLEADOS_ARCHIVO = "empleados.ser";

    //Meter try catch

    public static void main(String[] args) throws Exception{
        
          try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
        }

        Connection connection = null;
        // Database connect
        // Conectamos con la base de datos
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mati2", "mati", "mati");
        //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "a");
        Statement st = connection.createStatement();
        connection.setAutoCommit(false);

        int eleccion;
        String id;
        do{
            System.out.println("Elige una opcion\n"+
            "1- Dar de alta a un empleado en el sistema:\n"+
            "2- Dar de baja a un empleado en el sistema.\n"+
            "3- Búsqueda de empleados dentro del sistema\n"+
            "4- Listar empleados.\n"+
           "5- Guardar datos actuales\n"+
            "6- Cargar datos guardados\n"+
            "7- Guardar datos actuales en BD\n"+
            "8- Cargar datos guardado en BD");
            eleccion = (int)Integer.parseInt(IO.pedirTexto());
            switch (eleccion) {
                case 1:
                    try{
                        GestorEmpleados.añadirEmpleado();
                    }catch(SalarioInvalidoException sie){
                        System.err.println(sie.getMessage());
                    }
                    break;
                case 2:
                    GestorEmpleados.listarEmpleados();
                    System.out.println("Dime la id del empleado a eliminar");
                    id = IO.pedirTexto();
                    try{
                    GestorEmpleados.eliminarEmpleado(id);
                    }catch(EmpleadoNoEncontrado ene){
                        System.err.println(ene.getMessage());
                    }
                    break;
                case 3:
                    GestorEmpleados.listarEmpleados();
                    System.out.println("Dime la id del empleado buscado");
                    id = IO.pedirTexto();
                    try{
                    GestorEmpleados.buscarEmpleado(id);
                    }catch(EmpleadoNoEncontrado ene){
                        System.err.println(ene.getMessage());
                    }
                    break;
                case 4:
                    GestorEmpleados.listarEmpleados();
                    break;
                case 5:
                    GestorEmpleados.guardarDatosEnFichero(EMPLEADOS_ARCHIVO);
                    break; 
                case 6:
                    GestorEmpleados.cargarDatosDesdeFichero(EMPLEADOS_ARCHIVO);
                    break;
                case 7:
                    GestorEmpleados.guardarEnDB(st);
                    break;
                case 8:
                    GestorEmpleados.cargarDatosDesdeBD(st);
                    break;        
                case 9:
                    System.out.println("Adios");
                    break;
                default:
                    break;
            }
        }while(eleccion != 9);
    }
}