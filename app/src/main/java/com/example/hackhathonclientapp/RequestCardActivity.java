package com.example.hackhathonclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.client.myapplication.client.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class RequestCardActivity extends AppCompatActivity {

    Spinner cardTypeSpinner;
    Button addButton;
    EditText etIp;
    EditText etPort;
    Thread connectToServerThread;
    String SERVER_IP;
    int SERVER_PORT;

    TextView connectivityTextView;
    Thread Thread1;
    Thread Thread2;
    Thread Thread3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_card);

        addButton = (Button) findViewById(R.id.btnAddCard);
        etIp = (EditText) findViewById(R.id.etIp);
        etPort = (EditText) findViewById(R.id.etPort);
    }

    public void SendRequestToServer(View view) {
        SERVER_IP = etIp.getText().toString();
        SERVER_PORT= Integer.parseInt(etPort.getText().toString());

        Thread1 = new Thread(new ConnectingThread());
        Thread1.start();
    }

    private DataOutputStream output;
    private BufferedReader input;
    Socket socket;
    TextView tvMessages;

    public void ReadNFC(View view) {
        /*TextView tv = findViewById(R.id.nfcText);
        tv.setText("Place your NFC Card behind the device");
        tv.setVisibility(View.VISIBLE);*/
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        NFCCardReader nfcReader = new NFCCardReader();
        if (nfc != null) {
            nfc.enableReaderMode(this, nfcReader, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK | NfcAdapter.FLAG_READER_NFC_B, null);
        }

        Thread thread = new Thread(){
            public void run(){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast toast = Toast.makeText(RequestCardActivity.this, "Place your NFC Card behind the device", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                });
            }
        };
        thread.start();
    }

    class ConnectingThread implements Runnable {

        @Override
        public void run() {

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new DataOutputStream(socket.getOutputStream());
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String strToSendServer = NFCCardReader.tagID + "~0067305985" +"<EOF>";
                output.writeUTF(strToSendServer);
                String messageReceived = input.readLine();

                if(messageReceived.startsWith("TRUE"))
                {
                    String[] cardType = messageReceived.split("~");
                    Card newCard = new Card(cardType[1]);
                    Card.CardList.add(newCard);

                    Thread thread = new Thread(){
                        public void run(){
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast toast = Toast.makeText(RequestCardActivity.this, "Card Added Successfully!", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                                    toast.show();
                                }
                            });
                        }
                    };
                    thread.start();
            }

                output.flush();
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
                //finish();
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

