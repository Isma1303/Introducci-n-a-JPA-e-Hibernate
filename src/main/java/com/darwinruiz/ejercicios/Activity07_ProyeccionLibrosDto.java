package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.darwinruiz.ejercicios.dto.LibroResumenDto;
import java.util.List;

/*
ENUNCIADO:
Proyecta Libros a LibroResumenDto (id, titulo, precio) usando:
SELECT new com.example.jpql_exercises.dto.LibroResumenDto(l.id, l.titulo, l.precio)
Ordena por id ASC e imprime.
*/
public class Activity07_ProyeccionLibrosDto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: crear query con SELECT new ... LibroResumenDto(...), getResultList, imprimir

            List<LibroResumenDto> librosResumen = entityManager.createQuery(
                            "SELECT new com.darwinruiz.ejercicios.dto.LibroResumenDto(l.id, l.titulo, l.precio) FROM Libro l ORDER BY l.id ASC", LibroResumenDto.class)
                    .getResultList();

            System.out.println("--- Proyección de Libros a DTO ---");
            if (librosResumen.isEmpty()) {
                System.out.println("No se encontraron libros.");
            } else {
                for (LibroResumenDto libro : librosResumen) {
                    System.out.println(libro);
                }
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
