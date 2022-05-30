package br.com.paulobof.lembretecompras.presentation.about

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import br.com.paulobof.lembretecompras.R
import br.com.paulobof.lembretecompras.presentation.base.auth.BaseAuthFragment

class AboutFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_about

    private lateinit var tvAboutBack: TextView
    private lateinit var ivAboutBack: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)

    }

    private fun setUpView(view: View) {
        tvAboutBack = view.findViewById(R.id.tvNewItemBack)
        ivAboutBack = view.findViewById(R.id.ivNewItemBack)

        tvAboutBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_homeFragment)
        }

        ivAboutBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_homeFragment)
        }
    }
}