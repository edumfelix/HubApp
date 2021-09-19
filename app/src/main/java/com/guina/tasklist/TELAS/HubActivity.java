package com.guina.tasklist.TELAS;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.guina.tasklist.R;

public class HubActivity extends AppCompatActivity {
    private Button btnTarefas, btnNoticias, btnSobre, btnFrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        btnTarefas = (Button) findViewById(R.id.btn_tarefas);
        btnNoticias = (Button) findViewById(R.id.btn_noticias);
        btnSobre = (Button) findViewById(R.id.btn_sobre);
        btnFrases = (Button) findViewById(R.id.btn_frases);

        Intent IrTarefas = new Intent(this, MainActivity.class);
        Intent IrSobre = new Intent(this, SobreActivity.class);
        Intent IrFrases = new Intent(this, FrasesActivity.class);

        btnTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrTarefas);
            }
        });

        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://thenewscc.com.br/noticias/")));

            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(IrSobre);
            }
        });

        btnFrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(IrFrases);
            }
        });


    }

}
