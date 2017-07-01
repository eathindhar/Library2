package com.example.gauthama.library;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class SignUp extends AppCompatActivity {
    String Base_url="https://library-69351.firebaseio.com";
    Firebase fb;

    EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7;
    Button button1;

    String name,pass,cpass,adhar,mail,phone,addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        button1 = (Button)findViewById(R.id.button1);

        Firebase.setAndroidContext(SignUp.this);
        fb = new Firebase(Base_url);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText1.getText().toString();
                pass = editText2.getText().toString();
                cpass = editText3.getText().toString();
                adhar = editText4.getText().toString();
                mail = editText5.getText().toString();
                phone = editText6.getText().toString();
                addr = editText7.getText().toString();

                if(!name.isEmpty() && !pass.isEmpty() && !cpass.isEmpty() && !adhar.isEmpty() && !mail.isEmpty() && !phone.isEmpty() && !addr.isEmpty())
                {
                    if(pass.equals(cpass))
                    {

                        new MyTask().execute();
                        finish();
                    }
                    else
                        Toast.makeText(SignUp.this,"Password does not match",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(SignUp.this,"Some Fields are empty",Toast.LENGTH_SHORT).show();
            }
        });
    }
   public class MyTask extends AsyncTask<String ,String ,String>{
       @Override
       protected String doInBackground(String... params) {
           SignUpCreds cd = new SignUpCreds();
           cd.setName(name);
           cd.setPass(pass);
           cd.setAdhar(adhar);
           cd.setMail(mail);
           cd.setPhone(phone);
           cd.setAddr(addr);

           fb.child("/Accounts").child("/Members").child("/"+name+"_user").setValue(cd);

           LendedBooksCreds ld = new LendedBooksCreds();
           ld.setBook1("");
           ld.setDate1("");
           ld.setBook2("");
           ld.setDate2("");
           fb.child("/Accounts").child("/User_books").child("/"+name+"_books").setValue(ld);

           FeedbackCreds fd = new FeedbackCreds();
           fd.setFeedback("");
           fb.child("/Accounts").child("/Feedback").child("/"+name+"_feedback").setValue(fd);


           Intent s = new Intent(SignUp.this,loginActivity.class);

           startActivity(s);


           return null;
       }
   }
}


