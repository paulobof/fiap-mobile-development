package br.com.paulobof.lembretecompras.presentation.newitem

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.paulobof.lembretecompras.R
import br.com.paulobof.lembretecompras.data.remote.datasource.ItemRemoteFirebaseDataSourceImpl
import br.com.paulobof.lembretecompras.data.remote.repository.ItemRepositoryImpl
import br.com.paulobof.lembretecompras.domain.usecases.CreateItemUseCase
import br.com.paulobof.lembretecompras.presentation.base.auth.BaseAuthFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NewItemFragment : BaseAuthFragment() {
    override val layout = R.layout.fragment_newitem
    private val newItemViewModel: NewItemViewModel by lazy {
        ViewModelProvider(
            this,
            NewItemViewModelFactory(
                CreateItemUseCase(
                    ItemRepositoryImpl(
                        ItemRemoteFirebaseDataSourceImpl(
                            FirebaseFirestore.getInstance()
                        )
                    )
                )
            )
        ).get(NewItemViewModel::class.java)
    }

    private lateinit var etNewItem: EditText
    private lateinit var btNewItemCreate: Button
    private lateinit var tvNewItemBack: TextView
    private lateinit var ivNewItemBack: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView(view)
    }

    private fun setUpView(view: View) {
        etNewItem = view.findViewById(R.id.etNewItem)
        btNewItemCreate = view.findViewById(R.id.btNewItemCreate)
        tvNewItemBack = view.findViewById(R.id.tvNewItemBack)
        ivNewItemBack = view.findViewById(R.id.ivNewItemBack)

        btNewItemCreate.setOnClickListener {
            newItemViewModel.create(
                etNewItem.text.toString()
            )
            findNavController().navigate(R.id.action_newItemFragment_to_listItemFragment)
        }

        tvNewItemBack.setOnClickListener {
            findNavController().navigate(R.id.action_newItemFragment_to_homeFragment)
        }

        ivNewItemBack.setOnClickListener {
            findNavController().navigate(R.id.action_newItemFragment_to_homeFragment)
        }
    }
}