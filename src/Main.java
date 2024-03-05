import java.io.*;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Main {
    public void main(String args[]) throws Exception {

        Scanner scan = new Scanner(System.in);
        String filename;
        int[] file;
        byte[] keyArray;
        byte[] encryptedFile;
        String key;



        System.out.println("Gib den Filenamen an: ");
        filename = scan.nextLine();
        file = readFile(filename);


        System.out.println("Bitte gib den Schlüssel ein: ");
        key = scan.nextLine();
        System.out.println();
        keyArray = new byte[key.getBytes().length];
        for (int i = 0; i < key.getBytes().length; i++) {
            keyArray[i] = key.getBytes()[i];
        }
        RC4 rc4 = new RC4(keyArray);


        int[] cipher = rc4.encrypt(file);
        System.out.print("eingegebener Key: ");
        for (int i = 0; i < cipher.length; i++) {
            System.out.print(cipher[i]);
        }

        System.out.println();
        System.out.println();



        FileUtils.writeByteArrayToFile(new File("G:\\Meine Ablage\\HTL\\2. Klasse\\ITS\\RC4-Verschluesselung\\Files"), writeInts(cipher));

    }
    public int[] readFile(String fileName) throws IOException {
        File myFile = new File(fileName);
        byte[] byteArray = FileUtils.readFileToByteArray(myFile);
        int[] intArray = new int[byteArray.length];

        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int) byteArray[i];
        }

        return intArray;
    }

    private static byte [] writeInts(int [] array) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(array.length * 4);
            DataOutputStream dos = new DataOutputStream(bos);
            for (int i = 0; i < array.length; i++) {
                dos.writeInt(array[i]);
            }

            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}