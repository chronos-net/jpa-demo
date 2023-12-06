package net.informatica.jpademo.repository;

import net.informatica.jpademo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}