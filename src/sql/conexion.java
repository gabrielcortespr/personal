/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Duoc UC
 */
public class conexion {
    public static Connection getConexion() {
        Connection connection = null;
        try {
           String driverClassName = "com.mysql.jdbc.Driver";
           String driverUrl="jdbc:mysql://localhost/personas_contratadas";
           Class.forName(driverClassName);
           connection = DriverManager.getConnection(driverUrl, "root","");
           System.out.println("BRAVO!!!");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
