package ui.registro;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import model.Usuario;
import request.ApiClient;
import ui.login.MainActivity;

public class RegistroActivityViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;


    public LiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData == null){
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public  void cargarDatos(Context context,String reg){

        if(reg.equals("vacio")){
            Usuario usuario=new Usuario();
            usuarioMutableLiveData.setValue(usuario);

        }
        if(reg.equals("lleno")){

            Usuario usuario1= ApiClient.leer(context);
            usuarioMutableLiveData.setValue(usuario1);
        }

    }


    public void guardarDatos(Context context, Usuario usuario){
        if(usuario!=null){
            ApiClient.guardar(context,usuario);
            Intent intent=new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }
}
