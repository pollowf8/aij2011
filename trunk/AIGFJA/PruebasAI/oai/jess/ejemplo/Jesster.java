package oai.jess.ejemplo;


import jess.*;
import java.io.*;


public class Jesster
{     // The inference engine
      private static Rete m_rete;
      //  Fichero que almacena el fuente del programa clips que vamos a cargar
      private static String programaFuente;
      //  Constructor
      public Jesster()
      {     m_rete = new Rete();
            programaFuente = "wordgame.clp";
            System.out.println("Cargando programa  " + programaFuente + "  ");
            cargaPrograma(programaFuente);
            
             }
      public static void main(String[] args)
      {     
    	 
            Jesster jesso;
            jesso = new Jesster();
            reset();
            run();
            listaHechos();
            halt();
      }

// obtiene e imprime la lista de hechos
     public static void listaHechos()
      {
        java.util.Iterator iterador;  // java.util.Iterator
        iterador= m_rete.listFacts();
        while (iterador.hasNext()) {
          System.out.println(iterador.next());
        } }
      public static void halt()
      {
        try
         { m_rete.halt(); }
        catch (JessException je3)
         { System.out.println("Error: no puedo detener programa "); }
      }

      public static void cargaPrograma(String nombre)
      {
        try
           {   
//        	m_rete.eval("(batch \"" + nombre + "\")");  }
        	m_rete.batch(nombre);
          }
        catch(JessException je0)
             {
               System.out.println("Error: no puedo leer programa " + nombre);
               je0.printStackTrace();
            }
      }

      public static void reset()
      {
               try
                   {   m_rete.reset();
                   }
                   catch(JessException je2)
                   {   System.out.println("Error: no puedo resetear ");
                      
                        je2.printStackTrace();
                   }
      }

      public static void run()
      {
                   try
                   {   m_rete.run();
                   }
                   catch(JessException je4)
                   {   System.out.println("Error: no puedo ejecutar ");

                      
                      je4.printStackTrace();
                  }
      }
}

