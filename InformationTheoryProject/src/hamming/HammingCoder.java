package hamming;
import java.util.Arrays;


public class HammingCoder {

	
	public static byte[] hamming_7_4_Code(byte[] p) {
		
		if(p.length!=4)
			return null;
		
		byte[][] GT = {
				{1,1,1,0,0,0,0},
				{1,0,0,1,1,0,0},
				{0,1,0,1,0,1,0},
				{1,1,0,1,0,0,1}};
						
		byte [] res = new byte[7];
		for (int j = 0; j < GT[0].length; j++) {
			for (int i = 0; i < p.length; i++) {
				res[j] = xor((byte)(GT[i][j]*p[i]),res[j]);
			}
		
		}
		return res;
		
	}
	
	private static byte xor(byte b1, byte b2) {
		return b1==b2? (byte)0 : (byte)1;
	}
	
	public static byte[] hamming_12_8_Code(byte []p) {
		if(p.length!=8)
			return null;
		
		byte[][] GT = {{1,1,1,0,0,0,0,0,0,0,0,0},
					   {1,0,0,1,1,0,0,0,0,0,0,0},
					   {0,1,0,1,0,1,0,0,0,0,0,0},
					   {1,1,0,1,0,0,1,0,0,0,0,0},
					   {1,0,0,0,0,0,0,1,1,0,0,0},
					   {0,1,0,0,0,0,0,1,0,0,1,0},
					   {1,1,0,0,0,0,0,1,0,0,1,0},
					   {0,0,0,1,0,0,0,1,0,0,0,1}
						 };
						
		byte [] res = new byte[12];
		for (int j = 0; j < GT[0].length; j++) {
			for (int i = 0; i < p.length; i++) {
				res[j] = xor((byte)(GT[i][j]*p[i]),res[j]);
			}
		
		}
		return res;
	}
	
	public static void main(String[]args) {
		System.out.println(Arrays.toString(hamming_12_8_Code(new byte[]{1,0,0,1,0,0,1,1})));
	}
	
}
