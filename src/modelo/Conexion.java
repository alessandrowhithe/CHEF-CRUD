/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jason
 */
public class Conexion {
    private static final String URL = "jdbc:oracle:thin:@LocalHost:1521:xe";
    private static final String USUARIO = "CHEF_DEVELOPER";
    private static final String CONTRASENA = "fakedrips";
    public static Connection getConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            return conexion;
        } catch (SQLException e) {
            System.out.println("Hubo un error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("La clase presentó un error" + ex);
              return null;
        }
    }
}
