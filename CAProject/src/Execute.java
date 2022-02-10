public class Execute {
	
	//Inputs
	  String myOp;
	  String aluSrc;
	  int readData1;//output too
	  int readData2; //output too
	  int signExtend;
	  int PCpipe; //output too
	  int shift;
	  String branch;
	
	//Outputs
	  String toBranchFlag;
	  int aluResult;
	  int branchAddressResult;
	
	//attributes that i just need to pass
	  String writeReg;
	  String memToReg;
	  String regWrite;
	  String memRead;
	  String memWrite;
	  String jump;

	
	public void Execute() {
		getPipeline();
		if(myOp==null) {
		//	System.out.println("no execute");
			return;}
		if(PipelineDecode.end)
		{
			//forward
			PipelineExecute.end = true;
			return;
		}
		PipelineExecute.end =false;
		int operand1 = readData1;
		int operand2;
		if(aluSrc.equals("1"))
			operand2=signExtend;
		else
			operand2 = readData2;
		String aluOperation = ALUcontrol.aluControl(myOp);
		ALU.setAluOperation(aluOperation);
		ALU.setOperand1(operand1);
		ALU.setOperand2(operand2);
		ALU.setShift(shift);
		ALU.ALUEvaluator();
		aluResult = ALU.getAluResult();
		computeBranchAddress();
		computetoBranchFLag();
		printExecuteOutput();
		passPipeline();
	}
	
	private void passPipeline() {
		// TODO Auto-generated method stub
		PipelineExecute.PCpipe = PCpipe;
//System.out.println("PCpipe in execute: "+PCpipe);
		PipelineExecute.branchAddressResult = branchAddressResult;
		PipelineExecute.toBranchFlag = toBranchFlag;
		PipelineExecute.jump = jump;
		PipelineExecute.memWrite = memWrite;
		PipelineExecute.memToReg=memToReg;
		PipelineExecute.memRead=memRead;
		PipelineExecute.readData1 = readData1;
		PipelineExecute.aluResult=aluResult;
		PipelineExecute.readData2 = readData2;
		PipelineExecute.writeReg = writeReg;
		PipelineExecute.regWrite = regWrite;
		
	}
	private   void getPipeline() {
		PCpipe = PipelineDecode.PCpipe;
		myOp = PipelineDecode.MyOp;
		if(myOp==null) {
		//	System.out.println("no execute");
			return;}
		shift = Integer.parseInt(PipelineDecode.Shamt,2);
		
		signExtend = (int) Long.parseLong(PipelineDecode.signExtend, 2) ;
		jump = PipelineDecode.jump;
		regWrite = PipelineDecode.regWrite;
		aluSrc = PipelineDecode.ALUSrc;
		memWrite = PipelineDecode.MemWrite;
		memRead = PipelineDecode.MemRead;
		memToReg = PipelineDecode.MemToReg;
		branch = PipelineDecode.branch;
		readData1 = (int) Long.parseLong(PipelineDecode.readData1,2);
		readData2 = (int) Long.parseLong(PipelineDecode.readData2,2);
		writeReg = PipelineDecode.writeReg;
		
	}
	public   void computeBranchAddress() {
		int pcAddress = PCpipe;
		int branchAddress = signExtend;
		branchAddress = branchAddress*4;
		branchAddress+=pcAddress;
		branchAddressResult= branchAddress;
	}
	
	public   void computetoBranchFLag() {
		String branchCondFlag;
		if(ALUcontrol.isBranchType()) {
			branchCondFlag = ALU.getAluResult()+"";
		}else branchCondFlag = ALU.getZeroFlag();
		if(branch.equals("1") && branchCondFlag.equals("1")) {
			toBranchFlag="1";
		}else toBranchFlag ="0";
	}
	
	public   void printExecuteOutput() {
		
	//commented here	
//		System.out.println(">Execute Stage:");
//		System.out.println("to Branch Flag: "+ toBranchFlag);
//		System.out.println("Branch Address: "+ extend(branchAddressResult) );
//		System.out.println("ALU Result/Address: "+ extend(aluResult)  );
//		System.out.println("Register Value to write to Memory: "+ extend(readData2));
//		System.out.println("rt/rd register: "+ writeReg);
//		System.out.println("WB controls: MemToReg: " +memToReg+ ", RegWrite: "+regWrite);
//		System.out.println("MEM controls: MemRead: "+memRead+", MemWrite: "+memWrite+", Branch: "+branch);
		
		Controller.printing.add("*");
		Controller.printing.add(">Execute Stage: ");
		Controller.printing.add("to Branch Flag: "+ toBranchFlag);
		Controller.printing.add("Branch Address: "+ extend(branchAddressResult) );
		Controller.printing.add("ALU Result/Address: "+ extend(aluResult)  );
		Controller.printing.add("Register Value to write to Memory: "+ extend(readData2));
		Controller.printing.add("rt/rd register: "+ writeReg);
		Controller.printing.add("WB controls: MemToReg: " +memToReg+ ", RegWrite: "+regWrite);
		Controller.printing.add("MEM controls: MemRead: "+memRead+", MemWrite: "+memWrite+", Branch: "+branch);
		Controller.printing.add(" ");
		Controller.printing.add("*");
		
		
	}

	public   String extend(int n) {
		String b = Integer.toBinaryString(n);
		while(b.length()<32)
			b = "0"+b;
		return b;
	}
	public   String getMyOp() {
		return myOp;
	}

	public   int getAluResult() {
		return aluResult;
	}
	public   int getBranchAddressResult() {
		return branchAddressResult;
	}
	
	public   void main(String[] args) {
		Execute();

	}
	
}
