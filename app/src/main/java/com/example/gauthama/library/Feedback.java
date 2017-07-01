package com.example.gauthama.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Feedback extends AppCompatActivity {

    String Base_url="https://library-69351.firebaseio.com";
    Firebase fb;

    TextView textView1;
    EditText editText1;
    Button button1,button2;

    String feedback,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        textView1 = (TextView)findViewById(R.id.textView1);
        editText1 = (EditText)findViewById(R.id.editText1);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button1);

        name = getIntent().getStringExtra("name");

        Firebase.setAndroidContext(Feedback.this);
        fb = new Firebase(Base_url+("/Accounts/Feedback/"+name+"_feedback"));

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(Feedback.this,MainPage.class);
                startActivity(s);
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = editText1.getText().toString();

                FeedbackCreds fd = new FeedbackCreds();
                fd.setFeedback(feedback);
                fb.setValue(fd);
                Toast.makeText(Feedback.this,"Your feedback is submitted",Toast.LENGTH_SHORT).show();
                Intent s = new Intent(Feedback.this,MainPage.class);
                startActivity(s);
            }
        });
    }
}
