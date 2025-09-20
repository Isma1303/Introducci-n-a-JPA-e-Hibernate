package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Selecciona titulo y una descripcion segura usando COALESCE(descripcion, 'Sin descripción').
Imprime "Titulo | DescripcionSegura".
*/
public class Activity15_CoalesceDescripcionLibro {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT l.titulo, COALESCE(l.descripcion, 'Sin descripción') FROM Libro l ORDER BY l.titulo
            // TODO: imprimir filas Object[]
            jakarta.persistence.TypedQuery<Object[]> query = entityManager.createQuery(
                    "SELECT l.titulo, COALESCE(l.descripcion, 'Sin descripción') FROM Libro l ORDER BY l.titulo", Object[].class);
            for (Object[] result : query.getResultList()) {
                System.out.println(result[0] + " | " + result[1]);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}