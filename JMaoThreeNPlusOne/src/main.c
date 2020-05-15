/*
 ============================================================================
 Name        : JMaoThreeNPlusOne.c
 Author      : Jianlyu Mao
 Version     :
 Copyright   : August 2018
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function Declaration

/*
 * take the smallest number of the range and the biggest number of the range as the parameters, use The Collatz Conjecture algorithm
 * to compute each number in range to get the sequences. Find the maximum sequence and return.
 */

int computeMaxSequenceLength(int first, int last);

/*
 * take a number and compute the sequence length using Collatz Conjecture, return length.
 */

int computeSequenceLength(int number);

/*
 * recursively using Collatz Conjecture until number is 1.
 */

int computeNextValue(int number);

int main(void) {
	setvbuf(stdout, NULL, _IONBF, 0);

	char file_name[BUFSIZ + 1];
	char buffer[BUFSIZ + 1];
	FILE*out_file;
	printf("Enter the output filename: \n");
	fgets(file_name, BUFSIZ, stdin);

	int name_length = strlen(file_name);
	file_name[name_length - 1] = '\0';

	out_file = fopen(file_name, "w");

	while (1) {
		int first = atoi(fgets(buffer, BUFSIZ, stdin));

		if (first == 0) {
			break;
		}

		int last = atoi(fgets(buffer, BUFSIZ, stdin));

		int max = computeMaxSequenceLength(first, last);

		fprintf(out_file, "%d\t%d\t%d\n", first, last, max);
	}

	printf("Complete\n");
	fclose(out_file);
	return EXIT_SUCCESS;

}

int computeNextValue(int number) {
	if (number % 2 == 0) {
		number = number / 2;
	} else if (number % 2 == 1) {
		number = 3 * number + 1;
	}

	return number;
}

int computeSequenceLength(int number) {
	int counter = 0;
	int done = 0;

	// special case if the first number is 1.
	if (number == 1) {
		return ++counter;
	}

	while (!done) {
		number = computeNextValue(number);
		counter++;
		if (number == 1) {
			done = 1;
			counter++;
		}
	}

	return counter;
}

int computeMaxSequenceLength(int first, int last) {
	int max_length = 0;
	int present = 0;
	int i = 0;
	for (i = first; i <= last; i++) {
		present = computeSequenceLength(i);

		if (present > max_length) {
			max_length = present;
		}
	}

	return max_length;
}
