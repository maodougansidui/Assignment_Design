package xmlvalidator;


public interface StringStack {

	/**
	 * Pushes the given item onto the top of the stack.
	 * 
	 * @param item
	 */
	public void push(String item);


	/**
	 * Removes the top item from the stack.
	 * 
	 * @return The removed item.
	 */
	public String pop();


	/**
	 * Returns, but does not remove, the item at the given position. 0 is the top, 1 is the second item, and so on.
	 * 
	 * @param position
	 * 
	 * @return The item at the given position.
	 */
	public String peek(int position);


	/**
	 * @return The number of items on the stack
	 */
	public int getCount();

}
