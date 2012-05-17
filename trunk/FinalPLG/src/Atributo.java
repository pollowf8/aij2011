
import java.util.Stack;

public class Atributo<Valor> {
   public Atributo()  {
    dependencias = new Stack<Atributo>();
    calculado = false;
    descripcion = "<descripcion de atributo no disponible>";
   } 
   public void fijaExpresion(ExpSem<Valor> exp) {
     this.exp = exp;  
   }
   public void fijaDescripcion(String descripcion) {
     this.descripcion = descripcion;  
   }
   public String descripcion() {
     return descripcion;  
   }
   public void calcula() {
     this.valor = exp.val();
     calculado = true;
   }
   public Valor val() {
     if (! calculado()) 
         throw new RuntimeException("Atributo no calculado: "+descripcion); 
     return valor;   
   } 
   public Atributo siguienteDependencia() {
       while (! dependencias.isEmpty()) {
         Atributo siguiente = dependencias.pop();
         if (!siguiente.calculado()) return siguiente;
      }
      return null; 
   } 
   public void ponDependencias(Atributo ... as) {
     for(Atributo a: as) {  
       dependencias.push(a);
     }  
   }
   public boolean calculado() {
      return calculado; 
   }
   
   private Valor valor;
   private Stack<Atributo> dependencias; 
   private boolean calculado;
   private ExpSem<Valor> exp;
   private String descripcion;
}
