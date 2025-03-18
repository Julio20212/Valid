package com.validation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validation.entity.Usuario;
import com.validation.service.UsuarioService;

import jakarta.validation.Valid;

	@RestController
	@RequestMapping("/usuarios")
	public class UsuarioController {

		private final UsuarioService usuarioService;

		@Autowired
		public UsuarioController(UsuarioService usuarioService) {
			this.usuarioService = usuarioService;
		}

		@GetMapping("/{id}")
		public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
			Usuario usuario = usuarioService.getUsuarioById(id);
			if (usuario != null) {
				return ResponseEntity.ok(usuario);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		@GetMapping("/")
		public ResponseEntity<List<Usuario>> getAllUsuarios() {
			List<Usuario> usuarios = usuarioService.getAllUsuario();
			return ResponseEntity.ok(usuarios);
		}

		@PostMapping("/")
		public ResponseEntity<Usuario> criarUsuario(@RequestBody @Valid Usuario usuario) {
			Usuario criarUsuario = usuarioService.salvarUsuario(usuario);
			return ResponseEntity.status(HttpStatus.CREATED).body(criarUsuario);
		}

		@PutMapping("/{id}")
		public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
			Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
			if (updatedUsuario != null) {
				return ResponseEntity.ok(updatedUsuario);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id) {
			boolean deleted = usuarioService.deleteUsuario(id);
			if (deleted) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	}
