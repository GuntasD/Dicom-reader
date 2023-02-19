package dicomfilereader;

import java.awt.Dimension;
import javax.swing.*; //imports Swing package which creates form and button

import org.dcm4che3.data.Attributes;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.util.TagUtils;

import java.awt.event.*; //imports Event package which listens for button press
import java.io.File;
import java.io.IOException;


public class Displaytags implements ActionListener 
{ //notice implements ActionListener
    JButton button;
    JTextField textField;
    JLabel label;
    JTextArea textArea;

    public static void main (String[] args) 
    {
    	Displaytags gui = new Displaytags();
        gui.showTags();

    }

    
    
    public void showTags()
    {
        JFrame frame = new JFrame(); //creates a Java Frame called frame
        frame.setBounds(50, 50, 800, 800);
        frame.getContentPane().setLayout(null);
        frame.setSize(434,700); //pixel size of frame in width then height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ends program when JFrame closed

        
        label = new JLabel(); //creates label object
        label.setText("DICOM file path:"); // sets label text
        label.setBounds(10, 10, 100, 20);
        
        button = new JButton("Show Tags"); //creates a Button called button
        button.addActionListener(this); //listens for button press
        button.setBounds(10,50,100,20);

        textField = new JTextField();
        textField.setBounds(110,10,300,22);
        
        textArea = new JTextArea();
        textArea.setBounds(10,75,400,550);
        
        frame.getContentPane().add(label); //adds button to frame
        frame.getContentPane().add(button); //adds button to frame
        frame.getContentPane().add(textField);
        frame.getContentPane().add(textArea);


        frame.setVisible(true); //if false then frame will be invisible

    }

    public void actionPerformed(ActionEvent event)
    { //if button is pressed then this is ran
       try 
       {
// 		String sampleDICOM = "C:\\Coding\\GuntasDhanjal\\dicomimages\\MRBRAIN.dcm";

    	   File file = new File(textField.getText());
           DicomInputStream dis = new DicomInputStream(file);
           Attributes attributes = dis.readDataset();
           int[] tags = attributes.tags();
           textArea.append("Total tag found in the given dicom file: "+tags.length);
           for (int tag: tags) {
               String tagAddress = TagUtils.toString(tag);
               String vr = attributes.getString(tag);
               //String vr = attributes.getVR(tag).toString();
               textArea.append("\nTag Address: " + tagAddress + " VR: " + vr);
           }
           dis.close();       } 
       catch (IOException e) 
       {
           System.out.println(e.getMessage());
       }
    }
    


}