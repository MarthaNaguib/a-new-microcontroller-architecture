import java.io.ObjectInputStream.GetField;

public class InstructionFetch {

	public static int pc = 0;

	public static String InstFetch() {
		String Instruction = "";
		for (int i = pc; i < pc + 4; i++) {

			Instruction += InstructionMemory.InstructionMemory.get(i);

		}
		if (Instruction.equals("################################")) {
			PipelineFetch.end = true;
			return Instruction;
		}
		PipelineFetch.end = false;
	
		// commented here
		//System.out.println("Instruction { " + Instruction + " } Has been fetched ;) at pc =" + pc);
		
		
		Controller.printing.add("*");
		Controller.printing.add(">Fetch Stage: ");
		Controller.printing.add("Instruction { " + Instruction + " } Has been fetched  at pc =" + pc);
		Controller.printing.add(" ");
	
		
		pc += 4;
		Controller.printing.add("Next PC: "+ProgCount());
		Controller.printing.add("*");
		PipelineFetch.Instructionpipe = Instruction;
		PipelineFetch.PCpipe = pc;
		return Instruction;

	}

	public static String ProgCount() { // trag3lie el PC in 32 Bits
		String s = Integer.toBinaryString(pc);
		for (int i = s.length(); i < 32; i++) {
			s = "0" + s;
		}
		return s;

	}

}
