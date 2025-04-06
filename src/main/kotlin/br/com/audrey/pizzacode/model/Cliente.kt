package br.com.audrey.pizzacode.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity(name = "clientes")
data class Cliente(

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    var id: Long? = null,

    @Column()
    var nome: String,
    var telefone: String,
    var endereco: String
)

