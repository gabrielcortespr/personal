
package modelo;

import sql.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class personal {
    protected int cod;
    protected String rut;
    protected String nombre;
    protected String apellido;
    protected String celular;
    protected String email;
    protected String sueldo_bruto;
    protected String est_civil;
    protected String nom_depto;

    public personal() {
    }

    public personal(int cod, String rut, String nombre, String apellido, String celular, String email, String sueldo_bruto, String est_civil, String nom_depto) {
        this.cod = cod;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.email = email;
        this.sueldo_bruto = sueldo_bruto;
        this.est_civil = est_civil;
        this.nom_depto = nom_depto;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSueldo_bruto() {
        return sueldo_bruto;
    }

    public void setSueldo_bruto(String sueldo_bruto) {
        this.sueldo_bruto = sueldo_bruto;
    }

    public String getEst_civil() {
        return est_civil;
    }

    public void setEst_civil(String est_civil) {
        this.est_civil = est_civil;
    }

    public String getNom_depto() {
        return nom_depto;
    }

    public void setNom_depto(String nom_depto) {
        this.nom_depto = nom_depto;
    }
    
    //Verificar empleado
    public static boolean buscarEmp(Integer codigo) {
        try {
            Connection cnx = conexion.getConexion();
            String query = "SELECT * FROM personal WHERE cod = ? ";
            PreparedStatement select = cnx.prepareStatement(query);
            select.setInt(1, codigo);
            select.execute();
            ResultSet rs = select.getResultSet();
            
            if (rs.next()) {
                cnx.close();
                return true;
            } else {
                cnx.close();
                return false;
            }

        } catch (SQLException s) {
            System.out.println("Error SQL  " + s.getMessage());
            return true;
        } catch (Exception e) {
            System.out.println("Error SQL" + e.getMessage());
            return true;
        }
    }
    
    //Agregar PELICULA
    public static boolean guardarEmp(int cod, String rut, String nombre, String apellido, String celular, String email, String sueldo_bruto, String est_civil, String nom_depto) {

        try {
            Connection cnx_save = conexion.getConexion();
            String query = "INSERT INTO personal (cod,rut,nombre,apellido,celular,email,sueldo_bruto,est_civil,nom_depto) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement insertar = cnx_save.prepareStatement(query);
            insertar.setInt(1, cod);
            insertar.setString(2, rut);
            insertar.setString(3, nombre);
            insertar.setString(4, apellido);
            insertar.setString(5, celular);
            insertar.setString(6, email);
            insertar.setString(7, sueldo_bruto);
            insertar.setString(8, est_civil);
            insertar.setString(9, nom_depto);
            insertar.execute();
            insertar.close();
            cnx_save.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Error SQL al agregar " + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al agregar " + e.getMessage());
            return false;
        }
    }
    
    public DefaultTableModel ListadoEmp(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Id","Código","Rut","Nombre","Apellido","Celular","Email","Sueldo Bruto","Estado Civil","Departamento"};
      try{
         Connection cnx_count = conexion.getConexion();
         PreparedStatement pstm = cnx_count.prepareStatement( "SELECT count(*) as total FROM personal");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][10];
      try{
         Connection cnx_select = conexion.getConexion();
         PreparedStatement pstm = cnx_select.prepareStatement("SELECT * FROM personal");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "cod" );
                data[i][2] = res.getString( "rut" );
                data[i][3] = res.getString( "nombre" );
                data[i][4] = res.getString( "apellido" );
                data[i][5] = res.getString( "celular" );
                data[i][6] = res.getString( "email" );
                data[i][7] = res.getString( "sueldo_bruto" );
                data[i][8] = res.getString( "est_civil" );
                data[i][9] = res.getString( "nom_depto" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    public DefaultTableModel ListadoEmpRedes(){
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Id","Código","Rut","Nombre","Apellido","Celular","Email","Sueldo Bruto","Estado Civil","Departamento"};
      try{
         Connection cnx_count = conexion.getConexion();
         PreparedStatement pstm = cnx_count.prepareStatement( "SELECT count(*) as total FROM personal");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
      Object[][] data = new String[registros][10];
      try{
         Connection cnx_select = conexion.getConexion();
         PreparedStatement pstm = cnx_select.prepareStatement("SELECT * FROM personal WHERE nom_depto='Redes'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "cod" );
                data[i][2] = res.getString( "rut" );
                data[i][3] = res.getString( "nombre" );
                data[i][4] = res.getString( "apellido" );
                data[i][5] = res.getString( "celular" );
                data[i][6] = res.getString( "email" );
                data[i][7] = res.getString( "sueldo_bruto" );
                data[i][8] = res.getString( "est_civil" );
                data[i][9] = res.getString( "nom_depto" );
            i++;
         }
         res.close();
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    public static boolean eliminarEmp(int codigo) {
        int n = 0;
        PreparedStatement borrar;
        try {
            Connection cnx_elim = conexion.getConexion();
            String query = "DELETE FROM personal WHERE cod=?";
            borrar = cnx_elim.prepareStatement(query);
            borrar.setInt(1, codigo);
            n = borrar.executeUpdate();
            borrar.close();           
            cnx_elim.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Error SQL al eliminar" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            return false;
        }    
    }
    
    public static boolean eliminarEmpPorSueldo() {
        int n = 0;
        PreparedStatement borrar;
        try {
            Connection cnx_elim_sueldo = conexion.getConexion();
            String query = "DELETE FROM personal WHERE sueldo_bruto='120000'";
            borrar = cnx_elim_sueldo.prepareStatement(query);
            n = borrar.executeUpdate();
            borrar.close();           
            cnx_elim_sueldo.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Error SQL al eliminar" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            return false;
        }    
    }
    
    public static boolean modificaSueldo() {
        PreparedStatement borrar;
        try {
            Connection cnx_mod = conexion.getConexion();
            String query = "UPDATE personal SET sueldo_bruto = (sueldo_bruto*0.1)+sueldo_bruto";
            borrar = cnx_mod.prepareStatement(query);
            borrar.executeUpdate();
            borrar.close();           
            cnx_mod.close();
            return true;
        } catch (SQLException s) {
            System.out.println("Error SQL al modificar" + s.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al modificar" + e.getMessage());
            return false;
        }    
    }
    
    public ArrayList<personal> buscarPorCodigo(int cod){
        ArrayList<personal> listaPersonal = new ArrayList<personal>();
        try{
            Connection cnx4 = conexion.getConexion();
            String query = "SELECT * FROM personal WHERE cod = ?";
            PreparedStatement buscarPorCodigo = cnx4.prepareStatement(query);
            buscarPorCodigo.setInt(1, cod);
            ResultSet rs = buscarPorCodigo.executeQuery();
            while(rs.next()){
                personal pers = new personal();
                pers.setRut(rs.getString("rut"));
                pers.setNombre(rs.getString("nombre"));
                pers.setApellido(rs.getString("apellido"));
                pers.setCelular(rs.getString("celular"));
                pers.setEmail(rs.getString("email"));
                pers.setSueldo_bruto(rs.getString("sueldo_bruto"));
                pers.setEst_civil(rs.getString("est_civil"));
                pers.setNom_depto(rs.getString("nom_depto"));
                listaPersonal.add(pers);
            }
        }catch(SQLException s){
            System.out.println("Error SQL al buscar"+s.getMessage());
        }catch(Exception e){
            System.out.println("Error al buscar"+e.getMessage());
        }
        return listaPersonal;
    }
    
    public boolean modificaEmpleado(int cod, String rut, String nom, String apell, String cel, String email, String sueldo, String est_civ, String depto){
        int s = 1;
        
        String q= "UPDATE personas_contratadas.personal SET rut='"+rut+"', nombre='"+nom+"', apellido='"+apell+"' , celular='"+cel+"' , email='"+email+"', sueldo_bruto='"+sueldo+"', est_civil='"+est_civ+"', nom_depto='"+depto+"' "
                + " WHERE cod='"+cod+"' ";
        try {
            Connection cnx_update = conexion.getConexion();
            PreparedStatement pstm2 = cnx_update.prepareStatement(q);
            pstm2.execute();
            pstm2.close();
            return true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return false;
    }
}
