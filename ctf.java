package com.example.ctf;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.os.Bundle;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class MainActivity extends AppCompatActivity {
    public static byte[] aes(byte[] paramArrayOfbyte1, byte[] paramArrayOfbyte2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(paramArrayOfbyte1, "AES/ECB/PKCS7Padding");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(paramArrayOfbyte2);
    }

    public static boolean verify(String paramString) {
        byte[] arrayOfByte = Base64.decode("5UJiFctbmgbDoLXmpL12mkno8HT4Lv8dlat8FxR2GOc=", 0);
        try {
            arrayOfByte = aes(hex("8d127684cbc37c17616d806cf50473cc"), arrayOfByte);

            Log.d("TAG", arrayOfByte.toString());
        } catch (Exception exception) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AES error:");
            stringBuilder.append(exception.getMessage());
            Log.d("CodeCheck", stringBuilder.toString());
            arrayOfByte = new byte[0];
        }
        return paramString.equals(new String(arrayOfByte));
    }

    public static byte[] hex(String paramString) {
        int i = paramString.length();
        byte[] arrayOfByte = new byte[i / 2];
        for (byte b = 0; b < i; b += 2)
            arrayOfByte[b / 2] = (byte)(byte)((Character.digit(paramString.charAt(b), 16) << 4) + Character.digit(paramString.charAt(b + 1), 16));
        return arrayOfByte;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verify("teststring");
    }
}
