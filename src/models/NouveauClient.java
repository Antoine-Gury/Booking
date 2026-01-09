package models;

/**
 * Classe NouveauClient.
 * Représente un nouveau client sans historique de réservations confirmées.
 * Pas de réduction applicable.
 */
public class NouveauClient extends Client {
    
    public NouveauClient(String nom, String prenom, String email, String adresse) {
        super(nom, prenom, email, adresse);
    }
    
    /**
     * Les nouveaux clients n'ont pas de réduction.
     * @return 0.0 (aucune réduction)
     */
    public double getTauxReduction() {
        return 0.0;
    }
    
    @Override
    public String getTypePersonne() {
        return "Nouveau Client";
    }
    
    @Override
    public String toString() {
        return getTypePersonne() + " : " + super.toString() + 
               " - Aucune réduction applicable";
    }
}
