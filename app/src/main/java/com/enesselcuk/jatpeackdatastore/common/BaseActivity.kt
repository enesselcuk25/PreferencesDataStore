package com.enesselcuk.jatpeackdatastore.common

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

open class BaseActivity<VB : ViewDataBinding>(
    private val inflateLayout: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflateLayout(layoutInflater)
        defination()
        setObserves()
        setContentView(binding.root)

        defination()
        setObserves()
    }


    open fun defination() {}
    open fun setObserves() {}
}