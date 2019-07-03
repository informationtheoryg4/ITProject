package utils;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
//Classe con metodi statici di utilita'
public class Util {
	
	//trasforma un stringa di testo in un array di bit
	public static String textToBinary(String msg) {
		  byte[] bytes = msg.getBytes();
		  return byteArrayToBinary(bytes);
	}
	
	public static String binaryToText(String binary) {
		String msg;
		byte [] bytes = binaryToByteArray(binary);
		msg = new String(bytes);
		return msg;
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
//        try {
//           
//            //create an print writer for writing to a file
//            PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\Giova\\Desktop\\output.txt"));
//            out.println(sb);
//            //close the file (VERY IMPORTANT!)
//            out.close();
//         }
//            catch(IOException e1) {
//              System.out.println("Error during reading/writing");
//         }
//      
//        byte [] b=sb.toString().getBytes();
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
	
	public static void byteArrayToImageFile(byte []input, String outPath) {
		 ByteArrayInputStream bis = new ByteArrayInputStream(input);
	      BufferedImage bImage2=null;
		try {
			bImage2 = ImageIO.read(bis);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ImageIO.write(bImage2, "jpg", new File(outPath) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String byteArrayToBinary(byte[] byteArray) {
		StringBuilder sb = new StringBuilder();
		for (byte b : byteArray){
		     int val = b;
		     for (int i = 0; i < 8; i++){
		        sb.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		}
        return sb.toString();
	}
	
	public static byte[] binaryToByteArray(String binary) {
		byte [] byteArray = new byte[binary.length()/8];
		for (int i = 0; i < byteArray.length; i++) {
			byteArray[i] = 0;
		}
		int index;
		for(int i = 0; i<binary.length(); i+=8) {
			for (int j = 0; j < 8; j++) {
				index = i+j;
				if(binary.charAt(index)=='1')
					byteArray[i/8]+=(byte)Math.pow(2, 7-j);				
			}
		}
		return byteArray;
	}
	
	public static String textFileToString(File f) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String text;
			while((text = br.readLine())!=null) {
				sb.append(text+"\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/*
	 * da implementare metodo per passare da file di testo ad array di byte
	 */

	public static void main(String[] args) {
		byte [] bytes=imageToByteArray("luna.jpg");
		String binary = byteArrayToBinary(bytes);
		System.out.println(binary.length());
		System.out.println("Array di bytes creato:\n");
		System.out.println(bytes[3]);
		BufferedImage bi= byteArrayToImage(binaryToByteArray(binary));
		try {
			ImageIO.write(bi, "jpg", new File("output.jpg") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		byte [] b = new byte[] {27, 4};  PORCO DIOOOOOO
//		String binary = byteArrayToBinary(b);
//		byte[] ba = binaryToByteArray(binary);
//		System.out.println(binary);
//		System.out.println(ba[0]+" "+ba[1]);
	}

}
