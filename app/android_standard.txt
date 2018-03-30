com.maimeng.comic

init
is check has
get
set
handle process deal
display show
update
save insert
reset
clear
remove delete
draw

class MyClass {
	public int pField;
	private int mPrivate;
	protected int mProtected;
	private static MyClass sInstance;
}

First
Last
Next
Pre
Cur

imageList 
userArr
nameMap
books

if (condition) body();

if (condition) {
    body();
}

如果某个方法的代码超出 40 行，请考虑是否可以在不破坏程序结构的前提下对其拆解。

类成员的顺序：
1.常量
2.字段
3.构造函数
4.重写函数和回调
5.公有函数
6.私有函数
7.内部类或接口

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private String mTitle;
    private TextView mTextViewTitle;

    @Override
    public void onCreate() {
        ...
    }

    public void setTitle(String title) {
    	mTitle = title;
    }

    private void setUpView() {
        ...
    }

    static class AnInnerClass {

    }
}

Context 作为其第一个参数，回调接口应该作为其最后一个参数
// Context always goes first
public User loadUser(Context context, int userId);

// Callbacks always go last
public void loadUserAsync(Context context, int userId, UserCallback callback);

SharedPreferences PREF_
Bundle BUNDLE_
Fragment Arguments ARGUMENT_
Intent Extra EXTRA_
Intent Action ACTION_

// 注意：字段的值与名称相同以避免重复问题
static final String PREF_EMAIL = "PREF_EMAIL";
static final String BUNDLE_AGE = "BUNDLE_AGE";
static final String ARGUMENT_USER_ID = "ARGUMENT_USER_ID";

// 与意图相关的项使用完整的包名作为值的前缀
static final String EXTRA_SURNAME = "com.myapp.extras.EXTRA_SURNAME";
static final String ACTION_OPEN_USER = "com.myapp.action.ACTION_OPEN_USER";

换行：
int longName = anotherVeryLongVariable + anEvenLongerOne - thisRidiculousLongOne
        + theFinalOne;

int longName =
        anotherVeryLongVariable + anEvenLongerOne - thisRidiculousLongOne + theFinalOne;
		
Picasso.with(context).load("https://blankj.com/images/avatar.jpg").into(ivAvatar);
Picasso.with(context)
        .load("https://blankj.com/images/avatar.jpg")
        .into(ivAvatar);

loadPicture(context, "https://blankj.com/images/avatar.jpg", ivAvatar, "Avatar of the user", clickListener);
loadPicture(context,
        "https://blankj.com/images/avatar.jpg",
        ivAvatar,
        "Avatar of the user",
        clickListener);
		
视图动画文件需放在 res/anim/ 目录下，属性动画文件需要放在 res/animator/ 目录下
{模块名_}逻辑名称
fade_in	淡入
fade_out	淡出
push_down_in	从下方推入
push_down_out	从下方推出
push_left	推向左方
slide_in_from_top	从头部滑动进入
zoom_enter	变形进入
slide_in	滑动进入
shrink_to_middle	中间缩小

res/drawable/ 目录下放的是位图文件（.png、.9.png、.jpg、.gif）或编译为可绘制对象资源子类型的 XML 文件，
而 res/mipmap/ 目录下放的是不同密度的启动图标，所以 res/mipmap/ 只用于存放启动图标，其余图片资源文件都应该放到 res/drawable/ 目录下
类型{_模块名}_逻辑名称、类型{_模块名}_颜色

btn_main_about.png	主页关于按键 类型_模块名_逻辑名称
btn_back.png	返回按键 类型_逻辑名称
divider_maket_white.png	商城白色分割线 类型_模块名_颜色
ic_edit.png	编辑图标 类型_逻辑名称
bg_main.png	主页背景 类型_逻辑名称
btn_red.png	红色按键 类型_颜色
btn_red_big.png	红色大按键 类型_颜色
ic_head_small.png	小头像图标 类型_逻辑名称
bg_input.png	输入框背景 类型_逻辑名称
divider_white.png	白色分割线 类型_颜色
bg_main_head.png	主页头部背景 类型_模块名_逻辑名称
def_search_cell.png	搜索页面默认单元图片 类型_模块名_逻辑名称
ic_more_help.png	更多帮助图标 类型_逻辑名称
divider_list_line.png	列表分割线 类型_逻辑名称
sel_search_ok.xml	搜索界面确认选择器 类型_模块名_逻辑名称
shape_music_ring.xml	音乐界面环形形状 类型_模块名_逻辑名称

sel_btn_xx	作用在 btn_xx 上的 selector
btn_xx_normal	默认状态效果
btn_xx_pressed	state_pressed 点击效果
btn_xx_focused	state_focused 聚焦效果
btn_xx_disabled	state_enabled 不可用效果
btn_xx_checked	state_checked 选中效果
btn_xx_selected	state_selected 选中效果
btn_xx_hovered	state_hovered 悬停效果
btn_xx_checkable	state_checkable 可选效果
btn_xx_activated	state_activated 激活效果
btn_xx_window_focused	state_window_focused 窗口聚焦效果

