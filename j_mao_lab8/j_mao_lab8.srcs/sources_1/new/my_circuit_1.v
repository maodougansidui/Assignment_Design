`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/02/2019 07:25:55 PM
// Design Name: 
// Module Name: my_circuit_1
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


module my_circuit_1(
    input a,
    input b,
    output c
    );
    assign c = ( a & b ) | ( !a & b);
endmodule
