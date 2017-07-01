package com.example.gauthama.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    String Base_url = "https://library-69351.firebaseio.com/";
    Firebase fb;

    EditText editText1,editText2;
    Button button1,button2;
    TextView textView1;

    String pass,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);

        Firebase.setAndroidContext(loginActivity.this);
        fb = new Firebase(Base_url);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = editText1.getText().toString();
                pass = editText2.getText().toString();

                SignUpCreds cd = new SignUpCreds();
                fb = new Firebase(Base_url+"/Accounts/Members");

                fb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                        {
                            SignUpCreds cd = postSnapshot.getValue(SignUpCreds.class);
                            if(cd.getAdhar().equals(id) && cd.getPass().equals(pass)){
                                Toast.makeText(loginActivity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                                Intent s = new Intent(loginActivity.this,MainPage.class);
                                String str = cd.getName();
                                System.out.println(str);
                                s.putExtra("name",str);
                                System.out.println("fuck1");
                                startActivity(s);
                                System.out.println("fuck2");
                                finish();
                            }
                            if(cd.getAdhar().equals(id)){
                                if(!cd.getPass().equals(pass)){
                                    Toast.makeText(loginActivity.this,"Adhar id and password does not match",Toast.LENGTH_SHORT).show();
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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(loginActivity.this,SignUp.class);
                startActivity(s);
                finish();
            }
        });
    }
}
