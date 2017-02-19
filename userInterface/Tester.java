import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class Tester {


	public static void main(String[] args) throws IOException{
		final ColoredItem item = new ColoredItem();
		final File imgFile = new File("URLStrings.txt");
		final PrintWriter pr = new PrintWriter(new FileWriter(imgFile));

		//info
		final JFrame frame = new JFrame("Synaesthesia");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//container.setPreferredSize(new Dimension(500,500));
		container.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), BorderFactory.createBevelBorder(1)));

		//logo panel
		JPanel logoPanel = new JPanel();
		logoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		ImageIcon imageIcon = new ImageIcon("/Users/agaut/Desktop/LOGOBOI.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		logoPanel.add(new JLabel(imageIcon));
		container.add(logoPanel);
		
		//image panel
		JPanel imagePanel = new JPanel();
		//imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));
		imagePanel.setBorder(BorderFactory.createTitledBorder("drag image"));
		final FileDragger fileDrag = new FileDragger();
		imagePanel.add(fileDrag);
		container.add(imagePanel);

		//word panel
		JPanel wordPanel = new JPanel();
		wordPanel.setLayout(new BoxLayout(wordPanel, BoxLayout.X_AXIS));
		wordPanel.setBorder(BorderFactory.createTitledBorder("enter word"));
		final JTextArea text2 = new JTextArea("",1,15);
		Font font = new Font(
				Font.MONOSPACED, 
				Font.PLAIN, 
				text2.getFont().getSize());
		wordPanel.add(new JScrollPane(text2,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		container.add(wordPanel);

		//text panel
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
		textPanel.setBorder(BorderFactory.createTitledBorder("enter text"));
		final JTextArea text3 = new JTextArea("",15,40);
		text3.setLineWrap(true);
		text3.setFont(font);
		textPanel.add(new JScrollPane(text3,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		container.add(textPanel);


		//button panel
		JPanel buttonPanel = new JPanel();
		JButton findPaletteButton = new JButton("find palette");
		buttonPanel.add(findPaletteButton);
		frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);

		frame.add(container);

		frame.pack();
		frame.setVisible(true);


		//colors
		final JFrame window = new JFrame();
		//final ArrayList<String> strings = new ArrayList<String>();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(30, 30, 400, 200);
		window.setLocation(400, 0);
		findPaletteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter fw = new FileWriter (imgFile);
					BufferedWriter pw=new BufferedWriter(fw);
					if(!fileDrag.isEmpty()){
						item.picLink = fileDrag.getFirstItem().toString();
						item.numPics =1;
						System.out.println(item.picLink);
						pw.write(item.picLink);
						pw.flush();
					}	
					if(!"".equals(text2.getText())){
						File f=new File("maxFrequencyWords.txt");
						pw = new BufferedWriter (new FileWriter(f.getAbsoluteFile()));
						item.word = text2.getText();
						item.numPics = 5;
						//System.out.println(item.word);
						pw.write(item.word);
						pw.flush();
						TestImage.start(f);
					}
					if(!"".equals(text3.getText())){
						File f=new File("maxFrequencyWords.txt");
						pw = new BufferedWriter (new FileWriter(f.getAbsoluteFile()));

						item.text = text3.getText();
						item.numPics =5;
						Text.getFreqArray(Text.createWords(item.text));
						TestImage.start(f);
					}
					JPanel buttonsPanel = new JPanel();
					JButton restart = new JButton("Get New Palette");
					window.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
					buttonsPanel.add(restart);
					window.getContentPane().add(new Pictures());
					window.setVisible(true);
					restart.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							window.setVisible(false);
							window.dispose();
							item.word = "";
							item.numPics = 0;
							item.picLink = "";
							item.text = "";

						}
					});
					//pw.print(item.picLink);
					window.setVisible(true);
					window.getContentPane().add(new Pictures());
					pw.close();
					fw.close();
				} catch (IOException | ParseException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}
}
