package pucrs.alpro2.core;

import pucrs.alpro2.reader.ReadFile;
import pucrs.alpro2.structure.AcidentesDoubleLinkedList;

public class App {

    public static void main(String[] args) {

	ReadFile rf = new ReadFile();

	long init = System.currentTimeMillis();
	System.out.println("Init: " + init);
	rf.findPath("/Users/wagneros/Desktop/arquivo.csv");

	AcidentesDoubleLinkedList l = rf.readFile();

	long end = System.currentTimeMillis();

	System.out.println("End: " + end);
	System.out.println("Diff : " + (end - init));
	System.out.println("Itens add: " + l.size());

	System.out.println(l.toStringForStreet());
	System.out.println(l.toStringForDate());

	// String keys = "";
	// for (String key : l.streetControl.keySet()) {
	// keys += key + ", ";
	// }
	// System.out.println("Keys = { " + keys + "}");

    }
}
