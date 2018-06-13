/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud06_a04_tarefa1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class UD06_A04_Tarefa1 {
    public static void main(String[] args) {
        String nomeArquivo = "empregados.xml";
        String nome = "";
        String dni = "";
        int idade = 0;
        String departamento = "";
        ArrayList<Empregado> empregados = new ArrayList<>();
        try {
            //Creación do fluxo de entrada ao documento XML a ler
            FileInputStream in = new FileInputStream(nomeArquivo);
            //Creación dunha nova factoría
            XMLInputFactory factory = XMLInputFactory.newInstance();
            //Creación do iterador de eventos
            XMLEventReader reader = factory.createXMLEventReader(in);
            //Recorrido polos eventos mediante o iterador

            while (reader.hasNext()) {
                String elemento;
                XMLEvent evento = reader.nextEvent();

                switch(evento.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement inicio = evento.asStartElement();
                        elemento = inicio.getName().getLocalPart();
                    
                        if (elemento.equalsIgnoreCase("nome")) {
                            nome = reader.getElementText();
                        } else if (elemento.equalsIgnoreCase("dni")) {
                            dni = reader.getElementText();
                        } else if (elemento.equalsIgnoreCase("idade")) {
                            idade = Integer.valueOf(reader.getElementText());
                        } else if (elemento.equalsIgnoreCase("departamento")) {
                            departamento = reader.getElementText();
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT: 
                        EndElement fin = evento.asEndElement();
                        elemento = fin.getName().getLocalPart();
                        //Cando se chegue a etiqueta de peche de empregado
                        //significa que xa se leron todos os datos, co que se 
                        //pode crear o obxecto e almacenalo na lista
                        if (elemento.equalsIgnoreCase("empregado")) {
                            empregados.add(
                                    new Empregado(nome, dni, idade, departamento));
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Problemas coa lectura do arquivo");
        } catch (XMLStreamException ex) {
            System.out.println("ERRO: Problemas co XML");
        }
        
        if (!empregados.isEmpty()) {
            for (Empregado em : empregados) {
                System.out.println(em);
            }
        }
    }
}