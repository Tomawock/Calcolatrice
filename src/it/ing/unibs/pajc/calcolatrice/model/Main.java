package it.ing.unibs.pajc.calcolatrice.model;

public class Main {

	public static void main(String[] args)
	{
		Calcolatrice myCalc=new Calcolatrice();
		myCalc.pushOperand(4.0);
		myCalc.pushOperand(2.0);
		myCalc.performOperation("+");
		myCalc.pushOperand(6.0);
		myCalc.performOperation("*");
	}

}
