package com.example.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnSave;
    EditText edtName;
    DatabaseReference databaseReference;
    ListView listViewProduits;
    List<User>produits;
    public static String produitId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produits = new ArrayList<User>();
        databaseReference = FirebaseDatabase.getInstance().getReference("produits");

        btnSave= (Button) findViewById(R.id.btnSave);
        edtName = (EditText) findViewById(R.id.edtName);
        listViewProduits = (ListView) findViewById(R.id.listViewProduits);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if(TextUtils.isEmpty(produitId)){
                    String id = databaseReference.push().getKey();
                    User produit = new User(id,name);
                    databaseReference.child(id).setValue(produit);
                    Toast.makeText(MainActivity.this, "Produit Created Succefully !", Toast.LENGTH_SHORT).show();

                }else{
                    databaseReference.child(produitId).child("name").setValue(name);
                    Toast.makeText(MainActivity.this, "User Update Succefully", Toast.LENGTH_SHORT).show();
                }
                edtName.setText(null);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                produits.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    User produit =postSnapshot.getValue(User.class);
                    produits.add(produit);
                }
                UserList produitAdapter = new UserList(MainActivity.this,produits,databaseReference,edtName);
                listViewProduits.setAdapter(produitAdapter);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
}
