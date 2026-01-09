package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe de base Hebergement.
 * Représente un hébergement (chambre, appartement, villa...).
 */
public class Hebergement implements Comparable<Hebergement> {
    
    // Compteur pour générer des IDs uniques
    private static int compteurId = 1;
    
    // Attributs de base
    private int id;
    private String nom;
    private String adresse;
    private String type;
    private int capaciteMax;
    private double prixParNuit;
    private String description;
    
    // Équipements
    private boolean wifi;
    private boolean tv;
    private boolean cuisine;
    
    // Périodes disponibles (dates de début et fin)
    private ArrayList<PeriodeDisponible> periodesDisponibles;
    
    // Notes des clients
    private ArrayList<Double> notes;
    private double noteMoyenne;
    
    public Hebergement(String nom, String adresse, String type, int capaciteMax, 
                       double prixParNuit, String description) {
        this.id = compteurId++;
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
        this.capaciteMax = capaciteMax;
        this.prixParNuit = prixParNuit;
        this.description = description;
        this.periodesDisponibles = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.noteMoyenne = 0.0;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public String getType() {
        return type;
    }
    
    public int getCapaciteMax() {
        return capaciteMax;
    }
    
    public double getPrixParNuit() {
        return prixParNuit;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getNoteMoyenne() {
        return noteMoyenne;
    }
    
    // Setters pour équipements
    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
    
    public void setTv(boolean tv) {
        this.tv = tv;
    }
    
    public void setCuisine(boolean cuisine) {
        this.cuisine = cuisine;
    }
    
    // Ajouter une période disponible
    public void ajouterPeriodeDisponible(Date debut, Date fin) {
        periodesDisponibles.add(new PeriodeDisponible(debut, fin));
    }
    
    // Supprimer une période disponible
    public void supprimerPeriodeDisponible(Date debut, Date fin) {
        for (PeriodeDisponible p : periodesDisponibles) {
            if (p.debut.equals(debut) && p.fin.equals(fin)) {
                periodesDisponibles.remove(p);
                break;
            }
        }
    }
    
    // Vérifier si une période est libre
    public boolean estDisponible(Date debut, Date fin) {
        for (PeriodeDisponible p : periodesDisponibles) {
            // Vérifie si la période demandée est couverte par une période disponible
            if (!p.debut.after(debut) && !p.fin.before(fin)) {
                return true;
            }
        }
        return false;
    }
    
    // Calculer le prix total d'un séjour
    public double calculerPrixTotal(Date debut, Date fin) {
        long diff = fin.getTime() - debut.getTime();
        int nbNuits = (int) (diff / (1000 * 60 * 60 * 24));
        return nbNuits * prixParNuit;
    }
    
    // Ajouter une note et mettre à jour la moyenne
    public void ajouterNote(double note) {
        notes.add(note);
        // Recalculer la moyenne
        double somme = 0;
        for (double n : notes) {
            somme += n;
        }
        noteMoyenne = somme / notes.size();
    }
    
    // Tri par prix (pour Comparable)
    @Override
    public int compareTo(Hebergement autre) {
        return Double.compare(this.prixParNuit, autre.prixParNuit);
    }
    
    @Override
    public String toString() {
        return type + " - " + nom + " (" + capaciteMax + " pers.) - " + 
               prixParNuit + "€/nuit";
    }
    
    // Classe interne pour gérer les périodes
    private class PeriodeDisponible {
        Date debut;
        Date fin;
        
        PeriodeDisponible(Date debut, Date fin) {
            this.debut = debut;
            this.fin = fin;
        }
    }
}