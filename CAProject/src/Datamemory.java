
public class Datamemory {

	public static int getDecimal(long l) {
		int decimal = 0;
		int n = 0;
		while (true) {
			if (l == 0) {
				break;
			} else {
				long temp = l % 10;
				decimal += temp * Math.pow(2, n);
				l = l / 10;
				n++;
			}
		}
		return decimal;
	}

	public static String Bin(String n) {
		// Size of an integer is assumed to be 32 bits
		String s = "";
		int length = n.length();

		int remainder = 32 - length;

		for (int i = 0; i < remainder; i++) {

			s = "0" + s;

		}
		s = s + n;
		return s;

	}

	public static void MemAccess() {
		if (PipelineExecute.memRead == null)
			return;

		if (PipelineExecute.end) {
			// forward
			PipeliningWriteback.end = true;
			return;
		}
		PipeliningWriteback.end = false;
		System.out.println("   ");
		
		Controller.printing.add(" ");

		String result = Bin(Integer.toBinaryString(PipelineExecute.aluResult));
		String readdata2 = Bin(Integer.toBinaryString(PipelineExecute.readData2));

		String branchadd = Bin(Integer.toBinaryString(PipelineExecute.branchAddressResult));
		String branchcondflag = PipelineExecute.toBranchFlag;
		String memread = PipelineExecute.memRead;
		String memwrite = PipelineExecute.memWrite;

		String jump = PipelineExecute.jump;
		String writereg = PipelineExecute.writeReg;
		String Pc = Bin(Integer.toBinaryString(PipelineExecute.PCpipe));
		String jumpadd = Bin(Integer.toBinaryString(PipelineExecute.readData1));
		;
		String memtoreg = PipelineExecute.memToReg;
		
		//commented here

//		System.out.println("                      memory access  Stage ");
//		System.out.println("ALU result : " + result);

		// System.out.println("sign extend : "+ extend);
		// System.out.println("Zero Flag : "+ zeroflag);
		
		
		//commented here

//		System.out.println("branch address result : " + branchadd);
//		System.out.println("MEM controls: MemRead: " + memread + ", MemWrite: " + memwrite);
		
		
		Controller.printing.add("*");
		Controller.printing.add(">Memory Access Stage: ");
		Controller.printing.add("ALU result : " + result);
		
		Controller.printing.add("Branch Address result : " + branchadd);
		Controller.printing.add("MEM controls: MemRead: " + memread + ", MemWrite: " + memwrite);

		String readdata = "";

		int r = getDecimal(Long.parseLong(result));

		int r2 = getDecimal(Long.parseLong(result)) / 4;
		
		
		
		//here

	//	System.out.println("writedata/readdata2 : " + readdata2);
		
		Controller.printing.add("writedata/readdata2 : " + readdata2);
		
		if (memwrite.equals("1")) {

			(Controller.datamemory).set(r2, readdata2);

			//here
			//System.out.println("Write address : " + result);
		
			Controller.printing.add("Write address : " + result);
			
			
			 Block data = Controller.cache.get(r % 8);
			 data.validbit =1;
			 data.data = readdata2; 
			 data.tag = r /8;
			

		}

		if (memread.equals("1")) {
			
			
			//here
			//System.out.println("read address : " + result);
		
			Controller.printing.add("Read address : " + result);
			
			readdata = Controller.datamemory.get(r2);
             
		}

		//////////////////////// cache

		if (memread.equals("1")  ) {
		
		  Block data = Controller.cache.get(r % 8);
		  
		  if (data.validbit == 1) {
		  
		  if (data.tag == r / 8) {
		  
			  Controller.printing.add("It is a hit in cache"); 
		  //System.out.println(data.data);
			  }
		  
		  else {
		  
		  
		  String out = Controller.datamemory.get(r2);
		  Controller.printing.add("It is a miss in cache !");
		  data.data = out; data.tag = r /
		  8; }
		  
		  }
		  
		  else {
		  
		  
		  String out = Controller.datamemory.get(r2);
		  
		  data.data = out; 
		  data.tag = r / 8; 
		  data.validbit = 1;
		  
		  Controller.printing.add("It is a miss in the cache!");
		  
		  
		  }
		  
		}
		
		
			
			
		
		
		
		//here
		//System.out.println("Read data :" + readdata);

		Controller.printing.add("Read data :" + readdata);
		
		String Pcnew = Pc;

		if (branchcondflag.equals("1")) {

			Pcnew = branchadd;
			
			//here
		//	System.out.println("branching to :" + branchadd);
		
			Controller.printing.add("Branching to :" + branchadd);
			InstructionFetch.pc = getDecimal(Integer.parseInt(Pcnew));
		}
		// else{

		// Pcnew=Pc;

		// }

		if (jump.equals("1")) {

			Pcnew = jumpadd;
			
		//here	
		//	System.out.println("jumping to :" + jumpadd);
			Controller.printing.add("Jumping to :" + jumpadd);
			InstructionFetch.pc = getDecimal(Integer.parseInt(Pcnew));
		}

		PipeliningWriteback.ALUResutReg = result;
		PipeliningWriteback.MemToRegReg = memtoreg;
		PipeliningWriteback.MemWriteReg = memwrite;
		PipeliningWriteback.PCReg = Pcnew;
		PipeliningWriteback.ReadDataReg = readdata;

		PipeliningWriteback.writeReg = writereg;
//		        		PipeliningWriteback.RegDstReg = 
		
		
		Controller.printing.add(" ");
		Controller.printing.add("*");

	}

}
