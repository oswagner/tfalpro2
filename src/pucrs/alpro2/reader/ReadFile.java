package pucrs.alpro2.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import pucrs.alpro2.core.Acidente;
import pucrs.alpro2.structure.AcidentesDoubleLinkedList;

public class ReadFile {

    private Path path;

    public Path findPath(String possiblePath) {
	this.path = Paths.get(possiblePath);
	return path;
    }

    public AcidentesDoubleLinkedList readFile() {
	AcidentesDoubleLinkedList acidentes = new AcidentesDoubleLinkedList();
	try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
	    String line = "";
	    reader.readLine(); // skip first line;
	    while ((line = reader.readLine()) != null) {

		try {
		    Acidente acidente = new Acidente(line);
		    acidentes.add(acidente);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		// System.out.println(acidentes.toStringForStreet());
		// System.out.println(acidentes.toStringForDate());
	    }
	} catch (IOException e) {

	}
	return acidentes;
    }

}
