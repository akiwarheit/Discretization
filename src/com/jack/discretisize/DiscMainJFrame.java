package com.jack.discretisize;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;


public class DiscMainJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private Discretization dis;
	private List<Bin> bins;
	private JTextPane equalWidthTextPane;
	private JTextPane equalDepthTextPane;
	private JTextPane boundariesTextPane;
	private JTextPane meansTextPane;
	private JLabel unsortedArray;
	private JLabel sortedArray;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {

		DiscMainJFrame frame;
    
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
					frame = new DiscMainJFrame();
					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
			    ConfigurationManager cm;

			    if (args.length > 0) {
			        cm = new ConfigurationManager(args[0]);
			    } else {
			        cm = new ConfigurationManager("src/discretization.config.xml");
			    }
			    
			    Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
			    recognizer.allocate();  
			    
			    Microphone microphone = (Microphone) cm.lookup("microphone");
			    if (!microphone.startRecording()) {
			        System.out.println("Cannot start microphone.");
			        recognizer.deallocate();
			        System.exit(1);
			    }
	        while (true) {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");

            Result result = recognizer.recognize();

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                if(resultText.contains("generate")) {
                	frame.updateUI();
                }
                if(resultText.contains("clear")) {
                	frame.clearUI();
                }
            } else {
                System.out.println("I can't hear what you said.\n");
            }
	        }
	}

	/**
	 * Create the frame.
	 */
	public DiscMainJFrame() {
		setTitle("Discasdasda");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel unsortedPanel = new JPanel();
		unsortedPanel.setBorder(new TitledBorder(null, "Unsorted", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		unsortedPanel.setBounds(10, 11, 506, 50);
		contentPane.add(unsortedPanel);
		unsortedPanel.setLayout(null);
		
		unsortedArray = new JLabel("");
		unsortedArray.setBounds(10, 11, 486, 28);
		unsortedPanel.add(unsortedArray);
		
		JPanel sortedPanel = new JPanel();
		sortedPanel.setBorder(new TitledBorder(null, "Sorted", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sortedPanel.setBounds(10, 72, 506, 50);
		contentPane.add(sortedPanel);
		sortedPanel.setLayout(null);
		
		sortedArray = new JLabel("");
		sortedArray.setBounds(10, 11, 486, 28);
		sortedPanel.add(sortedArray);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Equal Width", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 133, 248, 246);
		contentPane.add(panel);
		panel.setLayout(null);
		
		equalWidthTextPane = new JTextPane();
		equalWidthTextPane.setEditable(false);
		equalWidthTextPane.setBounds(10, 104, 228, 131);
		panel.add(equalWidthTextPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Equal Depth", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(268, 133, 248, 246);
		contentPane.add(panel_1);
		
		equalDepthTextPane = new JTextPane();
		equalDepthTextPane.setEditable(false);
		equalDepthTextPane.setBounds(10, 104, 228, 131);
		panel_1.add(equalDepthTextPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Smooth by Means", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setLayout(null);
		panel_2.setBounds(10, 390, 248, 246);
		contentPane.add(panel_2);
		
		meansTextPane = new JTextPane();
		meansTextPane.setEditable(false);
		meansTextPane.setBounds(10, 104, 228, 131);
		panel_2.add(meansTextPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Smooth by Boundaries", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setLayout(null);
		panel_3.setBounds(268, 390, 248, 246);
		contentPane.add(panel_3);
		
		boundariesTextPane = new JTextPane();
		boundariesTextPane.setEditable(false);
		boundariesTextPane.setBounds(10, 104, 228, 131);
		panel_3.add(boundariesTextPane);
		dis = new Discretization(12, 3, 20, 50);
		bins = dis.getBins();
		updateUI();
	}
	
	public void updateUI() {
		dis = new Discretization(12, 3, 20, 50);
		bins = dis.getBins();
//		private JTextPane equalWidthTextPane;
//		private JTextPane equalDepthTextPane;
//		private JTextPane boundariesTextPane;
//		private JTextPane meansTextPane;	
		String widthOutput = "", depthOutput = "", boundariesOutput = "", meansOutput = "", matrixOutput = "", sortedMatrixOutput = "";
		int[] matrix = dis.getMatrix();
		int[] sortedMatrix = dis.getSortedMatrix();
		
		for(int x = 0; x < dis.getSize(); ++x) {
			matrixOutput = matrixOutput + " " + matrix[x];
			sortedMatrixOutput = sortedMatrixOutput + " " + sortedMatrix[x];
		}
		
		unsortedArray.setText(matrixOutput);
		sortedArray.setText(sortedMatrixOutput);	
		
		equalWidthTextPane.setText("");
		equalDepthTextPane.setText("");
		boundariesTextPane.setText("");
		meansTextPane.setText("");
		for(Bin bin : bins) {
			widthOutput = widthOutput + "Bin " + bin.getPosition() + " : ";
			depthOutput = depthOutput + "Bin " + bin.getPosition() + " : ";		
			boundariesOutput = boundariesOutput + "Bin " + bin.getPosition() + " : ";
			meansOutput = meansOutput + "Bin " + bin.getPosition() + " : ";
			
			int[] equalWidth = bin.determineEqualWidth();
			int[] equalDepth = bin.determineEqualDepth();
			double[] means = bin.smoothByMean();
			int[] boundaries = bin.smoothByBoundaries();
			
			for(int x = 0; x < equalWidth.length; ++x) {
				widthOutput = widthOutput + " " + equalWidth[x];
			}
			
			for(int x = 0; x < (int)dis.getDepth(); ++x) {
				depthOutput = depthOutput + " " + equalDepth[x];
				boundariesOutput = boundariesOutput + " " + boundaries[x];
				meansOutput = meansOutput + " " + means[x];
			}
			
			widthOutput = widthOutput + "\n";
			depthOutput = depthOutput + "\n"; 
			boundariesOutput = boundariesOutput + "\n"; 
			meansOutput = meansOutput + "\n"; 
			equalWidthTextPane.setText(widthOutput);
			equalDepthTextPane.setText(depthOutput);
			boundariesTextPane.setText(boundariesOutput);
			meansTextPane.setText(meansOutput);
		}
	}
	
	public void clearUI() {
		unsortedArray.setText("");
		sortedArray.setText("");	
		equalWidthTextPane.setText("");
		equalDepthTextPane.setText("");
		boundariesTextPane.setText("");
		meansTextPane.setText("");	
	}
}
