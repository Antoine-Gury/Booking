<<<<<<< HEAD
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
=======
import models.*;
import java.util.Date;

public class MainTest {
    
    public static void main(String[] args) {
        // Création d'un client
        Client client = new Client("Dupont", "Jean", "jean@email.com", "Paris");
        
        // Création d'un hébergement
        Hebergement hotel = new Hebergement("Hôtel Nice", "Nice", "Hôtel", 2, 100.0, "Belle vue");
        hotel.setWifi(true);
        
        // Ajout de disponibilités
        Date debut = TestHelper.creerDate(1, 2, 2025);
        Date fin = TestHelper.creerDate(28, 2, 2025);
        hotel.ajouterPeriodeDisponible(debut, fin);
        
        // Création d'une réservation
        Date arrivee = TestHelper.creerDate(10, 2, 2025);
        Date depart = TestHelper.creerDate(15, 2, 2025);
        Reservation resa = new Reservation(client, hotel, arrivee, depart);
        resa.confirmer();
        client.ajouterReservation(resa);
        
        // Affichage
        TestHelper.afficherClient(client);
>>>>>>> 16a746b87bf0a59e7fafe614f7982eb4a300ec52
    }
}