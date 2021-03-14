/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prednaska1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author vsa
 */
public class Prednaska1 {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("prednaska1PU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from kniha", Kniha.class);
        List<Kniha> knihy = q.getResultList();
        for(Kniha k : knihy){
            System.out.println(""+k);
     
    }

  

    }
  /*   public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        Statement st = (Statement) con.createStatement();
        ResultSet rs = st.executeQuery("select * from KNIHA");
        List<Kniha> knihy= new ArrayList<>();
        while(rs.next()){
            Kniha k = new Kniha();
            k.nazov = rs.getString(1);
            k.cena = rs.getDouble("CENA");
            knihy.add(k);
            System.out.println(""+k);
        }

    }*/
    
}
