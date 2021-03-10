package com.example.firebaseexerciseusers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.firebaseexerciseusers.pojo.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Spinner spinnerSex;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editTextName = findViewById(R.id.editTextTextName);
        editTextLastName = findViewById(R.id.editTextTextLastName);
        editTextAge = findViewById(R.id.editTextTextAge);
        spinnerSex = findViewById(R.id.spinnerSex);
        db = FirebaseFirestore.getInstance();
    }

    public void onClickButtonSave(View view) {
        if(isFilled()){
            Toast.makeText(this, "!!!", Toast.LENGTH_SHORT).show();
            String name = editTextName.getText().toString().trim();
            String lastName = editTextLastName.getText().toString().trim();
            int age = Integer.parseInt(editTextAge.getText().toString());
            String sex = spinnerSex.getSelectedItem().toString();

            User user = new User(name, lastName, age, sex);
                    db.collection("usersExercise").add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(AddUserActivity.this, "Пользователь успешно добавлен", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddUserActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
        }
    }

    private boolean isFilled(){
        if(!editTextName.getText().toString().isEmpty() && !editTextLastName.getText().toString().isEmpty()
                && !editTextAge.getText().toString().isEmpty() && (Integer.parseInt(editTextAge.getText().toString()) > 0)){
            return true;
        }else {
            Toast.makeText(this, "Все поля должны быть правильно заполнены", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}