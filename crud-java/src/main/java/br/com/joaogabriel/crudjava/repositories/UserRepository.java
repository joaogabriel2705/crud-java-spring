package br.com.joaogabriel.crudjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaogabriel.crudjava.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
