package com.github.reactnativehero.shadow

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class RNTShadowManager(private val reactAppContext: ReactApplicationContext) : SimpleViewManager<ShadowView>() {

    override fun getName(): String {
        return "RNTShadow"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): ShadowView {
        return ShadowView(reactContext)
    }

    @ReactProp(name = "backgroundColor", customType = "Color")
    fun setBgColor(view: ShadowView, backgroundColor: Int) {
        view.bgColor = backgroundColor
    }

    @ReactProp(name = "borderRadius")
    fun setBorderRadius(view: ShadowView, borderRadius: Int) {
        view.borderRadius = borderRadius
    }

    @ReactProp(name = "shadowColor", customType = "Color")
    fun setShadowColor1(view: ShadowView, shadowColor: Int) {
        view.shadowColor = shadowColor
    }

    @ReactProp(name = "shadowOffsetX")
    fun setShadowOffsetX(view: ShadowView, shadowOffsetX: Int) {
        view.shadowOffsetX = shadowOffsetX
    }

    @ReactProp(name = "shadowOffsetY")
    fun setShadowOffsetY(view: ShadowView, shadowOffsetY: Int) {
        view.shadowOffsetY = shadowOffsetY
    }

    @ReactProp(name = "shadowRadius")
    fun setShadowRadius(view: ShadowView, shadowRadius: Int) {
        view.shadowRadius = shadowRadius
    }

}