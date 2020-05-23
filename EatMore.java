package de.game;

import java.awt.EventQueue;

public class EatMore {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window guiWindow = new Window();
					guiWindow.add(new Game());
					guiWindow.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
