package sortcomparison;

import java.util.*;
import static sbcc.Core.*;
import static java.lang.System.*;
import static org.apache.commons.lang3.StringUtils.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub
		for (int i = firstIndex + 1; i < firstIndex + numberToSort; i++) {
			String element = data.get(i);
			int j = i - 1;
			while (j >= firstIndex && data.get(j).compareTo(element) > 0) {
				data.set(j + 1, data.get(j));
				j--;
			}
			data.set(j + 1, element);
		}

	}


	@Override
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub
		if (numberToSort > 1) {
			int pivotNdx = partition(data, firstIndex, numberToSort);
			int leftSize = pivotNdx - firstIndex;
			int rightSize = firstIndex + numberToSort - 1 - pivotNdx;
			quickSort(data, firstIndex, leftSize);
			quickSort(data, pivotNdx + 1, rightSize);
		}

	}


	@Override
	public int partition(ArrayList<String> data, int firstIndex, int numberToPartition) {
		// TODO Auto-generated method stub
		String pivot = medianOfThree(data, firstIndex, numberToPartition);
		int tooBigNdx = firstIndex + 1;
		int tooSmallNdx = firstIndex + numberToPartition - 1;
		while (tooBigNdx < tooSmallNdx) {
			while (tooBigNdx < tooSmallNdx && data.get(tooBigNdx).compareTo(pivot) <= 0) {
				tooBigNdx++;
			}
			while (tooSmallNdx > firstIndex && data.get(tooSmallNdx).compareTo(pivot) > 0) {
				tooSmallNdx--;
			}
			if (tooBigNdx < tooSmallNdx) {
				Collections.swap(data, tooBigNdx, tooSmallNdx);
			}
		}

		if (pivot.compareTo(data.get(tooSmallNdx)) >= 0) {
			Collections.swap(data, firstIndex, tooSmallNdx);
			return tooSmallNdx;
		} else {
			return firstIndex;
		}
	}


	@Override
	public void mergeSort(ArrayList<String> data, int firstIndex, int numberToSort) {
		// TODO Auto-generated method stub
		if (numberToSort <= 15) {
			insertionSort(data, firstIndex, numberToSort);
		} else {
			int firstPart = numberToSort / 2;
			int secondPart = numberToSort - firstPart;
			int midPoint = (firstIndex + firstIndex + numberToSort) / 2;
			mergeSort(data, firstIndex, firstPart);
			mergeSort(data, midPoint, secondPart);
			merge(data, firstIndex, firstPart, secondPart);
		}

	}


	@Override
	public void merge(ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {
		// TODO Auto-generated method stub
		int firstPartNdx = firstIndex;
		int secondPartNdx = firstIndex + leftSegmentSize;

		ArrayList<String> temp = new ArrayList<String>();

		while (firstPartNdx < firstIndex + leftSegmentSize
				&& secondPartNdx < firstIndex + leftSegmentSize + rightSegmentSize) {
			if (data.get(firstPartNdx).compareTo(data.get(secondPartNdx)) <= 0) {
				temp.add(data.get(firstPartNdx));
				firstPartNdx++;
			} else {
				temp.add(data.get(secondPartNdx));
				secondPartNdx++;
			}
		}
		while (firstPartNdx < firstIndex + leftSegmentSize) {
			temp.add(data.get(firstPartNdx));
			firstPartNdx++;
		}

		while (secondPartNdx < firstIndex + leftSegmentSize + rightSegmentSize) {
			temp.add(data.get(secondPartNdx));
			secondPartNdx++;
		}

		// reset int i and firstPartNdx;
		int i = 0;
		firstPartNdx = firstIndex;
		while (i < leftSegmentSize + rightSegmentSize) {
			data.set(firstPartNdx, temp.get(i));
			i++;
			firstPartNdx++;
		}

	}


	@Override
	public void heapSort(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}


	@Override
	public void heapify(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}


	public String medianOfThree(ArrayList<String> data, int firstIndex, int numberToPartition) {
		int end = firstIndex + numberToPartition - 1;
		int center = (firstIndex + end) / 2;

		if (data.get(firstIndex).compareTo(data.get(center)) > 0) {
			Collections.swap(data, firstIndex, center);
		}
		if (data.get(firstIndex).compareTo(data.get(end)) > 0) {
			Collections.swap(data, firstIndex, end);
		}
		if (data.get(center).compareTo(data.get(end)) > 0) {
			Collections.swap(data, center, end);
		}

		Collections.swap(data, firstIndex, center);

		return data.get(firstIndex);

	}

}
