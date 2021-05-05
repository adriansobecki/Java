package lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gui {
	public static void gui()
	{
		JFrame frame = new JFrame("nazwa");
		frame.setSize(800,300);
		
		JTextArea seed = new JTextArea(2, 10);
		seed.setEditable(true);
		JLabel seedLabel = new JLabel("Seed:");
		
		JTextArea size = new JTextArea(2, 10);
		size.setEditable(true);
		JLabel sizeLabel = new JLabel("Size:");
		
		JTextArea capacity = new JTextArea(2, 10);
		seed.setEditable(true);
		JLabel capacityLabel = new JLabel("Capacity:");
		
		JButton submit = new JButton("submit");
		
		
		JPanel panel = new JPanel();
		panel.add(seedLabel);
		panel.add(seed);
		panel.add(sizeLabel);
		panel.add(size);
		panel.add(capacityLabel);
		panel.add(capacity);
		panel.add(submit);
		
		JPanel panel2 = new JPanel();
		
		JTextArea value = new JTextArea(2, 10);
		value.setEditable(false);
		JLabel valueLabel = new JLabel("Value:");
		
		JTextArea remaining = new JTextArea(2, 10);
		remaining.setEditable(false);
		JLabel remainingLabel = new JLabel("Remaining Size:");
		
		panel2.add(valueLabel);
		panel2.add(value);
		panel2.add(remainingLabel);
		panel2.add(remaining);
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int argSeed,argSize,argCapacity;
				argSeed=Integer.parseInt(seed.getText());
				argSize=Integer.parseInt(size.getText());
				argCapacity=Integer.parseInt(capacity.getText());
				
		        RandomNumberGenerator rng = new RandomNumberGenerator(argSeed);
		        List<Item> items = new ArrayList<Item>();

		        Backpack bag = new Backpack(argCapacity);

		        for (int i = 0; i < argSize; i++)
		        {
		        	Item it=new Item();
		        	it.set_value(rng.nextInt(1, 29));
		        	items.add(it);
		        }
		        for (int i = 0; i < argSize; i++)
		        {
		        	var it=items.get(i);
		        	it.set_weight(rng.nextInt(1, 29));
		            bag.addNewItem(it);
		        }
		        value.append(String.valueOf(bag.getRemainingSize()));
		        remaining.append(String.valueOf(bag.getValue()));
				
				
			}
		});
		
		
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, panel2);
		
		frame.setVisible(true);
	}
}
