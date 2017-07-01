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

public class LendedBooks extends AppCompatActivity {
    String Base_url="https://library-69351.firebaseio.com";
    Firebase fb;

    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    Button button1;

    String name,book1,book2,date1,date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lended_books);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        button1 = (Button)findViewById(R.id.button1);

        System.out.print("hello");

        name =  getIntent().getStringExtra("name");
        System.out.println(name);
        System.out.println("Fuck");

        LendedBooksCreds ld = new LendedBooksCreds();
        Firebase.setAndroidContext(LendedBooks.this);
        fb = new Firebase(Base_url+"/Accounts/User_books/"+name+"_books");



        fb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LendedBooksCreds ld = dataSnapshot.getValue(LendedBooksCreds.class);
                book1 = ld.getBook1();
                System.out.println(book1);

                System.out.print(ld.getBook1().toString());
                System.out.print(ld.getBook2().toString());
                System.out.print(ld.getDate1().toString());
                System.out.print(ld.getDate2().toString());


                textView3.setText("" + ld.getBook1().toString());
                textView4.setText("" + ld.getDate1().toString());
                textView5.setText("" + ld.getBook2().toString());
                textView6.setText("" + ld.getDate2().toString());

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(LendedBooks.this,MainPage.class);
                startActivity(s);
                finish();
            }
        });


    }
}
