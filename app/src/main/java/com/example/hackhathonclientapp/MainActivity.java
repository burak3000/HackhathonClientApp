package com.example.hackhathonclientapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.client.myapplication.client.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnAddCard;
    Spinner cardTypeSpinner;
    //Server to connect
    String SERVER_IP = "192.168.113.60";
    int SERVER_PORT = 7000;
    Socket socket;
    String[] arraySpinner = new String[]{
            "MainEntranceCard", "ShuttleCard", "InvalidCard"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        etIP = findViewById(R.id.etIP);
//        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnAddCard = findViewById(R.id.btnAddCard);
//        Button btnConnect = findViewById(R.id.btnConnect);
        cardTypeSpinner = (Spinner) findViewById(R.id.cardTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardTypeSpinner.setAdapter(adapter);
//        btnConnect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvMessages.setText("");
//                SERVER_IP = SERVER_IP;
//                SERVER_PORT = SERVER_PORT;
//                Thread1 = new Thread(new ConnectingThread());
//                Thread1.start();
//            }
//        });
        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String message = etMessage.getText().toString().trim();
                //if (!message.isEmpty()) {
                //   new Thread(new WriteMessageToOutputStream(message)).start();
                //}
                if (cardTypeSpinner.getSelectedItem().toString().equals(arraySpinner[0])) {
                    SERVER_IP = "192.168.113.60";
                }
                else if(cardTypeSpinner.getSelectedItem().toString().equals(arraySpinner[1]))
                    SERVER_IP="192.168.113.74";
                Thread1 = new Thread(new ConnectingThread());
                Thread1.start();

//                new Thread(new WriteMessageToOutputStream("0067305985")).start();

            }
        });
    }

    private DataOutputStream output;
    private BufferedReader input;

    class ConnectingThread implements Runnable {
        @Override
        public void run() {

            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                output = new DataOutputStream(socket.getOutputStream());


//                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");
                    }
                });
                output.writeUTF("0067305985");
                output.flush();

                socket.close();


//                new Thread(new Thread2()).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append("server: " + message + "\n");
                            }
                        });
                    } else {
                        Thread1 = new Thread(new ConnectingThread());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Write message to output stream
//    class WriteMessageToOutputStream implements Runnable {
//        private String message;
//
//        WriteMessageToOutputStream(String message) {
//            this.message = message;
//        }
//
//        @Override
//        public void run() {
//            output.write(message);
//            output.flush();
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
////            runOnUiThread(new Runnable() {
////                @Override
////                public void run() {
////                    tvMessages.append("client: " + message + "\n");
////                    etMessage.setText("");
////                }
////            });
//        }
//    }
}