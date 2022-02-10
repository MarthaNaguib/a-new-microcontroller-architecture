
public class Writeback {

	public static int getDecimal(int binary) {
		int decimal = 0;
		int n = 0;
		while (true) {
			if (binary == 0) {
				break;
			} else {
				int temp = binary % 10;
				decimal += temp * Math.pow(2, n);
				binary = binary / 10;
				n++;
			}
		}
		return decimal;
	}

	public static void writeback() {
		if (PipeliningWriteback.writeReg == null)
			return;
		if (PipeliningWriteback.end) {

			return;
		}
		String result = PipeliningWriteback.ALUResutReg;
		String readdata = PipeliningWriteback.ReadDataReg;
		String memtoreg = PipeliningWriteback.MemToRegReg;
		String writereg = PipeliningWriteback.writeReg;
		String regdest = PipeliningWriteback.RegDstReg;
		String pcnew = PipeliningWriteback.PCReg;
//		here
//		System.out.println("Write Back Stage ");

		//		String finalreg="don't care";

//		here
//		System.out.println("MemToReg: " + memtoreg);
		
		
		Controller.printing.add("*");
		Controller.printing.add(">Write Back Stage: ");
		Controller.printing.add("MemToReg: " + memtoreg);
		

		String finalout = "don't care";

		if (memtoreg.equals("0")) {
			finalout = result;

		} else {
			if (memtoreg.equals("1")) {

				finalout = readdata;
			}
		}

//		if (regdest.equals("0") || regdest.equals("1")){
		int r = getDecimal(Integer.parseInt(writereg));

//		(RegisterFile.Registers).set(r, finalout);
		RegisterFile.Registers[r] = finalout;
//	}

		
		//here
//		System.out.println("write data:  " + finalout);

//		System.out.println("pcnew: " + pcnew);
		
		Controller.printing.add("write Data: " + finalout);
		Controller.printing.add("PCnew: " + pcnew);
		
		Controller.printing.add(" ");
		Controller.printing.add("*");

		int p = getDecimal(Integer.parseInt(pcnew));

		// InstructionFetch.pc= p;

	}

}
