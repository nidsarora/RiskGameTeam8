/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RiskPlayerPanel.java
 *
 * Created on Nov 18, 2010, 4:43:14 PM
 */

package risk.controller;

import risk.model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The Class RiskPlayerPanelViewController.
 * @author Group8
 */
public class RiskPlayerPanelViewController extends javax.swing.JPanel {
	RiskGameModel risk;

	/** Creates new form RiskPlayerPanel */
	public RiskPlayerPanelViewController() {
		initComponents();
		repaint();
	}

	/**
	 * Instantiates a new risk player panel view controller.
	 *
	 */
	public RiskPlayerPanelViewController(RiskGameModel r) {
		risk = r;
		initComponents();
		repaint();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * 
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
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String name;
		int playerIndex;
		int playerNum;
		int armies;
		int pos = 0;
		int w;
		Font f = new Font("Arial", Font.BOLD, 26);

		int p = risk.iter;
		name = risk.curPlayer.getName();

		for (int i = 0; i < RiskGameModel.players.size(); i++) {
			playerNum = risk.iter;
			int loc = 0;
			playerIndex = risk.curPlayer.getPlayerIndex();
			armies = risk.curPlayer.getNumberOfArmies();
			if (armies > 9)
				loc = -15;

/** 
 *  Draws the player name and rectangle 
 *  sets the width and position  
 *  width of rectangle changes depending on number of players
 */
			if (p == playerNum) { // current player
				// with of rect changed for num of players
				w = 850 - (RiskGameModel.players.size() * 75);
				pos = 125;
				g.setFont(f);
				g.setColor(Color.white);
				g.drawString(name, pos, 40);
				g.setFont(new Font("Arial", Font.PLAIN, 60));
				g.drawString(Integer.toString(armies), 50 + loc, 60);
				g.setFont(new Font("Arial", Font.PLAIN, 12));
				g.drawString("armies", 50, 80);
			} else { // not current player
				// start pos changed based on num of players
				pos = 875 - (RiskGameModel.players.size() * 75);
				w = 75; // width of rec
			}


/**
 *  Assign the player color according to his/her index number
 * 
 */
			if (playerIndex == 0)
				g.setColor(Color.red);
			if (playerIndex == 1)
				g.setColor(Color.blue);
			if (playerIndex == 2)
				g.setColor(Color.yellow);
			if (playerIndex == 3)
				g.setColor(Color.green);
			if (playerIndex == 4)
				g.setColor(Color.pink);
			if (playerIndex == 5)
				g.setColor(Color.orange);

			// System.out.println(name + " width = " + w + " " + g.getColor().toString());
			g.fillRect(pos + (i * 75), 63, w, 20);
			// Go to next player
			// if((risk.getState() == RiskGame.NEW_GAME) || (risk.getState() ==
			// RiskGame.INITIAL_REINFORCE))
			risk.nextPlayer();
		}

	}
}
