import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static Node[] nodeRef;
	static ArrayList<Node> allNodes = new ArrayList<Node>();
	static int startNodeNum = -1;
	static int endNodeNum = -1;
	static boolean finalState = false;

	public static void main(String[] args) throws FileNotFoundException  {
		//Scanner sin = new Scanner(System.in);
		
		long startTime = System.currentTimeMillis();
		
		Scanner sin = new Scanner(new File("input.txt"));
		
		int lineNum = -1;
		int maxNodes = 0;
		
		while(sin.hasNext()){
			String line = sin.nextLine();
			if(lineNum==-1){
				// start the node making
				lineNum = Integer.parseInt(line);
				maxNodes = Integer.parseInt(line);
				
				nodeRef = new Node[maxNodes];
			} else if(lineNum == 0){
				// reached end of digraph
				startNodeNum = Integer.parseInt(line.split(" ")[0]);
				endNodeNum = Integer.parseInt(line.split(" ")[1]);
				break;
			} else{
				int nodeNum = maxNodes - lineNum;
				Node vs = nodeMaker(nodeNum);
				String[] allchilds = line.split("\\s+");
				if(line.equals("")){
					allchilds = new String[0];
				}
								
				Node child;
				for(int i=0;i<allchilds.length;i++){
					child = nodeMaker(Integer.parseInt(allchilds[i]));
					vs.addChild(child);
				}
				
				lineNum--;
			}
		}	
		
		traverse(nodeRef[startNodeNum]);
		String ret = "no";
		if(finalState)
			ret = "yes";
		
		System.out.println("s = "+nodeRef[startNodeNum].num+"; t = "+nodeRef[endNodeNum].num+"; "+ret );
		System.out.println("TimeTaken:"+(System.currentTimeMillis() - startTime));
	}
	private static void traverse(Node n){
		if(finalState)
			return;
		if(n.state>0)
			return;
		
		n.state++;
		//System.out.println("look:"+n.num);
		if(n.num == endNodeNum){
			finalState = true;
		}else{
			for(int i=0;i<n.childrenlist.size();i++){
				traverse(n.childrenlist.get(i));
			}
		}
	}
	private static Node nodeMaker(int nn){
		Node v;
		if(nodeRef[nn] != null){
			v = nodeRef[nn];
		}else{
			v = new Node(nn);
			nodeRef[nn] = v;
		}
		allNodes.add(v);
		return v;
	}
}
class Node{
	public int num = -1;
	public int state = 0;
	public ArrayList<Node> childrenlist = new ArrayList<Node>();
	public Node(int s){
		num = s;
	}
	public void addChild(Node n){
		childrenlist.add(n);
	}
}
