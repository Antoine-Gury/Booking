package models;

/**
 * Classe abstraite représentant une personne.
 */
public abstract class Personne {
    
    protected String nom;
    protected String prenom;
    protected String email;
    
    public Personne(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    
    // Getters
    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Méthode abstraite : chaque type de personne doit définir son type
    public abstract String getTypePersonne();
    
    @Override
    public String toString() {
        return prenom + " " + nom + " (" + email + ")";
    }
}