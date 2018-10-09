import javax.swing.JFrame;

import com.shimmerresearch.exceptions.ShimmerException;
import com.shimmerresearch.pcDriver.ShimmerPC;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestMain {
	static ShimmerPC shimmer = new ShimmerPC("ShimmerDevice"); 
	public static void main(String[] args) {
	
		TestMain tm = new TestMain();
		tm.initialize();
		// TODO Auto-generated method stub
		}

	protected void initialize() {
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shimmer.connect("COM7", null);
			}
		});
		btnNewButton.setBounds(12, 13, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDisconnect = new JButton("Disconnect");
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
		btnDisconnect.setBounds(12, 51, 97, 25);
		frame.getContentPane().add(btnDisconnect);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
