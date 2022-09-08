package cmsc351.p1;
import java.io.*;
import java.*;
import java.util.Scanner;  //for input
import java.util.Arrays;

public class StringTest {
	private static Scanner scanner = new Scanner( System.in );

	private static boolean VerifyMedian(Comparable[] list, int n, Comparable med) {
		Comparable[] templist = new Comparable[n];
		for (int i=0; i<n; i++) {
			templist[i] = list[i];
		}
		Arrays.sort(templist);
		/* Just in case you need to do a little debugging...*/
		//PrintTheList(templist);
		/*System.out.println(
				n
				+ " " 
				+ (int)(Math.ceil(n/2.0))
				+ " " 
				+ templist[(int)(Math.ceil(n/2.0))]
		);*/
		//*/
		System.out.println("This is the median: "+templist[(int)(Math.ceil(n/2.0)) - 1]);
		return (templist[(int)(Math.ceil(n/2.0)) - 1].compareTo(med)==0);
	}

	private static void PrintTheList(Comparable[] list) {
		int n=list.length;
		for (int i=0; i<n; i++) {
			System.out.print(list[i]);
			if (i!=(n-1)) System.out.print(", ");
		}
		System.out.println();
	}

	public static void main(String [] args) throws Exception {
		Comparable med;

		//System.out.print("Column size? ");
		int colSize = 8; //Integer.parseInt(scanner.nextLine());
		//System.out.print("Position in Column? ");
		int colMed = 8; //Integer.parseInt(scanner.nextLine());

		System.out.println("PRINT THE RECURRENCE RELATION T(n)=T(??)+T(??)+O(n) HERE WITH THE ?? REPLACED WITH THE RIGHT VALS");

		//System.out.print("How many elements? ");
		//int n = Integer.parseInt(scanner.nextLine());
    //Scanner in = new Scanner(StringTest.class.getResourceAsStream("string_test_10000.txt"));
		//BufferedReader bf = new BufferedReader(new FileReader(new File("string_test_10000.txt")));
    
    BufferedReader bf = new BufferedReader(new FileReader(
            new File(StringTest.class.getResource("string_test_10000.txt").toString().substring(5))));

		Comparable listToFindMedian[] = new Comparable[10000];
		String line = "";
		int i=0;
		while((line = bf.readLine())!= null){
			listToFindMedian[i++] = line;			
		}
		System.out.println(i);
		CMSC351P1 YourSelectionObject = new CMSC351P1();
		
			
		YourSelectionObject.resetComps();
		med = YourSelectionObject.Select(
				listToFindMedian, 
				(int)(Math.ceil(listToFindMedian.length/2.0)),
				colSize, colMed
		);
		System.out.println(
				"n=" + listToFindMedian.length + "  " +
				"Comparisons=" +
				YourSelectionObject.getComps()
		);
		
		if (VerifyMedian(listToFindMedian,listToFindMedian.length,med)) {
			System.out.println("Median!");
		}
		else {
			System.out.println("You returned: " + med + " which is not the median :(");
		}
		
		YourSelectionObject.selectionSort(listToFindMedian);
		System.out.println("Sorted input strings");
		for (i=0; i<listToFindMedian.length; i++) {
			//System.out.println(listToFindMedian[i]);
			//System.out.print("Enter value #"+(i+1)+": ");
			//listToFindMedian[i] = 
				//scanner.nextLine();
		}
	} //end main
}

