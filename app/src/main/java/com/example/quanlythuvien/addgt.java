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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addgt extends AppCompatActivity {

    EditText edtmasacch,edttensach,edttacgia,edtnxb,edtsotrang;
    Button btnthemsach,btnthoatthem;
    private DatabaseReference mta;
    ArrayList<Book> sachArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themgt);
        addcontrols();
        addevent();
        sachArrayList = new ArrayList<>();
        mta= FirebaseDatabase.getInstance().getReference();
        mta.child("GIAO TRINH").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sachArrayList.clear();
                for (DataSnapshot da : dataSnapshot.getChildren()){
                    Book sach=da.getValue(Book.class);
                    sachArrayList.add(sach);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void addevent() {
        btnthemsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtmasacch.getText().toString().equals("")) {
                    Toast.makeText(addgt.this, "Bạn chưa nhập mã sách!", Toast.LENGTH_SHORT).show();
                    edtmasacch.requestFocus();
                    return;
                } else if (edttensach.getText().toString().equals(""))
                {
                    Toast.makeText(addgt.this, "Bạn chưa nhập tên sách!", Toast.LENGTH_SHORT).show();
                    edttensach.requestFocus();
                    return;
                }
                else if(edttacgia.getText().toString().equals(""))
                {
                    Toast.makeText(addgt.this, "Bạn chưa nhập tác giả!", Toast.LENGTH_SHORT).show();
                    edttacgia.requestFocus();
                    return;
                }
                else if(edtnxb.getText().toString().equals(""))
                {
                    Toast.makeText(addgt.this, "Bạn chưa nhập nhà xuất bản!", Toast.LENGTH_SHORT).show();
                    edtnxb.requestFocus();
                    return;
                }
                else if(edtsotrang.getText().toString().equals(""))
                {
                    Toast.makeText(addgt.this, "Bạn chưa nhập số trang!", Toast.LENGTH_SHORT).show();
                    edtsotrang.requestFocus();
                    return;
                }
                else if(Integer.parseInt(edtsotrang.getText().toString())<8)
                {
                    Toast.makeText(addgt.this, "Số trang sách phải lớn hơn 7!", Toast.LENGTH_SHORT).show();
                    edtsotrang.requestFocus();
                    return;
                }
                else if(kiemtra()==false)
                {
                    Toast.makeText(addgt.this, "Mã đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Luu();
                }
            }
        });
        btnthoatthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void Luu() {
        String ma=edtmasacch.getText().toString();
        String ten=edttensach.getText().toString();
        String tacgia=edttacgia.getText().toString();
        String nxb=edtnxb.getText().toString();
        int sotrang=Integer.parseInt(edtsotrang.getText().toString());
        Book clsSach = new Book(ma, ten, tacgia, nxb, sotrang);
            mta.child("GIAO TRINH").push().setValue(clsSach, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(addgt.this, "Thêm thành công !", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(addgt.this, Showgt.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(addgt.this, "Thêm thất bại !", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    private void addcontrols() {
        edtmasacch= this.<EditText>findViewById(R.id.edtmasach);
        edttensach= this.<EditText>findViewById(R.id.edttensach);
        edttacgia= this.<EditText>findViewById(R.id.edttacgia);
        edtnxb= this.<EditText>findViewById(R.id.edtnxb);
        edtsotrang= this.<EditText>findViewById(R.id.edtsotrang);
        btnthemsach= this.<Button>findViewById(R.id.btnthemsach);
        btnthoatthem= this.<Button>findViewById(R.id.btnthoatthem);
    }
    private boolean kiemtra()
    {
        for (int i=0;i<sachArrayList.size();i++)
        {
            if(edtmasacch.getText().toString().equals(sachArrayList.get(i).getId())){
                return false;
            }
        }
        return true;
    }
}

