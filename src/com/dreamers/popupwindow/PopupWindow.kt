package com.dreamers.popupwindow

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.google.appinventor.components.annotations.DesignerProperty
import com.google.appinventor.components.annotations.SimpleEvent
import com.google.appinventor.components.annotations.SimpleFunction
import com.google.appinventor.components.annotations.SimpleProperty
import com.google.appinventor.components.common.PropertyTypeConstants
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent
import com.google.appinventor.components.runtime.AndroidViewComponent
import com.google.appinventor.components.runtime.ComponentContainer
import com.google.appinventor.components.runtime.EventDispatcher
import android.widget.PopupWindow as Popup

@SuppressLint("NewApi")
@Suppress("FunctionName")
class PopupWindow(container: ComponentContainer) : AndroidNonvisibleComponent(container.`$form`()) {

    private val context: Context = container.`$context`()
    private val popupWindow: Popup = Popup(context)

    private var elevation: Float = 2f
    private var backgroundColor: Int = Color.WHITE
    private var cancelable: Boolean = true

    // Runs when Popup window initializes
    init {
        popupWindow.setOnDismissListener { OnDismiss() }

        Elevation(elevation)
        BackgroundColor(backgroundColor)
        Cancelable(cancelable)
    }

    @SimpleFunction(description = "Display the content view in a popup window anchored to the bottom-left corner of the anchor view offset by the specified x and y coordinates.")
    fun Show(content: AndroidViewComponent, anchorView: AndroidViewComponent, xOffset: Int, yOffset: Int, gravity: Int) {
        val actualContent = content.view // Actual content view that we can use to show in popup window
        actualContent.visibility = View.VISIBLE

        // Make sure that the content has no parent.
        if (actualContent.parent != null) {
            val parent = actualContent.parent as ViewGroup
            parent.removeView(actualContent)
        }

        popupWindow.apply {
            contentView = actualContent
            showAsDropDown(anchorView.view, xOffset, yOffset, gravity)
        }
    }

    @SimpleFunction(description = "Dismiss popup window")
    fun Dismiss() {
        popupWindow.dismiss()
    }

    @SimpleEvent(description = "Event raised when popup window is dismissed")
    fun OnDismiss() {
        EventDispatcher.dispatchEvent(this, "OnDismiss")
    }

    @DesignerProperty(
        defaultValue = "2",
        editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT
    )
    @SimpleProperty(description = "Set elevation for popup window")
    fun Elevation(elevation: Float) {
        this.elevation = elevation
        popupWindow.elevation = elevation
    }

    @SimpleProperty(description = "Get elevation of popup window")
    fun Elevation() = elevation

    @DesignerProperty(
        defaultValue = "&HFFFFFFFF",
        editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR
    )
    @SimpleProperty(description = "Set background color for popup window")
    fun BackgroundColor(color: Int) {
        backgroundColor = color
        popupWindow.setBackgroundDrawable(ColorDrawable(color))
    }

    @SimpleProperty(description = "Get background color of popup window")
    fun BackgroundColor() = backgroundColor

    @DesignerProperty(
        editorType = PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN,
        defaultValue = "True"
    )
    @SimpleProperty(description = "Set whether the popup should dismiss when touched outside")
    fun Cancelable(isCancelAble: Boolean) {
        cancelable = isCancelAble
        popupWindow.isOutsideTouchable = isCancelAble
    }

    @SimpleProperty(description = "Get whether the popup should dismiss when touched outside")
    fun Cancelable() = cancelable

    // Gravity blocks
    @SimpleProperty(description = "Gravity : Center")
    fun Center() = Gravity.CENTER

    @SimpleProperty(description = "Gravity : Bottom")
    fun Bottom() = Gravity.BOTTOM

    @SimpleProperty(description = "Gravity : End")
    fun End() = Gravity.END

    @SimpleProperty(description = "Gravity : Left")
    fun Left() = Gravity.LEFT

    @SimpleProperty(description = "Gravity : Right")
    fun Right() = Gravity.RIGHT

    @SimpleProperty(description = "Gravity : Top")
    fun Top() = Gravity.TOP

    @SimpleProperty(description = "Gravity : Start")
    fun Start() = Gravity.START

    @SimpleProperty(description = "Gravity : Center Horizontal")
    fun CenterHorizontal() = Gravity.CENTER_HORIZONTAL

    @SimpleProperty(description = "Gravity : Center Vertical")
    fun CenterVertical() = Gravity.CENTER_VERTICAL

    @SimpleProperty(description = "Gravity : No Gravity")
    fun NoGravity() = Gravity.NO_GRAVITY

    @SimpleProperty(description = "Gravity : Fill Horizontal")
    fun FillHorizontal() = Gravity.FILL_HORIZONTAL

    @SimpleProperty(description = "Gravity : Fill Vertical")
    fun FillVertical() = Gravity.FILL_VERTICAL

    @SimpleProperty(description = "Gravity : Fill")
    fun Fill() = Gravity.FILL

}
