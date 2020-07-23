import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class VolumeIssueChecker extends JFrame
{
	private double availableConcrete;
	private double insideDiameter;
	private double prevTapeDepth;
	private double actualTapeDepth;
	
	public VolumeIssueChecker(double availableConcrete, double insideDiameter, double prevTapeDepth, double actualTapeDepth)
	{
		this.availableConcrete = availableConcrete;
		this.insideDiameter = insideDiameter;
		this.prevTapeDepth = prevTapeDepth;
		this.actualTapeDepth = actualTapeDepth;
	}
	
	public double coefficient()
	{
		double conv = (Math.PI / 3) * Math.pow((insideDiameter / 72), 2);
		return conv;
	}
	
	public double targetValue()
	{
		double theorLayerHeight = availableConcrete / this.coefficient();
		double target = prevTapeDepth - theorLayerHeight;
		return target;
	}
	
	public double gap()
	{
		double round = Math.round((this.targetValue() - actualTapeDepth) * 100.0) / 100.0;
		return round;
	}
	
	public boolean checker()
	{
		if (actualTapeDepth < this.targetValue())
		{
			return true;
		}
		return false;
	}
		
	
	public static void main(String[] args) 
	{
      JTextField concrete = new JTextField(5);
      JTextField diameter = new JTextField(5);
	  JTextField previousDepth = new JTextField(5);
      JTextField actualDepth = new JTextField(5);
	  JFrame parent = new JFrame();

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Available Concrete (cy): "));
      myPanel.add(concrete);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Inside Diameter (in): "));
      myPanel.add(diameter);
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Previous Tape Depth (ft): "));
      myPanel.add(previousDepth);
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Actual Tape Depth (ft): "));
      myPanel.add(actualDepth);
	  
	    int result = JOptionPane.showConfirmDialog(null, myPanel,
        "Please Enter Your Values", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION)
		{  
			// show a joptionpane dialog using showMessageDialog
			VolumeIssueChecker test = new VolumeIssueChecker(Double.parseDouble(concrete.getText()), Double.parseDouble(diameter.getText()), Double.parseDouble(previousDepth.getText()), Double.parseDouble(actualDepth.getText()));
			if (test.checker())
			{
				JOptionPane.showMessageDialog(null, "There is a volume issue in the shaft. The tape depth is " + test.gap() + " feet less than the target value.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "There does not appear to be a volume issue. The tape depth is " + Math.abs(test.gap()) + " feet greater than the target value.");
			}
		}
			
		
	 
 
   }
}
	