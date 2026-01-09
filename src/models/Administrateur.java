package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe Administrateur.
 * Représente un administrateur de la plateforme avec des droits étendus.
 */
public class Administrateur extends Personne {
    
    private String matricule;
    private ArrayList<Hebergement> hebergementsGeres;
    
    public Administrateur(String nom, String prenom, String email, String matricule) {
        super(nom, prenom, email);
        this.matricule = matricule;
        this.hebergementsGeres = new ArrayList<>();
    }
    
    // Getters
    public String getMatricule() {
        return matricule;
    }
    
    public ArrayList<Hebergement> getHebergementsGeres() {
        return hebergementsGeres;
    }
    
    @Override
    public String getTypePersonne() {
        return "Administrateur";
    }
    
    // ===== Méthodes de gestion des hébergements =====
    
    /**
     * Ajoute un hébergement à la liste des hébergements gérés.
     * @param hebergement L'hébergement à ajouter
     */
    public void ajouterHebergement(Hebergement hebergement) {
        if (!hebergementsGeres.contains(hebergement)) {
            hebergementsGeres.add(hebergement);
        }
    }
    
    /**
     * Supprime un hébergement de la liste des hébergements gérés.
     * @param hebergement L'hébergement à supprimer
     * @return true si supprimé avec succès, false sinon
     */
    public boolean supprimerHebergement(Hebergement hebergement) {
        return hebergementsGeres.remove(hebergement);
    }
    
    /**
     * Recherche un hébergement par son ID.
     * @param id L'ID de l'hébergement recherché
     * @return L'hébergement trouvé ou null
     */
    public Hebergement rechercherHebergementParId(int id) {
        for (Hebergement h : hebergementsGeres) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }
    
