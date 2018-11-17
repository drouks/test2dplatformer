package com.drouks.cpg.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

  public Window(int height, int width, String title, Game game)
  {
	game.setPreferredSize(new Dimension(width,height));
	game.setMaximumSize(new Dimension(width,height));
	game.setMinimumSize(new Dimension(width,height));
	
	JFrame frame = new JFrame(title);
	frame.add(game);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	
  }
	
	
}
