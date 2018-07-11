import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*;

public class Lector
{
	public String nombreArchivo;
	public ArrayList<Info> informacion;
	
	String linea = null;
	
	Lector(String nombreArchivo)
	{
		this.nombreArchivo = nombreArchivo;
		informacion = new ArrayList<Info>();
		this.readFile();
	}
	
	public void readFile()
	{
		try
		{
			// Creamos un objeto para leer el archivo
			//FileReader fileReader = new FileReader(this.nombreArchivo);
			// Ponemos en memoria el archivo
			BufferedReader bufferedReader = new BufferedReader(Request.reader(nombreArchivo));
			String info = "", url = "";
                        System.out.println("Control - 4");
			boolean exit = false;
                        System.out.println(bufferedReader.readLine());
                        System.out.println("Control - 5");
			while((linea = bufferedReader.readLine()) != null)
			{
                                System.out.println("Control while");
                                System.out.println(linea);
				switch (Info.TipoLinea(linea)) 
				{
				    case 1: info = linea;
					    break;
				    case 2: url = linea;
				        break;
				    default: exit = true;
				        break;
				}
				
				if (exit)
				{
                                        System.out.println("Control if");
					exit = false;
					continue;
				}
				
				if ((!info.equals("")) && (!url.equals("")))
				{
                                        System.out.println("Control segundo if");
					informacion.add(new Info(info, url));
					info = "";
					url = "";
				}
			}
                        System.out.println("Control - 6");
			//Cerramos el archivo, basicamente para que no se quede en la memoria
			bufferedReader.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + nombreArchivo + "'");
                        //System.out.println(ex.toString());
			System.exit(0);
		}
		catch(IOException ex)
		{
                        ex.printStackTrace();
			System.out.println("Error reading file '" + nombreArchivo + "'");
                        //ex.printStackTrace();
			System.exit(0);
		}
	}
	
	public JSONObject toJson()
	{
		JSONObject theJson = new JSONObject();
		int j = 0;
		for (Info i : informacion)
		{
			JSONObject videoJson = new JSONObject();
			videoJson.put("Id", i.id);
			videoJson.put("Name", i.name);
			videoJson.put("Logo", i.logo);
			videoJson.put("Group-title", i.id);
			
			theJson.put("Registro "+j, videoJson);
			j++;
		}
		return theJson;
	}
}
