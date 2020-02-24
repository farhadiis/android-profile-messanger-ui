package com.example.soroushprofile.components.layouts

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.soroushprofile.R
import com.example.soroushprofile.util.Util
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ParallaxLayout : CoordinatorLayout {

    private var mAppBarStateChangeListener: AppBarStateChangeListener

    private var mExpandAvatarSizeDP: Float = 0f
    private var mCollapsedAvatarSizeDP: Float = 0f

    private val mAvatarPoint        = FloatArray(2)
    private val mAvatarPinPoint     = FloatArray(2)
    private val mUsernamePoint      = FloatArray(2)
    private val mUsernamePinPoint   = FloatArray(2)
    private val mStatusPoint        = FloatArray(2)
    private val mStatusPinPoint     = FloatArray(2)

    private var layoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        initializeRes()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        calcPoints()
        translationView(mAppBarStateChangeListener.currentOffset)
    }

    init {
        mExpandAvatarSizeDP = Util.convertPixelsToDp(resources.getDimension(R.dimen.avatar_size), context)
        mCollapsedAvatarSizeDP = Util.convertPixelsToDp(resources.getDimension(R.dimen.pin_avatar_size), context)
        mAppBarStateChangeListener = object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
            }

            override fun onOffsetChanged(state: State, offset: Float) {
                translationView(offset)
            }
        }
    }

    private fun initializeRes() {
        app_bar.addOnOffsetChangedListener(mAppBarStateChangeListener)
        layoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                app_bar.viewTreeObserver.removeOnGlobalLayoutListener(layoutListener)
            } else {
                app_bar.viewTreeObserver.removeGlobalOnLayoutListener(layoutListener)
            }
            onWindowFocusChanged(false)
        }
        app_bar.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
    }
    
    private fun translationView(offset: Float) {

        val newAvatarSize = Util.convertDpToPixel(mExpandAvatarSizeDP - (mExpandAvatarSizeDP
                - mCollapsedAvatarSizeDP) * offset, context).toInt()

        val expandAvatarSize = Util.convertDpToPixel(mExpandAvatarSizeDP, context)
        val scaleAvatar = expandAvatarSize - newAvatarSize
        val xAvatarOffset = (mAvatarPinPoint[0] - mAvatarPoint[0]) * offset
        val yAvatarOffset = (mAvatarPinPoint[1] - mAvatarPoint[1] - scaleAvatar) * offset

        avatar_image_view.translationX = xAvatarOffset
        avatar_image_view.translationY = yAvatarOffset
        val params = avatar_image_view.layoutParams
        params.width = newAvatarSize
        params.height = newAvatarSize
        avatar_image_view.layoutParams = params

        val xTitleOffset = (mUsernamePinPoint[0] - mUsernamePoint[0] + scaleAvatar) * offset
        val yTitleOffset = (mUsernamePinPoint[1] - mUsernamePoint[1]) * offset
        username.translationX = xTitleOffset
        username.translationY = yTitleOffset


        val xStatusOffset = (mStatusPinPoint[0] - mStatusPoint[0] + scaleAvatar) * offset
        val yStatusOffset = (mStatusPinPoint[1] - mStatusPoint[1]) * offset
        status.translationX = xStatusOffset
        status.translationY = yStatusOffset
    }

    private fun calcPoints() {

        val offset = mAppBarStateChangeListener.currentOffset

        val newAvatarSize = Util.convertDpToPixel(mExpandAvatarSizeDP - (mExpandAvatarSizeDP
                - mCollapsedAvatarSizeDP) * offset, context)
        val expandAvatarSize = Util.convertDpToPixel(mExpandAvatarSizeDP, context)
        val scaleAvatar = expandAvatarSize - newAvatarSize

        val avatarPoint = IntArray(2)
        avatar_image_view.getLocationOnScreen(avatarPoint)
        mAvatarPoint[0] = avatarPoint[0] - avatar_image_view.translationX
        mAvatarPoint[1] = avatarPoint[1].toFloat() - avatar_image_view.translationY - scaleAvatar

        val pinAvatarPoint = IntArray(2)
        pin_avatar_view.getLocationOnScreen(pinAvatarPoint)
        mAvatarPinPoint[0] = pinAvatarPoint[0].toFloat()
        mAvatarPinPoint[1] = pinAvatarPoint[1].toFloat()

        val usernamePoint = IntArray(2)
        username.getLocationOnScreen(usernamePoint)
        mUsernamePoint[0] = usernamePoint[0] - username.translationX + scaleAvatar
        mUsernamePoint[1] = usernamePoint[1] - username.translationY

        val usernamePinPoint = IntArray(2)
        pin_username.getLocationOnScreen(usernamePinPoint)
        mUsernamePinPoint[0] = usernamePinPoint[0] - pin_username.translationX
        mUsernamePinPoint[1] = usernamePinPoint[1] - pin_username.translationY


        val statusPoint = IntArray(2)
        status.getLocationOnScreen(statusPoint)
        mStatusPoint[0] = statusPoint[0] - status.translationX + scaleAvatar
        mStatusPoint[1] = statusPoint[1] - status.translationY

        val statusPinPoint = IntArray(2)
        pin_status.getLocationOnScreen(statusPinPoint)
        mStatusPinPoint[0] = statusPinPoint[0] - pin_status.translationX
        mStatusPinPoint[1] = statusPinPoint[1] - pin_status.translationY
    }


}
