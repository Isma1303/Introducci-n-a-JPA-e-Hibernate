package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.darwinruiz.models.Cliente;
import java.util.List;

/*
ENUNCIADO:
1) Lista todos los clientes ordenados por nombre (JPQL).
2) Toma el primero de la lista y búscalo con entityManager.find() por ID.
3) Imprime ambos resultados.
*/
public class Activity04_ListarClientesYFind {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT c FROM Cliente c ORDER BY c.nombre
            // TODO: if (!lista.isEmpty()) -> find por id y print

            // 1) Lista todos los clientes ordenados por nombre (JPQL).
            List<Cliente> clientes = entityManager.createQuery("SELECT c FROM Cliente c ORDER BY c.nombre", Cliente.class)
                    .getResultList();

            System.out.println("--- Clientes listados por nombre ---");
            clientes.forEach(cliente -> System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() + ", Email: " + cliente.getEmail()));

            // 2) Toma el primero de la lista y búscalo con entityManager.find() por ID.
            // 3) Imprime ambos resultados.
            if (!clientes.isEmpty()) {
                Cliente primerCliente = clientes.get(0);
                System.out.println("\n--- Buscando el primer cliente por ID con entityManager.find() ---");
                Cliente clienteEncontrado = entityManager.find(Cliente.class, primerCliente.getId());

                if (clienteEncontrado != null) {
                    System.out.println("Cliente encontrado con find(): ID: " + clienteEncontrado.getId() + ", Nombre: " + clienteEncontrado.getNombre() + ", Email: " + clienteEncontrado.getEmail());
                } else {
                    System.out.println("No se encontró el cliente con ID: " + primerCliente.getId() + " usando find().");
                }
            } else {
                System.out.println("No hay clientes en la base de datos para listar.");
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}