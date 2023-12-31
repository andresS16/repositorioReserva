
package Controlador;
//import com.mysql.cj.xdevapi.Statement;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TransaccionesBD {
    
    public ResultSet realizarConsulta(String query){
        ConexionBD conexion = new ConexionBD();
        Connection conn = conexion.conectar();
        ResultSet rs = null;
        Statement stQuery;
        
        
        
        try {
            stQuery = conn.createStatement();
            rs= stQuery.executeQuery(query);
            
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al ejecutar : " + query + ":" + e);
        }
    
    return rs;
    }
    
    
    
    public Boolean ejecutarQuery(String query){ // sera usado para insertar , modificar y eliminar
        
        ConexionBD conexion = new ConexionBD();
        Connection conn = conexion.conectar();
        Boolean exito =false;
        Statement s;
        ResultSet rsid = null;
        
            try {
                PreparedStatement ps = conn.prepareStatement(query);
                int queryRecibido = ps.executeUpdate();

                    if(queryRecibido !=0)
                        exito = true;

                       else
                         exito=false;  
                System.out.println(" consulta recibida " + queryRecibido);
                JOptionPane.showMessageDialog(null,"Consulta recibida " + queryRecibido );

                } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,"Error al ejecutar  consulta : " + query + ":" + e);
                 exito =false;

        }
        return exito; 
    }
    
    
    
  public int contarDuplicados(String queryVerificar) {
    ConexionBD conexion = new ConexionBD();
    Connection conn = conexion.conectar();
    ResultSet rs = null;
    int count = 0;

    try {
        PreparedStatement pstmtVerificar = conn.prepareStatement(queryVerificar);
        rs = pstmtVerificar.executeQuery();
        rs.next();
        count = rs.getInt(1);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar duplicados: " + e.getMessage());
    } finally {
        // Cerrar recursos (ResultSet, PreparedStatement, Connection) aquí si es necesario
    }

    return count;
}
  
  
  
    public int contarDuplicados(String query, String param1, String param2) {
    ConexionBD conexion = new ConexionBD();
    Connection conn = conexion.conectar();
    ResultSet rs = null;
    int count = 0;

    try {
        PreparedStatement pstmtVerificar = conn.prepareStatement(query);
        pstmtVerificar.setString(1, param1);
        pstmtVerificar.setString(2, param2);
        rs = pstmtVerificar.executeQuery();
        rs.next();
        count = rs.getInt("countAula");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar duplicados: " + e.getMessage());
    } finally {
        // Cerrar recursos (ResultSet, PreparedStatement, Connection) aquí si es necesario
    }

    return count;
}
   public int contarDuplicadosNumero(String query, int param1 , LocalDate param2, int param3 ,int param4) {
    ConexionBD conexion = new ConexionBD();
    Connection conn = conexion.conectar();
    ResultSet rs = null;
    int count = 0;

    try {
        PreparedStatement pstmtVerificar = conn.prepareStatement(query);
        pstmtVerificar.setInt(1, param1);
        pstmtVerificar.setDate(2, java.sql.Date.valueOf(param2));
        pstmtVerificar.setInt(3, param3);
        pstmtVerificar.setInt(4, param4);
       
        rs = pstmtVerificar.executeQuery();
        rs.next();
        count = rs.getInt(1);
        if(count >0){
            JOptionPane.showMessageDialog(null, " Aula ocupada : No disponible en ese horario ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al verificar duplicados: " + e.getMessage());
    } finally {
        // Cerrar recursos (ResultSet, PreparedStatement, Connection) aquí si es necesario
    }

    return count;
}


    // ...

   /* public ResultSet ejecutarConsultaParametrizada(String query, int param1 , int param2, int param3 ,int param4 ) { // para hacer consultas para metrizadas
        ConexionBD conexion = new ConexionBD();
        Connection conn = conexion.conectar();
        ResultSet rs = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, param1);
            pstmt.setInt(2, param2);
            pstmt.setInt(3, param3);
            pstmt.setInt(4, param4);
            
            rs = pstmt.executeQuery();
             JOptionPane.showMessageDialog(null, "Ingreso exitoso ", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            // Manejar excepciones o errores aquí
            e.printStackTrace();
        }

        return rs;
    }*/

  
   
  public int ejecutarConsultaParametrizada(String query, int param1, int param2, int param3, int param4) {
    ConexionBD conexion = new ConexionBD();
    Connection conn = conexion.conectar();
    int count = 0;

    try {
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, param1);
        pstmt.setInt(2, param2);
        pstmt.setInt(3, param3);
        pstmt.setInt(4, param4);

        count = pstmt.executeUpdate();

        if (count > 0) {
            JOptionPane.showMessageDialog(null, "Filas afectadas: " + count, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Cerrar recursos (PreparedStatement, Connection) aquí si es necesario
    }

    return count;
}
  
  
public ResultSet ejecutarConsultaParametrizadaBUSCA_AULAS(String query, int capacidad, int horaDia, LocalDate fecha) {
    ConexionBD conexion = new ConexionBD();
    Connection conn = conexion.conectar();
    ResultSet rs = null;

    try {
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, capacidad);
        pstmt.setInt(2, horaDia);
        pstmt.setDate(3, java.sql.Date.valueOf(fecha));

        rs = pstmt.executeQuery();
    } catch (SQLException e) {
        // Manejar cualquier excepción de SQL
        e.printStackTrace();
    }

    return rs;
}
public ResultSet ejecutarConsultaParametrizadaBUSCA_OPCION(String query, String dia, int capacidad, LocalDate fecha) {
    ConexionBD conexion = new ConexionBD();
    Connection conn = conexion.conectar();
    ResultSet rs = null;

    try {
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, dia);
        pstmt.setInt(2, capacidad);
        
        pstmt.setDate(3, java.sql.Date.valueOf(fecha));
        pstmt.setDate(4, java.sql.Date.valueOf(fecha));

        rs = pstmt.executeQuery();
    } catch (SQLException e) {
        // Manejar cualquier excepción de SQL
        e.printStackTrace();
    }

    return rs;
}




    
}
