/** Functions for checking if a given string is an anagram. */
import java.util.Random;

public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String newStr1 = preProcess(str1);
		String newStr2 = preProcess(str2);
		String abc = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < abc.length(); i++) {
			int count1 = 0;
			int count2 = 0;
			for (int j = 0; j < newStr1.length(); j++) {
				if (newStr1.charAt(j) == abc.charAt(i)) {
					count1++;
				}
			}
			for (int j = 0; j < newStr2.length(); j++) {
				if (newStr2.charAt(j) == abc.charAt(i)) {
					count2++;
				}
			}
			if (count1 != count2) {
				return false;
			}
		}
		return true;
	}

	public static boolean isLower(char ch){
		if (ch == ' ') {
			return true;
		}
		return ch >= 'a' && ch <= 'z';
	}

	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		// Replace the following statement with your code
		String tempStr = str.toLowerCase();
		String newStr = "";
		for (int i = 0; i < tempStr.length(); i++) {
			if (isLower(tempStr.charAt(i))) {
				newStr += tempStr.charAt(i);
			}
		}
		return newStr;
	} 

	public static String removeChar(int index,String str){
		String newStr = "";
		for (int i = 0; i < str.length(); i++) {
			if (i != index) {
				newStr += str.charAt(i);
			}
		}
		return newStr;
	}

	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		Random rand = new Random();
		String randAnagram = "";
		while (str != "") {
			int num1 = rand.nextInt(str.length());
			randAnagram += str.charAt(num1);
			str = removeChar(num1,str);	
		}
		return randAnagram;
	}
}
