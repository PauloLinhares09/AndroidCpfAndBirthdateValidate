package br.com.packapps.cpfandbirthdatemask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.packapps.cpfandbirthdatemask.Util.DateUtil;

public class MainActivity extends AppCompatActivity {

    private EditText etCpf;
    private EditText etBirthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCpf = (EditText) findViewById(R.id.etCpf);
        etBirthdate = (EditText) findViewById(R.id.etBirthdate);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String birthdate = etBirthdate.getText().toString();
                if (DateUtil.valideBirthdate(birthdate)) {
                    StringBuilder birthdateSql = convertToDateSql(birthdate);

                    Snackbar.make(view, "Birthdate: " + birthdateSql.toString() + " is valid", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else
                    etBirthdate.setError("Verifique a Data de Nascimento ");

                //startActivity(new Intent(MainActivity.this, SecondExampleMaskActivity.class));
            }
        });


        //mask CPF
        etCpf.addTextChangedListener(Mask.insert("###.###.###-##", etCpf));
        etBirthdate.addTextChangedListener(Mask.insert("##/##/####", etBirthdate));


    }

    @NonNull
    private StringBuilder convertToDateSql(String birthdate) {
        String birthdateArray[] = birthdate.split("/");
        StringBuilder birthdateSql = new StringBuilder();
        birthdateSql.append(birthdateArray[2]);
        birthdateSql.append("-");
        birthdateSql.append(birthdateArray[1]);
        birthdateSql.append("-");
        birthdateSql.append(birthdateArray[0]);
        return birthdateSql;
    }

}
