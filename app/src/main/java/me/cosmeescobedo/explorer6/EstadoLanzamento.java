package me.cosmeescobedo.explorer6;

import org.json.JSONException;
import org.json.JSONObject;

public class EstadoLanzamento {
    public static String tiempoRestante;
    public static String etapaActual;
    public static void init(JSONObject estado) throws JSONException {
        tiempoRestante = estado.getString("tiempo_restante");
        etapaActual = estado.getString("etapa_actual");
    }

}
