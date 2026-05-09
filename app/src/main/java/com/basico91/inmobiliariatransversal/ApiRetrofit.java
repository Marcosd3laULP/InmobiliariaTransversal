package com.basico91.inmobiliariatransversal;

import android.content.Context;
import android.content.SharedPreferences;

public @interface ApiRetrofit {
    public class SharedPref {
        private static SharedPreferences sharedPreferences;

        private  static  SharedPreferences getSharedPreferences(Context context){
            if(sharedPreferences == null) {
                sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);

            }
            return sharedPreferences;
        }

        public  static  void guardarToken(Context context, String token) {
            SharedPreferences.Editor editor = getSharedPreferences(context).edit();
            editor.putString("token", token);
            editor.apply();
        }
        public static String obtenerToken(Context context) {
            return getSharedPreferences(context).getString("token","");
        }
    }
}
