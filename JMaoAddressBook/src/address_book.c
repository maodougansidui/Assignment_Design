/*
 * address_book.c
 *
 *  Created on: Oct 29, 2018
 *      Author: jmao
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "address_book.h"

/*
 *	This function takes three parameters, index to add the contact to the specific location of address book, info is the information required to input,
 *	 head is the head of the linked list. The function is to add one contact info in the address book.
 */

void addContact(int indexList, char info[], NodeP *head) {
	NodeP newContact = malloc(sizeof(Node));

	// next segment of code is to split info into 4 pieces and assign them to newContact members respectively.

	char strArr[5][40];
	int i = 0;
	char *ptr = strtok(info, ",");

	while (ptr != NULL) {
		strcpy(strArr[i], ptr);
		ptr = strtok(NULL, ",");
		++i;
	}
	strcpy(newContact->last_name, strArr[0]); // assign strings to the each of them.
	strcpy(newContact->first_name, strArr[1]);
	strcpy(newContact->email_address, strArr[2]);
	strcpy(newContact->phone_number, strArr[3]);

	// insert the node at certain location.
	newContact->nextP = NULL;
	NodeP currentNode = *head;
	NodeP prevNode = NULL;

	int j = 0;
	for (j = 0; j < indexList; j++) { // as the assignment specifies, we do not need to worry that index is larger than length of list.
		prevNode = currentNode;
		currentNode = currentNode->nextP;
	}

	if (prevNode == NULL) { // check if the list is empty and insert the new node at the beginning of the list.
		newContact->nextP = *head;
		*head = newContact;
	} else {
		prevNode->nextP = newContact;
		newContact->nextP = currentNode;
	}

}

/*
 *This function is to delete the node at asked location.
 */

void deleteContact(int indexList, NodeP *head) {
	int numOfList = getNumber((*head));
	if (indexList > numOfList - 1) {
	} else {
		NodeP temp = *head;
		NodeP prev = NULL;
		int i = 0;
		if (indexList == 0) { // if the header is to be deleted.
			*head = temp->nextP;
			free(temp); // free memory.
			return;
		}
		for (i = 0; i < indexList; i++) { // loop until get the correct index.
			prev = temp;
			temp = temp->nextP;
		}
		prev->nextP = temp->nextP;

	}

}

/*
 *	print out the contact at the required location.
 */

void getContact(int indexList, NodeP head) {

	NodeP current = head; // initializes the current node.
	int i = 0;
	for (i = 0; i < indexList; i++) {
		current = current->nextP;
	}
	printf("%s,%s,%s,%s\n", current->last_name, current->first_name,
			current->email_address, current->phone_number);

}

/*
 *	This function is to get the index of the contact, and print out the required field. Valid input should be
 *	"firstName","lastName","email","phoneNumber".
 */

void getField(int indexList, char field[], NodeP head) {
	NodeP current = head; // initializes the current node.
	int i = 0;
	for (i = 0; i < indexList; i++) {
		current = current->nextP;
	}
	if (strcmp(field, "firstName") == 0) {
		printf("%s\n", current->first_name);
	} else if (strcmp(field, "lastName") == 0) {
		printf("%s\n", current->last_name);
	} else if (strcmp(field, "email") == 0) {
		printf("%s\n", current->email_address);
	} else if (strcmp(field, "phoneNumber") == 0) {
		printf("%s\n", current->phone_number);
	} else {
		printf("Invalid Input\n");
	}

}

/*
 * This function gets the number of the linked list.
 */

int getNumber(NodeP head) {
	int count = 0;
	NodeP current = head; // initializes the pointer.
	while (current != NULL) {
		++count;
		current = current->nextP;
	}
	return count;
}

/*
 * This function is to load the csv file and save all the contact information into the linked list. Note if the address book
 * is not empty, append the loaded file at the end of the list.
 */

