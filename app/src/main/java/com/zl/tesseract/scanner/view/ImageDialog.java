package com.zl.tesseract.scanner.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.tesseract.R;
import com.zl.tesseract.scanner.tess.TesseractCallback;

public class ImageDialog extends AlertDialog {

    private Bitmap bmp;

    private String title;

    public ImageDialog(@NonNull Context context) {
        super(context);
    }

    public ImageDialog addBitmap(Bitmap bmp) {
        if (bmp != null) {
            this.bmp = bmp;
        }
        return this;
    }

    public ImageDialog addTitle(String title) {
        if (title != null) {
            this.title = title;
        }
        return this;
    }

    private TesseractCallback tesseractCallback;

    public void addListener(TesseractCallback tesseractCallback) {
        this.tesseractCallback = tesseractCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_dialog);

        ImageView imageView = (ImageView) findViewById(R.id.image_dialog_imageView);
        TextView textView = (TextView) findViewById(R.id.image_dialog_textView);

        textView.setBackgroundColor(Color.rgb(170, 170, 170));
        textView.setTextColor(Color.rgb(60, 65, 62));
        Button button = findViewById(R.id.ok_BT);

        if (bmp != null) {
            imageView.setImageBitmap(bmp);
        }

        if (title != null) {
            textView.setText(this.title);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tesseractCallback.succeed(title);

                ImageDialog.this.dismiss();
            }
        });

    }

    @Override
    public void dismiss() {
        bmp.recycle();
        bmp = null;
        System.gc();
        super.dismiss();
    }
}