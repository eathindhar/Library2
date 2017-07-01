package com.example.gauthama.library;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Buy extends AppCompatActivity {
    String Base_url ="https://library-69351.firebaseio.com";
    Firebase fb;

    EditText editText1;
    Button button1;

    String book,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        editText1 = (EditText)findViewById(R.id.editText1);
        button1 = (Button)findViewById(R.id.button1);

        Firebase.setAndroidContext(Buy.this);

        name = getIntent().getStringExtra("name");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book = editText1.getText().toString();

                Intent s = new Intent(Buy.this,Display.class);
                s.putExtra("book",book);
                startActivity(s);

            }
        });
    }
}
