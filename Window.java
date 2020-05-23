package de.game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame {

	private JPanel contentPane;
	private static JLabel lblNewLabel;

	public Window() {
		/**
		 * Create the frame.
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 335, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("EatMore - Zimm's Snake Clone");
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 300, 370);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("Score: 0");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 18));
		lblNewLabel.setBounds(160, 10, 140, 40);
		panel.add(lblNewLabel);

	}
	
	public static void setScore(String s) {
		lblNewLabel.setText(s);
	}

}
