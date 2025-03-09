package boundary;

import control.FileImporter;
import control.WineLogic;
import entity.Wine;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.swing.filechooser.FileFilter;

public class RootLayout extends JFrame {
	private JPanel contentPane;
	static RootLayout frame;
	private FrmManufacture ordersPanel;

	private JMenu mnHome;
	private JMenu mnManufactures;
	private JMenu mnEmployees;
	private JMenu mnExport;
	private JMenu mnImportWeekly;
	private JMenu mnChooseWineType;
	private JMenu mnWines;
	private JMenu mnOrders;
	private JMenu mnWineLocation;
	private JMenu mnUnproductiveEmployees;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RootLayout();
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
	public RootLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 521);

		createMenuBar();

	}

	public void createMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		ImageIcon icon = new ImageIcon("exit.png");

		JMenu file = new JMenu("File");

		file.setMnemonic(KeyEvent.VK_F);

		JMenuItem eMenuItem = new JMenuItem("Exit", icon);
		eMenuItem.setMnemonic(KeyEvent.VK_E);
		eMenuItem.setToolTipText("Exit application");
		eMenuItem.addActionListener((ActionEvent event) -> {
			System.exit(0);
			//System.out.println("in");
		});

		file.add(eMenuItem);

		menuBar.add(file);


		mnHome = new JMenu("Home");
		menuBar.add(mnHome);
		mnHome.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuSelected(MenuEvent e) {
				new MainMenu().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});


		mnManufactures = new JMenu("Manufactures");
		mnManufactures.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuSelected(MenuEvent e) {
				new FrmManufacture().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});
		mnManufactures.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnManufactures);

		mnWines = new JMenu("Wines");
		mnWines.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuSelected(MenuEvent e) {
				new FrmWine().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});
		mnWines.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnWines);

		mnOrders = new JMenu("Orders");
		mnOrders.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuSelected(MenuEvent e) {
				new FrmOrder().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});
		mnOrders.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnOrders);

		mnWineLocation = new JMenu("Wine Location");
		mnWineLocation.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuSelected(MenuEvent e) {
				new FrmWineLocation().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});
		mnWineLocation.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnWineLocation);


		mnUnproductiveEmployees = new JMenu("Unproductive Employees");
		mnUnproductiveEmployees.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}

			public void menuDeselected(MenuEvent e) {
			}

			public void menuSelected(MenuEvent e) {
				new FrmUnproductiveEmployees().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});
		mnUnproductiveEmployees.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnUnproductiveEmployees);


		mnExport = new JMenu("Import data");
		mnExport.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				fileChooser.setFileFilter(new FileFilter() {
					@Override
					public boolean accept(File file) {

						return file.isDirectory() || file.getName().toLowerCase().endsWith(".xml");
					}

					@Override
					public String getDescription() {
						return "XML Files (*.xml)";
					}
				});

				int result = fileChooser.showOpenDialog(frame);


				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					FileImporter fileImporter = new FileImporter();
					List<Wine> wines = fileImporter.importXML(selectedFile);

					//WineLogic.getInstance().addNewData(wines);
					JOptionPane.showMessageDialog(frame, "File chosen: " + selectedFile.getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(frame, "File canceled.");
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
		mnExport.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnExport);

		mnChooseWineType = new JMenu("ChooseWineType");
		mnChooseWineType.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				new FrmChooseWine().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
				f1.dispose();
			}
		});
		mnChooseWineType.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		menuBar.add(mnChooseWineType);


		mnImportWeekly = new JMenu("Import weekly report");
		mnImportWeekly.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});
		mnImportWeekly.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		menuBar.add(mnImportWeekly);

		setJMenuBar(menuBar);
	}


	 
	 private void changePanel(JPanel panel) {
		    getContentPane().removeAll();
			System.out.println("in");
		    getContentPane().add(panel);
			System.out.println("in");
		    getContentPane().doLayout();
		    update(getGraphics());
		}
	 
	 public void updateMenuSelectionHome() {
		 mnHome.setOpaque(true);
		 mnHome.setBackground(SystemColor.activeCaption);
	 }
	 
	 public void updateMenuSelectionManufactures() {
		 mnManufactures.setOpaque(true);
		 mnManufactures.setBackground(SystemColor.activeCaption);
	 }

	 public void updateMenuSelectionEmployee() {
		 //mnEmployees.setOpaque(true);
		 //mnEmployees.setBackground(SystemColor.activeCaption);
	 }

}
