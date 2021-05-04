/* Projet PIIA : Circé Carletti / Maya Gawinowski */

package Plant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Cette classe permet de réaliser une écriture de fichier
 */

class Ecriture
{

  private String text;
  private String name;
  public Ecriture() {
	  
	 
  }
  
  
  /*
   * Cette fonction permet d'écrire dans le nom de fichier (path) donné en argument la String (text) également donnée en argument
   */
  public static void ecrire(String path, String text) throws IOException 
  {
      System.out.println("écriture : "+text); 
      System.out.println(path);
      
      File file = new File("Text/"+path);

      // On regarde si le nom du fichier existe déjà ou non
      if (file.createNewFile()) {
          
          System.out.println("File has been created.");
      } else {
      
          System.out.println("File already exists.");
      }
      
      // On écrie dans le fichier donné 
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
            		System.out.println("trace2");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
       }
  }
}  