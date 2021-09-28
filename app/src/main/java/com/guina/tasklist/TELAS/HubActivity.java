package com.guina.tasklist.TELAS;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.guina.tasklist.R;

public class HubActivity extends AppCompatActivity {
    private Button btnTarefas, btnNoticias, btnSobre, btnFrases;

    //Teste de conexão para pagina notícias
    public boolean Online() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

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
        Intent IrNoticias = new Intent(this, NoticiasActivity.class);


        btnTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IrTarefas);
            }
        });

        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Online()){
                startActivity(IrNoticias);
            }
            else {
                    Snackbar.make(findViewById(R.id.layout_hub), "Ops! Parece que você não está conectado a internet!", Snackbar.LENGTH_LONG).show();
                }

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
