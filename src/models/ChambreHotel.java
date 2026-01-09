package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe ChambreHotel.
 * Représente une chambre d'hôtel avec services spécifiques.
 */
public class ChambreHotel extends Hebergement implements Reservable {
    
    private String numeroChbre;
    private int etage;
    private boolean petitDejeunerInclus;
    private boolean serviceEtage;
    private ArrayList<Reservation> reservations;
    
    public ChambreHotel(String nom, String adresse, int capaciteMax, 
                       double prixParNuit, String description, 
                       String numeroChbre, int etage) {
        super(nom, adresse, "Chambre d'hôtel", capaciteMax, prixParNuit, description);
        this.numeroChbre = numeroChbre;
        this.etage = etage;
        this.petitDejeunerInclus = false;
        this.serviceEtage = false;
        this.reservations = new ArrayList<>();
    }
    
    // Getters et Setters spécifiques
    public String getNumeroChbre() {
        return numeroChbre;
    }
    
    public int getEtage() {
        return etage;
    }
    
    public boolean isPetitDejeunerInclus() {
        return petitDejeunerInclus;
    }
    
    public void setPetitDejeunerInclus(boolean petitDejeunerInclus) {
        this.petitDejeunerInclus = petitDejeunerInclus;
    }
    
    public boolean isServiceEtage() {
        return serviceEtage;
    }
    
    public void setServiceEtage(boolean serviceEtage) {
        this.serviceEtage = serviceEtage;
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
        return super.toString() + " - Chambre " + numeroChbre + ", Étage " + etage +
               (petitDejeunerInclus ? " (Petit-déjeuner inclus)" : "");
    }
}
