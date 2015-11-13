package main;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.NormalCalculatorController;
import view.CalculatorView;
import Model.NormalCalculatorModel;


public class test {
			
	
	public static void main(String[] args) {
		
        //for the calculator 		
		NormalCalculatorModel theModel = new NormalCalculatorModel();
		CalculatorView theView = new CalculatorView();
		NormalCalculatorController theController = new NormalCalculatorController(theModel, theView);
		
		theView.setVisible(true);
	}
}
