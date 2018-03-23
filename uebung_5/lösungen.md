Theoretische Aufgaben

Aufgabe 1

function turnlist(list)
  object previous = list.get(0) //erstes element der liste
  object current = list.get(1)  //Vom zweiten Element der liste...
  while(current.hasNext)        //...durchiterieren bis zum letzten
    object tmp = current.next   //nächstes Element zwischenspeichern
    current.next = previous     //zeiger umkehren
    previous = current          //weitergehen mit derm zeiger
    current = tmp               //''

Aufgabe 2

  new List queue
  ListObject head;
  ListObject tail;

  ENQUEUE(ListObject obj)
    ListObject tmp;
    tail = tmp;
    tail = obj;
    obj.next = tmp;

  DEQUEUE()
    ListObject tmp = tail;
    while(tmp.next.hasNext())
      tmp = tmp.next;
    head = tmp;
    tmp = tmp.next;
    head.next = null;
    return tmp;
-------------------------------------------

  Aufgabe 3
