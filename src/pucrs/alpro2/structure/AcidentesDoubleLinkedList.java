package pucrs.alpro2.structure;

import java.util.HashMap;
import java.util.Map;

import pucrs.alpro2.core.Acidente;

public class AcidentesDoubleLinkedList implements MyLinkedList {

    private Node header;
    private Node trailer;
    private int count;
    public Map<String, Node> streetControl = new HashMap<>();

    // Construtor
    public AcidentesDoubleLinkedList() {
	header = new Node(null);
	trailer = new Node(null);
	header.nextStreet = trailer;
	trailer.prevStreet = header;
	header.nextDate = trailer;
	trailer.prevDate = header;
	count = 0;
    }

    private class Node {
	public Acidente element;
	public Node nextStreet;
	public Node prevStreet;
	public Node nextDate;
	public Node prevDate;

	public Node(Acidente e) {
	    element = e;
	    prevStreet = nextStreet = null;
	    prevDate = nextDate = null;
	}
    }

    @Override
    public void add(Acidente acidente) {
	boolean key = streetControl.containsKey(acidente.getLogradouro());

	Node newNode = new Node(acidente);

	if (!key) {
	    // nova rua e 0 elementoss
	    if (count == 0) {
		addFirstStreet(acidente, newNode);
		addFirstDate(acidente, newNode);
	    }

	    if (count == 1) {

		// nova rua e 1 elemento e add antes
		if (acidente.getLogradouro().compareToIgnoreCase(header.nextStreet.element.getLogradouro()) < 0)
		    addFirstStreet(acidente, newNode);

		// nova rua e 1 elemento e add depois por rua
		if (acidente.getLogradouro().compareToIgnoreCase(header.nextStreet.element.getLogradouro()) > 0)
		    addLastStreet(acidente, newNode);

		// nova rua e 1 elemento e add antes por data
		if (acidente.getDataHora().before(header.nextDate.element.getDataHora()))
		    addFirstDate(acidente, newNode);

		// nova rua e 1 elemento e add depois por data
		if (acidente.getDataHora().after(header.nextDate.element.getDataHora()))
		    addLastDate(acidente, newNode);
	    }

	    // nova rua e n > 1 para n elementos
	    Node targetStreet = header.nextStreet;
	    Node targetDate = header.nextDate;
	    while (targetStreet != trailer && targetDate != trailer) {
		Acidente elementTargetStreet = targetStreet.element;
		Acidente elementTargetDate = targetDate.element;

		if (acidente.getLogradouro().compareToIgnoreCase(elementTargetStreet.getLogradouro()) < 0) {
		    newNode.prevStreet = targetStreet.prevStreet;
		    newNode.nextStreet = targetStreet;
		    newNode.prevStreet.nextStreet = newNode;
		    targetStreet.prevStreet = newNode;
		    break;
		}

		if (acidente.getDataHora().before(elementTargetDate.getDataHora())) {
		    if(targetDate.prevDate == header){
			addFirstDate(acidente, newNode);
		    }
//		    newNode.prevDate = targetDate.prevDate;
//		    newNode.nextDate = targetDate;
//		    newNode.prevDate.nextDate = newNode;
//		    targetDate.prevDate = newNode;
		    break;
		}

		// nova rua e N elementos e add por ultimo e por rua
		if (acidente.getLogradouro().compareToIgnoreCase(elementTargetStreet.getLogradouro()) > 0
			&& targetStreet.nextStreet == trailer)
		    addLastStreet(acidente, newNode);

		if (acidente.getDataHora().after(elementTargetDate.getDataHora()) && targetDate.nextDate == trailer)
		    addLastDate(acidente, newNode);

		targetStreet = targetStreet.nextStreet;
		targetDate = targetDate.nextDate;
	    }
	    // add new key
	    streetControl.put(acidente.getLogradouro(), newNode);
	} else {

	    Node target = streetControl.get(acidente.getLogradouro());

	    boolean addInMidle = false;

	    if (count == 1) {
		// nova rua e 1 elemento e add antes
		if (acidente.getLogradouro().compareToIgnoreCase(target.element.getLogradouro()) < 0)
		    addFirstStreet(acidente, newNode);

		// nova rua e 1 elemento e add depois por rua
		if (acidente.getLogradouro().compareToIgnoreCase(target.element.getLogradouro()) > 0)
		    addLastStreet(acidente, newNode);

		// nova rua e 1 elemento e add antes por data
		if (acidente.getDataHora().before(target.element.getDataHora()))
		    addFirstDate(acidente, newNode);

		// nova rua e 1 elemento e add depois por data
		if (acidente.getDataHora().after(target.element.getDataHora()))
		    addLastDate(acidente, newNode);

	    }

	    if (target.nextStreet == trailer) {
		addLastStreet(acidente, newNode);
	    }
	    // newNode.nextStreet = target.nextStreet;
	    // newNode.prevStreet = target;
	    // newNode.nextStreet.prevStreet = newNode;
	    // target.nextStreet = newNode;

	    if (acidente.getDataHora().after(target.element.getDataHora())) {
		if (target.nextDate == trailer) {
		    addLastDate(acidente, newNode);
		}	
//		newNode.nextDate = target.nextDate;
//		newNode.prevDate = target;
//		newNode.nextDate.prevDate = newNode;
//		target.nextDate = newNode;
	    }

	    if (acidente.getDataHora().before(target.element.getDataHora())) {
		target.prevDate.nextDate = newNode;
		newNode.prevDate = target.prevDate;
		newNode.nextDate = target;
		target.prevDate = newNode;
	    }
	}
	// total de elementos
	count++;
    }

