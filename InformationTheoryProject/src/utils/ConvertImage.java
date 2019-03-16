package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/*
 * converte un'immagine jpg in una stringa di bit
 */
public class ConvertImage {
	
	
	public static String toBinary(String filePath) {
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
        return sb.toString();
	}
	

}


