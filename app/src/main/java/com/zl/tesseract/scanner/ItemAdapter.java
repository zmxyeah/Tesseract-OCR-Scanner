package com.zl.tesseract.scanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.tess.TesseractCallback;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> dataList;

    public ItemAdapter(Context context, List<String> dataList) {
        this.mContext = context;
        this.dataList = dataList;
    }

    TesseractCallback tesseractCallback;

    public void addDelListener(TesseractCallback tesseractCallback) {
        this.tesseractCallback = tesseractCallback;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.no_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.index_TV = convertView.findViewById(R.id.index_TV);
            viewHolder.no_TV = convertView.findViewById(R.id.text1);
            viewHolder.button = convertView.findViewById(R.id.del_BT);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_edit(dataList.get(position));
            }
        });
        viewHolder.no_TV.setText(dataList.get(position));
        viewHolder.index_TV.setText(position + "：");
        return convertView;
    }


    class ViewHolder {
        TextView no_TV;
        TextView index_TV;
        Button button;
    }

    public void alert_edit(final String no) {
        final TextView et = new TextView(mContext);
        et.setTextSize(25);
        et.setText(no);
        new AlertDialog.Builder(mContext).setTitle("删除")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        tesseractCallback.succeed(et.getText().toString());
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("取消", null).show();
    }
}
