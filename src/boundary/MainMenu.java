package boundary;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.SwingUtilities;
import control.auth.User;
import control.auth.Role;

public class MainMenu extends RootLayout {

	private JPanel contentPane;
	static User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new LoginFrame().setVisible(true); // запускаем окно логина
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu(User user) {
		this.currentUser = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		updateMenuSelectionHome();

		JLabel lblNewLabel = new JLabel();
		ImageIcon icon = new ImageIcon(MainMenu.class.getResource("/boundary/images/logo_.png"));

		// Получаем изображение и изменяем размер
		Image img = icon.getImage();
		Image scaledImg = img.getScaledInstance(200, 300, Image.SCALE_SMOOTH); // 200x100 - изменить при необходимости

		lblNewLabel.setIcon(new ImageIcon(scaledImg));
		lblNewLabel.setPreferredSize(new Dimension(200, 100));

		JButton btnNewButton_1 = new JButton("Print sales Report");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						//.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, 200, 200, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, 200, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							//.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						).addComponent(lblNewLabel))
					.addContainerGap(58, Short.MAX_VALUE))
		);

		JButton btnNewButton = new JButton("Categories");
		btnNewButton.setEnabled(false);
		btnNewButton.setPreferredSize(new Dimension(170, 25));





		JButton btnSuppliers = new JButton("Suppliers");
		btnSuppliers.setEnabled(false);
		btnSuppliers.setPreferredSize(new Dimension(170, 25));

		JButton btnOrders = new JButton("Manufacture");
		btnOrders.setPreferredSize(new Dimension(170, 25));
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmManufacture().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(panel);
				f1.dispose();
			}
		});


		JButton btnProducts = new JButton("Products");
		btnProducts.setEnabled(false);
		btnProducts.setPreferredSize(new Dimension(170, 25));


		JButton btnUnproductive = new JButton("Unproductive employees");
		btnUnproductive.setPreferredSize(new Dimension(170, 25));
		btnUnproductive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmUnproductiveEmployees().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(panel);
				f1.dispose();
			}
		});


		JButton btnChoose = new JButton("Choose wine type");
		btnChoose.setPreferredSize(new Dimension(170, 25));
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmChooseWine().setVisible(true);
				JFrame f1 = (JFrame) SwingUtilities.windowForComponent(panel);
				f1.dispose();
			}
		});


		JButton btnImport = new JButton("Import weekly report");
		btnImport.setPreferredSize(new Dimension(170, 25));


		panel.add(btnSuppliers);
		panel.add(btnOrders);
		panel.add(btnProducts);
		panel.add(btnUnproductive);
		panel.add(btnImport);
		panel.add(btnChoose);
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(gl_contentPane);

		if (currentUser.getRole() == Role.ADMIN) {
			btnSuppliers.setEnabled(true);
			btnProducts.setEnabled(true);
			btnUnproductive.setEnabled(true);
			btnImport.setEnabled(true);
		} else {
			btnSuppliers.setEnabled(false);
			btnProducts.setEnabled(false);
			btnUnproductive.setEnabled(false);
			btnImport.setEnabled(false);
		}

// Кнопки, доступные всем
		btnOrders.setEnabled(true);
		btnChoose.setEnabled(true);
	}


}
