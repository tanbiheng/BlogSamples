package com.tbh.app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tbh.app.R;
import com.tbh.app.impl.OnLimitClickHelper;
import com.tbh.app.impl.OnLimitClickListener;

public class SearchDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        findViewById(R.id.search_cancle_layout).setOnClickListener(
                new OnLimitClickHelper(new OnLimitClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }));
    }
}
