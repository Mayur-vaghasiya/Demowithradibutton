package com.example.demowithradibutton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by peacock on 3/11/16.
 */
public class Listadapter extends BaseAdapter {

    Context context;
    ArrayList<User> listuser;

    public Listadapter(Context context, ArrayList<User> listitem) {
        this.context = context;
        this.listuser = listitem;

    }

    @Override
    public int getCount() {
        return listuser.size();
    }

    @Override
    public Object getItem(int position) {
        return listuser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutinflater;

        if (convertView == null) {
            layoutinflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutinflater.inflate(R.layout.listitem, null);
            holder = new Holder();
            holder.txtusername = (TextView) convertView.findViewById(R.id.textname);
            holder.txtusermail = (TextView) convertView.findViewById(R.id.textemail);
            holder.txtuserphone = (TextView) convertView.findViewById(R.id.textphone);
            holder.txtgender = (TextView) convertView.findViewById(R.id.textgender);
            holder.lableuser = (TextView) convertView.findViewById(R.id.lblname);
            holder.lableemail = (TextView) convertView.findViewById(R.id.lblemail);
            holder.lableephone = (TextView) convertView.findViewById(R.id.lblphone);
            holder.lablegender = (TextView) convertView.findViewById(R.id.lblGender);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtusername.setText(listuser.get(position).getName());
        holder.txtusermail.setText(listuser.get(position).getEmail());
        holder.txtuserphone.setText(listuser.get(position).getMobile());
        holder.txtgender.setText(listuser.get(position).getGender());

        return convertView;
    }


    public class Holder {
        TextView txtusername, txtusermail, txtuserphone, txtgender, lableuser, lableemail, lableephone, lablegender;
    }
}
