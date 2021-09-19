package com.guina.tasklist.TELAS;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.guina.tasklist.AppDatabase;
import com.guina.tasklist.ListarTarefasAdapter;
import com.guina.tasklist.R;
import com.guina.tasklist.Tarefa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvListarTarefas;
    private List<Tarefa> tarefas;
    private FloatingActionButton fabCadastrarTarefa;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<Intent> activityResultLauncher2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       // getSupportActionBar().hide();
        setContentView(R.layout.lista_main);

        lsvListarTarefas = (ListView) findViewById(R.id.lsv_listar_tarefas);
        fabCadastrarTarefa = (FloatingActionButton) findViewById(R.id.fab_cadastrar_tarefa);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                                Snackbar.make(findViewById(R.id.layout_listar_tarefas), "Tarefa cadastrada com sucesso!", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });

        activityResultLauncher2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Snackbar.make(findViewById(R.id.layout_listar_tarefas), "Tarefa alterada com sucesso!", Snackbar.LENGTH_LONG).show();
                    }
                });


        fabCadastrarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastrarTarefaActivity.class);
                activityResultLauncher.launch(intent);
            }
        });

        lsvListarTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(MainActivity.this, AlterarTarefaActivity.class);
                intent2.putExtra("id_tarefa", tarefas.get(position).getId());
                activityResultLauncher2.launch(intent2);
            }
        });

        lsvListarTarefas.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem deletar = menu.add("Deletar");
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                final Tarefa tarefaSelecionada = (Tarefa) lsvListarTarefas.getAdapter().getItem(info.position);
                deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        AlertDialog a = new AlertDialog.Builder(MainActivity.this)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Deletar")
                                .setMessage("Deseja realmente excluir?")
                                .setPositiveButton("Sim",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                AppDatabase.getAppDatabase(MainActivity.this).tarefaDAO().deletar(tarefaSelecionada);
                                                atualizarLista();
                                                Snackbar.make(findViewById(R.id.layout_listar_tarefas), "Deletado com sucesso", Snackbar.LENGTH_LONG).show();
                                            }
                                        })
                                .setNegativeButton("Não", null)
                                .show();

                        return false;
                    }
                });
            }});

    }

    protected void onStart() {
        super.onStart();
        atualizarLista();
    }

    public void atualizarLista(){
        tarefas = AppDatabase.getAppDatabase(this).tarefaDAO().listarTodos();
        ListarTarefasAdapter adapter = new ListarTarefasAdapter(tarefas, this);
        lsvListarTarefas.setAdapter(adapter);
    }

}