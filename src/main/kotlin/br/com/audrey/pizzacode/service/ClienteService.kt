package br.com.audrey.pizzacode.service

import br.com.audrey.pizzacode.model.Cliente
import br.com.audrey.pizzacode.repository.ClienteRepository
import org.springframework.stereotype.Service

@Service
class ClienteService (
    val clienteRepository : ClienteRepository
) {

    fun buscarClientePorId(id: Long): Cliente? {
        return clienteRepository.findById(id).orElseThrow()
    }

    fun buscarTodosClientes(nome: String?): List<Cliente> {
        return if (nome != null) {
            clienteRepository.findByNomeContaining(nome)
        } else {
            clienteRepository.findAll().toList()
        }
    }

    fun buscarClientePorTelefone(telefone: String): List<Cliente> {
        return clienteRepository.findByTelefoneContaining(telefone)
    }

    fun buscarClientePorEndereco(endereco: String): List<Cliente> {
        return clienteRepository.findByEnderecoContaining(endereco)
    }

    fun criarCliente(cliente: Cliente) {
        clienteRepository.save(cliente)
    }

    fun atualizarCliente(cliente: Cliente) {
        if (!clienteRepository.existsById(cliente.id!!)) {
            throw Exception("Cliente não encontrado")
        }
        clienteRepository.save(cliente)
    }

    fun deletarCliente(id: Long) {

        if (!clienteRepository.existsById(id)) {
            throw Exception("Cliente não encontrado")
        }
        clienteRepository.deleteById(id)
    }
}