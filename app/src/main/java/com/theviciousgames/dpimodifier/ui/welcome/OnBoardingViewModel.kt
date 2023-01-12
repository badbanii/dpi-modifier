package com.theviciousgames.dpimodifier.ui.welcome

import androidx.lifecycle.ViewModel
import com.fxn.stash.Stash
import com.theviciousgames.dpimodifier.utils.Constants

class OnBoardingViewModel: ViewModel() {

    fun setUserIsOld()
    {
        Stash.put(Constants.USER_IS_OLD,true)
    }

    fun getUserIsOld():Boolean
    {
        return Stash.getBoolean(Constants.USER_IS_OLD,false)
    }
}