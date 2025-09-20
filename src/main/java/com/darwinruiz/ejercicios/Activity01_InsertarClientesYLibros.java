package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
1) Inserta al menos 3 clientes y 3 libros con datos realistas.
2) Varía fechas (fechaRegistro y fechaPublicacion), genera 1 libro con descripcion = NULL y 1 con stock = 0.
3) Imprime los IDs generados.
Restricciones:
- Usa tipos explícitos (sin var).
- Maneja transacción (begin/commit/rollback).
*/
public class Activity01_InsertarClientesYLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: crear clientes y libros, settear campos y persistir
            // Cliente cliente = new Cliente(); cliente.setNombre("..."); ...

            // Clientes
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan Perez");
            cliente1.setEmail("juan.perez@example.com");
            cliente1.setCiudad("Madrid");
            cliente1.setEdad(30);
            cliente1.setActivo(true);
            cliente1.setFechaRegistro(LocalDate.of(2023, 1, 15));
            entityManager.persist(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Maria Garcia");
            cliente2.setEmail("maria.garcia@example.com");
            cliente2.setCiudad("Barcelona");
            cliente2.setEdad(25);
            cliente2.setActivo(true);
            cliente2.setFechaRegistro(LocalDate.of(2022, 11, 1));
            entityManager.persist(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Pedro Lopez");
            cliente3.setEmail("pedro.lopez@example.com");
            cliente3.setCiudad("Valencia");
            cliente3.setEdad(40);
            cliente3.setActivo(false);
            cliente3.setFechaRegistro(LocalDate.of(2023, 3, 20));
            entityManager.persist(cliente3);

            // Libros
            Libro libro1 = new Libro();
            libro1.setTitulo("El Gran Gatsby");
            libro1.setAutorNombre("F. Scott Fitzgerald");
            libro1.setGenero("Novela");
            libro1.setPrecio(new BigDecimal("15.50"));
            libro1.setStock(100);
            libro1.setActivo(true);
            libro1.setFechaPublicacion(LocalDate.of(1925, 4, 10));
            libro1.setDescripcion("Clásico de la literatura estadounidense.");
            entityManager.persist(libro1);

            Libro libro2 = new Libro();
            libro2.setTitulo("Cien años de soledad");
            libro2.setAutorNombre("Gabriel García Márquez");
            libro2.setGenero("Realismo mágico");
            libro2.setPrecio(new BigDecimal("20.00"));
            libro2.setStock(0); 
            libro2.setActivo(true);
            libro2.setFechaPublicacion(LocalDate.of(1967, 5, 30));
            libro2.setDescripcion("Obra maestra de la literatura hispanoamericana.");
            entityManager.persist(libro2);

            Libro libro3 = new Libro();
            libro3.setTitulo("Don Quijote de la Mancha");
            libro3.setAutorNombre("Miguel de Cervantes");
            libro3.setGenero("Novela");
            libro3.setPrecio(new BigDecimal("12.75"));
            libro3.setStock(50);
            libro3.setActivo(true);
            libro3.setFechaPublicacion(LocalDate.of(1605, 1, 16));
            libro3.setDescripcion(null); 
            entityManager.persist(libro3);

            entityManager.getTransaction().commit();

            // TODO: imprimir IDs creados
            System.out.println("Cliente 1 ID: " + cliente1.getId());
            System.out.println("Cliente 2 ID: " + cliente2.getId());
            System.out.println("Cliente 3 ID: " + cliente3.getId());
            System.out.println("Libro 1 ID: " + libro1.getId());
            System.out.println("Libro 2 ID: " + libro2.getId());
            System.out.println("Libro 3 ID: " + libro3.getId());
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}