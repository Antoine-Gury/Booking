package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe Appartement.
 * Représente un appartement avec équipements complets.
 */
public class Appartement extends Hebergement implements Reservable {
    
    private int nombreChambres;
    private int nombreSallesDeBain;
    private double superficie;
    private boolean balcon;
    private boolean parking;
    private ArrayList<Reservation> reservations;
    
    public Appartement(String nom, String adresse, int capaciteMax, 
                      double prixParNuit, String description,
                      int nombreChambres, int nombreSallesDeBain, double superficie) {
        super(nom, adresse, "Appartement", capaciteMax, prixParNuit, description);
        this.nombreChambres = nombreChambres;
        this.nombreSallesDeBain = nombreSallesDeBain;
        this.superficie = superficie;
        this.balcon = false;
        this.parking = false;
        this.reservations = new ArrayList<>();
    }
    
    // Getters et Setters spécifiques
    public int getNombreChambres() {
        return nombreChambres;
    }
    
    public int getNombreSallesDeBain() {
        return nombreSallesDeBain;
    }
    
    public double getSuperficie() {
        return superficie;
    }
    
    public boolean hasBalcon() {
        return balcon;
    }
    
    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }
    
    public boolean hasParking() {
        return parking;
    }
    
    public void setParking(boolean parking) {
        this.parking = parking;
    }
    
    // Implémentation de Reservable
    @Override
    public boolean verifierDisponibilite(Date dateDebut, Date dateFin) {
        // Vérifier si les dates ne se chevauchent pas avec les réservations existantes
        for (Reservation res : reservations) {
            if (!res.getStatut().equals(Reservation.ANNULEE)) {
                // Vérifier le chevauchement
                if (!(dateFin.before(res.getDateArrivee()) || dateDebut.after(res.getDateDepart()))) {
                    return false; // Il y a un chevauchement
                }
            }
        }
        // Vérifier aussi avec la méthode de la classe parent
        return estDisponible(dateDebut, dateFin);
    }
    
    @Override
    public Reservation reserver(Date dateDebut, Date dateFin, Client client) {
        // Vérifier la disponibilité
        if (!verifierDisponibilite(dateDebut, dateFin)) {
            return null;
        }
        
        // Créer la réservation
        Reservation reservation = new Reservation(client, this, dateDebut, dateFin);
        
        // Appliquer la réduction si le client est un ancien client
        if (client instanceof AncienClient) {
            double tauxReduction = ((AncienClient) client).getTauxReduction();
            reservation.appliquerReduction(tauxReduction);
        }
        
        // Ajouter la réservation à la liste
        reservations.add(reservation);
        client.ajouterReservation(reservation);
        
        // Confirmer la réservation
        reservation.confirmer();
        
        return reservation;
    }
    
    @Override
    public boolean annulerReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            reservation.annuler();
            return true;
        }
        return false;
    }
    
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - " + nombreChambres + " chambres, " + 
               superficie + "m²" + (balcon ? " avec balcon" : "");
    }
}
