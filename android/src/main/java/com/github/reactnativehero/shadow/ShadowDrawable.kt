package com.github.reactnativehero.shadow

import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat

// 精简自 https://github.com/Liberuman/ShadowDrawable
class ShadowDrawable private constructor(private val bgColor: Int, shapeRadius: Int, shadowColor: Int, shadowRadius: Int, offsetX: Int, offsetY: Int) : Drawable() {

    companion object {
        fun setShadowDrawable(view: View, bgColor: Int, shapeRadius: Int, shadowColor: Int, shadowRadius: Int, offsetX: Int, offsetY: Int) {
            val drawable = ShadowDrawable(bgColor, shapeRadius, shadowColor, shadowRadius, offsetX, offsetY)
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }
    }

    private val mShadowPaint: Paint = Paint()
    private val mBgPaint: Paint
    private val mBgColor: Int = bgColor
    private val mShadowRadius: Int = shadowRadius
    private val mShapeRadius: Int = shapeRadius
    private val mOffsetX: Int = offsetX
    private val mOffsetY: Int = offsetY
    private var mRect: RectF? = null

    init {
        mShadowPaint.color = Color.TRANSPARENT
        mShadowPaint.isAntiAlias = true
        mShadowPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
        mShadowPaint.setShadowLayer(shadowRadius.toFloat(), offsetX.toFloat(), offsetY.toFloat(), shadowColor)
        mBgPaint = Paint()
        mBgPaint.isAntiAlias = true
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        mRect = RectF((left + mShadowRadius - mOffsetX).toFloat(), (top + mShadowRadius - mOffsetY).toFloat(), (right - mShadowRadius - mOffsetX).toFloat(),
                (bottom - mShadowRadius - mOffsetY).toFloat())
    }

    override fun draw(canvas: Canvas) {
        mBgPaint.color = mBgColor
        mRect?.let {
            canvas.drawRoundRect(it, mShapeRadius.toFloat(), mShapeRadius.toFloat(), mShadowPaint)
            canvas.drawRoundRect(it, mShapeRadius.toFloat(), mShapeRadius.toFloat(), mBgPaint)
        }
    }

    override fun setAlpha(alpha: Int) {
        mShadowPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mShadowPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

}
