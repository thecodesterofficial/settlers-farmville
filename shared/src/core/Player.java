package core;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Player {
	public static GameBoard game;
	public Color color;
	public String username;
	public ArrayList<ResourceCardType> cards = new ArrayList<ResourceCardType>();

	public Player(Color color, String username) {
		this.color = color;
		this.username = username;
	}

}