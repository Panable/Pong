package com.alkira.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.alkira.main.Pong;

/*
 * Developed by Dhanveer Ramnauth.
 */

public class Keyboard implements KeyListener {

	private boolean w, s, up, down;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) w = true;
		if (key == KeyEvent.VK_S) s = true;
		if (key == KeyEvent.VK_UP) up = true;
		if (key == KeyEvent.VK_DOWN) down = true;
		
		if (key == KeyEvent.VK_X) {
			Pong.score1 = 0;
			Pong.score2 = 0;
		}
		
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) w = false;
		if (key == KeyEvent.VK_S) s = false;
		if (key == KeyEvent.VK_UP) up = false;
		if (key == KeyEvent.VK_DOWN) down = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean isW() {
		return w;
	}

	public boolean isS() {
		return s;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

}