    private void addFirstStreet(Acidente acidente, Node newNode) {
	newNode.prevStreet = header;
	newNode.nextStreet = header.nextStreet;
	header.nextStreet.prevStreet = newNode;
	header.nextStreet = newNode;
    }

    private void addLastStreet(Acidente acidente, Node newNode) {
	newNode.nextStreet = trailer;
	newNode.prevStreet = trailer.prevStreet;
	newNode.prevStreet.nextStreet = newNode;
	trailer.prevStreet = newNode;
    }

    private void addFirstDate(Acidente acidente, Node newNode) {
	newNode.prevDate = header;
	newNode.nextDate = header.nextDate;
	header.nextDate.prevDate = newNode;
	header.nextDate = newNode;
    }

    private void addLastDate(Acidente acidente, Node newNode) {
	newNode.nextDate = trailer;
	newNode.prevDate = trailer.prevDate;
	newNode.prevDate.nextDate = newNode;
	trailer.prevDate = newNode;
    }

    @Override
    public void add(int index, Acidente element) {
	// TODO Auto-generated method stub

    }

    @Override
    public Acidente get(int index) {
	if ((index < 0) || (index >= count))
	    throw new IndexOutOfBoundsException("Pos. inválida: " + index);

	if (index < (count / 2)) {
	    Node target = header.nextStreet;
	    int flag = 0;
	    while (flag < index) {
		target = target.nextStreet;
		flag++;
	    }
	    return target.element;
	} else {
	    Node target = trailer.prevStreet;
	    int flag = count - 1;
	    while (flag > index) {
		target = target.prevStreet;
		flag--;
	    }
	    return target.element;
	}
    }

    @Override
    public int indexOf(Acidente e) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void set(int index, Acidente element) {
	// TODO Auto-generated method stub

    }

    @Override
    public boolean remove(Acidente e) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public Acidente remove(int index) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public int size() {
	return count;
    }

    @Override
    public boolean contains(Acidente e) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void clear() {
	// TODO Auto-generated method stub
    }

    public String toStringForDate() {
	String ret = "";
	if (count == 0) {
	    return ret = "Acidentes Por Data = { }";
	} else {
	    Node target = header.nextDate;
	    ret += "Acidentes Por Data = {";
	    while (target != trailer) {
		ret += target.element.getLogradouro() + "[" + target.element.getDataHora() + "]" + ", ";
		target = target.nextDate;
	    }
	    ret += "}";
	    return ret;
	}
    }

    public String toStringForStreet() {
	String ret = "";
	if (count == 0) {
	    return ret = "Acidentes Por Rua = { }";
	} else {
	    Node target = header.nextStreet;
	    ret += "Acidentes Por Rua = {";
	    while (target != trailer) {
		ret += "," + target.element.getLogradouro();
		target = target.nextStreet;
	    }
	    ret += "}";
	    return ret;
	}

    }
}
