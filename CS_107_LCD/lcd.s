;******************************************************************************
; CS 107: Computer Architecture and Organization
;
; Project: L03_LCD
; Filename: lcd.s
; Author: Jianlyu Mao
; Semester: Spring 2019
; Description: This is the driver routines for the LCD Keypad Shield. The pinouts for
; the board are as follows:
;
;  | LCD Hardware | Arduino Pin | LPC 4088 Port |
;  |:------------:|:-----------:|:-------------:|
;  |     D4       |     D4      |      P0.5     |
;  |     D5       |     D5      |      P5.0     |
;  |     D6       |     D6      |      P5.1     |
;  |     D7       |     D7      |      P0.4     |
;  |     RS       |     D8      |      P5.3     |
;  |     E        |     D9      |      P5.4     |
;  |  Backlight   |    D10      |      P0.6     |
;  |  Buttons     |    A0       |      P0.23    |
;
;******************************************************************************		
BACKLIGHT_ON	EQU	0x01
BACKLIGHT_OFF	EQU	0x00
SET_LOW			EQU	0x00
SET_HIGH		EQU	0x01

GPIO_BASE	EQU 0x20098000
SET0		EQU	0x018
CLR0		EQU	0x01C
DIR0		EQU	0x000
SET1		EQU	0x038
CLR1		EQU	0x03C
SET5		EQU	0x0B8
CLR5		EQU	0x0BC
DIR5		EQU	0x0A0
	
				AREA    LCDCODE, CODE
				IMPORT	DELAY_1_MS
				IMPORT	DELAY_2_MS
					
;******************************************************************************		
; Initializes the LCD hardware. 
;
; void LCD_INIT()
;
; D4 - D7 are I/O, RS, E, RW, and backlight are digital I/O outputs only. All
; I/O pins are outputs because the LCD Keypad Shield has the R/W* pin grounded
; so you can only write to it. Once the pins (and ports) are setup you need to
; program a sequence based on the one shown in Figure 24.
;******************************************************************************	

;https://mil.ufl.edu/3744/docs/lcdmanual/commands.html
;
;
LCD_INIT		PROC
				EXPORT  LCD_INIT					[WEAK]
				
				; Your code goes here.
				; delay 40 ms.
				PUSH	{lr}
				MOV r0, #0
				
delay40			CMP r0, #40
				BGE	config
				BL	DELAY_1_MS
				ADD	r0,r0,#1
				B	delay40

