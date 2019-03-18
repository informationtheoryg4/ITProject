package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;
//Classe con metodi statici di utilita'
public class Util {
	
	//trasforma un stringa di testo in un array di bit
	public static String textToBinary(String msg) {
		  byte[] bytes = msg.getBytes();
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
	        return binary.toString();
	}
	
	//trasforma un'immagine jpg in un array di bit
	public static String imageToBinary(String filePath) {
		
        byte[] jpgByteArray = null;
		try {
			jpgByteArray = Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
      
        return sb.toString();
	}

	public static void main(String[] args) {
		imageToBinary("luna.jpg");
	}

}
