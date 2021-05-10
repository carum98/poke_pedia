package com.example.pokepedia.widgets

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pokepedia.R
import com.example.pokepedia.databinding.FragmentEmptyBinding


class EmptyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int=0
) : ConstraintLayout(context, attrs, defStyleAttr,defStyleRes) {
/*
private  var attr: AttributeSet ?= null
    private var defStyleAtt: Int = 0
    private var defStyleResLocal: Int = 0

    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        attr = attrs
    }
    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        attr = attrs
        defStyleAtt = defStyleAttr
    }
    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        attr = attrs
        defStyleAtt = defStyleAttr
        defStyleResLocal = defStyleRes
    }
*/
    private val binding: FragmentEmptyBinding =
        FragmentEmptyBinding.inflate(LayoutInflater.from(context), this)

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.EmptyViewInfo,
            defStyleAttr,
            defStyleRes
        )
        binding.tituloEmpty.text  = typedArray.getString(
            R.styleable.EmptyViewInfo_titleFragment
        )
        binding.imagenPrincipalEmpty.setImageResource(
            typedArray.getResourceId(R.styleable.EmptyViewInfo_imagen,
                                     R.drawable.pokemo_no_encontrado)
        )
        typedArray.recycle()
    }
}