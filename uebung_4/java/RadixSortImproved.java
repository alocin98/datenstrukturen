import java.util.LinkedList;

public class RadixSortImproved{

  public static void radixSortImproved(char[][] A, int d){

    LinkedList[] queues = new LinkedList[26];

    //Sort the List according to Algorithm used in RadixSort
    A = sortListAfterLengths(A, d);

    for(int j=d-1; j>=0; j--)
		{
			// initialize empty queues
			for(int i=0; i<27; i++) queues[i] = new LinkedList();

			// place each character array in correct queue
			for(int i=0; i<A.length; i++)
			{		// characters 'a'-'z'
					queues[A[i][j]-'a'+1].addLast(A[i]);
				}
			}

  }

  private static char[][] sortListAfterLengths(char[][] A, int d){

    LinkedList[] lengths = new LinkedList[d];
    
	for(int j = 0; j<d; j++) {
      for (int i=0; i<d; i++) lengths[i] = new LinkedList();
      for(int i=0; i<A.length; i++) lengths[A[i].length-1].addLast(A[i]);
    }

    int n = 0;
    for(int i=d-1; i>=0; i--)
    {
      while(lengths[i].size() > 0)
      {
        A[n] = (char[])lengths[i].removeFirst();
        n++;
      }
    }
    
    return A;
  }

}
