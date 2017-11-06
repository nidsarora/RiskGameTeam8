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
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import risk.helpers.Utility;

/**
 * This class is the main controller of the application and contains the logic
 * for the map editor
 * 
 * @author Team8
 */
public class RiskStartGameController extends java.awt.Frame {

	static HashMap<String, String> HmAdjusentadded = new HashMap<String, String>();

	/** Creates new form RiskStartGame */

	public RiskStartGameController(String test) {

	}

	public RiskStartGameController() {
		Utility.writeLog("-------------------------------------------------------");
		Utility.writeLog("application start");

		initComponents();
		jButton1.setEnabled(false);
		setLocationRelativeTo(null);

		try {
			Sequence song = MidiSystem.getSequence(getClass().getResourceAsStream("../resources/song.mid"));
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			sequencer.setSequence(song);
			sequencer.setLoopCount(2);
			// sequencer.start();

		} catch (IOException e) {
		} catch (MidiUnavailableException e) {
		} catch (InvalidMidiDataException e) {
		}
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
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});

		jPanel1.setBackground(new java.awt.Color(1, 1, 1));
		jPanel1.setName("jPanel1"); // NOI18N

		jButton1.setText("Start Game");
		jButton1.setName("jButton1"); // NOI18N
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton3.setText("Exit");
		jButton3.setName("jButton3"); // NOI18N
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton2.setText("Add RiskPlayer");
		jButton2.setName("jButton2"); // NOI18N
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(Utility.getImagePath("risk-logo.jpg")))); // NOI18N
		jLabel1.setName("jLabel1");

		JButton btnChooseMap = new JButton();
		btnChooseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					generateChooseMapPanel();
					populateMapPanel();
					initializeMapVariables();
				} catch (IOException e) {
					e.printStackTrace();
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

		// This is clear the CurrentGameMap of previous games details
		// clearCurrentGameMap();
		initializeCurrentGameMap();
	}

	private void clearCurrentGameMap() {
		try {
			File currentGameMap = new File(Utility.getMapPathforFile("CurrentGameMap.map"));
			BufferedWriter brCurrentMapModifier = new BufferedWriter(new FileWriter(currentGameMap));
			brCurrentMapModifier.write("", 0, "".length());
			brCurrentMapModifier.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void populateMapPanel() throws IOException {
		// TODO Auto-generated method stub
		StringBuilder sbCurrentMap = new StringBuilder();
		String strCurrentMapLine;
		BufferedReader brCurrentGameMap = new BufferedReader(new InputStreamReader(
				RiskStartGameController.class.getResourceAsStream(Utility.getMapPath("CurrentGameMap.map"))));
		Boolean reachedTerritories = false;
		while ((strCurrentMapLine = brCurrentGameMap.readLine()) != null) {
			if (reachedTerritories) {
				if (!strCurrentMapLine.equals("[Adjacents]"))// Do not add new
																// line after
																// the last
																// line.
					mapEditTextArea.append(strCurrentMapLine + "\n");
				else
					break;
			}
			if (strCurrentMapLine.equals("[Territories]"))
				reachedTerritories = true;
		}
		brCurrentGameMap.close();
		generateMapFrame.repaint();
		scrollTextAreaPanel.repaint();
		Utility.writeLog("Map Editor is provided for the user.");
	}

	private void initializeCurrentGameMap() {
		try {
			StringBuilder sbEarthMapReader = new StringBuilder();
			String strEarthMapLine;
			BufferedReader brEarthMapReader = new BufferedReader(new InputStreamReader(RiskStartGameController.class
					.getResourceAsStream(Utility.getMapPath("BaseEarthMapWithoutCoordinates.map"))));
			File currentGameMap = new File(Utility.getMapPathforFile("CurrentGameMap.map"));
			BufferedWriter brCurrentMapModifier = new BufferedWriter(new FileWriter(currentGameMap));

			while ((strEarthMapLine = brEarthMapReader.readLine()) != null) {
				if (!strEarthMapLine.equals(";;"))// Do not add new line after
													// the last line.
					sbEarthMapReader.append(strEarthMapLine + "\n");
				else
					sbEarthMapReader.append(strEarthMapLine);
			}

			brCurrentMapModifier.write(sbEarthMapReader.toString(), 0, sbEarthMapReader.toString().length());
			brCurrentMapModifier.close();
			brEarthMapReader.close();
			Utility.writeLog("Load the entries from Base Map file");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Exit the Application */
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		RiskAddPlayerController add = new RiskAddPlayerController();
		add.setVisible(true);
	}

	/**
	 * Creates the JFrame and JPanels within on the click on Choose Map Button.
	 * Uses GridBagLayout for the components within.
	 * 
	 * @throws IOException
	 */
	public void generateChooseMapPanel() throws IOException {

		generateMapFrame = new JFrame("Generate Map");
		generateMapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		generateMapFrame.setResizable(false);

		Container pane = generateMapFrame.getContentPane();
		pane.setLayout(new GridBagLayout());
		pane.setComponentOrientation(ComponentOrientation.UNKNOWN);

		GridBagConstraints c = new GridBagConstraints();

		scrollTextAreaPanel = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 30;
		c.ipady = 400;
		c.gridx = 1;
		c.gridy = 0;
		mapEditTextArea = new JTextArea("", 200, 120);
		// mapEditTextArea.setEditable(false);
		scrollTextAreaPanel.add(new JScrollPane(mapEditTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		pane.add(scrollTextAreaPanel, c);

		JPanel inputLinePanel = new JPanel();
		mapEditTextField = new JTextField("", 120);
		inputLinePanel.add(mapEditTextField);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.ipady = 50;
		c.gridy = 2;
		pane.add(inputLinePanel, c);

		JPanel buttonsPanel = new JPanel();
		JButton addButton = new JButton("Add");
		JButton finishButton = new JButton("Finish");

		/**
		 * Handles the click event for the add button in the Choose Map Panel.
		 */
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonPressed(e);
			}
		});

		/**
		 * Handles the click event for the finish button in the Choose Map
		 * Panel.
		 */
		finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishButtonPressed(e, generateMapFrame);
			}

		});

		buttonsPanel.add(addButton);
		buttonsPanel.add(finishButton);
		c.weightx = 0.5;
		c.gridx = 1;
		c.ipady = 50;
		c.gridy = 3;
		pane.add(buttonsPanel, c);

		notePanel = new JPanel();
		noteLabel = new JLabel("");
		notePanel.add(noteLabel);
		c.weightx = 1;
		c.gridx = 1;
		c.ipady = 50;
		c.gridy = 4;
		pane.add(notePanel, c);

		JPanel formatInfoPanel = new JPanel();
		JLabel formatInforLabel = new JLabel(
				"Please enter the data in the following format - '<Territory>,<Continent>,<Adjacent_Country1>,<Adjacent_Country2>...'");
		formatInfoPanel.add(formatInforLabel);
		c.weightx = 1;
		c.gridx = 1;
		c.ipady = 50;
		c.gridy = 5;
		pane.add(formatInfoPanel, c);

		generateMapFrame.pack();
		generateMapFrame.setVisible(true);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		RiskController.ShowGUI();
		setVisible(false);
	}

	/**
	 * Performs final activities for creating and saving the newly created Map
	 * for the current game. Creating the file CurrentGameMap.map and
	 * initializes it till '[Territories]' from the earth map. Copies the
	 * contents of the JTextArea in Choose Map Panel and save the file in the
	 * Risk.resources package.
	 * 
	 * @param e,frame
	 *            Action event for the click of the Finish button. jframe to
	 *            close the parent window.
	 */
	private void finishButtonPressed(ActionEvent e, JFrame jframe) {

		CurrentGameMapEditor("");
		CurrentGameMapEditor(mapEditTextArea.getText());
		jframe.dispose();
		Utility.writeLog("map editor closed by the user");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new instance of the CurrentGameMap.map file and initializes
	 * with text will '[Territories]' from the base earth map. Appends the
	 * contents of the JTextArea in Choose Map Panel to the newly created file
	 * 
	 * @param editTextArea,
	 *            content of the JTextArea in Choose Map Panel.
	 */
	private void CurrentGameMapEditor(String editTextArea) {
		String EarthMapStaticContent;
		File currentGameMap;
		String AdjacentCountryInfo;
		HmAdjusentadded.clear();

		try {
			currentGameMap = new File(Utility.getMapPathforFile("CurrentGameMap.map"));

			// Called during initialize to empty the map of previous territories
			if (editTextArea.equals("")) {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentGameMap, false));
				EarthMapStaticContent = sbBaseMapString.substring(0,
						sbBaseMapString.indexOf("[Territories]") + String.valueOf("[Territories]").length());
				bufferedWriter.write(EarthMapStaticContent, 0, EarthMapStaticContent.length());
				bufferedWriter.close();
			} else {
				AdjacentCountryInfo = insertAdjacentCountriesInfoWrapper(editTextArea); // (mapEditTextField.getText()));
				BufferedWriter brCurrentMapModifier = new BufferedWriter(new FileWriter(currentGameMap, true));
				brCurrentMapModifier.write("\n" + AdjacentCountryInfo, 0, ("\n" + AdjacentCountryInfo).length()); // +2
																													// is
																													// for
																													// ;;
																													// appended
				brCurrentMapModifier.write(";;", 0, 2);
				brCurrentMapModifier.close();
				Utility.writeLog("write the current game map file.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String insertAdjacentCountriesInfoWrapper(String editTextArea) {
		String[] editTextAreaArray = editTextArea.split("\n");
		StringBuilder adjacentCountryBuilder = new StringBuilder();

		// adjacentCountryBuilder.append("[Adjacents]");
		for (String inputLine : editTextAreaArray) {
			HmAdjusentadded.put(inputLine.split(",")[0], inputLine);
			insertAdjacentCountriesInfo(inputLine);
		}

		Iterator itAdjusten = HmAdjusentadded.entrySet().iterator();
		while (itAdjusten.hasNext()) {
			Map.Entry pair = (Map.Entry) itAdjusten.next();
			adjacentCountryBuilder.append(pair.getValue() + "\n");
			// itAdjusten.remove(); // avoids a ConcurrentModificationException
		}

		return adjacentCountryBuilder.toString();
	}

	/**
	 * This method will find the coordinates and continent of the adjacent
	 * countries supplied by the user and append it to JTextArea.
	 * 
	 * @param mapInputLineText
	 * @return
	 */
	private void insertAdjacentCountriesInfo(String mapInputLineText) {
		StringBuilder adjacentCountriesInfo = new StringBuilder();
		String ajacentCountryInfo = "";
		int reachAdjacent = 0;

		for (int i = 4; i < mapInputLineText.split(",").length; i++) {

			if (!HmAdjusentadded.containsKey(mapInputLineText.split(",")[i])) {
				try {

					ajacentCountryInfo = getAdjacentCountryInfo(mapInputLineText.split(",")[i]);
					ajacentCountryInfo += "," + mapInputLineText.split(",")[0];
					HmAdjusentadded.put(mapInputLineText.split(",")[i], ajacentCountryInfo);
					// adjacentCountriesInfo.append(ajacentCountryInfo + "\n");
				} catch (ParserConfigurationException | SAXException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				HmAdjusentadded.put(mapInputLineText.split(",")[i],
						HmAdjusentadded.get(mapInputLineText.split(",")[i]) + "," + mapInputLineText.split(",")[0]);
				ajacentCountryInfo = HmAdjusentadded.get(mapInputLineText.split(",")[i]);
				// adjacentCountriesInfo.append(ajacentCountryInfo + "\n");
			}

		}

		// return adjacentCountriesInfo.toString();

	}

	/**
	 * This method will find the coordinates and continent of the adjacent
	 * countries supplied by the user and append it to JTextArea.
	 * 
	 * @param adjacentTerritory,
	 *            individual adjacent country to find the information for.
	 * @return String with coordinates and continent for adjacent country -
	 *         adjacentTerritory.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	private String getAdjacentCountryInfo(String adjacentTerritory)
			throws ParserConfigurationException, SAXException, IOException {
		String countryInfo = "";
		File locationsXml = new File(Utility.getPathforFile("Locations.xml"));
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(locationsXml);
		NodeList countryList;
		countryList = doc.getElementsByTagName("Country");

		for (int countryIndex = 0; countryIndex < countryList.getLength(); countryIndex++) {
			Node countryNode = (Node) countryList.item(countryIndex);
			if (countryNode.getTextContent().toLowerCase().equals((adjacentTerritory.toLowerCase()))) {
				countryInfo = adjacentTerritory + ","
						+ countryNode.getAttributes().getNamedItem("coordinate").getNodeValue() + ","
						+ countryNode.getAttributes().getNamedItem("continent").getNodeValue();
				break;
			}
		}
		return countryInfo;
	}

	/**
	 * To find the territory which the user supplied in his/her input text. To
	 * find the coordinates of the supplied territory from the baseMap -
	 * EarthMap. Append the found coordinates to the input text and insert them
	 * in the JTextArea in Choose Map Panel.
	 * 
	 * @param mapInputLineText,
	 *            input text inserted by the user specifying the territory and
	 *            the adjacent countries.
	 * @return String, the complete line text with the coordinates inserted
	 *         after the territory.
	 */
	public String mapEditTextInsertCoordinates(String mapInputLineText) {

		StringBuilder sbInputWithCoordinates = new StringBuilder();
		String territory = mapInputLineText.substring(0, mapInputLineText.indexOf(','));
		String coordinates = fetchCoordinates(territory);

		sbInputWithCoordinates.append(mapInputLineText.substring(0, mapInputLineText.indexOf(','))).append(coordinates)
				.append(mapInputLineText.substring(mapInputLineText.indexOf(',') + 1, mapInputLineText.length()));

		return sbInputWithCoordinates.toString();
	}

	/**
	 * Fetch the coordinates of the territories given to this method. Call the
	 * recursive search function to perform the search.
	 * 
	 * @param territory,
	 *            territory inserted by the user.
	 * @return = String, the coordinates of the territory supplied in string -
	 *         e.g - ,XX,YY, - format.
	 */
	public String fetchCoordinates(String territory) {
		String coordinates = "";
		// initializeMapVariables();
		coordinates = recursiveSearchCoordinates(sbBaseMapString.toString(), territory);
		return coordinates;
	}

	/**
	 * Processes user input from the JTextField. Calls another method to fetch
	 * and insert coordinates for the territory. Appends the input with
	 * coordinates to the JTextArea in Choose Map Panel
	 * 
	 * @param e
	 *            ActionEvent passed for the button click event.
	 */
	private void addButtonPressed(ActionEvent e) {
		try {
			if (validateMapLineInputText(mapEditTextField.getText())) {
				mapEditTextArea.append(mapEditTextInsertCoordinates(mapEditTextField.getText()) + "\n");
				scrollTextAreaPanel.repaint();
				mapEditTextField.setText("");
			} else {
				notePanel.repaint();
			}
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * The base earth map has individual territories specified in multiple
	 * places. At times as countries with coordinates next to it and at times as
	 * an adjacent country to some other country during which it does not have
	 * coordinates next to it. Hence a recursive search is needed to find only
	 * that particular instance of country which has coordinates next to it in
	 * the base map. This function performs that recursive search needed.
	 * 
	 * @param baseMapString,
	 *            this class level variable has the earth map read into it in
	 *            string format.
	 * @param territory,
	 *            this is the territory inserted by the user.
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
	 * @param baseMapString,
	 *            string for the entire base map.
	 * @param territory,
	 *            territory supplied by the user.
	 * @param index,
	 *            index of the first character of the incorrect territory
	 *            instance.
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
	 * @param index,
	 *            index of the correct instance of the territory in the base
	 *            map.
	 * @param baseMapString,
	 *            the base map in string format.
	 * @return coordinates, in the format XX/X,YY/Y.
	 */
	private String fetchCoordinates(int index, String baseMapString) {
		// TODO Auto-generated method stub
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
	 * @param baseMapString,
	 *            base earth map in string format.
	 * @param index,
	 *            index of the first occurrence 'territory' in the base map.
	 * @param territory,
	 *            the territory supplied by the user.
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
	 * @param mapInputLineText,
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
						if (countryNode.getTextContent().toLowerCase()
								.equals((mapInputLineText.split(",")[location]).toLowerCase()))
							isCountryValid = true;
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
						if (continentNode.getTextContent().toLowerCase()
								.equals((mapInputLineText.split(",")[location]).toLowerCase()))
							isContinentValid = true;
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
	 * @param args
	 *            the command line arguments
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
	private javax.swing.JTextArea mapEditTextArea;
	private javax.swing.JTextField mapEditTextField;
	private String fetchedCoordinates;
	private JPanel scrollTextAreaPanel;
	private JPanel notePanel;
	private JLabel noteLabel;
	private StringBuilder sbBaseMapString = new StringBuilder();
	private JFrame generateMapFrame;
	private StringBuilder sbAdjacentCountryInfo = new StringBuilder();

}
