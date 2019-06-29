package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Chontheloai extends AppCompatActivity {
    ImageView imgkhkt,imgcntt,imggt,imgttt,imgvhnt,imgnttc,imgthoatshow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai);
        addcontrols();
        addevent();

    }

    private void addevent() {
        imgkhkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Showkhkt.class);
                startActivity(intent);
            }
        });
        imgcntt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Showcntt.class);
                startActivity(intent);

            }
        });
        imgnttc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Shownttc.class);
                startActivity(intent);
            }
        });
        imggt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Showgt.class);
                startActivity(intent);
            }
        });
        imgvhnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Showvhnt.class);
                startActivity(intent);
            }
        });
        imgttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Showttt.class);
                startActivity(intent);
            }
        });
        imgthoatshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Chontheloai.this,Dangnhap.class);
                startActivity(intent);
            }
        });
    }

    private void addcontrols() {
        imgkhkt= this.<ImageView>findViewById(R.id.imgkhkt);
        imgcntt= this.<ImageView>findViewById(R.id.imgcntt);
        imggt= this.<ImageView>findViewById(R.id.imggt);
        imgnttc= this.<ImageView>findViewById(R.id.imgnttc);
        imgttt= this.<ImageView>findViewById(R.id.imgttt);
        imgvhnt= this.<ImageView>findViewById(R.id.imgvhnt);
        imgthoatshow= this.<ImageView>findViewById(R.id.imgthoatshow);
    }
}
