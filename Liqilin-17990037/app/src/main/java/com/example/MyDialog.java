package com.example;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.liqilin.R;


public class MyDialog extends Dialog {
    TextView tv_add,tv_xiangqin;
    public MyDialog(@NonNull Context context, int themeResId,ResultEntity entity) {
        super(context, themeResId);
        this.setContentView(themeResId);
        tv_add=findViewById(R.id.tv_add);
        tv_xiangqin=findViewById(R.id.tv_xiangqin);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.dimAmount = 0.40f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(true);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,AddActivity.class);
                intent.putExtra("data",entity);
                context.startActivity(intent);
                dismiss();
            }
        });
        tv_xiangqin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,XiangQinActivity.class);
                intent.putExtra("data",entity);
                context.startActivity(intent);
                dismiss();
            }
        });
    }
}
