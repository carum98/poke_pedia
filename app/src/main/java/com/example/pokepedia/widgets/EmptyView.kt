package com.example.pokepedia.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pokepedia.R
import com.example.pokepedia.databinding.EmptyViewBinding

class EmptyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: EmptyViewBinding = EmptyViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmptyView,
            defStyleAttr,
            defStyleRes
        )

        binding.textoImagenEmpty.text = typedArray.getString(R.styleable.EmptyView_title)
        binding.imagenPrincipalEmpty.setImageResource(
            typedArray.getResourceId(R.styleable.EmptyView_imagen,
                R.drawable.pokemo_no_encontrado)
        )
        typedArray.recycle()
    }
}