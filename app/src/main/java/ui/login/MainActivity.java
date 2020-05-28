package ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.logindatafileapplication.R;

import ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {
private EditText etEmail,etPass;
private Button btLogin,btRegistro;
private TextView tvCartel;
private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        vm.getCartelMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvCartel.setText(s);
            }
        });
    }
    public void inicializar(){
        etEmail=findViewById(R.id.etEmailLogin);
        etPass=findViewById(R.id.etpassLogin);
        tvCartel=findViewById(R.id.tvCartelLogin);
        btLogin=findViewById(R.id.btLogin);
        btRegistro=findViewById(R.id.btRegistro);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.validarDatos(getApplicationContext(),etEmail.getText().toString(),etPass.getText().toString());
            }
        });
        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vm.registrarDatos(getApplicationContext());

            }
        });
    }
}
