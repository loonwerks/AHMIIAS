package com.soartech.simple;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jsoar.kernel.SoarException;

public class SimpleGui extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton launch, send;
	private JLabel labelX1, labelX2;
	private JTextField entryX1, entryX2;
	private RunAgent master;
	
	public SimpleGui(RunAgent a, String title) {
		master = a;
		
		setLayout(new GridLayout(6,1));
		setTitle(title);
		
		launch = new JButton("Launch Soar Agent");
		add(launch);
		launch.addActionListener(new LaunchHandlerClass());
		
		labelX1 = new JLabel("X1");
		entryX1 = new JTextField(10);
		labelX2 = new JLabel("X2");
		entryX2 = new JTextField(10);
		add(labelX1);
		add(entryX1);
		add(labelX2);
		add(entryX2);
		
		entryX1.setText("0.0");
		entryX2.setText("0.0");
		
		send = new JButton("Send new values to agent");
		add(send);
		send.addActionListener(new InfoSenderClass());
		
		setMinimumSize(new Dimension(200,300));
		
	}
	
	private class LaunchHandlerClass implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				master.setupAgent();
			} catch (SoarException | InterruptedException e1) {
				e1.printStackTrace();
			}	
		}

	}

	private class InfoSenderClass implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Double x1 = Double.valueOf(entryX1.getText());
			Double x2 = Double.valueOf(entryX2.getText());
			master.setX1Value(x1);
			master.setX2Value(x2);
		}

	}


}
