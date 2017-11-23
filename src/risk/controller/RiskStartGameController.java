
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RiskStartGame.java
 *
 * Created on Nov 15, 2010, 11:13:31 AM
 */

package risk.controller;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import risk.helpers.Utility;
import risk.model.RiskGameModel;
import risk.model.Observable.RiskStartupPhaseModel;
import risk.view.RiskPhaseViewObserver;
import risk.view.RiskPlayerDominationViewObserver;

/**
 * This class is the main controller of the application and contains the logic
 * for the map editor.
 *
 * @author Team8
 */
public class RiskStartGameController extends java.awt.Frame {

	HashMap<String, String> hmCountryDetails = new HashMap<String, String>();
	HashMap<String, String> hmCurrentCountry = new HashMap<String, String>();
	public HashMap<String, String> copyhmCountryDetails = new HashMap<String, String>();
	public HashMap<String, HashMap<String, String>> hmCotinentDetails = new HashMap<String, HashMap<String, String>>();
	Vector<String> LinkedCountries = new Vector<String>();

	/**
	 * Creates new form RiskStartGame.
	 *
	 */
	public RiskStartGameController(String test) {

	}

	/**
	 * Instantiates a new risk start game controller.
	 */
	public RiskStartGameController() {

		Utility.writeLog("-------------------------------------------------------");
		Utility.writeLog("application start");
		initComponents();
		try {
			populatePredefinedTerritoryCoordinatesList();
		} catch (ParserConfigurationException | SAXException | IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		jButton1.setEnabled(false);
		setLocationRelativeTo(null);

		try {
			Sequence song = MidiSystem.getSequence(getClass().getResourceAsStream("../resources/song.mid"));
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			sequencer.setSequence(song);
			sequencer.setLoopCount(2);

		} catch (IOException exception) {
		} catch (MidiUnavailableException exception) {
		} catch (InvalidMidiDataException exception) {
		}
	}

	/**
	 * Populate predefined territory coordinates list.
	 *
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void populatePredefinedTerritoryCoordinatesList()
			throws ParserConfigurationException, SAXException, IOException {
		predefinedTerritoryCoordinatesList = new ArrayList<String>();
		File locationsXml = new File(Utility.getPathforFile("Locations.xml"));
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(locationsXml);
		NodeList countryList;
		Node countryNode;
		countryList = doc.getElementsByTagName("Country");

		for (int countryIndex = 0; countryIndex < countryList.getLength(); countryIndex++) {
			countryNode = (Node) countryList.item(countryIndex);
			predefinedTerritoryCoordinatesList
					.add(countryNode.getAttributes().getNamedItem("coordinate").getNodeValue());
		}
	}

	/**
	 * Update predefined territory coordinates list.
	 *
	 */
	private void updatePredefinedTerritoryCoordinatesList(String coordinate) {
		predefinedTerritoryCoordinatesList.remove(coordinate);
	}

	/**
	 * Gets the from predefined territory coordinates list.
	 *
	 * @return the from predefined territory coordinates list
	 */
	private String getFromPredefinedTerritoryCoordinatesList() {
		String coordinate = predefinedTerritoryCoordinatesList.get(0);
		predefinedTerritoryCoordinatesList.remove(0);
		return coordinate;
	}

	/**
	 * Initiates the observers.
	 */
	private void initObservers() {

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 */
	public void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setBackground(new java.awt.Color(1, 1, 1));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent event) {
				exitForm(event);
			}
		});

		jPanel1.setBackground(new java.awt.Color(1, 1, 1));
		jPanel1.setName("jPanel1"); // NOI18N

		jButton1.setText("Start Game");
		jButton1.setName("jButton1"); // NOI18N
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				jButton1ActionPerformed(event);
			}
		});

		jButton3.setText("Exit");
		jButton3.setName("jButton3"); // NOI18N
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				jButton3ActionPerformed(event);
			}
		});

		jButton2.setText("Add RiskPlayer");
		jButton2.setName("jButton2"); // NOI18N
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				jButton2ActionPerformed(event);
			}
		});

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(Utility.getImagePath("risk-logo.jpg")))); // NOI18N
		jLabel1.setName("jLabel1");

		JButton btnChooseMap = new JButton();
		btnChooseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					userDefinedContinentList.clear();
					hmCountryDetails.clear();
					generateChooseMapPanel();
					populateMapPanel();
					initializeMapVariables();
					RiskController.isBaseMapEdited = true;
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}

		});
		btnChooseMap.setText("Choose Map");
		btnChooseMap.setName("jButton1");
		btnChooseMap.setEnabled(true);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel1)
										.addContainerGap(43, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(109)
										.addGroup(jPanel1Layout.createParallelGroup(Alignment.CENTER)
												.addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
												.addComponent(jButton3, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
												.addComponent(btnChooseMap, GroupLayout.PREFERRED_SIZE, 113,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
										.addGap(87)))));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnChooseMap).addGap(12)
						.addComponent(jButton1).addPreferredGap(ComponentPlacement.RELATED).addComponent(jButton2)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(jButton3)
						.addContainerGap(42, Short.MAX_VALUE)));
		jPanel1.setLayout(jPanel1Layout);

		add(jPanel1, java.awt.BorderLayout.CENTER);

		pack();

		initializeCurrentGameMap();
	}

	/**
	 * Populate map panel.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void populateMapPanel() throws IOException {
		String strCurrentMapLine;
		BufferedReader brCurrentGameMap = new BufferedReader(new InputStreamReader(
				RiskStartGameController.class.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"))));
		Boolean reachedContinents = false;
		while ((strCurrentMapLine = brCurrentGameMap.readLine()) != null) {
			if (strCurrentMapLine.equals("[Continents]"))
				reachedContinents = true;

			if (reachedContinents) {
				if (!strCurrentMapLine.equals(";;"))
					mapEditTextArea.append(strCurrentMapLine + "\n");
			}
		}
		brCurrentGameMap.close();
		generateMapFrame.repaint();
		scrollTextAreaPanel.repaint();
	}

	/**
	 * Initialize current game map.
	 */
	private void initializeCurrentGameMap() {
		try {
			File currentGameMap = new File(Utility.getMapPathforFile("CurrentGameMap.map"));
			BufferedWriter brCurrentMapModifier = new BufferedWriter(new FileWriter(currentGameMap, false));
			brCurrentMapModifier.write("");
			brCurrentMapModifier.close();
		} catch (IOException exception) {

			exception.printStackTrace();
		}
	}

	/**
	 * Exit the Application.
	 *
	 */
	private void exitForm(java.awt.event.WindowEvent event) {
		System.exit(0);
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent event) {
		System.exit(0);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent event) {
		RiskAddPlayerController add = new RiskAddPlayerController();
		add.setVisible(true);
	}

	/**
	 * Creates the JFrame and JPanels within on the click on Choose Map Button.
	 * Uses GridBagLayout for the components within.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void generateChooseMapPanel() throws IOException {

		generateMapFrame = new JFrame("Generate Map");
		generateMapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		generateMapFrame.setResizable(false);

		Container pane = generateMapFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		pane.setComponentOrientation(ComponentOrientation.UNKNOWN);

		GridBagConstraints gridbagconstraints = new GridBagConstraints();

		scrollTextAreaPanel = new JPanel();
		gridbagconstraints.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstraints.weightx = 30;
		gridbagconstraints.ipady = 400;
		gridbagconstraints.gridx = 1;
		gridbagconstraints.gridy = 0;
		mapEditTextArea = new JTextArea("", 200, 120);
		mapEditTextArea.setEditable(false);
		scrollTextAreaPanel.add(new JScrollPane(mapEditTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		pane.add(scrollTextAreaPanel, gridbagconstraints);

		JPanel inputLinePanel = new JPanel();
		mapEditTextField = new JTextField("", 60);
		inputLinePanel.add(mapEditTextField);
		gridbagconstraints.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstraints.weightx = 1;
		gridbagconstraints.gridx = 1;
		gridbagconstraints.ipady = 50;
		gridbagconstraints.gridy = 2;
		pane.add(inputLinePanel, gridbagconstraints);

		JPanel buttonsPanel = new JPanel();
		addButton = new JButton("Add Country");
		addButton.setVisible(false);
		finishButton = new JButton("Finish");
		// finishButton.setVisible(false);
		addContinent = new JButton("Add Continent");
		continentDoneButton = new JButton("Done");
		jButtonCustomMap = new javax.swing.JButton("Custom Map");
		jButtonCustomMap.setName("jButtonCustomMap");
		jTecxtCustomMap = new javax.swing.JTextField("", 40);
		jLabelCustomMap = new javax.swing.JLabel("Map File Name");
		inputLinePanel.add(jLabelCustomMap);
		inputLinePanel.add(jTecxtCustomMap);

		jButtonCustomMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				CustomMapButtonPressed(exception);
			}
		});
		/**
		 * Handles the click event for the add button in the Choose Map Panel.
		 */
		addContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				continentButtonPressed(exception);
			}
		});
		continentDoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				continentDoneButtonPressed(exception);
			}
		});
		/**
		 * Handles the click event for the add button in the Choose Map Panel.
		 */
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				addButtonPressed(exception);
			}
		});

		/**
		 * Handles the click event for the finish button in the Choose Map
		 * Panel.
		 */
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exception) {
				finishButtonPressed(exception, generateMapFrame);
			}

		});

		buttonsPanel.add(addButton);
		buttonsPanel.add(finishButton);
		buttonsPanel.add(addContinent);
		buttonsPanel.add(continentDoneButton);
		buttonsPanel.add(jButtonCustomMap);
		gridbagconstraints.weightx = 0.5;
		gridbagconstraints.gridx = 1;
		gridbagconstraints.ipady = 50;
		gridbagconstraints.gridy = 3;
		pane.add(buttonsPanel, gridbagconstraints);

		notePanel = new JPanel();
		noteLabel = new JLabel("");
		notePanel.add(noteLabel);
		gridbagconstraints.weightx = 1;
		gridbagconstraints.gridx = 1;
		gridbagconstraints.ipady = 50;
		gridbagconstraints.gridy = 4;
		pane.add(notePanel, gridbagconstraints);

		JPanel formatInfoPanel = new JPanel();
		JLabel formatInforLabel = new JLabel(
				"Please enter the data in the following format - '<Territory>,<Continent>,<Adjacent_Country1>,<Adjacent_Country2>...'");
		formatInfoPanel.add(formatInforLabel);
		gridbagconstraints.weightx = 1;
		gridbagconstraints.gridx = 1;
		gridbagconstraints.ipady = 50;
		gridbagconstraints.gridy = 5;
		pane.add(formatInfoPanel, gridbagconstraints);

		generateMapFrame.pack();
		generateMapFrame.setVisible(true);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent event) {

		RiskController.ShowGUI();
		// Open the Phase View
		setVisible(false);
	}

	private void continentButtonPressed(ActionEvent exception) {
		if (validateContinent(mapEditTextField.getText()))
			addUserDefinedContinent(mapEditTextField.getText());
		else {
			noteLabel.setText("Invalid Continent.");
			notePanel.repaint();
		}

	}

	void CustomMapButtonPressed(ActionEvent exception) {

		continentDoneButton.setVisible(false);
		addContinent.setVisible(false);
		addButton.setVisible(true);
		
		String Filename = jTecxtCustomMap.getText() + ".map";
		String nextLine = "";
		hmCountryDetails.clear();
		userDefinedContinentList.clear();
		boolean done;
		InputStream MapInput = RiskGameModel.class.getResourceAsStream(Utility.getMapPath(Filename));

		Scanner ContinentTerritoryScanner = new Scanner(MapInput);

		while (ContinentTerritoryScanner.hasNextLine()) {
			nextLine = ContinentTerritoryScanner.nextLine();
			if (nextLine.equals("[Continents]")) {
				nextLine = ContinentTerritoryScanner.nextLine();
				done = false;
				do {
					userDefinedContinentList.put(nextLine.split("=")[0], nextLine.split("=")[1]);
					// mapEditTextArea.append(nextLine.split("=")[0] + "=" +
					// nextLine.split("=")[1] + "\n");
					// mapEditTextField.setText("");
					nextLine = ContinentTerritoryScanner.nextLine();
					if (nextLine.equals(""))
						done = true;
				} while (done == false);
			}
			if (nextLine.equals("[Territories]")) {
				done = false;
				nextLine = ContinentTerritoryScanner.nextLine();

				do {
					if (!nextLine.equals(""))
						hmCountryDetails.put(nextLine.split(",")[0], nextLine);

					nextLine = ContinentTerritoryScanner.hasNext() ? ContinentTerritoryScanner.nextLine() : ";;";
					if (nextLine.equals(";;"))
						done = true;
				} while (done == false);
			}
		}
		updateMapEditTextArea();
	}

	private boolean validateContinent(String continent) {
		return continent.trim().equals("") ? false : true;
	}

	/**
	 * Adds the user defined continent.
	 *
	 */
	public void addUserDefinedContinent(String continent) {
		Random continentValueRandom = new Random();
		int continentValue;
		this.userDefinedContinentList.put(continent, String.valueOf(continentValue = continentValueRandom.nextInt(7)));
		mapEditTextArea.append(continent + "=" + continentValue + "\n");
		mapEditTextField.setText("");
	}

	/**
	 * Performs final activities for creating and saving the newly created Map
	 * for the current game. Creating the file CurrentGameMap.map and
	 * initializes it till '[Territories]' from the earth map. Copies the
	 * contents of the JTextArea in Choose Map Panel and save the file in the
	 * Risk.resources package.
	 */
	private void finishButtonPressed(ActionEvent exception, JFrame mainwindow) {

		// copyhmCountryDetails = (HashMap<String, String>)
		// hmCountryDetails.clone();

		String NotLinkedCountries = "";
		BuildContriesBasedOnContinent();
		boolean flag = false;
		for (Entry<String, HashMap<String, String>> entry : hmCotinentDetails.entrySet()) {
			copyhmCountryDetails = (HashMap<String, String>) entry.getValue().clone();
			LinkedCountries.clear();
			hmCurrentCountry = entry.getValue();
			if (CheckCountriesConnected(hmCurrentCountry)) {
				System.out.println("all countries connected");
				flag = true;
			} else {
				flag = false;
				for (String value : copyhmCountryDetails.values()) {
					NotLinkedCountries += value.split(",")[0] + ",";
				}
				System.out.println(NotLinkedCountries.substring(0, NotLinkedCountries.length() - 1) + " of "
						+ entry.getKey() + " are not connected");
				JOptionPane.showMessageDialog(null, NotLinkedCountries.substring(0, NotLinkedCountries.length() - 1)
						+ " of " + entry.getKey() + " are not connected", "Alert", JOptionPane.INFORMATION_MESSAGE);
				break;
			}

		}

		if (flag) {
			CurrentGameMapEditor(mapEditTextArea.getText());
			mainwindow.dispose();
		}

	}

	private void BuildContriesBasedOnContinent() {
		for (Entry<String, String> entry : hmCountryDetails.entrySet()) {
			if (hmCotinentDetails.containsKey(entry.getValue().split(",")[3])) {
				HashMap<String, String> CountryDetails = hmCotinentDetails.get(entry.getValue().split(",")[3]);
				CountryDetails.put(entry.getValue().split(",")[0], entry.getValue());
			} else {
				HashMap<String, String> CountryDetails = new HashMap<String, String>();
				CountryDetails.put(entry.getValue().split(",")[0], entry.getValue());
				hmCotinentDetails.put(entry.getValue().split(",")[3], CountryDetails);
			}
		}
	}

	/**
	 * Reads the base earth map into string builder to be used in later methods.
	 */
	public void initializeMapVariables() {
		BufferedReader brEarthMapReader = new BufferedReader(new InputStreamReader(
				RiskStartGameController.class.getResourceAsStream(Utility.getMapPath("BaseEarthMap.map"))));
		String baseMapLine;
		try {
			while ((baseMapLine = brEarthMapReader.readLine()) != null) {
				sbBaseMapString.append(baseMapLine + "\n");
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Creates a new instance of the CurrentGameMap.map file and initializes
	 * with text will '[Territories]' from the base earth map. Appends the
	 * contents of the JTextArea in Choose Map Panel to the newly created file
	 *
	 * @param editTextArea
	 *            the edit text area
	 * 
	 */
	private void CurrentGameMapEditor(String editTextArea) {
		String EarthMapStaticContent;
		File currentGameMap;

		try {
			currentGameMap = new File(Utility.getMapPathforFile("CurrentGameMap.map"));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentGameMap, false));
			EarthMapStaticContent = sbBaseMapString.substring(0, sbBaseMapString.indexOf("[Continents]")) + "\n";

			bufferedWriter.write(EarthMapStaticContent + editTextArea + "\n;;");
			bufferedWriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * Insert adjacent countries info wrapper.
	 *
	 * @param editTextArea
	 *            the edit text area
	 * 
	 * @return the string
	 */
	private String insertAdjacentCountriesInfoWrapper(String editTextArea) {
		String[] editTextAreaArray = editTextArea.split("\n");
		StringBuilder adjacentCountryBuilder = new StringBuilder();

		// adjacentCountryBuilder.append("[Adjacents]");
		for (String inputLine : editTextAreaArray) {
			adjacentCountryBuilder.append(insertAdjacentCountriesInfo(inputLine) + "\n");
		}

		return adjacentCountryBuilder.toString();
	}

	/**
	 * Processes user input from the JTextField. Calls another method to fetch
	 * and insert coordinates for the territory. Appends the input with
	 * coordinates to the JTextArea in Choose Map Panel
	 * 
	 * @param event
	 *            ActionEvent passed for the button click event.
	 * 
	 */
	private void addButtonPressed(ActionEvent event) {
		if (RiskStartGameController.predefinedTerritoryCoordinatesList.size() == 0) {
			noteLabel.setText("Cannot add anymore country, max is 42 inclusing adjacents");
			return;
		}
		if (validateContinentLineText(mapEditTextField.getText())) {

			// below line not used. instead loading the country details through
			// hashmap
			sbMapTerritoryContents.append(mapEditTextInsertCoordinates(mapEditTextField.getText()) + "\n");

			updateMapEditTextArea();
			noteLabel.setText("");
			notePanel.repaint();
		} else {
			noteLabel.setText("Invalid Continent, choose from what was defined before.");
			notePanel.repaint();
		}
		mapEditTextField.setText("");
	}

	/**
	 * Update map edit text area.
	 */
	private void updateMapEditTextArea() {
		sbMapContinentContents = new StringBuilder();
		sbMapContinentContents.append("[Continents]\n");
		for (String userDefinedContinentKey : this.userDefinedContinentList.keySet())
			sbMapContinentContents.append(
					userDefinedContinentKey + "=" + this.userDefinedContinentList.get(userDefinedContinentKey) + "\n");
		sbMapContinentContents.append("\n\n[Territories]\n\n");
		// sbMapContinentContents.append(sbMapTerritoryContents.toString());

		for (Object value : hmCountryDetails.values()) {
			sbMapContinentContents.append(value.toString() + "\n"); // changed
																	// the
																	// logic for
																	// writting
																	// the
																	// current
																	// map -
																	// goutham
		}

		mapEditTextArea.setText(sbMapContinentContents.toString());
		scrollTextAreaPanel.repaint();
	}

	public boolean CheckCountriesConnected(HashMap<String, String> countrylistdetials) {

		int i = 0;

		java.util.Iterator<Entry<String, String>> entries = countrylistdetials.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, String> entry = entries.next();

			if (i == 0) {
				LinkedCountries.addElement(entry.getValue().toString().split(",")[0]);
				AddCountriesToLinkedNodeList(entry.getValue().toString());
				copyhmCountryDetails.remove(entry.getKey());
			} else {
				if (LinkedCountries.contains(entry.getValue().toString().split(",")[0])) {
					AddCountriesToLinkedNodeList(entry.getValue().toString());
					copyhmCountryDetails.remove(entry.getKey());
				} else
					CheckAndAddLinkedCountry(entry.getValue().toString());
			}
			i++;
		}

		if (copyhmCountryDetails.isEmpty())
			return true;

		return false;
	}

	private void CheckAndAddLinkedCountry(String countryDetail) {
		for (int i = 4; i < countryDetail.split(",").length; i++) {
			if (LinkedCountries.contains(countryDetail.split(",")[i])
					&& hmCurrentCountry.containsKey(countryDetail.split(",")[i])) {
				LinkedCountries.addElement(countryDetail.split(",")[0]);
				copyhmCountryDetails.remove(countryDetail.split(",")[0]);
			}
		}
	}

	private void AddCountriesToLinkedNodeList(String countries) {
		for (int i = 4; i < countries.split(",").length; i++) {
			if (hmCurrentCountry.containsKey(countries.split(",")[i]))
				LinkedCountries.addElement(countries.split(",")[i]);
		}
	}

	/**
	 * Continent done button pressed.
	 *
	 */
	private void continentDoneButtonPressed(ActionEvent e) {
		continentDoneButton.setVisible(false);
		addContinent.setVisible(false);
		finishButton.setVisible(true);
		addButton.setVisible(true);

		hmCountryDetails.clear();
		updateMapEditTextArea();

	}

	/**
	 * Validate continent line text.
	 *
	 * @param mapInputLineText
	 *            the map input line text
	 * 
	 * @return true, if successful
	 */
	private boolean validateContinentLineText(String mapInputLineText) {
		String userDefinedContinent = mapInputLineText.split(",").length > 1 ? mapInputLineText.split(",")[1] : "";
		return this.userDefinedContinentList.containsKey(userDefinedContinent) ? true : false;
	}

	/**
	 * This method will find the coordinates and continent of the adjacent
	 * countries supplied by the user and append it to JTextArea.
	 *
	 * @param mapInputLineText
	 *            the map input line text
	 * 
	 * @return the string
	 */
	private String insertAdjacentCountriesInfo(String mapInputLineText) {
		StringBuilder adjacentCountriesInfo = new StringBuilder();
		String ajacentCountryInfo = "";
		int reachAdjacent = 0;
		String currentContinent = "", currentCountry = "";
		for (String component : mapInputLineText.split(",")) {
			if (reachAdjacent == 0)
				currentCountry = component;
			if (reachAdjacent == 3)
				currentContinent = component;
			if (reachAdjacent > 3) {// Skip the country, coordinated and the
									// continent to reach adjacent territory
									// only.
				try {
					ajacentCountryInfo = getAdjacentCountryInfo(component, currentCountry, currentContinent); // format
																												// -
					// ajdacentcountry,coordinates,continent,currentcountry
					AddCountryToHashMap(ajacentCountryInfo);

					adjacentCountriesInfo.append(ajacentCountryInfo + "\n");
				} catch (ParserConfigurationException | SAXException | IOException exception) {
					// TODO Auto-generated catch block
					exception.printStackTrace();
				}
			}
			reachAdjacent++;
		}

		return adjacentCountriesInfo.toString();

	}

	/**
	 * This method will find the coordinates and continent of the adjacent
	 * countries supplied by the user and append it to JTextArea.
	 *
	 * @param adjacentTerritory
	 *            the adjacent territory
	 * 
	 * @param currentCountry
	 *            the current country
	 * 
	 * @param currentContinent
	 *            the current continent
	 * 
	 * @return String with coordinates and continent for adjacent country -
	 *         adjacentTerritory.
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             the SAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public String getAdjacentCountryInfo(String adjacentTerritory, String currentCountry, String currentContinent)
			throws ParserConfigurationException, SAXException, IOException {
		return adjacentTerritory + "," + this.getFromPredefinedTerritoryCoordinatesList() + "," + currentContinent + ","
				+ currentCountry;
	}

	/**
	 * To find the territory which the user supplied in his/her input text. To
	 * find the coordinates of the supplied territory from the baseMap -
	 * EarthMap. Append the found coordinates to the input text and insert them
	 * in the JTextArea in Choose Map Panel.
	 *
	 * @param mapInputLineText
	 *            the map input line text
	 * 
	 * @return String, the complete line text with the coordinates inserted
	 *         after the territory.
	 */
	public String mapEditTextInsertCoordinates(String mapInputLineText) {

		StringBuilder sbInputWithCoordinates = new StringBuilder();
		String territory = mapInputLineText.substring(0, mapInputLineText.indexOf(','));
		String coordinates = fetchCoordinates(territory);

		// CurrentCountry,coordinates,Continent,adjacents...
		mapInputLineText = mapInputLineText.substring(0, mapInputLineText.indexOf(',') + 1) + coordinates
				+ mapInputLineText.substring(mapInputLineText.indexOf(','), mapInputLineText.length());
		sbInputWithCoordinates.append(mapInputLineText + "\n");

		AddCountryToHashMap(mapInputLineText);
		// Insert lines below for adjacents
		sbInputWithCoordinates.append(insertAdjacentCountriesInfo(mapInputLineText));
		return sbInputWithCoordinates.toString();

	}

	public void AddCountryToHashMap(String mapInputLineText) {
		String adjustent = "";
		if (!hmCountryDetails.containsKey(mapInputLineText.split(",")[0]))
			hmCountryDetails.put(mapInputLineText.split(",")[0], mapInputLineText);
		else {
			for (int i = 4; i < mapInputLineText.split(",").length; i++) {
				adjustent = adjustent + mapInputLineText.split(",")[i] + ",";
			}
			hmCountryDetails.put(mapInputLineText.split(",")[0], hmCountryDetails.get(mapInputLineText.split(",")[0])
					+ "," + adjustent.substring(0, adjustent.length() - 1));
		}
	}

	/**
	 * Fetch the coordinates of the territories given to this method. Call the
	 * recursive search function to perform the search.
	 *
	 * @param territory
	 *            the territory
	 * 
	 * @return = String, the coordinates of the territory supplied in string -
	 *         e.g - ,XX,YY, - format.
	 */
	public String fetchCoordinates(String territory) {
		String coordinates = "";
		// // initializeMapVariables();
		// coordinates = recursiveSearchCoordinates(sbBaseMapString.toString(),
		// territory);
		// if (coordinates.equals(""))
		coordinates = getFromPredefinedTerritoryCoordinatesList();
		return coordinates;
	}

	/**
	 * The base earth map has individual territories specified in multiple
	 * places. At times as countries with coordinates next to it and at times as
	 * an adjacent country to some other country during which it does not have
	 * coordinates next to it. Hence a recursive search is needed to find only
	 * that particular instance of country which has coordinates next to it in
	 * the base map. This function performs that recursive search needed.
	 *
	 * @param baseMapString
	 *            the base map string
	 * 
	 * @param territory
	 *            the territory
	 * 
	 * @return coordinates, returns the coordinates of the needed territory in
	 *         string - ,XX/X,YY/Y, - format.
	 */
	private String recursiveSearchCoordinates(String baseMapString, String territory) {
		int index;
		index = baseMapString.indexOf(territory);
		int updatedindex;
		if (index != -1) {
			if (isCoordinatesNextToIt(baseMapString, index, territory)) {
				fetchedCoordinates = fetchCoordinates(index + territory.length(), baseMapString); // alaska-,27,29,-abc
																									// :
																									// pass
																									// char
																									// within
																									// colon.
				return fetchedCoordinates;
			} else {
				updatedindex = updateIndexToNextTerritory(baseMapString, territory, index);
				baseMapString = baseMapString.substring(updatedindex, baseMapString.length());
				recursiveSearchCoordinates(baseMapString, territory);
			}
		} else
			return fetchedCoordinates;
		return fetchedCoordinates;
	}

	/**
	 * The recursive search take a smaller text every iteration till the correct
	 * instance of territory is found. This method sends the begin index for the
	 * new cut-shorted string for the next recursive search.
	 *
	 * @param baseMapString
	 *            the base map string
	 * 
	 * @param territory
	 *            the territory
	 * 
	 * @param index
	 *            the index
	 * @return begin index, for the upcoming recursive search.
	 */
	private int updateIndexToNextTerritory(String baseMapString, String territory, int index) {
		if (baseMapString.charAt(index + territory.length()) == ','
				|| String.valueOf(baseMapString.charAt(index + territory.length())) == "")
			return index + territory.length() + 1;
		else
			return index + territory.length();
	}

	/**
	 * Once the correct instance of the word territory is found in the base
	 * earth map, this method will read the coordinates next to it.
	 * 
	 * @param index
	 *            index of the correct instance of the territory in the base
	 *            map.
	 * 
	 * @param baseMapString
	 *            the base map in string format.
	 * 
	 * @return coordinates, in the format XX/X,YY/Y.
	 */
	private String fetchCoordinates(int index, String baseMapString) {
		StringBuilder coordinates = new StringBuilder();
		for (char a : baseMapString.substring(index, baseMapString.length() - 1).toCharArray()) {
			if (!Character.isDigit(a)) {
				if (a != ',' && Character.toString(a) != "")
					break;
			}
			coordinates.append(a); // Like ,29,39,
		}
		return coordinates.toString();
	}

	/**
	 * Checks whether the instance of territory found in the base map has
	 * coordinates next to it or not.
	 * 
	 * @param baseMapString
	 *            base earth map in string format.
	 * 
	 * @param index
	 *            index of the first occurrence 'territory' in the base map
	 * 
	 * @param territory
	 *            the territory supplied by the user.
	 * 
	 * @return Boolean, true if this is the instance of territory with
	 *         coordinates next to it else False.
	 */
	private boolean isCoordinatesNextToIt(String baseMapString, int index, String territory) {
		String subbaseMapString = baseMapString.substring(index);
		if (Character.isDigit(subbaseMapString.charAt(territory.length() + 1))) // Brazil,10,10,
			return true;
		else
			return false;
	}

	/**
	 * To check of the user has inserted the text in the proper format. Check if
	 * corresponding territories and continents exist.
	 * 
	 * @param mapInputLineText
	 *            the input text from the user in the needed format specifying
	 *            the adjacent countries.
	 * @return Boolean, returns true if input text is valid, else false.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public Boolean validateMapLineInputText(String mapInputLineText)
			throws ParserConfigurationException, SAXException, IOException {
		Boolean isValid = false;
		if (mapInputLineText.split(",").length >= 3) {
			File locationsXml = new File("src/risk/resources/Locations.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(locationsXml);
			NodeList countryList, continentList;
			Boolean isCountryValid, isContinentValid;
			for (int location = 0; location < mapInputLineText.split(",").length; location++) {
				if (location == 0 || location > 1) {
					// Need to present in the locations xml as a country
					countryList = doc.getElementsByTagName("Country");
					isCountryValid = false;
					for (int countryIndex = 0; countryIndex < countryList.getLength(); countryIndex++) {
						Node countryNode = (Node) countryList.item(countryIndex);
						/*
						 * if (countryNode.getTextContent().toLowerCase()
						 * .equals((mapInputLineText.split(",")[location]).
						 * toLowerCase())) isCountryValid = true;
						 */
					}
					if (!isCountryValid) {
						noteLabel.setText(
								"The Country " + mapInputLineText.split(",")[location] + " mentioned is not correct");
						isValid = false;
						break;
					}

				} else if (location == 1) {
					// Need to present in the locations xml as a country
					continentList = doc.getElementsByTagName("Continent");
					isContinentValid = false;
					for (int continentIndex = 0; continentIndex < continentList.getLength(); continentIndex++) {
						Node continentNode = (Node) continentList.item(continentIndex);
						/*
						 * if (continentNode.getTextContent().toLowerCase()
						 * .equals((mapInputLineText.split(",")[location]).
						 * toLowerCase())) isContinentValid = true;
						 */
					}
					if (!isContinentValid) {
						noteLabel.setText("The Continent mentioned is not correct");
						isValid = false;
						break;
					}
				}
				isValid = true;
			}
		} else
			return isValid; // Need to have atleast 3 parts - territory,
							// continent, one adjacent country.
		return isValid;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the command line arguments
	 * 
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new RiskStartGameController().setVisible(true);
			}
		});
	}

	public static javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	javax.swing.JLabel jLabelCustomMap;
	javax.swing.JButton jButtonCustomMap;
	javax.swing.JTextField jTecxtCustomMap;
	private javax.swing.JTextArea mapEditTextArea;
	private javax.swing.JTextField mapEditTextField;
	private String fetchedCoordinates;
	private JPanel scrollTextAreaPanel;
	private JPanel notePanel;
	private JLabel noteLabel;
	private StringBuilder sbBaseMapString = new StringBuilder();
	private JFrame generateMapFrame;
	private StringBuilder sbAdjacentCountryInfo = new StringBuilder();
	private static List<String> predefinedTerritoryCoordinatesList;
	private HashMap<String, String> userDefinedContinentList = new HashMap<String, String>();
	private JButton addButton;
	private JButton finishButton;
	private JButton addContinent;
	private JButton continentDoneButton;
	private StringBuilder sbMapTerritoryContents = new StringBuilder();
	private StringBuilder sbMapContinentContents = new StringBuilder();

}
