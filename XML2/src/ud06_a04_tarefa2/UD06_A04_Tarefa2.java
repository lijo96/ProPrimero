/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud06_a04_tarefa2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class UD06_A04_Tarefa2 {

    public static void main(String[] args) {
        String nomeArquivo = "empregados.xml";
        String nome = "";
        boolean maior = false;

        int cantidade;
        int numE = 0;

        cantidade = lerInt("Introduza o valor do salario: ");
        System.out.println("Os empregados cun salario superior a " + cantidade 
                + " son:");
        
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
                        
                        //Comprobamos se o salario do empregado é superior á
                        //cantidade introducida polo usuairo
                        if (elemento.equalsIgnoreCase("empregado")) {
                            Attribute atributo  = inicio.getAttributeByName(
                                    QName.valueOf("salario"));
                            maior = Integer.valueOf(
                                    atributo.getValue()) > cantidade;   
                            
                        } else if (elemento.equalsIgnoreCase("nome")) {
                            //Gardamos o valor do nome do empregado por se
                            //necesitamos imprimilo
                            nome = reader.getElementText();
                        } 
                        break;
                    case XMLStreamConstants.END_ELEMENT: 
                        EndElement fin = evento.asEndElement();
                        elemento = fin.getName().getLocalPart();
                        //Ao chegar ao final da lectura do elemento empregado
                        //e en caso de que detectáramos que o seu salario é
                        //superior á cantidade indicada, imprimimos o nome
                        if (elemento.equalsIgnoreCase("empregado") && maior) {
                            System.out.println("\t" + nome);
                            //Contamos que hai un empregado que cumpre a condición
                            numE++;
                        }
                        break;
                }
            }
//            if (numE == 0) {
//                System.out.println("\tNon hai ningún empregado que cumpra "
//                    + "coa condición");
//            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Problemas coa lectura do arquivo");
        } catch (XMLStreamException ex) {
            System.out.println("ERRO: Problemas co XML");
        }
        
    }  
    
    public static int lerInt(String cadea) {
        int valor = 0;
        boolean repetir;
        Scanner sc = new Scanner(System.in);
        
        System.out.print(cadea);
        repetir = true;
        while (repetir) {
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                repetir = (valor < 1);
            }  else {
                sc.next(); 
            }
            if (repetir) {
                System.out.print("\tERRO: debe introducir un valor numérico. "
                        + "\n\t\tVolva a introducir un número: ");
            }
        }
        return valor;
    }
}