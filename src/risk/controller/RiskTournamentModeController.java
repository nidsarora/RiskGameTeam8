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
import risk.model.strategy.Aggressive;
import risk.model.strategy.Benevolent;
import risk.model.strategy.Cheater;
import risk.model.strategy.Human;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
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
	static ArrayList<String> behaviourPlayers = new ArrayList<String>(7);

	/** The max turns. */
	int mapNum, playerNum, gameNum, maxTurns;

	/**
	 * Creates new form RiskAddPlayerUI.
	 */
	public RiskTournamentModeController() {
		initComponents();
		setLocationRelativeTo(null);
	}

	/** The added. */
	boolean added;

	public RiskTournamentModel riskTournamentModel;

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {

		modePanel = new javax.swing.JPanel();
		modePanel.setForeground(Color.BLACK);
		Integer[] numbers = { 2,3, 4, 5, 6 };
		Integer[] mapNumArray = { 1, 2, 3, 4, 5 };
		Integer[] playerStrategies = { 2, 3, 4 };
		Integer[] games = { 1, 2, 3, 4, 5 };
		JComboBox turnNumCombobox = new JComboBox();
		for (int i = 10; i <= 50; i++) {
			turnNumCombobox.addItem(new Integer(i));
		}
		String[] behaviours = { "Aggressive", "Benevolent", "Random", "Cheater" };
        String[] maps = { "World", "3dCliff", "UserDefined","India","british columbia","Europe" };
		JComboBox mapNumCombobox = new JComboBox(mapNumArray);
		JComboBox behaviourPlayer1 = new JComboBox(behaviours);
		JComboBox behaviourPlayer2 = new JComboBox(behaviours);
		JComboBox behaviourPlayer3 = new JComboBox(behaviours);
		JComboBox behaviourPlayer4 = new JComboBox(behaviours);
		JComboBox playerNumCombobox = new JComboBox(playerStrategies);
		JComboBox gameNumCombobox = new JComboBox(games);
		modePanel.setBackground(new java.awt.Color(1, 1, 1));
		modePanel.setName("jPanel1");

		JButton tournamentModeDone = new JButton("Ok");
		tournamentModeDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utility.writeLog("***********Tournament Mode begins  *************");
				Utility.writeLog("User seleccted " + mapNum + " maps");
				Utility.writeLog("User seleccted " + playerNum + " number of palyer strategies");
				Utility.writeLog("User seleccted " + gameNum + " number of games");
				Utility.writeLog("User seleccted " + maxTurns + " number of maximm turns");
				setVisible(false);
				startGame();
			}

		});

		JLabel lblTournamentMode = new JLabel("Choose number of maps, player strategies, games,limit on number of turns ");
		lblTournamentMode.setForeground(Color.WHITE);

		JLabel lblMap = new JLabel("Map");
		lblMap.setForeground(Color.WHITE);

		JLabel lblPlayer = new JLabel("Player Strategies");
		lblPlayer.setForeground(Color.WHITE);

		JLabel lblGame = new JLabel("Game");
		lblGame.setForeground(Color.WHITE);

		JLabel lblMaximumNumberOf = new JLabel("Maximum number of turns allowed");
		lblMaximumNumberOf.setForeground(Color.WHITE);
		
		JLabel lblPlayer_1 = new JLabel("Player 1");
		lblPlayer_1.setForeground(Color.WHITE);
		
		JLabel lblPlayer_2 = new JLabel("Player 2");
		lblPlayer_2.setForeground(Color.WHITE);
		
		JLabel lblPlayer_3 = new JLabel("Player 3");
		lblPlayer_3.setForeground(Color.WHITE);
		
		JLabel lblPlayer_4 = new JLabel("Player 4");
		lblPlayer_4.setForeground(Color.WHITE);
		mapNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mapNum = (int) mapNumCombobox.getSelectedItem();
			}
		});

		playerNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playerNum = (int) playerNumCombobox.getSelectedItem();
				
				if (playerNum == 2) {
					
					behaviourPlayer1.setVisible(true);
					lblPlayer_1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer_2.setVisible(true);
					behaviourPlayer3.setVisible(false);
					lblPlayer_3.setVisible(false);
					lblPlayer_4.setVisible(false);
					behaviourPlayer4.setVisible(false);
					
				}
				if (playerNum == 3) {
					
					behaviourPlayer1.setVisible(true);
					lblPlayer_1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer_2.setVisible(true);
					behaviourPlayer3.setVisible(true);
					lblPlayer_3.setVisible(true);
					lblPlayer_4.setVisible(false);
					behaviourPlayer4.setVisible(false);
				}
				if (playerNum == 4) {
					behaviourPlayer1.setVisible(true);
					lblPlayer_1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer_2.setVisible(true);
					behaviourPlayer3.setVisible(true);
					lblPlayer_3.setVisible(true);
					lblPlayer_4.setVisible(true);
					behaviourPlayer4.setVisible(true);
				}
				
				behaviourPlayer1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 1)
							behaviourPlayers.remove(0);
						behaviourPlayers.add(0, (String) behaviourPlayer1.getSelectedItem());
						for (int i = 0; i < behaviourPlayers.size(); i++) {
							System.out.println( behaviourPlayers.get(i));}
					}
				});
				behaviourPlayer2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 2)
							behaviourPlayers.remove(1);
						behaviourPlayers.add(1, (String) behaviourPlayer2.getSelectedItem());
						for (int i = 0; i < behaviourPlayers.size(); i++) {
							System.out.println( behaviourPlayers.get(i));}
					}
				});
				behaviourPlayer3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 3)
							behaviourPlayers.remove(2);
						behaviourPlayers.add(2, (String) behaviourPlayer3.getSelectedItem());
						for (int i = 0; i < behaviourPlayers.size(); i++) {
							System.out.println( behaviourPlayers.get(i));}
					}
				});
				behaviourPlayer4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 4)
							behaviourPlayers.remove(3);
						behaviourPlayers.add(3, (String) behaviourPlayer4.getSelectedItem());
						for (int i = 0; i < behaviourPlayers.size(); i++) {
							System.out.println( behaviourPlayers.get(i));}
					}
				});
				
			}
		});

		playerNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				gameNum = (int) playerNumCombobox.getSelectedItem();
			}
		});
		turnNumCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				maxTurns = (int) turnNumCombobox.getSelectedItem();
			}
		});


		
		//JComboBox behaviourPlayer1 = new JComboBox();
		behaviourPlayer1.setVisible(false);
		lblPlayer_1.setVisible(false);
		behaviourPlayer2.setVisible(false);
		lblPlayer_2.setVisible(false);
		behaviourPlayer3.setVisible(false);
		lblPlayer_3.setVisible(false);
		behaviourPlayer4.setVisible(false);
		lblPlayer_4.setVisible(false);

		javax.swing.GroupLayout gl_modePanel = new javax.swing.GroupLayout(modePanel);
		gl_modePanel.setHorizontalGroup(
			gl_modePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modePanel.createSequentialGroup()
					.addGap(156)
					.addComponent(lblTournamentMode)
					.addContainerGap(218, Short.MAX_VALUE))
				.addGroup(gl_modePanel.createSequentialGroup()
					.addGap(88)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_modePanel.createSequentialGroup()
							.addComponent(lblPlayer_4, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_modePanel.createSequentialGroup()
								.addComponent(lblPlayer_3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_modePanel.createSequentialGroup()
									.addComponent(lblPlayer_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_modePanel.createSequentialGroup()
										.addComponent(lblPlayer_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(gl_modePanel.createSequentialGroup()
										.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblMap)
											.addComponent(lblPlayer, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblGame, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblMaximumNumberOf, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
										.addGap(69)
										.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_modePanel.createSequentialGroup()
												.addComponent(gameNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addContainerGap())
											.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_modePanel.createSequentialGroup()
													.addComponent(turnNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
													.addContainerGap())
												.addGroup(gl_modePanel.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_modePanel.createSequentialGroup()
														.addComponent(playerNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addContainerGap())
													.addGroup(gl_modePanel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_modePanel.createSequentialGroup()
															.addComponent(mapNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addContainerGap(305, Short.MAX_VALUE))
														.addGroup(gl_modePanel.createSequentialGroup()
															.addComponent(tournamentModeDone)
															.addGap(72))))
												.addGroup(gl_modePanel.createSequentialGroup()
													.addGroup(gl_modePanel.createParallelGroup(Alignment.TRAILING)
														.addComponent(behaviourPlayer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(gl_modePanel.createSequentialGroup()
															.addComponent(playerNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
															.addGap(45)))
													.addGap(372))))))))))
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
					.addGap(18)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer)
						.addComponent(playerNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGame)
						.addComponent(playerNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaximumNumberOf)
						.addComponent(turnNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer_1)
						.addComponent(behaviourPlayer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer_2)
						.addComponent(behaviourPlayer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer_3)
						.addComponent(behaviourPlayer3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_modePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer_4)
						.addComponent(behaviourPlayer4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(tournamentModeDone)
					.addGap(21))
				.addGroup(gl_modePanel.createSequentialGroup()
					.addContainerGap(155, Short.MAX_VALUE)
					.addComponent(gameNumCombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(226))
		);
		modePanel.setLayout(gl_modePanel);

		add(modePanel, java.awt.BorderLayout.CENTER);

		pack();
	}

	private void startGame() {
        
		Vector<RiskPlayerModel> gameplayers = new Vector<RiskPlayerModel>();
		for (int i = 0; i < behaviourPlayers.size(); i++) {
			System.out.println("behaviour--");
			System.out.println( behaviourPlayers.get(i));}
	
	
	
		System.out.println("----------behaviour------------------");
		for (int i = 0; i < behaviourPlayers.size(); i++) {
			System.out.println( behaviourPlayers.get(i));
		
		
			switch (behaviourPlayers.get(i)) {
           
			case "Aggressive": {
				gameplayers.add(new RiskPlayerModel("PLayer" + (i+1), (i+1), new Aggressive()));
				System.out.println((i + 1) + "new risk.model.strategy.Aggressive");
				Utility.writeLog("Player " +(i + 1)+" :: Aggressive");
				break;
			}
			case "Benevolent": {
				gameplayers.add(new RiskPlayerModel("PLayer" + (i+1), (i+1), new Benevolent()));
				System.out.println((i + 1) + "new risk.model.strategy.Benevolent");
				Utility.writeLog("Player " +(i + 1)+" :: Benevolent");
				break;
			}
			case "Random": {
				gameplayers.add(new RiskPlayerModel("PLayer" + (i+1), (i+1), new risk.model.strategy.Random()));
				System.out.println((i + 1) + "new risk.model.strategy.Random");
				Utility.writeLog("Player " +(i + 1)+" :: Random");
				break;
			}
			case "Cheater": {
				gameplayers.add(new RiskPlayerModel("PLayer" + (i+1), (i+1), new Cheater()));
				System.out.println((i + 1) + "new risk.model.strategy.Cheater");
				Utility.writeLog("Player " +(i + 1)+" :: Cheater");
				break;
			}
			}
		}
		ArrayList<String> randomMapNameList = new ArrayList<String>();
		ArrayList<String> tempmapNameList = new ArrayList<String>();
		ArrayList<String> mapNameList = new ArrayList<String>();
		randomMapNameList.add("BaseEarthMap.map");
		randomMapNameList.add("World.map");
		randomMapNameList.add("3D Cliff.map");
		randomMapNameList.add("India.map");
		randomMapNameList.add("Europe.map");
		randomMapNameList.add("BritishColumbia.map");
        Collections.shuffle(randomMapNameList);
        Utility.writeLog("Maps are selected randomly " );
        for (int mapIndex=0; mapIndex<mapNum; mapIndex++) {
        	tempmapNameList.add(randomMapNameList.get(mapIndex));
        	Utility.writeLog("Map "+(mapIndex+1)+ " "+randomMapNameList.get(mapIndex));
            System.out.println("Map "+(mapIndex+1)+  " "+randomMapNameList.get(mapIndex));
        }
        for(int j=0;j<tempmapNameList.size();j++) {
        for(int i=0;i<=gameNum;i++) {
        	
        	mapNameList.add(tempmapNameList.get(j));
        	//System.out.println("index"+i+mapNameList.get(j));
        	}
        }
        for(int i=0;i<mapNameList.size();i++)
{
	System.out.println("index" + i+ mapNameList.get(i));
}


		riskTournamentModel = new RiskTournamentModel(mapNum =1, gameNum = 2, gameplayers, maxTurns = 50, mapNameList);
        riskTournamentModel.initializeTournament();
		riskTournamentModel.startTournament();
		riskTournamentModel.printTournamentResult();
	}
	
	/** The mode panel. */
	private javax.swing.JPanel modePanel;
}
