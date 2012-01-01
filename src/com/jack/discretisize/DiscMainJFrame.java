package com.jack.discretisize;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class DiscMainJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable unsortedTable;
	private JTable sortedTable;
	
	static List<Bin> bins;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		Discretization d;
			public void run() {
				try {
					DiscMainJFrame frame = new DiscMainJFrame();
					d = new Discretization(12,3,1,100);
					bins = d.getBins();
					for(Bin bin : bins) {
						System.out.println("**BIN "+bin.getPosition()+" **");
						System.out.println("ONSET :" + bin.onset);
						System.out.println("OFFSET :" + bin.offset);
						int[] smoothByBoundaries = bin.smoothByBoundaries();
						for(int y = 0; y < smoothByBoundaries.length; ++y) {
							System.out.println(smoothByBoundaries[y]);
						}
					}
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DiscMainJFrame() {
		setTitle("Discasdasda");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel unsortedPanel = new JPanel();
		unsortedPanel.setBorder(new TitledBorder(null, "Unsorted", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		unsortedPanel.setBounds(10, 11, 99, 368);
		contentPane.add(unsortedPanel);
		unsortedPanel.setLayout(null);
		
		unsortedTable = new JTable();
		unsortedTable.setBounds(10, 26, 79, 331);
		unsortedTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Unsorted"
			}
		));
		unsortedPanel.add(unsortedTable);
		
		JPanel sortedPanel = new JPanel();
		sortedPanel.setBorder(new TitledBorder(null, "Sorted", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sortedPanel.setBounds(119, 11, 99, 368);
		contentPane.add(sortedPanel);
		sortedPanel.setLayout(null);
		
		sortedTable = new JTable();
		sortedTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sorted"
			}
		));
		sortedTable.setBounds(10, 27, 79, 330);
		sortedPanel.add(sortedTable);
	}
}
