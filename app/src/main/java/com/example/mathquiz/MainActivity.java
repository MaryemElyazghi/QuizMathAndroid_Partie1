package com.example.mathquiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Déclaration des vues
    private TextView tvNumber1, tvNumber2, tvResult, tvScore, tvHistory;
    private Button btnAdd, btnSubtract, btnMultiply, btnGenerate, btnResetScore, btnShowHistory;
    private RadioGroup rgDifficulty;
    private RadioButton rbEasy, rbMedium, rbHard;

    // Variables pour la logique du jeu
    private int number1, number2;
    private int score = 0;
    private Random random;
    private ArrayList<String> history;

    // Constantes pour les niveaux de difficulté
    private static final int EASY_MIN = 111;
    private static final int EASY_MAX = 999;
    private static final int MEDIUM_MIN = 111;
    private static final int MEDIUM_MAX = 999;
    private static final int HARD_MIN = 111;
    private static final int HARD_MAX = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation
        initializeViews();
        random = new Random();
        history = new ArrayList<>();

        // Générer les premiers nombres
        generateNumbers();

        // Configuration des écouteurs d'événements
        setupListeners();

        // Mise à jour du score initial
        updateScore();
    }

    /**
     * Initialise toutes les vues de l'interface
     */
    private void initializeViews() {
        // TextViews
        tvNumber1 = findViewById(R.id.tvNumber1);
        tvNumber2 = findViewById(R.id.tvNumber2);
        tvResult = findViewById(R.id.tvResult);
        tvScore = findViewById(R.id.tvScore);
        tvHistory = findViewById(R.id.tvHistory);

        // Boutons d'opération
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnResetScore = findViewById(R.id.btnResetScore);
        btnShowHistory = findViewById(R.id.btnShowHistory);

        // RadioGroup et RadioButtons pour la difficulté
        rgDifficulty = findViewById(R.id.rgDifficulty);
        rbEasy = findViewById(R.id.rbEasy);
        rbMedium = findViewById(R.id.rbMedium);
        rbHard = findViewById(R.id.rbHard);
    }

    /**
     * Configure tous les écouteurs d'événements pour les boutons
     */
    private void setupListeners() {
        // Bouton Addition
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('+');
            }
        });

        // Bouton Soustraction
        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('-');
            }
        });

        // Bouton Multiplication
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOperation('×');
            }
        });

        // Bouton Générer
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateNumbers();
                tvResult.setText("?");
                tvResult.setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        // Bouton Réinitialiser le score
        btnResetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                history.clear();
                updateScore();
                updateHistory();
                Toast.makeText(MainActivity.this, "Score réinitialisé!", Toast.LENGTH_SHORT).show();
            }
        });

        // Bouton Afficher l'historique (déjà visible, mais on peut faire défiler)
        btnShowHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (history.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            getString(R.string.msg_empty_history),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Historique: " + history.size() + " opérations",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Écouteur pour le changement de niveau de difficulté
        rgDifficulty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                generateNumbers();
                tvResult.setText("?");
            }
        });
    }

    /**
     * Génère deux nombres aléatoires selon le niveau de difficulté sélectionné
     */
    private void generateNumbers() {
        int min = EASY_MIN;
        int max = EASY_MAX;

        // Déterminer la plage selon la difficulté
        int selectedId = rgDifficulty.getCheckedRadioButtonId();

        if (selectedId == R.id.rbEasy) {
            min = EASY_MIN;
            max = EASY_MAX;
        } else if (selectedId == R.id.rbMedium) {
            min = MEDIUM_MIN;
            max = MEDIUM_MAX;
        } else if (selectedId == R.id.rbHard) {
            min = HARD_MIN;
            max = HARD_MAX;
        }

        // Générer les deux nombres aléatoires entre min et max (inclus)
        number1 = random.nextInt(max - min + 1) + min;
        number2 = random.nextInt(max - min + 1) + min;

        // Afficher les nombres
        tvNumber1.setText(String.valueOf(number1));
        tvNumber2.setText(String.valueOf(number2));
    }

    /**
     * Effectue l'opération mathématique et affiche le résultat
     * @param operation Le caractère de l'opération (+, -, ×)
     */
    private void performOperation(char operation) {
        int result = 0;
        String operationSymbol = "";

        // Calculer le résultat selon l'opération
        switch (operation) {
            case '+':
                result = number1 + number2;
                operationSymbol = "+";
                break;
            case '-':
                result = number1 - number2;
                operationSymbol = "-";
                break;
            case '×':
                result = number1 * number2;
                operationSymbol = "×";
                break;
        }

        // Afficher le résultat
        tvResult.setText(String.valueOf(result));
        tvResult.setTextColor(getResources().getColor(R.color.correctColor));

        // Mettre à jour le score (bonus: +10 points par opération)
        score += 10;
        updateScore();

        // Ajouter à l'historique
        String historyEntry = number1 + " " + operationSymbol + " " + number2 + " = " + result;
        history.add(historyEntry);
        updateHistory();

        // Message de confirmation
        Toast.makeText(this, getString(R.string.msg_correct), Toast.LENGTH_SHORT).show();
    }

    /**
     * Met à jour l'affichage du score
     */
    private void updateScore() {
        tvScore.setText(String.format(getString(R.string.label_score), score));
    }

    /**
     * Met à jour l'affichage de l'historique
     */
    private void updateHistory() {
        if (history.isEmpty()) {
            tvHistory.setText(getString(R.string.msg_empty_history));
        } else {
            StringBuilder historyText = new StringBuilder();
            // Afficher les 10 dernières opérations
            int startIndex = Math.max(0, history.size() - 10);
            for (int i = startIndex; i < history.size(); i++) {
                historyText.append((i + 1)).append(". ").append(history.get(i)).append("\n");
            }
            tvHistory.setText(historyText.toString());
        }
    }

    /**
     * Sauvegarde l'état de l'application lors de la rotation de l'écran
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("number1", number1);
        outState.putInt("number2", number2);
        outState.putInt("score", score);
        outState.putStringArrayList("history", history);
    }

    /**
     * Restaure l'état de l'application après une rotation de l'écran
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        number1 = savedInstanceState.getInt("number1");
        number2 = savedInstanceState.getInt("number2");
        score = savedInstanceState.getInt("score");
        history = savedInstanceState.getStringArrayList("history");

        // Mettre à jour l'affichage
        tvNumber1.setText(String.valueOf(number1));
        tvNumber2.setText(String.valueOf(number2));
        updateScore();
        updateHistory();
    }
}