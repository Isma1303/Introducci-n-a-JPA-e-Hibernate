package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.darwinruiz.ejercicios.model.Cliente;
import jakarta.persistence.NoResultException;

/*
ENUNCIADO:
Elimina un cliente usando una NamedQuery.
*/
public class Activity10_EliminarClienteNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: Implementar la lógica para eliminar un cliente usando una NamedQuery

            String emailBuscado = getEmailBuscado(); 
            if (emailBuscado == null) {
                System.out.println("No se proporcionó un email válido.");
                return;
            }
            entityManager.getTransaction().begin();
            try {
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", emailBuscado)
                        .getSingleResult();

                System.out.println(" Cliente encontrado para eliminar ");
                System.out.println(cliente);

                entityManager.remove(cliente);
                entityManager.getTransaction().commit();

                System.out.println(" Cliente eliminado exitosamente ");

            } catch (NoResultException e) {
                System.out.println("No se encontró ningún cliente con el email: " + emailBuscado);
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
                e.printStackTrace();
                entityManager.getTransaction().rollback();
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}