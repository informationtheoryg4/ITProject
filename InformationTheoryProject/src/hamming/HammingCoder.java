package hamming;
import java.util.Arrays;

import utils.Util;


public class HammingCoder {
	
	public static String encode(String p, HammingType type) {
		StringBuilder sb = new StringBuilder();
		
		int i;
		switch (type) {
		case H_7_4:
			sb.append("0");
			for (i = 0; i+4 <= p.length(); i+=4) {
				sb.append(hamming_7_4_Code(p.substring(i, i+4))); //corretto
			}
			if(p.length()%4!=0) sb.append(hamming_gen(p.substring(i-4)));
			break;
		case H_12_8:
			sb.append("1");
			for (i = 0; i+8 <= p.length(); i+=8) {
				sb.append(hamming_12_8_Code(p.substring(i, i+8))); //corretto
			}
			if(p.length()%8!=0)  sb.append(hamming_gen(p.substring(i-8)));
			break;
		default:
			sb.append("x");
			return hamming_gen(p);
		}
		return sb.toString();
		
	}
	
	private static String hamming_gen(String p) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String hamming_7_4_Code(String p) {
//		System.out.println(p);
		if(p.length()!=4)
			return null;
		
		String[] GT = {
				"1110000",
				"1001100",
				"0101010",
				"1101001"};
		String tmp = "0000000";
		char [] res = tmp.toCharArray();
		for (int j = 0; j < GT[0].length(); j++) {
			for (int i = 0; i < p.length(); i++) {
				res[j] = xor((Character.getNumericValue(GT[i].charAt(j))*Character.getNumericValue(p.charAt(i))),Character.getNumericValue(res[j]));
			}
		
		}
		return String.valueOf(res);
		
//		byte[][] GT = {
//				{1,1,1,0,0,0,0},
//				{1,0,0,1,1,0,0},
//				{0,1,0,1,0,1,0},
//				{1,1,0,1,0,0,1}};
//						
//		byte [] res = new byte[7];
//		for (int j = 0; j < GT[0].length; j++) {
//			for (int i = 0; i < p.length; i++) {
//				res[j] = xor((byte)(GT[i][j]*p[i]),res[j]);
//			}		
//		}
		
		
	}
	
	private static char xor(int b1, int b2) {
		return b1==b2? '0' : '1';
	}
	
	public static String hamming_12_8_Code(String p) {
		
		if(p.length()!=8)
			return null;
		
		String[] GT = {
				"111000000000",
				"100110000000",
				"010101000000",
				"110100100000",
				"100000011000",
				"010000010010",
				"110000010010",
				"000100010001"
				};
		String tmp = "000000000000";
		char [] res = tmp.toCharArray();
		for (int j = 0; j < GT[0].length(); j++) {
			for (int i = 0; i < p.length(); i++) {
				res[j] = xor((Character.getNumericValue(GT[i].charAt(j))*Character.getNumericValue(p.charAt(i))),Character.getNumericValue(res[j]));
			}
		
		}
		return String.valueOf(res);
		
//		if(p.length!=8)
//			return null;
//		
//		byte[][] GT = {
//					   {1,1,1,0,0,0,0,0,0,0,0,0},
//					   {1,0,0,1,1,0,0,0,0,0,0,0},
//					   {0,1,0,1,0,1,0,0,0,0,0,0},
//					   {1,1,0,1,0,0,1,0,0,0,0,0},
//					   {1,0,0,0,0,0,0,1,1,0,0,0},
//					   {0,1,0,0,0,0,0,1,0,0,1,0},
//					   {1,1,0,0,0,0,0,1,0,0,1,0},
//					   {0,0,0,1,0,0,0,1,0,0,0,1}
//						 };
		
		
//						
//		byte [] res = new byte[12];
//		for (int j = 0; j < GT[0].length; j++) {
//			for (int i = 0; i < p.length; i++) {
//				res[j] = xor((byte)(GT[i][j]*p[i]),res[j]);
//			}
//		
//		}
//		return res;
	}
	
	public static void main(String[]args) {
		System.out.println(encode("1001", HammingType.H_7_4));
//		System.out.println(hamming_7_4_Code("0010"));
//		System.out.println(hamming_7_4_Code("0101"));
	}
	
}
