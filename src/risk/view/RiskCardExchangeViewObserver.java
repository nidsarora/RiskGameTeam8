package risk.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import risk.model.RiskPlayerModel;

public class RiskCardExchangeViewObserver implements Observer {
	JPanel cardExchangeViewPanel;
	JTextArea cardExchangeViewTextArea;
	JFrame cardExchangeViewFrame;
	StringBuilder cardExchangeViewTextAreaString = new StringBuilder();
	private static RiskCardExchangeViewObserver instance = new RiskCardExchangeViewObserver();

	private RiskCardExchangeViewObserver() {
	}

	public static RiskCardExchangeViewObserver getInstance() {
		if (instance == null)
			instance = new RiskCardExchangeViewObserver();
		return instance;
	}

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
		cardExchangeViewFrame.setResizable(false);
		cardExchangeViewFrame.pack();
		cardExchangeViewFrame.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		cardExchangeViewTextArea.setText(((RiskPlayerModel)o).getCardExchangeViewContent());
		cardExchangeViewFrame.repaint();
	}
}
