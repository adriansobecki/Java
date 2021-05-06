package lab5;

import java.util.ArrayList;
import java.util.List;


public class Program {

	public static void main(String[] args) {
        int n = 10;
        int seed = 1;
        int size = 50;
        RandomNumberGenerator rng = new RandomNumberGenerator(seed);
        List<Item> items = new ArrayList<Item>();

        Backpack bag = new Backpack(size);

        for (int i = 0; i < n; i++)
        {
        	Item it=new Item();
        	it.set_value(rng.nextInt(1, 29));
        	items.add(it);
        }
        for (int i = 0; i < n; i++)
        {
        	var it=items.get(i);
        	it.set_weight(rng.nextInt(1, 29));
            bag.addNewItem(it);
        }

        System.out.println(bag.getRemainingSize());
        System.out.println(bag.getValue());
        
        Gui.gui();

	}

}
