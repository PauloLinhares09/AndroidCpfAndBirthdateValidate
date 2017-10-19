package br.com.packapps.cpfandbirthdatemask;

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
            }
        });


        //mask CPF
        setMaskCpf();


    }

    private void setMaskCpf() {
        etCpf.addTextChangedListener(new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String withMask = includeMaskCpf(s.toString(), count);
                if (isUpdating){
                    old = withMask;
                    isUpdating = false;
                }else{
                    isUpdating = true;
                    etCpf.setText(withMask);
                    etCpf.setSelection(withMask.length());
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private String includeMaskCpf(String s, int count) {
        switch (s.length()){
            case 3:
                if (count == 0)//necessary in case of deleting
                    return s.substring(0, 3);
                else
                    return s + ".";
            case 7:
                if (count == 0)//necessary in case of deleting
                    return s.substring(0, 7);
                else
                    return s + ".";
            case 11:
                if (count == 0)//necessary in case of deleting
                    return s.substring(0, 11);
                else
                    return s + "-";

            //### For cases DELETING ###

            case 12://necessary for case to be deleted string after of the character "-" and go back continue digit
                if (count == 0)//necessary in case of deleting
                    return s.substring(0, 11);

                if (!s.contains("-"))
                    return s.substring(0,11) + "-"+ s.substring(11,12);//deleting character "-" and conti

            case 8://necessary for case to be deleted string after of the character "-" and go back continue digit
                if (count == 0)//deleting
                    return s.substring(0, 7);

                if (!s.substring(5,8).contains("."))
                    return s.substring(0,7) + "."+ s.substring(7,8);//deleting character "." and conti
        }

        return s;
    }

}
