package serverSide;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class serverGUI {

	private JFrame frame;
	private Server server;
	private String port;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private ServerTask serverTask;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverGUI window = new serverGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public serverGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_main = new JPanel();
		panel_main.setBorder(UIManager.getBorder("OptionPane.border"));
		frame.getContentPane().add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new BorderLayout(0, 0));

		JPanel panel_south = new JPanel();
		panel_main.add(panel_south, BorderLayout.SOUTH);
		panel_south.setBorder(new TitledBorder(null, "Controls", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_south.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_4 = new JPanel();
		panel_south.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewButton = new JButton("Accept all torrents");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_4.add(btnNewButton);

		JButton btnAcceptSelectedTorrent = new JButton("Accept selected torrent");
		btnAcceptSelectedTorrent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_4.add(btnAcceptSelectedTorrent);

		JButton btnStartServer = new JButton("Start server");
		btnStartServer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				port = JOptionPane.showInputDialog(null,
						"Enter server port?",
						"",
						JOptionPane.QUESTION_MESSAGE);

				(serverTask = new ServerTask()).execute();
			}
		});
		panel_4.add(btnStartServer);

		JPanel panel_north = new JPanel();
		panel_main.add(panel_north, BorderLayout.NORTH);
		panel_north.setBorder(new TitledBorder(null, "Torrent requests", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 120));
		panel_north.add(scrollPane);

		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 10));
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"magnet:?xt=urn:btih:f127b282300b8379168018fc57e65b57f7066fd6&dn=The.Expendables.2.2012.720p.BluRay.x264-AVSHD+%5BPublicHD%5D+&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80&tr=udp%3A%2F%2Ftracker.istole.it%3A6969&tr=udp%3A%2F%2Ftracker.ccc.de%3A80"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);



		JPanel panel_center = new JPanel();
		panel_main.add(panel_center, BorderLayout.CENTER);
		panel_center.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Torrent info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_center.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("80px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(133dlu;default)"),},
				new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));

		JPanel panel = new JPanel();
		panel_center.add(panel, "1, 1, left, top");

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblName);

		textField = new JTextField();
		panel_center.add(textField, "3, 1");
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_center.add(panel_1, "1, 3, left, top");

		JLabel lblRequestDate = new JLabel("Request date");
		lblRequestDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_1.add(lblRequestDate);

		textField_1 = new JTextField();
		panel_center.add(textField_1, "3, 3");
		textField_1.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_center.add(panel_2, "1, 5, left, top");

		JLabel lblRequestedBy = new JLabel("Requested by");
		lblRequestedBy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_2.add(lblRequestedBy);

		textField_2 = new JTextField();
		panel_center.add(textField_2, "3, 5");
		textField_2.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_center.add(panel_3, "1, 7, left, top");

		JLabel lblAcceptedBy = new JLabel("Accepted by");
		lblAcceptedBy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_3.add(lblAcceptedBy);

		textField_3 = new JTextField();
		panel_center.add(textField_3, "3, 7");
		textField_3.setColumns(10);
	}
	private class ServerTask extends SwingWorker<Void, String> {
		@Override
		protected Void doInBackground() throws IOException {
			String clientHostname;
			String magnetURI;

			ServerSocket servSocket = new ServerSocket(1234);
			while(!isCancelled())
			{
				Socket connectionSocket = servSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				clientHostname = inFromClient.readLine();
				magnetURI = inFromClient.readLine();
				System.out.println(clientHostname);
				System.out.println(magnetURI);
				outToClient.writeBytes("OK");
			}
			return null;
		}
	}
}
