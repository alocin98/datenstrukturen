/**
* Vergleicht Studenten anhand von Name und Vorname.
*/

public class NameVornameComparator implements java.util.Comparator<StudentIn>{
  /**
  * liefert:    -1  a ist vor b im Alphabet
  *             0   a ist gleich b
  *             1   b ist vor a im Alphabet
  * --> Name vor Vorname, also z.b Mueller Nicolas
  */
  public int compare(StudentIn a, StudentIn b) {
    return compareHelper(extractNames(a), extractNames(b));
  }

  private int compareHelper(String a, String b){
    if(a.length() == 0 && b.length() == 0) return 0;

    int integer_a = a.charAt(0);
    int integer_b = b.charAt(0);

    if(integer_a < integer_b) return -1;
    if(integer_a > integer_b) return 1;

    return compareHelper(a.substring(1), b.substring(1));
  }

  private String extractNames(StudentIn std){
    return std.getName().concat(std.getVorname()).trim().toLowerCase();
  }
}
