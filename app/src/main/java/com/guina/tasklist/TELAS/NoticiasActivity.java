package com.guina.tasklist.TELAS;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.guina.tasklist.R;

public class NoticiasActivity extends AppCompatActivity {
    private Button btntncc,btncnn,btnest,btng1,btntnyt;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        btntncc = (Button) findViewById(R.id.btn_tncc);
        btncnn = (Button) findViewById(R.id.btn_CNN);
        btnest = (Button) findViewById(R.id.btn_estadao);
        btng1 = (Button) findViewById(R.id.btn_g1);
        btntnyt = (Button) findViewById(R.id.btn_tnyt);



        btntncc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://thenewscc.com.br/noticias/")));
            }
        });

        btncnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cnnbrasil.com.br/")));
            }
        });

        btnest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.estadao.com.br/")));
            }
        });

        btng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://g1.globo.com/")));
            }
        });

        btntnyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nytimes.com/")));
            }
        });
    }
}
