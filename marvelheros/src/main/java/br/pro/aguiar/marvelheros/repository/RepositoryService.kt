package br.pro.aguiar.marvelheros.repository

import br.pro.aguiar.marvelheros.model.MarvelCharacters

interface RepositoryService {
    suspend fun all(): MarvelCharacters
    suspend fun show(): MarvelCharacters
}