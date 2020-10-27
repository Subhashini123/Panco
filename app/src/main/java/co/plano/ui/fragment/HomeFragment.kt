package co.plano.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import co.plano.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_child_view.*


class HomeFragment : Fragment() {
    lateinit var rlAddChild:RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        rlAddChild=view.findViewById(R.id.rl_add_child)

        rlAddChild.setOnClickListener {
            val btnsheet = layoutInflater.inflate(R.layout.bottom_sheet_child_view, null)
            val dialog = BottomSheetDialog(this.requireContext())
            dialog.setContentView(btnsheet)
            btnsheet.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }



        return view
    }

}