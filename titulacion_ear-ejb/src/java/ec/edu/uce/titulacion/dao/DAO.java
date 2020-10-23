package ec.edu.uce.titulacion.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {

    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public void Conectar() throws Exception {

        try {
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/titulacion?user=postgres&password=Pass_1992");
        } catch (Exception e) {
            throw e;
        }
    }

    public void Cerrar() throws Exception {

        try {
            if (cn != null) {
                if (cn.isClosed() == false) {
                    cn.close();
                }
            }
        } catch (Exception e) {
        throw e;
        }

    }
}
