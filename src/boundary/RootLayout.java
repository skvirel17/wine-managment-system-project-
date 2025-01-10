package boundary;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;

public class RootLayout extends JFrame {

	private JPanel contentPane;
	static RootLayout frame;
	private FrmManufacture ordersPanel;

	private JMenu mnHome;
	private JMenu mnManufactures;
	private JMenu mnEmployees;

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
		setBounds(100, 100, 753, 521);

		createMenuBar() ;
		

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


//			mnEmployees = new JMenu("Employees");
//			mnEmployees.addMenuListener(new MenuListener() {
//				public void menuCanceled(MenuEvent e) {
//				}
//				public void menuDeselected(MenuEvent e) {
//				}
//				public void menuSelected(MenuEvent e) {
//					mngEmployees = new FrmEmployees();
//					mngEmployees.setVisible(true);
//					JFrame f1 = (JFrame) SwingUtilities.windowForComponent(menuBar);
//					f1.dispose();
//
//				}
//			});
//			menuBar.add(mnEmployees);
//
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
	 
	 public void updateMenuSelectionOrders() {
		 //mnOrders.setOpaque(true);
		 //mnOrders.setBackground(SystemColor.activeCaption);

	 }

	 public void updateMenuSelectionEmployee() {
		 //mnEmployees.setOpaque(true);
		 //mnEmployees.setBackground(SystemColor.activeCaption);
	 }

}
