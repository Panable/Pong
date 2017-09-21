package com.alkira.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.alkira.input.Keyboard;
import com.alkira.main.Pong;

/*
 * Developed by Dhanveer Ramnauth.
 */

public class Paddle {

	public enum Controller {
		one(), two();
	}

	private int width = 18, height = 80;
	private int m_x, m_y, m_speed;
	private Controller m_controller;
	private Keyboard m_key;

	public Paddle(int x, int y, int speed, Keyboard key, Controller controller) {
		m_x = x;
		m_y = y;
		m_speed = speed;
		m_key = key;
		m_controller = controller;
	}

	public void update() {

		if (m_controller == Controller.one) {

			if (m_key.isW())
				m_y -= m_speed;
			if (m_key.isS())
				m_y += m_speed;
		} else {
			if (m_key.isUp())
				m_y -= m_speed;
			if (m_key.isDown())
				m_y += m_speed;
		}

		if (m_y < 0)
			m_y = 0;
		if (m_y >= Pong.HEIGHT - height)
			m_y = Pong.HEIGHT - height;
	}

	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(m_x, m_y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle(m_x, m_y, width, height);
	}

	public int getM_speed() {
		return m_speed;
	}

	public void setSpeed(int m_speed) {
		this.m_speed = m_speed;
	}

}
