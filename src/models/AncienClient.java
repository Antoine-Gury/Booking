package models;

/**
 * Classe AncienClient.
 * Représente un client avec un historique de réservations confirmées.
 * Bénéficie d'une réduction de 10% sur toutes les réservations.
 */
public class AncienClient extends Client {
    
    private static final double TAUX_REDUCTION_DEFAULT = 10.0;
    private double tauxReduction;
    
    public AncienClient(String nom, String prenom, String email, String adresse) {
        super(nom, prenom, email, adresse);
        this.tauxReduction = TAUX_REDUCTION_DEFAULT;
    }
    
    /**
     * Constructeur avec taux de réduction personnalisé.
     * @param nom Nom du client
     * @param prenom Prénom du client
     * @param email Email du client
     * @param adresse Adresse du client
     * @param tauxReduction Taux de réduction personnalisé (en %)
     */
    public AncienClient(String nom, String prenom, String email, String adresse, double tauxReduction) {
        super(nom, prenom, email, adresse);
        this.tauxReduction = tauxReduction;
    }
    
    /**
     * Récupère le taux de réduction applicable.
     * @return Le taux de réduction en pourcentage
     */
    public double getTauxReduction() {
        return tauxReduction;
    }
    
    /**
     * Modifie le taux de réduction (par exemple pour fidélité accrue).
     * @param tauxReduction Nouveau taux de réduction
     */
    public void setTauxReduction(double tauxReduction) {
        if (tauxReduction >= 0 && tauxReduction <= 100) {
            this.tauxReduction = tauxReduction;
        }
    }
    
    /**
     * Calcule le montant de la réduction pour un prix donné.
     * @param prixBase Prix de base avant réduction
     * @return Montant de la réduction
     */
    public double calculerMontantReduction(double prixBase) {
        return prixBase * tauxReduction / 100;
    }
    
    /**
     * Calcule le prix final après application de la réduction.
     * @param prixBase Prix de base avant réduction
     * @return Prix final après réduction
     */
    public double calculerPrixApresReduction(double prixBase) {
        return prixBase - calculerMontantReduction(prixBase);
    }
    
    @Override
    public String getTypePersonne() {
        return "Ancien Client";
    }
    
    @Override
    public String toString() {
        return getTypePersonne() + " : " + super.toString() + 
               " - Réduction : " + tauxReduction + "%";
    }
}
