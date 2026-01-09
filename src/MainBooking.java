import models.*;
import java.util.Date;
import java.util.List;

/**
 * Programme de test pour la Partie 3 : Collections, Recherche et Finalisation
 * 
 * Ce programme démontre l'utilisation de CollectionHebergements avec :
 * - Recherche multicritères
 * - Tri des hébergements
 * - Gestion des statistiques
 * 
 * Note: Ce programme suppose que les classes des Parties 1 et 2 sont 
 * déjà implémentées par vos collègues.
 */
public class MainBooking {
    
    public static void main(String[] args) {
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        MINI-BOOKING - PARTIE 3 : COLLECTIONS ET RECHERCHE  ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Ce programme démontre les fonctionnalités de la Partie 3 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("⚠️  ATTENTION : Ce programme nécessite que les classes des");
        System.out.println("    Parties 1 et 2 soient implémentées :");
        System.out.println("    - Personne, Client, NouveauClient, AncienClient");
        System.out.println("    - Hebergement, ChambreHotel, Appartement, Villa");
        System.out.println("    - Reservation, Reservable, PeriodeDisponible\n");
        
        demoCollectionHebergements();
    }
    
    /**
     * Démontre les fonctionnalités de CollectionHebergements
     */
    private static void demoCollectionHebergements() {
        System.out.println("=".repeat(60));
        System.out.println("DÉMONSTRATION : CollectionHebergements");
        System.out.println("=".repeat(60) + "\n");
        
        // Créer une collection
        CollectionHebergements collection = new CollectionHebergements();
        System.out.println("✓ Collection créée\n");
        
        // Afficher les méthodes disponibles
        System.out.println("Méthodes disponibles dans CollectionHebergements :\n");
        
        System.out.println("🔍 RECHERCHE :");
        System.out.println("  • rechercherParPrixMax(double prixMax)");
        System.out.println("  • rechercherParCapaciteMin(int capaciteMin)");
        System.out.println("  • rechercherParType(String type)");
        System.out.println("  • rechercherParNoteMin(double noteMin)");
        System.out.println("  • rechercherDisponibles(Date debut, Date fin)");
        System.out.println("  • rechercherMulticriteres(prix, capacité, type, note, dates)\n");
        
        System.out.println("📊 TRI :");
        System.out.println("  • trierParPrix()");
        System.out.println("  • trierParNote()");
        System.out.println("  • trierParCapacite()\n");
        
        System.out.println("📈 STATISTIQUES :");
        System.out.println("  • getTaille()");
        System.out.println("  • getPrixMoyen()");
        System.out.println("  • getNoteMoyenne()\n");
        
        System.out.println("➕ GESTION :");
        System.out.println("  • ajouter(Hebergement h)");
        System.out.println("  • supprimer(String identifiant)");
        System.out.println("  • rechercherParIdentifiant(String id)");
        System.out.println("  • afficherTous()");
        System.out.println("  • afficherListe(List<Hebergement> liste)\n");
        
        System.out.println("=".repeat(60));
        System.out.println("ÉTAPES POUR TESTER :");
        System.out.println("=".repeat(60) + "\n");
        
        System.out.println("1. Créer des hébergements via les sous-classes");
        System.out.println("   (ChambreHotel, Appartement, Villa)");
        System.out.println("\n2. Les ajouter à la collection :");
        System.out.println("   collection.ajouter(chambreHotel1);");
        System.out.println("\n3. Effectuer des recherches :");
        System.out.println("   List<Hebergement> resultats = ");
        System.out.println("       collection.rechercherParPrixMax(150.0);");
        System.out.println("\n4. Trier les résultats :");
        System.out.println("   collection.trierParNote();");
        System.out.println("   collection.afficherTous();");
        System.out.println("\n5. Voir les statistiques :");
        System.out.println("   System.out.println(\"Prix moyen: \" + ");
        System.out.println("       collection.getPrixMoyen());");
        System.out.println();
    }
}
