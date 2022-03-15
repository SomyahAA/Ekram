package com.example.ekram.view.identity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ekram.R
import com.example.ekram.RegisterValidation
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {
    private val validator = RegisterValidation()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerEmail: EditText = view.findViewById(R.id.emialRegister)
        val registerPassword: EditText = view.findViewById(R.id.passwordRegister)
        val registerButton: Button = view.findViewById(R.id.registerButton)
        val confirmPassword: EditText = view.findViewById(R.id.confirmPasswordRegister)


        registerButton.setOnClickListener {
            /**
             * The condition below is to check if the user meets the requirement of registration
             * and let them register if the they meet the requirement using the firebase
             */


            val email: String = registerEmail.text.toString()
            val password: String = registerPassword.text.toString()
            val confirmPassword:String = confirmPassword.text.toString()
            if (email.isNotBlank() &&password.isNotBlank() &&confirmPassword.isNotBlank()) {
                if(password==confirmPassword){

                    if (validator.emailIsValid(email)){

                        if(validator.passwordValid(password)){

                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener() {
                                    if (it.isSuccessful) {

//                                        sharedPreferencesEditor.putBoolean("status",true).commit()
//                                        sharedPreferencesEditor.putString("uid", FirebaseAuth.getInstance().currentUser?.uid)
//                                        findNavController().navigate(R.id.action_registerFragment_to_userInformationFragment)


                                    }else{

                                        Toast.makeText(context, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                                    }

                                }
                        }else{

                            Toast.makeText(context, "Please check your password", Toast.LENGTH_SHORT).show()
                        }




                    }else{

                        Toast.makeText(context, "Please check your email ", Toast.LENGTH_SHORT).show()
                    }
                }else{

                    Toast.makeText(context, "passwords do not match", Toast.LENGTH_SHORT).show()
                }
//                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener() {
//                        if (it.isSuccessful) {
//                            findNavController().navigate(R.id.action_registerFragment_to_userInformationFragment)
//
//
//                        }


            }


        }




    }


}