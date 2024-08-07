import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;


public class encrypt {

    public static List<Object> encrypt(String plainText, int keyValue) throws IOException {
        List<Object> result = new ArrayList<>();
        StringBuilder cipherText = new StringBuilder();
        StringBuilder encText = new StringBuilder();         // nope, find a better name and explaination that encTexT
        StringBuilder encryptionKey = new StringBuilder();

        for (char character : plainText.toCharArray()) {
            // Get ASCII value, convert to binary, and handle leading zeros
            String binaryChar = Integer.toBinaryString(character).toString();
            binaryChar = String.format("%8s", binaryChar).replace(' ', '0');

            // Swap 4-bit chunks
            String swapped4Bit = binaryChar.substring(4) + binaryChar.substring(0, 4);

            // Swap 2-bit chunks within each 4-bit chunk
            String swapped2Bit = swapped4Bit.substring(2, 4) + swapped4Bit.substring(0, 2) +
                                swapped4Bit.substring(6, 8) + swapped4Bit.substring(4, 6);

            // Reverse the entire 8-bit value
            String reversedBinary = new StringBuilder(swapped2Bit).reverse().toString();

            
            // char encryptedChar = (char) (Integer.parseInt(reversedBinary, 2) + keyValue);
            char encryptedChar = (char) (Integer.parseInt(reversedBinary, 2));  // 129
            char encKeyChar = (char) (Integer.parseInt(reversedBinary, 2) + keyValue);  // Adding keys == 129 + keys 
            cipherText.append(encryptedChar);
            encText.append(encKeyChar);
           
            encryptionKey.append(keyValue); 
        }

        String filename = "./encrypted_data.txt";
        String file = "./new_enc.txt"; 
        try(FileWriter writer = new FileWriter(filename)){
            writer.write(cipherText.toString() + "\n");  // saving 129 cipher text
            System.out.println(encText);
            // writer.write(encText.toString() + "\n");  
            writer.write(encryptionKey.toString());  // saving the key
        }
        try(FileWriter writer = new FileWriter(file)){
            writer.write(encText.toString() + "\n");    // saving 132 enc text
         }

        result.add(cipherText.toString());  
        result.add(encText.toString());    // adding 132 in arrayList
        
        result.add(encryptionKey.toString());
        return result;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // statt time
        try {
            String plainText = "ABCDEF";
            int keyValue = 42;
    
            List<Object> encryptionResult = encrypt(plainText, keyValue);
            System.out.println("plain text: " + plainText);

            // long endTime = System.currentTimeMillis();

            // // Calculate execution time
            // long executionTime = endTime - startTime;
            // System.out.println("Execution time: " + executionTime + " milliseconds");


            System.out.println("Cipher text: " + encryptionResult.get(0));
            int len = encryptionResult.size();
            System.out.println("len" + len);
            // System.out.println("Enc text: " + encryptionResult.get(1));
            System.out.println("Debug: " + encryptionResult.get(1));
            System.out.println("Encryption key: " + encryptionResult.get(2));
            long endTime = System.currentTimeMillis();

            // Calculate execution time
            long executionTime = endTime - startTime;
            System.out.println("Execution time: " + executionTime + " milliseconds");
        } catch(IOException e){
            System.err.println("Error enc and writing to file:" + e.getMessage());
        }
    }
}

