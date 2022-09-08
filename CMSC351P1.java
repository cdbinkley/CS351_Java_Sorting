package cmsc351.p1;

/**
 * @author Chris Binkley
 * @UID 110663829
 * @date 2011 November 26
 * @class CMSC351
 */

import java.io.*;
import java.*;
import java.util.*;
import java.util.Arrays;

public class CMSC351P1 {
  private int comps;
  private int myColSize = 5;
  private int myColMed = 1;
	public void resetComps() {comps=0;}
	public int getComps() {return comps;}
  
  /* Implement Select(Comparable[] list, int pos, int colSize, int colMed) in CMSC351P1.java.
      list: list of values of which to find a specified position
      pos: the specified position
      colSize: the size of the "columns" that we create in the first stage
      colMed: the position in those "columns" that we use as the "MedX"
  */
  
  public Comparable Select (Comparable[] TheList, int pos, int colSize, int colMed) {
    if(colSize < 5)
      myColSize = colSize = 5;
    else
      myColSize = colSize;

    if(colMed > colSize)
      myColMed = colSize-1;
    else
      myColMed = colMed;
    
    if( TheList.length <= Math.pow(colSize, 2.0) ) {
      selectionSort(TheList);
      return TheList[pos-1]; //pos is 1 based
    } else {
      return SelectHelper(TheList, pos-1); // converts to 0 based index
    }    
    
	} // end Select

  private Comparable SelectHelper(Comparable[] list, int pos) {
    
    int length = list.length;

    if(list.length <= Math.pow(myColSize, 2.0) ) {
//      System.out.println("position: " + pos + " of " + (list.length-1));
//      System.out.print("unsort: ");
//      PrintTheList(list);
      selectionSort(list);
//      System.out.print("sorted: ");
//      PrintTheList(list);
      return list[pos];
    } else {  
//      testing with random pivot; aka, checking that ChoosePivot is not the problem
//      Comparable pivot_element = list[(int)Math.ceil(Math.random() * (length-1))];
//      int pivot_rank = Partition(list, pivot_element)    
      Comparable pivot_element = ChoosePivot(list);
      int pivot_rank = Partition(list, pivot_element);
//      System.out.println("pos: " + pos + " pivot rank: " + pivot_rank + " pivot vlaue: " + pivot_element);
      
      if(pos == pivot_rank) {
        return pivot_element;
      } else if(pos < pivot_rank) { //left subarray
        int size = pivot_rank;
        Comparable[] sublist = new Comparable[size];
        int j = 0;
        for(int i = 0; i < pivot_rank; i++) {
          sublist[j] = list[i];
          j++;
        }
        return SelectHelper(sublist, pos);
        
      } else { //right subarray
        int size = (length-1) - pivot_rank;
        Comparable[] sublist = new Comparable[size];
        int j = 0;
        for(int i = (pivot_rank+1); i < length; i++) {
          sublist[j] = list[i];
          j++;
        }
        return SelectHelper(sublist, pos - pivot_rank - 1);
      }
    }
  }
  
  private Comparable ChoosePivot(Comparable[] list) {
    
    if(list.length <= Math.pow(myColSize, 2.0)) {
      selectionSort(list);
      if(myColMed >= list.length)
        return list[0];
      else
        return list[myColMed]; 

    } else {
      int numCol = (int)Math.ceil((double) list.length / myColSize);
      
      Comparable[] columnMedians = new Comparable[numCol];
      
      int indexL = 0; //index of the list passed in
      int indexCM = 0; //index of the list of medians
      
      for(int i = 0; i < numCol-1; i++) {
        Comparable[] subarray = new Comparable[myColSize];
        for(int j = 0; j < myColSize; j++) {
          subarray[j] = list[indexL];
          indexL++;
        }
        selectionSort(subarray);
        if(myColMed >= subarray.length)
          columnMedians[indexCM] = subarray[0];
        else
          columnMedians[indexCM] = subarray[myColMed];
        indexCM++;
      }
      
      int smallSize = list.length - indexL;
      Comparable[] subarray = new Comparable[smallSize];
      for(int j = 0; j < smallSize; j++) {
        subarray[j] = list[indexL];
        indexL++;
      }
      selectionSort(subarray);
    
      if(myColMed >= subarray.length)
        columnMedians[indexCM] = subarray[0];
      else
        columnMedians[indexCM] = subarray[myColMed];
 
//System.out.println("Column Medians: " + columnMedians.length); 
//System.out.println("\nCMs: vv");
//PrintTheList(columnMedians);
//System.out.println("CMs: ^^\n");
      
      return ChoosePivot(columnMedians);
    }
  }
  
  private int Partition(Comparable[] list, Comparable element) {
    Comparable[] copy = new Comparable[list.length];
    int start = 0;
    int end = list.length-1;
    int medCount = 0;
//    System.out.println("element: " + element);
    
    for(int i = 0; i < list.length; i++) {
      comps++;
      if(list[i].compareTo(element) < 0) {
        copy[start] = list[i];
//        System.out.println("i: " + i + " list[i]: " + list[i] 
//                + "  start: " + start + " copy[start]: " + copy[start] 
//                + " comparison (" + list[i].compareTo(element) + ")");
        start++;
      } else if (list[i].compareTo(element) > 0){
        copy[end] = list[i];
//        System.out.println("i: " + i + " list[i]: " + list[i]
//                + "  end: " + end + " copy[end]: "+ copy[end] 
//                + " comparison (" + list[i].compareTo(element) + ")");
        end--;
      } else { //if (list[i].compareTo(element) == 0)
        medCount++;
      }
    }
    
    for(int i = 0; i < medCount; i++) {
      copy[start] = element;
      start++;
    }
    
//    System.out.print("after sorting smaller and larger about the value " + element + "\n        ");
//    PrintTheList(copy);
    
    for(int i = 0; i < list.length; i++) {
      list[i] = copy[i];
    }
    
//System.out.println("Pivot position: " + start + " among " + list.length + " elements.");
//    System.out.println("returning start-1: " + (start-1));
    return (start-1);
  }
  
  public void selectionSort (Comparable[] x) {
    int first = 0;
    int last = x.length;
//System.out.println("selectionSort: first (" + first + "), last (" + last + "), length (" + x.length + ")");
    for (int i = first; i < last-1; i++) {
      for (int j = i+1; j < last; j++) {
        comps++;
        if ( x[i].compareTo(x[j]) > 0 ) {
          //... Exchange elements
          Comparable temp = x[i];
          x[i] = x[j];
          x[j] = temp;
        }
      }
    }
  }
      
  // debugging method
  private static void PrintTheList(Comparable[] list) {
    int n = list.length;
//    Comparable[] copy = new Comparable[n];
//    
//    System.out.println("Unsorted: ");
    for (int i=0; i<n; i++) {
      System.out.print(list[i]); 
      if (i!=(n-1)) System.out.print(", ");
      else System.out.println();
    }
    
//    for(int i = 0; i < n; i++) { copy[i] = list[i]; }
//    Arrays.sort(copy);
//    
//    System.out.println("Sorted: ");
//    for (int i=0; i<n; i++) {
//      System.out.print(copy[i]);
//      if (i!=(n-1)) System.out.print(", ");
//      else System.out.println();
//    }
  }
} // end class
