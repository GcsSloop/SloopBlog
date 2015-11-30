# Rocker
安卓虚拟摇杆
### 作者微博: [@安卓攻城师sloop](http://weibo.com/5459430586)
## [获取代码：Rocker](https://github.com/GcsSloop/Rocker)

# 说明
由于需要制作一个控制小车移动的应用，使用按键控制不太舒服，故制作了一个虚拟摇杆。

该摇杆原理十分简单，就是继承一个surfaceView，然后根据用户操作不断重绘界面，同时返回给用户当前角度。
如果用户不指定摇杆背景和摇杆，则默认绘制两个圆形，效果如下图所示。
<img src="http://img.blog.csdn.net/20150915193841817" width = "240" height = "450" alt="rocker3" align=center />  <img src="http://img.blog.csdn.net/20150915194048856" width = "240" height = "450" alt="rocker3" align=center />

 
摇杆的图片和背景图片可以随意指定，并且在运行过程中也可以更换（ps：图片内容需要为圆形且背景透明），指定图片后效果如下：

<img src="http://img.blog.csdn.net/20150915194120248" width = "240" height = "450" alt="rocker3" align=center />  <img src="http://img.blog.csdn.net/20150915194139505" width = "240" height = "450" alt="rocker3" align=center />

## <a href="http://pan.baidu.com/share/link?shareid=1802923607&uk=3009583694&fid=188394437825762" target="_blank">点击这里可以观看效果视频</a>

 
# 使用示例：
## 1.在布局文件中添加摇杆
 

     <com.sloop.widget.Rocker
         android:id="@+id/rudder"
         android:layout_width="match_parent"
         android:layout_height="match_parent" />

##  2.找到该组件，指定摇杆图片和背景图片（可省略）
 
    Rocker rocker = (Rocker) findViewById(R.id.rudder);
		Bitmap rocker_bg = BitmapFactory.decodeResource(getResources(), R.drawable.rocker_bg1);
		Bitmap rocker_ctrl = BitmapFactory.decodeResource(getResources(), R.drawable.rocker_ctrl);
		rocker.setRockerBg(rocker_bg);
		rocker.setRockerCtrl(rocker_ctrl);
		
## 3.设置监听器获得摇杆状态

      rocker.setRudderListener(new Rocker.RudderListener() {
  			@Override
  			public void onSteeringWheelChanged(int action, int angle) {
  				if (action == Rocker.ACTION_RUDDER) {
  					//TODO:事件实现
  					Log.e("夹角", "angle"+angle);
  				}
  			}
		});
		
# 组件设置参数的方法：

    /** 设置摇杆背景图  */
    public void setRockerBg(Bitmap bitmap) {
        rocker_bg = Bitmap.createScaledBitmap(bitmap, mWheelRadius * 2, mWheelRadius * 2, true);
    }

    /** 设置摇杆图片 */
    public void setRockerCtrl(Bitmap bitmap) {
        rocker_ctrl = Bitmap.createScaledBitmap(bitmap, mRudderRadius * 2, mRudderRadius * 2, true);
    }

    /** 设置摇杆活动半径  */
    public void setmWheelRadius(int radius) {
        mWheelRadius = DensityUtil.dip2px((ContextThemeWrapper) context, radius);
    }

    /** 设置摇杆半径 */
    public void setmRudderRadius(int radius) {
        mRudderRadius = DensityUtil.dip2px((ContextThemeWrapper) context, radius);
    }

#### ps：摇杆位置默认处于surfaceView的中心，故这里没有提供设置摇杆位置的方法。

### 参考的文章： [android 虚拟摇杆图片版](http://blog.csdn.net/jwzhangjie/article/details/8839744)
### 作者微博: [@安卓攻城师sloop](http://weibo.com/5459430586)
## [获取代码：Rocker](https://github.com/GcsSloop/Rocker)

