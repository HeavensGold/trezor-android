package com.satoshilabs.trezor.intents.ui

private const val HEX_CHARS = "0123456789ABCDEF"

/**
 * Converts ByteArray to a hex string.
 * https://gist.github.com/fabiomsr/845664a9c7e92bafb6fb0ca70d4e44fd
 */
fun ByteArray.toHex(): String {
    val result = StringBuffer()

    forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS[firstIndex])
        result.append(HEX_CHARS[secondIndex])
    }

    return result.toString()
}

/**
 * Converts a hex string to ByteArray.
 * https://gist.github.com/fabiomsr/845664a9c7e92bafb6fb0ca70d4e44fd
 */
fun String.hexToBytes(): ByteArray {
    val result = ByteArray(length / 2)

    for (i in 0 until length step 2) {
        val firstIndex = HEX_CHARS.indexOf(this[i]);
        val secondIndex = HEX_CHARS.indexOf(this[i + 1]);

        val octet = firstIndex.shl(4).or(secondIndex)
        result[i.shr(1)] = octet.toByte()
    }

    return result
}