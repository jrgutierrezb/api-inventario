package com.inventario.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.entidades.Empleado;
import com.inventario.repositories.UsuarioRepository;
import com.inventario.repositories.EmpleadoRepository;

@RestController
@RequestMapping("/Empleado")
public class EmpleadoApi {
	
	@Autowired
	private EmpleadoRepository EmpleadoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {

        List<Empleado> lstEmpleados = EmpleadoRepository.findAll();

        if (lstEmpleados.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lstEmpleados);
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<Empleado> getEmpleado(@PathVariable("id") int id) {

        Optional<Empleado> optionalEmpleado = EmpleadoRepository.findById(id);

        if (optionalEmpleado.isPresent()) {
            return ResponseEntity.ok(optionalEmpleado.get());
        }

        return ResponseEntity.notFound().build();
    }
    
    @PutMapping()
    public ResponseEntity<Empleado> modificarEmpleado(@RequestBody Empleado Empleado) {

        Optional<Empleado> optionalEmpleado = EmpleadoRepository.findById(Empleado.getId());

        if (optionalEmpleado.isPresent()) {

        	Empleado updateEmpleado = optionalEmpleado.get();

            updateEmpleado.setDireccion(Empleado.getDireccion());
            updateEmpleado.setFechaNacimiento(Empleado.getFechaNacimiento());
            updateEmpleado.setEstadoVacuna(Empleado.getEstadoVacuna());
            updateEmpleado.setCelular(Empleado.getCelular());

            Empleado saveEmpleado = EmpleadoRepository.save(updateEmpleado);

            return ResponseEntity.ok(saveEmpleado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> borrarEmpleado(@PathVariable("id") int id) {
    	EmpleadoRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }

    

}
