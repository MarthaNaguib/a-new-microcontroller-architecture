
public class ALU {
	// inputs
	static int operand1;
	static int operand2;
	static String aluOperation;
	static int shift;
	// outputs
	static String zeroFlag;
//	static boolean negativeFlag;//TODO
	static int aluResult;
//	static int branchAddressResult;

	public static void ALUEvaluator() {
		switch (aluOperation) {
		case "0000": // sub
//		case "1001": //beq
			subOp(operand1, operand2);
			break;
		case "0001": // add, addi
//		case "0111": //load
//		case "1000": //store
			addOp(operand1, operand2);
			break;
		case "0010": // multiply
			mulOp(operand1, operand2);
			break;
		case "0011": // OR
			OROp(operand1, operand2);
			break;
		case "0100": // Andi
			ANDOp(operand1, operand2);
			break;
		case "0101": // srl
			srlOp(operand1, shift);
			break;
		case "0110": // sll
			sllOp(operand1, shift);
			break;
		case "1011": // slti,blt
			sltOp(operand1, operand2);
			break;

		default:
			System.out.println("Operation not recognized");
			Controller.printing.add("Operation not recognized");
		}
	}

	private static void mulOp(int o1, int o2) {
		aluResult = o1 * o2;
		zeroFlag = (aluResult == 0 ? "1" : "0");
	}

	public static void ANDOp(int o1, int o2) {
		aluResult = o1 & o2;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

	public static void OROp(int o1, int o2) {
		aluResult = o1 | o2;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

	public static void addOp(int o1, int o2) {
		aluResult = o1 + o2;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

	public static void subOp(int o1, int o2) {
		aluResult = o1 - o2;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

	public static void sltOp(int o1, int o2) {
		aluResult = (o1 < o2) ? 1 : 0;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

	public static void srlOp(int o1, int shift) {
		aluResult = o1 >> shift;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

	public static void sllOp(int o1, int shift) {
		aluResult = o1 << shift;
		zeroFlag = (aluResult == 0 ? "1" : "0");

	}

//	public static void NOR(int o1, int o2) {
//		aluResult =  ~(o1|o2);
//		zeroFlag = (aluResult==0?true:false);
//
//	}

	public static void setOperand1(int operand1) {
		ALU.operand1 = operand1;
	}

	public static void setOperand2(int operand2) {
		ALU.operand2 = operand2;
	}

	public static void setAluOperation(String aluOperation) {
		ALU.aluOperation = aluOperation;
	}

	public static void setShift(int shift) {
		ALU.shift = shift;
	}

//	public static boolean isZeroFlag() {
//		return zeroFlag;
//	}

	public static int getAluResult() {
		return aluResult;
	}

	public static String getZeroFlag() {
		return zeroFlag;
	}

//	public static void main(String[] args) {
//		sllOp(14, 1);
//		System.out.println(aluResult);
//	}
}
