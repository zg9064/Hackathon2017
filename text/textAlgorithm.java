import java.util.ArrayList;
import java.util.scanner;
import java.io.file;
public class textAlgorithm {
  public static void main(String[] args) {
    private static String[] uselessWords = {"he", "her", "I", "you", "them", "us", "we", "it", "to", "a", "and", "the", "of", "at", "from", "in", "on", "my", "your", "his", "its", "our", "their", "one's", "mine", "yours", "hers", "ours", "theirs", "she", "they", "one", "with"};
    
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
    
    public static void removeWords(string stringName){
      for(int i = 0; i<uselessWords.length; i++)
      {
        if (stringName.contains(uselessWords[i] + " ")
            {
              stringName.replace(uselessWords[i] + " ", "")
            }
      }
      }
  }
}
