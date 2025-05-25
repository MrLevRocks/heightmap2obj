package heightmap;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

public class HeightMapModelMakerGUI implements ActionListener {

	private JFrame frmHeightMapModelMaker;
	private JPanel contentPane;
	private JTextField inputFilePathField;
	private JTextField heightMaxField;
	private JTextField mapSizeField;
	private JTextField heightMinField;
	private JTextField outputFilePathField;
	
	private JFileChooser fc = new JFileChooser();
	
	private JButton inputButton;
	private JButton outputButton;
	private JButton makeModelButton;
	private JLabel statusLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HeightMapModelMakerGUI window = new HeightMapModelMakerGUI();
					window.frmHeightMapModelMaker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HeightMapModelMakerGUI() {
		initialize();
	}
	
	private void initialize() {
		frmHeightMapModelMaker = new JFrame();
		frmHeightMapModelMaker.setTitle("Height Map to OBJ Maker by MrLevRocks");
		try {
			frmHeightMapModelMaker.setIconImage(ImageIO.read(HeightMapModelMakerGUI.class.getClassLoader().getResource("icon.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		frmHeightMapModelMaker.setBounds(100, 100, 450, 300);
		frmHeightMapModelMaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		contentPane.setLayout(gridBagLayout);

		frmHeightMapModelMaker.setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel heightLabel = new JLabel("Height minimum");
		GridBagConstraints gbc_heightLabel = new GridBagConstraints();
		gbc_heightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_heightLabel.gridx = 0;
		gbc_heightLabel.gridy = 0;
		panel.add(heightLabel, gbc_heightLabel);
		
		JLabel heightLabel2 = new JLabel("Height maximum");
		GridBagConstraints gbc_heightLabel2 = new GridBagConstraints();
		gbc_heightLabel2.insets = new Insets(0, 0, 5, 5);
		gbc_heightLabel2.gridx = 1;
		gbc_heightLabel2.gridy = 0;
		panel.add(heightLabel2, gbc_heightLabel2);
		
		JLabel sizeLabel = new JLabel("Size of Map");
		GridBagConstraints gbc_sizeLabel = new GridBagConstraints();
		gbc_sizeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_sizeLabel.gridx = 2;
		gbc_sizeLabel.gridy = 0;
		panel.add(sizeLabel, gbc_sizeLabel);
		
		heightMinField = new JTextField();
		GridBagConstraints gbc_heightMinField = new GridBagConstraints();
		gbc_heightMinField.insets = new Insets(0, 0, 5, 5);
		gbc_heightMinField.gridx = 0;
		gbc_heightMinField.gridy = 1;
		panel.add(heightMinField, gbc_heightMinField);
		heightMinField.setColumns(10);
		
		heightMaxField = new JTextField();
		GridBagConstraints gbc_heightMaxField = new GridBagConstraints();
		gbc_heightMaxField.insets = new Insets(0, 0, 5, 5);
		gbc_heightMaxField.gridx = 1;
		gbc_heightMaxField.gridy = 1;
		panel.add(heightMaxField, gbc_heightMaxField);
		heightMaxField.setColumns(10);
		
		mapSizeField = new JTextField();
		GridBagConstraints gbc_mapSizeField = new GridBagConstraints();
		gbc_mapSizeField.insets = new Insets(0, 0, 5, 0);
		gbc_mapSizeField.gridx = 2;
		gbc_mapSizeField.gridy = 1;
		panel.add(mapSizeField, gbc_mapSizeField);
		mapSizeField.setColumns(10);
		
		JLabel inputLabel = new JLabel("Input Image");
		GridBagConstraints gbc_inputLabel = new GridBagConstraints();
		gbc_inputLabel.gridwidth = 3;
		gbc_inputLabel.insets = new Insets(0, 0, 5, 0);
		gbc_inputLabel.gridx = 0;
		gbc_inputLabel.gridy = 2;
		panel.add(inputLabel, gbc_inputLabel);
		
		inputFilePathField = new JTextField();
		inputFilePathField.setEnabled(false);
		inputFilePathField.setEditable(false);
		GridBagConstraints gbc_inputFilePathField = new GridBagConstraints();
		gbc_inputFilePathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputFilePathField.gridwidth = 2;
		gbc_inputFilePathField.insets = new Insets(0, 0, 5, 5);
		gbc_inputFilePathField.gridx = 0;
		gbc_inputFilePathField.gridy = 3;
		panel.add(inputFilePathField, gbc_inputFilePathField);
		inputFilePathField.setColumns(10);
		
		inputButton = new JButton("Load Image");
		GridBagConstraints gbc_inputButton = new GridBagConstraints();
		gbc_inputButton.insets = new Insets(0, 0, 5, 0);
		gbc_inputButton.anchor = GridBagConstraints.NORTH;
		gbc_inputButton.gridx = 2;
		gbc_inputButton.gridy = 3;
		panel.add(inputButton, gbc_inputButton);
		
		JLabel outputLabel = new JLabel("Output File");
		GridBagConstraints gbc_outputLabel = new GridBagConstraints();
		gbc_outputLabel.gridwidth = 3;
		gbc_outputLabel.insets = new Insets(0, 0, 5, 0);
		gbc_outputLabel.gridx = 0;
		gbc_outputLabel.gridy = 4;
		panel.add(outputLabel, gbc_outputLabel);
		
		outputFilePathField = new JTextField();
		outputFilePathField.setEditable(false);
		outputFilePathField.setEnabled(false);
		GridBagConstraints gbc_outputFilePathField = new GridBagConstraints();
		gbc_outputFilePathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_outputFilePathField.gridwidth = 2;
		gbc_outputFilePathField.insets = new Insets(0, 0, 5, 5);
		gbc_outputFilePathField.gridx = 0;
		gbc_outputFilePathField.gridy = 5;
		panel.add(outputFilePathField, gbc_outputFilePathField);
		outputFilePathField.setColumns(10);
		
		outputButton = new JButton("Set Output File");
		GridBagConstraints gbc_outputButton = new GridBagConstraints();
		gbc_outputButton.insets = new Insets(0, 0, 5, 0);
		gbc_outputButton.gridx = 2;
		gbc_outputButton.gridy = 5;
		panel.add(outputButton, gbc_outputButton);
		
		makeModelButton = new JButton("Make OBJ");
		GridBagConstraints gbc_makeModelButton = new GridBagConstraints();
		gbc_makeModelButton.gridwidth = 4;
		gbc_makeModelButton.insets = new Insets(0, 0, 5, 0);
		gbc_makeModelButton.gridx = 0;
		gbc_makeModelButton.gridy = 6;
		panel.add(makeModelButton, gbc_makeModelButton);
		makeModelButton.addActionListener(this);
		
		statusLabel = new JLabel("Ready...");
		GridBagConstraints gbc_statusLabel = new GridBagConstraints();
		gbc_statusLabel.gridwidth = 3;
		gbc_statusLabel.gridx = 0;
		gbc_statusLabel.gridy = 7;
		panel.add(statusLabel, gbc_statusLabel);
		
		inputButton.addActionListener(this);
		outputButton.addActionListener(this);
		
		fc.setAcceptAllFileFilterUsed(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == inputButton) {
			fc.setSelectedFile(null);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "bmp", "jpg", "jpeg", "gif");
			fc.setFileFilter(filter);
			int returnVal = fc.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				inputFilePathField.setText(fc.getSelectedFile().getPath());
			} else if(returnVal == JFileChooser.ERROR_OPTION) {
				statusLabel.setText("An error loading the input has occurred!");
			}
		}
		
		if(e.getSource() == outputButton) {
			fc.setSelectedFile(null);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("OBJ Files", "obj");
			fc.setFileFilter(filter);
			int returnVal = fc.showSaveDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				outputFilePathField.setText(fc.getSelectedFile().getPath());
			} else if(returnVal == JFileChooser.ERROR_OPTION) {
				statusLabel.setText("An error selecting the output has occurred!");
			}
		}

		if(e.getSource() == makeModelButton) {
			try {
				Double.parseDouble(heightMinField.getText());
			} catch (NumberFormatException e1) {
				statusLabel.setText("Height minimum is not a number input!");
				return;
			}
			try {
				Double.parseDouble(heightMaxField.getText());
			} catch (NumberFormatException e1) {
				statusLabel.setText("Height maximum is not a number input!");
				return;
			}
			try {
				Double.parseDouble(mapSizeField.getText());
			} catch (NumberFormatException e1) {
				statusLabel.setText("Map size is not a number input!");
				return;
			}
			if(inputFilePathField.getText().equals("")) {
				statusLabel.setText("No input file specified.");
				return;
			}
			if(outputFilePathField.getText().equals("")) {
				statusLabel.setText("No output file specified.");
				return;
			}
			String[] inputs = {inputFilePathField.getText(), heightMinField.getText(), heightMaxField.getText(), mapSizeField.getText(), outputFilePathField.getText()};
			try {
				statusLabel.setText("Starting model making, please wait...");
				HeightMapModelMaker.main(inputs);
			} catch (Exception e1) {
				statusLabel.setText(e1.toString());
			}
			statusLabel.setText("Model made!");
		}
	}

}
