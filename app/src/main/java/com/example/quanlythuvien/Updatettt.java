package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Updatettt extends AppCompatActivity {
    EditText edtmasachup,edttensachup,edttacgiaup,edtnxbup,edtsotrangup;
    Button btnupdate,btnthoatup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatettt);
        addcontrols();
        loaddata();
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edttensachup.getText().toString().equals(""))
                {
                    Toast.makeText(Updatettt.this, "Bạn chưa nhập tên sách!", Toast.LENGTH_SHORT).show();
                    edttensachup.requestFocus();
                    return;
                }
                else if(edttacgiaup.getText().toString().equals(""))
                {
                    Toast.makeText(Updatettt.this, "Bạn chưa nhập tác giả!", Toast.LENGTH_SHORT).show();
                    edttacgiaup.requestFocus();
                    return;
                }
                else if(edtnxbup.getText().toString().equals(""))
                {
                    Toast.makeText(Updatettt.this, "Bạn chưa nhập nhà xuất bản!", Toast.LENGTH_SHORT).show();
                    edtnxbup.requestFocus();
                    return;
                }
                else if(edtsotrangup.getText().toString().equals(""))
                {
                    Toast.makeText(Updatettt.this, "Bạn chưa nhập số trang!", Toast.LENGTH_SHORT).show();
                    edtsotrangup.requestFocus();
                    return;
                }
                else if(Integer.parseInt(edtsotrangup.getText().toString())<8)
                {
                    Toast.makeText(Updatettt.this, "Số trang sách phải lớn hơn 7!", Toast.LENGTH_SHORT).show();
                    edtsotrangup.requestFocus();
                    return;
                }
                else {
                    update();
                    Updatettt.this.onBackPressed();
                    Intent intent = new Intent(Updatettt.this, Showttt.class);
                    startActivity(intent);
                }
            }
        });
        btnthoatup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Updatettt.this.onBackPressed();
                Intent intent = new Intent(Updatettt.this, Showttt.class);
                startActivity(intent);
            }
        });
    }

    private void loaddata()
    {
        Bundle ten = getIntent().getBundleExtra("ahihicntt");
        Book sach=(Book) ten.getSerializable("khaucntt");
        edtmasachup.setText(sach.getId());
        edttensachup.setText(sach.getTensach());
        edttacgiaup.setText(sach.getTacgia());
        edtnxbup.setText(sach.getNxb());
        edtsotrangup.setText(""+sach.getSotrang());
    }
    private void update()
    {   String ma=edtmasachup.getText().toString();
        final String ten=edttensachup.getText().toString();
        final String tacgia=edttacgiaup.getText().toString();
        final String ncb=edtnxbup.getText().toString();
        final int sotrang=Integer.parseInt(edtsotrangup.getText().toString());
        Book clsbook=new Book(ma,ten,tacgia,ncb,sotrang);
        final DatabaseReference mta = FirebaseDatabase.getInstance().getReference("TRUYEN TIEU THUYET");
        final Query query = mta.orderByChild("id").equalTo(clsbook.getId());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if(snapshot.exists()){
                    for (DataSnapshot child: snapshot.getChildren()) {
                        String postkey = child.getRef().getKey();
                        mta.child(postkey).child("tensach").setValue(ten);
                        mta.child(postkey).child("tacgia").setValue(tacgia);
                        mta.child(postkey).child("sotrang").setValue(sotrang);
                        mta.child(postkey).child("nxb").setValue(ncb);
                    }
                    Toast.makeText(Updatettt.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Updatettt.this, "Lỗi cập nhật !", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addcontrols() {
        edtmasachup= this.<EditText>findViewById(R.id.edtmasachup);
        edttensachup= this.<EditText>findViewById(R.id.edttensachup);
        edttacgiaup= this.<EditText>findViewById(R.id.edttacgiaup);
        edtnxbup= this.<EditText>findViewById(R.id.edtnxbup);
        edtsotrangup= this.<EditText>findViewById(R.id.edtsotrangup);
        btnupdate= this.<Button>findViewById(R.id.btnupdate);
        btnthoatup= this.<Button>findViewById(R.id.btnthoatup);
    }
}
