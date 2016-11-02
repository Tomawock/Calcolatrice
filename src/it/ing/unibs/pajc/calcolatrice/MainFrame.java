package it.ing.unibs.pajc.calcolatrice;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;

import it.ing.unibs.pajc.calcolatrice.model.Calcolatrice;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

public class MainFrame {

	private JFrame frame;
	private Calcolatrice brain;
	private PanOperators panOperators;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		brain=new Calcolatrice();
		initialize();
		brain.addKnownOperator("^",(a)->a*a);
		refreshOperators(null);
	}
	void refreshOperators(ChangeEvent e)
	{
		/*panOperators.removeAllButtons();
		for(String s:brain.getKnow)
		*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblOut = new JLabel("");
		frame.getContentPane().add(lblOut, BorderLayout.NORTH);
		lblOut.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		PanKeyPad panKeyPad = new PanKeyPad();
		panKeyPad.setBounds(45, 23, 161, 201);
		panel.add(panKeyPad);
		
		panOperators = new PanOperators();
		panOperators.setBounds(229, 23, 54, 201);
		panel.add(panOperators);
		
		panKeyPad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOut.setText(lblOut.getText()+e.getActionCommand());
			}
		});
		
		//evento che permette ai bottoni di scrivere sull lable
		ActionListener aclButton=new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Object source =e.getSource();
				if(source instanceof JButton)
					lblOut.setText(lblOut.getText().concat(((JButton) source).getText()));				
			}
		};	
	}
}
