public class RegisterFile {

	static String[] Registers = new String[32];

	public RegisterFile() {

		Registers[0] = "00000000000000000000000000000000";
		Registers[1] = "00000000000000000000000000000001";
		Registers[2] = "00000000000000000000000000000010";
		Registers[3] = "00000000000000000000000000000011";
		Registers[4] = "00000000000000000000000000000100";
		Registers[5] = "00000000000000000000000000000101";

	}

	public static void ReadData(String rs, String rt) {

		String ReadData1;
		String ReadData2;

		int decimal1 = Integer.parseInt(rs, 2);

		int decimal2 = Integer.parseInt(rt, 2);

		String data1 = Registers[decimal1];
		String data2 = Registers[decimal2];

		//System.out.println("Read Data 1: " + data1);
		//System.out.println("Read Data 2: " + data2);
		
		Controller.printing.add("Read Data 1: " + data1);
		Controller.printing.add("Read Data 2: " + data2);
		
		

		ReadData1 = data1;
		ReadData2 = data2;
		
		
		
		PipelineDecode.readData1=ReadData1;
		PipelineDecode.readData2=ReadData2;

	}

	public static void main(String[] args) {

		RegisterFile r = new RegisterFile();

		r.ReadData("0001", "0001");
	}

}