package br.com.paulobof.lembretecompras.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import br.com.paulobof.lembretecompras.R
import br.com.paulobof.lembretecompras.presentation.base.auth.BaseAuthFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_home

    private lateinit var tvHomeHelloUser: TextView
    private lateinit var tvAbout: TextView
    private lateinit var tvClose: TextView
    private lateinit var btListAll: Button
    private lateinit var btNewItem: Button
    private lateinit var ivAbout: ImageView
    private lateinit var ivClose: ImageView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)

        registerBackPressedAction()
    }

    private fun setUpView(view: View) {
        tvHomeHelloUser = view.findViewById(R.id.tvNewItem)
        tvAbout = view.findViewById(R.id.tvAbout)
        tvClose = view.findViewById(R.id.tvNewItemBack)
        btListAll = view.findViewById(R.id.btListAll)
        btNewItem = view.findViewById(R.id.btNewItem)
        ivAbout = view.findViewById(R.id.ivAbout)
        ivClose = view.findViewById(R.id.ivNewItemBack)

        btListAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listItemFragment)
        }

        btNewItem.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newItemFragment)
        }

        ivAbout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        ivAbout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }

        ivClose.setOnClickListener {
            activity?.finish()
        }

        ivClose.setOnClickListener {
            activity?.finish()
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

