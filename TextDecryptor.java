public class TextDecryptor {

    public static void main(String[] args) {
        String cipherText = "R$CdEb";
        String encryptionText = "115";

        String decryptedText = decryptText(cipherText, encryptionText);

        if (decryptedText != null) {
            System.out.println("Decrypted text: " + decryptedText);
        } else {    
            System.out.println("Decryption failed!");
        }
    }

    public static String decryptText(String cipherText, String encryptionText) {
        StringBuilder plainTextBuilder = new StringBuilder();

        // Convert ASCII values of cipher text and encryption text to integers
        int c = (int) cipherText.charAt(0);
        int e = Integer.parseInt(encryptionText);

        // Step 4: Subtract keyValue from e
        e -= 10; // Assuming the key value added during encryption was 10

        // Step 5: Check if e equals c
        if (e != c) {
            return null; // Stop decryption
        }

        // Step 7: Convert ASCI to Binary
        String binaryValue = String.format("%8s", Integer.toBinaryString(e)).replace(' ', '0');

        // Step 8: Reverse binary value
        binaryValue = new StringBuilder(binaryValue).reverse().toString();

        // Step 9-13: Manipulate binary chunks
        String chunk1 = binaryValue.substring(0, 4);
        String chunk2 = binaryValue.substring(4);
        String chunk3 = chunk1.substring(0, 2);
        String chunk4 = chunk1.substring(2);
        String chunk5 = chunk2.substring(0, 2);
        String chunk6 = chunk2.substring(2);
        String decryptedBinary = chunk3 + chunk5 + chunk4 + chunk6;

        // Step 14: Convert Binary into ASCI value
        int decryptedValue = Integer.parseInt(decryptedBinary, 2);

        // Convert ASCI value to character
        char decryptedChar = (char) decryptedValue;

        // Append character to plain text
        plainTextBuilder.append(decryptedChar);

        return plainTextBuilder.toString();
    }
}
