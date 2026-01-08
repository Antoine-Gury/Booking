import models.*;
import java.util.Date;
import java.util.Calendar;

/**
 * Programme de test pour la Partie 1.
 */
public class MainTest {
    
    public static void main(String[] args) {
        
        System.out.println("=== TEST PARTIE 1 : Mini-Booking ===\n");
        
        // Test 1 : Création de clients
        System.out.println("--- Création de clients ---");
        Client client1 = new Client("Dupont", "Jean", "jean@email.com", "12 rue de Paris");
        Client client2 = new Client("Martin", "Sophie", "sophie@email.com", "45 avenue Hugo");
        System.out.println(client1);
        System.out.println(client2);
        
        // Test 2 : Création d'hébergements
        System.out.println("\n--- Création d'hébergements ---");
        Hebergement hotel = new Hebergement(
            "Hôtel Belle Vue",
            "10 Boulevard de la Mer, Nice",
            "Hôtel",
            2,
            120.0,
            "Hôtel avec vue sur mer"
        );
        hotel.setWifi(true);
        hotel.setTv(true);
        
        Hebergement appart = new Hebergement(
            "Appart Centre",
            "25 rue de la République, Paris",
            "Appartement",
            4,
            85.0,
            "Appartement moderne"
        );
        appart.setWifi(true);
        appart.setCuisine(true);
        
        System.out.println(hotel);
        System.out.println(appart);
        
        // Test 3 : Ajout de notes
        System.out.println("\n--- Ajout de notes ---");
        hotel.ajouterNote(4.5);
        hotel.ajouterNote(5.0);
        hotel.ajouterNote(4.0);
        System.out.println("Note moyenne hôtel : " + hotel.getNoteMoyenne());
        
        appart.ajouterNote(4.2);
        appart.ajouterNote(4.8);
        System.out.println("Note moyenne appart : " + appart.getNoteMoyenne());
        
        // Test 4 : Gestion des disponibilités
        System.out.println("\n--- Test disponibilités ---");
        Date debut = creerDate(1, 2, 2025);
        Date fin = creerDate(28, 2, 2025);
        hotel.ajouterPeriodeDisponible(debut, fin);
        
        Date arrivee = creerDate(15, 2, 2025);
        Date depart = creerDate(20, 2, 2025);
        boolean dispo = hotel.estDisponible(arrivee, depart);
        System.out.println("Hôtel disponible 15-20 fév ? " + dispo);
        
        // Test 5 : Calcul de prix
        System.out.println("\n--- Calcul de prix ---");
        double prix = hotel.calculerPrixTotal(arrivee, depart);
        System.out.println("Prix pour 5 nuits : " + prix + "€");
        
        // Test 6 : Création de réservations
        System.out.println("\n--- Création de réservations ---");
        Reservation resa1 = new Reservation(client1, hotel, arrivee, depart);
        resa1.confirmer();
        client1.ajouterReservation(resa1);
        System.out.println(resa1);
        
        Reservation resa2 = new Reservation(client2, appart, debut, fin);
        resa2.appliquerReduction(10.0);
        resa2.confirmer();
        client2.ajouterReservation(resa2);
        System.out.println(resa2);
        
        // Test 7 : Affichage final
        System.out.println("\n--- Résumé ---");
        System.out.println(client1 + " : " + client1.getReservations().size() + " réservation(s)");
        System.out.println(client2 + " : " + client2.getReservations().size() + " réservation(s)");
    }
    
    // Méthode utilitaire pour créer des dates facilement
    private static Date creerDate(int jour, int mois, int annee) {
        Calendar cal = Calendar.getInstance();
        cal.set(annee, mois - 1, jour, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}