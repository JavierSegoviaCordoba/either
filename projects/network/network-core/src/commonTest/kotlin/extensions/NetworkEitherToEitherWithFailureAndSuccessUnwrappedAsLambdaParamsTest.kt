package com.javiersc.either.network.extensions

import com.javiersc.either.Either
import com.javiersc.either.network.Headers
import com.javiersc.either.network.HttpStatusCode
import com.javiersc.either.network.buildNetworkFailureHttp
import com.javiersc.either.network.buildNetworkFailureLocal
import com.javiersc.either.network.buildNetworkFailureRemote
import com.javiersc.either.network.buildNetworkFailureUnknown
import com.javiersc.either.network.buildNetworkSuccess
import io.kotest.matchers.shouldBe
import io.ktor.http.headersOf
import io.ktor.util.toMap
import kotlin.test.Test

internal class NetworkEitherToEitherWithFailureAndSuccessUnwrappedAsLambdaParamsTest {

    private val success = 1
    private val failureHttp = 0
    private val failureLocal = 1000
    private val failureRemote = 2000
    private val failureUnknown = 3000
    private val headers: Headers
        get() = headersOf("A" to listOf("B")).toMap()

    @Test
    fun `Transform a NetworkEither to Either with success input`() {
        buildNetworkSuccess<Int, Int>(success, HttpStatusCode(200), headers)
            .toEither(
                success = { data: Int, _, _ -> data },
                httpError = { error: Int, _, _ -> error },
                localError = { failureLocal },
                remoteError = { failureRemote },
                unknownError = { failureUnknown },
            ) shouldBe Either.Right(success)
    }

    @Test
    fun `Transform a NetworkEither to Either with failure http`() {
        buildNetworkFailureHttp<Int, Int>(failureHttp, HttpStatusCode(400), headers)
            .toEither(
                success = { data: Int, _, _ -> data },
                httpError = { error: Int, _, _ -> error },
                localError = { failureLocal },
                remoteError = { failureRemote },
                unknownError = { failureUnknown },
            ) shouldBe Either.Left(failureHttp)
    }

    @Test
    fun `Transform a NetworkEither to Either with failure local`() {
        buildNetworkFailureLocal<Int, Int>()
            .toEither(
                success = { data: Int, _, _ -> data },
                httpError = { error: Int, _, _ -> error },
                localError = { failureLocal },
                remoteError = { failureRemote },
                unknownError = { failureUnknown },
            ) shouldBe Either.Left(failureLocal)
    }

    @Test
    fun `Transform a NetworkEither to Either with failure remote`() {
        buildNetworkFailureRemote<Int, Int>()
            .toEither(
                success = { data: Int, _, _ -> data },
                httpError = { error: Int, _, _ -> error },
                localError = { failureLocal },
                remoteError = { failureRemote },
                unknownError = { failureUnknown },
            ) shouldBe Either.Left(failureRemote)
    }

    @Test
    fun `Transform a NetworkEither to Either with failure unknown`() {
        buildNetworkFailureUnknown<Int, Int>(IllegalStateException())
            .toEither(
                success = { data: Int, _, _ -> data },
                httpError = { error: Int, _, _ -> error },
                localError = { failureLocal },
                remoteError = { failureRemote },
                unknownError = { failureUnknown },
            ) shouldBe Either.Left(failureUnknown)
    }
}
