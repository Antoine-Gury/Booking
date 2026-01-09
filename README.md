# Mini-Booking - Partie 3 : Collections et Finalisation

```
╔══════════════════════════════════════════════════════════════╗
║                                                              ║
║         MINI-BOOKING - PARTIE 3 (30% du projet)             ║
║              Collections et Recherche Multicritères          ║
║                                                              ║
║   ✅ Classe CollectionHebergements                          ║
║   ✅ Recherche multicritères                                ║
║   ✅ Tri flexible                                           ║
║   ✅ Statistiques complètes                                 ║
║                                                              ║
╚══════════════════════════════════════════════════════════════╝
```

> 📚 **Navigation rapide :** Consultez [INDEX.md](INDEX.md) pour la documentation complète

## 📋 Description

Cette **Partie 3** fournit la classe `CollectionHebergements` pour la gestion d'une collection dynamique d'hébergements avec recherche multicritères et tri flexible. Les Classes des Parties 1 et 2 sont implémentées par vos collègues.

## 🎯 Fonctionnalités de la Partie 3

- ✅ **Recherche multicritères** : Par prix, capacité, type, note, dates disponibles
- ✅ **Tri flexible** : Par prix (croissant), note (décroissant), capacité (décroissant)
- ✅ **Statistiques** : Taille, prix moyen, note moyenne
- ✅ **Gestion CRUD** : Ajouter, supprimer, rechercher des hébergements
- ✅ **Affichage formaté** : Afficher tous les hébergements ou une liste filtrée

## 🏗️ Architecture Partie 3

```
src/
├── MainBooking.java           # Programme d'exemple
├── MainTest.java              # Tests Parties 1-2
└── models/
    ├── Personne.java          # Classe abstraite (existante)
    └── CollectionHebergements.java  # ✅ PARTIE 3
```

## 🚀 Compilation et exécution

### Prérequis
- Java JDK 8 ou supérieur
- Un terminal ou IDE Java (Eclipse, IntelliJ, VS Code)

### Compilation

```bash
# Compilation de tous les fichiers
javac -d bin src/**/*.java src/*.java

# Ou avec encodage spécifique si nécessaire
javac -encoding UTF-8 -d bin src/**/*.java src/*.java
```

### Exécution

```bash
# Exécution du programme principal
java -cp bin MainBooking

# Exécution des tests de la Partie 1
java -cp bin MainTest
```

## 📊 Classe : CollectionHebergements

### Recherches simples

```java
List<Hebergement> rechercherParPrixMax(double prixMax)
List<Hebergement> rechercherParCapaciteMin(int capaciteMin)
List<Hebergement> rechercherParType(String type)
List<Hebergement> rechercherParNoteMin(double noteMin)
List<Hebergement> rechercherDisponibles(Date debut, Date fin)
```

### Recherche multicritères

```java
List<Hebergement> rechercherMulticriteres(
    Double prixMax,           // Null = ignorer
    Integer capaciteMin,      // Null = ignorer
    String type,              // Null ou "" = ignorer
    Double noteMin,           // Null = ignorer
    Date debut, Date fin      // Null = ignorer
)
```

### Tris

```java
void trierParPrix()       // Ordre croissant
void trierParNote()       // Ordre décroissant
void trierParCapacite()   // Ordre décroissant
```

### Gestion et affichage

```java
void ajouter(Hebergement h)
boolean supprimer(String identifiant)
void afficherTous()
void afficherListe(List<Hebergement> liste)
int getTaille()
double getPrixMoyen()
double getNoteMoyenne()
```

## 💡 Exemple d'utilisation

```java
// Créer une collection
CollectionHebergements collection = new CollectionHebergements();

// Ajouter des hébergements (supposant que Hebergement existe)
collection.ajouter(nouvelHebergement);

// Recherche multicritères
List<Hebergement> resultats = collection.rechercherMulticriteres(
    200.0,    // Prix max
    2,        // Capacité min
    "Villa",  // Type
    4.0,      // Note min
    debut, fin // Dates
);

// Tri et affichage
collection.trierParNote();
collection.afficherTous();

// Statistiques
System.out.println("Nombre: " + collection.getTaille());
System.out.println("Prix moyen: " + collection.getPrixMoyen());
```

## ⚙️ Dépendances

La classe `CollectionHebergements` nécessite que vos collègues implémentent :
- `Hebergement` - Classe de base
- `ChambreHotel`, `Appartement`, `Villa` - Sous-classes spécialisées
- Attributs : `identifiant`, `prix`, `capacite`, `type`, `note`
- Interface `Comparable<Hebergement>` pour le tri

## 👥 Collaboration

**Partie 3 (cette implémentation) :**
- ✅ `CollectionHebergements.java` - Gestion de collection complète

**Parties 1 et 2 (à implémenter par vos collègues) :**
- Classes utilisateurs : `Client`, `NouveauClient`, `AncienClient`, `Administrateur`
- Classes hébergements : `Hebergement`, `ChambreHotel`, `Appartement`, `Villa`
- Classes métier : `Reservation`, `Reservable`, `PeriodeDisponible`, `DateUtils`

## 📝 Licence

Projet éducatif - Tous droits réservés

---

**Mini-Booking - Partie 3**  
*Janvier 2026*