config			; IOCON_P0_5 R/W 0x4002C014
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
                                                                                                                                                                                                                                                        			MOVS r0,#0							; D4 = P0.5
				LDR r1, =0x4002C014
				
				STR r0,[r1]
				
				; IOCON_P5_0 R/W  0x4002C280
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS r0,#0							; D5=P5.0
				LDR r1, =0x4002C280
				
				STR r0,[r1]
				
				; IOCON_P5_1 R/W  0x4002C284
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS r0,#0							; D6 = P5.1
				LDR r1, =0x4002C284
				
				STR r0,[r1]
				
				; IOCON_P0_4 R/W   0x4002C010
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS r0,#0							; D7 = P0.4
				LDR r1, =0x4002C010
				
				STR r0,[r1]
				
				; IOCON_P5_3 R/W   0x4002C28C
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS r0,#0							; RS= D8 = P5.3
				LDR r1, =0x4002C28C
				
				STR r0,[r1]
				
				; IOCON_P5_4 R/W   0x4002C290
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS r0,#0							; E= D9 = P5.4
				LDR r1, =0x4002C290
				
				STR r0,[r1]
				
				; IOCON_P0_6 R/W   0x4002C018
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS r0,#0							; BackLight= D10 = P0.6
				LDR r1, =0x4002C018
				
				STR r0,[r1]
				
				; Configure Port DIR 
				;
				LDR		r1,=GPIO_BASE
				MOVS	r0, #0x70
				STR		r0,[r1,#DIR0]
				
				LDR		r1,=GPIO_BASE
				MOVS	r0,#0x1B
				STR		r0,[r1,#DIR5]
				
				; delay 1ms
				;
				
				BL		DELAY_1_MS
				
				; initialize RS and E
				;
				MOV	r7,#SET_LOW		; r7 is specifically used for initializing RS and E
				BL	SET_RS		
				BL	SET_E	
				
				; function set (intf 8 bit)
				MOV	r4,#0x03	; 0011
				BL	WRITE_LS_NYBBLE	
				; delay 6ms, I don't want to write a loop no more, since it is only 6 ms, I just repeat funciton delay_2 3 times.

				BL	DELAY_2_MS
				BL	DELAY_2_MS
				BL	DELAY_2_MS
	
				; function set (intf 8 bit)
				MOV	r4,#0x03	;0011
				BL	WRITE_LS_NYBBLE
				
				; delay 1 ms.
				
				BL	DELAY_1_MS
				; function set (intf 4 bit)
				MOV	r4,#0x02	; 0010
				BL	WRITE_LS_NYBBLE

				;delay 2 ms.
				BL	DELAY_2_MS

				; in 4 bit interface, set 2 lines, font 5x8
				MOV	r4,#0x28	; 0010 10**	* -> dont care, and I simply put 0
				BL	WRITE_COMMAND	; we are using different funciton because the value in r4 
							; is byte, and this is in command register.

				; delay for 2 ms.
				BL	DELAY_2_MS

				; Display off.
				MOV	r4,#0x08	; 0000 1000 
				BL	WRITE_COMMAND

				; delay for 2 ms.
				BL	DELAY_2_MS

				; display clear.
				MOV	r4, #0x01	; 0000 0001
				BL	WRITE_COMMAND

				; delay for 2 ms.
				BL	DELAY_2_MS

				; increment cursor, don't shift.
				MOV	r4, #0x06	; 0000 0110
				BL	WRITE_COMMAND

				; delay for 2 ms.
				BL	DELAY_2_MS

				; Display on, cursor displayed and blinking
				MOV	r4, #0x0F	; 0000 1111
				BL	WRITE_COMMAND

				; end of the initialization.

				POP		{pc}

				ENDP


;******************************************************************************		
; Writes a string to the first or second line of the LCD Keypad Shield.
;
; void WRITE_CSTRING(uchar_32 line, char *string)
;
; r4 contains which line with 0 being the top line and 1 the bottom. r5
; contains the address of the first character to be output. When writing a
; character be sure to wait 2 ms between characters.
;******************************************************************************		
WRITE_CSTRING	PROC
				EXPORT  WRITE_CSTRING					[WEAK]

				; Your code goes here.
				
				
				ENDP


;******************************************************************************		
; Writes a command byte to the LCD Keypad Shield
;
; void WRITE_COMMAND(uint_32 value)
;
; r4 contains the value to write. This routine simply sets the RS signal and
; calls WRITE_BYTE.
;******************************************************************************		
WRITE_COMMAND	PROC
				EXPORT  WRITE_COMMAND					[WEAK]

				; Your code goes here.
				; low
				PUSH	{r7,lr}
				MOV	r7,#SET_LOW
				
				BL		SET_RS
				; after the RS is set, then branch to WRITE_BYTE
					
				BL		WRITE_BYTE
				
				POP		{r7,pc}
				
				ENDP

;******************************************************************************		
; Writes a data byte to the LCD Keypad Shield
;
; void WRITE_COMMAND(uint_32 value)
;
; r4 contains the value to write. This routine simply sets the RS signal and
; calls WRITE_BYTE.
;******************************************************************************		
WRITE_DATA		PROC
				EXPORT  WRITE_DATA					[WEAK]
				
				; Your code goes here.
				; high
				
				PUSH	{r7,lr}
				MOV	r7,#SET_HIGH
				
				BL		SET_RS
				
				BL		WRITE_BYTE
				
				POP		{r7,pc}
				
				ENDP

;******************************************************************************		
; Writes a byte to the LCD Keypad Shield
;
; void WRITE_BYTE(uint_32 value)
;
; r4 contains the value to write. We should set the RS signal before calling
; this routine. Setting RS to LOW gives us a command and setting RS to HIGH
; gives us a data command. Since our LCD Keypad Shield is using a 4-bit
; interface we need to send out the MS nybble first followed by the LS nybble.
;******************************************************************************		
WRITE_BYTE		PROC
				EXPORT  WRITE_BYTE					[WEAK]
				
				; Your code goes here.
				; save the r4 value into the temp
				PUSH	{r8,lr}
				MOV	r8,r4		; r8 means temp, no other functions will use r8.
				
				LSR	r4,r4,#4	; logical shift right so the MS nybble is saved.	
				BL	WRITE_LS_NYBBLE
					
				MOV	r4,r8		; then use temp to save back to r4, r4 now contains LS nybble.
				BL	WRITE_LS_NYBBLE

				POP	{r8,pc}	
				ENDP

;******************************************************************************		
; Writes the LS nybble to the LCD Keypad Shield.
;
; void WRITE_LS_NYBBLE(uint_32 value)
;
; r4 contains the value to write. It is assumed that the RS line has already
; been set to the proper value. Be sure to set E to HIGH, output the data, and
; set E to LOW to write the data to the LCD Keypad Shield.
;******************************************************************************		
WRITE_LS_NYBBLE	PROC
				EXPORT  WRITE_LS_NYBBLE					[WEAK]
				
				; Your code goes here.
				;In this case,no matter MS nybble or LS nybble, we all consider it to be least 4 bits.
				;For RS set differently, data and command would follow the same codes below.

				PUSH	{r0-r3,lr}
				; SET E HIGH
				MOV	r7, #SET_HIGH		;r7 will always be created as RS, E special register.
				BL	SET_E
				
				; r2 will be the GPIO_BASE
				LDR	r3,=GPIO_BASE
				
				; Do the LSR (logic shift right) to get the carry flag. if carry flag is set,
				; then the bit is 1, otherwise it is 0. r4 will be the value we need.
				
				MOV	r0, #0x20	; D4 is P0.5
				LSRS	r4,r4,#1
				STRCS	r0,[r3,#SET0]	; if D4 is 1, carry flag is 1 and store it in the base+offset address.
				STRCC	r0,[r3,#CLR0]	; if D4 is 0, carry flag is 0 and store it in the base+offset address.
				
				MOV	r0, #0x01	; D5 is P 5.0
				LSRS	r4,r4,#1
				STRCS	r0,[r3,#SET5]	; if D5 is 1, carry flag is 1 and store it in the base+offset address.
				STRCC	r0,[r3,#CLR5]	; if D5 is 0, carry flag is 0 and store it in the base+offset address.
				
				MOV	r0, #0x02	; D6 is P 5.1
				LSRS	r4,r4,#1
				STRCS	r0,[r3,#SET5]	; if D6 is 1, carry flag is 1 and store it in the base+offset address.
				STRCC	r0,[r3,#CLR5]	; if D6 is 0, carry flag is 0 and store it in the base+offset address.
				
				MOV	r0, #0x10	; D7 is P0.4
				LSRS	r4,r4,#1
				STRCS	r0,[r3,#SET0]	; if D7 is 1, carry flag is 1 and store it in the base+offset address. 
				STRCC	r0,[r3,#CLR0]	; if D7 is 0, carry flag is 0 and store it in the base+offset address. 

				; SET E LOW
				MOV	r7, #SET_LOW
				BL	SET_E

				POP	{r0-r3,pc}	
				ENDP

;******************************************************************************		
; Sets the RS data line to the value passed.
;
; void SET_RS(uint_32 status)
;
; r4 contains the value to set RS. RS is bit P5.3 which is already set to output.
;******************************************************************************		
SET_RS			PROC
				EXPORT  SET_RS					[WEAK]
				
				; Your code goes here.
				PUSH	{r5,r6,lr}
				LDR		r6,=GPIO_BASE
				CMP		r7,#SET_LOW
				BEQ		rs_0
				MOV		r5,#0x04
				STR		r5,[r6,#SET5]
				POP		{r5,r6,pc}
				
rs_0			MOV		r5,#0x04
				STR		r5,[r6,#CLR5]
				POP		{r5,r6,pc}
				
				ENDP

;******************************************************************************		
; Sets the E data line to the value passed.
;
; void SET_E(uint_32 status)
;
; r4 contains the value to set E. E is bit P5.4 which is already set to output.
;******************************************************************************		
SET_E			PROC
				EXPORT  SET_E					[WEAK]
				
				; Your code goes here.
				PUSH	{r5,r6,lr}
				LDR		r6,=GPIO_BASE
				CMP		r7,#SET_LOW
				BEQ		e_0
				MOV		r5,#0x10
				STR		r5,[r6,#SET5]
				POP		{r5,r6,pc}
				
e_0				MOV		r5,#0x10
				STR		r5,[r6,#CLR5]
				POP		{r5,r6,pc}
				
				ENDP

;******************************************************************************		
; Turns on or off the LCD backlight. The parameter status is passed on the 
; stack.
;
; void LCD_BACKLIGHT(int status)
;
; status - 1 turn on backlight, 0 - turn off backlight
;******************************************************************************		
LCD_BACKLIGHT	PROC
				EXPORT  LCD_BACKLIGHT					[WEAK]
				
				; Your code goes here.
				PUSH	{r1,r2,lr}
				LDR		r1,[sp,#0x0C]
				LDR		r2,=GPIO_BASE
				CMP		r1, #BACKLIGHT_OFF
				BEQ		backlightOff
				MOV		r1,#0x40
				STR		r1,[r2,#SET0]
				POP		{r1,r2,pc}
				
backlightOff	MOV		r1,#0x40
				STR		r1,[r2,#CLR0]
				POP		{r1,r2,pc}
				
				ENDP

				END
