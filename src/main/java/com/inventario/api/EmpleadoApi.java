package com.inventario.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
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

import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

import com.inventario.entidades.Empleado;
import com.inventario.entidades.Usuario;
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
    public ResponseEntity<List<Empleado>> obtenerEmpleados() {

        List<Empleado> lstEmpleados = EmpleadoRepository.findAll();

        if (lstEmpleados.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(lstEmpleados);
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable("id") int id) {

        Optional<Empleado> optionalEmpleado = EmpleadoRepository.findById(id);

        if (optionalEmpleado.isPresent()) {
            return ResponseEntity.ok(optionalEmpleado.get());
        }

        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Usuario> crearEmpleado(@Valid @RequestBody Empleado empleado) {
        
        String strUsuario = generarNombreUsuario(empleado);
        String password = generarRandomClave(10);
        Usuario usuario = new Usuario();
        usuario.setUsuario(strUsuario);
        usuario.setClave(password);

        usuario.setEmpleado(empleado);
        empleado.setUsuario(usuario);

        Usuario newUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(newUsuario);
    }
    
    @PutMapping()
    public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado Empleado) {

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
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable("id") int id) {
    	EmpleadoRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }

    private String generarRandomClave(int len) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random(len, chars);
    }

    private String generarNombreUsuario(Empleado empleado) {

        Random generar = new Random();
        String usuario = empleado.getNombre().replaceAll("\\s+", "").toLowerCase().substring(0, 2)
                + empleado.getApellido().replaceAll("\\s+", "").toLowerCase().substring(0, 6)
                + String.valueOf(generar.nextInt(89) + 10);

        return usuario;
    }

}
