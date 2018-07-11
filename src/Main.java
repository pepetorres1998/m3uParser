import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lector momo = new Lector("http://192.99.160.177:8000/get.php?username=FENIXTV&password=1234&type=m3u_plus&output=hls");
		//Lector momo2 = new Lector("2.m3u");

		System.out.println(momo.toJson().toJSONString());
                try(FileWriter file = new FileWriter("/home/jose/Documents/jsonm3u1.json"))
                {
                    file.write(momo.toJson().toJSONString());
                    System.out.println("Archivo creado");
                }
                catch(IOException ex)
                {
                    System.out.println("Path equivocado");
                }
	}

}
