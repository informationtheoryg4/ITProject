package hamming;

public class HammingDecoder {
	
	public static String decode(String c) {
		HammingType type = HammingType.H_GEN;
		if (c.charAt(0)=='0')
			type = HammingType.H_7_4;
		else if (c.charAt(0)=='1')
			type = HammingType.H_12_8;
		StringBuilder sb = new StringBuilder();
		
		int i;
		switch (type) {
		case H_7_4:
			for (i = 1; i+7 <= c.length(); i+=7) {
				sb.append(hamming_7_4_Decode(c.substring(i, i+7)));
			}
			break;
		case H_12_8:
			for (i = 1; i+12 <= c.length(); i+=12) {
				sb.append(hamming_12_8_Decode(c.substring(i, i+12)));
			}
			break;
		default:
			return hamming_gen_decode(c);
		}
		return sb.toString();
		
	}

	private static String hamming_gen_decode(String c) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String hamming_12_8_Decode(String c) {
		return c.charAt(2)+c.substring(4,7)+c.substring(8);
	}

	private static String hamming_7_4_Decode(String c) {
		return c.charAt(2)+c.substring(4);
	}
	
	public static void main(String[]args) {
		System.out.println(decode("00011001"));
	}

}
