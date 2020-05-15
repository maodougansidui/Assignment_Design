;******************************************************************************
; CS 107: Computer Architecture and Organization
;
; Project: cs107-ex05-rot13
; Filename: main.s
; Group Number: Jianlyu Mao
; Semester: Spring 2019
; Description: See the README.md file in the Git Repo.
;
;******************************************************************************		
				AREA    SBCCDATA, DATA
; Declare your memory space here.

transformation	SPACE 40

				AREA    SBCCCODE, CODE
					
start			PROC
				EXPORT  start					[WEAK]
					
; pseudo code
; while (c!= 0){
;	if (c>='A' && c<='Z')
;		d=c+13;
;		if (d>'Z')
;			a=d-90;
;			a=a+'A'-1;
;	c++;
;	store into memory.
;

				
				LDR r0,=msg						; load the address of the msg
				MOV r1, #0						; index of the string
				MOV r7, #0						; offset of the memory address.
				LDR r2,=transformation			; loads the memory location called transformation

LOOP			LDRB r3,[r0,r1]					; get the letter under index.
				CMP r3,#0						; if the character is null, meaning the end of the string
				BEQ done						; then branch to done
				CMP	r3, #0x41					; if char ascii code is less than 'A'
				BCC ifless						; then branch to ifless
				CMP r3,#0x5A					; else if it is in the domain of 0x41<=char<=0x5A
				BLS ifLetter					; indicating it is a capital Character, branch
				ADD r1,r1,#1					; index++, store the character into memory location + offset
				STR r3,[r2,r7]
				ADD r7,r7,#1
				B	LOOP
				
ifless			ADD r1,r1,#1					; the same as above
				STR r3,[r2,r7]
				ADD r7,r7,#1
				B LOOP

ifLetter		ADD r4,r3,#0x0D					; do the rotation instruciton
				CMP r4,#0x5A					; if the result is larger than 0x5A
				BHI	changeLe					; branch to changeLetter
				ADD r1,r1,#1					; same as above
				STR r4,[r2,r7]
				ADD r7,r7,#1
				B LOOP
				
changeLe		SUB r5,r4,#0x5A					; do the calculation and rotate
				ADD	r5,r5,#0x40
				ADD r1,r1,#1
				STR r5,[r2,r7]
				ADD r7,r7,#1
				B	LOOP
				
done
				
				; Loop forever
				B       .
				
				ENDP
msg			DCB		"SBCC CS IS THE BEST",0


				END
