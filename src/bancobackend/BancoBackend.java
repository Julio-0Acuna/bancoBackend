/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 *//*
package bancobackend;

import bancobackend.bd.dbConnection;
import bancobackend.dao.CuentaDAO;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author julio
 
public class BancoBackend {

    /**
     * @param args the command line arguments
     
    public static void main(String[] args) {
        // TODO code application logic here
        
        dbConnection dbc = new dbConnection();
        dbc.conectar();
    }

public static void main(String[] args) throws SQLException {

        try (Connection con = dbConnection.conectar()) {
            System.out.println("Sistema Banco iniciado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
 
        CuentaDAO dao = new CuentaDAO();
        int id = dao.crearCuenta("Vanessa");
        System.out.println("Cuenta creada con ID: " + id);
        
}

}*/

package bancobackend;

import bancobackend.ui.Menu;

public class BancoBackend {
    public static void main(String[] args) {
        new Menu().iniciar();
    }
}