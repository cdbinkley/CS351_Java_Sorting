package cmsc351.p1;

/**
 * @author UMD CS department and Chris Binkley
 * @date 2011 November 26
 */

import cmsc351.p1.CMSC351P1;
import java.io.*;
import java.*;
import java.util.Scanner;  //for input
import java.util.Arrays;

public class P1driver {
    private static Scanner scanner = new Scanner( System.in );

    private static boolean VerifyMedian(Comparable[] list, int n, Comparable med) {
        Comparable[] templist = new Comparable[n];
        for (int i=0; i<n; i++) {
                templist[i] = list[i];
        }
        Arrays.sort(templist);
        /* Just in case you need to do a little debugging... */
        PrintTheList(templist);
        System.out.println(
                n + " " + (int)(Math.ceil(n/2.0))
                + " " + templist[(int)(Math.ceil(n/2.0))]
        );
        
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

	//System.out.print("Column size? (colSize) ");
	int colSize = Integer.parseInt(scanner.nextLine());
	//System.out.print("Position in Column? (colMed) ");
	int colMed = Integer.parseInt(scanner.nextLine());

	System.out.println("RECURRENCE RELATION: T(n)=T(n/colSize)+T(colMed*n/colSize)+O(n)");

	//System.out.print("How many elements? ");
	int n = Integer.parseInt(scanner.nextLine());

	Integer listToFindMedian[] = new Integer[n];

	for (int i=0; i<n; i++) {
            System.out.print("Enter value #"+(i+1)+": ");
            listToFindMedian[i] = 
			new Integer(Integer.parseInt(scanner.nextLine()));
	}
        /*
        Integer listToFindMedian[] = {14, 57, 24, 6, 37, 32, 2, 43, 30, 25, 23, 
            52, 12, 63, 3, 5, 44, 17, 34, 64, 10, 27, 48, 8, 19, 60, 21, 1, 
            55, 41, 29, 11, 58, 39};
        
        n = 34;
        */
	CMSC351P1 YourSelectionObject = new CMSC351P1();	
	YourSelectionObject.resetComps();
        
	med = YourSelectionObject.Select(
                listToFindMedian, (int)(Math.ceil(n/2.0)), colSize, colMed);
        
	System.out.println(
            "n=" + n + "  " + "Comparisons=" + YourSelectionObject.getComps() );
        
	if (VerifyMedian(listToFindMedian,n,med)) {
            System.out.println("Median!");
	} else {
            System.out.println("You returned: " + med + " which is not the median :(");
	}
    } //end main
}
