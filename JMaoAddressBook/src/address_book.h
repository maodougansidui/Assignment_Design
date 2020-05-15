/*
 * address_book.h
 *
 *  Created on: Oct 29, 2018
 *      Author: jmao
 */

#ifndef ADDRESS_BOOK_H_
#define ADDRESS_BOOK_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct addressBook {
	char first_name[30];
	char last_name[30];
	char email_address[100];
	char phone_number[50];
	struct addressBook *nextP;
};

typedef struct addressBook Node;
typedef Node *NodeP;

/*
 *	This function takes three parameters, index to add the contact to the specific location of address book, info is the information required to input,
 *	 head is the head of the linked list. The function is to add one contact info in the address book.
 */

void addContact(int indexList, char info[], NodeP *head);

/*
 *This function is to delete the node at asked location.
 */

void deleteContact(int indexList, NodeP *head);

/*
 *	print out the contact at the required location.
 */

void getContact(int indexList, NodeP head);

/*
 *	This function is to get the index of the contact, and print out the required field. Valid input should be
 *	"firstName","lastName","email","phoneNumber".
 */

void getField(int indexList, char field[], NodeP head);

/*
 * This function gets the number of the linked list.
 */

int getNumber(NodeP head);

/*
 * This function is to load the csv file and save all the contact information into the linked list. Note if the address book
 * is not empty, append the loaded file at the end of the list.
 */

void loadFile(char filename[], NodeP *head);

/*
 *This function is to save the all contact information into a csv file.
 */

void saveContact(char filename[], NodeP head);

/*
 * This function is to edit the contact information with specific location, field and new value to input.
 */

void editContact(int index1, char field[], char newValue[], NodeP *head);

/*
 * This function is to use the bubble sort and sort the linked list in ascending order, based on the last name first.
 */

void sortContact(NodeP head);

/*
 * This function will swap the two nodes value based on Last Name.
 */

void swap(NodeP a, NodeP b);

/*
 * This function is to test two nodes information comparison, based on Last name, first name, email, phoneNumber.
 */

int compareTwoNodes(NodeP a, NodeP b);

#endif /* ADDRESS_BOOK_H_ */
