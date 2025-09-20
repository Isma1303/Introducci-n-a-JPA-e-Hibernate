package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

/*
ENUNCIADO:
Consulta libros con precio entre 150 y 800 (inclusive) usando BETWEEN.
Ordena por precio ASC e imprime.
*/
public class Activity11_RangosPrecioBetweenLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: setParameter("min", new BigDecimal("150")) y ("max", new BigDecimal("800"))
            // TODO: imprimir resultados
            BigDecimal minPrice = new BigDecimal("150");
            BigDecimal maxPrice = new BigDecimal("800");

            jakarta.persistence.TypedQuery<com.darwinruiz.entidades.Libro> query = entityManager.createQuery(
                    "SELECT l FROM Libro l WHERE l.precio BETWEEN :min AND :max ORDER BY l.precio ASC", com.darwinruiz.entidades.Libro.class);
            query.setParameter("min", minPrice);
            query.setParameter("max", maxPrice);

            List<com.darwinruiz.entidades.Libro> libros = query.getResultList();
            for (com.darwinruiz.entidades.Libro libro : libros) {
                System.out.println(libro.getTitulo() + " | " + libro.getPrecio());
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}