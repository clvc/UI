package com.palfund.ui.autocompletetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteTextViewActivity extends AppCompatActivity {
    private List<Book> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id
                .autoCompleteTextView);
        initData();
        ActvAdapter adapter = new ActvAdapter(this, mList);
        autoCompleteTextView.setAdapter(adapter);
    }

    private void initData() {
        Book b1 = new Book(1, "三国演义", "罗贯中", 38, "sanguoyanyi");
        Book b2 = new Book(2, "红楼梦", "曹雪芹", 25, "hongloumeng");
        Book b3 = new Book(3, "西游记", "吴承恩", 43, "xiyouji");
        Book b4 = new Book(4, "水浒传", "施耐庵", 72, "shuihuzhuan");
        Book b5 = new Book(5, "随园诗话", "袁枚", 32, "suiyuanshihua");
        Book b6 = new Book(6, "说文解字", "许慎", 14, "shuowenjiezi");
        Book b7 = new Book(7, "文心雕龙", "刘勰", 18, "wenxindiaolong");
        mList.add(b1);
        mList.add(b2);
        mList.add(b3);
        mList.add(b4);
        mList.add(b5);
        mList.add(b6);
        mList.add(b7);
    }
}
