\documentclass[12pt,a4paper]{article}
\usepackage[utf8]{inputenc}
\usepackage[french]{babel}
\usepackage{graphicx}
\usepackage{hyperref}
\usepackage{listings}
\usepackage{xcolor}
\usepackage{geometry}
\usepackage{float}

\geometry{margin=2.5cm}

\definecolor{codegreen}{rgb}{0,0.6,0}
\definecolor{codegray}{rgb}{0.5,0.5,0.5}
\definecolor{codepurple}{rgb}{0.58,0,0.82}
\definecolor{backcolour}{rgb}{0.95,0.95,0.92}

\lstdefinestyle{mystyle}{
backgroundcolor=\color{backcolour},   
commentstyle=\color{codegreen},
keywordstyle=\color{magenta},
numberstyle=\tiny\color{codegray},
stringstyle=\color{codepurple},
basicstyle=\ttfamily\footnotesize,
breakatwhitespace=false,         
breaklines=true,                 
captionpos=b,                    
keepspaces=true,                 
numbers=left,                    
numbersep=5pt,                  
showspaces=false,                
showstringspaces=false,
showtabs=false,                  
tabsize=2
}

\lstset{style=mystyle}

\title{\textbf{Rapport TP1: MathQuiz} \\ Application Android de Quiz Mathématique}
\author{Votre Nom \\ Groupe: GINF}
\date{\today}

\begin{document}

\maketitle
\tableofcontents
\newpage

\section{Introduction}

Ce document présente le développement de l'application Android \textbf{MathQuiz}, une application éducative permettant de pratiquer les opérations mathématiques de base (addition, soustraction, multiplication) avec des nombres aléatoires entre 111 et 999.

\subsection{Objectifs du projet}
\begin{itemize}
\item Créer une interface utilisateur intuitive et moderne
\item Implémenter la génération de nombres aléatoires
\item Gérer les événements utilisateur (clics sur boutons)
\item Calculer et afficher les résultats des opérations
\item Ajouter des fonctionnalités bonus (score, niveaux, historique)
\end{itemize}

