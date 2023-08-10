
import java.util.LinkedList;
import java.util.Scanner;

public class Deadlock {
	public static boolean compare(int row[],int available[]) {
		for(int i=0; i<row.length; i++) {
			if(row[i] > available[i]) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of processes: ");
		int processes = input.nextInt();						
		System.out.println("Processes: "+processes);
		
		System.out.print("Enter the number of resources (A,B,......,Z): ");
		int resources = input.nextInt();						
		System.out.println("Resources : "+resources);	

		

		int allocMatrix[][] = new int[processes][resources];
		int reqMatrix[][] = new int[processes][resources];
		int resoursescount[] = new int[resources]; //TOTAL RESOURCES QUANTITY
		int available[] = new int[resources];
		boolean finish[] = new boolean[processes];
		boolean deadlockVar=false;
		LinkedList<String> order = new LinkedList<String>();
		
		
		//process finished;
		for(int i=0; i<processes; i++) {
			finish[i] = false;
		}
		
		
		

		//Allocation Matrix
		for (int i=0; i<processes; i++) {
			for(int j=0; j<resources; j++) {
				System.out.print("Enter Allocation for process "+i+", Resource "+j+" : ");
				allocMatrix[i][j] = input.nextInt();
			}
		}
		
		//Need Matrix
		for (int i=0; i<processes; i++) {
			for(int j=0; j<resources; j++) {
				System.out.print("Enter Requested Resource "+j+" for process "+i+";");
				reqMatrix[i][j] = input.nextInt();
			}
		}
		
		
		
		//Total Resources 
		for (int i=0; i<resources; i++) {
			System.out.print("Enter the number of Resource R"+i+":");
			resoursescount[i]=input.nextInt();
		}
		
		//available resources=all resources
		for(int i=0; i<resoursescount.length; i++) {
			available[i] =resoursescount[i];
		}
		
		//subtract alloc from avail 
		for (int i=0; i<processes; i++) {
			for(int j=0; j<resources; j++) {
				if(allocMatrix[i][j] != 0) {
					available[j] -= allocMatrix[i][j];
				}
				
			}
		}
		System.out.println("");
		
		
		//Available
		System.out.println("");
		System.out.println("Available Resources");
		System.out.println("-----------------------------");
		for(int i=0; i<available.length ; i++) {
			System.out.print(available[i]+"  ");
			
		}
		System.out.println("");
		System.out.println("");
		
		
		//Allocation matrix
		System.out.println("Allocation Matrix");
		System.out.println("-----------------------------");
		for (int i=0; i<processes; i++) {
			for(int j=0; j<resources; j++) {
				System.out.print(allocMatrix[i][j]+"  ");
			}
			System.out.println("");
			
		}
		
		System.out.println("");
		//Request matrix
		System.out.println("Request Matrix");
		System.out.println("-----------------------------");
		for (int i=0; i<processes; i++) {
			for(int j=0; j<resources; j++) {
				System.out.print(reqMatrix[i][j]+"  ");
			}
			System.out.println("");
			
		}
		
		int row[]= new int[resources];
				
		
		
		
		
		
		int i=0;
		
		for(int t=0; t<4; t++) {
			i=0;
			while(i<processes) {
				if(!finish[i]) {
					for (int j=0; j<resources; j++) {
						row[j] = reqMatrix[i][j];
					}
					if(compare(row, available)) {

						for(int z=0; z<resources; z++) {
							available[z] += allocMatrix[i][z];
						}
						
						finish[i % processes] = true;
						order.add("P"+Integer.toString(i));
					}
					
				}
				i++;
			}//while
		}
		
		
		for(i=0; i<finish.length; i++) {
			if(!finish[i]) {
				deadlockVar=true;
			}
		}
		if(!deadlockVar) {
			System.out.println("");
			System.out.println("-----------------------");
			System.out.println("Deadlock not Detected");
			System.out.println("-----------------------");
			System.out.println("System is in Safe State");
			System.out.println("-----------------------");
		}
		else {
			System.out.println("");
			System.out.println("-----------------------");
			System.out.println("Deadlock Detected");
			System.out.println("-----------------------");
			System.out.println("System is not in Safe State");
			System.out.println("-----------------------");
		}
		System.out.println("");
		System.out.println("Process Execution Order");
		System.out.println("-----------------------");
		System.out.println(order);
		
		

		
	}

}