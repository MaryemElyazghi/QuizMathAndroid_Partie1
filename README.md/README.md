# 🧮 MathQuiz - Application Android

Application éducative Android pour pratiquer les opérations mathématiques de base (addition, soustraction, multiplication).

## 📋 Table des matières
- [Fonctionnalités](#fonctionnalités)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Architecture](#architecture)
- [Problèmes corrigés](#problèmes-corrigés)
- [Captures d'écran](#captures-décran)

## ✨ Fonctionnalités

### Fonctionnalités principales
- ✅ Génération de nombres aléatoires
- ➕ Trois opérations mathématiques : Addition, Soustraction, Multiplication
- 🎯 Affichage instantané des résultats
- 🔄 Génération de nouveaux exercices

### Fonctionnalités bonus
- 🏆 **Système de score** : +10 points par opération
- 📊 **Trois niveaux de difficulté** :
    - **Facile** : nombres de 10 à 99
    - **Moyen** : nombres de 100 à 500
    - **Difficile** : nombres de 500 à 999
- 📝 **Historique** : affichage des 10 dernières opérations
- 🎨 **Interface Material Design** : design moderne et épuré
- 🔄 **Persistance de données** : sauvegarde lors de la rotation d'écran
- 🔴 **Réinitialisation** : bouton pour remettre le score et l'historique à zéro

## 🔧 Prérequis

- **Android Studio** : Arctic Fox ou supérieur
- **SDK minimum** : API 21 (Android 5.0 Lollipop)
- **SDK cible** : API 34 (Android 14)
- **JDK** : Java 8 ou supérieur
- **Gradle** : 8.13

## 📥 Installation

### Méthode 1 : Cloner le projet
```bash
git clone <votre-repo>
cd MathQuiz
```

### Méthode 2 : Ouvrir dans Android Studio
1. Ouvrir Android Studio
2. Cliquer sur "Open an Existing Project"
3. Sélectionner le dossier du projet
4. Attendre la synchronisation Gradle

### Exécution
1. Connecter un appareil Android ou lancer un émulateur
2. Cliquer sur le bouton "Run" (▶️) dans Android Studio
3. Sélectionner l'appareil cible

## 🎮 Utilisation

1. **Lancer l'application** : L'app génère automatiquement deux nombres
2. **Choisir le niveau** : Sélectionner Facile, Moyen ou Difficile
3. **Effectuer une opération** : Cliquer sur +, - ou × pour voir le résultat
4. **Gagner des points** : Chaque opération rapporte 10 points
5. **Générer de nouveaux nombres** : Cliquer sur le bouton "Générer"
6. **Consulter l'historique** : Faire défiler la zone en bas de l'écran
7. **Réinitialiser** : Cliquer sur "Réinitialiser" pour remettre à zéro

## 🏗️ Architecture

```
app/src/main/
├── java/com/example/mathquiz/
│   └── MainActivity.java          # Logique principale
├── res/
│   ├── layout/
│   │   └── activity_main.xml      # Interface utilisateur
│   ├── values/
│   │   ├── strings.xml            # Textes de l'application
│   │   ├── colors.xml             # Palette de couleurs
│   │   ├── dimens.xml             # Dimensions
│   │   └── themes.xml             # Thème de l'application
│   └── drawable/                  # Icônes et ressources graphiques
└── AndroidManifest.xml            # Configuration de l'application
```

### Composants clés

#### MainActivity.java
- Gestion des vues et événements
- Génération de nombres aléatoires avec `Random()`
- Calcul des opérations mathématiques
- Gestion du score et de l'historique
- Sauvegarde/restauration de l'état

#### Ressources XML
- **strings.xml** : Centralisation des textes (internationalisation)
- **colors.xml** : Palette Material Design cohérente
- **dimens.xml** : Standardisation des dimensions
- **activity_main.xml** : Layout avec ScrollView et CardViews

## 🐛 Problèmes corrigés

### 1. SDK incompatible ❌➡️✅
**Problème** : `compileSdk = 21` (trop ancien)
```gradle
// Avant
compileSdk = 21
targetSdk = 21
```
**Solution** : Mise à jour vers SDK 34
```gradle
// Après
compileSdk = 34
targetSdk = 34
```

### 2. Niveaux de difficulté identiques ❌➡️✅
**Problème** : Tous les niveaux utilisaient [111, 999]
```java
// Avant
private static final int EASY_MIN = 111;
private static final int EASY_MAX = 999;
private static final int MEDIUM_MIN = 111;
private static final int MEDIUM_MAX = 999;
private static final int HARD_MIN = 111;
private static final int HARD_MAX = 999;
```
**Solution** : Plages différenciées
```java
// Après
private static final int EASY_MIN = 10;
private static final int EASY_MAX = 99;
private static final int MEDIUM_MIN = 100;
private static final int MEDIUM_MAX = 500;
private static final int HARD_MIN = 500;
private static final int HARD_MAX = 999;
```

### 3. Thème incompatible ❌➡️✅
**Problème** : `Theme.Material` nécessite API 21+
```xml
<!-- Avant -->
<style name="Theme.MathQuiz" parent="android:Theme.Material.Light.NoActionBar" />
```
**Solution** : Utilisation de AppCompat
```xml
<!-- Après -->
<style name="Theme.MathQuiz" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>
```

### 4. Méthode getColor() dépréciée ❌➡️✅
**Problème** : `getResources().getColor()` déprécié
```java
// Avant
tvResult.setTextColor(getResources().getColor(R.color.colorAccent));
```
**Solution** : Utilisation de ContextCompat
```java
// Après
tvResult.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
```

## 📸 Captures d'écran

```
┌─────────────────────────┐
│   Quiz Mathématique     │
│   Score: 50       🏆    │
├─────────────────────────┤
│       Nombre 1          │
│         456             │
│       Nombre 2          │
│         789             │
├─────────────────────────┤
│ Niveau                  │
│ ○ Facile ● Moyen ○ Difficile
├─────────────────────────┤
│  [+]    [-]    [×]     │
├─────────────────────────┤
│      Résultat           │
│        1245             │
├─────────────────────────┤
│     [Générer]           │
│ [Réinitialiser] [Historique]
├─────────────────────────┤
│ Historique:             │
│ 1. 456 + 789 = 1245    │
│ 2. 123 - 45 = 78       │
│ 3. 12 × 34 = 408       │
└─────────────────────────┘
```

## 🚀 Améliorations futures

- ⏱️ **Mode chronomètre** : Temps limité pour répondre
- 📈 **Statistiques** : Graphiques de performance
- 💾 **Sauvegarde persistante** : SharedPreferences
- ➗ **Division** : Ajout d'une quatrième opération
- 👥 **Mode multijoueur** : Compétition entre deux joueurs
- 🎵 **Sons et animations** : Feedback audio/visuel
- 🌙 **Mode sombre** : Thème alternatif
- 🌐 **Multilingue** : Support de plusieurs langues

## 📄 Licence

Ce projet est un exercice éducatif développé dans le cadre du cours de développement Android.

## 👨‍💻 Auteur

**Votre Nom** - Groupe GINF

---

**Note** : Pour plus de détails techniques, consultez le rapport LaTeX fourni avec le projet.