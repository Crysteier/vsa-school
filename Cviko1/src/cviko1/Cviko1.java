/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cviko1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Cviko1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cviko1PU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from kniha", Kniha.class);
        List<Kniha> knihy = q.getResultList();
        knihy.stream().forEach((k) -> {
            System.out.println(""+k);
        });
        
        System.out.println("Cena knihy je: "+cenaKnihy("Hobit"));
        System.out.println(pridajKnihu("For whom the bell tolls",3.65));
        zlava("wer");
    }
    
    public static double cenaKnihy(String nazov){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cviko1PU");
        EntityManager em = emf.createEntityManager();
        Query q = em.createNativeQuery("select * from kniha where nazov='"+nazov+"'", Kniha.class);
        Kniha k = new Kniha();
        k=(Kniha) q.getSingleResult();
        double cena =k.getCena();
        return cena;
    }
    
    public static boolean pridajKnihu(String meno, double cena) throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        Statement st = (Statement) con.createStatement();
        try{boolean kontrol = st.execute("INSERT INTO KNIHA VALUES ('"+meno+"',"+cena+")");
        }    
        catch(Exception SQLException){
            return false;
        }
        return true;
    }
    public static void zlava(String nazov) throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
        Statement st = (Statement) con.createStatement();
        try{
            double cena = cenaKnihy(nazov);
            double pom = 0.2 * cena;
            cena = cena-pom;
            st.executeUpdate("UPDATE KNIHA SET CENA="+cena+" where nazov='"+nazov+"'");
            System.out.println("Nova cena knihy '"+nazov+"' po zlave 20% je "+cena);
        }
        catch(Exception SQLException){
            System.out.println("Kniha neni v DB");
        }
        
    }
}
