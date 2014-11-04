import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;


public class Generate {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		int num = 250000;
		PrintStream out = new PrintStream(new FileOutputStream("output_"+num+".txt"));
	    System.setOut(out);
	      
		//n += num+"\n";
		System.out.println(num);
		
		Random ran = new Random();
		int r = 0;
		for(int i=0;i<num; i++){
			String arr = "";
			
			r = ran.nextInt(4);
			for(int j=0;j< r ;j++){
				arr += ran.nextInt(num) +" ";
			}
			arr += ran.nextInt(num);
			
			//n += arr+"\n";
			System.out.println(arr);
		}
		
		System.out.println(ran.nextInt(num)+" "+ran.nextInt(num));
		System.out.println(0);
		
		 //create an print writer for writing to a file
	     
	     
	      
	      //close the file (VERY IMPORTANT!)
	      out.close();
		
		
		System.out.println("DONE");
		//
	}

}
