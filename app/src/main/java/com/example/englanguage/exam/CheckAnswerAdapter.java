package com.example.englanguage.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.englanguage.R;

import java.util.ArrayList;

public class CheckAnswerAdapter extends BaseAdapter {
    private ArrayList lsData;
    private LayoutInflater inflater;

    public CheckAnswerAdapter(ArrayList lsData, Context context) {
        this.lsData = lsData;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lsData.size();
    }

    @Override
    public Object getItem(int i) {
        return lsData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        Question question = (Question) getItem(i);
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_grid_view, null);
            viewHolder.tvNumber = (TextView) view.findViewById(R.id.tvNumAns);
            viewHolder.tvAnswer = (TextView) view.findViewById(R.id.tvAnswer);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int position = i + 1;
        viewHolder.tvNumber.setText("Câu " + position + ": ");
        viewHolder.tvAnswer.setText("");
        return view;
    }

    private static class ViewHolder{
        private TextView tvNumber;
        private TextView tvAnswer;
    }
}
