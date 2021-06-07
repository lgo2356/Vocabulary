package com.hun.vocabulary.pojo

data class Word(
    val vocaId: Long,
    val word: String,
    val meaningWord: String,
    val sentence: String,
    val meaningSentence: String,
    val hasLearn: Boolean
)
