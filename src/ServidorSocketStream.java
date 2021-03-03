import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {

	/////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////// SERVIDOR TCP //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {

		Servidor servidor = new Servidor();

		try {
			// MI SOCKET CONECTA CON EL SERVIDOR
			System.out.println("1_CONECTANDOME ...");
			Socket socketYo = new Socket();
			InetSocketAddress addr = new InetSocketAddress("172.30.2.72", 6666);
			socketYo.connect(addr);
			System.out.println("2_ESTOY CONECTADO!");

			InputStream is = socketYo.getInputStream();
			OutputStream os = socketYo.getOutputStream();

			String mensaje = "";

			boolean flag = true;
			while (flag) {

				os.write(mensaje.getBytes());
				System.out.println("2.4_Mensaje enviado");

				mensaje = "Fran: " + pideString();
			}

			socketYo.close();

		} catch (IOException e) {e.printStackTrace();}

	}

	private static String pideString() throws IOException {

		String mensaje;

		System.out.println("Introduzca mensaje");
		mensaje = String.valueOf(lector.readLine());

		return mensaje;
	}
}
