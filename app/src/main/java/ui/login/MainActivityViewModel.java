package ui.login;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import model.Usuario;
import request.ApiClient;
import ui.registro.RegistroActivity;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<String> cartelMutableLiveData;

    public LiveData<String> getCartelMutableLiveData(){
        if(cartelMutableLiveData==null){
            cartelMutableLiveData=new MutableLiveData<>();
        }
        return cartelMutableLiveData;
    }

    public void validarDatos(Context context, String email, String pass){
        Usuario usuario=new Usuario();
        usuario= ApiClient.login(context,email,pass);
        if(usuario != null){
            Intent intent= new Intent(context, RegistroActivity.class);
            intent.putExtra("registro","lleno");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            cartelMutableLiveData.setValue("Email o Contraseña Incorrectos");
        }

    }
    public  void registrarDatos(Context context){
        //borrar datos del archivo
        Intent intent= new Intent(context, RegistroActivity.class);
        intent.putExtra("registro","vacio");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void DatosInicio(Context context,Usuario usuario){
        ApiClient.guardar(context,usuario);
    }
}
