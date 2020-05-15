;******************************************************************************
; CS 107: Computer Architecture and Organization
;
; Project: E06 - Loops
; Filename: main.s
; Author: Jianlyu Mao
; Semester: Spring 2019 
; Description: Homework assignment worth 16 points. Be sure to fill out the 
; author and semester information.
;
;******************************************************************************		
; EQUs go here


; This is where you declare space using the SPACE assembler directive. Be sure
; you understand the AREA and SPACE directives.
				AREA	SBCCDATA, DATA

smallest	SPACE	4	
absval		SPACE	4
sumevens	SPACE	10
sumodds		SPACE	10
; This is where the code goes. Note that the AREA directive stays in effect
; until another AREA is declared.
				AREA    SBCCCODE, CODE
					
start			PROC
				EXPORT  start					[WEAK]
;******************************************************************************		
; Homework Question 1 (HW1)
; Write a for loop to execute the ARM no operation idiom MOV r0, r0 5 times. Be
; sure to load the 5 from memory don't make it an immediate value in an
; instruction.
;******************************************************************************		
				BL		clear_reg	; Zeros out registers r0 through r8.
				; Your code to solve HW1 starts here.
				LDR r1,=hw1		; r1= address of 5
				LDRB r2,[r1]	; r2=5

LOOP
				MOV r0, r0		; MOV r0,r0 5 times
				SUBS r2,r2,#1	; r2=r2-1
				BNE LOOP		; if r2=0, finish

done 	
				
				
				
				; Your code to solve HW1 ends here.
				
;******************************************************************************		
; Homework Question 2 (HW2)
; Write a short program to find the smallest number in a group of 4 numbers.
; The numbers must come from data (not immediate values loaded into registers)
; and the smallest must be loaded into a memory location called "smallest".
;******************************************************************************		
				BL		clear_reg	; Zeros out registers r0 through r8.
				; Your code to solve HW2 starts here.
				; temp=a[0]
				; for (i=0;i<4;i++){
				;		if (temp>a[i])
				;			temp=a[i]
				;
				LDR	r0,=hw2				; load the address of array.
				MOV r1, #0				; start at the index i=0
				LDRB r2, [r0,r1]			; get the temporary variable at a[0]
				LDR r4,=smallest		; pointer to the SMALLEST memory location.
loop			
				CMP r1,#4				; if r1 is larger than 4,end loop
				BGE DONE
				LDRB r3, [r0,r1]		; loop to a[i]
				CMP	r2,r3				; compare these two, if r2>r3, then branch
				BGT larger
				ADD r1,r1,#1
				B	loop
larger			MOVS r2,r3
				STRB r2,[r4]			;load the r2 value into r4, which is memory location called "smallest"
				ADD r1,r1,#1
				B	loop
DONE
				
				
				
				; Your code to solve HW2 ends here.


;******************************************************************************		
; Homework Question 3 (HW3)
; Write a short program to compute the absolute value of a signed 32 bit
; integer. The data must be loaded from memory. Place the computed value into
; a memory location called "absval". You must use some conditional execution
; as part of the solution.
;******************************************************************************		
				BL		clear_reg	; Zeros out registers r0 through r8.
				; Your code to solve HW3 starts here.
				LDR r0,=hw3				; load the address of -10
				MOV r1,#0				; 0 to be compared if it is negative 
				LDR r2,=absval
				LDRSB r0,[r0]			; load the signed byte of the -10
				CMP r0,r1
				BLT abs					; if it is negative, branch to abs
				STRB r0,[r2]

abs				EOR r3,r0,#0xFFFFFFFF	; basically this alogorithm convert negative to positive
				ADDS r3,r3,#1
				STRB r3,[r2]
				
FINISH
				
				
				
				
				
				; Your code to solve HW3 ends here.


;******************************************************************************		
; Homework Question 4 (HW4)
; For list of random bytes sum up all the even numbers and place them into a
; memory location called "sumevens" and sum up all the odd values and place
; the sum into a location called "sumodds". Use conditional execution as part
; of the solution. Process all of the values in the list until you reach zero.
;******************************************************************************		
				BL		clear_reg	; Zeros out registers r0 through r8.
				; Your code to solve HW4 starts here.
				LDR r0,=hw4				; load the address of the hw4
				MOV r3,#0				; r3 as index 0
				MOV r6,#0				; r6 as the sum of odd numbers
				MOV r7,#0				; r7 as the sum of even numbers
				LDR r1,=sumevens		; it loads the memory location
				LDR r2,=sumodds			; it loads the memory location
				
oddeven	     	LDRB r4, [r0,r3]		; load the a[i] value into r4
				CMP r4,#0				; compare if r4=0
				BEQ sumsup				; if it is 0, branch to sumsup
				ANDS r5,r4,#1			; do the calculation, if r5 is 0, then branch
				BEQ even				; to even, if r5 is 1, continue as r4 is odd number
				ADD r6,r6,r4			; sums odd numbers
				ADD r3,r3,#1			; index++
				B	oddeven
				
even			ADD r7,r7,r4			; sums even numbers
				ADD r3,r3,#1			; index++
				B	oddeven	
				
sumsup			STR r6,[r2]				; store sum of odd numbers into memory location called "sumodds"
				STR r7,[r1]				; store sum of odd numbers into memory location called "sumevens"
				B	finish				; then branch to finish

finish
				; Your code to solve HW4 ends here.


; DO NOT MODIFY CODE - START
;******************************************************************************		
; Loop forever. No student code should follow this. You can, however declare
; some memory (using DCB) sbelow.
;******************************************************************************		
				B       .
				ENDP

;******************************************************************************		
; Clears registers so that each part starts with the registers at zero. Doesn't
; clear flags so be aware of their initial state. DO NOT MODIFY THIS CODE
;******************************************************************************		
clear_reg
				MOVS	r0, #0
				MOVS	r1, #0
				MOVS	r2, #0
				MOVS	r3, #0
				MOVS	r4, #0
				MOVS	r5, #0
				MOVS	r6, #0
				MOVS	r7, #0
				MOVS	r8, #0
				BX		lr
; DO NOT MODIFY CODE - END

;******************************************************************************		
; Declare data here. An example is shown below. Be sure the change label1 to
; something sensible.
;******************************************************************************		
hw1			DCB		5
hw2			DCB 	24,100,5,122
hw3   		DCB		-10				
hw4			DCB		20,30,40,11,23,45,0					
				END
