
public class ALUcontrol {
	
	//input
	static String myOp;
	//output
	static String aluOperation;
	static boolean branchType;
	
	public static String aluControl(String Op) {
		myOp = Op;
		int oper = Integer.parseInt(myOp, 2);
		if(oper<=6 || oper==11) {
			branchType = false;
			aluOperation=myOp;
		}
		else if(oper==7 || oper==8) {//load,store
			branchType = false;
			aluOperation="0001";
		}
		else if(oper==9) { //beq
			branchType = false;
			aluOperation="0000";
		}
		else if(oper==10) {//blt
			branchType = true;
			aluOperation="1011";//slt
		}
		return aluOperation;
	}


	public static boolean isBranchType() {
		return branchType;
	}
	
	

}
