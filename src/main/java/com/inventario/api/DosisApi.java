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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.entidades.Dosis;
import com.inventario.entidades.Empleado;
import com.inventario.repositories.DosisRepository;
import com.inventario.repositories.EmpleadoRepository;

@RestController
public class DosisApi {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;

	 @Autowired
	 private DosisRepository dosisRepository;
	 
	 @GetMapping("/empleado/{empleadoId}/dosis")
	 public ResponseEntity<List<Dosis>> obtenerDosisPorEmpleadoId(@PathVariable("empleadoId") int empleadoId) {

	     List<Dosis> lstDosis = dosisRepository.findByEmpleadoId(empleadoId);

	     if (lstDosis.size() == 0) {
	         return ResponseEntity.notFound().build();
	     }

	     return ResponseEntity.ok(lstDosis);
	 }
	 
	 @PostMapping("/empleado/{empleadoId}/dosis")
	    public ResponseEntity<Dosis> crearEmpleado(@Valid @PathVariable("empleadoId") int empleadoId,
	            @RequestBody Dosis dosis) {

	        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(empleadoId);

	        if (!optionalEmpleado.isPresent()) {
	            return ResponseEntity.notFound().build();
	        }

	        dosis.setEmpleado(optionalEmpleado.get());
	        Dosis newDosis = dosisRepository.save(dosis);
	        return ResponseEntity.ok(newDosis);

	    }

	    @DeleteMapping("/empleado/{empleadoId}/dosis/{id}")
	    public ResponseEntity<Void> eliminarDosis(@PathVariable("empleadoId") int empleadoId, @PathVariable("id") int id) {

	        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(empleadoId);

	        if (!optionalEmpleado.isPresent()) {
	            return ResponseEntity.notFound().build();
	        }

	        dosisRepository.deleteById(id);
	        return ResponseEntity.ok(null);
	    }
	 
}
