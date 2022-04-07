package com.example.courseacademy.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.courseacademy.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
        var sharebtn:Button = view!!.findViewById(R.id.btnshare)
        var tc:Button = view!!.findViewById(R.id.btntermcondition)
        var faq:Button = view!!.findViewById(R.id.btnfaq)
        var contact:Button = view!!.findViewById(R.id.btncontact)
        var feedback:Button = view!!.findViewById(R.id.btnfeedback)
        var about:Button = view!!.findViewById(R.id.btnabout)
        var logout:Button = view!!.findViewById(R.id.btnlogout)
        var profile:Button = view!!.findViewById(R.id.btnprofile)
        var rateapp:Button = view!!.findViewById(R.id.btnrate)

        sharebtn.setOnClickListener {
            var body = "Download Our App From Playstore Click On the Link Below : "
            var subbody = "Course Academy A E-Learning App Learn The Courses From AnyWhere At Any Time "
            var share = Intent(Intent.ACTION_SEND)
            share.type = "plain/text"
            share.putExtra(Intent.EXTRA_SUBJECT,body)
            share.putExtra(Intent.EXTRA_TEXT,subbody)
            startActivity(share)
        }
        tc.setOnClickListener { 
        var tcpage:Intent = Intent(activity,TermAndCondition::class.java)
        startActivity(tcpage)
        }
        feedback.setOnClickListener {
            var feedbakepage:Intent = Intent(activity,Feedback::class.java)
            startActivity(feedbakepage)
        }
        about.setOnClickListener {
            var aboutpage:Intent = Intent(activity,AboutUs::class.java)
            startActivity(aboutpage)
        }
        faq.setOnClickListener {
            var faqPage:Intent = Intent(activity, FAQ::class.java)
        }
        contact.setOnClickListener {
            var contactpage:Intent = Intent(activity,ContactUs::class.java)
            startActivity(contactpage)
        }
        profile.setOnClickListener {
            var profilepage:Intent = Intent(activity,Userprofile::class.java)
            startActivity(profilepage)
        }
        rateapp.setOnClickListener {

        }
        logout.setOnClickListener {
            var backtologin:Intent = Intent(activity,LoginPage::class.java)
            startActivity(backtologin)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}