import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.swing.text.Element;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.jsoup.*;
import org.jsoup.nodes.Node;
import org.w3c.dom.NodeList;

public class TestImage {

	public static void start(File f) throws org.json.simple.parser.ParseException, JSONException, IOException {
		File file = new File ("URLStrings.txt");
		FileWriter fw = new FileWriter (file);
		PrintWriter pw = new PrintWriter(fw);

		int counter5=0;
		//Scanner scan = new Scanner (new File ("maxFrequencyWords.txt"));
		FileReader fr = new FileReader (f);
		BufferedReader br = new BufferedReader (fr);
		
		//boolean test = scan.hasNext();
		//System.out.println(br.ready());
		//while (br.ready()) {
			String word = br.readLine(); 
			String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
			String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=" + word;

			//ArrayList<String> resultUrls = new ArrayList<String>();
			//ArrayList<Element> elements = new ArrayList<Element>();

			try {
				org.jsoup.nodes.Document doc = Jsoup.connect(url).userAgent(userAgent).referrer("https://www.google.com/").get();

				///org.w3c.dom.NodeList elements = (org.w3c.dom.NodeList) (doc).select("div.rg_meta");
				org.jsoup.select.Elements elements = (org.jsoup.select.Elements) (doc).select("div.rg_meta");

				JSONObject jsonObject;
				int counter = 0;
				for (Node element : elements) {
					if (counter == 5) {
						counter5++;
						break;
					}
					if (((Node) element).childNodeSize() > 0) {
						jsonObject = (JSONObject) new JSONParser().parse(((Node) element).childNode(0).toString());
						//resultUrls.add((String) jsonObject.get("ou"));
						//pw.println((String)jsonObject.get("ou"));
						if(counter5==4&&counter==4) pw.print((String)jsonObject.get("ou"));
						else pw.println((String)jsonObject.get("ou"));
					}
					counter++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			//System.out.println("number of results: " + resultUrls.size());


			//			int counter2 = 0;
			//			for (String imageUrl : resultUrls) {
			//				counter2++;
			//				System.out.println(imageUrl);
			//				pw.print(imageUrl);
			//				if (counter2 != 25) {
			//					pw.print("\n");
			//				}
		//}
		fw.close();
		pw.close();
		//scan.close();
		fr.close();
		br.close();


		//			for (String imageUrl : resultUrls) {
		//				 BufferedImage image = ImageIO.read(new URL(imageUrl));
		//				 System.out.println(image);
		//			}


	}
}
