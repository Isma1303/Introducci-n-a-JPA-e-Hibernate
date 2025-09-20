package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/*
ENUNCIADO:
Agrupa libros por género y muestra:
- COUNT(*), AVG(precio), SUM(stock)
Ordena por género ASC.
*/
public class Activity06_AgruparLibrosPorGenero {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) FROM Libro l GROUP BY l.genero ORDER BY l.genero
            // TODO: imprimir filas

            List<Object[]> resultados = entityManager.createQuery(
                            "SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) FROM Libro l GROUP BY l.genero ORDER BY l.genero ASC", Object[].class)
                    .getResultList();

            System.out.println("--- Agrupación de Libros por Género ---");
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron libros para agrupar.");
            } else {
                System.out.printf("%-20s %-10s %-15s %-10s\n", "Género", "Cantidad", "Precio Promedio", "Stock Total");
                System.out.println("------------------------------------------------------------------");
                for (Object[] resultado : resultados) {
                    String genero = (String) resultado[0];
                    Long cantidad = (Long) resultado[1];
                    Double precioPromedio = (Double) resultado[2];
                    Long stockTotal = (Long) resultado[3];
                    System.out.printf("%-20s %-10d %-15.2f %-10d\n", genero, cantidad, precioPromedio, stockTotal);
                }
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}