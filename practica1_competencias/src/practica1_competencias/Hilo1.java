package practica1_competencias;
import javax.swing.JTextArea;

public class Hilo1 extends Thread{
private JTextArea area;
 boolean suspender; //Suspende un hilo cuando es true
 boolean pausar;    //Detiene un hilo cuando es true
 
Hilo1(JTextArea area){
this.area=area;
   suspender=false;
        pausar=false;
}

public void run(){

        System.out.println(" iniciando.");
        try {
            for (int i=1;;i++){
                 area.append(i+"\n");
                 //i++;
                 Thread.sleep(5);              
              
                synchronized (this) {
                    if (suspender==true) {
                          wait();
                           System.out.println( "Hilo en espera de reanudacion");
                       } 
                    if (pausar) break;
                }
            }
        }catch (InterruptedException exc){
            System.out.println( "interrumpido.");
        }
        System.out.println(" finalizado.");
    }

    //Pausar el hilo
    synchronized void pausarhilo(){ 
  pausar=true;
notify();
   
    }

    //Suspender un hilo
    synchronized void suspenderhilo(){
    //    suspender=true;
//notify();
//System.out.println("Suspendiendo Hilo.");
if(suspender==true){
suspender=false;
        notify();
}else if (suspender==false){
 suspender=true; 
        notify();
}
        //lo siguiente garantiza que un hilo suspendido puede detenerse.




    }

    //Renaudar un hilo
    synchronized void renaudarhilo(){
        pausar=false;
        suspender=false;
        notify();
System.out.println("Reanudar hilo");
    }
}










