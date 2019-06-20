package circularlinkedlist;
public class Encryption {


    public static char encryptChar(char plain, int key){
        int letterToNum = plain - 'a';
        int encryptedInt = (letterToNum + key) % 26;
        char encryptedLetter  = (char) (encryptedInt + 'a');
        return encryptedLetter ;


    }


    public static char decryptChar(char cipher, int key) {
        int cipherNum =  cipher - 'a';
        int decryptedInt = (cipherNum - key) % 26;
        if(decryptedInt < 0){
            decryptedInt+=26;
        }

        char plainLetter = (char) (decryptedInt + 'a');
        return plainLetter;
    }


    public static void main(String[] args) {
        System.out.println(decryptChar('c',3));
    }
}
