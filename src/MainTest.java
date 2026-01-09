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
    }
}