activity_main.xml	主窗体 类型_模块名
activity_main_head.xml	主窗体头部 类型_模块名_逻辑名称
fragment_music.xml	音乐片段 类型_模块名
fragment_music_player.xml	音乐片段的播放器 类型_模块名_逻辑名称
dialog_loading.xml	加载对话框 类型_逻辑名称
ppw_info.xml	信息弹窗（PopupWindow） 类型_逻辑名称
item_main_song.xml	主页歌曲列表项 类型_模块名_逻辑名称

 <resources>

      <!-- grayscale -->
      <color name="white"     >#FFFFFF</color>
      <color name="gray_light">#DBDBDB</color>
      <color name="gray"      >#939393</color>
      <color name="gray_dark" >#5F5F5F</color>
      <color name="black"     >#323232</color>

      <!-- basic colors -->
      <color name="green">#27D34D</color>
      <color name="blue">#2A91BD</color>
      <color name="orange">#FF9D2F</color>
      <color name="red">#FF432F</color>

  </resources>
  
  <resources>

    <!-- font sizes -->
    <dimen name="font_22">22sp</dimen>
    <dimen name="font_18">18sp</dimen>
    <dimen name="font_15">15sp</dimen>
    <dimen name="font_12">12sp</dimen>

    <!-- typical spacing between two views -->
    <dimen name="spacing_40">40dp</dimen>
    <dimen name="spacing_24">24dp</dimen>
    <dimen name="spacing_14">14dp</dimen>
    <dimen name="spacing_10">10dp</dimen>
    <dimen name="spacing_4">4dp</dimen>

    <!-- typical sizes of views -->
    <dimen name="button_height_60">60dp</dimen>
    <dimen name="button_height_40">40dp</dimen>
    <dimen name="button_height_32">32dp</dimen>

</resources>

{模块名_}逻辑名称
main_menu_about	主菜单按键文字
friend_title	好友模块标题栏
friend_dialog_del	好友删除提示
login_check_email	登录验证
dialog_title	弹出框标题
button_ok	确认键
loading	加载文字

id命名：
命名规则：view 缩写{_模块名}_逻辑名，例如： btn_main_search、btn_back。

/**
 * <pre>
 *     author : Blankj
 *     e-mail : xxx@xx
 *     time   : 2017/03/07
 *     desc   : xxxx 描述
 *     version: 1.0
 * </pre>
 */
public class WelcomeActivity {
    ...
}

/**
 * <pre>
 *     author : ${USER}
 *     e-mail : xxx@xx
 *     time   : ${YEAR}/${MONTH}/${DAY}
 *     desc   :
 *     version: 1.0
 * </pre>
 */
 
 /**
 * bitmap 转 byteArr
 *
 * @param bitmap bitmap 对象
 * @param format 格式
 * @return 字节数组
 */
public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format) {
    if (bitmap == null) return null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bitmap.compress(format, 100, baos);
    return baos.toByteArray();
}

/*
 * This is
 * okay.
 */

// And so
// is this.

/* Or you can
* even do this. */

其他的一些规范

合理布局，有效运用 <merge>、<ViewStub>、<include> 标签；

Activity 和 Fragment 里面有许多重复的操作以及操作步骤，所以我们都需要提供一个 BaseActivity 和 BaseFragment，让所有的 Activity 和 Fragment 都继承这个基类。

方法基本上都按照调用的先后顺序在各自区块中排列；

相关功能作为小区块放在一起（或者封装掉）；

当一个类有多个构造函数，或是多个同名函数，这些函数应该按顺序出现在一起，中间不要放进其它函数；

数据提供统一的入口。无论是在 MVP、MVC 还是 MVVM 中，提供一个统一的数据入口，都可以让代码变得更加易于维护。比如可使用一个 DataManager，把 http、preference、eventpost、database 都放在 DataManager 里面进行操作，我们只需要与 DataManager 打交道；

多用组合，少用继承；

提取方法，去除重复代码。对于必要的工具类抽取也很重要，这在以后的项目中是可以重用的。

可引入 Dagger2 减少模块之间的耦合性。Dagger2 是一个依赖注入框架，使用代码自动生成创建依赖关系需要的代码。减少很多模板化的代码，更易于测试，降低耦合，创建可复用可互换的模块；

项目引入 RxAndroid 响应式编程，可以极大的减少逻辑代码；

通过引入事件总线，如：EventBus、AndroidEventBus、RxBus，它允许我们在 DataLayer 中发送事件，以便 ViewLayer 中的多个组件都能够订阅到这些事件，减少回调；

尽可能使用局部变量；

及时关闭流；

