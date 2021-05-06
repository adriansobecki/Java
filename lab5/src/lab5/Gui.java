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
		// okreœlanie widoku
		// górny panel
		JFrame frame = new JFrame("nazwa");
		frame.setSize(800,300);
		
		
		JTextArea seed = new JTextArea(1, 10);
		seed.setEditable(true);
		JLabel seedLabel = new JLabel("Seed:");
		
		JTextArea size = new JTextArea(1, 10);
		size.setEditable(true);
		JLabel sizeLabel = new JLabel("Size:");
		
		JTextArea capacity = new JTextArea(1, 10);
		seed.setEditable(true);
		JLabel capacityLabel = new JLabel("Capacity:");
		
		JButton submit = new JButton("submit");
		
		
		JPanel panelTop = new JPanel();
		panelTop.add(seedLabel);
		panelTop.add(seed);
		panelTop.add(sizeLabel);
		panelTop.add(size);
		panelTop.add(capacityLabel);
		panelTop.add(capacity);
		panelTop.add(submit);
		
		// panel œrodkowy
		JTextArea all_items = new JTextArea(1, 50);
		all_items.setEditable(false);
		
		JTextArea backpack_items = new JTextArea(1, 50);
		backpack_items.setEditable(false);
		
		JPanel panelCenter = new JPanel();
		panelCenter.add(all_items);
		panelCenter.add(backpack_items);
		
		// dolny panel
		JPanel panelBottom = new JPanel();
		
		JTextArea value = new JTextArea(1, 10);
		value.setEditable(false);
		JLabel valueLabel = new JLabel("Value:");
		
		JTextArea remaining = new JTextArea(1, 10);
		remaining.setEditable(false);
		JLabel remainingLabel = new JLabel("Remaining Size:");
		
		panelBottom.add(valueLabel);
		panelBottom.add(value);
		panelBottom.add(remainingLabel);
		panelBottom.add(remaining);		
		
		frame.getContentPane().add(BorderLayout.PAGE_START, panelTop);
		frame.getContentPane().add(BorderLayout.CENTER, panelCenter);
		frame.getContentPane().add(BorderLayout.PAGE_END, panelBottom);
		frame.setVisible(true);
		// koniec okreœlania widoku
		
		// akcja
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// czyszczenie inputów
				all_items.setText("");
				backpack_items.setText("");
				
				//definiowanie zmiennych
				int argSeed,argSize,argCapacity;
		        List<Item> items = new ArrayList<Item>();
				argSeed=Integer.parseInt(seed.getText());
				argSize=Integer.parseInt(size.getText());
				argCapacity=Integer.parseInt(capacity.getText());
				
				// definicja obiektów
		        RandomNumberGenerator rng = new RandomNumberGenerator(argSeed);
		        Backpack bag = new Backpack(argCapacity);

		        for (int i = 0; i < argSize; i++)
		        {
		        	Item it=new Item();
		        	it.set_value(rng.nextInt(1, 29));
		        	it.set_weight(rng.nextInt(1, 29));
		        	items.add(it);
		        	
		        	all_items.append(String.valueOf(it.get_weight()) + ", ");
		        	
		            if(bag.addNewItem(it)) {
		            	backpack_items.append(String.valueOf(it.get_weight()) + ", ");
		            }
		        }
		        value.setText(String.valueOf(bag.getValue()));
		        remaining.setText(String.valueOf(bag.getRemainingSize()));
			}
		});
	}
}
