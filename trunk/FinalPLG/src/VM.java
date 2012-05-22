
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class VM {
   private Stack<PValue> pilaEvaluacion;
   private Instruccion[] programa;
   private int cp;
   private ArrayList<Integer> memoria;
   // para una máquina más complicada existirá también una memoria de
   // evaluación. Puede representarse, por ejemplo, mediante un array,
   // o mediante Vector (aunque Vector, como Stack, suponen una carga
   // importante para el programa).
   

  public static abstract class PValue {
   public int asInt() { throw new UnsupportedOperationException("asInt"); } 
   // puede introducirse un nuevo método para cada tipo de valor basico de la
   // maquina P
   public boolean asBoolean() { throw new UnsupportedOperationException("asBoolean"); }
   }
  
// Puede proporcionarse una subclase de PValue para cada tipo de valor basico
// de la máquina P.

  public static class IntPValue extends PValue {
    private int value;
    public IntPValue(int value) {
     this.value = value;    
    }
     public int asInt() {return value;}
     public String toString() {return "<"+value+">";}
   }
  
  public static class BooleanPValue extends PValue {
	    private boolean value;
	    public BooleanPValue(boolean value) {
	     this.value = value;    
	    }
	     public boolean asBoolean() {return value;}
	     public String toString() {return "<"+new Boolean(value).toString()+">";}
	   }
   public VM(String fprograma) {
      try {
    	 memoria = new ArrayList<Integer>();//A�adido
         pilaEvaluacion = new Stack<PValue>();
         List<Instruccion> instrucciones = (List<Instruccion>)new ObjectInputStream(new FileInputStream(fprograma)).readObject();
         programa = new Instruccion[instrucciones.size()];
         int j=0;
         for(Instruccion i: instrucciones) 
             programa[j++] = i;    
         cp = 0;
      }
      catch(Exception e) {
        System.err.println("Error al cargar el programa:"+e);
        System.exit(1);
      }
   }
   public void run(boolean traza) {
      if (traza) System.out.println(pilaEvaluacion); 
      while (cp < programa.length) {
        if (traza) System.out.print(programa[cp]+"=>");  
        programa[cp].ejecuta(this);
        if (traza) System.out.println(pilaEvaluacion); 
      }  
   }
   public void push(PValue value) {
      pilaEvaluacion.push(value); 
   } 
   public PValue pop() {
      return pilaEvaluacion.pop(); 
   }
   public void incCP() {cp++;}
   public void decCP() {cp--;}
   public void setCP(int newCp) {cp=newCp;}
   
   public static void main(String args[]) {
      if (args.length != 1) {
        System.err.println("ERROR - debe indicarse archivo a interpretar");
        System.exit(1);
      }
      VM vm = new VM(args[0]);
      vm.run(true);
   }     
   // a�adido
	public void addValMem(int dir, int val) {
		try {
			memoria.add(dir, val);
		} catch (ArrayIndexOutOfBoundsException e) {
			memoria.ensureCapacity(dir+1);
			memoria.add(dir, val);
		}
	}

	public Integer getValMem(int dir) {
		try {
			Integer a= memoria.get(dir);
			return a;
		} catch (ArrayIndexOutOfBoundsException e) {
			return new Integer(-1);
		}
	}
	public Integer getPrimeraPosLibre() {
		return memoria.size();
	}
	
}
