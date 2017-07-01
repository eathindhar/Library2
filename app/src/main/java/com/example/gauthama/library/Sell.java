package com.example.gauthama.library;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Sell extends AppCompatActivity {

    String Base_url = "https://library-69351.firebaseio.com";
    Firebase fb;

    EditText editText1,editText2,editText3,editText4,editText5;
    Button button1;

    String book,author,dis,type,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        button1 = (Button)findViewById(R.id.button1);

        Firebase.setAndroidContext(Sell.this);
        fb = new Firebase(Base_url);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                book = editText1.getText().toString();
                author = editText2.getText().toString();
                dis = editText3.getText().toString();
                type = editText4.getText().toString();
                phone = editText5.getText().toString();

                new MyTask().execute();
            }
        });

    }
    class MyTask extends AsyncTask<String ,String, String>{
        @Override
        protected String doInBackground(String... params) {

            SellCreds sd = new SellCreds();
            sd.setBook(book);
            sd.setAuthor(author);
            sd.setDis(dis);
            sd.setType(type);
            sd.getPhone();

            fb.child("/Sell").child("/"+book+"_name").setValue(sd);

            Intent s =new Intent(Sell.this,MainPage.class);
            startActivity(s);

            return null;
        }

    }
}
