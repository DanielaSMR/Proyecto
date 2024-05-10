import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class main {

    public static ArrayList<Empleado> empleados = new ArrayList<>();
    public static HashMap<String,Empleado> ordEmpleados = new HashMap<String,Empleado>(); 
    
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

        //hacer menu
    }
}