package it.ing.unibs.pajc.calcolatrice.model;

import java.util.HashMap;
import java.util.Stack;

public class Calcolatrice 
{	
	private class CalcuatorElement {
		
		BaseOperator operator;
		Double value;
		boolean isOperator;
		String name;
		
		public CalcuatorElement(String name,BaseOperator operator) {
			this.operator = operator;
			this.isOperator=true;
			this.name=name;
			this.value=null;
		}
		
		public CalcuatorElement(Double value) {
			this.value = value;
			this.isOperator=false;
			this.name=null;
			this.operator=null;
		}
	}
	
	private Stack<CalcuatorElement>dati= new Stack<>();
	private HashMap<String,CalcuatorElement> knownOperators=new HashMap<>();
	
	public Calcolatrice() {
		knownOperators.put("*",new CalcuatorElement("*",new BinaryOperator() {
			
			@Override
			public double eval(double a, double b) {
				
				return a*b;
			}}) );
		knownOperators.put("/", );
		knownOperators.put("+", );
		knownOperators.put("-", );
		
		knownOperators.put("sqrt", );
	}
	
	
				
}
