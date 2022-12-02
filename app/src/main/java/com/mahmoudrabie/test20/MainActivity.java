package com.mahmoudrabie.test20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
DatabaseReference databaseReference;
EditText f,con,mail,a,pass;
RadioButton m;
RadioGroup g;
Button but;
String mm;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        f=findViewById(R.id.full);
        mail=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        con=findViewById(R.id.pass2);
        a=findViewById(R.id.age);
       // m=findViewById(R.id.radiopm);
       // f=findViewById(R.id.radiof);
        g=findViewById(R.id.radiog);
        but=findViewById(R.id.bu);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users");




/*
        g.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(m.getId()){
                    case R.id.radiopm:
                        mm="male";
                        finish();
                    case R.id.radiof:
                        mm="female";
                        finish();
                }
            }
        });
*/

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=f.getText().toString().trim();
                String email=mail.getText().toString().trim();
                String password=pass.getText().toString().trim();
                String confirm=con.getText().toString().trim();
                String age=a.getText().toString().trim();
                if(password.length()<8){
                    pass.setError("the nuber <8");
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                firebaseUser = task.getResult().getUser();
                                DatabaseReference newuser = databaseReference.child(firebaseUser.getUid());
                                newuser.child("Full name").setValue(name);
                                newuser.child("E-Mail").setValue(email);
                                newuser.child("Password").setValue(password);
                                newuser.child("Age").setValue(age);

                                Intent intent=new Intent(MainActivity.this,Login.class);
                                startActivity(intent);
                            }
                    }
                });
            }
        });

    }
}