package ui.registro;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import model.Usuario;
import request.ApiClient;

public class RegistroActivityViewModel extends ViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;


    public LiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData == null){
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public  void cargarDatos(Context context){
        Usuario usuario= ApiClient.leer(context);
        if(usuario !=null){
            usuarioMutableLiveData.setValue(usuario);
        }
       // usuarioMutableLiveData.setValue(usuario);
    }


    public void guardarDatos(Context context, Usuario usuario){
        if(usuario!=null){
            ApiClient.guardar(context,usuario);
        }

    }
}
