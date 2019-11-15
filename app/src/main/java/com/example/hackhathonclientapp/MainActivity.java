package com.example.hackhathonclientapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.client.myapplication.client.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    Button btnAddCard;
    ListView CardListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddCard =(Button) findViewById(R.id.btnAddCard);
        CardListView = (ListView) findViewById(R.id.list_view);

        ArrayAdapter<Card> adapter = new ArrayAdapter<Card>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Card.CardList);
        CardListView.setAdapter(adapter);
    }

    public void RequestOnClick(View view) {
        Intent myIntent = new Intent(this, RequestCardActivity.class);
        startActivity(myIntent);
    }

}
