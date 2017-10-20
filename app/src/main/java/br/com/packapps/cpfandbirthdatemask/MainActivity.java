package br.com.packapps.cpfandbirthdatemask;

import android.content.Intent;
import android.os.Bundle;
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

import br.com.packapps.cpfandbirthdatemask.util.CPFUtil;

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

                boolean valid = validateCpf();
                if (!valid)
                    etCpf.setError("Verifique o CPF!");

                Snackbar.make(view, "CPF: "+ etCpf.getText().toString() + " -> " +valid, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        //mask CPF
        etCpf.addTextChangedListener(Mask.insert("###.###.###-##", etCpf));
        //mask birthdate
        etBirthdate.addTextChangedListener(Mask.insert("##/##/####", etBirthdate));


    }

    private boolean validateCpf() {
        //Verify if has 11 digits
        String cpfClean = etCpf.getText().toString().replace(".", "");
        cpfClean = cpfClean.replace("-", "");
        if (cpfClean.length() != 11)
            return false;

        //Verify if is a number
        try{
            double number = Double.valueOf(cpfClean);
        }catch (Exception e){
            return false;
        }

        //finally verify if is a CPF valid
        return CPFUtil.validaCpf(cpfClean);
    }

}
