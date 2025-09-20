package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.darwinruiz.models.Libro;
import jakarta.persistence.NoResultException;

/*
ENUNCIADO:
Elimina un libro buscándolo por título exacto con JPQL.
Restricciones:
- Usa getSingleResult() o maneja lista vacía.
- Transacción obligatoria.
*/
public class Activity03_EliminarLibroPorTitulo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: SELECT l FROM Libro l WHERE l.titulo = :titulo -> remove
            String tituloBuscado = getTituloBuscado();
            if (tituloBuscado == null) {
                System.out.println("No se proporcionó un título válido.");
                return;
            }
            Libro libroAEliminar = null;

            try {
                libroAEliminar = entityManager.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class)
                        .setParameter("titulo", tituloBuscado)
                        .getSingleResult();
            } catch (NoResultException e) {
                System.out.println("No se encontró ningún libro con el título: " + tituloBuscado);
            }

            if (libroAEliminar != null) {
                System.out.println("Libro encontrado: " + libroAEliminar.getTitulo() + ". Eliminando...");
                entityManager.remove(libroAEliminar);
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el libro porque no fue encontrado.");
            }

            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}