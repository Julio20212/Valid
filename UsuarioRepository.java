package com.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validation.entity.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

}
