import java.util.ArrayList;
import java.util.Vector;

class Block {
	int validbit = 0;
	int tag;
	String data;
	
	
	
	public Block(int tag, String data) {

		this.tag = tag;
		this.data = data;

	}

	
	
}

public class Controller {

	public static ArrayList<String> datamemory;
	public static Vector<String> InstructionMemory;
	public static ArrayList<String> registers;
	public static ArrayList<Block> cache;
	public static Vector<String> printing = new Vector();

	public Controller() {

		
		cache = new ArrayList<Block>(128);
		
		
		datamemory = new ArrayList<String>(1024);

		

		
		
		//datamemory.add("11111111111111110000000000000000");
		//datamemory.add("11111111111111110000000000000000");
		
		
		for (int i = 0; i < 128; i++) {
			
			cache.add(new Block(0, null));

		}
//for (int i = 0; i < 1024; i++) {
			
		//	datamemory.add(null);

		//}
datamemory.add("11111111111111110000000000000000");



	}

	public static void run() {

	}

	public void yalaprint(int i) {

		for (int j = printing.size() - 1; j >= 0; j--) {

			int to = 0;
			int from = 0;
			boolean foundend = false;
			boolean foundstart = false;

			if (printing.get(j).equals("*") && foundend == false) {

				to = j;
				foundend = true;
				for (int y = j - 1; y >= 0; y--) {

					if (printing.get(y).equals("*") && foundstart == false) {

						from = y;
						foundstart = true;

					}

				}

				for (int a = from + 1; a < to; a++) {

					System.out.println(printing.get(a));

				}

				j = from;

			}

		}

	}

	public static void main(String[] args) {
		
		/*PLEASE READ BEFORE TESTING
		
		 1. Place your last instruction in the program as "################################"
		 2. Our Formats are a) Control(8 bits), MyOp(4 bits), Rs(5bits), Rt(5bits), Rd(5bits),shamt(5bits)
							b) Control(8 bits), MyOp(4 bits), Rs(5bits), Rt(5bits),immediate(10 bits)
		 3. Control(8bits) are the control signals in this order "Jump, RegDst, RegWrite, ALUSrc, MemWrite MemRead, MemToReg, Branch"
		 4. MyOp(4 bits) to identify the operation: SUB  >> 0000
		 											ADD  >> 0001
		 											ADDi >> 0001
		 											MUL  >> 0010
		 											OR   >> 0011
		 											ANDi >> 0100
		 											SRL  >> 0101
		 											SLL  >> 0110
		 											LW   >> 0111
		 											SW   >> 1000
		 											BEQ	 >> 1001
		 											BLT  >> 1010
		 											STLi >> 1011
											   
		
		*/
		
		
		String MyProgram="";
		//MyProgram = "011000000001000100010000000000000" + "\n";// add $s3, $s2 ,$s2 martha
		MyProgram="001100000001 00001 00001 0000000001"+"\n";//addi
		//MyProgram += "01100000000000000000100000000000" + "\n"; // sub r0,r2,r0 sandy
		MyProgram += "001101100111 00010 00001 0000000000" + "\n";// lw $t0,4 ($t1)// yomna
	
	
		MyProgram +="011000000001 00010 00100 0000000001" + "\n"; // add
		MyProgram+="011000000011 00001 00010 0000000000"+"\n";//or
		MyProgram += "000110001000 00110 00111 0000000000" + "\n";// sw $t0,4($t2)//monica
		MyProgram += "000000011001 11111 00000 0000000000" + "\n";// Beq $t0,$t1,0 farah
		//MyProgram += "10000000000000000011000000000000" + "\n";// jump to pc 0
		// (infinite loop)
		MyProgram += "################################";
		InstructionMemory im = new InstructionMemory();
		im.loadProgram(MyProgram);

		for (int i = 0; i < 32; i++) {
			RegisterFile.Registers[i] = "00000000000000000000000000000000";

		}
		
		//datamemory = new ArrayList<String>(1024);
		//datamemory.add("11111111111111110000000000000000");
		Controller x = new Controller();
		InstructionFetch f = new InstructionFetch();
		Decode d = new Decode();
		Execute e = new Execute();
		Datamemory m = new Datamemory();
		Writeback w = new Writeback();

		for (int i = 0; i < 50; i++) {

			System.out.println("Cycle " + i);

			w.writeback();
			m.MemAccess();
			e.Execute();
			d.InstDecode();
			f.InstFetch();

			//System.out.println("HERE" + printing);

			x.yalaprint(i);
			printing.clear();

//			d.InstDecode();
//			e.Execute();
//			m.MemAccess();
//			w.writeback();
//			System.out.println("im:"+im.InstructionMemory.size());
//			System.out.println(f.pc);
		}

	}

}
