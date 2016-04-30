import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Spammer {

	public static boolean running = false;
	public static Timer timer;

	public static void typeWithShift(Robot robot, int keyCode) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(keyCode);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(keyCode);
	}

	public static void sendMessage(String text) {
		try {
			Robot robot = new Robot();
			int[] keys = new int[text.length()];
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				keys[i] = KeyEvent.getExtendedKeyCodeForChar(c);
				if (Character.isUpperCase(c)) {
					robot.keyPress(KeyEvent.VK_SHIFT);
				}
				if (c == '!') {
					typeWithShift(robot, KeyEvent.VK_1);
					continue;
				}
				if (c == '?') {
					typeWithShift(robot, KeyEvent.VK_SLASH);
					continue;
				}
				if (c == '@') {
					typeWithShift(robot, KeyEvent.VK_2);
					continue;
				}
				if (c == '#') {
					typeWithShift(robot, KeyEvent.VK_3);
					continue;
				}
				if (c == '$') {
					typeWithShift(robot, KeyEvent.VK_4);
					continue;
				}
				if (c == '%') {
					typeWithShift(robot, KeyEvent.VK_5);
					continue;
				}
				if (c == '^') {
					typeWithShift(robot, KeyEvent.VK_6);
					continue;
				}
				if (c == '&') {
					typeWithShift(robot, KeyEvent.VK_7);
					continue;
				}
				if (c == '*') {
					typeWithShift(robot, KeyEvent.VK_8);
					continue;
				}
				if (c == '(') {
					typeWithShift(robot, KeyEvent.VK_9);
					continue;
				}
				if (c == ')') {
					typeWithShift(robot, KeyEvent.VK_0);
					continue;
				}
				if (c == '<') {
					typeWithShift(robot, KeyEvent.VK_COMMA);
					continue;
				}
				if (c == '>') {
					typeWithShift(robot, KeyEvent.VK_PERIOD);
					continue;
				}
				if (c == '+') {
					typeWithShift(robot, KeyEvent.VK_EQUALS);
					continue;
				}
				robot.keyPress(keys[i]);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}

			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Window window = new Window("Spammer", 600, 325);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 325));
		panel.setLayout(null);
		window.getFrame().getContentPane().add(panel);

		// Spam Message UI Display
		window.addLabel("spamMessageName", panel, "Spam Message: ", 100, 38, 200, 50, new Font("Arial", Font.BOLD, 18));
		window.addTextField("spamMessage", panel, 250, 50, 300, 25);

		// Interval UI Display
		window.addLabel("intervalMessageName", panel, "Interval(ms): ", 100, 88, 200, 50, new Font("Arial", Font.BOLD, 18));
		window.addTextField("intervalMessage", panel, 250, 100, 300, 25);

		// Button UI Display
		window.addButton("startStop", panel, "Start", 250, 180, 100, 100);

		window.getFrame().revalidate();
		window.getFrame().repaint();

		JButton startStop = (JButton) window.getComponent("startStop");
		JTextField spamMessage = (JTextField) window.getComponent("spamMessage");

		startStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!running) {
					timer = new Timer();
					startStop.setText("Stop");
					System.out.println(spamMessage.getText());
					timer.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							sendMessage(spamMessage.getText());
						}
					}, Integer.parseInt(((JTextField) window.getComponent("intervalMessage")).getText()) + 5000, Integer.parseInt(((JTextField) window.getComponent("intervalMessage")).getText()));
					running = true;
				} else {
					running = false;
					startStop.setText("Start");
					timer.cancel();
				}
			}
		});
	}
}