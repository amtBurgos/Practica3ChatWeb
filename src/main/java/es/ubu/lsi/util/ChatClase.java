package es.ubu.lsi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Clase que implementa el chat
 * 
 * @author Andrés Miguel Terán
 * @author Francisco Saiz Güemes
 */
public class ChatClase {

	/**
	 * HashMap con los mensajes de cada usuario.
	 */
	private HashMap<String, List<String>> mensajes = new HashMap<String, List<String>>();

	/**
	 * Contructor de la clase.
	 */
	public ChatClase() {

	}

	/**
	 * Da de alta un nuevo usuario.
	 * 
	 * @param nombreUsuario
	 *            nombre
	 */
	public void registraUsuario(String usuario) {
		mensajes.put(usuario, new ArrayList<String>());
		enviarMensaje("-> El usuario " + usuario + " se ha conectado.", "Admin");
	}

	/**
	 * Coge los mensajes asociados a un cliente.
	 * 
	 * @param nombreUsuario
	 *            nombre
	 * @return Lista de mensajes
	 */
	public List<String> getMensajes(String usuario) {

		return mensajes.get(usuario);
	}

	/**
	 * Envia mensaje a todos los usuarios.
	 * 
	 * @param mensaje
	 *            mensaje
	 * @param emisor
	 *            emisor
	 */
	public void enviarMensaje(String mensaje, String emisor) {
		// Sacamos todos los usuarios para añadir en su lista el mensaje.
		Set<String> usuarios = mensajes.keySet();
		List<String> listaMensaje = new ArrayList<>();
		for (String usuario : usuarios) {
			// Comprobamos que el usuario no es igual al emisor del mensaje.
			if (!emisor.equals(usuario)) {
				listaMensaje = mensajes.get(usuario);
				listaMensaje.add(mensaje);
				mensajes.put(usuario, listaMensaje);
			}
		}
	}
}
