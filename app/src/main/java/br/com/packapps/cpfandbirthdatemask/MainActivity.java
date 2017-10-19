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

public class MainActivity extends AppCompatActivity {

    private EditText etCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etCpf = (EditText) findViewById(R.id.etCpf);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "CPF: "+ etCpf.getText().toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //startActivity(new Intent(MainActivity.this, SecondExampleMaskActivity.class));
            }
        });


        //mask CPF
        setMaskCpf("###.###.###-##");


    }

    private void setMaskCpf(String maskCpf) {
        etCpf.addTextChangedListener(Mask.insert(maskCpf, etCpf));
    }
}
