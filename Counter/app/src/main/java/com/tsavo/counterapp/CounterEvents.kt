package com.tsavo.counterapp

sealed class CounterEvents {

    data object OnIncreaseCounterValue : CounterEvents()
    data object OnDecreaseCounterValue : CounterEvents()

}