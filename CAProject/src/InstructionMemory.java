import java.io.ObjectInputStream.GetField;
import java.util.Vector;

public class InstructionMemory {
	public static  Vector<String> InstructionMemory =new Vector<String>(1024);

	
	  public void loadProgram(String s){
	      String[] lines = s.split("\n");
	      //System.out.println(lines.length);
	      for(int PC=0;PC<lines.length;++PC){ //
	    	  InstructionMemory.add(lines[PC].substring(0, 8));
	    	  InstructionMemory.add(lines[PC].substring(8, 16));
	    	  InstructionMemory.add(lines[PC].substring(16, 24));
	    	  InstructionMemory.add(lines[PC].substring(24));

	  
	      }}
	  
	  public static void main(String[] args) 
		{
			InstructionMemory M = new InstructionMemory();
			//String MyProgram = "00000010010100101001100000100000"+"\n";//add $s3, $s2 ,$s2
//			String MyProgram="001100000001000010000100000000001"+"\n";//addi
//			//MyProgram+="00000001011011000101000000100010"+"\n"; //sub $t3,$t4,$t2
//			MyProgram+="00110110011100010000010000000010"+"\n";//lw $t0,4 ($t1)
//			MyProgram+= "00000010010100101001100000100000"+"\n";//add $s3, $s2 ,$s2
//			//MyProgram+="00110110011100010000010000000010"+"\n";//sw $t0,4($t2)
//			MyProgram+="01100000001100001000100000000000"+"\n";//or
//			MyProgram+="00110110011100010000010000000010"+"\n";//sw $t0,4($t2)
//			MyProgram+="00010001000010010000000000000100"+"\n";//Beq $t0,$t1,4
//			//MyProgram+="00001000000000000000000000000100";//j 4
//
//-----------------------Testing Inst Fetch and Inst Memory-------------------------------
//			M.loadProgram(MyProgram);
//			InstructionFetch f =new InstructionFetch();
//			System.out.println("My Pc in 32 bits is "+f.ProgCount()+"My Pc value in demcimal is "+f.pc);	
//			f.InstFetch();
//			System.out.println("------------------------");
//			System.out.println("My Pc in 32 bits is "+f.ProgCount()+"My Pc value in demcimal is "+f.pc);	
//			f.InstFetch();
//		System.out.println("------------------------");
//		System.out.println("My Pc in 32 bits is "+f.ProgCount()+"My Pc value in demcimal is "+f.pc);	
//		f.pc+=8;
//		System.out.println("My Pc in 32 bits is "+f.ProgCount()+"My Pc value in demcimal is "+f.pc);	
//		f.InstFetch();
//		System.out.println("------------------------");
//		System.out.println("My Pc in 32 bits is "+f.ProgCount()+"My Pc value in demcimal is "+f.pc);	

			
		}
	  
	  
	  
	
	
}
