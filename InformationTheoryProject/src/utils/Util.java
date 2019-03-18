package utils;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;
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
      
        byte [] b=sb.toString().getBytes();
        return sb.toString();
       
	}
	
	public static byte[] imageToByteArray(String filePath) {
		 BufferedImage bImage;
		 ByteArrayOutputStream bos=null;
		try {
			bImage = ImageIO.read(new File(filePath));
			bos = new ByteArrayOutputStream();
		      ImageIO.write(bImage, "jpg", bos );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      byte [] data = bos.toByteArray();
	      return data;
	}
	
	public static BufferedImage byteArrayToImage(byte []input) {
		 ByteArrayInputStream bis = new ByteArrayInputStream(input);
	      BufferedImage bImage2=null;
		try {
			bImage2 = ImageIO.read(bis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("image created");
	      return bImage2;
	}
	
	/*
	 * da implementare metodo per passare da file di testo ad array di byte
	 */

	public static void main(String[] args) {
		byte [] bytes=imageToByteArray("luna.jpg");
		System.out.println("Array di bytes creato:\n");
		System.out.println(bytes[3]);
		BufferedImage bi= byteArrayToImage(bytes);
		try {
			ImageIO.write(bi, "jpg", new File("output.jpg") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
