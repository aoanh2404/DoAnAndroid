package com.example.quanlythuvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_sach extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Book> sachlist;

    public adapter_sach(Context context, int layout, ArrayList<Book> sachlist) {
        this.context = context;
        this.layout = layout;
        this.sachlist = sachlist;
    }

    @Override
    public int getCount() {
        return sachlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.activity_sach,null);
        Book quyensach=sachlist.get(position);
        ((TextView)convertView.findViewById(R.id.txtma)).setText(quyensach.getId());
        ((TextView)convertView.findViewById(R.id.txtten)).setText(quyensach.getTensach());
        ((TextView)convertView.findViewById(R.id.txttacgia)).setText(quyensach.getTacgia());
        return convertView;
    }
}
