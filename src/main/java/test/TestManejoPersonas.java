package test;

import datos.Conexion;
import datos.PersonaJDBC;
import domain.Persona;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestManejoPersonas {

    public static void main(String[] args) {
        
        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaJDBC personaJdbc = new PersonaJDBC(conexion);
            
            Persona cambioPersona = new Persona();
            cambioPersona.setIdPersona(1);
            cambioPersona.setNombre("Ingrid");
            cambioPersona.setApellido("Arteaga");
            cambioPersona.setEmail("ingridk@mail.com");
            cambioPersona.setTelefono("36345356");
            personaJdbc.actualizar(cambioPersona);
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Lina");
            nuevaPersona.setApellido("Montoya");
            personaJdbc.insertar(nuevaPersona);
            
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");
            
        } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                System.out.println("Entramos al rollback");
                try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
