package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Thongtinvhnt extends AppCompatActivity {
    EditText edtmasachtt,edttacgiatt,edttensachtt,edtnxbtt,edtsotrangtt;
    Button btnthoattt,btncapnhat;
    private Book sach;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtinvhnt);
        addcontrols();
        addevent();
        loaddata();
    }
    private void loaddata()
    {
        Bundle ten=getIntent().getBundleExtra("ahihicntt");
        sach =(Book) ten.getSerializable("khaucntt");
        edtmasachtt.setText(sach.getId());
        edttensachtt.setText(sach.getTensach());
        edttacgiatt.setText(sach.getTacgia());
        edtnxbtt.setText(sach.getNxb());
        edtsotrangtt.setText(""+sach.getSotrang());

    }
    private void addevent() {
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thongtinvhnt.this.onBackPressed();
                Intent intent=new Intent(Thongtinvhnt.this, Updatevhnt.class);
                Bundle load = new Bundle();
                load.putSerializable("khaucntt", sach);
                intent.putExtra("ahihicntt",load);
                startActivity(intent);
            }
        });
        btnthoattt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thongtinvhnt.this.onBackPressed();
                Intent intent=new Intent(Thongtinvhnt.this,Showvhnt.class);
                startActivity(intent);
            }
        });
    }

    private void addcontrols() {
        edtmasachtt= this.<EditText>findViewById(R.id.edtmasachtt);
        edttensachtt= this.<EditText>findViewById(R.id.edttensachtt);
        edttacgiatt= this.<EditText>findViewById(R.id.edttacgiatt);
        edtnxbtt= this.<EditText>findViewById(R.id.edtnxbtt);
        edtsotrangtt= this.<EditText>findViewById(R.id.edtsotrangtt);
        btnthoattt= this.<Button>findViewById(R.id.btnthoattt);
        btncapnhat= this.<Button>findViewById(R.id.btncapnhat);
    }
}
