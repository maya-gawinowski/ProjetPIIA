package Plant;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class EcritureEvent
{  
  public static void ecrire(String path, String text) throws IOException 
  {
//      System.out.println("écriture : "+text); 
//      System.out.println(path);
      
      File file = new File("Text/"+path);

      
      if(file.exists()) {
    	  if(file.delete()) {
    		  if(file.createNewFile()) {
//    			  System.out.println("fichier créé");
    		  }
    	  }
      }else{
    	  if(file.createNewFile()) {
//			  System.out.println("fichier créé");
		  }
      }
      
      
	  FileWriter writer = null;
       try{
//    	   System.out.println("trace 1");
            writer = new FileWriter("Text/"+path, true);
            writer.write(text,0,text.length());
       }catch(IOException ex)
       {
           ex.printStackTrace();
       }finally{
            if(writer != null)
            {
                 
            	try {
//            		System.out.println("trace2");
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
       }
  }
}  


