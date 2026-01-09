import models.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitaire pour les tests.
 */
public class TestHelper {
    
    // Créer une date facilement
    public static Date creerDate(int jour, int mois, int annee) {
        Calendar cal = Calendar.getInstance();
        cal.set(annee, mois - 1, jour, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    // Afficher les infos d'un client
    public static void afficherClient(Client c) {
        System.out.println(c);
        for (Reservation r : c.getReservations()) {
            System.out.println("  -> " + r);
        }
    }
}