package com.hun.vocabulary.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.hun.vocabulary.R
import java.lang.IllegalStateException

class WordAddDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = LayoutInflater.from(activity)
            val view = inflater.inflate(R.layout.dialog_word_add, null)

            builder.setTitle("단어 추가")
                .setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
