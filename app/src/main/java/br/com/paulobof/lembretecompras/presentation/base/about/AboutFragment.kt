package br.com.paulobof.lembretecompras.presentation.base.about

import android.os.Bundle
import android.view.View
import android.widget.Button
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

        registerBackPressedAction()
    }

    private fun setUpView(view: View) {
        tvAboutBack = view.findViewById(R.id.tvAboutBack)
        ivAboutBack = view.findViewById(R.id.ivAboutBack)

        tvAboutBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_homeFragment)
        }

        ivAboutBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_homeFragment)
        }
    }

    private fun registerBackPressedAction() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}