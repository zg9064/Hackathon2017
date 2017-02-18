import java.util.ArrayList;
import java.util.scanner;
import java.io.file;
public class textAlgorithm {
  public static void main(String[] args) {
    private static String[] uselessWords = {"he", "her", "I", "you", "them", "us", "we", "it", "to", "a", "and", "the", "of", "at", "from", "in", "on", "my", "your", "his", "its", "our", "their", "one's", "mine", "yours", "hers", "ours", "theirs", "she", "they", "one"};
    
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
