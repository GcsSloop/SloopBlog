# 安卓统一添加activity切换动画
  ---
##  背景说明
####  在实际 的应用开发过程中各种动画的使用不仅能使用户感觉起来更加流畅而且合适的动画效果可以帮助用户快速的熟悉应用操作，对提升用户体验来说，动画是必不可少的。
  
####  在安卓2.0之后，安卓提供了一个很方便的API用来设置安卓界面切换动画：overridePendingTransition(EnterAnim, ExitAnim);

## 发现问题
####但是这里有一个缺点，就是每次打开activity之前都要重写这个动画效果，如果activity比较多岂不是要重复的写很多遍？对于追(lan)求(ai)简(wan)洁(qi)优(huan)美(zhe)的程序员怎么可以忍受。

#### 既然出现问题就要解决，如何才能简洁优雅的为activity添加动画效果呢？

## 分析解决
#### 作为一个有(fei)追(chang)求(lan)的程序员，自然想在界面切换的时候就能自动添加动画效果。

#### 一般来说，如果想让某一类组件均具有相同的效果我们首先想到的就是重写这个组件的基类，然后让组件继承这个基类。这个是与Activity相关的内容，我们就自己实现一个Activity的基类，重写里面相关部分的函数。

## 结果演示
#### 为了方便大家理解我们就做一个小例子。
#### 先看一下效果（质量不太好 见谅）：
![这里写图片描述](http://img.blog.csdn.net/20151014211203884)

### 1.准备动画文件
(开启新界面用到的两个动画文件)
create_zoomin.xml
```
	<?xml version="1.0" encoding="utf-8"?>
	<set xmlns:Android="http://schemas.android.com/apk/res/android"
	    Android:interpolator="@android:anim/decelerate_interpolator">
	    <scale
	    Android:duration="@android:integer/config_mediumAnimTime
	    Android:fromXScale=".8"
		Android:fromYScale=".8"
		Android:pivotX="50%p"
		Android:pivotY="50%p"
		Android:toXScale="1.0"
		Android:toYScale="1.0" />
	</set>
```

create_zoomout.xml
```
	<?xml version="1.0" encoding="utf-8"?>
	<set xmlns:Android="http://schemas.android.com/apk/res/android"
	    Android:interpolator="@android:anim/decelerate_interpolator"
	    Android:zAdjustment="top">
	    <scale
	        Android:duration="@android:integer/config_mediumAnimTime"
	        Android:fromXScale="1.0"
	        Android:fromYScale="1.0"
	        Android:pivotX="50%p"
	        Android:pivotY="50%p"
	        Android:toXScale="2"
	        Android:toYScale="2" />
	    <alpha
	        Android:duration="@android:integer/config_mediumAnimTime"
	        Android:fromAlpha="0.5"
	        Android:toAlpha="0" />
	</set> 
```
(页面结束返回到上一页用到的两个动画文件)

finish_zoomin.xml
```
	<?xml version="1.0" encoding="utf-8"?>
	<set xmlns:Android="http://schemas.android.com/apk/res/android"
	    Android:interpolator="@android:anim/decelerate_interpolator">
	    <scale
	        Android:duration="@android:integer/config_mediumAnimTime"
	        Android:fromXScale="2.0"
	        Android:fromYScale="2.0"
	        Android:pivotX="50%p"
	        Android:pivotY="50%p"
	        Android:toXScale="1.0"
	        Android:toYScale="1.0" />
	</set>
```
finish_zoomout.xml
```
	<?xml version="1.0" encoding="utf-8"?>
	<set xmlns:Android="http://schemas.android.com/apk/res/android"
	    Android:interpolator="@android:anim/decelerate_interpolator"
	    Android:zAdjustment="top">
	    <scale
	        Android:duration="@android:integer/config_mediumAnimTime"
	        Android:fromXScale="1.0"
	        Android:fromYScale="1.0"
	        Android:pivotX="50%p"
	        Android:pivotY="50%p"
	        Android:toXScale=".5"
	        Android:toYScale=".5" />
	    <alpha
	
	        Android:duration="@android:integer/config_mediumAnimTime"
	        Android:fromAlpha="1.0"
	        Android:toAlpha="0" />
	</set> 
```
### 2.自定义Activity基类

BaseActivity.java
```
	public class BaseActivity extends AppCompatActivity {
	    
	    /**
	     * 打开activity
	     */
	    protected void openActivity(Class<?> cls) {
	        openActivity(this, cls);
	        overridePendingTransition(R.anim.create_zoomin, R.anim.create_zoomout);
	    }
	
	    /**
	     * 打开activity
	     */
	    public static void openActivity(Context context, Class<?> cls) {
	        Intent intent = new Intent(context, cls);
	        context.startActivity(intent);
	    }
	
	    @Override
	    public void finish() {
	        super.finish();
	        overridePendingTransition(R.anim.finish_zoomin, R.anim.finish_zoomout);
	    }
	}
```

### 3.让所有Activity继承自BaseActivity
例如：
```
public class MainActivity extends BaseActivity {
}
```
### 4.在需要打开新页面时只使用openActivity函数
例如：
```
openActivity(SeccondActivity.class);
```

### 基本就是这样了，看我罗嗦了半天，其实原理很简单，感兴趣的小伙伴可以直接去看源码： <a href="https://github.com/GcsSloop/AndroidBlog/tree/master/2015-10/14%20%E5%AE%89%E5%8D%93%E7%BB%9F%E4%B8%80%E6%B7%BB%E5%8A%A0activity%E5%88%87%E6%8D%A2%E5%8A%A8%E7%94%BB/%E6%BA%90%E7%A0%81/main"   target=_blank>【ActivityAnimTest】</a>
