package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.darwinruiz.models.Cliente;
import jakarta.persistence.NoResultException;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para encontrar un cliente por email
y actualizar su nombre y ciudad.
Restricciones:
- Maneja transacción.
- Si no encuentra, muestra un mensaje.
*/
public class Activity02_ActualizarClientePorEmail {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: obtener cliente por NamedQuery, modificar campos y merge
            String emailBuscado = getEmailBuscado();
            if (emailBuscado == null) {
                System.out.println("No se proporcionó un email válido.");
                return;
            }
            Cliente clienteAActualizar = null;

            try {
                clienteAActualizar = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailBuscado)
                        .getSingleResult();
            } catch (NoResultException e) {
                System.out.println("No se encontró ningún cliente con el email: " + emailBuscado);
            }

            if (clienteAActualizar != null) {
                System.out.println("Cliente encontrado: " + clienteAActualizar.getNombre() + " - " + clienteAActualizar.getCiudad());
                clienteAActualizar.setNombre("Juan Pérez Actualizado");
                clienteAActualizar.setCiudad("Sevilla");
                entityManager.merge(clienteAActualizar);
                System.out.println("Cliente actualizado a: " + clienteAActualizar.getNombre() + " - " + clienteAActualizar.getCiudad());
            } else {
                System.out.println("No se pudo actualizar el cliente porque no fue encontrado.");
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