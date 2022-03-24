package com.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.entidades.Empleado;
import com.inventario.entidades.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Empleado findByUsuario(int usuario);
}
