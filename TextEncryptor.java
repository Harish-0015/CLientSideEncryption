import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.event.InternalFrameAdapter;

class TextEncryptor {
    
    public static void main(String[] args) {

        String plaintext = ReadTextFile();
        System.out.println(plaintext);

        String[] result = encryptText(plaintext);
        String encText = result[0];
        String ciphertext = result[1];
        String encryptionKey = result[2];

        System.out.println("Enc text: " + encText);
        System.out.println("Cipher text: " + ciphertext);
        System.out.println("Encryption key: " + encryptionKey);

        writeTextFile(encText, ciphertext, encryptionKey);

        //String deplainText = decryptText(ciphertext, encryptionKey) ;
        //System.out.println(deplainText);
    }

    public static String ReadTextFile(){
        String fileName = "D:\\IIIT Kottayam\\research\\harish.txt"; // Replace with your file name

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                return line ;
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return null;
    }

    public static void writeTextFile(String encText, String cipherText, String encKey){
        String filename = "D:\\IIIT Kottayam\\research\\encrypted_data.txt";
        try (FileWriter writer = new FileWriter(filename); BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(encText);
                bw.newLine();
                bw.write(cipherText);
                bw.newLine();
                bw.write(encKey);
        }catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static String[] encryptText(String plainText) {
        StringBuilder encTextBuilder = new StringBuilder();
        StringBuilder cipherTextBuilder = new StringBuilder();
        StringBuilder encryptionKeyBuilder = new StringBuilder();

        int keyvalue = keyValue() ;

        for (char character : plainText.toCharArray()) {
            System.out.println(character);
            // Step 1: Read ASCII value for single character
            int asciiValue = (int) character;

            // Step 2: Convert ASCII to Binary
            String binaryValue = String.format("%8s", Integer.toBinaryString(asciiValue)).replace(' ', '0');
            System.out.println(binaryValue);

            // Step 3-6: Manipulate bits
            binaryValue = binaryValue.substring(4) + binaryValue.substring(0,4);
            System.out.println(binaryValue) ;
            binaryValue = binaryValue.substring(2,4) + binaryValue.substring(0, 2) + 
            binaryValue.substring(6) + binaryValue.substring(4,6);
            System.out.println(binaryValue) ;

            // Step 7-9: Manipulate bits
            binaryValue = new StringBuilder(binaryValue).reverse().toString();

            // Step 10: Convert Binary to ASCII value
            int encryptedValue = Integer.parseInt(binaryValue, 2);
            System.out.println(encryptedValue);

            // Step 11: Add keyValue
            int cypherTextValue = encryptedValue + keyvalue; // Just an example, you can use any key you want

            System.out.println(encryptedValue);

            // Construct ciphertext and encryption key
            cipherTextBuilder.append((char) cypherTextValue);
            encTextBuilder.append((char) encryptedValue);
            
        }

        encryptionKeyBuilder.append(keyvalue);
        System.out.println(keyvalue);
        String cipherText = cipherTextBuilder.toString();
        String encText = encTextBuilder.toString();
        String encryptionKey = encryptionKeyBuilder.toString();

        return new String[]{encText, cipherText, encryptionKey};
    }

    public static int keyValue(){
        String bucketName = "Amazonbucket" ;
        int value = 0;
        for (char character : bucketName.toCharArray()) {
            value += (int) character;
        }

        int keyValue = value % bucketName.length();
        System.out.println(keyValue);
        return keyValue;
    }

    /* 
    public static String decryptText(String cypherText, String key){
        System.out.println("Inside decrypt method");
        StringBuilder plainTextBuilder = new StringBuilder();
        System.out.println(cypherText);
        System.out.println(key);

        for (char character : cypherText.toCharArray()){
            // Step 1: Read ASCII value for single character
            int asciiValue = (int) character;
            asciiValue = asciiValue - (Integer.parseInt(key));
            //System.out.println(asciiValue);

            // Step 2: Convert ASCII to Binary
            String binaryValue = String.format("%8s", Integer.toBinaryString(asciiValue)).replace(' ', '0');
            //System.out.println(binaryValue);    

            // Step 3-6: Manipulate bits
            binaryValue = binaryValue.substring(2,4) + binaryValue.substring(0, 2) + 
            binaryValue.substring(6) + binaryValue.substring(4,6);
            //System.out.println(binaryValue) ;

            binaryValue = binaryValue.substring(4) + binaryValue.substring(0,4);
            //System.out.println(binaryValue) ;

            // Step 7-9: Manipulate bits
            binaryValue = new StringBuilder(binaryValue).toString();
            //System.out.println(binaryValue);

            int deplain = Integer.parseInt(binaryValue, 2);

            plainTextBuilder.append((char) deplain);
        }

        System.out.println("Outside decrypt method");
        return plainTextBuilder.toString() ;
    }
    */
}
