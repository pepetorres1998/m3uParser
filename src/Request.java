import java.io.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

//SE NECESITAN LAS LIBRERIAS DE LA CARPETA "LIBRERIAS HTTP"
public class Request {

    public static void reader(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault(); //Crea un cliente HTTP
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;//Al inicio la respuesta es nula
        InputStreamReader br;
        StringBuilder sb = new StringBuilder();
        String line;
        System.out.println("Control - 3");
        try {
            response = client.execute(request);//El cliente intenta ejecutar el request
            int status = response.getStatusLine().getStatusCode();//Es el estado de la carga del request, es decir cuanto tiempo se tarda
            if (status >= 200 && status < 300) {//Si responde entre ese intervalo, imprime lo que tenga el link
                br = new InputStreamReader(response.getEntity().getContent());
                BufferedReader buf = new BufferedReader(br);
                while ((line = buf.readLine()) != null) {
				sb.append(line);
			}
                
                System.out.println("Control - 2");
                try(FileWriter file = new FileWriter("movies1.m3u"))
                {
                    file.write(sb.toString());
                    System.out.println("Archivo creado");
                }
                catch(IOException ex)
                {
                    System.out.println("Path equivocado");
                }
            } else {//En caso de tardarse, mandara un aviso de cuanto se ha tardado
                System.out.println("Unexpected response status: " + status);
            }
        } catch (IOException | UnsupportedOperationException e) { //Excepciones en caso de que el link no exista, falle o el cliente no hizo bien el request
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
