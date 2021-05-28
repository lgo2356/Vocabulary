package com.hun.vocabulary.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.hun.vocabulary.R

class LearningProgressBar @JvmOverloads constructor(context: Context, attrs: AttributeSet, defStyle: Int) : View(context, attrs, defStyle) {

    init {
        setAttrs(attrs)
//        attrs.run {
//            context.obtainStyledAttributes(this, R.styleable.LearningProgressBar)
//        }.run {
//            val progressBarColorId =
//                getResourceId(R.styleable.LearningProgressBar_progressBarColor, R.color.teal_200)
//            val progressTextColorId =
//                getResourceId(R.styleable.LearningProgressBar_progressTextColor, R.color.teal_200)
//
//            mProgressColor = progressBarColorId
//            mTextColor = progressTextColorId
//
//            recycle()
//        }
    }

    companion object {
        private const val MAX_PROGRESS = 100f
        private const val MAX_SWEEP_ANGLE = 360f
        private const val ANIMATION_DURATION = 400L
    }

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStartAngle = -90f
    private var mSweepAngle = 0f
    private var mStrokeWidth = 20
    private var mProgressColor = Color.BLACK
    private var mTextColor = Color.BLACK

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawOutlineArc(canvas)
        drawText(canvas)
    }

    private fun setAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LearningProgressBar)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val progressBarColorID =
            typedArray.getResourceId(
                R.styleable.LearningProgressBar_progressBarColor,
                R.color.teal_200
            )
        val progressTextColorID =
            typedArray.getResourceId(
                R.styleable.LearningProgressBar_progressTextColor,
                R.color.black
            )

        mProgressColor = progressBarColorID
        mTextColor = progressTextColorID

        typedArray.recycle()
    }

    private fun drawOutlineArc(canvas: Canvas) {
        val strokeWidth = 20F
        val diameter: Float = Math.min(width, height) - strokeWidth * 2
        val outerOval = RectF(strokeWidth, strokeWidth, diameter, diameter)

        mPaint.apply {
            color = mProgressColor
            setStrokeWidth(strokeWidth)
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
            style = Paint.Style.STROKE
        }

        canvas.drawArc(outerOval, mStartAngle, mSweepAngle, false, mPaint)
    }

    private fun drawText(canvas: Canvas) {
        mPaint.apply {
            textSize = Math.min(width, height) / 5f
            textAlign = Paint.Align.CENTER
            strokeWidth = 0f
            color = mTextColor
            style = Paint.Style.FILL
        }

        val xPos: Float = canvas.width / 2f
        val yPos: Float = (canvas.height / 2) - (mPaint.descent() + mPaint.ascent() / 2)

        canvas.drawText("${calcProgressFromSweepAngle(mSweepAngle)}%", xPos, yPos, mPaint)
    }

    private fun calcSweepAngleFromProgress(progress: Int): Float {
        return (MAX_SWEEP_ANGLE / MAX_PROGRESS) * progress
    }

    private fun calcProgressFromSweepAngle(sweepAngle: Float): Int {
        return ((sweepAngle * MAX_PROGRESS) / MAX_SWEEP_ANGLE).toInt()
    }

    fun setProgress(progress: Int) {
        val animator =
            ValueAnimator.ofFloat(mSweepAngle, calcSweepAngleFromProgress(progress)).apply {
                interpolator = DecelerateInterpolator()
                duration = ANIMATION_DURATION
                addUpdateListener {
                    mSweepAngle = it.animatedValue as Float
                    invalidate()
                }
            }
        animator.start()
    }

    fun setProgressColor(color: Int) {
        mProgressColor = color
        invalidate()
    }

    fun setProgressWidth(width: Int) {
        mStrokeWidth = width
        invalidate()
    }

    fun setTextColor(color: Int) {
        mTextColor = color
        invalidate()
    }
}
