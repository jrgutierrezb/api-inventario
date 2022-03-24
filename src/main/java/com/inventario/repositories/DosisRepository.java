package com.inventario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.entidades.Dosis;

public interface DosisRepository extends JpaRepository<Dosis, Integer> {
    
    List<Dosis> findByEmpleadoId(int empleado);

}

