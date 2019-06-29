package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Dangnhap extends AppCompatActivity {
    EditText edtuser,edtpass;
    Button btndangnhap,btndangki;
    private FirebaseAuth mAthu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        addcontrol();
        addevent();
        mAthu=FirebaseAuth.getInstance();
    }

    private void addevent() {
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtuser.length()!=0&&edtpass.length()!=0)
                {
                dangnhapht();
                }
                else
                {
                    Toast.makeText(Dangnhap.this, "Bạn chưa nhập tài khoản !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dangnhap.this,Dangki.class);
                startActivity(intent);

            }
        });
    }
private void dangnhapht()
{
    String user=edtuser.getText().toString();
    String pass=edtpass.getText().toString();
    mAthu.signInWithEmailAndPassword(user,pass)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Dangnhap.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Dangnhap.this, Chontheloai.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Dangnhap.this,"User hoặc password sai!",Toast.LENGTH_SHORT).show();
                    }


                }
            });
}

    private void addcontrol() {
        edtuser= this.<EditText>findViewById(R.id.edtuser);
        edtpass= this.<EditText>findViewById(R.id.edtpass);
        btndangnhap= this.<Button>findViewById(R.id.btndangnhap);
        btndangki= this.<Button>findViewById(R.id.btndangki);
    }
}
