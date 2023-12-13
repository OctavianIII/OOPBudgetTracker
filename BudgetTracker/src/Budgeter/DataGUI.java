package Budgeter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class DataGUI extends JFrame{
	
	private JPanel content;
	private JScrollPane  list;
	private JScrollPane parentList;
	private JButton backBtn;
	private JButton deleteBtn;
	private JButton addBtn;
	private JTextField f1;
	private JTextField f2;
	private JTextField f3;
	private JTextField f4;
	private JLabel heading;
	private JLabel heading1;
	private JLabel heading2;
	private DataNode data;
	private DataNode r;
	private DataNode c;
	
	public DataGUI() {
		data = Main.d.getHead();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1280,800);
		content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		setContentPane(content);
		this.generate();
	}
	
	//generates JFrame content
	private void generate() {
		
		//PARENT EDITING PANEL, INCLUDES DATA TABLE, HEADER, AND BACK/DELETE BUTTONS
		EmptyBorder elementPadding = new EmptyBorder(40, 0, 40, 0);
		int showParent = 0;
		String tmp[] = data.toString().split(",");
		Object[][] parent = new Object[1][tmp.length+1];
		System.arraycopy(tmp, 0, parent[0], 0, tmp.length);
		parent[0][tmp.length] = Double.toString(DataParser.getSum(data));
		Object[] parentCols = {};
		if(data instanceof Range) {
			heading1 = new JLabel("Categories");
			parentCols = (new DataNode()).header();
			showParent = 1;
			heading = new JLabel("Edit Date Range");
		} else if(data instanceof Category) {
			parentCols = (new Range("", 0,"","")).header();
			showParent = 1;
			heading1 = new JLabel("Transactions");
			heading = new JLabel("Edit Category");
		} else if(data instanceof Transaction) {
			parentCols = (new Category(0,"", "")).header();
			showParent = 1;
			heading = new JLabel("Edit Transaction");
		} else {
			heading = new JLabel("View Date Ranges");
		}
		heading.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		heading.setAlignmentX(Component.CENTER_ALIGNMENT);
		heading.setBorder(elementPadding);
		content.add(heading);
		if(showParent==1) {
			
	        DefaultTableModel model = new DefaultTableModel(parent, parentCols);
			JTable parentTable = new JTable(model);
			model.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.UPDATE) {
                        if(data instanceof Range) {
                        	((Range) data).update((String)model.getValueAt(0, 2), Double.parseDouble((String)model.getValueAt(0, 3)), (String)model.getValueAt(0, 4), (String)model.getValueAt(0, 5));
                        } else if(data instanceof Category) {
                        	((Category) data).update((String)model.getValueAt(0, 2), Double.parseDouble((String)model.getValueAt(0, 3)), (String)model.getValueAt(0, 4));
                		} else if(data instanceof Transaction) {
                			((Transaction) data).update((String)model.getValueAt(0, 2), Double.parseDouble((String)model.getValueAt(0, 4)), (String)model.getValueAt(0, 3));
                		}
                    }
                }
            });
						
			TableColumnModel columnModel = parentTable.getColumnModel();
		    columnModel.removeColumn(columnModel.getColumn(0));
		    columnModel.removeColumn(columnModel.getColumn(0));
		    parentList = new JScrollPane(parentTable);
		    parentList.setPreferredSize(new Dimension(500, 50));
		  	content.add(parentList);
		  	heading1.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
			heading1.setAlignmentX(Component.CENTER_ALIGNMENT);
			heading1.setBorder(elementPadding);
			backBtn = new JButton("Back");
			backBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnBack();
				}
			});
			backBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			content.add(backBtn);
			deleteBtn = new JButton("Delete");
			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnDelete();
				}
			});
			deleteBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			deleteBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			content.add(deleteBtn);
			content.add(heading1);
		} else {
			backBtn = new JButton("Back");
			backBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnBack();
				}
			});
			backBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
			backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
			content.add(backBtn);
		}
		
		
		//CONTENT DATA PANEL
		if(!(data instanceof Transaction)) {
			Object[][] d = DataParser.parse(data);
			Object[] colNames = data.header();
	        DefaultTableModel model = new DefaultTableModel(d, colNames) {
	            private static final long serialVersionUID = 1L;
				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
			JTable dataList = new JTable(model);
			dataList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dataList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent e) {
	                if (!e.getValueIsAdjusting()) {
	                    int selectedRow = dataList.getSelectedRow();
	                    btnEdit(Integer.parseInt((String)d[selectedRow][1]));
	                }
	            }
	        });
			
			TableColumnModel columnModel = dataList.getColumnModel();
		    columnModel.removeColumn(columnModel.getColumn(0));
		    columnModel.removeColumn(columnModel.getColumn(0));
		    
			list = new JScrollPane(dataList);
			content.add(list);
		}
		
		
		//USER INPUT FOR ADDING ELEMENTS
		f1= new JTextField();
		f1.setHorizontalAlignment(SwingConstants.LEFT);
		f1.setForeground(new Color(94, 94, 94));
		f1.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		f1.setColumns(20);
		f1.setAlignmentX(Component.CENTER_ALIGNMENT);
		f2= new JTextField();
		f2.setHorizontalAlignment(SwingConstants.LEFT);
		f2.setForeground(new Color(94, 94, 94));
		f2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		f2.setColumns(20);
		f2.setAlignmentX(Component.CENTER_ALIGNMENT);
		f3= new JTextField();
		f3.setHorizontalAlignment(SwingConstants.LEFT);
		f3.setForeground(new Color(94, 94, 94));
		f3.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		f3.setColumns(20);
		f3.setAlignmentX(Component.CENTER_ALIGNMENT);
		f4= new JTextField();
		f4.setHorizontalAlignment(SwingConstants.LEFT);
		f4.setForeground(new Color(94, 94, 94));
		f4.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		f4.setColumns(20);
		f4.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		heading2 = new JLabel("Add New Content");
	  	heading2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		heading2.setAlignmentX(Component.CENTER_ALIGNMENT);
		heading2.setBorder(elementPadding);
		content.add(heading2);
		
		if(data instanceof Range) {
			f1.setText("Enter title");
			content.add(f1);
			f2.setText("Enter budget");
			content.add(f2);
			f3.setText("Enter description");
			content.add(f3);
		} else if(data instanceof Category) {
			f1.setText("Enter title");
			content.add(f1);
			f2.setText("Enter date");
			content.add(f2);
			f3.setText("Enter amount");
			content.add(f3);
		} else {
			f1.setText("Enter title");
			content.add(f1);
			f2.setText("Enter budget");
			content.add(f2);
			f3.setText("Enter start date");
			content.add(f3);
			f4.setText("Enter end date");
			content.add(f4);
		}
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd();
			}
		});
		addBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		content.add(addBtn);
		EmptyBorder emptyBorder = new EmptyBorder(0, 80, 40, 80);
		content.setBorder(emptyBorder);
	}
	
	//preview page btn
	@SuppressWarnings("deprecation")
	private void btnBack() {
		if(data instanceof Range) {
			data = Main.d.getHead();
		} else if(data instanceof Category) {
			data = r;
		} else if(data instanceof Transaction) {
			data = c;
		} else {
			this.hide();
			Main.mGUI.show();
			return;
		}
		content.removeAll();
		content.revalidate();
		content.repaint();
		this.generate();
	}
	
	//edit selected item
	private void btnEdit(int id) {
		DataNode n = data.get(id);
		data = n;
		content.removeAll();
		content.revalidate();
		content.repaint();
		this.generate();
		if(data instanceof Range) {
			r=data;
		} else if(data instanceof Category) {
			c=data;
		}
	}
	
	//add a new item with the input text fields
	private void btnAdd() {
		DataNode n;
		if(data instanceof Range) {
			n = new Category(Double.parseDouble(f2.getText()), f1.getText(), f3.getText());
		} else if(data instanceof Category) {
			n = new Transaction(f1.getText(), f2.getText(), Double.parseDouble(f3.getText()));
		} else {
			n = new Range(f1.getText(), Double.parseDouble(f2.getText()), f3.getText(), f4.getText());
		}
		data.add(n);
		content.removeAll();
		content.revalidate();
		content.repaint();
		this.generate();
	}
	
	//delete the current item and escape to its parent
	private void btnDelete() {
		DataNode tmp = data;
		if(data instanceof Range) {
			data = Main.d.getHead();
		} else if(data instanceof Category) {
			data = r;
		} else if(data instanceof Transaction) {
			data = c;
		}
		data.remove(tmp);
		content.removeAll();
		content.revalidate();
		content.repaint();
		this.generate();
	}
}