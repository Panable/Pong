package com.alkira.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.alkira.input.Keyboard;
import com.alkira.objects.Ball;
import com.alkira.objects.Paddle;
import com.alkira.objects.Paddle.Controller;

/*
 * Developed by Dhanveer Ramnauth.
 */

public class Pong extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800, HEIGHT = 600;
	public static String window_title = "Pong!";
	private JFrame frame;

	private Thread thread;
	private boolean running = false;

	private Keyboard key;
	private Paddle paddle1;
	private Paddle paddle2;
	private Ball ball;

	public Pong() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setSize(size);

		frame = new JFrame();
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle(window_title);
		frame.setVisible(true);

		key = new Keyboard();
		addKeyListener(key);

		paddle1 = new Paddle(10, 10, 5, key, Controller.one);
		paddle2 = new Paddle(WIDTH - 10 - 18, 10, 5, key, Controller.two);
		ball = new Ball(30, 50, 2, paddle1, paddle2);

		start();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		requestFocus();

		double updates = 60.0;
		double ns = 1000000000.0 / updates;
		double delta = 0;

		double ball_ns = 1000000000.0;
		double ball_delta = 0;
		
		int frames = 0;

		long ball_lastTime = System.nanoTime();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				delta = 0;
			}
			
			long ball_currentTime = System.nanoTime();
			ball_delta += (ball_currentTime - ball_lastTime) / (ball_ns / Ball.updates);
			ball_lastTime = ball_currentTime;

			if (ball_delta >= 1) {
				ball.update();
				ball_delta = 0;
			}
			
			render();
			frames++;

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				frame.setTitle(window_title + "   | FPS:  " + frames);
				frames = 0;
			}

		}
		stop();
	}

	int x = WIDTH / 2;

	private void update() {
		paddle1.update();
		paddle2.update();
	}

	public static int score1 = 0, score2 = 0;
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		////////////////////////////////////////////////////////////////////////

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Verdana", Font.PLAIN, 20));
		g.drawString(Integer.toString(score1), WIDTH / 2 - 50, 40);
		g.drawString(Integer.toString(score2), WIDTH / 2 + 50, 40);
		
		paddle1.render(g);
		paddle2.render(g);
		ball.render(g);

		////////////////////////////////////////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Pong();
	}

}
