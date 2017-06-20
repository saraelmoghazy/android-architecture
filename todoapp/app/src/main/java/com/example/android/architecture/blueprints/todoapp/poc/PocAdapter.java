package com.example.android.architecture.blueprints.todoapp.poc;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.poc.data.Poc;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by user on 6/14/2017.
 */

public class PocAdapter extends BaseAdapter {
    PocItemListener pocItemListener;
    List<Poc> pocList;


    public PocAdapter(List<Poc> pocList, PocItemListener pocItemListener) {
        setPocList(pocList);
        this.pocItemListener = checkNotNull(pocItemListener);
    }

    public void replaceData(List<Poc> pocList) {
        setPocList(pocList);
        notifyDataSetChanged();
    }

    private void setPocList(List<Poc> pocList) {
        this.pocList = checkNotNull(pocList);
    }


    @Override
    public int getCount() {
        return pocList.size();
    }

    @Override
    public Poc getItem(int position) {
        return pocList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.poc_item, parent, false);
        }


        final Poc poc = getItem(position);
        LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.itemLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pocItemListener.onPocClicked(poc);
            }
        });
        TextView title = (TextView) rowView.findViewById(R.id.title);
        title.setText(poc.getTitle());
        ImageView status = (ImageView) rowView.findViewById(R.id.status);
        if (poc.isCompleted())
            status.setVisibility(View.VISIBLE);
        else
            status.setVisibility(View.INVISIBLE);
        return rowView;


    }
}
