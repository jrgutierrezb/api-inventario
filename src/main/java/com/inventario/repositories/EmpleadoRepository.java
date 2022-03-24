package com.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.entidades.Empleado;


public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>  {

}
