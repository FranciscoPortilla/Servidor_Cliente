import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.LinkedList;

public class Cliente extends Thread {


	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private Servidor servidor;
	private boolean flag;

	
	public Cliente(Socket socket, Servidor servidor) {
		this.socket = socket;
		this.servidor = servidor;
		try {
			is = this.socket.getInputStream();
			os = this.socket.getOutputStream();
		} catch (Exception e) {}
	}

	public void run() {
		try {
			escuchar();
		} catch (Exception e) {}
		desconectar();
	}

	private void escuchar() throws IOException {

		flag = true;
		byte[] mensaje = new byte[64];
		while (flag) {
			try {
				try {
					is.read(mensaje);
					servidor.mandaMensajes(new String(mensaje));// MANDA LOS MENSAJES A TODOS LOS CLIENTES EN EL CHAT
																// DESDE EL SERVIDOR
				} catch (SocketException e) {
					flag = false;
					e.printStackTrace();
				}

			} catch (NullPointerException ex) {
				System.out.println("Error al intentar enviar un mensaje: " + ex.getMessage());
			}
		}
	}

	private void desconectar() {
		try {
			socket.close();
			this.flag = false;
		} catch (Exception e) {}
	}
	
	public Socket getSocket() {
		return socket;
	}

}
