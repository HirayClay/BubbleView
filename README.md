# BubbleView
简单的气泡控件，包装内容成气泡形式展现，在通讯应用中比较常见。在某些情况下需要气泡的显示不仅仅是显示在某个控件下方，
有时是需要根据气泡的尖角来控制气泡位置，所以本空间提供了两种显示位置的方式：1）针对控件位置显示气泡  2）针对某点显示气泡

## 效果
<img width="280" height=“512” src="https://github.com/HirayClay/BubbleView/raw/master/app/static/art1.png"></img>
<img width="280" height=“512” src="https://github.com/HirayClay/BubbleView/raw/master/app/static/art2.png"></img>
<img width="280" height=“512” src="https://github.com/HirayClay/BubbleView/raw/master/app/static/art3.png"></img>

## Usage
![](https://github.com/HirayClay/BubbleView/raw/master/app/static/illustration.png "poor picture quality")<br>


##Attention!
由于内部实现偷了懒，只绘制了箭头在左边和在右边两种情况下的路径，朝下和朝右的是在前者路径的基础上施加Matrix变换得来的，所以如果是想朝右时，并且不需要右上角的圆角，这时候就不能设置right_top_corner圆角为零，而是设置left_top_corner圆角为零
