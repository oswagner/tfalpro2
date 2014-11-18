package pucrs.alpro2.structure;

import pucrs.alpro2.core.Acidente;

public interface MyLinkedList {

    void add(Acidente e); // insere e no final

    void add(int index, Acidente element); // insere e em determinada posição
					   // (index)

    Acidente get(int index); // obtém elemento na posição index

    int indexOf(Acidente e); // retorna a posiçãoo onde e está

    void set(int index, Acidente element); // altera o elemento na posição index

    boolean remove(Acidente e); // remove o elemento e, se existir

    Acidente remove(int index); // remove o elemento da posição index

    boolean isEmpty(); // retorna true se o array está vazio

    int size(); // retorna o total de elementos armazenados

    boolean contains(Acidente e); // retorna true se e existe no array

    void clear(); // remove todos os elementos

}
