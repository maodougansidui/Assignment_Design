package sortcomparison;

import java.util.*;

public interface Sorter {

	/**
	 * Sorts part or all of a list in ascending order.
	 * 
	 * @param data
	 *            The list of elements to sort
	 * @param firstIndex
	 *            Index of the first element to be sorted.
	 * @param numberToSort
	 *            The number of elements in the section to be sorted.
	 */
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort);


	/**
	 * Sorts part or all of the list, data, in ascending order. quickSort() must: - Call the
	 * insertionSort() function in this interface for segments of size 15 or less. - Use the
	 * median-of-three method to prevent O(n^2) running time for sorted data sets - Call the
	 * partition() function in this interface to do its partitioning.
	 * 
	 * @param data
	 *            The list of elements to sort
	 * @param firstIndex
	 *            Index of the first element to be sorted.
	 * @param numberToSort
	 *            The number of elements in the section to be sorted.
	 */
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort);


	/**
	 * Partitions part (or all) of the list. The range of indices included in the partitioning is
	 * [firstIndex, firstIndex + numberToPartition -1].
	 * 
	 * @param data
	 * @param firstIndex
	 * @param numberToPartition
	 * @return The index, relative to data[0], where the pivot value is located at the end of this
	 *         partitioning.
	 */
	public int partition(ArrayList<String> data, int firstIndex, int numberToPartition);


	/**
	 * Sorts part or all of a list in ascending order.
	 * 
	 * mergeSort() must: - Call the insertionSort() function in this interface for segments of size
	 * 15 or less. - Call the merge() function in this interface to do its merging.
	 * 
	 * @param data
	 *            list of elements to sort
	 * @param firstIndex
	 *            Index of the first element to be sorted.
	 * @param numberToSort
	 *            The number of elements in the section to be sorted.
	 */
	public void mergeSort(ArrayList<String> data, int firstIndex, int numberToSort);


	/**
	 * Merges two sorted segments into a single sorted segment. The left and right segments are
	 * contiguous.
	 * 
	 * @param data
	 *            The list of elements to merge
	 * @param firstIndex
	 *            Index of the first element of the left segment.
	 * @param leftSegmentSize
	 *            The number of elements in the left segment.
	 * @param rightSegmentSize
	 *            The number of elements in the right segment.
	 */
	public void merge(ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize);


	/**
	 * EXTRA CREDIT
	 * 
	 * Sorts the list in ascending order.
	 * 
	 * @param data
	 *            The list of elements to sort
	 */
	public void heapSort(ArrayList<String> data);


	/**
	 * EXTRA CREDIT
	 * 
	 * Heapifies the given list.
	 * 
	 * @param data
	 *            The list to heapify.
	 */
	public void heapify(ArrayList<String> data);

}
