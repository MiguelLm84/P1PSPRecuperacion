import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) {
		                  
        String vocal=null;
        String ficheroInicio=args[0];
        FilesProperties fP=new FilesProperties();
        String ficheroVocales=null;
        int sumaConteo=0;
        int numVocales=0;
        File result=null;
        File archivoResultVocal=null;
        
        //Comprueba si los argumentos que se le pasan por consola son correctos el programa se Ejecutar�,
        try {            
            if(args.length!=3) {
                System.out.println("Error de sintaxis: java Main <dir fichero> <dir FileProperties> <dir FileManagedment>");            
            } else {                
                //Bucle donde se recorre la lista de vocales.                
                String[] listaVocales= {"a","e","i","o","u"};        
                for(int i=0;i<listaVocales.length;i++) {
                    vocal=listaVocales[i];
                    
                    //Se crea fichero para cada vocal.
                    ficheroVocales="ResultVocal_"+vocal.toUpperCase()+".txt";
                  
                    //Se crea fichero de resultados.                   
                    result=new File("Result_"+ficheroInicio);
                    if(!result.exists()) {
                    	result.createNewFile();       
	                }
                    
	                //Se crean y se inician los ProcessBuilder.
                    File rutaFM=new File(args[0]);
	                String PruebaPSP=rutaFM.getAbsolutePath();  
                    
	                //Se iniciaran los ProcessBuilder. 
	                ProcessBuilder pb=new ProcessBuilder("java","FilesManagedment",PruebaPSP,vocal,ficheroVocales);
	                pb.start(); 
	                    
	                Thread.sleep(6000);
                                
                    archivoResultVocal=new File(ficheroVocales);
                    if(archivoResultVocal.exists()) {
                    	 BufferedReader br=fP.getBufferedReader(archivoResultVocal);        
                  	    numVocales=Integer.parseInt(br.readLine());
                  	    sumaConteo=sumaConteo+numVocales;		
                  	    PrintWriter prWr=fP.getPrintWriter(result);
                  	    prWr.write("\nEl resultado de la vocal_"+vocal.toUpperCase()+" es: "+numVocales);
                  	    System.out.println("\nEl resultado de la vocal_"+vocal.toUpperCase()+" es: "+numVocales);
                  	    
                  	    br.close();
                  	    prWr.close();
                  	 	//Se borran los ficheros con los resultados de los conteos de las vocales.
                 	   	if(archivoResultVocal!=null){
                 	   		archivoResultVocal.delete();	
                 	   	}
                    } else {
                    	System.out.println("Error, el archivo no existe!!.");
                    }
                }
                PrintWriter prWr2=fP.getPrintWriter(result);
         	    prWr2.write("\nEl resultado Total es: "+sumaConteo);
         	   	System.out.println("\nEl resultado Total es: "+sumaConteo);

         	   	//Se cierran los flujos.
       			prWr2.close();
       			
            }        
        } catch(ArrayIndexOutOfBoundsException f) {
            System.out.println("Error, no se est� pasando ning�n argumento a la matriz.");
            f.getMessage();
            
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
            e.getMessage();
            
            e.getStackTrace();
            e.printStackTrace();
            
        } catch (InterruptedException g) {
            System.out.println("Error en el hilo (Thread.sleep())");
            g.getMessage();            
        } catch (NullPointerException h) {
			System.out.println("Error,el fichero 'ResultVocal_"+vocal.toUpperCase()+".txt' est� vac�o y no se puede acceder a �l.");
			h.getMessage();	
		}
	}
}
