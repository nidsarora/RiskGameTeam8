package risk.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import risk.helpers.Utility;
import risk.model.RiskPlayerModel;

/**
 * An asynchronous update interface for receiving notifications
 * about RiskCardExchangeView information as the Risk Card Exchange View is constructed.
 */
public class RiskCardExchangeViewObserver implements Observer ,Serializable{
	
	/** The card exchange view panel. */
	JPanel cardExchangeViewPanel;
	
	/** The card exchange view text area. */
	JTextArea cardExchangeViewTextArea;
	
	/** The card exchange view frame. */
	JFrame cardExchangeViewFrame;
	
	/** The card exchange view text area string. */
	StringBuilder cardExchangeViewTextAreaString = new StringBuilder();
	
	/** The instance. */
	private static RiskCardExchangeViewObserver instance = new RiskCardExchangeViewObserver();

	/**
	 * This method is called when information about an RiskCardExchangeView
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 */
	private RiskCardExchangeViewObserver() {
	}

	/**
	 * This method is called when information about an RiskCardExchangeView
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @return the instance
	 */
	public static RiskCardExchangeViewObserver getInstance() {
		if (instance == null)
			instance = new RiskCardExchangeViewObserver();
		return instance;
	}

	/**
	 * This method is called when information about an RiskCardExchangeView
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 */
	public void generateCardExchangeView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 2 / 3;
		int width = screenSize.width * 1 / 3;
		JScrollPane scrollPane;
		
		cardExchangeViewFrame = new JFrame();
		cardExchangeViewFrame.setTitle("Card Exchange View");
		cardExchangeViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cardExchangeViewFrame.setPreferredSize(new Dimension(width, height));
		cardExchangeViewPanel = new JPanel();
		cardExchangeViewTextArea = new JTextArea("", 40, 40);
		cardExchangeViewTextArea.setEditable(false);
		scrollPane = new JScrollPane(cardExchangeViewTextArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cardExchangeViewPanel.add(scrollPane);
		
		cardExchangeViewFrame.add(cardExchangeViewPanel);
		cardExchangeViewFrame.setResizable(true);
		cardExchangeViewFrame.pack();
		cardExchangeViewFrame.setVisible(true);
	}

	@Override
	public void update(Observable object, Object argument) {
		cardExchangeViewTextAreaString.append(((RiskPlayerModel)object).getCardExchangeViewContent());
		cardExchangeViewTextAreaString.append("*************************************************************");
		cardExchangeViewTextArea.setText(((RiskPlayerModel)object).getCardExchangeViewContent());
		Utility.writeCardViewLog(((RiskPlayerModel)object).getCardExchangeViewContent());
		cardExchangeViewFrame.repaint();
	}
}

