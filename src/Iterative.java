// Nelson Wu - nwu009 - 1177289
// Purpose : Finds if there is a path in a given set of nodes

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Iterative {
	
	static int[][] nodeRef;
	static boolean[] visited;

	static int startNodeNum = -1;
	static int endNodeNum = -1;
	static boolean finalState = false;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//Scanner sin = new Scanner(System.in);
		InputStream inputStream = new FileInputStream("rand10K-2.in");
		BufferedReader sin = new BufferedReader(new InputStreamReader(inputStream));
		//BufferedReader sin = new BufferedReader(new InputStreamReader( System.in));
		
		long startTime = System.nanoTime();
		
		int lineNum = -1;
		int maxNodes = 0;
		String[] t;
		String[] allChilds;
		int[] intArr;
		int i = 0;
		
		String line;
		while ( (line = sin.readLine()) != null){
			//String line = sin.nextLine();
			if(lineNum==-1){ // start the node making
				lineNum = 0;
				maxNodes = Integer.parseInt(line);
				nodeRef = new int[maxNodes][0];
				visited = new boolean[maxNodes];
			} else if(lineNum > maxNodes){ // reached end of digraph
				t = line.split(" ");
				startNodeNum = Integer.parseInt(t[0]);
				endNodeNum = Integer.parseInt(t[1]);
				break;
			} else{
				allChilds = line.split("\\s+");
				if(line.equals("")){
					allChilds = new String[0];
				}
				
				intArr = new int[allChilds.length];
				
				for(i=0;i<allChilds.length;i++){
					intArr[i] = Integer.parseInt(allChilds[i]); 
				}
				nodeRef[(lineNum-1)] = intArr;
			}
			lineNum++;
		}	
		sin.close();
		
		//traverse(startNodeNum);
		//--------------------		
		
		ArrayList<Integer> stack = new ArrayList<Integer>();
		int curNode = startNodeNum;
		finalState = false;
		
		stack.add(curNode);
		while (curNode != endNodeNum || stack.size() > 0 ){
			if(curNode == endNodeNum){
				finalState = true;
				break;
			}
						
			if(!visited[curNode]){
				//
				//manualAdd(stack, nodeRef[curNode]);
				if(nodeRef[curNode].length > 0){
					for(int ix : nodeRef[curNode]){
						stack.add(ix);
					}
				//
				}else{
					stack.remove( stack.size()-1 );
				}
				visited[curNode]= true;
			}else{
				stack.remove( stack.size()-1 );
			}
			if(stack.size() == 0){
				break;
			}
			
			curNode = stack.get(stack.size()-1);
			//System.out.println(curNode+" -"+ stack);
		}
		
		
		
		

		
		
		//--------------
		String ret = (finalState)? "yes" : "no";
		
		System.out.println("s = "+startNodeNum+"; t = "+endNodeNum+"; "+ret );
		System.out.println("TimeTaken:"+(System.nanoTime() - startTime)/1000000);
	}
	private static void traverse(int n){
		
	}
}
