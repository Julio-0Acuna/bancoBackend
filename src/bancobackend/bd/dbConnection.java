/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancobackend.bd;
import java.sql.*;

/**
 *
 * @author Julio Acuña
 */
public class dbConnection {
    /*
   static String url="jdbc:mysql://localhost:3306/banco";
   static String user="root";
   static String password ="123456789";
   */
    
    private static final String URL =
            "jdbc:mysql://localhost:3306/banco?useSSL=false&serverTimezone=UTC";

    private static final String USUARIO = "root";
    private static final String CLAVE = "123456789";
    private dbConnection(){
        
    }
    
    public static Connection conectar() throws SQLException{
        
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
        
        /*Connection con=null;
        try{
          con = DriverManager.getConnection(url, user, password); 
                System.out.println("Conexion exitosa");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Fallo");
        }
        return con;*/
       
        
    }
    
    
}
