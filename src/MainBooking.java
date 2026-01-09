/**
 * Programme de démonstration pour la Partie 3 : Collections et Recherche
 * 
 * Ce programme affiche les fonctionnalités de CollectionHebergements
 * et s'exécute sans dépendre des Parties 1-2.
 */
public class MainBooking {
    
    public static void main(String[] args) {
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        MINI-BOOKING - PARTIE 3 : COLLECTIONS ET RECHERCHE  ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Démonstration des fonctionnalités disponibles             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        demoCollectionHebergements();
    }
    
    /**
     * Affiche les fonctionnalités de CollectionHebergements
     */
    private static void demoCollectionHebergements() {
        System.out.println("=".repeat(60));
        System.out.println("CLASSE : CollectionHebergements");
        System.out.println("=".repeat(60) + "\n");
        
        System.out.println("Cette classe gère une collection d'hébergements avec :\n");
        
        System.out.println("🔍 MÉTHODES DE RECHERCHE :");
        System.out.println("  • rechercherParPrixMax(double prixMax)");
        System.out.println("  • rechercherParCapaciteMin(int capaciteMin)");
        System.out.println("  • rechercherParType(String type)");
        System.out.println("  • rechercherParNoteMin(double noteMin)");
        System.out.println("  • rechercherParPrixMax(double prixMax)");
        System.out.println("  • rechercherParCapaciteMin(int capaciteMin)");
        System.out.println("  • rechercherParType(String type)");
        System.out.println("  • rechercherParNoteMin(double noteMin)");
        System.out.println("  • rechercherDisponibles(Date debut, Date fin)");
        System.out.println("  • rechercherMulticriteres(...)\n");
        
        System.out.println("📊 TRI :");
        System.out.println("  • trierParPrix() - Ordre croissant");
        System.out.println("  • trierParNote() - Ordre décroissant");
        System.out.println("  • trierParCapacite() - Ordre décroissant\n");
        
        System.out.println("📈 STATISTIQUES :");
        System.out.println("  • getTaille() - Nombre d'hébergements");
        System.out.println("  • getPrixMoyen() - Prix moyen");
        System.out.println("  • getNoteMoyenne() - Note moyenne\n");
        
        System.out.println("➕ GESTION :");
        System.out.println("  • ajouter(Hebergement h) - Ajouter à la collection");
        System.out.println("  • supprimer(String identifiant) - Supprimer par ID");
        System.out.println("  • rechercherParIdentifiant(String id) - Chercher par ID");
        System.out.println("  • afficherTous() - Afficher tous");
        System.out.println("  • afficherListe(List<Hebergement> liste) - Afficher une liste\n");
        
        System.out.println("=".repeat(60));
        System.out.println("UTILISATION :");
        System.out.println("=".repeat(60) + "\n");
        
        System.out.println("// Créer une collection");
        System.out.println("CollectionHebergements collection = new CollectionHebergements();\n");
        
        System.out.println("// Ajouter des hébergements");
        System.out.println("collection.ajouter(hebergement1);");
        System.out.println("collection.ajouter(hebergement2);\n");
        
        System.out.println("// Recherche multicritères");
        System.out.println("List<Hebergement> resultats = collection.rechercherMulticriteres(");
        System.out.println("    200.0,      // Prix max");
        System.out.println("    2,          // Capacité min");
        System.out.println("    \"Villa\",   // Type");
        System.out.println("    4.0,        // Note min");
        System.out.println("    debut, fin  // Dates");
        System.out.println(");\n");
        
        System.out.println("// Trier et afficher");
        System.out.println("collection.trierParNote();");
        System.out.println("collection.afficherTous();\n");
        
        System.out.println("// Statistiques");
        System.out.println("System.out.println(\"Nombre: \" + collection.getTaille());");
        System.out.println("System.out.println(\"Prix moyen: \" + collection.getPrixMoyen());");
        System.out.println("System.out.println(\"Note moyenne: \" + collection.getNoteMoyenne());\n");
        
        System.out.println("=".repeat(60));
        System.out.println("⏭️  Prêt pour tester avec les classes des Parties 1-2 !");
        System.out.println("=".repeat(60));
    }
}

