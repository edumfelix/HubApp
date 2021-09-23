package com.guina.tasklist.TELAS;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.guina.tasklist.AppDatabase;
import com.guina.tasklist.R;
import com.guina.tasklist.Tarefa;

import java.util.Calendar;
import java.util.Locale;

public class AlterarTarefaActivity extends AppCompatActivity {

    private Tarefa tarefa;
    private Calendar dataHora = Calendar.getInstance();
    private EditText editarDescricao;
    private TextView txtAlterarHora;
    private TextView txtAlterarData;
    private Button btnAlterar;
    private SwitchCompat swtRealizado;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_tarefa);

        editarDescricao = findViewById(R.id.edt_editar_descricao);
        txtAlterarHora = findViewById(R.id.txt_editar_hora);
        txtAlterarData = findViewById(R.id.txt_editar_data);
        btnAlterar = findViewById(R.id.btn_alterar);
        swtRealizado = findViewById(R.id.switch_realizado);


        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        int id = args.getInt("id_tarefa");
        tarefa = AppDatabase.getAppDatabase(this).tarefaDAO().listarUm(id);

        editarDescricao.setText(tarefa.getDescricao());

        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        txtAlterarData.setText(formatData.format(tarefa.getDataHora()));

        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm", Locale.US);
        txtAlterarHora.setText(formatHora.format(tarefa.getDataHora()));

        swtRealizado.setChecked(tarefa.isRealizado());

        txtAlterarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AlterarTarefaActivity.this, dateDialog, dataHora.get(Calendar.YEAR),
                        dataHora.get(Calendar.MONTH), dataHora.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txtAlterarHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(AlterarTarefaActivity.this, timeDialog, dataHora.get(Calendar.HOUR_OF_DAY),
                        dataHora.get(Calendar.MINUTE), true).show();
            }
        });

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tarefa.setDescricao(editarDescricao.getText().toString().trim());
                tarefa.setDataHora(dataHora.getTimeInMillis());
                tarefa.setRealizado(swtRealizado.isChecked());
                AppDatabase.getAppDatabase(AlterarTarefaActivity.this).tarefaDAO().alterar(tarefa);
                Intent intent = new Intent();
                intent.putExtra("resposta", "ok");
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

    DatePickerDialog.OnDateSetListener dateDialog = new DatePickerDialog.OnDateSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            dataHora.set(Calendar.YEAR, year);
            dataHora.set(Calendar.MONTH, month);
            dataHora.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat formatHora = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            txtAlterarData.setText(formatHora.format(dataHora.getTime()));
        }
    };

    TimePickerDialog.OnTimeSetListener timeDialog = new TimePickerDialog.OnTimeSetListener() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            dataHora.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dataHora.set(Calendar.MINUTE, minute);
            SimpleDateFormat formatacao = new SimpleDateFormat("HH:mm", Locale.US);
            txtAlterarHora.setText(formatacao.format(dataHora.getTime()));
        }
    };

}