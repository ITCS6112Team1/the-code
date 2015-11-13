package Model;

public class NormalCalculatorModel {
		//input "backspace"
		public String[] handleBackspace(String text , boolean firstDigit){
			int i = text.length();
			String[] back = {text , String.valueOf(firstDigit) };
			if(i > 1){
				back[0] = text.substring(0, i-1);
			}else{
				back[0] = "0";
				back[1] = "true";
			}
			return back;
		}
		//input "C"
		public String handleC(){
			
			return "0";
		}
		//input "CE"
		public String[] handleCE(String text , boolean firstDigit){
			String[] back = {text , String.valueOf(firstDigit) };
			back[0] = "0";
			back[1]  = "true";
			return back;
		}
		//input numbers
		public String[] handleNumber(boolean firstDigit , String text , String key) {
			/*
			 * input numbers 
			 * "text" is the text from textBox , key is the input number
			 * output string = { text , firstDigit }
			 */
			String[] back = {text , String.valueOf(firstDigit)};
			if(firstDigit){
				text = key;
			}else if(key.equals(".") && !text.contains(".")){
				text = text+".";
			}else if(!key.equals(".")){
				text = text +key ; 
			}
			back[0] = text;
			back[1] =  "false";
			return back;
		}
		
		//input operator
		public String[] handleOperator(String text , float processNumber , boolean firstDigit ,  String preOperater , String key) {
			
			String[] back = {String.valueOf(processNumber) , preOperater ,String.valueOf(firstDigit)};
			float calculateNumber = Float.valueOf(text);
			if(key.equals("+/-")){
				processNumber = -calculateNumber;
			}else if(key.equals("sqrt")){
				processNumber = (float) Math.sqrt(calculateNumber);
			}else if(key.equals("%")){
				processNumber = calculateNumber/100;
			}else if(key.equals("1/x")){
				processNumber = 1/calculateNumber;
			}
			back[0] = String.valueOf(processNumber);
			back[1] = key;
			back[2] = "true";
			return back;	
		}
		
}
