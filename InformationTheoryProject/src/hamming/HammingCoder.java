package hamming;
import java.util.Arrays;


public class HammingCoder {
	
	public static byte[] encode(byte[] p, HammingType type) {
		StringBuilder sb = new StringBuilder();
		
		int i;
		switch (type) {
		case H_7_4:
			for (i = 0; i < p.length; i+=4) {
				sb.append(hamming_7_4_Code(Arrays.copyOfRange(p, i, i+4))); //DA CORREGGERE
			}
			sb.append(hamming_gen(Arrays.copyOfRange(p, i-4, p.length)));
			break;
		case H_12_8:
			for (i = 0; i < p.length; i+=8) {
				sb.append(hamming_12_8_Code(Arrays.copyOfRange(p, i, i+8))); //DA CORREGGERE
			}
			sb.append(hamming_gen(Arrays.copyOfRange(p, i-4, p.length)));
			break;
		default:
			return hamming_gen(p);
		}
		String res = sb.toString();
		byte [] resByte = new byte [res.length()];
		for (int j = 0; j < resByte.length; j++) {
			resByte[j] = (byte)Character.getNumericValue(res.charAt(j));
		}
		System.out.println(Arrays.toString(resByte));
		return resByte;
	}
	
	private static byte[] hamming_gen(byte[] p) {
		// TODO Auto-generated method stub
		return null;
	}

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
		
		byte[][] GT = {
					   {1,1,1,0,0,0,0,0,0,0,0,0},
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
		byte [] r= encode(new byte[]{0,1,0,0,1,1,0,0},HammingType.H_7_4);
		
	}
	
}
