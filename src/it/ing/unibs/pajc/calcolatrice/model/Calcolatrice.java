package it.ing.unibs.pajc.calcolatrice.model;

import java.util.HashMap;
import java.util.Stack;

public class Calcolatrice extends BaseModel
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
	
	private class EvaluateResult{
		Double result;
		Stack<CalcuatorElement> remaingElements;
		
		public EvaluateResult(Double result, Stack<CalcuatorElement> remaingElements) {
			this.result = result;
			this.remaingElements = remaingElements;
			System.out.println(result);
		}
		
	}
	
	private Stack<CalcuatorElement>dati= new Stack<>();
	private HashMap<String,CalcuatorElement> knownOperator=new HashMap<>();

	public Calcolatrice() {
		addKnownOperator("*",(a,b)-> a*b );//LAMDA EXPRESSION
		addKnownOperator("/",(a,b)-> b/a ); // per notazione polacca
		addKnownOperator("+",(a,b)-> a+b );
		addKnownOperator("-",(a,b)-> b-a );// per notazione polacca
		
		addKnownOperator("sqrt", Math::sqrt);//lamda expression con un singolo valore ::7
	}
	
	public void addKnownOperator(String name,BinaryOperator op)
	{
		knownOperator.put(name, new CalcuatorElement(name,op));
		
	}
	
	public void addKnownOperator(String name,UnaryOperator op)
	{
		knownOperator.put(name, new CalcuatorElement(name,op));
	}
	
	public Double pushOperand(Double b)
	{
		dati.add(new CalcuatorElement(b));
		
		return evaluate();
	}
	
	private EvaluateResult evaluate(Stack<CalcuatorElement> opStack)
	{
		if(opStack.isEmpty())
			return new EvaluateResult(null, opStack);
		
		Stack<CalcuatorElement> wStack=(Stack<CalcuatorElement>) opStack.clone();
		
		CalcuatorElement op=wStack.pop();

		if(!op.isOperator)
		{
			return new EvaluateResult(op.value,wStack);
		}else if(op.operator instanceof UnaryOperator)
		{
			EvaluateResult opEval= evaluate(wStack);
			if(opEval!=null)
				return new EvaluateResult(((UnaryOperator)op.operator).eval(opEval.result),opEval.remaingElements);
		}else if(op.operator instanceof BinaryOperator)
		{
			EvaluateResult opEval1= evaluate(wStack);
			/*wStack.pop();
			EvaluateResult opEval2= evaluate(wStack);
			if(opEval1!=null && opEval2!=null)*/
			if(opEval1!=null)
			{
				EvaluateResult opEval2= evaluate(opEval1.remaingElements);
				if(opEval2!=null)
				{
					return new EvaluateResult(((BinaryOperator)op.operator).eval(opEval1.result,opEval2.result),opEval2.remaingElements);
				}
			}	
		}
		return new EvaluateResult(null, opStack);
	}
	private Double evaluate()
	{	
		return evaluate((Stack<CalcuatorElement>)dati.clone()).result;
	}
	public Double performOperation(String op)
	{
		switch(op)
		{
		case"CA":dati.clear();
			break;
		case"CL":if(dati.size()>0)
				dati.pop();
			break;
		default:
			if(knownOperator.containsKey(op))
				dati.push(knownOperator.get(op));
		}		
		return dati.size()>0 ? evaluate(): 0.;
	}
}