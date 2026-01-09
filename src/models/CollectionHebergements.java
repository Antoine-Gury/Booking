package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Classe pour gérer une collection d'hébergements avec recherche multicritères.
 */
public class CollectionHebergements {
    
    private List<Hebergement> hebergements;
    
    public CollectionHebergements() {
        this.hebergements = new ArrayList<>();
    }
    
    /**
     * Ajoute un hébergement à la collection.
     */
    public void ajouter(Hebergement hebergement) {
        hebergements.add(hebergement);
    }
    
    /**
     * Supprime un hébergement par son identifiant.
     */
    public boolean supprimer(String identifiant) {
        return hebergements.removeIf(h -> h.getIdentifiant().equals(identifiant));
    }
    
    /**
     * Recherche un hébergement par son identifiant.
     */
    public Hebergement rechercherParIdentifiant(String identifiant) {
        for (Hebergement h : hebergements) {
            if (h.getIdentifiant().equals(identifiant)) {
                return h;
            }
        }
        return null;
    }
    
    /**
     * Recherche des hébergements par prix maximum.
     */
    public List<Hebergement> rechercherParPrixMax(double prixMax) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.getPrixParNuit() <= prixMax) {
                resultats.add(h);
            }
        }
        return resultats;
    }
    
    /**
     * Recherche des hébergements par capacité minimale.
     */
    public List<Hebergement> rechercherParCapaciteMin(int capaciteMin) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.getCapaciteMax() >= capaciteMin) {
                resultats.add(h);
            }
        }
        return resultats;
    }
    
    /**
     * Recherche des hébergements par type.
     */
    public List<Hebergement> rechercherParType(String type) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.getType().equalsIgnoreCase(type)) {
                resultats.add(h);
            }
        }
        return resultats;
    }
    
    /**
     * Recherche des hébergements par note minimale.
     */
    public List<Hebergement> rechercherParNoteMin(double noteMin) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.getNoteMoyenne() >= noteMin) {
                resultats.add(h);
            }
        }
        return resultats;
    }
    
    /**
     * Recherche des hébergements disponibles pour une période.
     */
    public List<Hebergement> rechercherDisponibles(Date debut, Date fin) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.estDisponible(debut, fin)) {
                resultats.add(h);
            }
        }
        return resultats;
    }
    
    /**
     * Recherche multicritères complète.
     */
    public List<Hebergement> rechercherMulticriteres(Double prixMax, Integer capaciteMin, 
                                                     String type, Double noteMin, 
                                                     Date debut, Date fin) {
        List<Hebergement> resultats = new ArrayList<>(hebergements);
        
        // Filtrer par prix
        if (prixMax != null) {
            resultats.removeIf(h -> h.getPrixParNuit() > prixMax);
        }
        
        // Filtrer par capacité
        if (capaciteMin != null) {
            resultats.removeIf(h -> h.getCapaciteMax() < capaciteMin);
        }
        
        // Filtrer par type
        if (type != null && !type.isEmpty()) {
            resultats.removeIf(h -> !h.getType().equalsIgnoreCase(type));
        }
        
        // Filtrer par note
        if (noteMin != null) {
            resultats.removeIf(h -> h.getNoteMoyenne() < noteMin);
        }
        
        // Filtrer par disponibilité
        if (debut != null && fin != null) {
            resultats.removeIf(h -> !h.estDisponible(debut, fin));
        }
        
        return resultats;
    }
    
    /**
     * Trie les hébergements par prix (croissant).
     */
    public void trierParPrix() {
        Collections.sort(hebergements);
    }
    
    /**
     * Trie les hébergements par note (décroissant).
     */
    public void trierParNote() {
        Collections.sort(hebergements, new Comparator<Hebergement>() {
            @Override
            public int compare(Hebergement h1, Hebergement h2) {
                return Double.compare(h2.getNoteMoyenne(), h1.getNoteMoyenne());
            }
        });
    }
    
    /**
     * Trie les hébergements par capacité (décroissant).
     */
    public void trierParCapacite() {
        Collections.sort(hebergements, new Comparator<Hebergement>() {
            @Override
            public int compare(Hebergement h1, Hebergement h2) {
                return Integer.compare(h2.getCapaciteMax(), h1.getCapaciteMax());
            }
        });
    }
    
    /**
     * Affiche tous les hébergements de la collection.
     */
    public void afficherTous() {
        System.out.println("\n=== Liste des hébergements (" + hebergements.size() + ") ===");
        for (Hebergement h : hebergements) {
            System.out.println(h);
        }
    }
    
    /**
     * Affiche une liste d'hébergements.
     */
    public void afficherListe(List<Hebergement> liste) {
        System.out.println("\n=== Résultats de recherche (" + liste.size() + ") ===");
        if (liste.isEmpty()) {
            System.out.println("Aucun résultat trouvé.");
        } else {
            for (Hebergement h : liste) {
                System.out.println(h);
            }
        }
    }
    
    /**
     * Retourne le nombre d'hébergements.
     */
    public int getTaille() {
        return hebergements.size();
    }
    
    /**
     * Calcule le prix moyen des hébergements.
     */
    public double getPrixMoyen() {
        if (hebergements.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        for (Hebergement h : hebergements) {
            somme += h.getPrixParNuit();
        }
        return somme / hebergements.size();
    }
    
    /**
     * Calcule la note moyenne de tous les hébergements.
     */
    public double getNoteMoyenne() {
        if (hebergements.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        int count = 0;
        for (Hebergement h : hebergements) {
            if (h.getNombreAvis() > 0) {
                somme += h.getNoteMoyenne();
                count++;
            }
        }
        return count > 0 ? somme / count : 0.0;
    }
    
    /**
     * Retourne la liste des hébergements.
     */
    public List<Hebergement> getHebergements() {
        return hebergements;
    }
}
