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

public class RiskPlayerDominationViewObserver implements Observer,Serializable {
	
	JPanel playerDominationViewPanel;
	JTextArea playerDominationViewTextArea;
	JFrame playerDominationViewFrame;
	StringBuilder playerDominationViewTextAreaString = new StringBuilder();
	private static RiskPlayerDominationViewObserver instance = new RiskPlayerDominationViewObserver();

	private RiskPlayerDominationViewObserver() {
	}

	public static RiskPlayerDominationViewObserver getInstance() {
		if (instance == null)
			instance = new RiskPlayerDominationViewObserver();
		return instance;
	}

	public void generatePhaseView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height * 1 / 3;
		int width = screenSize.width * 2 / 3;
		JScrollPane scrollPane;
		
		playerDominationViewFrame = new JFrame();
		playerDominationViewFrame.setTitle("Player Domination View");
		playerDominationViewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		playerDominationViewFrame.setPreferredSize(new Dimension(width, height));
		playerDominationViewPanel = new JPanel();
		playerDominationViewTextArea = new JTextArea("", 40, 60);
		playerDominationViewTextArea.setEditable(false);

		scrollPane = new JScrollPane(playerDominationViewTextArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		playerDominationViewPanel.add(scrollPane);
		
		playerDominationViewFrame.add(playerDominationViewPanel);
		playerDominationViewFrame.setResizable(true);
		playerDominationViewFrame.pack();
		playerDominationViewFrame.setVisible(true);
	}

	@Override
	public void update(Observable object, Object argument) {
		playerDominationViewTextAreaString.append(((RiskPlayerModel)object).getPlayerDominationViewContent());
		playerDominationViewTextArea.setText(((RiskPlayerModel)object).getPlayerDominationViewContent());
		Utility.writePlayerViewLog(((RiskPlayerModel)object).getPlayerDominationViewContent());
		playerDominationViewFrame.repaint();
	}
}
