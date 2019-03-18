package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.imageio.ImageIO;
//Classe con metodi statici di utilita'
public class Util {
	
	//trasforma un stringa di testo in un array di bit
	public static byte [] textToBinary(String msg) {
		  byte[] bytes = msg.getBytes();
		  byte[]bits;
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		  }
		  System.out.println(binary.toString());
		  bits = new byte[binary.length()];
		  for(int i=0; i< bits.length; i++)
	        	bits[i]= (byte)Character.getNumericValue(binary.charAt(i));
	        return bits;
	}
	
	//trasforma un'immagine jpg in un array di bit
	public static byte[] ImagetoBinary(String filePath) {
		BufferedImage img;
		 ByteArrayOutputStream b = new ByteArrayOutputStream();
		try {
			img = ImageIO.read(new File(filePath));
			 ImageIO.write(img, "jpg", b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // write image to byte array in-memory (jpg format)

        byte[] jpgByteArray = b.toByteArray();
        StringBuilder sb = new StringBuilder();
        for (byte by : jpgByteArray) {
            sb.append(Integer.toBinaryString(by & 0xFF));
        }
        try {
           
            //create an print writer for writing to a file
            PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Giova\\Desktop\\output.txt"));
            out.println(sb);
            //close the file (VERY IMPORTANT!)
            out.close();
         }
            catch(IOException e1) {
              System.out.println("Error during reading/writing");
         }
        byte [] bits= new byte[sb.length()];
        for(int i=0; i< bits.length; i++)
        	bits[i]= (byte)Character.getNumericValue(sb.charAt(i));
        return bits;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(textToBinary("1000")));
	}

}