    /**
     * Recherche des hébergements par type.
     * @param type Le type d'hébergement recherché
     * @return Liste des hébergements du type spécifié
     */
    public ArrayList<Hebergement> rechercherHebergementsParType(String type) {
        ArrayList<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergementsGeres) {
            if (h.getType().equalsIgnoreCase(type)) {
                resultats.add(h);
            }
        }
        return resultats;
    }
    
    // ===== Méthodes de tri =====
    
    /**
     * Trie les hébergements par prix croissant.
     * @return Liste triée par prix
     */
    public ArrayList<Hebergement> trierParPrix() {
        ArrayList<Hebergement> hebergementsTries = new ArrayList<>(hebergementsGeres);
        Collections.sort(hebergementsTries); // Utilise compareTo de Hebergement (tri par prix)
        return hebergementsTries;
    }
    
    /**
     * Trie les hébergements par prix décroissant.
     * @return Liste triée par prix décroissant
     */
    public ArrayList<Hebergement> trierParPrixDecroissant() {
        ArrayList<Hebergement> hebergementsTries = new ArrayList<>(hebergementsGeres);
        Collections.sort(hebergementsTries, Collections.reverseOrder());
        return hebergementsTries;
    }
    
    /**
     * Trie les hébergements par note moyenne décroissante.
     * @return Liste triée par note
     */
    public ArrayList<Hebergement> trierParNote() {
        ArrayList<Hebergement> hebergementsTries = new ArrayList<>(hebergementsGeres);
        Collections.sort(hebergementsTries, new Comparator<Hebergement>() {
            @Override
            public int compare(Hebergement h1, Hebergement h2) {
                // Tri décroissant (meilleures notes en premier)
                return Double.compare(h2.getNoteMoyenne(), h1.getNoteMoyenne());
            }
        });
        return hebergementsTries;
    }
    
    /**
     * Trie les hébergements par capacité maximale.
     * @return Liste triée par capacité
     */
    public ArrayList<Hebergement> trierParCapacite() {
        ArrayList<Hebergement> hebergementsTries = new ArrayList<>(hebergementsGeres);
        Collections.sort(hebergementsTries, new Comparator<Hebergement>() {
            @Override
            public int compare(Hebergement h1, Hebergement h2) {
                return Integer.compare(h1.getCapaciteMax(), h2.getCapaciteMax());
            }
        });
        return hebergementsTries;
    }
    
    // ===== Méthodes statistiques =====
    
    /**
     * Calcule le nombre total d'hébergements gérés.
     * @return Le nombre d'hébergements
     */
    public int getNombreHebergements() {
        return hebergementsGeres.size();
    }
    
    /**
     * Calcule le prix moyen par nuit de tous les hébergements.
     * @return Le prix moyen
     */
    public double getPrixMoyen() {
        if (hebergementsGeres.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        for (Hebergement h : hebergementsGeres) {
            somme += h.getPrixParNuit();
        }
        return somme / hebergementsGeres.size();
    }
    
    /**
     * Calcule la note moyenne de tous les hébergements.
     * @return La note moyenne globale
     */
    public double getNoteMoyenneGlobale() {
        if (hebergementsGeres.isEmpty()) {
            return 0.0;
        }
        double somme = 0;
        int nbNotations = 0;
        for (Hebergement h : hebergementsGeres) {
            if (h.getNoteMoyenne() > 0) {
                somme += h.getNoteMoyenne();
                nbNotations++;
            }
        }
        return nbNotations > 0 ? somme / nbNotations : 0.0;
    }
    
    /**
     * Trouve l'hébergement le moins cher.
     * @return L'hébergement le moins cher ou null
     */
    public Hebergement getHebergementLeMoinsCher() {
        if (hebergementsGeres.isEmpty()) {
            return null;
        }
        Hebergement leMoinsCher = hebergementsGeres.get(0);
        for (Hebergement h : hebergementsGeres) {
            if (h.getPrixParNuit() < leMoinsCher.getPrixParNuit()) {
                leMoinsCher = h;
            }
        }
        return leMoinsCher;
    }
    
    /**
     * Trouve l'hébergement le plus cher.
     * @return L'hébergement le plus cher ou null
     */
    public Hebergement getHebergementLePlusCher() {
        if (hebergementsGeres.isEmpty()) {
            return null;
        }
        Hebergement lePlusCher = hebergementsGeres.get(0);
        for (Hebergement h : hebergementsGeres) {
            if (h.getPrixParNuit() > lePlusCher.getPrixParNuit()) {
                lePlusCher = h;
            }
        }
        return lePlusCher;
    }
    
    /**
     * Trouve l'hébergement le mieux noté.
     * @return L'hébergement le mieux noté ou null
     */
    public Hebergement getHebergementLeMieuxNote() {
        if (hebergementsGeres.isEmpty()) {
            return null;
        }
        Hebergement leMieuxNote = hebergementsGeres.get(0);
        for (Hebergement h : hebergementsGeres) {
            if (h.getNoteMoyenne() > leMieuxNote.getNoteMoyenne()) {
                leMieuxNote = h;
            }
        }
        return leMieuxNote;
    }
    
    /**
     * Affiche un rapport statistique complet.
     * @return Un rapport formaté sous forme de chaîne
     */
    public String genererRapportStatistique() {
        StringBuilder rapport = new StringBuilder();
        rapport.append("===== RAPPORT STATISTIQUE =====\n");
        rapport.append("Administrateur : ").append(getPrenom()).append(" ").append(getNom()).append("\n");
        rapport.append("Matricule : ").append(matricule).append("\n\n");
        
        rapport.append("Nombre total d'hébergements : ").append(getNombreHebergements()).append("\n");
        rapport.append("Prix moyen par nuit : ").append(String.format("%.2f", getPrixMoyen())).append("€\n");
        rapport.append("Note moyenne globale : ").append(String.format("%.2f", getNoteMoyenneGlobale())).append("/5\n\n");
        
        Hebergement moinsCher = getHebergementLeMoinsCher();
        if (moinsCher != null) {
            rapport.append("Hébergement le moins cher : ").append(moinsCher.getNom())
                   .append(" (").append(moinsCher.getPrixParNuit()).append("€/nuit)\n");
        }
        
        Hebergement plusCher = getHebergementLePlusCher();
        if (plusCher != null) {
            rapport.append("Hébergement le plus cher : ").append(plusCher.getNom())
                   .append(" (").append(plusCher.getPrixParNuit()).append("€/nuit)\n");
        }
        
        Hebergement mieuxNote = getHebergementLeMieuxNote();
        if (mieuxNote != null && mieuxNote.getNoteMoyenne() > 0) {
            rapport.append("Hébergement le mieux noté : ").append(mieuxNote.getNom())
                   .append(" (").append(String.format("%.2f", mieuxNote.getNoteMoyenne())).append("/5)\n");
        }
        
        rapport.append("==============================");
        return rapport.toString();
    }
    
    @Override
    public String toString() {
        return getTypePersonne() + " : " + super.toString() + 
               " - Matricule : " + matricule + 
               " - Gère " + hebergementsGeres.size() + " hébergement(s)";
    }
}
