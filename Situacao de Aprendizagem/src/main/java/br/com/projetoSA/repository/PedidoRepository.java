package br.com.projetoSA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetoSA.model.Pedido;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findAll();

	
}