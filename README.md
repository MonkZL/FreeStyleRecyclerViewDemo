# FreeStyleRecyclerViewDemo

<P><h3>Add it in your root build.gradle at the end of repositories:</h3></p>

<pre><code>	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}</code></pre>
    
<P><h3> Add the dependency</h3></p>

   <pre><code>	dependencies {
	        compile 'com.github.Monkeynessss:FreeStyleRecyclerViewDemo:1.0.0'
	}
</code></pre>


<P><h3>使用方式：</h3></p>

   <pre><code> 
   &lt;zl.com.freestylerecyclerview.FreeStyleRecyclerView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        app:defaultHeight="100dp"
        app:targetHeight="300dp" /&gt;
	</code></pre>

    
     
   <pre><h4> defaultHeight是最小高度</h4></pre>   
   <pre><h4> targetHeight是最大高度</h4></pre>   

