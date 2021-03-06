
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

/**
 * This class is used to add RiskPlayers to the game.
 * 
 * @author Team8
 */
public class RiskAddPlayerController extends java.awt.Frame {

	/** The no of players. */
	static int noOfPlayers = 3;

	/** The map. */
	static String map;

	/** The behaviour players. */
	static ArrayList<String> behaviourPlayers = new ArrayList<String>(7);

	/**
	 * Creates new form RiskAddPlayerUI.
	 */
	public RiskAddPlayerController() {
		initComponents();
		setLocationRelativeTo(null);
	}

	/** boolean for players added. */
	boolean added;

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		doneSelectingPlayers = new javax.swing.JButton();
		Integer[] numbers = { 3, 4, 5, 6 };
		String[] behaviours = { "Human", "Aggressive", "Benevolent", "Random", "Cheater" };
		String[] maps = { "World", "3dCliff", "UserDefined", "India", "british columbia", "Europe" };
		JComboBox numPlayers = new JComboBox(numbers);
		JComboBox behaviourPlayer1 = new JComboBox(behaviours);
		JComboBox behaviourPlayer2 = new JComboBox(behaviours);
		JComboBox behaviourPlayer3 = new JComboBox(behaviours);
		JComboBox behaviourPlayer4 = new JComboBox(behaviours);
		JComboBox behaviourPlayer5 = new JComboBox(behaviours);
		JComboBox behaviourPlayer6 = new JComboBox(behaviours);
		JComboBox chooseMap = new JComboBox(maps);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jPanel1.setBackground(new java.awt.Color(1, 1, 1));
		jPanel1.setName("jPanel1"); // NOI18N

		doneSelectingPlayers.setText("Ok");
		doneSelectingPlayers.setName("jButton1"); // NOI18N
		doneSelectingPlayers.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		JLabel lblEnterTheNumber = new JLabel();
		lblEnterTheNumber.setText("Enter the number of players");
		lblEnterTheNumber.setName("jLabel1");
		lblEnterTheNumber.setForeground(new Color(254, 254, 254));

		JLabel lblText = new JLabel("Choose  behaviour of al players");
		lblText.setForeground(Color.WHITE);

		JLabel lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setForeground(Color.WHITE);

		JLabel lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setForeground(Color.WHITE);

		JLabel lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setForeground(Color.WHITE);

		JLabel lblPlayer4 = new JLabel("Player 4");
		lblPlayer4.setForeground(Color.WHITE);

		JLabel lblPlayer5 = new JLabel("Player 5");
		lblPlayer5.setForeground(Color.WHITE);

		JLabel lblPlayer6 = new JLabel("Player 6");
		lblPlayer6.setForeground(Color.WHITE);

		lblText.setVisible(false);
		behaviourPlayer1.setVisible(false);
		lblPlayer1.setVisible(false);
		behaviourPlayer2.setVisible(false);
		lblPlayer2.setVisible(false);
		behaviourPlayer3.setVisible(false);
		lblPlayer3.setVisible(false);
		behaviourPlayer4.setVisible(false);
		lblPlayer4.setVisible(false);
		behaviourPlayer5.setVisible(false);
		lblPlayer5.setVisible(false);
		behaviourPlayer6.setVisible(false);
		lblPlayer6.setVisible(false);

