/**
 * Programme de test pour les Parties 1 et 2.
 * 
 * Ce fichier contient les tests des classes développées par vos collègues.
 * À implémenter quand les Parties 1-2 seront disponibles.
 */
public class MainTest {
    
    public static void main(String[] args) {
        System.out.println("=== TEST PARTIES 1 et 2 : Mini-Booking ===\n");
        System.out.println("Vos collègues doivent implémenter les classes suivantes :");
        System.out.println("  - Client, NouveauClient, AncienClient, Administrateur");
        System.out.println("  - Hebergement, ChambreHotel, Appartement, Villa");
        System.out.println("  - Reservation, Reservable, PeriodeDisponible");
        System.out.println("  - DateUtils (classe utilitaire)");
        System.out.println("\nUne fois ces classes implémentées, ce fichier pourra être mis à jour avec des tests.");
    }
}
    // Méthode utilitaire pour créer des dates facilement
    private static Date creerDate(int jour, int mois, int annee) {
        Calendar cal = Calendar.getInstance();
        cal.set(annee, mois - 1, jour, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}