package query;


//this class is used to format output file
public class OutputFormat {
	public static String outputFormat(String input) {
		input = input.substring(1, input.length() - 1);
		input += ";";
		
		String temp = new String();
		int s= 0, e = 0;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == ';') {
				e = i;
				temp += outputSubstringFormat(input.substring(s, e));
				s = i + 1;
			}
		}
		
		temp += "\n";
		return temp.replaceAll("_", " ");
	}
	
	private static String outputSubstringFormat(String input) {
		String temp = new String();
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '=') {
				if(input.charAt(i+1) == '\"') {
					for(int j = i + 2; j < input.length(); j++) {
						if(input.charAt(j) == '\"')
							return input.substring(0, j).replaceAll("=", " = ").replaceAll("\"", "") + "\n";
					}
				}
				temp += input.substring(0,i+1);
				temp = temp.replaceAll("=", " = ");
			}
			else if(input.charAt(i) == ':') {
				temp += input.substring(i + 1, input.length());
				break;
			}
		}
		return temp + "\n";
	}
}
