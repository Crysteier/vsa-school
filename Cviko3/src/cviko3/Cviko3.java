/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviko3;

import entities.TOsoba;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author guntel
 */
public class Cviko3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //neurobim vsetky ulohy lebo som to uz raz urobil
        //len tu nieco necham pre ukazku lebo su vsetky trivialne
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cviko3PU");
        EntityManager em = emf.createEntityManager(); 
        TOsobaJpaController dao = new TOsobaJpaController(emf);
        
        //ked ma zaznam v db vaha na NULL tak setne na 80 super easy
        TypedQuery<TOsoba> q = em.createNamedQuery("TOsoba.setTucko", TOsoba.class);
        List<TOsoba> osoby = q.getResultList();
        for(TOsoba o: osoby){
            em.getTransaction().begin();
            o.setVaha(80d);
            em.getTransaction().commit();
        }
        
        //----------------------------------------------------------------------
        //ulohy s entity managerom neurobil lebo su self explenatory v nazve
        //----------------------------------------------------------------------
        
        //ulohy s JPA niektore
        //vytvorit novy zaznam
        TOsoba osoba = new TOsoba(5L,"Kubo");
        //dao.create(osoba);
        
        //editovat zaznam
        osoba.setVaha(125d);
        dao.edit(osoba);
        
        //find by long ID
        System.out.println(dao.findTOsoba(2L));
        
        //ziska vsetky zaznamy
        System.out.println(dao.getTOsobaCount());
    }
    
    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cviko3PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
