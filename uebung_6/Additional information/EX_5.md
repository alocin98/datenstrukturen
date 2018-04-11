Vorschlag für Speichernutzung innerhalb der Tabelle:
-Man könnte die Überläufer in einem freien Tabellenplatz abspeichern und dann
mit einem Zeiger darauf verweisen. Somit müsste kein Speicher ausserhalb der Tabelle genutzt werden.

Einfach/Doppelte Verkettung
-Um die Laufzeit O(1) zu erhalten, muss die Liste doppelt verkettet sein. So
kann bei der Suche direkt auf das vorangehende Element zugegriffen werden und
so in Laufzeit O(1) realisiert werden. (Z.b bei einer binären Suche)
