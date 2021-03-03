import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Servidor extends Thread {

	private ServerSocket serverSocket;
	boolean conectado;
	private ArrayList<Cliente> clientes;

	Servidor() {
		clientes = new ArrayList<>();
		this.start();
	}

	public void run() {

		try {
			System.out.println("1_CREANDO SERVIDOR ...");
			serverSocket = new ServerSocket();

			System.out.println("2_REALIZANDO EL BIND ...");
			InetSocketAddress addres = new InetSocketAddress("172.30.2.72", 6666);
			serverSocket.bind(addres);

			// RECIBE CLIENTES
			while (true) {
				Cliente c;
				Socket socket;
				socket = serverSocket.accept();
				System.out.println("HA ENTRADO NUEVO CLIENTE");
				c = new Cliente(socket, this);
				clientes.add(c);
				c.start();
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	public void mandaMensajes(String mensaje) throws IOException {

		for (int i = 0; i < clientes.size(); i++) {
			try {
				OutputStream osCliente = clientes.get(i).getSocket().getOutputStream();
				osCliente.write(mensaje.getBytes());
			} catch (SocketException e) {e.printStackTrace();}
		}
		System.out.println(new String(mensaje));
	}

	
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

}
