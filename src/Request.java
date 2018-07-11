import java.io.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

//SE NECESITAN LAS LIBRERIAS DE LA CARPETA "LIBRERIAS HTTP"
public class Request {

    public static InputStreamReader reader(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault(); //Crea un cliente HTTP
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = null;//Al inicio la respuesta es nula
        InputStreamReader br;
        System.out.println("Control - 1");
        try {
            response = client.execute(request);//El cliente intenta ejecutar el request
            int status = response.getStatusLine().getStatusCode();//Es el estado de la carga del request, es decir cuanto tiempo se tarda
            if (status >= 200 && status < 300) {//Si responde entre ese intervalo, imprime lo que tenga el link
                br = new InputStreamReader(response.getEntity().getContent());
                System.out.println("Control - 2");
                System.out.println(br.read());
                return br;
            } else {//En caso de tardarse, mandara un aviso de cuanto se ha tardado
                System.out.println("Unexpected response status: " + status);
                System.out.println("Is null");
                System.out.println("Control - 3");
                return br = null;
            }
        } catch (IOException | UnsupportedOperationException e) { //Excepciones en caso de que el link no exista, falle o el cliente no hizo bien el request
            e.printStackTrace();
            System.out.println("Is null");
            return br = null;
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
