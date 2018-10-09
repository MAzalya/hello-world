import javax.swing.JFrame;

import com.shimmerresearch.bluetooth.ShimmerBluetooth.BT_STATE;
import com.shimmerresearch.driver.BasicProcessWithCallBack;
import com.shimmerresearch.driver.CallbackObject;
import com.shimmerresearch.driver.ObjectCluster;
import com.shimmerresearch.driver.ShimmerMsg;
import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.guiUtilities.plot.BasicPlotManagerPC;
import com.shimmerresearch.pcDriver.ShimmerPC;
import com.shimmerresearch.tools.bluetooth.BasicShimmerBluetoothManagerPc;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestMain extends BasicProcessWithCallBack {
	static ShimmerPC shimmer = new ShimmerPC("ShimmerDevice"); 
	static BasicShimmerBluetoothManagerPc btManager = new BasicShimmerBluetoothManagerPc();
	private JTextField statusConnect;
	BasicPlotManagerPC plotManager = new BasicPlotManagerPC();
	
	public static void main(String[] args) {
		
		TestMain tm = new TestMain();
		tm.initialize();
		//tm.setWaitForData(btManager.callBackObject);
		tm.setWaitForData(shimmer);
		// TODO Auto-generated method stub
		}

	protected void initialize() {
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JPanel panelConnect = new JPanel();
		panelConnect.setBounds(30, 32, 346, 35);
		frame.getContentPane().add(panelConnect);
		
		JButton btnConnect = new JButton("Connect");
		panelConnect.add(btnConnect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		panelConnect.add(btnDisconnect);
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shimmer.disconnect();
				} catch (ShimmerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		statusConnect = new JTextField();
		panelConnect.add(statusConnect);
		statusConnect.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(74, 80, 272, 47);
		frame.getContentPane().add(panel);
		
		JButton btnStartStreaming = new JButton("Start Streaming");
		panel.add(btnStartStreaming);
		btnStartStreaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shimmer.startStreaming();
			}
		});
		
		JButton btnStopStreaming = new JButton("Stop Streaming");
		panel.add(btnStopStreaming);
		btnStopStreaming.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shimmer.stopStreaming();
			}
		});
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shimmer.connect("COM14", null);
				//statusConnect.setText("connecting...");
			}
		});
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
		
	
	protected void processMsgFromCallback(ShimmerMsg shimmerMSG) {
			// TODO Auto-generated method stub

			int ind = shimmerMSG.mIdentifier;

			Object object = (Object) shimmerMSG.mB;

			if (ind == ShimmerPC.MSG_IDENTIFIER_STATE_CHANGE) {
				CallbackObject callbackObject = (CallbackObject)object;
				
				if (callbackObject.mState == BT_STATE.CONNECTING) {
					statusConnect.setText("connecting...");
				} else if (callbackObject.mState == BT_STATE.CONNECTED) {
					statusConnect.setText("connected");
					//shimmer = (ShimmerPC) btManager.getShimmerDeviceBtConnected("COM11");
//					shimmerDevice = btManager.getShimmerDeviceBtConnected(btComport);
					//shimmer.startStreaming();
				} else if (callbackObject.mState == BT_STATE.DISCONNECTED
//						|| callbackObject.mState == BT_STATE.NONE
						|| callbackObject.mState == BT_STATE.CONNECTION_LOST){
					statusConnect.setText("disconnected");				
				}
			} else if (ind == ShimmerPC.MSG_IDENTIFIER_NOTIFICATION_MESSAGE) {
				CallbackObject callbackObject = (CallbackObject)object;
				int msg = callbackObject.mIndicator;
				if (msg== ShimmerPC.NOTIFICATION_SHIMMER_FULLY_INITIALIZED){
					statusConnect.setText("device fully initialized");
				}
				if (msg == ShimmerPC.NOTIFICATION_SHIMMER_STOP_STREAMING) {
					statusConnect.setText("device stopped streaming");
				} else if (msg == ShimmerPC.NOTIFICATION_SHIMMER_START_STREAMING) {
					statusConnect.setText("device streaming");
				} else {}
			} else if (ind == ShimmerPC.MSG_IDENTIFIER_DATA_PACKET) {
				System.out.println("Shimmer MSG_IDENTIFIER_DATA_PACKET");
				ObjectCluster objc = (ObjectCluster) shimmerMSG.mB;
				
				try {
					//plotManager.filterDataAndPlot(objc);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else if (ind == ShimmerPC.MSG_IDENTIFIER_PACKET_RECEPTION_RATE_OVERALL) {
				
			}		
			
	}
}
