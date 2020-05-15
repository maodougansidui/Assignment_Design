/*
 ============================================================================
 Name        : JMaoHtmlSrcList.c
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
 * take the source string as the parameter and compute the number of src attributes.
 */

int countSrc(char *source);

/*
 * take the source string as the parameter and demonstrate the tag names.
 */

void demoTags(char *source);

/*
 * take the source string as the parameter and demonstrate the urls.
 */

void demoUrls(char *sourcecode);

/*
 * take the source string as the parameter and demonstrate the a list of tag names and tag counts.
 */

void demoFrequences(char *source);

int main(void) {
	setvbuf(stdout, NULL, _IONBF, 0);

	// initialize basic variables.
	char buffer[BUFSIZ + 1];
	char file_name[BUFSIZ + 1];
	char command[] = "curl -s ";
	char whole_file[256 * 256];
	FILE* in_file;

	// Print to the standard output.
	printf("URL:\n");
	fgets(file_name, BUFSIZ, stdin);

	file_name[strlen(file_name) - 1] = '\0';

	// combine two strings together.
	strcat(command, file_name);

	in_file = popen(command, "r"); // read the file.

	while (!feof(in_file)) { // loop through the whole file and copy it into string.
		fgets(buffer, BUFSIZ, in_file);
		strcat(whole_file, buffer);
	}

	while (1) {
		char input[BUFSIZ + 1];
		printf("Ready\n");
		fgets(input, BUFSIZ, stdin);
		if (*input == 'c') {
			int count = countSrc(whole_file);
			printf("%d\n", count);
		}
		else if (*input == 't') {
			demoTags(whole_file);
		}
		else if (*input == 'u') {
			demoUrls(whole_file);
		}
		else if (*input == 'f') {
			demoFrequences(whole_file);
		}
		else if (*input == 'q') {
			break;
		}else{
			printf("Please input the correct command.\n");
		}
	}

	printf("Complete\n");

	pclose(in_file);
	return 0;
}

int countSrc(char *source) {
	char *copy = source;
	int counter = 0;
	char *p = "src=\"";
	while (strstr(copy, p) != NULL) { // check if the next "src" exists.
		copy = strstr(copy, p);
		if (*(copy - 1) != '.') { // check if the space before "src" is not a dot.
			counter++;
		}
		copy++;
	}
	return counter;
}

void demoTags(char *source) {
	char *copy1 = source;
	char *p = "src=\"";
	while (copy1 != NULL && *copy1 != '\0') {
		if (strstr(copy1, p) != NULL) {
			copy1 = strstr(copy1, p);
			if (*(copy1 - 1) != '.') {
				char *pointer = copy1;
				while (*(pointer - 1) != '<') { // looking for '<'.
					pointer--;
				}
				while (*pointer != ' ') { // check if there is a space after the tag.
					printf("%c", *pointer);
					pointer++;
				}
				printf("\n");
			}
		}

		// move to the next "src".
		copy1++;
	}

}

void demoUrls(char *sourcecode) {
	char temp[BUFSIZ + 1];
	char *p = "src=\"";
	char *copy1 = sourcecode;
	while ((copy1 = strstr(copy1, p)) != NULL) { // move the pointer to the next "src".
		copy1--; // move back one pointer.
		if (copy1[0] != '.') { // check if it is a dot.
			copy1++;
			copy1 += strlen(p);
			int pQ1 = 0, pQ2 = pQ1 + 1, done = 0;

			while (!done) { // get the length of result.
				if (copy1[pQ2] == '\"') {
					done = 1;
				} else {
					pQ2++;
				}
			}
			strncpy(temp, copy1, pQ2); // copy the source to temporary string.
			printf("%s\n", temp);
			memset(temp, 0, sizeof(temp));
		} else {
			copy1++;
			copy1 += strlen(p);
		}
	}
}

void demoFrequences(char *source) {
	char *copy=source;
	char tags[1000][100]; // initializes the tag arrays.
	int count[100]; // initializes the count arrays.
	int index=0, arrdex=0,i=0,j=0;

	char printTags[50][100];

	while (strstr(copy,"src=\"")!=NULL){ // basically, this function will store all the tags in tag arrays.
		copy=strstr(copy,"src=\"");
		if (*(copy-1)!='.'){
			while (*(copy-1)!='<'){
				copy--;
			}
			char * last=strchr(copy,' ');
			strncpy(tags[index],copy,last-copy);
			index++;
			copy=strstr(copy,"src=\"");
		}
		copy++;
	}

	for (i=0;i<index;i++){ // loop through all the tags and get the counts and name.
		if(arrdex==0){
			strcpy(printTags[arrdex],tags[i]);
			count[arrdex]=1;
			arrdex++;
		}else{
			for (j=0;j<arrdex;j++){
				if (strcmp(printTags[j],tags[i]) == 0){ // if the same, count adds 1.
					count[j]+=1;
					break;
				}else if (j==arrdex-1){ // if not the same, create a new name.
					strcpy(printTags[arrdex],tags[i]);
					count[arrdex]=1;
					arrdex++;
					break;
				}
			}
		}
	}

	for (i=0;i<arrdex;i++){
		printf("%s\t%d\n",printTags[i],count[i]);
	}
}

