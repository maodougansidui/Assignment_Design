;******************************************************************************
; CS 107: Computer Architecture and Organization
;
; Project: first
; Filename: main.s
; Author: Leonard Euler
; Semester: Fall 2049
; Description: This program lights an LED.
;
;******************************************************************************		
; These names come from section 8.5 Register Description (pg. 146) in the
; LPC4088 Users Manual.
DIR1	EQU 0x20
SET1	EQU	0x38
CLR1	EQU	0x3C

				AREA    SBCCCODE, CODE
					
start			PROC
				EXPORT  start					[WEAK]
					
				; IOCON_P1_7 R/W 0x4002C094
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS	r0, #0
				
				MOVW	r1, #0xC09C 			; this is the P1.7 address
				MOVT	r1, #0x4002				;
				
				STR		r0, [r1]				; set this address to have 0, so it could function I/O
				
				; IOCON_P1_11 R/W 0x4002C0AC
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS	r0, #0
				
				MOVW	r1, #0xC0AC				; this is the P1.11 address
				MOVT	r1, #0x4002				;
				
				STR		r0, [r1]				; set this address to have 0, so it could function I/O
				
				; IOCON_P1_5 R/W 0x4002C094
				; FUNC: 000 - Digital I/O pin
				; MODE: 00 - No pullup/pulldown
				; Load value into R0
				MOVS	r0, #0
				
				MOVW	r1, #0xC094				; this is the P1.5 address
				MOVT	r1, #0x4002				;
				
				STR		r0, [r1]				; set this address to have 0, so it could function I/O
				
				
				
				; Set base address for GPIO 0x20098000
				; MOVW	r1, #0x8000
				; MOVT	r1, #0x2009
				; Same as above but using the psedo-instruction.
				LDR		r1,=0x20098000
				
				; Set bit P1.7, P1.11 to be an output
				MOVS	r0, #0x880				; this is how P1 output should be.  Puple LED 
				STR		r0, [r1, #DIR1]

				; Set the bit at P1.7 high by setting the bit in the SET1 register. This
				; will turn the LED on. Turn it off by setting the same bit in the CLR1
				; register. Note that the bit set comes from #0x2000 used earlier.
				STR		r0, [r1, #CLR1]

				; Loop forever
				B       .
				ENDP
					
				END
