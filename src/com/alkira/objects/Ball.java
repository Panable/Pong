package com.alkira.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.alkira.main.Pong;

/*
 * Developed by Dhanveer Ramnauth.
 */

public class Ball {

	public static double updates = 60.0;
	public final double update_multiplier = 1.25;

	boolean destroyed = false;

	private int width = 8, height = 8;
	private int m_x, m_y, m_speed;
	private int startX, startY;
	private int velX, velY;
	private Paddle m_paddle1, m_paddle2;
	private Random random;

	public Ball(int x, int y, int speed, Paddle paddle1, Paddle paddle2) {
		m_x = x;
		m_y = y;
		startX = x;
		startY = y;
		m_paddle1 = paddle1;
		m_paddle2 = paddle2;
		m_speed = speed;
		velX = speed;
		velY = -speed / 2;
		random = new Random();
	}

	public void update() {
		m_x += velX;
		m_y += velY;
		checkCollision();
	}

	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(m_x, m_y, width, height);
	}

	private Rectangle getBounds() {
		return new Rectangle(m_x, m_y, width, height);
	}

	public void checkCollision() {
		if (getBounds().intersects(m_paddle1.getBounds())) {
			velX *= -1;
			updates = (random.nextInt(2) == 0) ? updates * update_multiplier : updates;

		}
		if (getBounds().intersects(m_paddle2.getBounds())) {
			velX *= -1;
			updates = (random.nextInt(2) == 0) ? updates * update_multiplier : updates;
		}

		if (m_y < 0)
			velY *= -1;
		if (m_y > Pong.HEIGHT - height)
			velY *= -1;

		if (m_x < m_paddle1.getX() + 16) {
			Pong.score2++;

			m_x = startX;
			m_y = startY;
			velX = m_speed;
			velY = m_speed;
			updates = 60.0;
		}
		if (m_x > m_paddle2.getX()) {
			Pong.score1++;
			m_x = startX;
			m_y = startY;
			velX = m_speed;
			velY = m_speed;
			updates = 60.0;
		}

	}

	public boolean isDestroyed() {
		return destroyed;
	}

}
