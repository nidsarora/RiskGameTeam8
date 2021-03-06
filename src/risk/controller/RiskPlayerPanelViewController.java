

package risk.controller;

import risk.model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *This class creates a panel for the player using a different color to represent each player.
 *Abstract Window Toolkit(awt) is used for creating the panel.
 *Number of players allowed vary from 3 to 6.
 * @author Team8
 */
public class RiskPlayerPanelViewController extends javax.swing.JPanel {

	/** The risk. */
	RiskGameModel risk;

	/**
	 *  Creates new form RiskPlayerPanel.
	 */
	public RiskPlayerPanelViewController() {
		initComponents();
		repaint();
	}

	/**
	 * Instantiates a new risk player panel view controller.
	 *
	 * @param riskgamemodel the riskgamemodel
	 */
	public RiskPlayerPanelViewController(RiskGameModel riskgamemodel) {
		risk = riskgamemodel;
		initComponents();
		repaint();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 763, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
	}


	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		String name;
		int playerIndex;
		int playerNum;
		int armies;
		int position = 0;
		int width;
		Font f = new Font("Arial", Font.BOLD, 26);

		int p = risk.iter;
		name = risk.curPlayer.getName();

		for (int index = 0; index< RiskGameModel.players.size(); index++) {
			playerNum = risk.iter;
			int loc = 0;
			playerIndex = risk.curPlayer.getPlayerIndex();
			armies = risk.curPlayer.getNumberOfArmies();
			if (armies > 9)
				loc = -15;

			// Draws the player name and stuff
			// sets the width and position
			if (p == playerNum) { // current player
				// with of rect changed for num of players
				width = 850 - (RiskGameModel.players.size() * 75);
				position = 125;
				graphics.setFont(f);
				graphics.setColor(Color.white);
				graphics.drawString(name, position, 40);
				graphics.setFont(new Font("Arial", Font.PLAIN, 60));
				graphics.drawString(Integer.toString(armies), 50 + loc, 60);
				graphics.setFont(new Font("Arial", Font.PLAIN, 12));
				graphics.drawString("armies", 50, 80);
			} else { // not current player
				// start position changed based on num of players
				position = 875 - (RiskGameModel.players.size() * 75);
				 width= 75; // width of rec
			}

	/** Assign the player color 
	 * 
	 */
			if (playerIndex == 0)
				graphics.setColor(Color.red);
			if (playerIndex == 1)
				graphics.setColor(Color.blue);
			if (playerIndex == 2)
				graphics.setColor(Color.yellow);
			if (playerIndex == 3)
				graphics.setColor(Color.green);
			if (playerIndex == 4)
				graphics.setColor(Color.pink);
			if (playerIndex == 5)
				graphics.setColor(Color.orange);

			graphics.fillRect(position + (index * 75), 63, width, 20);
			// Go to next player
			risk.nextPlayer(true);
		}

	}
}
