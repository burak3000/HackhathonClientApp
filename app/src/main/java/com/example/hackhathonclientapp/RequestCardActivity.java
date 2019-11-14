package com.example.hackhathonclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.client.myapplication.client.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class RequestCardActivity extends AppCompatActivity {

    Spinner cardTypeSpinner;
    Button addButton;
    String[] arraySpinner = new String[]{
            "MainEntranceCard", "ShuttleCard", "InvalidCard"
    };
    Thread connectToServerThread;
    String SERVER_IP;
    int SERVER_PORT;
    private PrintWriter output;
    private BufferedReader input;
    TextView connectivityTextView;
    Thread Thread1;
    Thread Thread2;
    Thread Thread3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_card);

        //cardTypeSpinner = (Spinner) findViewById(R.id.cardTypeSpinner);
        addButton = (Button) findViewById(R.id.btnAddCard);
        connectivityTextView = (TextView) findViewById(R.id.connectivityTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardTypeSpinner.setAdapter(adapter);
    }

//    public void RequestAddCard(View view) {
//        String selectedCardType = cardTypeSpinner.getSelectedItem().toString();
//        DispatchRequestToServer(selectedCardType);
//    }
//
//    public void DispatchRequestToServer(String selectedCardType) {
//        if (selectedCardType.equals(arraySpinner[0]))
//            sendRequestToMainEntranceServer(new Card(UUID.randomUUID().toString(), "MainEntranceCard"));
//
//    }
//
//    private void sendRequestToMainEntranceServer(Card mainEntranceCard) {
//        SERVER_IP = "192.168.113.60";
//        SERVER_PORT = 25000;
//        new Thread(new WriteMessageToOutputStream("MainEntrance")).start();
//    }
//
//    public void ConnectToServer(View view) {
//
//    }

}
