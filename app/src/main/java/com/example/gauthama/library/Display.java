package com.example.gauthama.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Display extends AppCompatActivity {
    String Base_url ="https://library-69351.firebaseio.com";
    Firebase fb;

    TextView textView1,textView2,textView3,textView4,textView5;
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String book = getIntent().getStringExtra("book");

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        SellCreds sd = new SellCreds();

        Firebase.setAndroidContext(Display.this);
        fb = new Firebase(Base_url+"/Sell/"+book+"_name");

        fb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final SellCreds sd = dataSnapshot.getValue(SellCreds.class);

                textView1.setText(sd.getBook().toString());
                textView2.setText(sd.getAuthor().toString());
                textView3.setText(sd.getDis().toString());
                textView4.setText(sd.getType().toString());

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView5.setText(sd.getPhone().toString());
                    }
                });

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(Display.this,MainPage.class);
                startActivity(s);
            }
        });
    }
}
