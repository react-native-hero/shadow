package com.github.reactnativehero.shadow

import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat

class ShadowDrawable private constructor(private val mShape: Int, bgColor: IntArray, shapeRadius: Int, shadowColor: Int, shadowRadius: Int, offsetX: Int, offsetY: Int) : Drawable() {

    companion object {
        const val SHAPE_ROUND = 1

        /**
         * 为指定View添加阴影
         * @param view 目标View
         * @param shapeRadius View的圆角
         * @param shadowColor 阴影的颜色
         * @param shadowRadius 阴影的宽度
         * @param offsetX 阴影水平方向的偏移量
         * @param offsetY 阴影垂直方向的偏移量
         */
        fun setShadowDrawable(view: View, shapeRadius: Int, shadowColor: Int, shadowRadius: Int, offsetX: Int, offsetY: Int) {
            val drawable = Builder()
                    .setShapeRadius(shapeRadius)
                    .setShadowColor(shadowColor)
                    .setShadowRadius(shadowRadius)
                    .setOffsetX(offsetX)
                    .setOffsetY(offsetY)
                    .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }

        /**
         * 为指定View设置带阴影的背景
         * @param view 目标View
         * @param bgColor View背景色
         * @param shapeRadius View的圆角
         * @param shadowColor 阴影的颜色
         * @param shadowRadius 阴影的宽度
         * @param offsetX 阴影水平方向的偏移量
         * @param offsetY 阴影垂直方向的偏移量
         */
        fun setShadowDrawable(view: View, bgColor: Int, shapeRadius: Int, shadowColor: Int, shadowRadius: Int, offsetX: Int, offsetY: Int) {
            val drawable = Builder()
                    .setBgColor(bgColor)
                    .setShapeRadius(shapeRadius)
                    .setShadowColor(shadowColor)
                    .setShadowRadius(shadowRadius)
                    .setOffsetX(offsetX)
                    .setOffsetY(offsetY)
                    .builder()
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            ViewCompat.setBackground(view, drawable)
        }
    }

    private val mShadowPaint: Paint
    private val mBgPaint: Paint
    private val mShadowRadius: Int
    private val mShapeRadius: Int
    private val mOffsetX: Int
    private val mOffsetY: Int
    private val mBgColor: IntArray?
    private var mRect: RectF? = null

    init {
        mBgColor = bgColor
        mShapeRadius = shapeRadius
        mShadowRadius = shadowRadius
        mOffsetX = offsetX
        mOffsetY = offsetY
        mShadowPaint = Paint()
        mShadowPaint.color = Color.TRANSPARENT
        mShadowPaint.isAntiAlias = true
        mShadowPaint.setShadowLayer(shadowRadius.toFloat(), offsetX.toFloat(), offsetY.toFloat(), shadowColor)
        mShadowPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
        mBgPaint = Paint()
        mBgPaint.isAntiAlias = true
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        mRect = RectF((left + mShadowRadius - mOffsetX).toFloat(), (top + mShadowRadius - mOffsetY).toFloat(), (right - mShadowRadius - mOffsetX).toFloat(),
                (bottom - mShadowRadius - mOffsetY).toFloat())
    }

    override fun draw(canvas: Canvas) {
        if (mBgColor != null) {
            if (mBgColor.size == 1) {
                mBgPaint.color = mBgColor[0]
            } else {
                mBgPaint.shader = LinearGradient(mRect!!.left, mRect!!.height() / 2, mRect!!.right,
                        mRect!!.height() / 2, mBgColor, null, Shader.TileMode.CLAMP)
            }
        }
        if (mShape == SHAPE_ROUND) {
            canvas.drawRoundRect(mRect, mShapeRadius.toFloat(), mShapeRadius.toFloat(), mShadowPaint)
            canvas.drawRoundRect(mRect, mShapeRadius.toFloat(), mShapeRadius.toFloat(), mBgPaint)
        } else {
            canvas.drawCircle(mRect!!.centerX(), mRect!!.centerY(), Math.min(mRect!!.width(), mRect!!.height()) / 2, mShadowPaint)
            canvas.drawCircle(mRect!!.centerX(), mRect!!.centerY(), Math.min(mRect!!.width(), mRect!!.height()) / 2, mBgPaint)
        }
    }

    override fun setAlpha(alpha: Int) {
        mShadowPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter) {
        mShadowPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    class Builder {
        private var mShape = SHAPE_ROUND
        private var mShapeRadius: Int
        private var mShadowColor: Int
        private var mShadowRadius: Int
        private var mOffsetX: Int
        private var mOffsetY: Int
        private var mBgColor: IntArray

        fun setShapeRadius(ShapeRadius: Int): Builder {
            mShapeRadius = ShapeRadius
            return this
        }

        fun setShadowColor(shadowColor: Int): Builder {
            mShadowColor = shadowColor
            return this
        }

        fun setShadowRadius(shadowRadius: Int): Builder {
            mShadowRadius = shadowRadius
            return this
        }

        fun setOffsetX(OffsetX: Int): Builder {
            mOffsetX = OffsetX
            return this
        }

        fun setOffsetY(OffsetY: Int): Builder {
            mOffsetY = OffsetY
            return this
        }

        fun setBgColor(BgColor: Int): Builder {
            mBgColor[0] = BgColor
            return this
        }

        fun setBgColor(BgColor: IntArray): Builder {
            mBgColor = BgColor
            return this
        }

        fun builder(): ShadowDrawable {
            return ShadowDrawable(mShape, mBgColor, mShapeRadius, mShadowColor, mShadowRadius, mOffsetX, mOffsetY)
        }

        init {
            mShapeRadius = 12
            mShadowColor = Color.parseColor("#4d000000")
            mShadowRadius = 18
            mOffsetX = 0
            mOffsetY = 0
            mBgColor = IntArray(1)
            mBgColor[0] = Color.TRANSPARENT
        }
    }

}