package risk.view;
//A specific Observer to observe ClockTimerModel: DigitalClockView
//


import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import risk.model.interfaces.PhaseViewInterface;
public class RiskPhaseViewObserver implements Observer  {

	JPanel phaseViewPanel;
	JTextArea phaseViewTextArea;
	JFrame phaseViewFrame;
	
	
	/** 
	    * create an object of SingleObject embedded as a static member of the class itself
	    */
	   private static RiskPhaseViewObserver instance = new RiskPhaseViewObserver();
	   /** 
	    * Make the constructor private so that this class cannot be instantiated
	    */
	   private RiskPhaseViewObserver(){}
	   /**
	    * If the instance was not previously created, create it. Then return the instance
	    */
	   public static RiskPhaseViewObserver getInstance(){
	      if (instance == null)
	      instance = new RiskPhaseViewObserver();
	      return instance;
	   }
	
	public void generatePhaseView()
	{
		phaseViewFrame = new JFrame();
		phaseViewFrame.setTitle("Phase View");
		phaseViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// get the screen size as a java dimension
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// get 2/3 of the height, and 2/3 of the width
		int height = screenSize.height * 2/3;
		int width = screenSize.width * 2 / 3;
		phaseViewFrame.setPreferredSize(new Dimension(width, height));

//		
//		Container pane = getContentPane();
//		pane.setLayout(new GridBagLayout());
//		pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//
//		GridBagConstraints c = new GridBagConstraints();
		//Phase View Panel
		phaseViewPanel = new JPanel();
		//phaseViewPanel.setSize(new Dimension(width/2, height));
		//c.weightx = 30;
//		c.ipady = 0;
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridheight = height;
//		c.gridwidth = width;
		phaseViewTextArea = new JTextArea("",100,40);
		phaseViewTextArea.setEditable(false);
		//phaseViewTextArea.setSize(new Dimension(width/2, height));
		phaseViewPanel.add(phaseViewTextArea);
		phaseViewPanel.setVisible(true);
		//pane.add(phaseViewPanel, c);

		phaseViewFrame.add(phaseViewPanel);
		phaseViewFrame.setResizable(false);		
		phaseViewFrame.pack();
		phaseViewFrame.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		phaseViewTextArea.setText(((PhaseViewInterface)o).getContent());
		phaseViewFrame.repaint();
	}

}