void loadFile(char filename[], NodeP *head) {
	FILE *inCsv = fopen(filename, "r"); 	// open the csv file.
	int index1 = 0;
	int length = getNumber((*head));
	char contact[BUFSIZ + 1];
	if (inCsv != NULL) { 					// if the file is not empty.
		fgets(contact, BUFSIZ, inCsv); // get the first line of the csv file, which is not used.
		if (head != NULL) {		// This is to check if the list is empty or not.
			while (fgets(contact, BUFSIZ, inCsv)) {
				contact[strlen(contact) - 1] = '\0';
				addContact(length, contact, head);
				length++;
			}
		} else {							// This means list is empty.
			while (fgets(contact, BUFSIZ, inCsv)) {
				contact[strlen(contact) - 1] = '\0';
				addContact(index1, contact, head); // Here we need to use the address of the head because we have to change the list.(Double pointer).
				index1++;
			}
		}
	}
	fclose(inCsv);

}

/*
 *This function is to save the all contact information into a csv file.
 */
void saveContact(char filename[], NodeP head) {
	FILE *outCsv = fopen(filename, "w");
	fprintf(outCsv, "%s,%s,%s,%s\n", "lastName", "firstName", "email",
			"phoneNumber");
	NodeP current = head; // initializes the pointer.
	while (current != NULL) {
		// this code is to save each node's contact into csv file.
		fprintf(outCsv, "%s,%s,%s,%s\n", current->last_name,
				current->first_name, current->email_address,
				current->phone_number);
		current = current->nextP;
	}

	fclose(outCsv);
}

/*
 * This function is to edit the contact information with specific location, field and new value to input.
 */

void editContact(int index1, char field[], char newValue[], NodeP *head) {
	int i = 0;
	NodeP current = *head;
	for (i = 0; i < index1; i++) {
		current = current->nextP;
	}
	if (strcmp(field, "firstName") == 0) {
		strcpy(current->first_name, newValue);
	} else if (strcmp(field, "lastName") == 0) {
		strcpy(current->last_name, newValue);
	} else if (strcmp(field, "email") == 0) {
		strcpy(current->email_address, newValue);
	} else if (strcmp(field, "phoneNumber") == 0) {
		strcpy(current->phone_number, newValue);
	} else {
		printf("Invalid input.\n");
	}

}
/*
 * This function is to use the bubble sort and sort the linked list in ascending order, based on the last name, then first name, then email, then phoneNumber.
 */

void sortContact(NodeP head) {
	NodeP current = head; // because we don't have to change the linked list, all we have to do is swap value in the node.
	NodeP lPtr = NULL; // initializes the last node as NULL.
	int done;

	if (head == NULL) {
		return;
	}

	do {
		done = 1;
		current = head;

		while (current->nextP != lPtr) {
			if (compareTwoNodes(current, current->nextP) > 0) {
				swap(current, current->nextP);
				done = 0;
			}
			current = current->nextP;
		}
		lPtr = current;

	} while (!done);

}

/*
 * This function will swap the two nodes value based on Last Name.
 */

void swap(NodeP a, NodeP b) {
	char temp[100];
	strcpy(temp, a->last_name);
	strcpy(a->last_name, b->last_name);
	strcpy(b->last_name, temp);

	strcpy(temp, a->first_name);
	strcpy(a->first_name, b->first_name);
	strcpy(b->first_name, temp);

	strcpy(temp, a->email_address);
	strcpy(a->email_address, b->email_address);
	strcpy(b->email_address, temp);

	strcpy(temp, a->phone_number);
	strcpy(a->phone_number, b->phone_number);
	strcpy(b->phone_number, temp);

}

/*
 * This function is to test two nodes information comparison, based on Last name, first name, email, phoneNumber.
 */

int compareTwoNodes(NodeP a, NodeP b) {
	int result = strcmp(a->last_name, b->last_name);
	if (result != 0) {
		return result;
	}

	result = strcmp(a->first_name, b->first_name);
	if (result != 0) {
		return result;
	}

	result = strcmp(a->email_address, b->email_address);
	if (result != 0) {
		return result;
	}

	return strcmp(a->phone_number, b->phone_number);

}
