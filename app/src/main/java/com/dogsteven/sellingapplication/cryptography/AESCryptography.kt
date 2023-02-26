package com.dogsteven.sellingapplication.cryptography

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class AESCryptography @Inject constructor(

) {
    companion object {
        private const val PBKDF_ALGORITHM = "PBKDF2WithHmacSHA1"
        private const val AES_ALGORITHM = "AES/CBC/PKCS5PADDING"
    }

    data class EncryptionResult(
        val salt: String,
        val iv: String,
        val encryptedMessage: String
    )

    private fun generateRandomByteArray(size: Int): ByteArray = SecureRandom().run {
        ByteArray(size).also(this::nextBytes)
    }

    private fun calculateSecretKey(salt: ByteArray, commonSecret: String): ByteArray {
        val pbeKeySpec = PBEKeySpec(commonSecret.toCharArray(), salt, 1324, 256)
        val secretKeyFactory = SecretKeyFactory.getInstance(PBKDF_ALGORITHM)
        return secretKeyFactory.generateSecret(pbeKeySpec).encoded
    }

    fun encrypt(message: String, commonSecret: String): EncryptionResult {
        val salt = generateRandomByteArray(256)
        val secretKey = calculateSecretKey(salt, commonSecret)
        val secretKeySpec = SecretKeySpec(secretKey, "AES")

        val iv = generateRandomByteArray(16)
        val ivSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance(AES_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec)
        val encryptedMessage = cipher.doFinal(message.encodeToByteArray())

        val base64Encoder = Base64.getEncoder()

        return EncryptionResult(
            salt = base64Encoder.encodeToString(salt),
            iv = base64Encoder.encodeToString(iv),
            encryptedMessage = base64Encoder.encodeToString(encryptedMessage)
        )
    }

    fun decrypt(encryptionResult: EncryptionResult, commonSecret: String): String {
        val (salt, iv, encryptedMessage) = encryptionResult.run {
            val base64Decoder = Base64.getDecoder()
            Triple(
                base64Decoder.decode(salt),
                base64Decoder.decode(iv),
                base64Decoder.decode(encryptedMessage)
            )
        }

        val secretKey = calculateSecretKey(salt, commonSecret)
        val secretKeySpec = SecretKeySpec(secretKey, "AES")

        val ivSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance(AES_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec)
        val decryptedMessage = cipher.doFinal(encryptedMessage)

        return decryptedMessage.decodeToString()
    }
}