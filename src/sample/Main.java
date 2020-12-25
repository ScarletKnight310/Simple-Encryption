package sample;

import base.AntiCrypter;

public class Main{

 public static void main(String[] args) {
        System.out.println((char)('c'+ 10));
        AntiCrypter test = new AntiCrypter(new String[]{"my dog lives", "I spread the word"}, new int[]{10});
        System.out.println("");
        printIt(test);

        System.out.println("\n");
        test.Crypt();
        printIt(test);

        System.out.println("\n");
        test.encryptThis = false;
        test.Crypt();
        printIt(test);
    }

    private static void printIt(AntiCrypter test){
        for(String s: test.getText()){
            System.out.println(s);
        }
    }
}
