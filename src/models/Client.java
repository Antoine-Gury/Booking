package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe Client héritant de Personne.
 * Un client peut faire des réservations.
 */
public class Client extends Personne {
    
    private String adresse;
    private Date dateInscription;
    private ArrayList<Reservation> reservations;
    
    public Client(String nom, String prenom, String email, String adresse) {
        super(nom, prenom, email);
        this.adresse = adresse;
        this.dateInscription = new Date();
        this.reservations = new ArrayList<>();
    }
    
    // Getters
    public String getAdresse() {
        return adresse;
    }
    
    public Date getDateInscription() {
        return dateInscription;
    }
    
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
    
    @Override
    public String getTypePersonne() {
        return "Client";
    }
    
    // Ajouter une réservation
    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    
    @Override
    public String toString() {
        return getTypePersonne() + " : " + super.toString() + 
               " - " + reservations.size() + " réservation(s)";
    }
}