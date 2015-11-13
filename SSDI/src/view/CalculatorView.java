package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorView extends JFrame{
	private final String[] KEYS = { "7", "8", "9", "/", "sqrt", "4", "5", "6",
			"*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };
	private final String[] COMMAND = { "Backspace", "CE", "C" };
	//private final String[] M = { "Formula", "MC", "MR", "MS", "M+" };
	private final String[] M={"(", ")"};
	private JButton keys[] = new JButton[KEYS.length];
	private JButton commands[] = new JButton[COMMAND.length];
	private JButton m[] = new JButton[M.length];
	private JTextField resultText = new JTextField("0");
	
	
	// if it's the first number
	private boolean firstDigit = true;
	// the result of process
	private double resultNum = 0.0;
	// the operator right now
	private String operator = "=";

	private boolean operateValidFlag = true;

	public CalculatorView() {
		super();
		init();
		this.setBackground(Color.LIGHT_GRAY);
		this.setTitle("Calculator");
		this.setLocation(500, 300);
		this.setResizable(false);
		this.pack();
	}
	
	private void init() {
				resultText.setHorizontalAlignment(JTextField.RIGHT);
				// not allowed to change test label
				resultText.setEditable(false);
				// set white as the backgroud color
				resultText.setBackground(Color.white);

				JPanel calckeysPanel = new JPanel();
				calckeysPanel.setLayout(new GridLayout(4, 5, 3, 3));
				for (int i = 0; i < KEYS.length; i++) {
					keys[i] = new JButton(KEYS[i]);
					calckeysPanel.add(keys[i]);
					keys[i].setForeground(Color.blue);
				}
				// set color of operation bottoms
				keys[3].setForeground(Color.red);
				keys[8].setForeground(Color.red);
				keys[13].setForeground(Color.red);
				keys[18].setForeground(Color.red);
				keys[19].setForeground(Color.red);

				// function bottoms
				JPanel commandsPanel = new JPanel();
				commandsPanel.setLayout(new GridLayout(1, 3, 3, 3));
				for (int i = 0; i < COMMAND.length; i++) {
					commands[i] = new JButton(COMMAND[i]);
					commandsPanel.add(commands[i]);
					commands[i].setForeground(Color.red);
				}

				// "M"bottoms
				JPanel calmsPanel = new JPanel();
				calmsPanel.setLayout(new GridLayout(5, 1, 3, 3));
				for (int i = 0; i < M.length; i++) {
					m[i] = new JButton(M[i]);
					calmsPanel.add(m[i]);
					m[i].setForeground(Color.red);
				}

				//the whole view of normal calculator
				JPanel panel1 = new JPanel();
				panel1.setLayout(new BorderLayout(3, 3));
				panel1.add("North", commandsPanel);
				panel1.add("West", calckeysPanel);

				JPanel top = new JPanel();
				top.setLayout(new BorderLayout());
				top.add("Center", resultText);

				// total layout
				getContentPane().setLayout(new BorderLayout(3, 5));
				getContentPane().add("North", top);
				getContentPane().add("Center", panel1);
				getContentPane().add("West", calmsPanel);

	}
	
	// set listeners to bottoms of calculator
	public void addCalculationListener(ActionListener listenForCalcButtom){
		for (int i = 0; i < KEYS.length; i++) {
			keys[i].addActionListener(listenForCalcButtom);
		}
		for (int i = 0; i < COMMAND.length; i++) {
			commands[i].addActionListener(listenForCalcButtom);
		}
		for (int i = 0; i < M.length; i++) {
			m[i].addActionListener(listenForCalcButtom);
		}
	}
	

	public String getResultText() {
		return resultText.getText();
	}

	public void setResultText(String resultText) {
		this.resultText.setText(resultText);
	}
	
	
}
