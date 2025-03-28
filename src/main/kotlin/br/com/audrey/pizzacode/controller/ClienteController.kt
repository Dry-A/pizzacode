package br.com.audrey.pizzacode.controller

import br.com.audrey.pizzacode.model.Cliente
import br.com.audrey.pizzacode.controller.request.PostClienteRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clientes")
class ClienteController {

    val listaClientes = mutableListOf<Cliente>()

    @GetMapping("/soneto")
    fun mostraSoneto(): String{
        return "💗De tudo, ao meu amor serei atento\n" +
                "Antes, e com tal zelo, e sempre, e tanto\n" +
                "Que mesmo em face do maior encanto\n" +
                "Dele se encante mais meu pensamento.\n" +
                "Quero vivê-lo em cada vão momento\n" +
                "E em seu louvor hei de espalhar meu canto🫰🏼\n" +
                "E rir meu riso e derramar meu pranto\n" +
                "Ao seu pesar ou seu contentamento.\n" +
                "E assim, quando mais tarde me procure\n" +
                "Quem sabe a morte, angústia de quem vive\n" +
                "Quem sabe a solidão, fim de quem ama🔥\n" +
                "Eu possa me dizer do amor (que tive):\n" +
                "Que não seja imortal, posto que é chama\n" +
                "Mas que seja infinito enquanto dure.🎀\n" +
                "Vinícius de Moraes"
    }


    @GetMapping
    fun buscaCliente(): List<Cliente>{
        return listaClientes
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun criaCliente(@RequestBody cliente: PostClienteRequest){
        listaClientes.add(Cliente( 2, cliente.nome, cliente.telefone, cliente.endereco))
    }
}