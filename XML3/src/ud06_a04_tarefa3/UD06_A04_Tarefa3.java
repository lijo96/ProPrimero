/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud06_a04_tarefa3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class UD06_A04_Tarefa3 {
    public static void main(String[] args) {
        String nomeArquivo = "instituto.xml";
        StartElement elementoI;
        EndElement elementoF;
        Characters texto;
        byte engadir;

        try {
            //Creación do fluxo de saída ao documento XML a crear
            FileOutputStream out = new FileOutputStream(nomeArquivo);
            //Creación dunha nova factoría
            XMLOutputFactory factoria = XMLOutputFactory.newInstance();
            //Creación o escritor de eventos
            XMLEventWriter writer = factoria.createXMLEventWriter(out);
            //Creación da factoría de eventos
            XMLEventFactory eventosF = XMLEventFactory.newInstance();
            //Creación da etiqueta de inicio do documento
            StartDocument inicioDoc = eventosF.createStartDocument();
            //Engadir a etiqueta de inicio ao documento
            writer.add(inicioDoc);

            //Creamos os eventos de salto de liña e tabulador
            XMLEvent saltoL = eventosF.createDTD("\n");
            XMLEvent tabulador = eventosF.createDTD("\t");

            //Engadimos o salto de liña
            writer.add(saltoL);

            //Crear a etiqueta de apertura <instituto>
            elementoI = eventosF.createStartElement("", "", "instituto");
            //Engadir a etiqueta creada ao documento
            writer.add(elementoI);
            writer.add(saltoL);

            //Creamos cada un dos elementos que contén <alumno>
            engadir = lerByte("Desexa introducir un alumno no documento XML? "
                    + "[0-Non 1-Si]: ");
            while (engadir != 0 && engadir != 1) {
                System.out.println("\tERRO: só opción 0 ou 1."
                        + "Volva a introducir [0-Non 1-Si]:");
                engadir = lerByte("Desexa introducir un alumno no documento XML? "
                        + "[0-Non 1-Si]: ");
            }
            while (engadir == 1) {
                //Creamos a etiqueta de apertura de alumno
                writer.add(tabulador);
                elementoI = eventosF.createStartElement("", "", "alumno");
                //Engadimos a etiqueta de apertura ao documento
                writer.add(elementoI);
                writer.add(saltoL);

                //Lemos os datos que corresponden a un alumno
                String nome = lerString("\tNome: ");
                int idade = lerInt("\tIdade:");
                int curso = lerInt("\tCurso:");
                float nota = lerFloat("\tNota media:");

                //Creamos os elementos dentro de alumno cos datos lidos
                crearElemento(writer, "nome", nome, saltoL, tabulador, 2);        
                crearElemento(writer, "idade", String.valueOf(idade), saltoL, tabulador, 2);
                crearElemento(writer, "curso", String.valueOf(curso), saltoL, tabulador, 2);
                crearElemento(writer, "notamedia", String.valueOf(nota), saltoL, tabulador, 2);

                 //Creamos a etiqueta de peche de alumno
                writer.add(tabulador);
                elementoF = eventosF.createEndElement("", "", "alumno");
                //Engadimos a etiqueta de peche ao documento
                writer.add(elementoF);
                writer.add(saltoL);

                //Volvemos a preguntar se se desexa introducir outro alumno
                engadir = lerByte("Desexa introducir un alumno no documento XML? "
                        + "[0-Non 1-Si]: ");
                while (engadir != 0 && engadir != 1) {
                    System.out.println("\tERRO: só opción 0 ou 1."
                            + "Volva a introducir [0-Non 1-Si]:");
                    engadir = lerByte("Desexa introducir un alumno no documento XML? "
                            + "[0-Non 1-Si]: ");
                }
            }

            //Crear a etiqueta de peche </instituto>
            elementoF = eventosF.createEndElement("", "", "instituto");
            //Engadir a etiqueta de peche ao documento
            writer.add(elementoF);
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Problemas coa lectura do arquivo");
        } catch (XMLStreamException ex) {
            System.out.println("ERRO: Problemas co XML");
        }
    }
    
    private static void crearElemento(XMLEventWriter eventoW, String nome, 
            String valor, XMLEvent salto, XMLEvent tab, int nivel)
            throws XMLStreamException{
        for (int i = 0; i < nivel; i++) {
            eventoW.add(tab);
        }
        XMLEventFactory eventosF = XMLEventFactory.newInstance();
        eventoW.add(eventosF.createStartElement("", "", nome));
        eventoW.add(eventosF.createCharacters(valor));
        eventoW.add(eventosF.createEndElement("", "", nome));
        eventoW.add(salto);
        
    }
    
    private static String lerString(String cadea) {
        String valor = "";
        boolean repetir;
        Scanner sc = new Scanner(System.in);
        
        System.out.print(cadea);
        repetir = true;
        while (repetir) {
            if (sc.hasNextLine()) {
                valor = sc.nextLine();
                repetir = (valor.isEmpty());
            }  else {
                sc.next(); 
            }
            if (repetir) {
                System.out.print("\tERRO: debe introducir algún texto. "
                        + "\n\t\tVolva a introducir o valor: ");
            }
        }
        return valor;
    }
    
    private static int lerInt(String cadea) {
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
                System.out.print("\tERRO: debe introducir un valor numérico positivo. "
                        + "\n\t\tVolva a introducir un número: ");
            }
        }
        return valor;
    }
    
    private static float lerFloat(String cadea) {
        float valor = 0;
        boolean repetir;
        Scanner sc = new Scanner(System.in);
        
        System.out.print(cadea);
        repetir = true;
        while (repetir) {
            if (sc.hasNextFloat()) {
                valor = sc.nextFloat();
                repetir = (valor < 1);
            }  else {
                sc.next(); 
            }
            if (repetir) {
                System.out.print("\tERRO: debe introducir un valor float. "
                        + "\n\t\tVolva a introducir un número: ");
            }
        }
        return valor;
    }
    
    private static byte lerByte(String cadea) {
        byte valor = 0;
        boolean repetir;
        Scanner sc = new Scanner(System.in);
        
        System.out.print(cadea);
        repetir = true;
        while (repetir) {
            if (sc.hasNextFloat()) {
                valor = sc.nextByte();
                repetir = (valor < 0);
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
