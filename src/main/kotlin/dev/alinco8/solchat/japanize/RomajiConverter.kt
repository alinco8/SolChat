package dev.alinco8.solchat.japanize

class RomajiConverter {
    companion object {
        fun convert(input: String): String {
            val (protected, values) = protectNoConversion(input)
            val converted = GoogleCGI.convert(Romaji2Kana.conv(protected))
            return restoreNoConversion(converted, values)
        }

        val NO_CONVERSION_REGEX = listOf<Regex>()
        val REFERENCE_REGEX = Regex("＜[０１２３４５６７８９]+＞")

        fun protectNoConversion(input: String): Pair<String, Map<String, String>> {
            var protected = input
            val values = mutableMapOf<String, String>()

            fun escape(s: String) {
                val placeholder = "＜${values.size.toString().map { (it.code + 0xFEE0).toChar() }.joinToString("")}＞"
                values[placeholder] = s
                protected = protected.replace(s, placeholder)
            }

            REFERENCE_REGEX.findAll(input).forEach {
                escape(it.value)
            }

            NO_CONVERSION_REGEX.forEach { regex ->
                regex.findAll(protected).forEach { matchResult ->
                    escape(matchResult.value)
                }
            }
            return Pair(protected, values)
        }
        fun restoreNoConversion(input: String, values: Map<String, String>): String {
            var restored = input
            values.forEach { (key, value) ->
                restored = restored.replace(key, value)
            }
            return restored
        }
    }
}