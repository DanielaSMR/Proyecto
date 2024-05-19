import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

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
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mati", "mati", "mati2");
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
            "4- Alquiler de un libro por un usuario.\n"+
           "5- Devolución de un libro por un usuario.\n"+//cambiar esto
            "6- Gestión de empleados/as de la biblioteca.\n"+
            "7- Gestión de usuarios/as de la biblioteca.\n"+
            "8- Salir del sistema.");
            eleccion = (int)Integer.parseInt(IO.pedirTexto());
            switch (eleccion) {
                case 1:
                    GestorEmpleados.añadirEmpleado();
                    break;
                case 2:
                    GestorEmpleados.listarEmpleados();
                    System.out.println("Dime la id del empleado a eliminar");
                    id = IO.pedirTexto();
                    GestorEmpleados.eliminarEmpleado(id);
                    break;
                case 3:
                    GestorEmpleados.listarEmpleados();
                    System.out.println("Dime la id del empleado buscado");
                    id = IO.pedirTexto();
                    GestorEmpleados.buscarEmpleado(id);
                    break;
                case 4:
                    GestorEmpleados.listarEmpleados();
                    break;
                case 5:
                    GestorEmpleados.guardarDatosEnFichero(empleados);
                    break; 
                case 6:
                    empleados = GestorEmpleados.cargarDatosDesdeFichero();
                    break;
                case 7:
                    //Añadir base de datos
                    break;
                case 8:
                    
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