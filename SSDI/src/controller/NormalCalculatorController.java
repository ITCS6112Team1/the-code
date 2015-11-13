package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import view.CalculatorView;
import Model.NiBoLan;
import Model.NormalCalculatorModel;

public class NormalCalculatorController {

	private NormalCalculatorModel theModel;
	private CalculatorView theView;
	boolean firstDigit = true;
	float processNumber = 0;
	String preOperater = "=";

	public NormalCalculatorController(NormalCalculatorModel theModel,
			CalculatorView theView) {
		this.theView = theView;
		this.theModel = theModel;
		this.theView.addCalculationListener(new CalculateListener());
	}

	class CalculateListener implements ActionListener {

		@Override
		/*
		 * Calculator operator action
		 */
		public void actionPerformed(ActionEvent e) {
			String label = e.getActionCommand();
			String text = theView.getResultText();
			
			if(label.equals("Backspace")){
				// user input backspace
				String result[] = theModel.handleBackspace(text, firstDigit);
				theView.setResultText(result[0]);
				firstDigit = result[1].equals("true") ? true : false;
			}else if("0123456789.+-*/()".indexOf(label) >= 0){
				 //user input number or "."
				String[] result = theModel.handleNumber(firstDigit, text, label);
				theView.setResultText(result[0]);
				firstDigit = result[1].equals("true") ? true : false;
			} else if (label.equals("CE")) {
				// user input"CE"
				String[] result = theModel.handleCE(text, firstDigit);
				theView.setResultText(result[0]);
				firstDigit = result[1].equals("true") ? true : false;
			}else if(label.equals("=")){
				NiBoLan exp = new NiBoLan();
				exp.reverse(text);
				float result =  exp.calculate(exp.getReversedExpression());
				theView.setResultText(String.valueOf(result));
				firstDigit = true;
			}else{
				//calculate by NiBolan,and show the result on view
				String[] result = theModel.handleOperator(text, processNumber,
				firstDigit, preOperater, label);
				theView.setResultText(result[0]);
				processNumber = Float.valueOf(result[0]);
				preOperater = result[1];
				firstDigit = result[2].equals("true") ? true : false;
			}
			
			
			
//			else{
//				String[] result = theModel.handleOperator(text, processNumber,
//				firstDigit, preOperater, label);
//				theView.setResultText(result[0]);
//				processNumber = Float.valueOf(result[0]);
//				preOperater = result[1];
//				firstDigit = result[2].equals("true") ? true : false;
//			}
		}
	}


}
