import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Hashtable;

public class Text {

	//65 - 90: A - Z ; 97 - 122 : a - z
	public static Hashtable<String, Integer> fileToString(String fileName) throws IOException {
		Hashtable<String, Integer> words = new Hashtable<String, Integer>();
		File file = new File (fileName);
		FileReader fr = new FileReader (file);
		Scanner sc = new Scanner(fr);
		while (sc.hasNext()) {
			String key = sc.next().toLowerCase();
			if(words.containsKey(key))
				words.put(key, words.get(key) + 1);
			else
				words.put(key, 1);
		}
		fr.close();
		sc.close();
		return words;
	}

	public static Hashtable<String, Integer> createWords(String str) {
		Hashtable<String, Integer> words = new Hashtable<String, Integer>();
		for(int index = 0; index < str.length(); index++) {
			char chr = str.charAt(index);
			if(chr >= 65 && chr <= 90 || chr >= 97 && chr <= 122 || chr == ' ') {

			}	
			else {
				String temp = str.substring(0, index);
				if(index != str.length() - 1) {
					str = temp + str.substring(index + 1);
				}
				if (index == str.length()-1) {
					str = str.substring(0, str.length()-1);
				} else {
					index--;
				}
			}
		}
		//int index = str.length() - 1;
		int index2 = 0;
		int index3 = 0;
		while (index2 != str.length()) {
			if (str.charAt(index2) == ' ') {
				String key = str.substring(index3, index2).toLowerCase();
				index3 = index2+1;
				if (words.containsKey(key)) {
					words.put(key, words.get(key) + 1);
				} else {
					words.put(key, 1);
				}
			}
			index2++;
		}
		String key = str.substring(index3);
		if (words.containsKey(key)) {
			words.put(key, words.get(key) + 1);
		} else {
			words.put(key, 1);
		}
		return words;
	}

	public static String printHashTable(Hashtable<String, Integer> words) {
		String str = "";
		for(String key : words.keySet()) {
			str = str + key + ": " + words.get(key) + "\n";
		}
		return str;
	}

	public static String[] getFreqArray(Hashtable<String, Integer> words) throws IOException {
		Hashtable <String, Integer> commonWords = new Hashtable <String, Integer>();
		//BufferedReader br = new BufferedReader(new FileReader (new File("commonWords.txt")));
		Scanner scan = new Scanner (new File("commonWords.txt"));
		while (scan.hasNext()) { 
			commonWords.put(scan.next(), 0);
		}
		scan.close();
		

		String [] maxFreqWords = new String[5];

		int counter3 = 0;
		while (counter3 < 5) {
			int maxFreq = 0;
			String maxKey = "";
			//Set<String> keys = words.keySet();
			Enumeration<String> keys = words.keys();
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				if (words.get(key) > maxFreq && !commonWords.containsKey(key)) {
					maxFreq = words.get(key);
					maxKey = key;
					break;
				}
			}
			words.put (maxKey, 0);
			maxFreqWords[counter3] = maxKey;
			counter3++;
		}
		return maxFreqWords;
	}
}
