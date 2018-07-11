
package Bases;
import java.io.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

//SE NECESITAN LAS LIBRERIAS DE LA CARPETA "LIBRERIAS HTTP"
public class HttpRequest {

    public static void main(String[] args) {
        CloseableHttpClient client = HttpClients.createDefault(); //Crea un cliente HTTP
        HttpGet request = new HttpGet("http://192.99.160.177:8000/get.php?username=FENIXTV&password=1234&type=m3u_plus&output=hls");
        CloseableHttpResponse response = null;//Al inicio la respuesta es nula
        try {
            response = client.execute(request);//El cliente intenta ejecutar el request
            int status = response.getStatusLine().getStatusCode();//Es el estado de la carga del request, es decir cuanto tiempo se tarda
            if (status >= 200 && status < 300) {//Si responde entre ese intervalo, imprime lo que tenga el link
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
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

