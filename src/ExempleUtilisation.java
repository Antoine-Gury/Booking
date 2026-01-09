import models.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Exemple d'utilisation simple des fonctionnalités de la Partie 2.
 * Démontre les cas d'usage principaux.
 */
public class ExempleUtilisation {
    
    public static void main(String[] args) {
        System.out.println("=== DÉMONSTRATION SYSTÈME DE RÉSERVATION ===\n");
        
        // 1. Création de l'administrateur
        Administrateur admin = new Administrateur("Dupont", "Jean", "admin@booking.com", "ADM001");
        System.out.println("✓ Administrateur créé : " + admin.getPrenom() + " " + admin.getNom());
        
        // 2. Création des hébergements
        ChambreHotel chambre = new ChambreHotel(
            "Suite Prestige", 
            "Hôtel Royal, 10 Champs Élysées, Paris", 
            2, 200.0, 
            "Suite luxueuse avec vue sur la Tour Eiffel", 
            "501", 5
        );
        chambre.setPetitDejeunerInclus(true);
        chambre.setServiceEtage(true);
        
        Appartement appart = new Appartement(
            "Studio Cosy Centre Ville", 
            "5 Place Bellecour, Lyon", 
            4, 120.0,
            "Appartement moderne avec cuisine équipée",
            2, 1, 65.0
        );
        appart.setBalcon(true);
        appart.setParking(true);
        
        Villa villa = new Villa(
            "Villa Méditerranée", 
            "Corniche Kennedy, Marseille",
            8, 500.0,
            "Villa de luxe avec vue mer panoramique",
            4, 3, 250.0
        );
        villa.setPiscine(true);
        villa.setJardin(true);
        villa.setSuperficieJardin(500.0);
        
        System.out.println("✓ Hébergements créés");
        
        // 3. Ajout des notes
        chambre.ajouterNote(4.8);
        chambre.ajouterNote(4.9);
        appart.ajouterNote(4.5);
        appart.ajouterNote(4.6);
        villa.ajouterNote(5.0);
        villa.ajouterNote(4.9);
        
        // 4. Ajout des périodes disponibles
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.MONTH, 3);
        Date fin = cal.getTime();
        
        chambre.ajouterPeriodeDisponible(debut, fin);
        appart.ajouterPeriodeDisponible(debut, fin);
        villa.ajouterPeriodeDisponible(debut, fin);
        
        System.out.println("✓ Périodes de disponibilité configurées");
        
        // 5. Ajout des hébergements à l'administrateur
        admin.ajouterHebergement(chambre);
        admin.ajouterHebergement(appart);
        admin.ajouterHebergement(villa);
        
        System.out.println("✓ Hébergements ajoutés à l'administrateur\n");
        
        // 6. Création des clients
        NouveauClient clientNouveau = new NouveauClient(
            "Martin", "Sophie", 
            "sophie.martin@email.com", 
            "15 Rue de la Paix, Paris"
        );
        
        AncienClient clientFidele = new AncienClient(
            "Durand", "Pierre", 
            "pierre.durand@email.com", 
            "42 Avenue Victor Hugo, Lyon"
        );
        
        AncienClient clientVIP = new AncienClient(
            "Leroy", "Marie", 
            "marie.leroy@email.com", 
            "88 Boulevard de la Croisette, Nice",
            20.0 // Réduction VIP de 20%
        );
        
        System.out.println("📋 CLIENTS CRÉÉS :");
        System.out.println("  " + clientNouveau);
        System.out.println("  " + clientFidele);
        System.out.println("  " + clientVIP);
        System.out.println();
        
        // 7. Affichage des hébergements disponibles
        System.out.println("🏨 HÉBERGEMENTS DISPONIBLES :");
        for (Hebergement h : admin.getHebergementsGeres()) {
            System.out.println("  " + h);
            System.out.println("     Note : " + String.format("%.1f", h.getNoteMoyenne()) + "/5");
        }
        System.out.println();
        
        // 8. Réservations
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date dateArrivee = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 5); // Séjour de 5 nuits
        Date dateDepart = cal.getTime();
        
        System.out.println("📅 RÉSERVATIONS EN COURS :\n");
        
        // Réservation 1 : Nouveau client -> Chambre
        System.out.println("1. " + clientNouveau.getPrenom() + " réserve une chambre d'hôtel");
        Reservation res1 = chambre.reserver(dateArrivee, dateDepart, clientNouveau);
        if (res1 != null) {
            System.out.println("   ✓ Réservation confirmée");
            System.out.println("   Prix : " + res1.getPrixTotal() + "€ (5 nuits × 200€)");
            System.out.println("   Réduction : 0% (nouveau client)");
        }
        System.out.println();
        
        // Réservation 2 : Client fidèle -> Appartement
        System.out.println("2. " + clientFidele.getPrenom() + " réserve un appartement");
        Reservation res2 = appart.reserver(dateArrivee, dateDepart, clientFidele);
        if (res2 != null) {
            System.out.println("   ✓ Réservation confirmée");
            System.out.println("   Prix de base : " + (120.0 * 5) + "€");
            System.out.println("   Réduction : 10% (ancien client) = -" + 
                             clientFidele.calculerMontantReduction(120.0 * 5) + "€");
            System.out.println("   Prix final : " + res2.getPrixTotal() + "€");
        }
        System.out.println();
        
        // Réservation 3 : Client VIP -> Villa
        System.out.println("3. " + clientVIP.getPrenom() + " réserve une villa");
        Reservation res3 = villa.reserver(dateArrivee, dateDepart, clientVIP);
        if (res3 != null) {
            System.out.println("   ✓ Réservation confirmée");
            System.out.println("   Prix de base : " + (500.0 * 5) + "€");
            System.out.println("   Réduction : 20% (client VIP) = -" + 
                             clientVIP.calculerMontantReduction(500.0 * 5) + "€");
            System.out.println("   Prix final : " + res3.getPrixTotal() + "€");
        }
        System.out.println();
        
        // 9. Tri des hébergements
        System.out.println("📊 CLASSEMENTS :\n");
        
        System.out.println("Par prix croissant :");
        for (Hebergement h : admin.trierParPrix()) {
            System.out.println("  " + h.getNom() + " - " + h.getPrixParNuit() + "€/nuit");
        }
        System.out.println();
        
        System.out.println("Par note (meilleurs d'abord) :");
        for (Hebergement h : admin.trierParNote()) {
            System.out.println("  " + h.getNom() + " - " + 
                             String.format("%.1f", h.getNoteMoyenne()) + "/5");
        }
        System.out.println();
        
        // 10. Statistiques de l'administrateur
        System.out.println(admin.genererRapportStatistique());
        System.out.println();
        
        // 11. Démonstration annulation
        System.out.println("❌ ANNULATION DE RÉSERVATION :\n");
        if (res1 != null) {
            System.out.println("Annulation de la réservation de " + clientNouveau.getPrenom());
            System.out.println("Statut avant : " + res1.getStatut());
            chambre.annulerReservation(res1);
            System.out.println("Statut après : " + res1.getStatut());
            System.out.println("✓ La chambre est maintenant disponible à nouveau");
        }
        
        System.out.println("\n=== FIN DE LA DÉMONSTRATION ===");
    }
}
