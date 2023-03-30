package com.example.viikko_10;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class RegisterUserActivity extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private RadioGroup radioGroupDegreeProgram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        radioGroupDegreeProgram = findViewById(R.id.radioGroup);
    }

    public void addUserButtonClicked(View view) {
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString().trim(); // trim() to remove whitespace
        int selectedDegreeProgramId = radioGroupDegreeProgram.getCheckedRadioButtonId();
        RadioButton radioButtonDegreeProgram = findViewById(selectedDegreeProgramId);
        String degreeProgram = radioButtonDegreeProgram.getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || selectedDegreeProgramId == -1) {
            Toast.makeText(this, "Kaikkia kenttiä vaaditaan", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(firstName, lastName, email, degreeProgram);
        UserStorage.getInstance(getApplicationContext()).addUser(user);

        Toast.makeText(this, "Käyttäjä lisätty", Toast.LENGTH_SHORT).show();
        finish();
    }
}

