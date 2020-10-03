package ec.edu.uce.titulacion.dao;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.entidades.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class PlanDaoImpl extends DAO implements PlanDao{
    
    @Override
    public void registrar(Plan plan) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO plan (tema,fecha) VALUES(?,?)");
            st.setString(1, plan.getTema());
            st.setDate(2, (Date) plan.getFecha());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
    
    @Override
    public List<Plan> listarPlan() throws Exception{
        List<Plan> lista;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareCall("SELECT id_plan, tema, fecha FROM plan");
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setIdPlan(rs.getInt("id_plan"));
                plan.setTema(rs.getString("tema"));
                plan.setFecha(rs.getDate("fecha"));
                lista.add(plan);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }
    
    @Override
    public List<Plan> listarPlanByUser(Usuario usuario) throws Exception{
        List<Plan> lista;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement st = this.getCn().prepareCall("SELECT p.id_plan, p.tema, p.fecha\n" +
                                                            "FROM plan p, usuario u, plan_usuario pu\n" +
                                                            "WHERE p.id_plan= pu.id_plan AND\n" +
                                                            "pu.id_usuario = u.id_usuario AND u.id_usuario=?");
            
            st.setInt(1, usuario.getIdUsuario());
            rs = st.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Plan plan = new Plan();
                plan.setIdPlan(rs.getInt("id_plan"));  
                plan.setTema(rs.getString("tema"));
                plan.setFecha(rs.getDate("fecha"));
                lista.add(plan);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }
}
