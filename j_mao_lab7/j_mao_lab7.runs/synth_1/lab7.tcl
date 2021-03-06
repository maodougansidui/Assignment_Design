# 
# Synthesis run script generated by Vivado
# 

set_msg_config -id {HDL 9-1061} -limit 100000
set_msg_config -id {HDL 9-1654} -limit 100000
create_project -in_memory -part xc7z010clg400-1

set_param project.compositeFile.enableAutoGeneration 0
set_param synth.vivado.isSynthRun true
set_property webtalk.parent_dir C:/Users/jmao/j_mao_lab7/j_mao_lab7.cache/wt [current_project]
set_property parent.project_path C:/Users/jmao/j_mao_lab7/j_mao_lab7.xpr [current_project]
set_property default_lib xil_defaultlib [current_project]
set_property target_language Verilog [current_project]
set_property board_part digilentinc.com:zybo:part0:1.0 [current_project]
set_property vhdl_version vhdl_2k [current_fileset]
read_verilog -library xil_defaultlib C:/Users/jmao/j_mao_lab7/j_mao_lab7.srcs/sources_1/new/lab7.v
read_xdc C:/Users/jmao/j_mao_lab7/j_mao_lab7.srcs/constrs_1/new/lab7.xdc
set_property used_in_implementation false [get_files C:/Users/jmao/j_mao_lab7/j_mao_lab7.srcs/constrs_1/new/lab7.xdc]

synth_design -top lab7 -part xc7z010clg400-1
write_checkpoint -noxdef lab7.dcp
catch { report_utilization -file lab7_utilization_synth.rpt -pb lab7_utilization_synth.pb }
