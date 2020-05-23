package de.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

	// Attribute / Eigenschaften

	// Größe des Spielfeldes
	private int width = 300;
	private int height = 300;

	// Aufbau der Schlange
	private Image apple;
	private Image head;
	private Image tail;
	private int tail_amount = 3; // Startlänge
	private int snake_width = 10; // Abmaß in Pixeln
	private int snake_x[] = new int[width * height / (snake_width * snake_width)];
	private int snake_y[] = new int[width * height / (snake_width * snake_width)];

	// Position des Apfels
	private int apple_x;
	private int apple_y;

	private static boolean running;

	private String gameOverString = "Game Over!";

	// Timer stellt Bewegungsgeschwindigkeit der Schlange da
	private Timer t;

	public static int direction;

	private int score = 0;

	public Game() {

		addKeyListener(new SnakeListener());
		setBounds(10, 50, width, height);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		setFocusable(true);

		URL resource = Game.class.getResource("apfel_s.png");
		URL resource1 = Game.class.getResource("head_s.png");
		URL resource2 = Game.class.getResource("tail_s.png");

		ImageIcon icon_apple = new ImageIcon(resource);
		ImageIcon icon_head = new ImageIcon(resource1);
		ImageIcon icon_tail = new ImageIcon(resource2);

		apple = icon_apple.getImage();
		head = icon_head.getImage();
		tail = icon_tail.getImage();

		// Start Punkt der Schlange
		for (int i = 0; i < tail_amount; i++) {
			snake_x[i] = 100 - i * 10;
			snake_y[i] = 50;

		}

		running = true;

		t = new Timer(75, this);
		t.start();

		spawn_apple();

	}

	// Methode erstellt Apfel im Spielfeld
	private void spawn_apple() {

		int randomX = (int) (Math.random() * 29);
		int randomY = (int) (Math.random() * 29);

		apple_x = randomX * snake_width;
		apple_y = randomY * snake_width;

		for (int i = 0; i < tail_amount; i++) {
			if (snake_x[i] == apple_x && snake_y[i] == apple_y) {
				spawn_apple();
				break;
			} else {
				apple_x = randomX * snake_width;
				apple_y = randomY * snake_width;

			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			check_apple();
			check_death();
			move_snake();
		}
		repaint();

	}

	// Bewegungsmethode der Schlange
	private void move_snake() {

		for (int i = tail_amount; i > 0; i--) {
			snake_x[i] = snake_x[i - 1];
			snake_y[i] = snake_y[i - 1];
		}

		switch (direction) {

		case 0:
			snake_x[0] -= snake_width;
			break;

		case 1:
			snake_x[0] += snake_width;
			break;

		case 2:
			snake_y[0] -= snake_width;
			break;

		case 3:
			snake_y[0] += snake_width;
			break;

		default:
			break;
		}

	}

	// Checkt ob Schlage tot ist
	private void check_death() {

		for (int i = tail_amount; i > 3; i--) {
			if (snake_x[0] == snake_x[i] && snake_y[0] == snake_y[i]) {
				running = false;
			}
		}
		if (snake_y[0] >= height || snake_x[0] >= width || snake_y[0] < 0 || snake_x[0] < 0) {
			running = false;
		}
		if (!running) {
			t.stop();

		}

	}

	// Checkt ob Apfel "gefressen" wurde
	private void check_apple() {
		if (snake_x[0] == apple_x && snake_y[0] == apple_y) {
			tail_amount++;
			score = score + 10;
			spawn_apple();
			Window.setScore("Score: " + score);

		}

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (running) {

			g.drawImage(apple, apple_x, apple_y, this);

			for (int i = 1; i < tail_amount; i++) {
				g.drawImage(tail, snake_x[i], snake_y[i], this);
			}

			g.drawImage(head, snake_x[0], snake_y[0], this);

			Toolkit.getDefaultToolkit().sync();
		} else {

			Font f = new Font("Consolas", Font.BOLD, 16);
			FontMetrics metrics = getFontMetrics(f);

			g.setColor(Color.CYAN);
			g.setFont(f);
			g.drawString(gameOverString, (width - metrics.stringWidth(gameOverString) - 100), (int) (height / 5));

		}

	}

}
