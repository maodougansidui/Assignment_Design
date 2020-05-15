;******************************************************************************
; CS 107: Computer Architecture and Organization
;
; Project: L03_LCD
; Filename: delay.s
; Author: Jianlyu Mao
; Semester: Spring 2019
; Description: Various delay routines.
;
;******************************************************************************		
	
				AREA    DELAYCODE, CODE

;******************************************************************************		
; Delays for one millisecond.
;
; void DELAY_1_MS()
;
; This is a simple loop which will delay for approximately one millisecond. A
; loop count of 3000 will work.
;******************************************************************************		
DELAY_1_MS		PROC
				EXPORT  DELAY_1_MS					[WEAK]
				
				; Your code goes here.
				PUSH	{r11,lr}
				MOV r11,#3000

loop			SUBS r11,r11,#1
				BNE	 loop

done_1_ms		POP		{r11,pc}
				
				ENDP

;******************************************************************************		
; Delays for about two milliseconds.
;
; void DELAY_2_MS()
;
; This 2 ms delay is longer than the longest command will take to the LCD
; Keypad display.
;******************************************************************************		
DELAY_2_MS		PROC
				EXPORT  DELAY_2_MS					[WEAK]
				
				; Your code goes here.
				PUSH	{r12,lr}
				MOV r12,#6000
				
LOOP			SUBS	r12,r12,#1
				BNE		LOOP
				
done_2_ms			POP		{r12,pc}
				
				ENDP

				END
