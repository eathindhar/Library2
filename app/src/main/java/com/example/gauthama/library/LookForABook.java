package com.example.gauthama.library;

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

public class LookForABook extends AppCompatActivity {
    String Base_url = "https://library-69351.firebaseio.com";
    Firebase fb;

    TextView textView2,textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_for_abook);

        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);

        Firebase.setAndroidContext(LookForABook.this);
        //fb = new Firebase(Base_url+"/books");

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mb = new AlertDialog.Builder(LookForABook.this);
                View ve = getLayoutInflater().inflate(R.layout.bookknown,null);

                final EditText editText1;
                final TextView textView1,textView2;
                Button button1;

                editText1 = (EditText)ve.findViewById(R.id.editText1);
                textView1 = (TextView)ve.findViewById(R.id.textView1);
                textView2 = (TextView)ve.findViewById(R.id.textView2);
                button1 = (Button)ve.findViewById(R.id.button1);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String bookName = editText1.getText().toString();
                        fb = new Firebase(Base_url+"/book");

                        fb.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot post1: dataSnapshot.getChildren()){
                                    for(DataSnapshot post2 : post1.getChildren()){
                                        BookAndAuthor bd = post2.getValue(BookAndAuthor.class);
                                        if(bookName.equals(bd.getBookname())){
                                            textView2.setText(""+bd.getNo()+" Books left");
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                    }
                });




                mb.setView(ve);
                AlertDialog m = mb.create();
                m.show();

            }
        });


        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mb =  new AlertDialog.Builder(LookForABook.this);
                View ve = getLayoutInflater().inflate(R.layout.bookandauthor,null);

                final TextView textView1,textView2;
                final EditText editText1,editText2;
                Button button1;

                textView1 = (TextView)ve.findViewById(R.id.textView1);
                textView2 = (TextView)ve.findViewById(R.id.textView2);
                editText1 = (EditText)ve.findViewById(R.id.editText1);
                editText2 = (EditText)ve.findViewById(R.id.editText2);
                button1 = (Button)ve.findViewById(R.id.button1);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String author = editText1.getText().toString();
                        String book = editText2.getText().toString();

                        fb = new Firebase(Base_url+"/book/"+author+"/"+book);
                        BookAndAuthor bd = new BookAndAuthor();

                        fb.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                BookAndAuthor bd = dataSnapshot.getValue(BookAndAuthor.class);
                                textView2.setText(""+bd.getNo().toString()+" books left");
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });

                mb.setView(ve);
                AlertDialog m = mb.create();
                m.show();
            }
        });

    }
}
