package models;

import java.util.Date;

/**
 * Interface Reservable.
 * Définit les méthodes que tout hébergement réservable doit implémenter.
 */
public interface Reservable {
    
    /**
     * Vérifie si l'hébergement est disponible pour une période donnée.
     * @param dateDebut Date de début du séjour
     * @param dateFin Date de fin du séjour
     * @return true si disponible, false sinon
     */
    boolean verifierDisponibilite(Date dateDebut, Date dateFin);
    
    /**
     * Réserve l'hébergement pour une période donnée.
     * Bloque les dates pour qu'elles ne soient plus disponibles.
     * @param dateDebut Date de début du séjour
     * @param dateFin Date de fin du séjour
     * @param client Le client qui fait la réservation
     * @return La réservation créée ou null si impossible
     */
    Reservation reserver(Date dateDebut, Date dateFin, Client client);
    
    /**
     * Annule une réservation et libère les dates.
     * @param reservation La réservation à annuler
     * @return true si l'annulation a réussi, false sinon
     */
    boolean annulerReservation(Reservation reservation);
}
