/**
* Vergleicht Studenten anhand von Name und Vorname.
*/

public class NameVornameComparator implements java.util.Comparator<StudentIn>{
  /**
   * Extrahiert den vollen Namen aus dem Objekt, wenn es sich um
   * ein Objekt der Klasse StudentIn handelt.
   */
  private String extractFullName(StudentIn std) {
	  return std.getName() + " " + std.getVorname();
  }

  /** Vergleicht StudentIn a mit StudentIn b
  * liefert:    -1  a ist vor b im Alphabet
  *             0   a ist gleich b
  *             1   b ist vor a im Alphabet */
  public int compare(StudentIn a, StudentIn b) {
	  return signum(extractFullName(a),  extractFullName(b));
  }

  /** Vergleicht die Strings miteinander. */
  private static final int signum (String a, String b) {
    if (a.compareTo(b)<0)
      return -1;
    else if (a.compareTo(b)>0)
      return 1;
    else return 0;
  }
}
