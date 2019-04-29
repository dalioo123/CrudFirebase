package com.example.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class UserList extends ArrayAdapter<User> {
    private Activity context;
    private List<User> produits;
    DatabaseReference databaseReference;
    EditText edtName;
    public UserList(@NonNull Activity context, List<User> produits, DatabaseReference databaseReference,EditText edtName) {
        super(context, R.layout.layout_produit_list,produits);

        this.context=context;
        this.produits=produits;
        this.databaseReference=databaseReference;
        this.edtName=edtName;
    }


    public View getView(int pos, View View,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_produit_list, null,true);

        TextView txtName = (TextView) listViewItem.findViewById(R.id.txtName);
        Button btnDelete = (Button) listViewItem.findViewById(R.id.btnDelete);
        Button btnUpdate = (Button) listViewItem.findViewById(R.id.btnUpdate);

        final User produit = produits.get(pos);
        txtName.setText(produit.getProduit());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                databaseReference.child(produit.getId()).removeValue();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                edtName.setText(produit.getProduit());
                MainActivity.produitId = produit.getId();
            }
        });

        return listViewItem;

    }
}
