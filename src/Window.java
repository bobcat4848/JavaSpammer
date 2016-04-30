import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window {

	private JFrame frame;
	private String title;
	private int width, height;
	private Map<String, Component> panelComps = new HashMap<>();
	
	public Window(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		frame = new JFrame(title);
		frame.setSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void addButton(String name, JPanel panel, String text, int x, int y, int w, int h) {
		JButton button = new JButton(text);
		button.setBounds(x, y, w, h);
		panel.add(button);
		panelComps.put(name, button);
	}
	
	public void addLabel(String name, JPanel panel, String text, int x, int y, int w, int h, Font font) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, w, h);
		label.setFont(font);
		panel.add(label);
		panelComps.put(name, label);
	}
	
	public void addLabel(String name, JPanel panel, String text, int x, int y, int w, int h) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, w, h);
		panel.add(label);
		panelComps.put(name, label);
	}
	
	public void addTextField(String name, JPanel panel, int x, int y, int w, int h) {
		JTextField tempInput = new JTextField(20);
		tempInput.setBounds(x, y, w, h);
		panel.add(tempInput);
		panelComps.put(name, tempInput);
	}
	
	public void addComboBox(String name, JPanel panel, int x, int y, int w, int h, String... tempTypeStrings) {
		JComboBox<String> tempType = new JComboBox<>(tempTypeStrings);
		tempType.setBounds(x, y, w, h);
		tempType.setSelectedIndex(0);
		panel.add(tempType);
		panelComps.put(name, tempType);
	}
	
	public Component getComponent(String name) {
		if (panelComps.containsKey(name)) {
			return panelComps.get(name);
		}
		return null;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		this.frame.setTitle(title);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public JFrame getFrame() {
		return frame;
	}
	
}