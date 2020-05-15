`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 03/12/2019 07:58:43 PM
// Design Name: 
// Module Name: lab7
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module lab7(
    input [3:0] sw,
    output [3:0] leds
    );
    assign leds[0]=(~sw[1]&~sw[2]) | (sw[3]&~sw[2]) | (~sw[1]&sw[0]&sw[3]) | (sw[1]&~sw[0]);
    assign leds[1]=(~sw[1]&~sw[2]) | (sw[3]&~sw[2]) | (~sw[1]&sw[0]&sw[3]) | (sw[1]&~sw[0]);
    assign leds[2]=(~sw[1]&~sw[2]) | (sw[3]&~sw[2]) | (~sw[1]&sw[0]&sw[3]) | (sw[1]&~sw[0]);
    assign leds[3]=(~sw[1]&~sw[2]) | (sw[3]&~sw[2]) | (~sw[1]&sw[0]&sw[3]) | (sw[1]&~sw[0]);
endmodule
