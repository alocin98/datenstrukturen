import java.util.*;

public class SortComp{
//hallo
  public static void main (String[] args){
    System.out.println("Array: " + Arrays.toString(createArray(100)));
  }

  private static int[] createArray(int length){
    Random random = new Random();
    int[] array = new int[length];
    for(int i = 0; i < array.length; i++){
      array[i] = random.nextInt(length);
    }
    return array;
  }



}
