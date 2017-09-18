package com.alkira.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.alkira.main.Pong;

/*
 * Developed by Dhanveer Ramnauth.
 */

public class Ball {

	boolean destroyed = false;

	private int width = 8, height = 8;
	private int m_x, m_y;
	private int velX, velY;
	private Paddle m_paddle1, m_paddle2;

	public Ball(int x, int y, int speed, Paddle paddle1, Paddle paddle2) {
		m_x = x;
		m_y = y;
		m_paddle1 = paddle1;
		m_paddle2 = paddle2;
		velX = speed;
		velY = -speed;
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
		if (getBounds().intersects(m_paddle1.getBounds()))
			velX *= -1;
		if (getBounds().intersects(m_paddle2.getBounds()))
			velX *= -1;

		if (m_y < 0)
			velY *= -1;
		if (m_y > Pong.HEIGHT - height)
			velY *= -1;

		if (m_x < 0) {
			Pong.score2++;
			destroyed = true;
		}
		if (m_x > Pong.WIDTH - width) {
			Pong.score1++;
			destroyed = true;
		}

	}

	public boolean isDestroyed() {
		return destroyed;
	}

}
