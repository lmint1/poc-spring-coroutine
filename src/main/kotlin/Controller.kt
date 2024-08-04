package br.com.poc

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @Autowired
    private lateinit var useCase: UseCase

    @GetMapping("/api")
    fun get(): String {
        println(">>> Thread ${Thread.currentThread().id}")
        return runBlocking { useCase.execute() }
    }

    @GetMapping("/api/suspended")
    suspend fun getSuspended(): String {
        println(">>> Thread ${Thread.currentThread().id}")
        return useCase.execute()
    }

}

@Service
class UseCase {

    suspend fun execute(): String {
        delay(5000)
        return "OK"
    }

}
