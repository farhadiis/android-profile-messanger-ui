package com.example.soroushprofile.components.layouts

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.soroushprofile.R
import com.example.soroushprofile.util.Util
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ParallaxLayout : CoordinatorLayout {

    private var mAvatarImageView: ImageView? = null
    private var mPinAvatarView: View? = null

    private var mUsernameTextView: TextView? = null
    private var mPinUsernameTextView: TextView? = null

    private var mStatusTextView: TextView? = null
    private var mPinStatusTextView: TextView? = null

    private var mAppBarStateChangeListener: AppBarStateChangeListener? = null

    private var EXPAND_AVATAR_SIZE_DP: Float = 0.toFloat()
    private var COLLAPSED_AVATAR_SIZE_DP: Float = 0.toFloat()

    private val mAvatarPoint = FloatArray(2)
    private val mAvatarPinPoint = FloatArray(2)
    private val mUsernamePoint = FloatArray(2)
    private val mUsernamePinPoint = FloatArray(2)
    private val mStatusPoint = FloatArray(2)
    private val mStatusPinPoint = FloatArray(2)


    private var layoutListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        initializeRes()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        calcPoints()
        translationView(mAppBarStateChangeListener!!.currentOffset)
    }

    private fun initializeRes() {

        mAvatarImageView = findViewById(R.id.avatar_image_view)
        mPinAvatarView = findViewById(R.id.pin_avatar_view)
        mUsernameTextView = findViewById(R.id.username)
        mPinUsernameTextView = findViewById(R.id.pin_username)
        mStatusTextView = findViewById(R.id.status)
        mPinStatusTextView = findViewById(R.id.pin_status)


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

    private fun init() {
        EXPAND_AVATAR_SIZE_DP = Util.convertPixelsToDp(resources.getDimension(R.dimen.avatar_size), context)
        COLLAPSED_AVATAR_SIZE_DP = Util.convertPixelsToDp(resources.getDimension(R.dimen.pin_avatar_size), context)
        mAppBarStateChangeListener = object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout,
                                        state: AppBarStateChangeListener.State) {
            }

            override fun onOffsetChanged(state: AppBarStateChangeListener.State, offset: Float) {
                translationView(offset)
            }
        }
    }

    private fun translationView(offset: Float) {

        val newAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, context).toInt()

        val expandAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, context)
        val scaleAvatar = expandAvatarSize - newAvatarSize
        val xAvatarOffset = (mAvatarPinPoint[0] - mAvatarPoint[0]) * offset
        val yAvatarOffset = (mAvatarPinPoint[1] - mAvatarPoint[1] - scaleAvatar) * offset

        mAvatarImageView!!.translationX = xAvatarOffset
        mAvatarImageView!!.translationY = yAvatarOffset
        val params = mAvatarImageView!!.layoutParams
        params.width = newAvatarSize
        params.height = newAvatarSize
        mAvatarImageView!!.layoutParams = params

        val xTitleOffset = (mUsernamePinPoint[0] - mUsernamePoint[0] + scaleAvatar) * offset
        val yTitleOffset = (mUsernamePinPoint[1] - mUsernamePoint[1]) * offset
        mUsernameTextView!!.translationX = xTitleOffset
        mUsernameTextView!!.translationY = yTitleOffset


        val xStatusOffset = (mStatusPinPoint[0] - mStatusPoint[0] + scaleAvatar) * offset
        val yStatusOffset = (mStatusPinPoint[1] - mStatusPoint[1]) * offset
        mStatusTextView!!.translationX = xStatusOffset
        mStatusTextView!!.translationY = yStatusOffset
    }

    private fun calcPoints() {

        val offset = mAppBarStateChangeListener!!.currentOffset

        val newAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, context)
        val expandAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, context)
        val scaleAvatar = expandAvatarSize - newAvatarSize

        val avatarPoint = IntArray(2)
        mAvatarImageView!!.getLocationOnScreen(avatarPoint)
        mAvatarPoint[0] = avatarPoint[0] - mAvatarImageView!!.translationX
        mAvatarPoint[1] = avatarPoint[1].toFloat() - mAvatarImageView!!.translationY - scaleAvatar

        val pinAvatarPoint = IntArray(2)
        mPinAvatarView!!.getLocationOnScreen(pinAvatarPoint)
        mAvatarPinPoint[0] = pinAvatarPoint[0].toFloat()
        mAvatarPinPoint[1] = pinAvatarPoint[1].toFloat()

        val usernamePoint = IntArray(2)
        mUsernameTextView!!.getLocationOnScreen(usernamePoint)
        mUsernamePoint[0] = usernamePoint[0] - mUsernameTextView!!.translationX + scaleAvatar
        mUsernamePoint[1] = usernamePoint[1] - mUsernameTextView!!.translationY

        val usernamePinPoint = IntArray(2)
        mPinUsernameTextView!!.getLocationOnScreen(usernamePinPoint)
        mUsernamePinPoint[0] = usernamePinPoint[0] - mPinUsernameTextView!!.translationX
        mUsernamePinPoint[1] = usernamePinPoint[1] - mPinUsernameTextView!!.translationY


        val statusPoint = IntArray(2)
        mStatusTextView!!.getLocationOnScreen(statusPoint)
        mStatusPoint[0] = statusPoint[0] - mStatusTextView!!.translationX + scaleAvatar
        mStatusPoint[1] = statusPoint[1] - mStatusTextView!!.translationY

        val statusPinPoint = IntArray(2)
        mPinStatusTextView!!.getLocationOnScreen(statusPinPoint)
        mStatusPinPoint[0] = statusPinPoint[0] - mPinStatusTextView!!.translationX
        mStatusPinPoint[1] = statusPinPoint[1] - mPinStatusTextView!!.translationY
    }


}
