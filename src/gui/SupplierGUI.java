package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.SupplierDAO;
import model.Supplier;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupplierGUI {

	private JFrame mainFrame;
	private JTextField supplierChoiceIpt;
	private JLabel supplierNameLbl;
	private JLabel supplierAddressLbl;
	private JLabel supplierMailLbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierGUI window = new SupplierGUI();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SupplierGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Affichage des fournisseurs");
		mainFrame.setBounds(100, 100, 800, 150);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel choicePnl = new JPanel();
		mainFrame.getContentPane().add(choicePnl);
		
		JLabel supplierchoiceLbl = new JLabel("Référence du fournisseur à afficher : ");
		choicePnl.add(supplierchoiceLbl);
		
		supplierChoiceIpt = new JTextField();
		choicePnl.add(supplierChoiceIpt);
		supplierChoiceIpt.setColumns(10);
		
		JButton supplierChoiceSearchBtn = new JButton("Rechercher");
		supplierChoiceSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Attention, il faudra faire davantage de vérifications sur la valeur entrée par l'utilisateur
				if (supplierChoiceIpt.getText().length() > 0) {
					int id = Integer.parseInt(supplierChoiceIpt.getText());
					displaySupplier(id);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "Vous devez entrer une référence de fournisseur", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		choicePnl.add(supplierChoiceSearchBtn);
		
		JPanel supplierDisplayPnl = new JPanel();
		mainFrame.getContentPane().add(supplierDisplayPnl);
		
		JLabel supplierSelectedLbl = new JLabel("Fournisseur sélectionné : ");
		supplierDisplayPnl.add(supplierSelectedLbl);
		
		supplierNameLbl = new JLabel("");
		supplierDisplayPnl.add(supplierNameLbl);
		
		supplierAddressLbl = new JLabel("");
		supplierDisplayPnl.add(supplierAddressLbl);
		
		supplierMailLbl = new JLabel("");
		supplierDisplayPnl.add(supplierMailLbl);
	}
	
	public void displaySupplier(int id) {
		// On récupère le fournisseur en BDD
		SupplierDAO suppDao = new SupplierDAO();
		Supplier supp = suppDao.get(id);
		
		if (supp != null) {
			// On met à jour les libellés représentant le fournisseur
			supplierNameLbl.setText(supp.getName() + " -");
			supplierAddressLbl.setText(supp.getAddress() + " -");
			supplierMailLbl.setText(supp.getEmail());
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Erreur lors de la récupération du fournisseur", "Dialog",
					JOptionPane.ERROR_MESSAGE);
			supplierNameLbl.setText("");
			supplierAddressLbl.setText("");
			supplierMailLbl.setText("");
		}
	}

}
