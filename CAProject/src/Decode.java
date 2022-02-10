public class Decode {

	public void InstDecode(){
		if(PipelineFetch.Instructionpipe==null)
			return;
		if(PipelineFetch.end)
		{
			//forward
			PipelineDecode.end = true;
			return;
		}
		PipelineDecode.end = false;
		String Instruction=PipelineFetch.Instructionpipe;
		int PC=PipelineFetch.PCpipe;
		String Control;
		String MyOp;
		String rs = "";
		String rt = "";
		String rd;
		String Shamt;
		String immediate;
		String extended;
		String Operation = "";
		String jump;
		String writeReg;

		Control = Instruction.substring(0, 8);
		jump = Instruction.substring(0, 1);
		MyOp = Instruction.substring(8, 12);

		rs = Instruction.substring(12, 17);
		rt = Instruction.substring(17, 22);
		rd = Instruction.substring(22, 27);
		Shamt = Instruction.substring(27, 32);
		immediate = Instruction.substring(22, 32);
		extended = SignExtend(immediate);
		
		
		
		//**
		if(Instruction.substring(1, 2).equals("1")) {
			writeReg = rd;
		} else writeReg = rt;
		//**

//		System.out.println("Control Signals: " + Control);
//		System.out.println("MyOp: " + MyOp);

		if (jump.equals("1")) {

			Operation = "Jump";

		}

		else if (Control.equals("00110000")) {

			// setlessthan andi addi

			if (MyOp.equals("1011")) {
				Operation = "Set On Less Than";

			}

			else if (MyOp.equals("0100")) {

				Operation = "ANDi";

			}

			else if (MyOp.equals("0001")) {

				Operation = "ADDi";

			}

		}

		else if (Control.equals("01100000")) {

			// shiftr shiftl or mult add sub

			if (MyOp.equals("0101")) {

				Operation = "Shift Right";

			}

			else if (MyOp.equals("0110")) {

				Operation = "Shift Left";

			}

			else if (MyOp.equals("0011")) {

				Operation = "Or";

			}

			else if (MyOp.equals("0010")) {

				Operation = "Multiply";

			}

			else if (MyOp.equals("0001")) {

				Operation = "Add";

			}

			else if (MyOp.equals("0000")) {

				Operation = "Subtract";

			}

		}

		else if (Control.equals("00110110")) {

			Operation = "Load Word";

		}

		else if (Control.equals("00011000")) {

			// sw

			Operation = "Store Word";

		}

		else if (Control.equals("00000001")) {

			// Beq blt

			if (MyOp.equals("1001")) {

				Operation = "Branch Equal";

			}

			else if (MyOp.equals("1010")) {

				Operation = "Branch on less than";

			}

		}
		
		//commented here
		
//		System.out.println("Decode Stage: ");
//		System.out.println("Operation: " + Operation);
//		RegisterFile.ReadData(rs, rt);
//		System.out.println("Sign-extend: " + extended);
//		System.out.println("Next PC: " + InstructionFetch.ProgCount());
//		// System.out.println("Rs: " + rs);
//		System.out.println("Rt: " + rt);
//		System.out.println("Rd: " + rd);
//		System.out.println("WB controls: " + " MemToReg: " + Instruction.substring(6, 7)+  "RegWrite: " + Instruction.substring(2, 3));
//		System.out.println("MEM controls: " + "MemRead: " + Instruction.substring(5, 6)+ "MemWrite: " + Instruction.substring(4, 5)+ "Branch: " + Instruction.substring(7, 8));
//		System.out.println("EX controls: " + "RegDest: " + Instruction.substring(1, 2) + "ALUOp: " + "ALUSrc: "+ Instruction.substring(3, 4));
//
//		System.out.println();
		
		
		
		Controller.printing.add("*");
		Controller.printing.add(">Decode Stage: ");
		Controller.printing.add("Operation: " + Operation);
		RegisterFile.ReadData(rs, rt);
		Controller.printing.add("Sign-extend: " + extended);
		Controller.printing.add("Next PC: " + InstructionFetch.ProgCount());
		// System.out.println("Rs: " + rs);
		Controller.printing.add("Rt: " + rt);
		Controller.printing.add("Rd: " + rd);
		Controller.printing.add("WB controls: " + " MemToReg: " + Instruction.substring(6, 7)+  " RegWrite: " + Instruction.substring(2, 3));
		Controller.printing.add("MEM controls: " + "MemRead: " + Instruction.substring(5, 6)+ " MemWrite: " + Instruction.substring(4, 5)+ " Branch: " + Instruction.substring(7, 8));
		Controller.printing.add("EX controls: " + "RegDest: " + Instruction.substring(1, 2) + " ALUOp: " +MyOp+ " ALUSrc: "+ Instruction.substring(3, 4));
		
		Controller.printing.add(" ");
		
		Controller.printing.add("*");
		
		
		
		
		
		PipelineDecode.PCpipe=PC;
		PipelineDecode.Shamt=Shamt;
		PipelineDecode.MyOp=MyOp;
		PipelineDecode.signExtend=extended;
		PipelineDecode.jump=jump;
		PipelineDecode.regDst=Instruction.substring(1, 2);
		PipelineDecode.regWrite=Instruction.substring(2, 3);
		PipelineDecode.ALUSrc=Instruction.substring(3, 4);
		PipelineDecode.MemWrite=Instruction.substring(4, 5);
		PipelineDecode.MemRead=Instruction.substring(5, 6);
		PipelineDecode.MemToReg=Instruction.substring(6, 7);
		PipelineDecode.branch=Instruction.substring(7, 8);
		PipelineDecode.writeReg = writeReg;
	
	
	}

	public String SignExtend(String immediate) {
		
		String s = immediate.substring(0, 1);
		String extended = "";

		if (s.equals("1")) {

			extended = "1111111111111111111111" + immediate;

		} else {

			extended = "0000000000000000000000" + immediate;

		}

		return extended;
	}

}