
import java.sql.*;
import Conexion.Conexion;
import javax.swing.JOptionPane;
import org.omg.CORBA.ORB;
public class Persona extends PersonaApp.PersonaPOA{
    private ORB orb;
Conexion conectar = new Conexion();

    @Override
    public boolean insertarPersona(int identificacion, String nombre, String apellido, String direccion, String telefono, String email) {

                        boolean resultado = false;
                try {
                String sentenciaSql = "insert into persona (identificacion, nombre, apellido, direccion, telefono, email)"
                + "values('"+ identificacion +"','"+ nombre +"','"+ apellido +"','"+ direccion +"','"+ telefono +"','"+
                email +"')";
                conectar.conectar();
                Statement st = conectar.conex.createStatement();
                int valor = st.executeUpdate(sentenciaSql);
                if(valor > 0){
                resultado = true;
                }
                //Se cierran los recursos
                st.close();
                conectar.conex.close();
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al insertar una nueva persona. " +
                e.getMessage());
                }
                return resultado;
                }
    @Override
    public String consultarPersona(int identificacion) {
        
    boolean resultado = false;
                String lista = "";
                try{
                String sentenciSql = "Select * from persona where identificacion = " + identificacion;
                conectar.conectar();
                Statement st = conectar.conex.createStatement();
                ResultSet rs = st.executeQuery(sentenciSql);
                while(rs.next()){
                lista += rs.getLong(2) + " - "
                + rs.getString(3)+ " - "
                + rs.getString(4)+ " - "
                + rs.getString(5)+ " - "
                + rs.getString(6)+ " - "
                + rs.getString(7);
                }
                //Se cierran los recursos.
                rs.close();
                conectar.conex.close();
                }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + ex.getMessage());
                }
                return lista;
                }

    @Override
    public boolean actualizarPersona(int identificacion, String nombre, String apellido, String direccion, String telefono, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarPersona(int identificacion) {
            boolean resultado = false;
                 try{
                 String sentenciSql = "delete from persona where identificacion = " + identificacion;
                 conectar.conectar();
                 Statement st = conectar.conex.createStatement();
                 int valor = st.executeUpdate(sentenciSql);
                 if(valor > 0){
                 resultado = true;
                 }
                 //Se cierran los recursos.
                 st.close();
                 conectar.conex.close();
                 }catch(Exception ex){
                 JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + ex.getMessage());
                 }
                 return resultado;
                 }

    @Override
    public String listarPersonas() {
                            String lista = "";
                       try{
                       String sentenciSql = "Select * from persona";
                       conectar.conectar();
                       Statement st = conectar.conex.createStatement();
                       ResultSet rs = st.executeQuery(sentenciSql);
                       while(rs.next()){
                       lista += rs.getLong(2) + " - "
                       + rs.getString(3)+ " - "
                       + rs.getString(4)+ " - "
                       + rs.getString(5)+ " - "
                       + rs.getString(6)+ " - "
                       + rs.getString(7) + "\n";
                       }
                       //Se cierran los recursos.
                       rs.close();
                       conectar.conex.close();
                       }catch(Exception ex){
                       JOptionPane.showMessageDialog(null, "Ocurrio un error en el catch: " + ex.getMessage());
                       }
                       return lista;
                       }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ResultSet cargarTablaPersona() {
ResultSet resultado = null;
try {
String query = "Select identificacion,nombre,apellido from persona";
conectar.conectar();
Statement st = conectar.conex.createStatement();
resultado = st.executeQuery(query);
} catch (Exception e) {
JOptionPane.showMessageDialog(null, "Ocurrio un error: "+e.getMessage());
}
return resultado;
}
    
    
}
