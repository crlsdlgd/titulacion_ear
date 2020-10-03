package ec.edu.uce.titulacion.controladores;

import ec.edu.uce.titulacion.entidades.Plan;
import ec.edu.uce.titulacion.dao.PlanDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "controladorPlan")
@SessionScoped
public class controladorPlan implements Serializable {

    @EJB
    private PlanDao planDao;
    
    private List<Plan> listaPlan;
    
    private String usuariosPlan;

    public String getUsuariosPlan() {
        return usuariosPlan;
    }

    public void setUsuariosPlan(String usuariosPlan) {
        this.usuariosPlan = usuariosPlan;
    }
    

    public List<Plan> getListaPlan() {
        return listaPlan;
    }

    public void setListaPlan(List<Plan> listaPlan) {
        this.listaPlan = listaPlan;
    }

    public controladorPlan() {
    }
    
    @PostConstruct
    public void init(){
        //cargarPlan();
        //mostrarPlan();
    }

    public void cargarPlan() throws Exception{
        listaPlan=planDao.listarPlan();
        
//        for(int i=0;i<listaPlan.size();i++){
//            for(int j=0;j<listaPlan.get(i).getUsuarioList().size();j++){
//                usuariosPlan+=listaPlan.get(i).getUsuarioList().get(j).getNombre()+", ";
//            }
//        }
//        System.out.println("lista de usuarios "+usuariosPlan);
    }

    public void mostrarPlan() {
        if(listaPlan.size()<0){
            System.out.println("No hay planes");
        }else{
            for(int i=0;i<listaPlan.size();i++){
                System.out.println(listaPlan.get(i).getTema());
            }
        }
    }
    
}
