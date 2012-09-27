package dynamicFitnessFunction;
import java.util.ArrayList;

import javax.swing.JFrame;


//public class RunEvoBoids implements Config

public class EvoBoidsDriver
{
	public static void main(String args[])
	{
		
		JFrame frame = new JFrame("Evolution Boids");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		FramePanel panel = new FramePanel();
		
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	

	
}


