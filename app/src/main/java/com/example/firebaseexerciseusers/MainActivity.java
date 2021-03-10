package com.example.firebaseexerciseusers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaseexerciseusers.adapters.UserAdapter;
import com.example.firebaseexerciseusers.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private List<User> users;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        users = new ArrayList<>();
        adapter = new UserAdapter();
        adapter.setUsers(users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();

        db.collection("usersExercise").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null){
                    users.removeAll(users);
                    for(QueryDocumentSnapshot documentSnapshot: value) {
                        users.add(documentSnapshot.toObject(User.class));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });


//        db.collection("usersExercise")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//                            users.removeAll(users);
//                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
//                                users.add(documentSnapshot.toObject(User.class));
//                            }
//                            adapter.notifyDataSetChanged();
//                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                        }else {
//                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    }

    public void onClickButtonAddUser(View view) {
        Intent intent = new Intent(this, AddUserActivity.class);
        startActivity(intent);
    }
}