package com.example.toastbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xlulibrary.ToastBox


class TestFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = TestFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ToastBox.setParams(layout = R.layout.toast_custom).showToast("This is Custom View")
    }


}