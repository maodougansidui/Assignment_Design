`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 04/02/2019 07:29:23 PM
// Design Name: 
// Module Name: my_tb
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


// test-bench code
// instantiate the module above

module my_tb();

reg a = 0;
reg b = 0;
wire c_from_circuit_1;
wire c_from_circuit_2;

my_circuit_1 my_circuit_1_0
(
.a(a),
.b(b),
.c(c_from_circuit_1)
);

my_circuit_2 my_circuit_2_0
(
.a(a),
.b(b),
.c(c_from_circuit_2)
);

assign f= c_from_circuit_1 ^ c_from_circuit_2;

// drive the inputs
initial begin
   a = 0;
   b = 0;
   #10; //wait for 10 time units

   a = 0;
   b = 1;
   #10; //wait for 10 time units

   a = 1;
   b = 0;
   #10; //wait for 10 time units

   a = 1;
   b = 1;
   #10; //wait for 10 time units

   $finish;
end

endmodule
