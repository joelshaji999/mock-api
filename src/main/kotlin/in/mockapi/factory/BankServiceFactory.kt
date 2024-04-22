package `in`.mockapi.factory

import `in`.mockapi.service.bank.BankService

interface BankServiceFactory {

    fun getBankService(bankName:String): BankService?
}