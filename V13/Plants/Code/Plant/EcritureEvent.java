/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class EcritureEvent
{  
	
	/**
	 * ecrie un texte dans un fichier.
	 * @param path le chemin+nom du fichier à ecrire
	 * @param text le texte à écrire dans le fichier
	 * @throws IOException
	 */
	public static void ecrire(String path, String text) throws IOException {
      
		File file = new File("Text/"+path);
	
	      //si le fichier existe déja, on le supprime et en recré un nouveau
	      if(file.exists()) {
	    	  if(file.delete()) {
	    		  if(file.createNewFile()) {
	    		  }
	    	  }
	      }else{
	    	  if(file.createNewFile()) {
			  }
	      }
	      
	      //intègre le texte au fichier 
		  FileWriter writer = null;
	       try{
	            writer = new FileWriter("Text/"+path, true);
	            writer.write(text,0,text.length());
	       }catch(IOException ex)
	       {
	           ex.printStackTrace();
	       }finally{
	            if(writer != null)
	            {
	                 
	            	try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
	       }
	}
}  


