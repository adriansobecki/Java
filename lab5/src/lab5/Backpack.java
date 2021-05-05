package lab5;

import java.util.ArrayList;
import java.util.List;

public class Backpack
{
    private List<Item> items = new ArrayList<Item>();
    private int size;
    private int remainingSize;
    private int totalValue = 0;

    public Backpack(int newSize) {
        size = newSize;
        remainingSize = newSize;
    }
    public boolean addNewItem(Item newItem) {
        if (newItem.get_weight() > remainingSize)
            return false;
        remainingSize -= newItem.get_weight();
        items.add(newItem);
        totalValue += newItem.get_value();

        return true;
    }

    public int getSize()
    {
        return size;
    }
    public int getRemainingSize()
    {
        return remainingSize;
    }

    public List<Item> GetItems()
    {
        return items;
    }

    public int getValue()
    {
        return totalValue;
    }


}