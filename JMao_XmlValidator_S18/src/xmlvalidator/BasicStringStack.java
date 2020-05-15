package xmlvalidator;

import java.util.*;

public class BasicStringStack implements StringStack {

	private int count;
	private ArrayList<String> items;


	public BasicStringStack() {
		count = 0;
		items = new ArrayList<String>();
	}


	@Override
	public void push(String item) {
		// TODO Auto-generated method stub
		items.add(item);
		count++;
	}


	@Override
	public String pop() {
		// TODO Auto-generated method stub
		if (count == 0) {
			return null;
		} else {
			String topElement = items.get(count - 1);
			items.remove(count - 1);
			count--;
			return topElement;
		}
	}


	@Override
	public String peek(int position) {
		// TODO Auto-generated method stub
		if ((position > count - 1) || (position < 0)) {
			return null;
		} else {
			String indexElement = items.get(count - position - 1);
			return indexElement;
		}

	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
