package ec.edu.uce.titulacion.dao;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
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

    @Override
    public void guardarPlan(String tema,Date fecha, List<String> listIntegrantes) throws Exception {
        
        try {
            this.Conectar();
            this.getCn().setAutoCommit(false);
            System.out.println("aaaaaaaaaaa");
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO plan (tema,fecha) VALUES(?,?)");
            st.setString(1, tema);
            st.setDate(2, (Date) fecha);
            st.executeUpdate();
            st.close();
            
            System.out.println("bbbbbbbbbbbbbbbbbbb");
            PreparedStatement st2 = this.getCn().prepareStatement("SELECT MAX(id_plan) AS id_plan FROM plan");
            ResultSet rs;
            int idPlan =0;
            rs=st2.executeQuery();
            while(rs.next()){
                idPlan=rs.getInt("id_plan");
            }
            rs.close();
            st2.close();
            System.out.println("ccccccccccccccc  id plan"+idPlan);
            System.out.println(listIntegrantes.size());
            List<Usuario> listUsuario = new ArrayList();
            for(int i=0;i<listIntegrantes.size();i++){
                System.out.println("ccccccccccbbbbb");
                PreparedStatement st3 = this.getCn().prepareStatement("SELECT id_usuario FROM usuario WHERE nombre = ?");
                ResultSet rs2;
                st3.setString(1, listIntegrantes.get(i));
                rs2 = st3.executeQuery();
                while(rs2.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    System.out.println("ccccccccccccccaaaaaa id de usuario"+usuario.getIdUsuario());
                    listUsuario.add(usuario);
                }
                //rs2.close();
                st3.close();
            }
            System.out.println("dddddddddddddddddddddd");
            for(int i=0;i<listUsuario.size();i++){
                System.out.println("dddddddddddddbbbbbbbbbbbb");
                PreparedStatement st4 = this.getCn().prepareStatement("INSERT INTO plan_usuario (id_plan,id_usuario) VALUES (?,?)");
                st4.setInt(1, idPlan);
                st4.setInt(2, listUsuario.get(i).getIdUsuario());
                st4.executeUpdate();
                System.out.println("dddddddddddddddddddaaaaaaa idPlan"+idPlan+" idUsuario"+listUsuario.get(i).getIdUsuario());
                st4.close();
            }
            System.out.println("eeeeeeeeeeeeeeeee");
            this.getCn().commit();
            
        } catch (Exception e) {
            this.getCn().rollback();
            System.out.println("fffffffffffffffffffffff");
            throw e;
        } finally {
            System.out.println("gggggggggggggggggggg");
            this.Cerrar();
        }
    }

}
