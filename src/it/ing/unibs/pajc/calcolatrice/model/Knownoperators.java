package it.ing.unibs.pajc.calcolatrice.model;

import java.util.HashMap;

public class Knownoperators {
	
	HashMap<String,BaseOperator> dati;

	public Knownoperators() {
		dati.put("*",new BinaryOperator() {
			
			@Override
			public double eval(double a, double b) {
				// TODO Auto-generated method stub
				return 0;
			}
		}; {
		})
	}
	

}
