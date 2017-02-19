import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class InfoGetter {
	public static Object imgFile = null;
	public static String word = null;
	public static String text = null;
	public File txtFile = null;
	public static int borderSize = 20;
	//public
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("Color Palette Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//container.setPreferredSize(new Dimension(500,500));
		container.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize), BorderFactory.createBevelBorder(1)));
		
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
		
//		//text file drag panel
//		JPanel textPanel2 = new JPanel();
//		//textPanel2.setLayout(new BoxLayout(textPanel2, BoxLayout.X_AXIS));
//		textPanel2.setBorder(BorderFactory.createTitledBorder("drag text file"));
//		JTextArea text4 = new JTextArea("drag here");
//		textPanel2.add(text4);
//		container.add(textPanel2);
				
		//button panel
		JPanel buttonPanel = new JPanel();
		JButton findPaletteButton = new JButton("find palette");
		findPaletteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!fileDrag.isEmpty()){
					imgFile = fileDrag.getFirstItem();
					System.out.println(imgFile);
				}	
				if(text2.getText()!=""){
					word = text2.getText();
					System.out.println(word);
				}
				if(text3.getText()!=""){
					text = text3.getText();
					System.out.println(text);
				}
			}
		});
		buttonPanel.add(findPaletteButton);
		frame.getContentPane().add(buttonPanel,BorderLayout.SOUTH);
		
		frame.add(container);
		
	    frame.pack();
	    frame.setVisible(true);
	}
}
