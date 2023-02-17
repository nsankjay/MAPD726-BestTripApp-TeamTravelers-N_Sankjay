package com.example.mapd726_besttripapp_teamtravelers_n_sankjay.dataModel

data class UserAccountDetailsDataModel(
    var firstName: String = "",
    var lastName: String = "",
    var eMail: String = "",
    var address: String = "",
    var pstalCode: String = "",
    var phoneNumber: Int = 0,
    var cardNumber: Int = 0,
)