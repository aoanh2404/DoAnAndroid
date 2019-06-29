package com.example.quanlythuvien;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Showcntt extends Activity {
    GridView grvsach;
    ImageButton btnthem,btnthoatkhkt;
    ArrayList<Book>sachArrayList;
    private DatabaseReference mta;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcntt);
        addcontrols();
        addevent();
        sachArrayList = new ArrayList<>();
        final adapter_sach quyensachadapter=new adapter_sach(Showcntt.this,R.layout.activity_sach,sachArrayList);
        grvsach.setAdapter(quyensachadapter);
        mta= FirebaseDatabase.getInstance().getReference();
        mta.child("CONG NGHE THONG TIN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sachArrayList.clear();
                for (DataSnapshot da : dataSnapshot.getChildren()){
                    Book clsSach = da.getValue(Book.class);
                    sachArrayList.add(clsSach);
                }
                quyensachadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void addevent() {
        btnthoatkhkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Showcntt.this,Chontheloai.class);

                startActivity(intent);
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Showcntt.this, addsachcntt.class);
                startActivity(intent);

            }
        });
        grvsach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
            }
        });
    }

    private void addcontrols() {
        grvsach= this.<GridView>findViewById(R.id.grvsach);
        btnthem= this.findViewById(R.id.btnthem);
        btnthoatkhkt= this.<ImageButton>findViewById(R.id.btnthoatkhkt);
    }
    public void showAlertDialog(final int pos){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn muốn thực hiện thao tác nào!");
        builder.setCancelable(true);
        builder.setPositiveButton("Xoá", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final DatabaseReference mta = FirebaseDatabase.getInstance().getReference("CONG NGHE THONG TIN");
                final Query query = mta.orderByChild("id").equalTo(sachArrayList.get(pos).getId());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            for (DataSnapshot child: snapshot.getChildren()) {
                                String postkey = child.getRef().getKey();
                                mta.child(postkey).removeValue();
                            }
                            Toast.makeText(Showcntt.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Showcntt.this, "Lỗi không thể xóa!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Showcntt.this.onBackPressed();
                Intent intent=new Intent(Showcntt.this, Updatecntt.class);
                Bundle ten=new Bundle();
                ten.putSerializable("khaucntt",sachArrayList.get(pos));
                intent.putExtra("ahihicntt",ten);
                startActivity(intent);

            }
        });
       builder.setNeutralButton("Xem", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               Showcntt.this.onBackPressed();
               Intent intent=new Intent(Showcntt.this, Thongtincntt.class);
               Bundle ten=new Bundle();
               ten.putSerializable("khaucntt",sachArrayList.get(pos));
               intent.putExtra("ahihicntt",ten);
               startActivity(intent);

           }
       });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}