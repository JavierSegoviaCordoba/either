package com.javiersc.either.network.ktor.serverError

import com.javiersc.either.network.NetworkEither
import com.javiersc.either.network.buildNetworkFailureHttp
import com.javiersc.either.network.ktor.BaseTest
import com.javiersc.either.network.ktor.NetworkEither
import com.javiersc.either.network.models.DogDTO
import com.javiersc.either.network.models.ErrorDTO
import com.javiersc.either.network.runTestBlocking
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import kotlin.test.Test

internal class Error5xxTest : BaseTest<ErrorDTO, DogDTO>() {

    override val codeToFile = 574 to "5xx.json"
    override val expected: NetworkEither<ErrorDTO, DogDTO> =
        buildNetworkFailureHttp(error, code, headers)

    @Test
    fun `Request 5xx`() = runTestBlocking {
        NetworkEither<ErrorDTO, DogDTO> { client.get("path") } shouldBe expected
    }
}