尽量减少对变量的重复计算；
for (int i = 0, len = list.size(); i < len; i++) {
      ...
}
尽量采用懒加载的策略，即在需要的时候才创建；
不要在循环中使用 try…catch…，应该把其放在最外层；
使用带缓冲的输入输出流进行 IO 操作；
尽量使用 HashMap、ArrayList、StringBuilder，除非线程安全需要，否则不推荐使用 HashTable、Vector、StringBuffer，后三者由于使用同步机制而导致了性能开销；
尽量在合适的场合使用单例；
把一个基本数据类型转为字符串，基本数据类型.toString() 是最快的方式，String.valueOf(数据) 次之，数据 + "" 最慢；
使用 AS 自带的 Lint 来优化代码结构（什么，你不会？右键 module、目录或者文件，选择 Analyze -> Inspect Code）；
最后不要忘了内存泄漏的检测；

UI 控件缩写表

名称	缩写
Button	btn
CheckBox	cb
EditText	et
FrameLayout	fl
GridView	gv
ImageButton	ib
ImageView	iv
LinearLayout	ll
ListView	lv
ProgressBar	pb
RadioButtion	rb
RecyclerView	rv
RelativeLayout	rl
ScrollView	sv
SeekBar	sb
Spinner	spn
TextView	tv
ToggleButton	tb
VideoView	vv
WebView	wv

常见的英文单词缩写表

名称	缩写
average	avg
background	bg（主要用于布局和子布局的背景）
buffer	buf
control	ctrl
current	cur
default	def
delete	del
document	doc
error	err
escape	esc
icon	ic（主要用在 App 的图标）
increment	inc
information	info
initial	init
image	img
Internationalization	I18N
length	len
library	lib
message	msg
password	pwd
position	pos
previous	pre
selector	sel（主要用于某一 view 多种状态，不仅包括 ListView 中的 selector，还包括按钮的 selector）
server	srv
string	str
temporary	tmp
window	win

include、merge 、ViewStub：
1、布局重用<include />：
	<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"  
		android:orientation="vertical"   
		android:layout_width=”match_parent”  
		android:layout_height=”match_parent”  
		android:background="@color/app_bg"  
		android:gravity="center_horizontal">  
	  
		<include layout="@layout/titlebar"/>  
	  
		<TextView android:layout_width=”match_parent”  
				  android:layout_height="wrap_content"  
				  android:text="@string/hello"  
				  android:padding="10dp" />  
	  
		...  
	  
	</LinearLayout> 
	1)<include />标签可以使用单独的layout属性，这个也是必须使用的。
    2)可以使用其他属性。<include />标签若指定了ID属性，而你的layout也定义了ID，则你的layout的ID会被覆盖，解决方案。
    3)在include标签中所有的android:layout_*都是有效的，前提是必须要写layout_width和layout_height两个属性。
    4)布局中可以包含两个相同的include标签，引用时可以使用如下方法解决（参考）:
	View bookmarks_container_2 = findViewById(R.id.bookmarks_favourite);   
	bookmarks_container_2.findViewById(R.id.bookmarks_list); 
2、减少视图层级<merge />:
	<merge/>标签在UI的结构优化中起着非常重要的作用，它可以删减多余的层级，优化UI。
	<merge/>多用于替换FrameLayout或者当一个布局包含另一个时，<merge/>标签消除视图层次结构中多余的视图组。
	例如你的主布局文件是垂直布局，引入了一个垂直布局的include，这是如果include布局使用的LinearLayout就没意义了，
	使用的话反而减慢你的UI表现。这时可以使用<merge/>标签优化。
	<merge xmlns:android="http://schemas.android.com/apk/res/android">  
	  
		<Button  
			android:layout_width="fill_parent"   
			android:layout_height="wrap_content"  
			android:text="@string/add"/>  
	  
		<Button  
			android:layout_width="fill_parent"   
			android:layout_height="wrap_content"  
			android:text="@string/delete"/>  
	  
	</merge> 
	现在，当你添加该布局文件时(使用<include />标签)，系统忽略<merge />节点并且直接添加两个Button
3、需要时使用<ViewStub />
	<ViewStub />标签最大的优点是当你需要时才会加载，使用他并不会影响UI初始化时的性能。
	各种不常用的布局想进度条、显示错误消息等可以使用<ViewStub />标签，以减少内存使用量，
	加快渲染速度。<ViewStub />是一个不可见的，大小为0的View。<ViewStub />标签使用如下：
	<ViewStub  
    android:id="@+id/stub_import"  
    android:inflatedId="@+id/panel_import"  
    android:layout="@layout/progress_overlay"  
    android:layout_width="fill_parent"  
    android:layout_height="wrap_content"  
    android:layout_gravity="bottom" />
	当你想加载布局时，可以使用下面其中一种方法：
	((ViewStub) findViewById(R.id.stub_import)).setVisibility(View.VISIBLE);  
	// or  
	View importPanel = ((ViewStub) findViewById(R.id.stub_import)).inflate();
	当调用inflate()函数的时候，ViewStub被引用的资源替代，并且返回引用的view。 这样程序可以直接得到引用的view而不用再次调用函数findViewById()来查找了。
	注：ViewStub目前有个缺陷就是还不支持 <merge /> 标签。























