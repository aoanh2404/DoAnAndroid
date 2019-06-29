package com.example.quanlythuvien;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Dangki extends AppCompatActivity {
    ImageView imgdki,imgthoat;
    EditText edtuserdangki,edtpassdangki;
    private FirebaseAuth mAthu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_dangki);
        addcontrols();
        mAthu=FirebaseAuth.getInstance();
        addevent();
    }

    private void addevent() {
        imgthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dangki.this.onBackPressed();
            }
        });
        imgdki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangki();
                Dangki.this.onBackPressed();
            }
        });
    }
    private  void dangki()
    {
        String user=edtuserdangki.getText().toString();
        String pass=edtpassdangki.getText().toString();
        mAthu.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Dangki.this,"Đăng kí thành công!",Toast.LENGTH_SHORT).show();
                            edtuserdangki.setText("");
                            edtpassdangki.setText("");

                        } else {
                            Toast.makeText(Dangki.this,"Đăng kí thất bại!",Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
    private void addcontrols() {
        imgdki= this.<ImageView>findViewById(R.id.imgdki);
        imgthoat= this.<ImageView>findViewById(R.id.imgthoat);
        edtuserdangki= this.<EditText>findViewById(R.id.edtuserdangki);
        edtpassdangki= this.<EditText>findViewById(R.id.edtpassdangki);
    }
}
