package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/*
ENUNCIADO:
Imprime libros paginando de 5 en 5, recorriendo todas las páginas.
Ordena por id ASC.
*/
public class Activity13_PaginacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: bucle con setFirstResult(offset) y setMaxResults(5) hasta que la página venga vacía
            int pageSize = 5;
            int offset = 0;
            List<com.darwinruiz.entidades.Libro> libros;

            do {
                jakarta.persistence.TypedQuery<com.darwinruiz.entidades.Libro> query = entityManager.createQuery(
                        "SELECT l FROM Libro l ORDER BY l.id ASC", com.darwinruiz.entidades.Libro.class);
                query.setFirstResult(offset);
                query.setMaxResults(pageSize);
                libros = query.getResultList();

                if (libros.isEmpty()) {
                    break;
                }

                System.out.println("--- Página " + ((offset / pageSize) + 1) + " ---");
                for (com.darwinruiz.entidades.Libro libro : libros) {
                    System.out.println(libro.getTitulo() + " (ID: " + libro.getId() + ")");
                }
                offset += pageSize;
            } while (true);
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}