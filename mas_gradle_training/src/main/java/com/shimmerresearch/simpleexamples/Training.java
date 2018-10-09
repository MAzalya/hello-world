package com.shimmerresearch.simpleexamples;

import javax.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JTextField;

import com.shimmerresearch.driver.ShimmerDevice;
import com.shimmerresearch.pcDriver.ShimmerPC;
import com.shimmerresearch.tools.bluetooth.BasicShimmerBluetoothManagerPc;



public class Training {
	private static JTextField txtComPort;
	private static JTextField txtStatus;
	static ShimmerPC shimmer = new ShimmerPC("ShimmerDevice"); 
	static ShimmerDevice shimmerDevice;
	static BasicShimmerBluetoothManagerPc btManager = new BasicShimmerBluetoothManagerPc();

	
	static String btComport;
	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame ("Button Button");
		frame.setSize(439,361);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel ();
		frame.getContentPane().add(panel);
		
		JPanel upPanel = new JPanel();
		panel.add(upPanel);
		
		JLabel lblEnterComPort = new JLabel("Enter COM Port");
		upPanel.add(lblEnterComPort);
		
		txtComPort = new JTextField();
		upPanel.add(txtComPort);
		txtComPort.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		upPanel.add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btComport = txtStatus.getText();
				btManager.connectShimmerThroughCommPort(btComport);
				
			}
});
		
		JButton btnDisconnect = new JButton("Disconnect");
		upPanel.add(btnDisconnect);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btManager.disconnectShimmer(shimmer);
				
			}
		});
		
		JPanel downPanel = new JPanel();
		panel.add(downPanel);
		
		JLabel lblNewLabel = new JLabel("Status");
		downPanel.add(lblNewLabel);
		
		txtStatus = new JTextField();
		downPanel.add(txtStatus);
		txtStatus.setColumns(10);
		JButton btnStartStreaming = new JButton ("Start Streaming");
		downPanel.add(btnStartStreaming);
		btnStartStreaming.setSize(100, 100);
		btnStartStreaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				shimmer.startStreaming();
				
			}
		});
		
		JButton btnStopStreaming = new JButton("Stop Streaming");
		downPanel.add(btnStopStreaming);
		btnStopStreaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				shimmer.stopStreaming();
				
			}
		});
		btnStartStreaming.setVisible(true);
		
	}
			
}
		
	
