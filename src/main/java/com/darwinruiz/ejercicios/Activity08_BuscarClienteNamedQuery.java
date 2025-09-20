package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.darwinruiz.ejercicios.model.Cliente;
import jakarta.persistence.NoResultException;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para buscar un cliente por correo.
Imprime el resultado o un mensaje si no existe.
*/
public class Activity08_BuscarClienteNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: createNamedQuery(...).setParameter("email", "...").getSingleResult() (maneja NoResultException)

            String emailBuscado = getEmailBuscado(); 
            if (emailBuscado == null) {
                System.out.println("No se proporcionó un email válido.");
                return;
            }
            try {
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailBuscado)
                        .getSingleResult();
                System.out.println(" Cliente encontrado usando NamedQuery ");
                System.out.println(cliente);
            } catch (NoResultException e) {
                System.out.println("No se encontró ningún cliente con el email: " + emailBuscado);
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}