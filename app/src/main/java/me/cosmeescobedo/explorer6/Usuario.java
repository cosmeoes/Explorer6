package me.cosmeescobedo.explorer6;

import org.json.JSONException;
import org.json.JSONObject;

public class Usuario {
    public static int id;
    public static String nombre;
    public static String pass;
    public static String email;
    public static String imagen;
    public static String puesto;
    public static String departamento;

    public static void init(JSONObject usuario) throws JSONException {
        id = usuario.getInt("Id");
        nombre = usuario.getString("Nombre");
        pass = usuario.getString("Password");
        email = usuario.getString("Email");
        imagen = usuario.getString("Imagen");
        puesto = usuario.getString("Puesto");
        departamento = usuario.getString("Departamento");

    }

}
