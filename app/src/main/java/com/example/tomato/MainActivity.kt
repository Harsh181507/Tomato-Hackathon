package com.example.tomato

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tomato.navigation.AppNavigation
import com.example.tomato.navigation.OrderNavigation
import com.example.tomato.viewModel.MainViewModel
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity(), PaymentResultListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Checkout.preload(applicationContext)

        setContent {

            val viewModel: MainViewModel = viewModel()

            AppNavigation(viewModel)

        }

    }

    override fun onPaymentSuccess(razorpayPaymentID: String?) {

        Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show()

        // Navigate to success screen
        OrderNavigation.goToSuccess()

    }

    override fun onPaymentError(code: Int, response: String?) {

        Toast.makeText(this, "Payment Failed", Toast.LENGTH_LONG).show()

    }

}