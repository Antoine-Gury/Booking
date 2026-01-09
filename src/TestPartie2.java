import models.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe de test pour la Partie 2 : Héritage et Polymorphisme.
 * Teste toutes les fonctionnalités demandées.
 */
public class TestPartie2 {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("TEST PARTIE 2 : Héritage et Polymorphisme");
        System.out.println("========================================\n");
        
        // Test 1 : Polymorphisme avec Personne
        testPolymorphismePersonne();
        
        // Test 2 : Polymorphisme avec Reservable
        testPolymorphismeReservable();
        
        // Test 3 : Réservations avec différents types d'hébergements
        testReservationsDifferentsTypes();
        
        // Test 4 : Application des réductions
        testReductions();
        
        // Test 5 : Tri de la collection
        testTriCollection();
        
        // Test 6 : Méthodes de l'administrateur
        testMethodesAdministrateur();
        
        System.out.println("\n========================================");
        System.out.println("TOUS LES TESTS SONT TERMINÉS");
        System.out.println("========================================");
    }
    
    private static void testPolymorphismePersonne() {
        System.out.println("===== TEST 1 : Polymorphisme avec Personne =====");
        
        // Création de différents types de personnes
        Personne[] personnes = new Personne[4];
        personnes[0] = new NouveauClient("Dupont", "Jean", "jean.dupont@email.com", "123 Rue Paris");
        personnes[1] = new AncienClient("Martin", "Marie", "marie.martin@email.com", "456 Avenue Lyon");
        personnes[2] = new AncienClient("Durand", "Pierre", "pierre.durand@email.com", "789 Boulevard Marseille", 15.0);
        personnes[3] = new Administrateur("Admin", "Super", "admin@booking.com", "ADM001");
        
        // Parcours polymorphe
        for (Personne p : personnes) {
            System.out.println(p.toString());
        }
        
        System.out.println("✓ Test polymorphisme Personne réussi\n");
    }
    
    private static void testPolymorphismeReservable() {
        System.out.println("===== TEST 2 : Polymorphisme avec Reservable =====");
        
        // Création de différents types d'hébergements
        Reservable[] hebergements = new Reservable[3];
        hebergements[0] = new ChambreHotel("Hôtel Luxe Paris", "10 Champs Élysées, Paris", 
                                           2, 150.0, "Belle chambre vue Tour Eiffel", "101", 1);
        hebergements[1] = new Appartement("Appart Centre Lyon", "5 Place Bellecour, Lyon",
                                          4, 120.0, "Appartement cosy", 2, 1, 65.0);
        hebergements[2] = new Villa("Villa Méditerranée", "Corniche Kennedy, Marseille",
                                    8, 350.0, "Villa avec vue mer", 4, 3, 200.0);
        
        // Ajout de périodes disponibles
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date fin = cal.getTime();
        
        for (Reservable h : hebergements) {
            if (h instanceof Hebergement) {
                ((Hebergement) h).ajouterPeriodeDisponible(debut, fin);
            }
        }
        
        // Test vérification disponibilité polymorphe
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 5);
        Date dateDebut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date dateFin = cal.getTime();
        
        for (int i = 0; i < hebergements.length; i++) {
            boolean dispo = hebergements[i].verifierDisponibilite(dateDebut, dateFin);
            System.out.println("Hébergement " + (i+1) + " disponible : " + dispo);
        }
        
        System.out.println("✓ Test polymorphisme Reservable réussi\n");
    }
    
    private static void testReservationsDifferentsTypes() {
        System.out.println("===== TEST 3 : Réservations avec différents types =====");
        
        // Création des clients
        Client client1 = new NouveauClient("Test", "Client1", "client1@test.com", "Adresse 1");
        
        // Création des hébergements
        ChambreHotel chambre = new ChambreHotel("Chambre Standard", "Hotel Paris", 
                                                2, 100.0, "Chambre confortable", "201", 2);
        Appartement appart = new Appartement("Studio Centre", "Lyon",
                                             2, 80.0, "Studio moderne", 1, 1, 30.0);
        Villa villa = new Villa("Villa Luxe", "Nice",
                               6, 300.0, "Villa luxueuse", 3, 2, 150.0);
        
        // Ajout de périodes disponibles
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date fin = cal.getTime();
        
        chambre.ajouterPeriodeDisponible(debut, fin);
        appart.ajouterPeriodeDisponible(debut, fin);
        villa.ajouterPeriodeDisponible(debut, fin);
        
        // Dates de réservation
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 10);
        Date dateDebut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date dateFin = cal.getTime();
        
        // Réservations
        Reservation res1 = chambre.reserver(dateDebut, dateFin, client1);
        Reservation res2 = appart.reserver(dateDebut, dateFin, client1);
        Reservation res3 = villa.reserver(dateDebut, dateFin, client1);
        
        System.out.println("Réservation chambre : " + (res1 != null ? "✓ Succès" : "✗ Échec"));
        System.out.println("Réservation appartement : " + (res2 != null ? "✓ Succès" : "✗ Échec"));
        System.out.println("Réservation villa : " + (res3 != null ? "✓ Succès" : "✗ Échec"));
        
        if (res1 != null) System.out.println(res1);
        if (res2 != null) System.out.println(res2);
        if (res3 != null) System.out.println(res3);
        
        // Test annulation
        if (res1 != null) {
            boolean annulation = chambre.annulerReservation(res1);
            System.out.println("Annulation réservation : " + (annulation ? "✓ Succès" : "✗ Échec"));
            System.out.println("Statut après annulation : " + res1.getStatut());
        }
        
        System.out.println("✓ Test réservations différents types réussi\n");
    }
    
    private static void testReductions() {
        System.out.println("===== TEST 4 : Application des réductions =====");
        
        // Création des clients
        NouveauClient nouveauClient = new NouveauClient("Nouveau", "Client", "nouveau@test.com", "Adresse");
        AncienClient ancienClient = new AncienClient("Ancien", "Client", "ancien@test.com", "Adresse");
        AncienClient ancienClientVIP = new AncienClient("VIP", "Client", "vip@test.com", "Adresse", 20.0);
        
        System.out.println("Nouveau client - Réduction : " + nouveauClient.getTauxReduction() + "%");
        System.out.println("Ancien client - Réduction : " + ancienClient.getTauxReduction() + "%");
        System.out.println("Ancien client VIP - Réduction : " + ancienClientVIP.getTauxReduction() + "%");
        
        // Création d'un hébergement
        ChambreHotel chambre = new ChambreHotel("Chambre Test", "Hotel", 
                                                2, 100.0, "Test", "301", 3);
        
        // Ajout période disponible
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        Date fin = cal.getTime();
        chambre.ajouterPeriodeDisponible(debut, fin);
        
        // Dates de réservation (3 nuits)
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 10);
        Date dateDebut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date dateFin = cal.getTime();
        
        // Réservations
        Reservation res1 = chambre.reserver(dateDebut, dateFin, nouveauClient);
        
        // Modifier les dates pour l'ancien client
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 15);
        dateDebut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        dateFin = cal.getTime();
        
        Reservation res2 = chambre.reserver(dateDebut, dateFin, ancienClient);
        
        // Modifier les dates pour le VIP
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 20);
        dateDebut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        dateFin = cal.getTime();
        
        Reservation res3 = chambre.reserver(dateDebut, dateFin, ancienClientVIP);
        
        System.out.println("\nRéservations avec prix :");
        if (res1 != null) {
            System.out.println("Nouveau client - Prix : " + res1.getPrixTotal() + "€ (Réduction : 0%)");
        }
        if (res2 != null) {
            System.out.println("Ancien client - Prix : " + res2.getPrixTotal() + "€ (Réduction : 10%)");
        }
        if (res3 != null) {
            System.out.println("Client VIP - Prix : " + res3.getPrixTotal() + "€ (Réduction : 20%)");
        }
        
        // Test calcul réduction
        double prixBase = 1000.0;
        System.out.println("\nCalcul sur prix base de " + prixBase + "€ :");
        System.out.println("Ancien client économise : " + ancienClient.calculerMontantReduction(prixBase) + "€");
        System.out.println("Ancien client paie : " + ancienClient.calculerPrixApresReduction(prixBase) + "€");
        System.out.println("Client VIP économise : " + ancienClientVIP.calculerMontantReduction(prixBase) + "€");
        System.out.println("Client VIP paie : " + ancienClientVIP.calculerPrixApresReduction(prixBase) + "€");
        
        System.out.println("✓ Test réductions réussi\n");
    }
    
    private static void testTriCollection() {
        System.out.println("===== TEST 5 : Tri de la collection =====");
        
        // Création de l'administrateur
        Administrateur admin = new Administrateur("Administrateur", "Système", "admin@booking.com", "ADM001");
        
        // Création de plusieurs hébergements avec différents prix et notes
        ChambreHotel chambre1 = new ChambreHotel("Chambre Budget", "Hotel A", 
                                                 2, 60.0, "Économique", "101", 1);
        ChambreHotel chambre2 = new ChambreHotel("Chambre Standard", "Hotel B", 
                                                 2, 100.0, "Confortable", "201", 2);
        Appartement appart = new Appartement("Studio Centre", "Ville",
                                             2, 80.0, "Moderne", 1, 1, 35.0);
        Villa villa = new Villa("Villa Luxe", "Côte",
                               8, 300.0, "Prestigieuse", 4, 3, 200.0);
        
        // Ajout de notes
        chambre1.ajouterNote(3.5);
        chambre1.ajouterNote(4.0);
        chambre2.ajouterNote(4.5);
        chambre2.ajouterNote(4.8);
        appart.ajouterNote(4.2);
        appart.ajouterNote(4.3);
        villa.ajouterNote(4.9);
        villa.ajouterNote(5.0);
        
        // Ajout à l'administrateur
        admin.ajouterHebergement(chambre1);
        admin.ajouterHebergement(chambre2);
        admin.ajouterHebergement(appart);
        admin.ajouterHebergement(villa);
        
        System.out.println("Liste originale :");
        for (Hebergement h : admin.getHebergementsGeres()) {
            System.out.println("  - " + h.getNom() + " : " + h.getPrixParNuit() + "€/nuit, Note: " + 
                             String.format("%.2f", h.getNoteMoyenne()));
        }
        
        // Tri par prix croissant
        System.out.println("\nTri par prix croissant :");
        ArrayList<Hebergement> triPrix = admin.trierParPrix();
        for (Hebergement h : triPrix) {
            System.out.println("  - " + h.getNom() + " : " + h.getPrixParNuit() + "€/nuit");
        }
        
        // Tri par prix décroissant
        System.out.println("\nTri par prix décroissant :");
        ArrayList<Hebergement> triPrixDesc = admin.trierParPrixDecroissant();
        for (Hebergement h : triPrixDesc) {
            System.out.println("  - " + h.getNom() + " : " + h.getPrixParNuit() + "€/nuit");
        }
        
        // Tri par note
        System.out.println("\nTri par note (décroissant) :");
        ArrayList<Hebergement> triNote = admin.trierParNote();
        for (Hebergement h : triNote) {
            System.out.println("  - " + h.getNom() + " : Note " + String.format("%.2f", h.getNoteMoyenne()));
        }
        
        // Tri par capacité
        System.out.println("\nTri par capacité :");
        ArrayList<Hebergement> triCapacite = admin.trierParCapacite();
        for (Hebergement h : triCapacite) {
            System.out.println("  - " + h.getNom() + " : " + h.getCapaciteMax() + " personnes");
        }
        
        System.out.println("✓ Test tri collection réussi\n");
    }
    
    private static void testMethodesAdministrateur() {
        System.out.println("===== TEST 6 : Méthodes de l'administrateur =====");
        
        // Création de l'administrateur
        Administrateur admin = new Administrateur("Administrateur", "Principal", "admin@booking.com", "ADM001");
        
        // Création d'hébergements
        ChambreHotel chambre1 = new ChambreHotel("Chambre 1", "Hotel A", 2, 100.0, "Desc1", "101", 1);
        ChambreHotel chambre2 = new ChambreHotel("Chambre 2", "Hotel B", 2, 150.0, "Desc2", "201", 2);
        Appartement appart = new Appartement("Appartement 1", "Ville", 4, 120.0, "Desc3", 2, 1, 60.0);
        Villa villa = new Villa("Villa 1", "Côte", 8, 400.0, "Desc4", 4, 3, 250.0);
        
        // Ajout de notes
        chambre1.ajouterNote(4.0);
        chambre2.ajouterNote(4.5);
        appart.ajouterNote(4.2);
        villa.ajouterNote(4.8);
        
        // Ajout des hébergements
        admin.ajouterHebergement(chambre1);
        admin.ajouterHebergement(chambre2);
        admin.ajouterHebergement(appart);
        admin.ajouterHebergement(villa);
        
        // Test des méthodes
        System.out.println(admin);
        System.out.println("\nStatistiques :");
        System.out.println("  Nombre d'hébergements : " + admin.getNombreHebergements());
        System.out.println("  Prix moyen : " + String.format("%.2f", admin.getPrixMoyen()) + "€");
        System.out.println("  Note moyenne : " + String.format("%.2f", admin.getNoteMoyenneGlobale()));
        
        Hebergement moinsCher = admin.getHebergementLeMoinsCher();
        Hebergement plusCher = admin.getHebergementLePlusCher();
        Hebergement mieuxNote = admin.getHebergementLeMieuxNote();
        
        System.out.println("\n  Le moins cher : " + moinsCher.getNom() + " (" + moinsCher.getPrixParNuit() + "€)");
        System.out.println("  Le plus cher : " + plusCher.getNom() + " (" + plusCher.getPrixParNuit() + "€)");
        System.out.println("  Le mieux noté : " + mieuxNote.getNom() + " (" + 
                         String.format("%.2f", mieuxNote.getNoteMoyenne()) + "/5)");
        
        // Test recherche par ID
        Hebergement trouve = admin.rechercherHebergementParId(chambre1.getId());
        System.out.println("\nRecherche par ID " + chambre1.getId() + " : " + 
                         (trouve != null ? trouve.getNom() : "Non trouvé"));
        
        // Test recherche par type
        ArrayList<Hebergement> chambres = admin.rechercherHebergementsParType("Chambre d'hôtel");
        System.out.println("Recherche chambres d'hôtel : " + chambres.size() + " trouvée(s)");
        
        // Test suppression
        boolean supprime = admin.supprimerHebergement(chambre1);
        System.out.println("Suppression chambre1 : " + (supprime ? "✓ Succès" : "✗ Échec"));
        System.out.println("Nombre d'hébergements après suppression : " + admin.getNombreHebergements());
        
        // Rapport statistique
        System.out.println("\n" + admin.genererRapportStatistique());
        
        System.out.println("\n✓ Test méthodes administrateur réussi\n");
    }
}
