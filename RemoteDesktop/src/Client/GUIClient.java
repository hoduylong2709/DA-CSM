package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GUIClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMyID;
	private JTextField txtMyPassword;
	private JTextField txtFriendId;
	private JTextField txtFriendPassword;
	private ClientConnect cCon;
	ClientControl clientCon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUIClient frame = new GUIClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		Line2D lin = new Line2D.Float(407, 120, 407, 400);
		g2.draw(lin);
	}

	/**
	 * Create the frame.
	 */
	public GUIClient() {
		Color color = Color.lightGray;

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 420);
		contentPane = new JPanel();
		contentPane.setBackground(color);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtMyID = new JTextField();
		txtMyID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMyID.setBounds(160, 150, 196, 46);
		contentPane.add(txtMyID);
		txtMyID.setColumns(10);

		txtMyPassword = new JTextField();
		txtMyPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMyPassword.setBounds(160, 220, 196, 46);
		contentPane.add(txtMyPassword);
		txtMyPassword.setColumns(10);

		txtFriendId = new JTextField();
		txtFriendId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFriendId.setBounds(584, 150, 200, 46);
		contentPane.add(txtFriendId);
		txtFriendId.setColumns(10);

		txtFriendPassword = new JTextField();
		txtFriendPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtFriendPassword.setBounds(584, 220, 200, 46);
		contentPane.add(txtFriendPassword);
		txtFriendPassword.setColumns(10);
		// Connect server
		JButton btnConnect = new JButton("Đăng ký");
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConnect.setBounds(100, 300, 200, 41);
		contentPane.add(btnConnect);

		// Connect Server to control friend computer
		JButton btnFrConnect = new JButton("Kết nối từ xa");
		btnFrConnect.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFrConnect.setBounds(530, 300, 200, 41);
		contentPane.add(btnFrConnect);

		JLabel lbRMD = new JLabel("Remote Desktop");
		lbRMD.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbRMD.setBounds(250, 25, 300, 32);
		contentPane.add(lbRMD);

		JLabel lbChoPhep = new JLabel("Cho phép điều khiển");
		lbChoPhep.setFont(new Font("Tahoma", Font.BOLD, 28));
		lbChoPhep.setBounds(20, 90, 300, 32);
		contentPane.add(lbChoPhep);

		JLabel lbDieuKhien = new JLabel("Điều khiển máy khác");
		lbDieuKhien.setFont(new Font("Tahoma", Font.BOLD, 28));
		lbDieuKhien.setBounds(450, 90, 300, 32);
		contentPane.add(lbDieuKhien);

		JLabel lbMyID = new JLabel("My ID");
		lbMyID.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbMyID.setBounds(12, 150, 78, 46);
		contentPane.add(lbMyID);

		JLabel lbMyPassword = new JLabel("Mật khẩu");
		lbMyPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbMyPassword.setBounds(12, 220, 139, 32);
		contentPane.add(lbMyPassword);

		JLabel lblNewLabel_2 = new JLabel("Friend ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(450, 150, 132, 42);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Mật khẩu");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(450, 220, 142, 46);
		contentPane.add(lblNewLabel_3);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				JOptionPane.showConfirmDialog(null, "xxxx", null, JOptionPane.YES_NO_OPTION);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				int hoi = JOptionPane.showConfirmDialog(null, "Do you want to close this app? ", null,
						JOptionPane.YES_NO_OPTION);
				if (hoi == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		// Add event for button
		// btFrnConnect
		btnFrConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtMyID.getText().trim().equals(txtFriendId.getText().trim())) {
					JOptionPane.showMessageDialog(null, "Don't control myself");
					return;
				}
				if (!txtMyID.getText().trim().equals("") && !txtMyPassword.getText().trim().equals("")
						&& !txtFriendId.getText().trim().equals("") && !txtFriendPassword.getText().trim().equals("")) {
					cCon = new ClientConnect(Integer.parseInt(txtMyID.getText()), txtMyPassword.getText());
					cCon.ConnectServer();
					clientCon = new ClientControl(cCon.GetSocket());
					int x = clientCon.checkPass(Integer.parseInt(txtFriendId.getText()), txtFriendPassword.getText());
					if (x == 1) {
						clientCon.ReceiveScreenAndSendEvent();
						btnFrConnect.setEnabled(false);
						btnConnect.setEnabled(false);
					} else if (x == 2) {
						JOptionPane.showMessageDialog(null, "Incorrect password or id");
					} else if (x == 3) {
						JOptionPane.showMessageDialog(null, "Devide busy" + txtFriendId.getText());
					}
				} else
					JOptionPane.showMessageDialog(null, "Please fullfill a Textbox");
			}
		});

		// btnConnect
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!txtMyID.getText().trim().equals("") && !txtMyPassword.getText().trim().equals("")) {
					btnFrConnect.setEnabled(false);
					btnConnect.setEnabled(false);
					cCon = new ClientConnect(Integer.parseInt(txtMyID.getText()), txtMyPassword.getText());
					cCon.ConnectServer();
					new ClientBeControl(cCon.GetSocket()).SendScreenAndReceiveEvent();
				} else
					JOptionPane.showMessageDialog(null, "Please FullFill a Textbox");
			}
		});
	}
}
