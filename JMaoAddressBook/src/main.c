/*
 ============================================================================
 Name        : JMaoAddressBook.c
 Author      : Jianlyu Mao
 Version     :
 Copyright   : August 2018
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "address_book.h"

int main(void) {
	setvbuf(stdout, NULL, _IONBF, 0);


	NodeP startPtr = NULL; 						// initializes all needed variables.
	char input;
	char buffer[BUFSIZ + 1]; 					// This is the general string input.
	char newValueInputed[BUFSIZ+1]; 			// This is the new value to be inputed when we want to edit the field.
	int index1;

	printf("Ready\n");
	scanf("%c", &input);
	while (input != 'q') {
		switch (input) {
		case 'a': 											// add the contact.
			scanf("%d\n", &index1);
			fgets(buffer, BUFSIZ, stdin);
			buffer[strlen(buffer) - 1] = '\0';
			addContact(index1, buffer, &startPtr);
			break;
		case 'd':										// delete the contact.
			scanf("%d", &index1);
			deleteContact(index1, &startPtr);
			break;
		case 'g':											// get the contact.
			scanf("%d", &index1);
			getContact(index1, startPtr);
			break;
		case 'f':										// get the field of contact.
			scanf("%d\n",&index1);
			fgets(buffer,BUFSIZ,stdin);
			buffer[strlen(buffer) - 1] = '\0';
			getField(index1,buffer,startPtr);
			break;
		case 'n':										// get the number of contact list.
			index1=getNumber(startPtr);
			printf("%d\n",index1);
			break;
		case 'l':										// load the whole csv file into list nodes.
			scanf("%s",buffer);
			loadFile(buffer,&startPtr);
			break;
		case 's':										// save the address book into a csv file.
			scanf("%s",buffer);
			saveContact(buffer,startPtr);
			break;
		case 'q':										// quit the program.
			break;
		case 'e':										// edit the contact field information.
			scanf("%d",&index1);
			scanf("%s",buffer);
			scanf("%s",newValueInputed);
			editContact(index1,buffer,newValueInputed,&startPtr);
			break;
		case 'o':
			sortContact(startPtr);
			break;
		}
		scanf("%c", &input);

	}

	printf("Complete\n");

	return EXIT_SUCCESS;
}
