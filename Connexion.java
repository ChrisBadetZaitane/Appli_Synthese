package applisynthese;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {

    String url;
    String login;
    String passwd;
    Connection cn;
    Statement st;
    ResultSet rs;
        
    public Connexion(){
        url = "jdbc:mysql://134.214.113.155/p1512799";
        login = "p1512799";
        passwd = "248128";
        cn = null;
        st = null;
        rs = null;
    }
    
    public void openConnexion(){
        try {
            // Etape 1 : Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // Etape 2 : recuperation de la connexion
            cn = DriverManager.getConnection(url, login, passwd);
        }catch (java.sql.SQLException e){
            System.out.println("Erreur connexion : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur connexion : " + e.getMessage());
        }
        if (cn == null) {
            System.out.println("Probleme de connexion.");
            System.exit(1);
        }
        
    }
    
    public void closeConnexion(){
        try {
            cn.close();	// Fermeture de la connexion
      }catch (java.sql.SQLException e){
            System.out.println("ERREUR MySQL" + e.getMessage());
      }
    }
    

    public void modifBase(String requete) {

        try {
            if (cn == null) {
                openConnexion();
            }
            // Etape 3 : Creation d'un statement
            st = cn.createStatement();

            String sql = requete;

            // Etape 4 : execution requete
            //rs = st.executeQuery(sql);
            int rowsAffected = st.executeUpdate(sql);

            // Si recup donn�es alors etapes 5 (parcours Resultset)

            //while (rs.next()) {
            //	System.out.println(rs.getString("name"));

            //}
    } catch (SQLException e) {
            e.printStackTrace();
    } finally {
            try {
            // Etape 6 : liberer ressources de la memoire.
                st.close();
                closeConnexion();
            } catch (SQLException e) {
                    e.printStackTrace();
            }
    }
    }
    
    
}