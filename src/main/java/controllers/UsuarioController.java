package controllers;
import org.hibernate.SessionFactory;

import org.hibernate.Session;

import org.hibernate.cfg.Configuration;

import models.Usuario;

public class UsuarioController {
	public String createUsuario(String nombre, String apellido, String ciudad) {
		SessionFactory sessionFactory = new Configuration().configure("hibernet.cfg.xml")
				.addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			Usuario usuario = new Usuario(nombre, apellido, ciudad);
			session.beginTransaction();
			session.persist(usuario);
			session.getTransaction().commit();
			sessionFactory.close();
			
			return "Usuario creado";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al registrar usuario";
	}
	
	public String deleteUsuario(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernet.cfg.xml")
				.addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			
			//Obtener el usuario actual a eliminar
			Usuario usuario = session.get(Usuario.class, id);
			
			//Validar que el usuario a eliminar exista
			if(usuario == null) return "Usuario no encontrado para ser eliminado.";
			
			session.remove(usuario);
			session.getTransaction().commit();
			session.close();
			
			return "Usuario eliminado";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al eliminar usuario";
	}
	
	public String updateUsuario(int id, String nombre, String apellido, String ciudad) {
		SessionFactory sessionFactory = new Configuration().configure("hibernet.cfg.xml")
				.addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			
			//Obtener el usuario actual a actualizar
			Usuario usuario = session.get(Usuario.class, id);
			
			//Validar que el usuario a actualizar exista
			if(usuario == null) return "Usuario no encontrado para ser actualizado.";
			
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setCiudad(ciudad);
			session.merge(usuario);
			session.getTransaction().commit();
			session.close();
			
			return "Usuario actualizado";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al actualizar usuario";
	}
	
	public String getUsuario(int id) {
		SessionFactory sessionFactory = new Configuration().configure("hibernet.cfg.xml")
				.addAnnotatedClass(Usuario.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			
			//Obtener el usuario actual a consultar
			Usuario usuario = session.get(Usuario.class, id);
			
			//Validar que el usuario a consultar exista
			if(usuario == null) return "Usuario no encontrado.";
	
			session.getTransaction().commit();
			session.close();
			
			return usuario.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al encontrar usuario";
	}
}
