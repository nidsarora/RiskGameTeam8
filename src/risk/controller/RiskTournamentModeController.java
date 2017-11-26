/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RiskAddPlayerUI.java
 *
 * Created on Nov 15, 2010, 12:39:35 PM
 */

package risk.controller;

import risk.controller.RiskStartGameController;
import risk.helpers.Utility;
import risk.controller.RiskStartGameController;
import risk.model.*;
import risk.model.strategy.Human;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * This class is used to add RiskPlayers to the game.
 * 
 * @author Team8
 */
public class RiskTournamentModeController extends java.awt.Frame {

	/** The no of players. */
	static int noOfPlayers = 3;
    
    /** The behaviour players. */
    static ArrayList<String> behaviourPlayers=new ArrayList<String>(7);
    
    /** The max turns. */
    int mapNum, playerNum, gameNum, maxTurns;
	
	/**
	 *  Creates new form RiskAddPlayerUI.
	 */
	public RiskTournamentModeController() {
		initComponents();
		setLocationRelativeTo(null);
	}

	/** The added. */
	boolean added;

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {

		modePanel = new javax.swing.JPanel();
		modePanel.setForeground(Color.BLACK);
		Integer[] numbers = { 3, 4, 5, 6 };
		Integer[] maps= {1,2,3,4,5};
		Integer[] playerStrategies= {1,2,3,4};
		Integer[] games= {1,2,3,4,5};
		JComboBox turnNumCombobox = new JComboBox();
		for(int i=10;i<=50;i++) 
		{  
			turnNumCombobox.addItem(new Integer(i));
		}
		String[] behaviours= {"Human","Aggressive","Benevolent","Random","Cheater"};
		JComboBox mapNumCombobox = new JComboBox(maps);
		JComboBox playerNumCombobox = new JComboBox(playerStrategies);
		JComboBox gameNumCombobox = new JComboBox(games);
		
	
		modePanel.setBackground(new java.awt.Color(1, 1, 1));
		modePanel.setName("jPanel1");
		
		JButton tournamentModeDone = new JButton("Ok");
		tournamentModeDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(mapNum);
				System.out.println(playerNum);
				System.out.println(gameNum);
				System.out.println(maxTurns);
				setVisible(false);

			}
		});
		
		mapNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mapNum=(int)mapNumCombobox.getSelectedItem();
			}
	});

		playerNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playerNum=(int)playerNumCombobox.getSelectedItem();
			}
	});

		gameNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				gameNum=(int)gameNumCombobox.getSelectedItem();
			}
	});
		turnNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				maxTurns=(int)turnNumCombobox.getSelectedItem();
			}
	});

		JLabel lblTournamentMode = new JLabel("Choose number of maps, players, games,limit on number of turns ");
		lblTournamentMode.setForeground(Color.WHITE);
		
		JLabel lblMap = new JLabel("Map");
		lblMap.setForeground(Color.WHITE);
		
		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setForeground(Color.WHITE);
		
		JLabel lblGame = new JLabel("Game");
		lblGame.setForeground(Color.WHITE);
		
		JLabel lblMaximumNumberOf = new JLabel("Maximum number of turns allowed");
		lblMaximumNumberOf.setForeground(Color.WHITE);
		

		
		javax.swing.GroupLayout gl_modePanel = new javax.swing.GroupLayout(modePanel);
		gl_modePanel.setHorizontalGroup(
			gl_modePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addGap(156)
					.addComponent(lblTournamentMode)
					.addContainerGap(215, Short.MAX_VALUE))
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap(570, Short.MAX_VALUE)
					.addComponent(tournamentModeDone)
					.addGap(72))
				.addGroup(Alignment.LEADING, gl_modePanel.createSequentialGroup()
					.addGap(88)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMap)
						.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGame, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMaximumNumberOf, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(turnNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(gameNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(playerNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mapNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(326, Short.MAX_VALUE))
		);
		gl_modePanel.setVerticalGroup(
			gl_modePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblTournamentMode)
					.addGap(38)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMap)
						.addComponent(mapNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer)
						.addComponent(playerNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGame)
						.addComponent(gameNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximumNumberOf)
						.addComponent(turnNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(tournamentModeDone)
					.addGap(21))
		);
		modePanel.setLayout(gl_modePanel);

		add(modePanel, java.awt.BorderLayout.CENTER);

		pack();
	}


	/** The mode panel. */
	private javax.swing.JPanel modePanel;
}
