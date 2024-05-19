import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
* GestorEmpleados
* Donde se gestiona todos los cambios que haremos en los empleados
* @author Daniela Perez / Empresa
* @version 0.1, 
*/

//Para el salario y el empleado no encontrado creariamos una nueva clase
//Meter ficheros y Bases de datos

public class GestorEmpleados implements Serializable{
    
    public static void agregarEmpleado(Empleado empleado) throws Exception{
        main.ordEmpleados.put(empleado.getId(), empleado);
        main.empleados.add(empleado);

    }

    public static void añadirEmpleado() throws Exception{
        System.out.println("Como se llama el nuevo empleado?");
        String nombre = IO.pedirTexto();
        System.out.println("Cual es el apellido?");
        String apellido = IO.pedirTexto();
        System.out.println("Cual es la ID");
        String id = IO.pedirTexto();
        System.out.println("Cual es el salario?");
        int salario = IO.pedirEntero();
        
        System.out.println("Sera 1-empleado temporal o 2-gerente?");
        String eleccion = IO.pedirTexto();
        if(eleccion.equals("1")){
            System.out.println("Dime la fecha del contrato");
            String fechaC = IO.pedirTexto();
            System.out.println("Dime la duracion del contrato");
            Integer duracionC = IO.pedirEntero();
            Empleado nuevoEmpleado = new EmpleadoTemporal(nombre,apellido,id,salario,fechaC,duracionC);
            agregarEmpleado(nuevoEmpleado);
        }else if(eleccion.equals("2")){
            System.out.println("Dime el departamento");
            String depa = IO.pedirTexto();
            System.out.println("Dime el nivel jerarquico");
            int nivel = IO.pedirEntero();
            Empleado nuevoEmpleado = new EmpleadoTemporal(nombre,apellido,id,salario,depa,nivel);
            agregarEmpleado(nuevoEmpleado);
        }
    }

    public static void eliminarEmpleado(String id) throws EmpleadoNoEncontrado{
            Empleado buscarEmpleado = main.ordEmpleados.get(id);
        if(buscarEmpleado == null){
            throw new EmpleadoNoEncontrado("Empleado con ID '" + id + "' no encontrado.");
        }else{
            main.ordEmpleados.remove(id);
        }
    }

    public static void buscarEmpleado(String id){

    }

    public static void listarEmpleados(){

    }

    public static void guardarDatosEnFichero(ArrayList<Empleado> empleados) throws FileNotFoundException, IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(main.EMPLEADOS_ARCHIVO))){
            oos.writeObject(empleados);
            System.out.println("Empleados serializados correctamente");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static ArrayList<Empleado> cargarDatosDesdeFichero() throws FileNotFoundException, IOException{
        ArrayList<Empleado> empleados = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(main.EMPLEADOS_ARCHIVO))){
            empleados = (ArrayList<Empleado>) ois.readObject();
            System.out.println("Emplezados desirilizados correctamente");
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return empleados;
    }

    //guardarDatosEnBD() y cargarDatosDesdeBD()

}
