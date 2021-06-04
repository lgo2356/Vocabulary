package com.hun.vocabulary.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.ContextCompat
import com.hun.vocabulary.R

class LearningProgressBar(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val MAX_PROGRESS = 100f
        private const val MAX_SWEEP_ANGLE = 360f
        private const val ANIMATION_DURATION = 400L
    }

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStartAngle = -90f
    private var mSweepAngle = 0f
    private var mStrokeWidth = 20
    private var mProgressColor = 0
    private var mTextColor = 0

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LearningProgressBar, 0, 0).apply {
            try {
                mProgressColor = getColor(
                    R.styleable.LearningProgressBar_progressBarColor,
                    ContextCompat.getColor(context, R.color.teal_200)
                )
                mTextColor = getColor(
                    R.styleable.LearningProgressBar_progressTextColor,
                    ContextCompat.getColor(context, R.color.teal_200)
                )
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawOutlineArc(canvas)
        drawText(canvas)
    }

    private fun drawOutlineArc(canvas: Canvas) {
        val strokeWidth = 20f
        val diameter = minOf(width, height) - strokeWidth * 2
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
