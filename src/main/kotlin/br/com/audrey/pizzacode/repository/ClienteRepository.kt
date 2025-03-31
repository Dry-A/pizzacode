package br.com.audrey.pizzacode.repository

import br.com.audrey.pizzacode.model.Cliente
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ClienteRepository : CrudRepository<Cliente, Long> {

    fun findByNomeContaining(nome: String): List<Cliente>
    fun findByTelefoneContaining(telefone: String): List<Cliente>
    fun findByEnderecoContaining(endereco: String): List<Cliente>
}