package it.ing.unibs.pajc.calcolatrice;

public class PanKeyPad extends PanOpBase {
	
	@Override
	void addOperator() {
		for(int i=9;i>=0;i--)
		{
			super.addOperator(""+i);
		}

	}

}
