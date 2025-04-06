package br.com.audrey.pizzacode.controller

import br.com.audrey.pizzacode.annotation.RateLimit
import br.com.audrey.pizzacode.controller.request.PostClienteRequest
import br.com.audrey.pizzacode.controller.request.PutClienteRequest
import br.com.audrey.pizzacode.extension.comEnderecoMascarado
import br.com.audrey.pizzacode.extension.toClienteModel
import br.com.audrey.pizzacode.model.Cliente
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import br.com.audrey.pizzacode.service.ClienteService

@RestController
@RequestMapping("/clientes")
class ClienteController(
    val clienteService : ClienteService

) {

    @GetMapping
    fun buscarTodosClientes(@RequestParam nome: String?): List<Cliente> {
        return clienteService.buscarTodosClientes(nome)
    }

    @GetMapping("/{id}")
    fun buscaClientePorId(@PathVariable id: Long): Cliente? {
        return clienteService.buscarClientePorId(id)
    }

    @GetMapping("/telefone")
    @RateLimit(limit = 3, period = 30, userBased = true)
    fun buscaClientePorTelefone(@RequestParam telefone: String): List<Cliente> {
        return clienteService.buscarClientePorTelefone(telefone)
    }

    @GetMapping("/endereco")
    fun buscaClientePorEndereco(@RequestParam endereco: String): List<Cliente> {
        return clienteService.buscarClientePorEndereco(endereco)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criaCliente(@RequestBody cliente: PostClienteRequest){
        clienteService.criarCliente(cliente.toClienteModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizaCliente(@PathVariable id: Long, @RequestBody cliente: PutClienteRequest) {
        clienteService.atualizarCliente(cliente.toClienteModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarCliente(@PathVariable id: Long) {
        clienteService.deletarCliente(id)
    }

}



