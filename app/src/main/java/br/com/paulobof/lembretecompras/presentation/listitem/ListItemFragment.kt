package br.com.paulobof.lembretecompras.presentation.listitem

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import br.com.paulobof.lembretecompras.R
import br.com.paulobof.lembretecompras.presentation.base.auth.BaseAuthFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class ListItemFragment : BaseAuthFragment() {

    override val layout = R.layout.fragment_listitem

    private lateinit var btListNewItem: FloatingActionButton
    private lateinit var btListNewBack: FloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
    }

    private fun setUpView(view: View) {


        btListNewItem = view.findViewById(R.id.btListNewItem)
        btListNewBack = view.findViewById(R.id.btListNewBack)

        btListNewItem.setOnClickListener {
            findNavController().navigate(R.id.action_listItemFragment_to_newItemFragment)
        }

        btListNewBack.setOnClickListener {
            findNavController().navigate(R.id.action_listItemFragment_to_homeFragment)
        }

    }

}
