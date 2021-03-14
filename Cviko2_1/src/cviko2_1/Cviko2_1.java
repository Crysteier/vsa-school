/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviko2_1;

import entities.Person;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author guntel
 */
public class Cviko2_1 {
    private EntityManager em;

    public Cviko2_1() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cviko2_1PU");
        this.em= emf.createEntityManager();
    }
    

    public static void main(String[] args) {
        Cviko2_1 cv = new Cviko2_1();
        cv.showAllPersons();
        //System.out.println(cv.addPerson("Tibor", (float) 55.2));
        System.out.println(cv.findPerson(41L));
    }
    
    public Long addPerson(String meno,float vaha){
        Person p = new Person();
        p.setName(meno);
        p.setWeight(vaha);
        persist(p);
        return p.getId();
    }
    
    public Person findPerson(Long id){
        Person p = this.em.find(Person.class, id);
        return p;
    }
//uloha 4 - hotova    
    public void showAllPersons(){
        Query q = this.em.createNativeQuery("select * from T_OSOBA", Person.class);
        List<Person> persons = q.getResultList();
        persons.forEach((p) -> {
            System.out.println(""+p);
        });
    }

    public void persist(Object object) {
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
