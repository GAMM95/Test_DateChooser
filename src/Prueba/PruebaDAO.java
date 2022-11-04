package Prueba;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PruebaDAO extends Conexion {
    //  Atributos

    private Connection cn = null;
    private ResultSet rs = null;
    private CallableStatement cs = null;
    private PreparedStatement ps = null;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//    DateFormat df = new SimpleDateFormat("yyyy-M-d");

    //  Instancia de la clase cargoDAO
    private static PruebaDAO instancia;

    public static PruebaDAO getInstancia() {
        if (instancia == null) {
            instancia = new PruebaDAO();
        }
        return instancia;
    }

    // Metodo para ingresar nuevos cargos
    public void registrarFecha(Prueba x) throws SQLException {
        cn = getConexion();
//        String fechaParaSQL = df.format(x.getFecha());
        String sql = "insert into prueba (fecha) values (?)";
        try {
            ps = cn.prepareStatement(sql);
            if (x.getFecha() != null) {
//                cs.setDate(1, new java.sql.Date(x.getFecha().getTime()));
                ps.setDate(1, java.sql.Date.valueOf(df.format(x.getFecha())));
            } else {
                ps.setDate(1, null);
            }
//            ps.setDate(1, java.sql.Date.valueOf(fechaParaSQL));
//            ps.setDate(1, Date.valueOf(df.format(x.getFecha())));
//            ps.setString(1, x.getFecha1());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage()); //Propagar la excepcion
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }
}
