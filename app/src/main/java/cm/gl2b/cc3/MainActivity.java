package cm.gl2b.cc3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Spinner difficultySpinner;
    private EditText guessInput;
    private Button submitButton;
    private TextView resultText;
    private Button showNumberButton;

    private int randomNumber;
    private int guessCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficultySpinner = (Spinner) findViewById(R.id.difficulty_spinner);
        guessInput = findViewById(R.id.guess_input);
        submitButton = findViewById(R.id.submit_button);
        resultText = findViewById(R.id.result_text);
        showNumberButton = findViewById(R.id.show_number_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });


        showNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumber();
            }
        });

        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setupGame(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupGame(int difficulty) {
        Random random = new Random();
        guessCount = 0;

        switch (difficulty) {
            case 0: // Facile (1 - 100)
                randomNumber = random.nextInt(100) + 1;
                break;
            case 1: // Normal (1 - 1000)
                randomNumber = random.nextInt(1000) + 1;
                break;
            case 2: // Difficile (1 - 10000)
                randomNumber = random.nextInt(10000) + 1;
                break;
        }

        resultText.setText("");
        showNumberButton.setVisibility(View.GONE);
    }

    private void checkGuess() {
        String guessString = guessInput.getText().toString();

        if (!guessString.isEmpty()) {
            int guess = Integer.parseInt(guessString);
            guessCount++;

            if (guess == randomNumber) {
                resultText.setText("Félicitations ! Vous avez trouvé le nombre en " + guessCount + " coups.");
                showNumberButton.setVisibility(View.VISIBLE);
            } else if (guess < randomNumber) {
                resultText.setText("Le nombre est plus grand.");
            } else {
                resultText.setText("Le nombre est plus petit.");
            }
        }
    }

    private void showNumber() {
        resultText.setText("Le nombre cherché était : " + randomNumber);
    }
}