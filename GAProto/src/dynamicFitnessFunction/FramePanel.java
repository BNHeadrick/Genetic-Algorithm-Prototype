package dynamicFitnessFunction;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class FramePanel extends JPanel implements Config
{
	private int counter;
	private JLabel counterLabel;
	private JButton counterButton;
	
	private boolean populationCreated = false;
	private RunEvoBoids executeBoids;
	
	public FramePanel()
	{
		executeBoids = new RunEvoBoids();
		
		setPreferredSize (new Dimension(800, 600));
		setBackground(Color.gray);
		setLayout(new BorderLayout(10,10));
//		setFont(new Font("yo", Font.BOLD, 10));

		JPanel topPanel = new JPanel();
//		topPanel.setLayout(new BorderLayout());
		JPanel middlePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		
		counterLabel = new JLabel("You've pushed the button 0 times.");
		
		counterButton = new JButton("PUSH ME!");
		counterButton.addActionListener(new ButtonListener());
		
		
		
		add(counterButton, BorderLayout.SOUTH);
		add(counterLabel);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
//		g.fillRect(12,12,20,20);
		
		if(populationCreated)
		{
//			g.setColor(Color.WHITE);
//			g.fillRect(20, 20, 10, 10);
			
			ArrayList<ArrayList<Member>> entirePopulation = new ArrayList<ArrayList<Member>>(executeBoids.getGAEval().getEntirePop());
			for(int n = 0; n<speciesPopulation; n++)
			{
				ArrayList<Member> singlePopulation = entirePopulation.get(n);
				System.out.println("species " + singlePopulation.get(0).getSpeciesType() + ":" + singlePopulation.get(0).getComGenomeToString());
				
				int[] tempColors = new int[3];
				for(int i = 0; i<3; i++)
				{
					tempColors[i] = Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(3*i, 3 + 3*i));
					System.out.println(tempColors[i]);
					
				}
				g.setColor(new Color(tempColors[0], tempColors[1], tempColors[2]));
				int[] xPoints = new int[5];
				int[] yPoints = new int[5];
				System.out.println("here");
				for(int i = 0; i<5; i++)
				{
//					System.out.print(Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(19+2*i, 20+2*i)));
//					System.out.print(" " );
//					System.out.println(Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(20+2*i, 21+2*i)));
					
					System.out.println("this one: " + Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(28, 30)));
					xPoints[i] = Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(19+2*i, 20+2*i)) + Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(28, 29));
					yPoints[i] = Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(20+2*i, 21+2*i)) + Integer.parseInt(singlePopulation.get(0).getComGenomeToString().substring(28, 29));
				}
				Polygon p = new Polygon(xPoints, yPoints, 5);
				
				p.translate(20*n, 20);
				g.fillPolygon(p);
				
			}
		}
	}
	
	
	private class ButtonListener implements ActionListener
	{
		
		
		public void actionPerformed(ActionEvent event)
		{
			executeBoids.runGA();
			populationCreated = true;
			counter++;
			if (counter==1)
			{
				counterLabel.setText("You've pushed the button " + counter + " time.");
			}
			else if (counter >1)
			{
				counterLabel.setText("You've pushed the button " + counter + " times.");
			}
		}
		
		
	}
	
}
