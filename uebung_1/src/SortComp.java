import java.util.*;

public class SortComp{

  static Timer time = new Timer();

  public static void main (String[] args){
    int[] elements = {2000, 5000, 8000, 10000, 20000, 50000, 80000, 100000,
                      200000, 500000, 800000};
    for(int i = 0; i <= 10; i++){
      System.out.println("Test " + i + " insertionSort with " + elements[i] +
      " elements took " + insertionTime(elements[i]) + "ms");
      System.out.println("Test " + i + " MergeSort with " + elements[i] +
      " elements took " + mergeTime(elements[i]) + "ms\n");
    }
  }

  private static int[] createArray(int length){
    Random random = new Random();
    int[] array = new int[length];
    for(int i = 0; i < array.length; i++){
      array[i] = random.nextInt(100) + 1;
    }
    return array;
  }

  private static long insertionTime(int length){
    int[] array = createArray(length);
    time.reset();
    Sorting.insertionSort(array);
    return time.timeElapsed();
  }

  private static long mergeTime(int length){
    int[] array = createArray(length);
    time.reset();
    Sorting.mergeSort(array, 0, array.length-1);
    return time.timeElapsed();
  }
}
