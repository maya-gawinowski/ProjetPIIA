package Plant;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Ecriture
{

  private String text;
  private String name;
  public Ecriture() {
	  System.out.println("création écriture");
	 
  }
  
  
  
  public static void ecrire(String path, String text) 
  {
      System.out.println("écriture : "+text); 
      System.out.println(path);
	  FileWriter writer = null;
       try{
    	   System.out.println("trace 1");
            writer = new FileWriter(path, true);
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
       }
  }
} 