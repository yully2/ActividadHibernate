package views;

import java.util.Scanner;
import controllers.UsuarioController;

public class UsuarioView {

	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		leerMenu();
	}

	private static void leerMenu() {

		// Bandera para controlar la petición de la acción a realizar hasta que la
		// ingresada sea valida
		Boolean flag = true;
		Opciones opcion = null;

		while (flag) {
			System.out.print("Ingrese la accion que desea realizar: C(Crear), R(Leer), U(Actualizar), D(Eliminar):");

			// Si la opción ingresada no es valida, se seguira mostrando el menú
			String input = keyboard.nextLine().toUpperCase();
			if (Opciones.findByValue(input) != null) {
				flag = false;
				opcion = Opciones.valueOf(input);
			}
		}

		opcionSeleccionada(opcion);
	}

	private static void opcionSeleccionada(Opciones opcion) {
		switch (opcion) {
		case C:
			System.out.print(crearUsuario());
			break;
		case R:
			System.out.print(getUsuario());
			break;
		case U:
			System.out.print(updateUsuario());
			break;
		case D:
			System.out.print(deleteUsuario());
			break;
		}
	}

	private static String crearUsuario() {
		System.out.print("Ingrese los datos del usuario: \n");

		System.out.print("Nombre:");
		String nombre = keyboard.nextLine();

		System.out.print("Apellido:");
		String apellido = keyboard.nextLine();

		System.out.print("Ciudad:");
		String ciudad = keyboard.nextLine();

		return new UsuarioController().createUsuario(nombre, apellido, ciudad);
	}

	private static String getUsuario() {
		System.out.print("Ingrese el ID del usuario: \n");
		int id = Integer.parseInt(keyboard.nextLine());

		return new UsuarioController().getUsuario(id);
	}

	private static String updateUsuario() {
		System.out.print("Ingrese los datos del usuario: \n");

		System.out.print("Id:");
		int id = Integer.parseInt(keyboard.nextLine());

		System.out.print("Nombre:");
		String nombre = keyboard.nextLine();

		System.out.print("Apellido:");
		String apellido = keyboard.nextLine();

		System.out.print("Ciudad:");
		String ciudad = keyboard.nextLine();

		return new UsuarioController().updateUsuario(id, nombre, apellido, ciudad);
	}

	private static String deleteUsuario() {
		System.out.print("Ingrese el ID del usuario: \n");
		int id = Integer.parseInt(keyboard.nextLine());

		return new UsuarioController().deleteUsuario(id);
	}

	private enum Opciones {
		C, // Create
		R, // Read
		U, // Update
		D; // Delete

		public static Opciones findByValue(String opcion) {
			for (Opciones o : values()) {
				if (o.name().equalsIgnoreCase(opcion)) {
					return o;
				}
			}
			return null;
		}
	}
}
