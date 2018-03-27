#Theoretische Aufgaben

##Aufgabe 1

function turnlist(list)
  object previous = list.get(0) //erstes element der liste
  object current = list.get(1)  //Vom zweiten Element der liste...
  while(current.hasNext)        //...durchiterieren bis zum letzten
    object tmp = current.next   //nächstes Element zwischenspeichern
    current.next = previous     //zeiger umkehren
    previous = current          //weitergehen mit derm zeiger
    current = tmp               
-----------------------------------------------

##Aufgabe 2

''''
  new List queue
  ListObject head;
  ListObject tail;

  ENQUEUE(ListObject obj)
    ListObject tmp;
    tail = tmp;
    tail = obj;
    obj.next = tmp;

  DEQUEUE()
    if(queue.isEmpty())
      throw EmptyQueueExcepton
    if(head = tail)
      Object tmp = tail
      head = null
      tail = null
      return tmp
    if()
    Object returnObject = tail
    Object newTail = head.next
    while(newTail.next.hasNext())
      newTail = newTail.next;
    tail = newTail;
    tail.next = null;
    return returnObject;
-------------------------------------------

##Aufgabe 3


''''
  MERGE(list1, list2)
    object_one = list1(0)
    object_two = list2(0)
    object tmp

    for(0 to (sum of list1 and list2))
      if(object_one < object_two)
        tmp = object_one.next
        object_one.next = object_two
        object_one = tmp
      else
        tmp = object_two.next
        object_two.next = object_one
        object_two = tmp

  Der Code aus Seite 32 hat eine quadratische Laufzeit bei zyklischen Listen,
  da der Code zwei mal durch die Listen iterieren muss, um das Ende der jeweiligen
  Liste zu bemerken.
