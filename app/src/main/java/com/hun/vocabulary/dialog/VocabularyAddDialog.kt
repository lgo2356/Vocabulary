package com.hun.vocabulary.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.hun.vocabulary.R
import java.lang.IllegalStateException

class VocabularyAddDialog : DialogFragment(), View.OnClickListener {

    private lateinit var btnCreateVocabulary: Button
    private lateinit var btnEditVocabulary: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = LayoutInflater.from(activity)
            val view = inflater.inflate(R.layout.dialog_voca_add, null)

            btnCreateVocabulary = view.findViewById(R.id.btn_create_voca)
            btnEditVocabulary = view.findViewById(R.id.btn_edit_voca)

            builder.setTitle("단어장 추가")
                .setView(view)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onStart() {
        super.onStart()

        setListener()
    }

    private fun setListener() {
        btnCreateVocabulary.setOnClickListener(this)
        btnEditVocabulary.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btnCreateVocabulary -> {

            }

            btnEditVocabulary -> {

            }
        }
    }
}