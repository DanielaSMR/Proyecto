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
import java.util.*;

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

        Empleado a1 = new EmpleadoTemporal("Teresa", "Garcia", "12AB", 100, "12/04/2024", 3);
        Empleado a2 = new EmpleadoTemporal("Pepe", "Garcia", "13AB", 200, "12/04/2024", 3);
        Empleado b1 = new Gerente("Nacho", "Perez", "14AB", 350, "Contabilidad", 3);
        Empleado b2 = new Gerente("Sara", "Perez", "15AB", 400, "Contabilidad", 2);
        agregarEmpleado(a1);
        agregarEmpleado(a2);
        agregarEmpleado(b1);
        agregarEmpleado(b2);
        
        // System.out.println("Como se llama el nuevo empleado?");
        // String nombre = IO.pedirTexto();
        // System.out.println("Cual es el apellido?");
        // String apellido = IO.pedirTexto();
        // System.out.println("Cual es la ID");
        // String id = IO.pedirTexto();
        // System.out.println("Cual es el salario?");
        // int salario = IO.pedirEntero();
        
        
        // System.out.println("Sera 1-empleado temporal o 2-gerente?");
        // String eleccion = IO.pedirTexto();
        // if(eleccion.equals("1")){
        //     System.out.println("Dime la fecha del contrato");
        //     String fechaC = IO.pedirTexto();
        //     System.out.println("Dime la duracion del contrato");
        //     Integer duracionC = IO.pedirEntero();
        //     Empleado nuevoEmpleado = new EmpleadoTemporal(nombre,apellido,id,salario,fechaC,duracionC);
        //     agregarEmpleado(nuevoEmpleado);
        // }else if(eleccion.equals("2")){
        //     System.out.println("Dime el departamento");
        //     String depa = IO.pedirTexto();
        //     System.out.println("Dime el nivel jerarquico");
        //     int nivel = IO.pedirEntero();
        //     Empleado nuevoEmpleado = new EmpleadoTemporal(nombre,apellido,id,salario,depa,nivel);
        //     agregarEmpleado(nuevoEmpleado);
        // }
        
    }

    public static void eliminarEmpleado(String id) throws EmpleadoNoEncontrado{
        boolean encontrado = false;
        Iterator<Empleado> iterator = main.empleados.iterator();//Recorre el array
        while (iterator.hasNext() && !encontrado) {
            Empleado empleado = iterator.next();
            if (empleado.getId().matches(id)) {
                System.out.println("Se eliminará el empleado " + empleado.getNombre());
                iterator.remove(); // Eliminar el empleado usando el iterador
                main.ordEmpleados.remove(id);
                encontrado = true;
            }

            
        }

        if(!encontrado){//Excepcion personalizada
            throw new EmpleadoNoEncontrado("Empleado no encontrado");
        }
    }

    public static void buscarEmpleado(String id)throws EmpleadoNoEncontrado{
        boolean encontrado = false;
        try{
            for (String i : main.ordEmpleados.keySet()){
                if(i.equals(id)){
                    System.out.println(main.ordEmpleados.get(i));
                    encontrado = true;
                }
            }

            if(!encontrado){//Otra excepcion con mensaje personalizado
                throw new EmpleadoNoEncontrado("Empleado no encontrado");//arreglar
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void listarEmpleados(){
        for(Empleado empleado : main.empleados){
            System.out.println("Nombre: " + empleado.getNombre() + " Apellido: " + empleado.getApellido() + "ID" + empleado.getId() + " Salario: " + empleado.getSalario());
        }
    }

    public static void guardarDatosEnFichero() throws FileNotFoundException, IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(main.EMPLEADOS_ARCHIVO))){
            oos.writeObject(main.empleados);
            System.out.println("Empleados serializados correctamente");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void cargarDatosDesdeFichero(String nombreFichero) throws FileNotFoundException, IOException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
            main.empleados = (ArrayList<Empleado>) ois.readObject();
            System.out.println("Empleados deserializados correctamente");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
