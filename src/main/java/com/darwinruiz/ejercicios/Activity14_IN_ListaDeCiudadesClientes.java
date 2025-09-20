package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Arrays;

/*
ENUNCIADO:
Consulta clientes cuya ciudad esté en una lista (IN :ciudades),
por ejemplo: ["Guatemala","Antigua","Cobán"].
Imprime resultados.
*/
public class Activity14_IN_ListaDeCiudadesClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: List<String> ciudades = Arrays.asList("Guatemala","Antigua","Cobán");
            // TODO: SELECT c FROM Cliente c WHERE c.ciudad IN :ciudades
            List<String> ciudades = Arrays.asList("Guatemala", "Antigua", "Cobán");
            jakarta.persistence.TypedQuery<com.darwinruiz.entidades.Cliente> query = entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE c.ciudad IN :ciudades", com.darwinruiz.entidades.Cliente.class);
            query.setParameter("ciudades", ciudades);
            for (com.darwinruiz.entidades.Cliente cliente : query.getResultList()) {
                System.out.println(cliente.getNombre() + " | " + cliente.getCiudad());
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}