		JLabel lblChooseMap = new JLabel("Choose Map");
		lblChooseMap.setForeground(Color.WHITE);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);

		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(202, Short.MAX_VALUE)
								.addComponent(lblEnterTheNumber, GroupLayout.PREFERRED_SIZE, 197,
										GroupLayout.PREFERRED_SIZE)
								.addGap(114))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(239)
								.addComponent(numPlayers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(243, Short.MAX_VALUE))
						.addGroup(jPanel1Layout
								.createSequentialGroup().addGap(162).addComponent(lblText)
								.addContainerGap(199, Short.MAX_VALUE))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(69)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblPlayer1, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
										.addComponent(lblPlayer2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblPlayer3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblPlayer4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(lblPlayer5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												lblPlayer6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addGap(41)
								.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
										.addComponent(behaviourPlayer6, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
														.addComponent(behaviourPlayer5, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer4, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer3, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(behaviourPlayer1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addContainerGap(252, Short.MAX_VALUE))))
						.addGroup(Alignment.TRAILING,
								jPanel1Layout.createSequentialGroup().addContainerGap(443, Short.MAX_VALUE)
										.addComponent(doneSelectingPlayers, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
										.addGap(26))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(61).addComponent(lblChooseMap).addGap(72)
								.addComponent(chooseMap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(386, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(
						jPanel1Layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										jPanel1Layout.createSequentialGroup().addContainerGap()
												.addComponent(lblEnterTheNumber)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(numPlayers, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18).addComponent(lblText).addGap(19)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblPlayer1)
														.addComponent(behaviourPlayer1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblPlayer2).addComponent(behaviourPlayer2,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblPlayer3)
														.addComponent(behaviourPlayer3, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(behaviourPlayer4, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPlayer4))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblPlayer5)
														.addComponent(behaviourPlayer5, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(behaviourPlayer6, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPlayer6))
												.addGap(65)
												.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
														.addComponent(lblChooseMap).addComponent(chooseMap,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addGap(37).addComponent(doneSelectingPlayers).addGap(29)));

		jPanel1.setLayout(jPanel1Layout);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		pack();
		Utility.writeLog("***********Single Mode begins  *************");
		// JComboBox comboBox = new JComboBox();
		numPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				JComboBox cb1 = (JComboBox) event.getSource();
				noOfPlayers = (Integer) cb1.getSelectedItem();

				if (noOfPlayers == 3) {
					lblText.setVisible(true);
					behaviourPlayer1.setVisible(true);
					lblPlayer1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer2.setVisible(true);
					behaviourPlayer3.setVisible(true);
					lblPlayer3.setVisible(true);
					lblPlayer4.setVisible(false);
					behaviourPlayer4.setVisible(false);
					lblPlayer5.setVisible(false);
					behaviourPlayer5.setVisible(false);
					lblPlayer6.setVisible(false);
					behaviourPlayer6.setVisible(false);
				}
				if (noOfPlayers == 4) {
					lblText.setVisible(true);
					behaviourPlayer1.setVisible(true);
					lblPlayer1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer2.setVisible(true);
					behaviourPlayer3.setVisible(true);
					lblPlayer3.setVisible(true);
					lblPlayer4.setVisible(true);
					behaviourPlayer4.setVisible(true);
					lblPlayer5.setVisible(false);
					behaviourPlayer5.setVisible(false);
					lblPlayer6.setVisible(false);
					behaviourPlayer6.setVisible(false);
				}
				if (noOfPlayers == 5) {
					lblText.setVisible(true);
					behaviourPlayer1.setVisible(true);
					lblPlayer1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer2.setVisible(true);
					behaviourPlayer3.setVisible(true);
					lblPlayer3.setVisible(true);
					lblPlayer4.setVisible(true);
					lblPlayer4.setVisible(true);
					behaviourPlayer4.setVisible(true);
					lblPlayer5.setVisible(true);
					behaviourPlayer5.setVisible(true);
					lblPlayer6.setVisible(false);
					behaviourPlayer6.setVisible(false);
				}
				if (noOfPlayers == 6) {
					lblText.setVisible(true);
					behaviourPlayer1.setVisible(true);
					lblPlayer1.setVisible(true);
					behaviourPlayer2.setVisible(true);
					lblPlayer2.setVisible(true);
					behaviourPlayer3.setVisible(true);
					lblPlayer3.setVisible(true);
					lblPlayer4.setVisible(true);
					behaviourPlayer4.setVisible(true);
					lblPlayer5.setVisible(true);
					behaviourPlayer5.setVisible(true);
					lblPlayer6.setVisible(true);
					behaviourPlayer6.setVisible(true);
				}

				behaviourPlayer1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 1)
							behaviourPlayers.remove(0);
						behaviourPlayers.add(0, (String) behaviourPlayer1.getSelectedItem());
					}
				});
				behaviourPlayer2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 2)
							behaviourPlayers.remove(1);
						behaviourPlayers.add(1, (String) behaviourPlayer2.getSelectedItem());
					}
				});
				behaviourPlayer3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 3)
							behaviourPlayers.remove(2);
						behaviourPlayers.add(2, (String) behaviourPlayer3.getSelectedItem());
					}
				});
				behaviourPlayer4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 4)
							behaviourPlayers.remove(3);
						behaviourPlayers.add(3, (String) behaviourPlayer4.getSelectedItem());
					}
				});
				behaviourPlayer5.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 5)
							behaviourPlayers.remove(4);
						behaviourPlayers.add(4, (String) behaviourPlayer5.getSelectedItem());
					}
				});
				behaviourPlayer6.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (behaviourPlayers.size() >= 6)
							behaviourPlayers.remove(5);
						behaviourPlayers.add(5, (String) behaviourPlayer6.getSelectedItem());
					}
				});
				chooseMap.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						map = (String) chooseMap.getSelectedItem();
						RiskController.isBaseMapEdited = true;
						Utility.writeLog("Map selected by user ::" + map);
						if (map.equalsIgnoreCase("3dcliff")) {
							RiskGameModel.ImageUsed="3D Cliff.bmp";}
						else if (map.equalsIgnoreCase("world")) {
							RiskGameModel.ImageUsed="World.bmp";}
						else if (map.equalsIgnoreCase("India")) {
							RiskGameModel.ImageUsed="India.bmp";}
						else if (map.equalsIgnoreCase("british columbia")) {
							RiskGameModel.ImageUsed="British Columbia.bmp";}
						else if (map.equalsIgnoreCase("europe")) {
						RiskGameModel.ImageUsed="Europe.bmp";}
					}
				});

			}

		});
	}

	/**
	 * Exit the Application.
	 *
	 * @param evt
	 *            the evt
	 */
	private void exitForm(java.awt.event.WindowEvent evt) {
		setVisible(false);
	}

	/**
	 * J button 1 action performed.
	 *
	 * @param evt
	 *            the evt
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		if (RiskGameModel.removeAllPlayer())
			System.out.println("all players removed");
		System.out.println("size of list" + noOfPlayers);
		Utility.writeLog("Number of players in Single Game Mode " + noOfPlayers);
		for (int i = 0; i < noOfPlayers; i++) {
			System.out.println("player" + (i + 1) + " " + behaviourPlayers.get(i));
			switch (behaviourPlayers.get(i)) {

			case "Human": {
				RiskGameModel.addPlayer("Player_" + (i + 1), new risk.model.strategy.Human());
				System.out.println((i + 1) + "new risk.model.strategy.Human");
				Utility.writeLog("User seleccted " + "Player_" + (i + 1) + "as " + "Human player");
				break;
			}

			case "Aggressive": {
				RiskGameModel.addPlayer("Player_" + (i + 1), new risk.model.strategy.Aggressive());
				System.out.println((i + 1) + "new risk.model.strategy.Aggressive");
				Utility.writeLog("User seleccted " + "Player_" + (i + 1) + "as " + "Aggressive player");
				break;
			}
			case "Benevolent": {
				RiskGameModel.addPlayer("Player_" + (i + 1), new risk.model.strategy.Benevolent());
				Utility.writeLog("User seleccted " + "Player_" + (i + 1) + "as " + "Benevolent player");
				System.out.println((i + 1) + "new risk.model.strategy.Benevolent");
				break;
			}
			case "Random": {
				RiskGameModel.addPlayer("Player_" + (i + 1), new risk.model.strategy.Random());
				Utility.writeLog("User seleccted " + "Player_" + (i + 1) + "as " + "Random player");
				System.out.println((i + 1) + "new risk.model.strategy.Random");
				break;
			}
			case "Cheater": {
				RiskGameModel.addPlayer("Player_" + (i + 1), new risk.model.strategy.Cheater());
				Utility.writeLog("User seleccted " + "Player_" + (i + 1) + "as " + "Cheater player");
				System.out.println((i + 1) + "new risk.model.strategy.Cheater");
				break;
			}
			}

		}
		added = true;
		if (added == true) {
			System.out.println(" added.");
			setVisible(false);
			RiskStartGameController.btnStartGame.setEnabled(true);
			Utility.writeLog("All players Added");
		}
		for (int i = 0; i < behaviourPlayers.size(); i++) {
			System.out.println(behaviourPlayers.get(i));
		}
		System.out.println("map" + map);
		RiskStartGameController riskGameController = new RiskStartGameController();
		if (map == "UserDefined") {
			riskGameController.onChooseMap();		
		}
		
		riskGameController.LoadCustomMap();
		// riskGameController.BuildContriesBasedOnContinent();
		if (riskGameController.CheckMapIsConnected()) {
			riskGameController.initializeMapVariables();
			riskGameController.CurrentGameMapEditor(riskGameController.TextForContinentAndCountries());
		}
	}

	/** Button The done selecting players. */
	private javax.swing.JButton doneSelectingPlayers;

	/** The jpanel 1 for UI */
	private javax.swing.JPanel jPanel1;
}