\section{Fonctionnement de l'application}

\subsection{Description générale}

L'application MathQuiz génère deux nombres aléatoires entre 111 et 999 et propose à l'utilisateur de choisir parmi trois opérations mathématiques: addition (+), soustraction (-) ou multiplication (×). Le résultat est calculé et affiché immédiatement après le clic sur l'une des opérations.

\subsection{Fonctionnalités principales}

\begin{enumerate}
\item \textbf{Génération de nombres aléatoires:} Deux nombres sont générés aléatoirement dans la plage [111, 999] en utilisant la classe \texttt{Random()}.

    \item \textbf{Opérations mathématiques:} L'utilisateur peut effectuer trois types d'opérations sur les nombres générés.
    
    \item \textbf{Affichage du résultat:} Le résultat de l'opération sélectionnée s'affiche dans un TextView dédié.
    
    \item \textbf{Bouton Générer:} Permet de créer un nouvel exercice avec de nouveaux nombres aléatoires.
\end{enumerate}

\subsection{Fonctionnalités bonus implémentées}

\begin{itemize}
\item \textbf{Système de score:} Chaque opération rapporte 10 points. Le score est affiché en temps réel.

    \item \textbf{Niveaux de difficulté:} Trois niveaux sont disponibles (Facile, Moyen, Difficile) qui déterminent la plage des nombres générés.
    
    \item \textbf{Historique des opérations:} Les 10 dernières opérations effectuées sont enregistrées et affichées dans une zone dédiée.
    
    \item \textbf{Interface Material Design:} Utilisation de CardView, couleurs modernes et design épuré pour une meilleure expérience utilisateur.
    
    \item \textbf{Bouton de réinitialisation:} Permet de remettre le score et l'historique à zéro.
\end{itemize}

\section{Architecture technique}

\subsection{Structure des fichiers}

Le projet est organisé selon l'architecture standard Android:

\begin{itemize}
\item \textbf{res/values/strings.xml:} Contient toutes les chaînes de caractères de l'application
\item \textbf{res/values/colors.xml:} Définit la palette de couleurs Material Design
\item \textbf{res/values/dimens.xml:} Spécifie les dimensions (tailles de texte, marges, etc.)
\item \textbf{res/layout/activity\_main.xml:} Décrit l'interface utilisateur
\item \textbf{MainActivity.java:} Contient la logique métier de l'application
\end{itemize}

\subsection{Ressources XML}

\subsubsection{strings.xml}
Ce fichier centralise tous les textes de l'application, facilitant la maintenance et la traduction future. Il contient:
\begin{itemize}
\item Les labels des boutons et TextView
\item Les messages de feedback utilisateur
\item Les noms des niveaux de difficulté
\end{itemize}

\subsubsection{colors.xml}
Définit une palette de couleurs cohérente basée sur Material Design:
\begin{itemize}
\item Couleur primaire: Bleu (\#2196F3)
\item Couleur d'accent: Orange (\#FF5722)
\item Couleurs spécifiques pour chaque bouton d'opération
\item Couleurs pour les états (correct/incorrect)
\end{itemize}

\subsubsection{dimens.xml}
Standardise les dimensions pour assurer une interface cohérente:
\begin{itemize}
\item Tailles de texte: de 14sp à 72sp
\item Marges et paddings: de 8dp à 32dp
\item Dimensions des boutons et cartes
\end{itemize}

\subsection{Interface utilisateur (activity\_main.xml)}

L'interface utilise un \texttt{ScrollView} comme conteneur principal pour permettre le défilement sur les petits écrans. Elle est composée de:

\begin{enumerate}
\item \textbf{En-tête:} Titre de l'application et affichage du score
\item \textbf{Carte des nombres:} CardView contenant les deux nombres générés
\item \textbf{Sélecteur de difficulté:} RadioGroup avec trois RadioButtons
\item \textbf{Boutons d'opération:} Trois boutons colorés (+, -, ×)
\item \textbf{Carte du résultat:} CardView affichant le résultat calculé
\item \textbf{Bouton Générer:} Pour créer un nouvel exercice
\item \textbf{Boutons supplémentaires:} Réinitialisation et historique
\item \textbf{Zone d'historique:} ScrollView affichant les opérations précédentes
\end{enumerate}

\subsection{Logique Java (MainActivity.java)}

\subsubsection{Variables principales}
\begin{lstlisting}[language=Java]
private int number1, number2;  // Nombres generes
private int score = 0;         // Score du joueur
private Random random;         // Generateur aleatoire
private ArrayList<String> history;  // Historique
\end{lstlisting}

\subsubsection{Méthodes clés}

\begin{itemize}
\item \texttt{generateNumbers():} Génère deux nombres aléatoires selon le niveau de difficulté
\item \texttt{performOperation(char operation):} Calcule et affiche le résultat
\item \texttt{updateScore():} Met à jour l'affichage du score
\item \texttt{updateHistory():} Rafraîchit l'historique des opérations
\end{itemize}

\subsubsection{Génération de nombres aléatoires}

La méthode utilise \texttt{Random.nextInt()} pour générer des nombres dans la plage [111, 999]:

\begin{lstlisting}[language=Java]
number1 = random.nextInt(max - min + 1) + min;
number2 = random.nextInt(max - min + 1) + min;
\end{lstlisting}

\section{Captures d'écran}

\begin{figure}[H]
\centering
% Inserer votre capture d'ecran ici
% \includegraphics[width=0.6\textwidth]{screenshot.png}
\caption{Interface principale de MathQuiz}
\label{fig:screenshot}
\end{figure}

\textit{Note: Veuillez ajouter votre capture d'écran en décommentant la ligne \texttt{includegraphics} et en plaçant votre image dans le même dossier que ce fichier LaTeX.}

\section{Difficultés rencontrées}

\subsection{Gestion des vues}

\textbf{Problème:} Configuration initiale de CardView et Material Design.

\textbf{Solution:} Ajout de la dépendance \texttt{implementation 'androidx.cardview:cardview:1.0.0'} dans le fichier \texttt{build.gradle}.

\subsection{Couleurs des boutons}

\textbf{Problème:} Les boutons n'affichaient pas les couleurs personnalisées correctement.

\textbf{Solution:} Utilisation de l'attribut \texttt{android:background} au lieu de \texttt{android:backgroundTint} pour une compatibilité avec SDK 21.

\subsection{Historique des opérations}

\textbf{Problème:} Affichage et mise à jour dynamique de l'historique.

\textbf{Solution:} Utilisation d'une \texttt{ArrayList<String>} et mise à jour du TextView après chaque opération.

\subsection{Rotation de l'écran}

\textbf{Problème:} Perte des données lors de la rotation de l'écran.

\textbf{Solution:} Implémentation de \texttt{onSaveInstanceState()} et \texttt{onRestoreInstanceState()} pour sauvegarder et restaurer l'état.

\section{Améliorations possibles}

\subsection{Fonctionnalités additionnelles}
\begin{itemize}
\item \textbf{Mode chronomètre:} Ajouter un timer pour limiter le temps de réponse
\item \textbf{Statistiques détaillées:} Graphiques montrant les performances par type d'opération
\item \textbf{Sauvegarde persistante:} Utiliser SharedPreferences pour conserver le score entre les sessions
\item \textbf{Opération division:} Ajouter la division avec gestion des nombres décimaux
\item \textbf{Mode deux joueurs:} Permettre un mode compétitif
\end{itemize}

\subsection{Améliorations UI/UX}
\begin{itemize}
\item \textbf{Animations:} Ajouter des transitions animées lors des changements d'état
\item \textbf{Sons:} Feedback sonore pour les bonnes/mauvaises réponses
\item \textbf{Thèmes:} Mode sombre et mode clair
\item \textbf{Vibrations:} Retour haptique sur les interactions
\end{itemize}

\subsection{Optimisations techniques}
\begin{itemize}
\item \textbf{Architecture MVVM:} Séparer la logique métier de l'interface
\item \textbf{Base de données:} Utiliser Room pour stocker l'historique complet
\item \textbf{Tests unitaires:} Ajouter des tests pour valider les calculs
\end{itemize}

\section{Conclusion}

Le projet MathQuiz a permis de mettre en pratique les concepts fondamentaux du développement Android: manipulation des ressources XML, conception d'interface utilisateur, gestion d'événements et logique Java.

Les fonctionnalités bonus (système de score, niveaux de difficulté, historique et Material Design) enrichissent considérablement l'expérience utilisateur et démontrent une compréhension approfondie des capacités d'Android.

L'application est fonctionnelle, intuitive et respecte toutes les exigences du TP1, tout en offrant des possibilités d'évolution intéressantes.

\section{Annexe: Configuration requise}

\subsection{Environnement de développement}
\begin{itemize}
\item \textbf{IDE:} Android Studio (dernière version stable)
\item \textbf{SDK minimum:} API 21 (Android 5.0 Lollipop)
\item \textbf{SDK cible:} API 33 ou supérieur
\item \textbf{Langage:} Java 8
\end{itemize}

\subsection{Dépendances}
\begin{lstlisting}[language=XML]
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.cardview:cardview:1.0.0'
implementation 'com.google.android.material:material:1.9.0'
\end{lstlisting}

\end{document}