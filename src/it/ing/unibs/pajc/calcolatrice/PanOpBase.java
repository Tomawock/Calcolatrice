package it.ing.unibs.pajc.calcolatrice;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

public abstract class PanOpBase extends JComponent implements ActionListener
{
	//publish subscribe
	
	static final int OPSIZE=45;
	
	FlowLayout layout;
	
	public PanOpBase() 
	{
		super();
		layout=new FlowLayout(FlowLayout.LEFT,5,5);
		this.setLayout(layout);
		this.addOperator();
	}
	
	public void addOperator(String name,int width,int height)
	{
		JButton btn=new JButton(name);
		btn.setPreferredSize(new Dimension(width,height));//setta la dimensione di bottoni
		this.add(btn);	
		btn.addActionListener(this);
		//gestione eventi.....
	}
	
	public void addOperator(String name)
	{
		this.addOperator(name, OPSIZE, OPSIZE);
	}
	abstract void addOperator();
	
	
	//*****
	public void actionPerformed(ActionEvent e)
	{
		fireActionPerformed(e);
	}
	public void addActionListener(ActionListener l)
	{
		listenerList.add(ActionListener.class, l);
	}
	
	public void fireActionPerformed(ActionEvent event)
	{
		//elaboro 2 dati alla volta perchè salvo
		//i dati nell'array come classe + listener
		//ciclo al contrario solo x non leggere n volte la length dell'array
		Object[] listener=listenerList.getListenerList();
		
		ActionEvent e =null;//oggetto unico ma lo cambio ogni volta nell'if LAZY 
		
		for(int i=listener.length-2;i>=0;i-=2)
		{
			if (e==null)
			{
				e=new ActionEvent(this, 
						ActionEvent.ACTION_PERFORMED,
						event.getActionCommand(),
						event.getWhen(),
						event.getModifiers());
			}
			if(listener[i]==ActionListener.class)
				((ActionListener)listener[i+1]).actionPerformed(e);
		}
	}
	
}
