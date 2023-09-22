/*
 * Permite la conexion con la Base de Datos
 */
package ConexionProductos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author madel
 */
    public class Conexion {
    Connection conectar=null;
    
    public Connection Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost/bdnegocio","root","");
        }catch (ClassNotFoundException |SQLException e){
            System.out.print(e.getMessage());
        }
        return conectar;
    }
}

