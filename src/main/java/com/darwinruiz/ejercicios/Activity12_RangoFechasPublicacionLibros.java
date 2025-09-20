package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

/*
ENUNCIADO:
Listar libros publicados entre dos fechas (inclusive) con BETWEEN sobre fechaPublicacion.
Ordena por fechaPublicacion DESC.
Sugerencia: usa LocalDate.now().minusDays(...) para variar.
*/
public class Activity12_RangoFechasPublicacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: setParameter("inicio", LocalDate.now().minusDays(10)); setParameter("fin", LocalDate.now())
            // TODO: imprimir resultados
            LocalDate fechaInicio = LocalDate.now().minusDays(10);
            LocalDate fechaFin = LocalDate.now();

            jakarta.persistence.TypedQuery<com.darwinruiz.entidades.Libro> query = entityManager.createQuery(
                    "SELECT l FROM Libro l WHERE l.fechaPublicacion BETWEEN :inicio AND :fin ORDER BY l.fechaPublicacion DESC", com.darwinruiz.entidades.Libro.class);
            query.setParameter("inicio", fechaInicio);
            query.setParameter("fin", fechaFin);

            List<com.darwinruiz.entidades.Libro> libros = query.getResultList();
            for (com.darwinruiz.entidades.Libro libro : libros) {
                System.out.println(libro.getTitulo() + " | " + libro.getFechaPublicacion());
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}