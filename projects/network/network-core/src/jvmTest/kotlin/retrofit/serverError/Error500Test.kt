package com.javiersc.either.network.retrofit.serverError

import com.javiersc.either.network.NetworkEither
import com.javiersc.either.network.buildNetworkFailureHttp
import com.javiersc.either.network.models.DogDTO
import com.javiersc.either.network.models.ErrorDTO
import com.javiersc.either.network.retrofit.BaseTest
import com.javiersc.either.network.runTestBlocking
import io.kotest.matchers.shouldBe
import kotlin.test.Test

internal class Error500Test : BaseTest<ErrorDTO, DogDTO>() {

    override val codeToFile: Pair<Int, String?> = 500 to "5xx.json"
    override val expected: NetworkEither<ErrorDTO, DogDTO> =
        buildNetworkFailureHttp(error, code, headers)

    @Test fun `suspend call 500`() = runTestBlocking { service.getDog() shouldBe expected }

    @Test
    fun `async call 500`() = runTestBlocking { service.getDogAsync().await() shouldBe expected }
}
