package request;

import android.content.Context;
import android.widget.Toast;

import java.io.*;

import model.Usuario;

public class ApiClient {
    private Context context;

   /* public File crearCarpeta(Context con, String nombre){
        File ruta=new File(con.getFilesDir(),nombre);
        if(!ruta.exists()){
            ruta.mkdir();
        }
        return ruta;
    }*/

    public static void guardar(Context context, Usuario usuario){

        File archivo=new File(context.getFilesDir(),"datos.dat");
        try {
            FileOutputStream fo=new FileOutputStream(archivo);
            BufferedOutputStream bo=new BufferedOutputStream(fo);
            DataOutputStream dos=new DataOutputStream(bo);
            if(usuario != null){
                dos.writeLong(usuario.getDni());
                dos.writeUTF(usuario.getNombre());
                dos.writeUTF(usuario.getApellido());
                dos.writeUTF(usuario.getEmail());
                dos.writeUTF(usuario.getPass());
            }

            bo.flush();
            fo.close();
        }catch (IOException e){

            Toast.makeText(context,"Error al tratar de conectarse al archivo",Toast.LENGTH_LONG).show();
        }
    }
    public static Usuario leer(Context context) {
        File archivo = new File(context.getFilesDir(), "datos.dat");
        Usuario usuario = null;
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream dis = new DataInputStream(bi);

            Long dni;
            String nombre = null;
            String apellido = null;
            String email = null;
            String pass = null;

            if ((dni = dis.readLong()) != -1) {
                nombre = dis.readUTF();
                apellido = dis.readUTF();
                email = dis.readUTF();
                pass = dis.readUTF();
                usuario = new Usuario(dni, nombre, apellido, email, pass);
            }
            fi.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error al tratar de conectarse al archivo", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }
    public static Usuario login(Context context,String mail,String passw){
        Usuario usuario=null;
        File archivo = new File(context.getFilesDir(), "datos.dat");
        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            DataInputStream dis = new DataInputStream(bi);
            Long dni ;
            String nombre = null;
            String apellido = null;
            String email = null;
            String pass = null;
            if((dni = dis.readLong()) != -1) {
                nombre = dis.readUTF();
                apellido = dis.readUTF();
                email = dis.readUTF();
                pass = dis.readUTF();
                usuario = new Usuario(dni, nombre, apellido, email, pass);
                if (usuario.getEmail().equals(mail) && usuario.getPass().equals(passw)) {

                }
            }
            fi.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error al tratar de conectarse al archivo", Toast.LENGTH_LONG).show();
        }
        return usuario;
    }

}
