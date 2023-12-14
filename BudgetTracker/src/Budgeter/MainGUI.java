package Budgeter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainGUI extends JFrame{
	
	private JTextField filepath;
	private JPanel content;
	private JButton loadBtn;
	private JButton saveBtn;
	private JButton viewBtn;
	private DataGUI dGUI;
	
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1280,800);
		content = new JPanel();
		setContentPane(content);
		content.setLayout(null);
		filepath = new JTextField();
		filepath.setHorizontalAlignment(SwingConstants.LEFT);
		filepath.setBounds(215, 460, 850, 37);
		filepath.setForeground(new Color(94, 94, 94));
		filepath.setText("Enter a filepath here to save or load");
		filepath.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		filepath.setColumns(40);
		content.add(filepath);
		
		saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave(filepath.getText());
			}
		});
		saveBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		saveBtn.setBounds(300, 333, 280, 79);
		content.add(saveBtn);
		
		loadBtn = new JButton("Load");
		loadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoad(filepath.getText());
			}
		});
		loadBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		loadBtn.setBounds(700, 333, 280, 79);
		content.add(loadBtn);
		
		viewBtn = new JButton("View Data");
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnView();
			}
		});
		viewBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		viewBtn.setBounds(490, 126, 300, 100);
		content.add(viewBtn);
	}
	
	//saves the current structure to a file in csv format
	private void btnSave(String filepath) {
		String csv = DataParser.parse(Main.d);
		FileManager file = new FileManager(filepath);
		if(file.save(csv)) {
			this.filepath.setText("File Saved!");
		} else {
			this.filepath.setText("ERROR: please enter a valid path");
		}
	}
	
	//loads a file to the data structure
	private void btnLoad(String filepath) {
		FileManager file = new FileManager(filepath);
		String s = file.load();
		DataParser.parse(s);
	}
	
	//views the data stored in the data structure.
	@SuppressWarnings("deprecation")
	private void btnView() {
		dGUI = new DataGUI();
		dGUI.show();
		this.hide();
	}
}