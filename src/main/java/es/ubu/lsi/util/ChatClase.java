package es.ubu.lsi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	 * HashMap con los usuarios y sus baneados correspondientes.
	 */
	private HashMap<String, HashSet<String>> listaBaneos = new HashMap<String, HashSet<String>>();

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
		listaBaneos.put(usuario, new HashSet<String>());
		enviarMensaje("El usuario \"" + usuario + "\" se ha conectado.", "Admin");
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
			//  que el usuario no es igual al emisor del mensaje.
			if (emisor.equals(usuario)) {
				listaMensaje = mensajes.get(usuario);
				listaMensaje.add("> yo: " +  mensaje);
				mensajes.put(usuario, listaMensaje);
			} else if (!checkIsBanned(usuario, emisor)) {
				listaMensaje = mensajes.get(usuario);
				listaMensaje.add("> " + emisor + ": " + mensaje);
				mensajes.put(usuario, listaMensaje);
			}

		}
	}

	/**
	 * Elimina un usuario de la lista.
	 * 
	 * @param user
	 *            usuario
	 */
	public void eliminarUsuario(String cliente) {
		enviarMensaje("El usuario \"" + cliente  + "\" se ha desconectado.", "Admin");
		mensajes.remove(cliente);
	}

	/**
	 * Banea un usuario.
	 * 
	 * @param msg
	 *            mensaje con los datos del usuario a banear
	 */
	public void ban(String usuario, String userToBan) {

		HashSet<String> baneados = listaBaneos.get(usuario);
		List<String> listaMensajes;

		// Comunicamos los baneos al servidor
		if (baneados.add(userToBan)) {
			System.out.println(usuario + " ha baneado a " + userToBan + ".");
			listaMensajes = mensajes.get(usuario);
			listaMensajes.add(usuario + " ha baneado a " + userToBan + ".");
			mensajes.put(usuario, listaMensajes);

		} else {
			System.out.println(usuario + " ya había baneado a " + userToBan + ".");
			listaMensajes = mensajes.get(usuario);
			listaMensajes.add(usuario + " ya habia baneado a " + userToBan + ".");
			mensajes.put(usuario, listaMensajes);
		}

		listaBaneos.put(usuario, baneados);

	}

	/**
	 * Desbanea un usuario.
	 *
	 * @param msg
	 *            mensaje con los datos del usuario a desbanear
	 */
	public void unban(String usuario, String userToUnban) {

		HashSet<String> baneados = listaBaneos.get(usuario);
		List<String> listaMensajes;

		if (baneados.remove(userToUnban)) {
			System.out.println(usuario + " ha desbaneado a " + userToUnban + ".");
			listaMensajes = mensajes.get(usuario);
			listaMensajes.add(usuario + " ha desbaneado a " + userToUnban + ".");
			mensajes.put(usuario, listaMensajes);
		} else {
			System.out.println(usuario + " no puede desbanear a " + userToUnban + " porque no está baneado.");
			listaMensajes = mensajes.get(usuario);
			listaMensajes.add(usuario + " no puede desbanear a " + userToUnban + " porque no está baneado.");
			mensajes.put(usuario, listaMensajes);
		}
		listaBaneos.put(usuario, baneados);
	}

	/**
	 * Comprueba si un usuario tiene baneado a otro.
	 *
	 * @param user1
	 *            usuario que igual a baneado al usuario2
	 * @param user2
	 *            usuario que igual tiene baneado el usuario1
	 * @return
	 */
	private boolean checkIsBanned(String user1, String user2) {
		return listaBaneos.get(user1).contains(user2);
	}
}
