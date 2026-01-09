package models;

import java.util.Date;

/**
 * Classe Reservation.
 * Relie un client, un hébergement et une période.
 */
public class Reservation {
    
    private static int compteurId = 1;
    
    // Statuts possibles
    public static final String EN_ATTENTE = "EN_ATTENTE";
    public static final String CONFIRMEE = "CONFIRMEE";
    public static final String ANNULEE = "ANNULEE";
    
    private int id;
    private String statut;
    private Client client;
    private Hebergement hebergement;
    private Date dateArrivee;
    private Date dateDepart;
    private double prixTotal;
    private double tauxReduction;
    private Date dateCreation;
    
    public Reservation(Client client, Hebergement hebergement, 
                      Date dateArrivee, Date dateDepart) {
        this.id = compteurId++;
        this.statut = EN_ATTENTE;
        this.client = client;
        this.hebergement = hebergement;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.tauxReduction = 0.0;
        this.dateCreation = new Date();
        this.prixTotal = hebergement.calculerPrixTotal(dateArrivee, dateDepart);
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getStatut() {
        return statut;
    }
    
    public Client getClient() {
        return client;
    }
    
    public Hebergement getHebergement() {
        return hebergement;
    }
    
    public Date getDateArrivee() {
        return dateArrivee;
    }
    
    public Date getDateDepart() {
        return dateDepart;
    }
    
    public double getPrixTotal() {
        return prixTotal;
    }
    
    public double getTauxReduction() {
        return tauxReduction;
    }
    
    // Calculer le prix total (avec réduction si applicable)
    public void calculerPrixTotal() {
        double prixBase = hebergement.calculerPrixTotal(dateArrivee, dateDepart);
        if (tauxReduction > 0) {
            prixTotal = prixBase - (prixBase * tauxReduction / 100);
        } else {
            prixTotal = prixBase;
        }
    }
    
    // Appliquer une réduction en pourcentage
    public void appliquerReduction(double pourcentage) {
        this.tauxReduction = pourcentage;
        calculerPrixTotal();
    }
    
    // Vérifier si la réservation est en cours
    public boolean estEnCours() {
        if (!statut.equals(CONFIRMEE)) {
            return false;
        }
        Date maintenant = new Date();
        return !maintenant.before(dateArrivee) && !maintenant.after(dateDepart);
    }
    
    // Annuler la réservation
    public void annuler() {
        this.statut = ANNULEE;
    }
    
    public void confirmer() {
        this.statut = CONFIRMEE;
    }
    
    @Override
    public String toString() {
        return "Réservation #" + id + " - " + hebergement.getNom() + 
               " - " + statut + " - " + prixTotal + "€";
    }
}