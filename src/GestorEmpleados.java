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
import java.io.FileReader;
import java.io.BufferedReader;

/**
* Main
* Donde se ejecutara el programa
* @author Daniela Perez / Empresa
* @version 0.1, 2004/05/30
*/

//Añadir excepciones "Salario Invalido" y "EmpleadoNoEncontradoExcepion"
//junto con try,catch y throw
//Meter ficheros y Bases de datos

public class GestorEmpleados implements Serializable{
    
    public void agregarEmpleado(Empleado empleado) throws Exception{
        
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
            main.empleados.add(new EmpleadoTemporal(nombre, apellido,id,salario,fechaC,duracionC));
        }else if(eleccion.equals("2")){
            System.out.println("Dime el departamento");
            
        }
    }

    public void eliminarEmpleado(String id){

    }

    public void buscarEmpleado(String id){

    }

    public void listarEmpleados(){

    }

    public void guardarDatosEnFichero(){

    }

    public void cargarDatosDesdeFichero(){

    }

    //guardarDatosEnBD() y cargarDatosDesdeBD()

}
