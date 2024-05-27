import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        Empleado a1 = new EmpleadoTemporal("Teresa", "Garcia", "2AB", 100, "12/04/2024", 3);
        Empleado a2 = new EmpleadoTemporal("Pepe", "Garcia", "3AB", 200, "12/04/2024", 2);
        Empleado b1 = new Gerente("Nacho", "Perez", "4AB", 350, "Contabilidad", 3);
        Empleado b2 = new Gerente("Sara", "Perez", "5AB", 400, "Contabilidad", 4);
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
            oos.writeObject(main.empleados);//Falta el hasmap
            System.out.println("Empleados serializados correctamente");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void cargarDatosDesdeFichero(String nombreFichero) throws FileNotFoundException, IOException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
            main.empleados = (ArrayList<Empleado>) ois.readObject();//falta el hashmap
            System.out.println("Empleados deserializados correctamente");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void guardarEnDB(Statement st){
        int filasAfectadas3 = 0;
        try {
            for(Empleado emple : main.empleados){
            //Comprobar si existe el empleado
            ResultSet rs = st.executeQuery("SELECT id FROM empleado WHERE id = '" + emple.getId() + "'");
            if (rs.next()) {
                System.out.println("El empleado" + emple.getNombre() + "ya existe en la base de datos.");
            }else {
                // Insertar nuevo empleado si el DNI no existe
                String sentenciaSql = "INSERT INTO public.empleado(id, nombre, apellido, salario) VALUES (?, ?, ?, ?);";

                PreparedStatement ps = st.getConnection().prepareStatement(sentenciaSql);
                ps.setString(1, emple.getId()) ;//parametro 1 del Insert
                ps.setString(2, emple.getNombre());//paranetro 2 del Insert
                ps.setString(3, emple.getApellido());
                ps.setInt(4, emple.getSalario());

                filasAfectadas3 = ps.executeUpdate();
                if (filasAfectadas3 > 0) {
                    System.out.println("Se guardo en BD sin problema.");
                    st.getConnection().commit();
                } else {
                    System.out.println("No se pudo guardar en BD.");
                }
                

                if(emple instanceof Gerente){
                    String sentenciaSql2 = "INSERT INTO public.gerente(niveljerarquico, id, departamento) VALUES (?, ?, ?);";
                    PreparedStatement ps2 = st.getConnection().prepareStatement(sentenciaSql2);
                    ps2.setInt(1,((Gerente) emple).getNivelJerarquico());//paranetro 2 del Insert
                    ps2.setString(2,  emple.getId());
                    ps2.setString(3, ((Gerente) emple).getDepartamento()) ;//parametro 1 del Insert

                    int filasAfectadas = ps2.executeUpdate();
                    if (filasAfectadas > 0) {
                        System.out.println("1parte Se guardo el Gerente sin problema.");
                        st.getConnection().commit();
                    } else {
                         System.out.println("No se pudo guardar el Gerente.");
                    }
                }
                if(emple instanceof EmpleadoTemporal){
                    String sentenciaSql3 = "INSERT INTO public.empleadotemporal(duracioncontrato, id, fechacontrato)  VALUES (?, ?, ?);";
                    PreparedStatement ps3 = st.getConnection().prepareStatement(sentenciaSql3);
                    ps3.setInt(1, ((EmpleadoTemporal) emple).getDuracioncontrato());//paranetro 2 del Insert
                    ps3.setString(2,  emple.getId());
                    ps3.setString(3, ((EmpleadoTemporal) emple).getFechacontrato());
                    int filasAfectadas = ps3.executeUpdate(); //da error
                    if (filasAfectadas > 0) {
                       System.out.println("1parte Se guardo el empleado temporal sin problema.");
                       st.getConnection().commit();
                    } else {
                       System.out.println("No se pudo guardar el Gerente.");
                    }
                }

                ps.close();
            }
            rs.close();
            }
           
          } catch (SQLException sqle) {
            sqle.printStackTrace();
          }
    }

    public static void cargarDatosDesdeBD(Statement st) throws SalarioInvalidoException{
        main.empleados.clear();
        main.ordEmpleados.clear();
        try {
                String sentenciaSql = "	SELECT * FROM public.empleado INNER JOIN public.empleadotemporal USING(id);";
                PreparedStatement sentencia = null;
                ResultSet resultado = null;
                
                try {
                sentencia = st.getConnection().prepareStatement(sentenciaSql);
                resultado = sentencia.executeQuery();
                while (resultado.next()) {
                    Empleado empleadoCarga = new EmpleadoTemporal(null, null, null, 500, null, null);
                    empleadoCarga.setNombre(resultado.getString("nombre"));
                    empleadoCarga.setApellido(resultado.getString("apellido"));
                    empleadoCarga.setId(resultado.getString("id"));
                    empleadoCarga.setSalario(resultado.getInt("salario"));
                    ((EmpleadoTemporal)empleadoCarga).setFechacontrato(resultado.getString("fechacontrato"));
                    ((EmpleadoTemporal)empleadoCarga).setDuracioncontrato(resultado.getInt("duracioncontrato"));


                    try{
                        main.empleados.add(empleadoCarga);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }

                    try{
                        main.ordEmpleados.put(resultado.getString("id"), empleadoCarga);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }


                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }
                
                System.out.println("Empleados cargados de la base de datos correctamente");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
