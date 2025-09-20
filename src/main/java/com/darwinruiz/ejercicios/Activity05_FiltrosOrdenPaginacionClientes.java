package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.darwinruiz.models.Cliente;
import java.util.List;

/*
ENUNCIADO:
Consulta clientes cuyo nombre contenga 'a' (case-insensitive),
ordena por ciudad ASC, y devuelve la página 2 (tamaño 3).
Imprime los resultados.
*/
public class Activity05_FiltrosOrdenPaginacionClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: crear TypedQuery con LOWER(nombre) LIKE :patron, ORDER BY ciudad
            // TODO: setFirstResult(3) y setMaxResults(3)
            // TODO: imprimir resultados

            String patronBusqueda = "%a%"; 
            int pagina = 2; 
            int tamanoPagina = 3; 
            int primerResultado = (pagina - 1) * tamanoPagina;

            List<Cliente> clientes = entityManager.createQuery(
                            "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(:patron) ORDER BY c.ciudad ASC", Cliente.class)
                    .setParameter("patron", patronBusqueda)
                    .setFirstResult(primerResultado)
                    .setMaxResults(tamanoPagina)
                    .getResultList();

            System.out.println("--- Clientes con 'a', ordenados por ciudad, página " + pagina + " (tamaño " + tamanoPagina + ") ---");
            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes que coincidan con los criterios.");
            } else {
                clientes.forEach(cliente -> System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() + ", Ciudad: " + cliente.getCiudad()));
            }

        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}