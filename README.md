# BubbleView
简单的气泡控件，可以指定尖角位置来控制控件的位置。在某些情况下需要气泡的显示不仅仅是显示在某个控件下方，
有时是需要根据气泡的尖角来精确控制气泡位置，所以本控件提供了两种显示位置的方式：1）针对控件位置显示气泡  2）针对某点显示气泡

## Display
<img width="280" height=“512” src="https://github.com/HirayClay/BubbleView/raw/master/app/static/art1.png"></img>
<img width="280" height=“512” src="https://github.com/HirayClay/BubbleView/raw/master/app/static/art2.png"></img>
<img width="280" height=“512” src="https://github.com/HirayClay/BubbleView/raw/master/app/static/art3.png"></img>

## Usage
<com.example.hirayclay.bubbleview.Bubble
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:arrow_direction="top"
    app:arrow_height="20dp"
    app:arrow_start_position="20dp"
    app:arrow_angle_position="10dp"
    app:arrow_width="20dp"
    app:extra_corner_padding="true"
    app:extra_corner_ratio="1"
    app:left_bottom_corner="9dp"
    app:bubble_color="@color/colorBrown"
    app:left_top_corner="0dp"
    app:right_bottom_corner="9dp"
    app:right_top_corner="9dp">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:includeFontPadding="false"
            android:text="IPSUM TEXTIPSUM TEXTIPSUM TEXT" />
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:includeFontPadding="false"
            android:text="IPSUM TEXTIPSUM TEXTIPSUM TEXT" />
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:includeFontPadding="false"
            android:text="IPSUM TEXTIPSUM TEXTIPSUM TEXT" />
    </LinearLayout>
</com.example.hirayclay.bubbleview.Bubble>
![](https://github.com/HirayClay/BubbleView/raw/master/app/static/illustration.png "poor picture quality")<br>


## Attention!
由于内部实现偷了懒，只绘制了箭头在左边和在右边两种情况下的路径，朝下和朝右的是在前者路径的基础上施加Matrix变换得来的，所以如果是想朝右时，并且不需要右上角的圆角，这时候就不能设置right_top_corner圆角为零，而是设置left_top_corner圆角为零。朝下，同理。
