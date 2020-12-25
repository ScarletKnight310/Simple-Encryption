package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AntiCrypter {

    private String[] text;
    private String[] text_Result;
    private int[] key; // Master Password(s)
    //HashMap<Character, Integer> keyInstructions = new HashMap<>();

    private String masterKey = ""; // Master Password in text form

    public boolean encryptThis;
    private String regex = "_";

    // Encrypt
    public AntiCrypter(String[] text, int[] keyContents){
        this.text = text;
        this.key = keyContents;
        this.encryptThis = true;
        setKey();
    }

    public AntiCrypter(String text, int[] keyContents){
        this(new String[]{text}, keyContents);
    }

    public AntiCrypter(String[] text, int keyContents){
        this(text, new int[]{keyContents});
    }

    public AntiCrypter(String text, int keyContents){
        this(new String[]{text}, new int[]{keyContents});
    }

    // Decrypt
    public AntiCrypter(String[] text, String masterKey){
        this.text = text;
        this.masterKey = masterKey;
        this.encryptThis = false;
        setKey();
    }

    public AntiCrypter(String text, String masterKey){
        this(new String[]{text}, masterKey);
    }

    // Main Crypt
    public void Crypt(){
        text_Result = new String[text.length];
        if(encryptThis) {
            for(int i = 0; i < key.length; i++){
                Crypt(key[i]);
            }
        }
        else{
            for(int i = 0; i < key.length; i++){
                Crypt(-key[i]);
            }
        }
        text = text_Result;
        text_Result = null;
    }

    private void Crypt(int keyCh){
        for(int i = 0; i < text.length; i++){ // lines
            if(text[i].isEmpty() || text[i].isBlank())
                continue;

            text_Result[i] = "";
            char[] line = text[i].toCharArray();

            for(char l: line){
                text_Result[i] += (char)(l + keyCh);
            }
        }
    }

    // Setup
    private void setKey(){
        if(encryptThis){
            CreateMasterKey();
        }
        else{
            CreateKeyList();
        }
    }

    private void CreateMasterKey(){
        for (int i = 0; i < key.length-1; i++) {
            String sign = key[i] < 0 ? "-" : "+";
            masterKey += sign + key[i] + regex;
        }

        String sign = key[key.length-1] >= 0 ? "+" : "-";
        masterKey += sign + Integer.toHexString(Math.abs(key[key.length-1]));
    }

    private void CreateKeyList(){
        String[] temp = masterKey.split(regex);
        key = new int[temp.length];

        for(int i = 0; i < key.length; i++){
            int sign = temp[i].charAt(0) == '-' ? -1 : 1;
            key[i] = sign * Integer.parseInt(temp[i].substring(1), 16);
        }
    }

    // Getters
    public String[] getText(){
        return text;
    }

    public String getMasterKey(){
        return masterKey;
    }

}
