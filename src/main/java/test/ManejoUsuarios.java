package test;

import datos.Conexion;
import datos.UsuarioJDBC;
import domain.Usuario;
import java.sql.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;



public class ManejoUsuarios {

    public static void main(String[] args) {
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            UsuarioJDBC usuarioJdbc = new UsuarioJDBC(conexion);
            
            Usuario cambioUsuario = new Usuario();
            cambioUsuario.setId_usuario(2);
            cambioUsuario.setUsername("Karita");
            cambioUsuario.setPassword("321");
            usuarioJdbc.actualizar(cambioUsuario);
            
            Usuario nuevaUsuario = new Usuario();
            nuevaUsuario.setUsername("karinita");
            nuevaUsuario.setPassword("1234");
            usuarioJdbc.insertar(cambioUsuario);
            
            conexion.commit();

        } catch (SQLException ex) {
            try {
                ex.printStackTrace(System.out);
                System.out.println("Entramos al rollback");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }
}
