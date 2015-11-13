package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.sound.sampled.ReverbType;

public class NiBoLan {
	//create two stack to store 
		private Stack<String> operate ;
		private Stack<String> reversedExpression;
		private float result = 0.0f;
		private Map<String , Integer> priorityOperation = new HashMap<String, Integer>(); 
		
		public NiBoLan(){
			priorityOperation.put("+", 0);
			priorityOperation.put("-", 0);
			priorityOperation.put("*", 1);
			priorityOperation.put("/", 1);
			priorityOperation.put("(", 0);
			priorityOperation.put(")", 0);
			operate = new Stack<String>();
			reversedExpression = new Stack<String>();
		}
		
		public int getPriority(String operation ){
			return priorityOperation.get(operation);
		}
		
		//compare the priority between left String and head element of operation stack
		// true: op is bigger 
		public boolean higherPriority(String op , String op2){
			int op_Priority = getPriority(op);
			int op2_Priority = getPriority(op2);
			if(op_Priority > op2_Priority){
				return true;
			}else{
				return false;
			}
		}
		
		public void reverse(String expression){
			int index = 0;
			int end = 0;
			for(int i = 0; i < expression.length() ; i++){
				if( String.valueOf( expression.charAt(i) ).matches(  "[0-9.]")  ){
					//make sure what we get is the whole value
					end++;
				}else{
					//put the number into stack 
					if(index!=end){
						String temp = expression.substring(index, end);
						reversedExpression.push(temp);
					}
					String tempOperate = expression.substring(end, ++end);
					index = end;
					if( tempOperate.equals("(") || tempOperate.equals("*") || tempOperate.equals("/") ){ 
						operate.push(tempOperate);
					}else if( tempOperate.equals("+") || tempOperate.equals("-") ){
						//judge the priority , then put into stack by condition
						while(!operate.empty()){
							String op2 = operate.lastElement();
								if( higherPriority(op2 , tempOperate)){
									String ele = operate.pop();
									reversedExpression.push(ele);
								}else{
									break;
								}
						}
						operate.push(tempOperate);
					}else if(tempOperate.equals(")") ){
						//get the operation between the last "(", and then put into stack
						while(!operate.empty()){
							if(!operate.lastElement().equals("(")){
//								System.out.println(operate.lastElement());
								String ele = operate.pop();
								reversedExpression.push(ele);
							}else{
								operate.pop();
								break;
							}
						}
				}
			}
			}
			//put the rest elements of operate stack into reversed stack
			if(index!=end){
				String lastNumber = expression.substring(index, end); 
				reversedExpression.push(lastNumber);
			}
			while(!operate.empty()){
				String rest = operate.pop();
				reversedExpression.push(rest);
			}
		}


		public Stack<String> getReversedExpression() {
			return reversedExpression;
		}
		
		public float calculate(Stack<String> reversedExpression){
			Stack<Float> resultStack = new Stack<Float>();
			float tempResult = 0.0f;
			for(String s:reversedExpression){
				if(s.matches("^[0-9]+.?[0-9]*$")){
					resultStack.push(Float.parseFloat(s));
				}
				else{
					float num1 = resultStack.pop();
					float num2 = resultStack.pop();
					if(s.equals("+")){
						tempResult = num1 + num2;
					}else if(s.equals("-")){
						tempResult = num2 - num1;
					}else if(s.equals("*")){
						tempResult = num1 * num2;
					}else if(s.equals("/")){
						tempResult = num2 / num1;
					}
					resultStack.push(tempResult);
				}
			}
			return tempResult;
		}
		
		public float cal(String expression){
			reverse(expression);
			return calculate(getReversedExpression());
		}
}
