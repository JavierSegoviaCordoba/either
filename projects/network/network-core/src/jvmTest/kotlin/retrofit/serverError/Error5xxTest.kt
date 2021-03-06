package com.javiersc.either.network.retrofit.serverError

import com.javiersc.either.network.NetworkEither
import com.javiersc.either.network.buildNetworkFailureHttp
import com.javiersc.either.network.models.DogDTO
import com.javiersc.either.network.models.ErrorDTO
import com.javiersc.either.network.retrofit.BaseTest
import com.javiersc.either.network.runTestBlocking
import io.kotest.matchers.shouldBe
import kotlin.test.Test

internal class Error5xxTest : BaseTest<ErrorDTO, DogDTO>() {

    override val codeToFile: Pair<Int, String?> = 574 to "5xx.json"
    override val expected: NetworkEither<ErrorDTO, DogDTO> =
        buildNetworkFailureHttp(error, code, headers)

    @Test fun `suspend call 5xx`() = runTestBlocking { service.getDog() shouldBe expected }

    @Test
    fun `async call 5xx`() = runTestBlocking { service.getDogAsync().await() shouldBe expected }
}
