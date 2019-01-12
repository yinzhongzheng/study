
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
     (adsbygoogle = window.adsbygoogle || []).push({
          google_ad_client: "ca-pub-9394337417063147",
          enable_page_level_ads: true
     });
</script>
<base target="_blank">
<meta name="baidu_union_verify" content="d30f7acd6a9a378cc0aeb0bd0ddfe30d">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<script>
  (adsbygoogle = window.adsbygoogle || []).push({
    google_ad_client: "ca-pub-9394337417063147",
    enable_page_level_ads: true
  });
</script>
<title>Java NIO系列教程（十一） Pipe | 并发编程网 &#8211; ifeve.com</title>
<meta name="description" content="让天下没有难学的技术" />

<link rel="alternate" type="application/rss+xml" title="并发编程网 &#8211; ifeve.com RSS Feed" href="http://ifeve.com/feed/" />
<link rel="alternate" type="application/atom+xml" title="并发编程网 &#8211; ifeve.com Atom Feed" href="http://ifeve.com/feed/atom/" />
<link rel="pingback" href="http://ifeve.com/xmlrpc.php" />

<link rel="stylesheet" href="http://ifeve.com/wp-content/themes/flat/style.css" type="text/css" />
<link rel="stylesheet" href="http://ifeve.com/wp-content/themes/flat/comment-style.css" type="text/css" />


<link rel='dns-prefetch' href='//ajax.proxy.ustclug.org' />
<link rel='dns-prefetch' href='//s.w.org' />
<link rel="alternate" type="application/rss+xml" title="并发编程网 - ifeve.com &raquo; Java NIO系列教程（十一） Pipe评论Feed" href="http://ifeve.com/pipe/feed/" />
		<script type="text/javascript">
			window._wpemojiSettings = {"baseUrl":"https:\/\/s.w.org\/images\/core\/emoji\/11\/72x72\/","ext":".png","svgUrl":"https:\/\/s.w.org\/images\/core\/emoji\/11\/svg\/","svgExt":".svg","source":{"concatemoji":"http:\/\/ifeve.com\/wp-includes\/js\/wp-emoji-release.min.js?ver=5.0.3"}};
			!function(a,b,c){function d(a,b){var c=String.fromCharCode;l.clearRect(0,0,k.width,k.height),l.fillText(c.apply(this,a),0,0);var d=k.toDataURL();l.clearRect(0,0,k.width,k.height),l.fillText(c.apply(this,b),0,0);var e=k.toDataURL();return d===e}function e(a){var b;if(!l||!l.fillText)return!1;switch(l.textBaseline="top",l.font="600 32px Arial",a){case"flag":return!(b=d([55356,56826,55356,56819],[55356,56826,8203,55356,56819]))&&(b=d([55356,57332,56128,56423,56128,56418,56128,56421,56128,56430,56128,56423,56128,56447],[55356,57332,8203,56128,56423,8203,56128,56418,8203,56128,56421,8203,56128,56430,8203,56128,56423,8203,56128,56447]),!b);case"emoji":return b=d([55358,56760,9792,65039],[55358,56760,8203,9792,65039]),!b}return!1}function f(a){var c=b.createElement("script");c.src=a,c.defer=c.type="text/javascript",b.getElementsByTagName("head")[0].appendChild(c)}var g,h,i,j,k=b.createElement("canvas"),l=k.getContext&&k.getContext("2d");for(j=Array("flag","emoji"),c.supports={everything:!0,everythingExceptFlag:!0},i=0;i<j.length;i++)c.supports[j[i]]=e(j[i]),c.supports.everything=c.supports.everything&&c.supports[j[i]],"flag"!==j[i]&&(c.supports.everythingExceptFlag=c.supports.everythingExceptFlag&&c.supports[j[i]]);c.supports.everythingExceptFlag=c.supports.everythingExceptFlag&&!c.supports.flag,c.DOMReady=!1,c.readyCallback=function(){c.DOMReady=!0},c.supports.everything||(h=function(){c.readyCallback()},b.addEventListener?(b.addEventListener("DOMContentLoaded",h,!1),a.addEventListener("load",h,!1)):(a.attachEvent("onload",h),b.attachEvent("onreadystatechange",function(){"complete"===b.readyState&&c.readyCallback()})),g=c.source||{},g.concatemoji?f(g.concatemoji):g.wpemoji&&g.twemoji&&(f(g.twemoji),f(g.wpemoji)))}(window,document,window._wpemojiSettings);
		</script>
		<style type="text/css">
img.wp-smiley,
img.emoji {
	display: inline !important;
	border: none !important;
	box-shadow: none !important;
	height: 1em !important;
	width: 1em !important;
	margin: 0 .07em !important;
	vertical-align: -0.1em !important;
	background: none !important;
	padding: 0 !important;
}
</style>
<link rel='stylesheet' id='wpfp-css' href='http://ifeve.com/wp-content/plugins/wp-favorite-posts/wpfp.css' type='text/css' />
<link rel='stylesheet' id='yarppWidgetCss-css'  href='http://ifeve.com/wp-content/plugins/yet-another-related-posts-plugin/style/widget.css?ver=5.0.3' type='text/css' media='all' />
<link rel='stylesheet' id='jquery-ui-css'  href='//ajax.proxy.ustclug.org/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css?ver=5.0.3' type='text/css' media='all' />
<link rel='stylesheet' id='dashicons-css'  href='http://ifeve.com/wp-includes/css/dashicons.min.css?ver=5.0.3' type='text/css' media='all' />
<link rel='stylesheet' id='admin-bar-css'  href='http://ifeve.com/wp-includes/css/admin-bar.min.css?ver=5.0.3' type='text/css' media='all' />
<link rel='stylesheet' id='wp-block-library-css'  href='http://ifeve.com/wp-includes/css/dist/block-library/style.min.css?ver=5.0.3' type='text/css' media='all' />
<link rel='stylesheet' id='dwqa-style-css'  href='http://ifeve.com/wp-content/plugins/dw-question-answer/templates/assets/css/style.css?ver=180720161356' type='text/css' media='all' />
<link rel='stylesheet' id='dwqa-rtl-css'  href='http://ifeve.com/wp-content/plugins/dw-question-answer/templates/assets/css/rtl.css?ver=180720161356' type='text/css' media='all' />
<link rel='stylesheet' id='wp-postratings-css'  href='http://ifeve.com/wp-content/plugins/wp-postratings/css/postratings-css.css?ver=1.86.1' type='text/css' media='all' />
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/jquery/jquery.js?ver=1.12.4'></script>
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.4.1'></script>
<script type='text/javascript' src='http://ifeve.com/wp-content/plugins/wp-favorite-posts/wpfp.js?ver=5.0.3'></script>
<link rel='https://api.w.org/' href='http://ifeve.com/wp-json/' />
<link rel="EditURI" type="application/rsd+xml" title="RSD" href="http://ifeve.com/xmlrpc.php?rsd" />
<link rel="wlwmanifest" type="application/wlwmanifest+xml" href="http://ifeve.com/wp-includes/wlwmanifest.xml" />
<link rel='prev' title='Java NIO系列教程（九） ServerSocketChannel' href='http://ifeve.com/server-socket-channel/' />
<link rel='next' title='Java NIO系列教程（八） SocketChannel' href='http://ifeve.com/socket-channel/' />
<meta name="generator" content="WordPress 5.0.3" />
<link rel="canonical" href="http://ifeve.com/pipe/" />
<link rel='shortlink' href='http://ifeve.com/?p=5330' />
<link rel="alternate" type="application/json+oembed" href="http://ifeve.com/wp-json/oembed/1.0/embed?url=http%3A%2F%2Fifeve.com%2Fpipe%2F" />
<link rel="alternate" type="text/xml+oembed" href="http://ifeve.com/wp-json/oembed/1.0/embed?url=http%3A%2F%2Fifeve.com%2Fpipe%2F&#038;format=xml" />

<!-- StarBox - the Author Box for Humans 3.2.0, visit: http://wordpress.org/plugins/starbox/ -->
<!-- /StarBox - the Author Box for Humans -->

<link rel='stylesheet' id='ABHfrontend.min.css-css'  href='http://ifeve.com/wp-content/plugins/starbox//themes/fancy/css/frontend.min.css?ver=3.2.0' type='text/css' media='all' />
<script type='text/javascript' src='http://ifeve.com/wp-content/plugins/starbox//themes/fancy/js/frontend.min.js?ver=3.2.0'></script>
<link rel='stylesheet' id='ABHhidedefault.min.css-css'  href='http://ifeve.com/wp-content/plugins/starbox//themes/admin/css/hidedefault.min.css?ver=3.2.0' type='text/css' media='all' />
<link rel="amphtml" href="http://ifeve.com/pipe/amp/"><style type="text/css" media="print">#wpadminbar { display:none; }</style>
<style type="text/css" media="screen">
	html { margin-top: 32px !important; }
	* html body { margin-top: 32px !important; }
	@media screen and ( max-width: 782px ) {
		html { margin-top: 46px !important; }
		* html body { margin-top: 46px !important; }
	}
</style>
<style type="text/css" id="syntaxhighlighteranchor"></style>

<script type="text/javascript" src="http://ifeve.com/wp-content/themes/flat/js/jscript.js"></script>
<script type="text/javascript" src="http://ifeve.com/wp-content/themes/flat/js/comment.js"></script>
<script type="text/javascript" src="http://ifeve.com/wp-content/themes/flat/js/rollover.js"></script>

<!--[if IE 7]>
<link rel="stylesheet" href="http://ifeve.com/wp-content/themes/flat/ie7.css" type="text/css" />
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" href="http://ifeve.com/wp-content/themes/flat/ie8.css" type="text/css" />
<![endif]-->


<style type="text/css">
a, .post .title a:hover, .post_meta a:hover, #bread_crumb ul li a:hover, #previous_post a:hover, #next_post a:hover, .post a.more-link:hover, #right_col li a:hover, #copyright li a:hover, #archive_headline #keyword,
   #comments_wrapper a:hover, #comment_header_right .comment_switch_active a, #comment_header_right .comment_switch_active a:hover, #comment_pager .current
   { color:#00A19E; }

#no_post a.back:hover, #wp-calendar td a:hover, #wp-calendar #prev a:hover, #wp-calendar #next a:hover, .page_navi a:hover, #submit_comment:hover
 { background-color:#00A19E; }

#guest_info input:focus, #comment_textarea textarea:focus
 { border:1px solid #00A19E; }

a:hover
 { color:#BDC900; }

body { font-size:14px; }

#header { background:url(http://ifeve.com/wp-content/uploads/flat/bg.png) left top; }
.logo_text a { color:#F5F5F5; }
#site_description {  border-top:1px solid #BBBBBB; color:#D2D9CC; }

#header { top:28px; }
#main_content { top:28px; }

</style>

</head>

<body>

 <div id="header">
  <div class="header_menu">
    <ul id="menu-%e4%b8%bb%e8%8f%9c%e5%8d%95" class="menu"><li id="menu-item-16594" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-home menu-item-16594"><a href="http://ifeve.com">首页</a></li>
<li id="menu-item-3432" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-3432"><a href="http://ifeve.com/wp-login.php">登录</a></li>
<li id="menu-item-9219" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-9219"><a href="http://ifeve.com/category/java/">JAVA</a>
<ul class="sub-menu">
	<li id="menu-item-16108" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-16108"><a href="http://ifeve.com/java-io/">Java IO 教程</a></li>
	<li id="menu-item-5283" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-5283"><a href="http://ifeve.com/java-nio-all/">Java NIO 教程</a></li>
	<li id="menu-item-17514" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-17514"><a href="http://ifeve.com/java-reflection/">JAVA Reflection教程</a></li>
	<li id="menu-item-16239" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-16239"><a href="http://ifeve.com/java-network">Java 网络教程</a></li>
	<li id="menu-item-4416" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-4416"><a href="http://ifeve.com/java-concurrency-thread-directory/">Java并发和多线程</a></li>
	<li id="menu-item-2767" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2767"><a href="http://ifeve.com/jmm-cookbook/">Java内存模型指南</a></li>
	<li id="menu-item-2747" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2747"><a href="http://ifeve.com/jmm-faq/">Java内存模型FAQ</a></li>
	<li id="menu-item-2765" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2765"><a href="http://ifeve.com/syn-jmm/">同步与Java内存模型</a></li>
	<li id="menu-item-5789" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-5789"><a href="http://ifeve.com/oracle-java-concurrency-tutorial/">Oracle官方并发教程</a></li>
	<li id="menu-item-15316" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-15316"><a href="http://ifeve.com/jvm-optimize/">JVM性能优化系列</a></li>
	<li id="menu-item-15313" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-15313"><a href="http://ifeve.com/useful-jvm-flags/">JVM实用参数系列</a></li>
</ul>
</li>
<li id="menu-item-7938" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7938"><a href="http://ifeve.com/category/c/">C++</a></li>
<li id="menu-item-9220" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-9220"><a href="http://ifeve.com/category/framework/">Framework</a>
<ul class="sub-menu">
	<li id="menu-item-12798" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-12798"><a href="http://ifeve.com/disruptor/">Disruptor 框架指南</a></li>
	<li id="menu-item-12783" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-12783"><a title="Netty" href="http://ifeve.com/category/netty/">Netty</a></li>
	<li id="menu-item-12791" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-12791"><a href="http://ifeve.com/category/scala/">Scala</a></li>
	<li id="menu-item-12312" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-12312"><a href="http://ifeve.com/getting-started-with-stom-index/">Storm 入门</a></li>
	<li id="menu-item-9218" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-9218"><a href="http://ifeve.com/google-guava/">Guava 官方指南</a></li>
	<li id="menu-item-20471" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-20471"><a href="http://ifeve.com/velocity-guide/">Apache Velocity 官方指南</a></li>
	<li id="menu-item-21439" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-21439"><a href="http://ifeve.com/storm-translation/">Apache Storm 官方文档</a></li>
</ul>
</li>
<li id="menu-item-555" class="menu-item menu-item-type-taxonomy menu-item-object-category current-post-ancestor current-menu-parent current-post-parent menu-item-has-children menu-item-555"><a href="http://ifeve.com/category/concurrency-translation/">其他译文</a>
<ul class="sub-menu">
	<li id="menu-item-2777" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2777"><a href="http://ifeve.com/java-concurrent/">并发基础</a></li>
	<li id="menu-item-3340" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-3340"><a href="http://ifeve.com/aqs/">j.u.c同步框架</a></li>
	<li id="menu-item-2769" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2769"><a href="http://ifeve.com/fj/">Fork Join框架</a></li>
	<li id="menu-item-9221" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-9221"><a href="http://ifeve.com/doug-lea/">Doug Lea 论文</a></li>
	<li id="menu-item-4643" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-4643"><a href="http://ifeve.com/mechanical-sympathy/">Mechanical Sympathy</a></li>
	<li id="menu-item-6397" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-6397"><a href="http://ifeve.com/stm/">软件事务内存导论</a></li>
	<li id="menu-item-10845" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-10845"><a href="http://ifeve.com/jsr133-cn/">JSR133中文版</a></li>
</ul>
</li>
<li id="menu-item-103" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-103"><a href="http://ifeve.com/category/talk-concurrent/">本站原创</a>
<ul class="sub-menu">
	<li id="menu-item-2763" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2763"><a href="http://ifeve.com/talk-concurrency/">聊聊并发</a></li>
	<li id="menu-item-2753" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2753"><a href="http://ifeve.com/java-memory-model-0/">深入理解Java内存模型</a></li>
	<li id="menu-item-4862" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-4862"><a href="http://ifeve.com/from-javaeye-sys-struct/">从Java视角理解系统结构</a></li>
	<li id="menu-item-4627" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-4627"><a href="http://ifeve.com/ali-itu-agile/">阿里内贸团队敏捷实践</a></li>
</ul>
</li>
<li id="menu-item-7119" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-7119"><a href="http://ifeve.com/category/%e5%b9%b6%e5%8f%91%e4%b9%a6%e7%b1%8d/">并发书籍</a>
<ul class="sub-menu">
	<li id="menu-item-10846" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-10846"><a href="http://ifeve.com/art-of-java-concurrent-program/">《Java 并发编程的艺术》迷你书</a></li>
	<li id="menu-item-6042" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-6042"><a href="http://ifeve.com/perfbook/">《深入理解并行编程》</a></li>
	<li id="menu-item-9172" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-9172"><a href="http://ifeve.com/c-plus-plus-concurrency-in-action/">《C++ 并发编程》</a></li>
	<li id="menu-item-7785" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-7785"><a href="http://ifeve.com/java-7-concurrency-cookbook/">《Java 7 并发编程指南》</a></li>
	<li id="menu-item-7120" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-7120"><a href="http://ifeve.com/jvm-concurrency">《Java 虚拟机并发编程》</a></li>
	<li id="menu-item-12360" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-12360"><a href="http://ifeve.com/getting-started-with-stom-index/">《Storm入门》中文版</a></li>
</ul>
</li>
<li id="menu-item-17079" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-17079"><a href="http://ifeve.com/questions/">面试题</a></li>
<li id="menu-item-22180" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-22180"><a href="http://ifeve.com/my-favorite/">我的收藏</a></li>
<li id="menu-item-9171" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-9171"><a href="http://ifeve.com/need-you/">加入我们</a>
<ul class="sub-menu">
	<li id="menu-item-3246" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-3246"><a href="http://ifeve.com/contribute/">我要投稿</a></li>
	<li id="menu-item-6854" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-6854"><a href="http://ifeve.com/neitui/">内推阿里</a></li>
</ul>
</li>
<li id="menu-item-105" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-item-105"><a href="http://ifeve.com/about-site/">关于本站</a>
<ul class="sub-menu">
	<li id="menu-item-11306" class="menu-item menu-item-type-taxonomy menu-item-object-category menu-item-11306"><a href="http://ifeve.com/category/%e6%b4%bb%e5%8a%a8/">活动</a></li>
	<li id="menu-item-1487" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1487"><a href="http://ifeve.com/sitemap.html">文章归档</a></li>
	<li id="menu-item-29361" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-29361"><a href="http://ifeve.com/author/">本站作者</a></li>
	<li id="menu-item-131" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-131"><a href="http://ifeve.com/about-site/about-me/">关于清英</a></li>
	<li id="menu-item-7600" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-7600"><a href="http://ifeve.com/ads/">广告合作</a></li>
</ul>
</li>
</ul>    </div>
  <!-- logo -->
  <h1 class="logo_text"><a href="http://ifeve.com/">并发编程网 - ifeve.com</a></h1>
<h2 id='site_description'>让天下没有难学的技术</h2>
 </div>

 <div id="main_content" class="clearfix">

  <div id="left_col">
      <div id="bread_crumb">
    <ul class="clearfix">
     <li id="bc_home"><a href="http://ifeve.com/" title="首页">首页</a></li>
     <li id="bc_cat"><a href="http://ifeve.com/category/concurrency-translation/">并发译文</a></li>     <li class="last">Java NIO系列教程（十一） Pipe</li>
    </ul>
   </div>


   <div class="post_wrap clearfix" id="single">
    <div class="post">
     <h3 class="title"><span>Java NIO系列教程（十一） Pipe</span></h3>
     <div class="post_content">
      <p><a title="原文链接" href="http://tutorials.jenkov.com/java-nio/pipe.html" target="_blank">原文链接</a>     <b>作者：</b>Jakob Jenkov     <b>译者：</b>黄忠       <b>校对：</b>丁一</p>
<p>Java NIO 管道是2个线程之间的单向数据连接。<code>Pipe</code>有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。</p>
<p>这里是Pipe原理的图示：</p>
<p><img class="aligncenter size-full wp-image-5386" src="http://ifeve.com/wp-content/uploads/2013/06/pipe.bmp" alt="" /></p>
<p><span id="more-5330"></span></p>
<h3 id="createpipe">创建管道</h3>
<p>通过<code>Pipe.open()</code>方法打开管道。例如：</p>
<pre class="brush: java; title: ; notranslate" title="">
Pipe pipe = Pipe.open();
</pre>
<h3>向管道写数据</h3>
<p>要向管道写数据，需要访问sink通道。像这样：</p>
<pre class="brush: java; title: ; notranslate" title="">
Pipe.SinkChannel sinkChannel = pipe.sink();
</pre>
<p>通过调用SinkChannel的<code>write()</code>方法，将数据写入<code>SinkChannel</code>,像这样：</p>
<pre class="brush: java; title: ; notranslate" title="">
String newData = &quot;New String to write to file...&quot; + System.currentTimeMillis();
ByteBuffer buf = ByteBuffer.allocate(48);
buf.clear();
buf.put(newData.getBytes());

buf.flip();

while(buf.hasRemaining()) {
    sinkChannel.write(buf);
}

</pre>
<h3>从管道读取数据</h3>
<p>从读取管道的数据，需要访问source通道，像这样：</p>
<pre class="brush: java; title: ; notranslate" title="">
Pipe.SourceChannel sourceChannel = pipe.source();
</pre>
<p>调用source通道的<code>read()</code>方法来读取数据，像这样：</p>
<pre class="brush: java; title: ; notranslate" title="">
ByteBuffer buf = ByteBuffer.allocate(48);

int bytesRead = sourceChannel.read(buf);
</pre>
<p><code>read()</code>方法返回的int值会告诉我们多少字节被读进了缓冲区。</p>
<div style="margin-top: 15px; font-style: italic">
<p><strong>原创文章，转载请注明：</strong> 转载自<a href="http://ifeve.com/">并发编程网 &#8211; ifeve.com</a><strong>本文链接地址:</strong> <a href="http://ifeve.com/pipe/">Java NIO系列教程（十一） Pipe</a></p>
</div>
<p><a target="_blank" href="http://www.proxool.cn/"><img src="http://ifeve.com/wp-content/uploads/2018/09/cache-ads.png"/><br />
</a></p>

                         <div class="abh_box abh_box_down abh_box_"><ul class="abh_tabs"> <li class="abh_about abh_active"><a href="#abh_about">About</a></li> <li class="abh_posts"><a href="#abh_posts">Latest Posts</a></li></ul><div class="abh_tab_content"><section class="vcard abh_about_tab abh_tab" style="display:block"><div class="abh_image"><a href="http://weibo.com/u/1744855511" class="url" target="_blank" title="A黄忠" ><img alt='' src='http://gravatar.proxy.ustclug.org/avatar/30c973b0d9dfe67533ccb3ce84a565c8?s=80&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/30c973b0d9dfe67533ccb3ce84a565c8?s=160&#038;d=mm&#038;r=g 2x' class='avatar avatar-80 photo' height='80' width='80' /></a></div><div class="abh_social"> </div><div class="abh_text"><h3 class="fn name" ><a href="http://weibo.com/u/1744855511" class="url" target="_blank" >A黄忠</a></h3><div class="abh_job" ></div><div class="description note abh_description" >大街网算法组工程师，凭着自己的兴趣和爱好学习计算机技术。</div></div> </section><section class="abh_posts_tab abh_tab" ><div class="abh_image"><a href="http://weibo.com/u/1744855511" class="url" target="_blank" title="A黄忠" ><img alt='' src='http://gravatar.proxy.ustclug.org/avatar/30c973b0d9dfe67533ccb3ce84a565c8?s=80&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/30c973b0d9dfe67533ccb3ce84a565c8?s=160&#038;d=mm&#038;r=g 2x' class='avatar avatar-80 photo' height='80' width='80' /></a></div><div class="abh_social"> </div><div class="abh_text"><h4 >Latest posts by A黄忠 <span class="abh_allposts">(<a href="http://ifeve.com/author/A黄忠/">see all</a>)</span></h4><div class="abh_description note" ><ul>				<li>					<a href="http://ifeve.com/pipe/">Java NIO系列教程（十一） Pipe</a><span> - 2013年6月6日</span>				</li></ul></div></div> </section></div> </div><span class='wpfp-span'><img src='http://ifeve.com/wp-content/plugins/wp-favorite-posts/img/star.png' alt='Favorite' title='Favorite' class='wpfp-img' /><img src='http://ifeve.com/wp-content/plugins/wp-favorite-posts/img/loading.gif' alt='Loading' title='Loading' class='wpfp-hide wpfp-img' /><a class='wpfp-link' href='?wpfpaction=add&amp;postid=5330' title='添加本文到我的收藏' rel='nofollow'>添加本文到我的收藏</a></span><div class='yarpp-related'>
<h3>Related posts:</h3><ol>
<li><a href="http://ifeve.com/java-nio-vs-io/" rel="bookmark" title="Java NIO系列教程（十二） Java NIO与IO">Java NIO系列教程（十二） Java NIO与IO </a></li>
<li><a href="http://ifeve.com/datagram-channel/" rel="bookmark" title="Java NIO系列教程（十） Java NIO DatagramChannel">Java NIO系列教程（十） Java NIO DatagramChannel </a></li>
<li><a href="http://ifeve.com/channels/" rel="bookmark" title="Java NIO系列教程（二） Channel">Java NIO系列教程（二） Channel </a></li>
<li><a href="http://ifeve.com/buffers/" rel="bookmark" title="Java NIO系列教程（三） Buffer">Java NIO系列教程（三） Buffer </a></li>
<li><a href="http://ifeve.com/file-channel/" rel="bookmark" title="Java NIO系列教程（七） FileChannel">Java NIO系列教程（七） FileChannel </a></li>
<li><a href="http://ifeve.com/selectors/" rel="bookmark" title="Java NIO系列教程（六） Selector">Java NIO系列教程（六） Selector </a></li>
<li><a href="http://ifeve.com/socket-channel/" rel="bookmark" title="Java NIO系列教程（八） SocketChannel">Java NIO系列教程（八） SocketChannel </a></li>
<li><a href="http://ifeve.com/server-socket-channel/" rel="bookmark" title="Java NIO系列教程（九） ServerSocketChannel">Java NIO系列教程（九） ServerSocketChannel </a></li>
<li><a href="http://ifeve.com/java-nio-scattergather/" rel="bookmark" title="Java NIO系列教程（四） Scatter/Gather">Java NIO系列教程（四） Scatter/Gather </a></li>
<li><a href="http://ifeve.com/java-nio-path-2/" rel="bookmark" title="Java NIO系列教程（十 五）Java NIO Path">Java NIO系列教程（十 五）Java NIO Path </a></li>
<li><a href="http://ifeve.com/java-nio-channel-to-channel/" rel="bookmark" title="Java NIO系列教程（五） 通道之间的数据传输">Java NIO系列教程（五） 通道之间的数据传输 </a></li>
<li><a href="http://ifeve.com/overview/" rel="bookmark" title="Java NIO系列教程（一） Java NIO 概述">Java NIO系列教程（一） Java NIO 概述 </a></li>
<li><a href="http://ifeve.com/java-nio-asynchronousfilechannel/" rel="bookmark" title="Java NIO AsynchronousFileChannel">Java NIO AsynchronousFileChannel </a></li>
<li><a href="http://ifeve.com/java-sql-date/" rel="bookmark" title="Java Date Time 教程-java.sql.Date">Java Date Time 教程-java.sql.Date </a></li>
<li><a href="http://ifeve.com/java-nio-path/" rel="bookmark" title="《Java NIO教程》Java NIO Path">《Java NIO教程》Java NIO Path </a></li>
</ol>
</div>
           </div><!-- END .post_content -->
    </div>
    <div class="meta">
          <ul>
      <li class="post_date clearfix">
       <span class="date">06</span>
       <span class="month">Jun</span>
       <span class="year">2013</span>
      </li>

<li class="post_comment">
62,813 人阅读</li>

      <li class="post_author"><a href="http://ifeve.com/author/A黄忠/" title="由A黄忠发布" rel="author">A黄忠</a></li>      <li class="post_category"><a href="http://ifeve.com/category/concurrency-translation/" rel="category tag">并发译文</a></li>       <li class="post_category"><div id="post-ratings-5330" class="post-ratings" itemscope itemtype="http://schema.org/Article" data-nonce="92fa8474a8"><img id="rating_5330_1" src="http://ifeve.com/wp-content/plugins/wp-postratings/images/stars_crystal/rating_on.gif" alt="好烂啊" title="好烂啊" onmouseover="current_rating(5330, 1, '好烂啊');" onmouseout="ratings_off(4.2, 0, 0);" onclick="rate_post();" onkeypress="rate_post();" style="cursor: pointer; border: 0px;" /><img id="rating_5330_2" src="http://ifeve.com/wp-content/plugins/wp-postratings/images/stars_crystal/rating_on.gif" alt="没价值" title="没价值" onmouseover="current_rating(5330, 2, '没价值');" onmouseout="ratings_off(4.2, 0, 0);" onclick="rate_post();" onkeypress="rate_post();" style="cursor: pointer; border: 0px;" /><img id="rating_5330_3" src="http://ifeve.com/wp-content/plugins/wp-postratings/images/stars_crystal/rating_on.gif" alt="凑合看看" title="凑合看看" onmouseover="current_rating(5330, 3, '凑合看看');" onmouseout="ratings_off(4.2, 0, 0);" onclick="rate_post();" onkeypress="rate_post();" style="cursor: pointer; border: 0px;" /><img id="rating_5330_4" src="http://ifeve.com/wp-content/plugins/wp-postratings/images/stars_crystal/rating_on.gif" alt="还不错" title="还不错" onmouseover="current_rating(5330, 4, '还不错');" onmouseout="ratings_off(4.2, 0, 0);" onclick="rate_post();" onkeypress="rate_post();" style="cursor: pointer; border: 0px;" /><img id="rating_5330_5" src="http://ifeve.com/wp-content/plugins/wp-postratings/images/stars_crystal/rating_off.gif" alt="超赞" title="超赞" onmouseover="current_rating(5330, 5, '超赞');" onmouseout="ratings_off(4.2, 0, 0);" onclick="rate_post();" onkeypress="rate_post();" style="cursor: pointer; border: 0px;" /> (<strong>29</strong> votes, average: <strong>4.24</strong> out of 5)<br /><span class="post-ratings-text" id="ratings_5330_text"></span><meta itemprop="headline" content="Java NIO系列教程（十一） Pipe" /><meta itemprop="description" content="原文链接&nbsp; &nbsp; &nbsp;作者：Jakob Jenkov &nbsp; &nbsp;&nbsp;译者：黄忠 &nbsp; &nbsp; &nbsp;&nbsp;校对：丁一

Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

这里是Pipe原理的图示：




创建管道
通过Pipe.open()方法打开管道。例如：



向管道写数据
要向..." /><meta itemprop="datePublished" content="2013-06-06T16:16:07+00:00" /><meta itemprop="dateModified" content="2014-07-16T15:52:03+00:00" /><meta itemprop="url" content="http://ifeve.com/pipe/" /><meta itemprop="author" content="A黄忠" /><meta itemprop="mainEntityOfPage" content="http://ifeve.com/pipe/" /><div style="display: none;" itemprop="publisher" itemscope itemtype="https://schema.org/Organization"><meta itemprop="name" content="并发编程网 - ifeve.com" /><div itemprop="logo" itemscope itemtype="https://schema.org/ImageObject"><meta itemprop="url" content="" /></div></div><div style="display: none;" itemprop="aggregateRating" itemscope itemtype="http://schema.org/AggregateRating"><meta itemprop="bestRating" content="5" /><meta itemprop="worstRating" content="1" /><meta itemprop="ratingValue" content="4.24" /><meta itemprop="ratingCount" content="29" /></div></div><div id="post-ratings-5330-loading" class="post-ratings-loading">
			<img src="http://ifeve.com/wp-content/plugins/wp-postratings/images/loading.gif" width="16" height="16" class="post-ratings-image" />Loading...</div></li>       <li class="post_comment"><a href="http://ifeve.com/pipe/#comments">6 条评论</a></li>           </ul>
         </div>
   </div><!-- END .post_wrap -->


      <div id="comments_wrapper">





<div id="comment_header" class="clearfix">

 <ul id="comment_header_left">
  <li id="add_comment"><a href="#respond">发表评论</a></li>
  <li id="comment_feed"><a href="http://ifeve.com/comments/feed/" title="RSS订阅评论">RSS订阅评论</a></li>
 </ul>

 <ul id="comment_header_right">
   <li id="trackback_closed">Trackback 关闭</li>
   <li id="comment_closed">评论 (6)</li>
 </ul>


</div><!-- END #comment_header -->

<div id="comments">

 <div id="comment_area">
  <!-- start commnet -->
  <ol class="commentlist">

 <li class="comment guest-comment" id="comment-1374">
  <div class="comment-meta">
   <div class="comment-meta-left">
  <img alt='' src='http://gravatar.proxy.ustclug.org/avatar/dd01a30a9c20e4d778dd547d901aa224?s=35&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/dd01a30a9c20e4d778dd547d901aa224?s=70&#038;d=mm&#038;r=g 2x' class='avatar avatar-35 photo' height='35' width='35' />
    <ul class="comment-name-date">
     <li class="comment-name">
<a id="commentauthor-1374" class="url guest-url" href="http://www.liuxinglanyue.com" rel="external nofollow">

liuxinglanyue
</a>
     </li>
     <li class="comment-date">2013/06/17 4:04下午</li>
    </ul>
   </div>

   <ul class="comment-act">
    <li class="comment-reply"><a rel='nofollow' class='comment-reply-link' href='http://ifeve.com/pipe/?replytocom=1374#respond' onclick='return addComment.moveForm( "comment-content-1374", "1374", "respond", "5330" )' aria-label='回复给liuxinglanyue'><span><span>回复</span></span></a></li>
    <li class="comment-quote"><a href="javascript:void(0);" onclick="MGJS_CMT.quote('commentauthor-1374', 'comment-1374', 'comment-content-1374', 'comment');">引用</a></li>
       </ul>

  </div>
  <div class="comment-content" id="comment-content-1374">
    <p>int bytesRead = inChannel.read(buf);<br />
inChannel应该为sourceChannel</p>
  </div>

</li><!-- #comment-## -->

 <li class="comment guest-comment" id="comment-26643">
  <div class="comment-meta">
   <div class="comment-meta-left">
  <img alt='' src='http://gravatar.proxy.ustclug.org/avatar/25255766048fa9af9fe9820607140bb6?s=35&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/25255766048fa9af9fe9820607140bb6?s=70&#038;d=mm&#038;r=g 2x' class='avatar avatar-35 photo' height='35' width='35' />
    <ul class="comment-name-date">
     <li class="comment-name">
<span id="commentauthor-26643">

johnny_w
</span>
     </li>
     <li class="comment-date">2016/01/21 4:55下午</li>
    </ul>
   </div>

   <ul class="comment-act">
    <li class="comment-reply"><a rel='nofollow' class='comment-reply-link' href='http://ifeve.com/pipe/?replytocom=26643#respond' onclick='return addComment.moveForm( "comment-content-26643", "26643", "respond", "5330" )' aria-label='回复给johnny_w'><span><span>回复</span></span></a></li>
    <li class="comment-quote"><a href="javascript:void(0);" onclick="MGJS_CMT.quote('commentauthor-26643', 'comment-26643', 'comment-content-26643', 'comment');">引用</a></li>
       </ul>

  </div>
  <div class="comment-content" id="comment-content-26643">
    <p>没有列举 sinkChannel 以及 sourceChannel 和我们要连接远程的 socketChannel有什么关系，以及怎么将sinkChannel设置成我们要连接远程服务器的那个channel？</p>
  </div>

<ul class="children">

 <li class="comment guest-comment" id="comment-27575">
  <div class="comment-meta">
   <div class="comment-meta-left">
  <img alt='' src='http://gravatar.proxy.ustclug.org/avatar/afb325e418125f8c774a517defa7442d?s=35&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/afb325e418125f8c774a517defa7442d?s=70&#038;d=mm&#038;r=g 2x' class='avatar avatar-35 photo' height='35' width='35' />
    <ul class="comment-name-date">
     <li class="comment-name">
<span id="commentauthor-27575">

er916340246
</span>
     </li>
     <li class="comment-date">2017/04/13 2:35下午</li>
    </ul>
   </div>

   <ul class="comment-act">
    <li class="comment-reply"><a rel='nofollow' class='comment-reply-link' href='http://ifeve.com/pipe/?replytocom=27575#respond' onclick='return addComment.moveForm( "comment-content-27575", "27575", "respond", "5330" )' aria-label='回复给er916340246'><span><span>回复</span></span></a></li>
    <li class="comment-quote"><a href="javascript:void(0);" onclick="MGJS_CMT.quote('commentauthor-27575', 'comment-27575', 'comment-content-27575', 'comment');">引用</a></li>
       </ul>

  </div>
  <div class="comment-content" id="comment-content-27575">
    <p>同问</p>
  </div>

<ul class="children">

 <li class="comment guest-comment" id="comment-28007">
  <div class="comment-meta">
   <div class="comment-meta-left">
  <img alt='' src='http://gravatar.proxy.ustclug.org/avatar/4d044e080b39bdddab53bed35c2f7ccd?s=35&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/4d044e080b39bdddab53bed35c2f7ccd?s=70&#038;d=mm&#038;r=g 2x' class='avatar avatar-35 photo' height='35' width='35' />
    <ul class="comment-name-date">
     <li class="comment-name">
<span id="commentauthor-28007">

cnzl0123
</span>
     </li>
     <li class="comment-date">2017/10/26 6:22下午</li>
    </ul>
   </div>

   <ul class="comment-act">
    <li class="comment-reply"><a rel='nofollow' class='comment-reply-link' href='http://ifeve.com/pipe/?replytocom=28007#respond' onclick='return addComment.moveForm( "comment-content-28007", "28007", "respond", "5330" )' aria-label='回复给cnzl0123'><span><span>回复</span></span></a></li>
    <li class="comment-quote"><a href="javascript:void(0);" onclick="MGJS_CMT.quote('commentauthor-28007', 'comment-28007', 'comment-content-28007', 'comment');">引用</a></li>
       </ul>

  </div>
  <div class="comment-content" id="comment-content-28007">
    <p>socketChannel客户端和服务器之前的数据交互，Pipe的sinkChannel和sourceChannel是服务器内部的两个不同线程之间的数据交互</p>
  </div>

</li><!-- #comment-## -->
</ul><!-- .children -->
</li><!-- #comment-## -->
</ul><!-- .children -->
</li><!-- #comment-## -->

 <li class="comment guest-comment" id="comment-27574">
  <div class="comment-meta">
   <div class="comment-meta-left">
  <img alt='' src='http://gravatar.proxy.ustclug.org/avatar/afb325e418125f8c774a517defa7442d?s=35&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/afb325e418125f8c774a517defa7442d?s=70&#038;d=mm&#038;r=g 2x' class='avatar avatar-35 photo' height='35' width='35' />
    <ul class="comment-name-date">
     <li class="comment-name">
<span id="commentauthor-27574">

er916340246
</span>
     </li>
     <li class="comment-date">2017/04/13 2:34下午</li>
    </ul>
   </div>

   <ul class="comment-act">
    <li class="comment-reply"><a rel='nofollow' class='comment-reply-link' href='http://ifeve.com/pipe/?replytocom=27574#respond' onclick='return addComment.moveForm( "comment-content-27574", "27574", "respond", "5330" )' aria-label='回复给er916340246'><span><span>回复</span></span></a></li>
    <li class="comment-quote"><a href="javascript:void(0);" onclick="MGJS_CMT.quote('commentauthor-27574', 'comment-27574', 'comment-content-27574', 'comment');">引用</a></li>
       </ul>

  </div>
  <div class="comment-content" id="comment-content-27574">
    <p>“Java NIO 管道是2个线程之间的单向数据连接” 但是Channel却是双向的，这样不矛盾吗？他们是什么关系呢？</p>
  </div>

</li><!-- #comment-## -->

 <li class="comment guest-comment" id="comment-27576">
  <div class="comment-meta">
   <div class="comment-meta-left">
  <img alt='' src='http://gravatar.proxy.ustclug.org/avatar/afb325e418125f8c774a517defa7442d?s=35&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/afb325e418125f8c774a517defa7442d?s=70&#038;d=mm&#038;r=g 2x' class='avatar avatar-35 photo' height='35' width='35' />
    <ul class="comment-name-date">
     <li class="comment-name">
<span id="commentauthor-27576">

er916340246
</span>
     </li>
     <li class="comment-date">2017/04/13 2:40下午</li>
    </ul>
   </div>

   <ul class="comment-act">
    <li class="comment-reply"><a rel='nofollow' class='comment-reply-link' href='http://ifeve.com/pipe/?replytocom=27576#respond' onclick='return addComment.moveForm( "comment-content-27576", "27576", "respond", "5330" )' aria-label='回复给er916340246'><span><span>回复</span></span></a></li>
    <li class="comment-quote"><a href="javascript:void(0);" onclick="MGJS_CMT.quote('commentauthor-27576', 'comment-27576', 'comment-content-27576', 'comment');">引用</a></li>
       </ul>

  </div>
  <div class="comment-content" id="comment-content-27576">
    <p>刚明白了一个是通道一个是管道看错了 alert(&#8220;明白&#8221;)</p>
  </div>

</li><!-- #comment-## -->
  </ol>
  <!-- comments END -->


 </div><!-- #comment-list END -->


 <div id="trackback_area">
 <!-- start trackback -->
  <!-- trackback end -->
 </div><!-- #trackbacklist END -->





 <fieldset class="comment_form_wrapper" id="respond">

    <div id="cancel_comment_reply"><a rel="nofollow" id="cancel-comment-reply-link" href="/pipe/#respond" style="display:none;">点击这里取消回复。</a></div>

  <form action="http://ifeve.com/wp-comments-post.php" method="post" id="commentform">


   <div id="comment_user_login">
    <p>您好，<a href="http://ifeve.com/wp-admin/profile.php">yzz__</a>。<span><a href="http://ifeve.com/wp-login.php?action=logout&amp;redirect_to=http%3A%2F%2Fifeve.com%2Fpipe%2F&amp;_wpnonce=64db622b5f" title="登出">[ 登出 ]</a></span></p>
   </div><!-- #comment-user-login END -->


   <div id="comment_textarea">
    <textarea name="comment" id="comment" cols="50" rows="10" tabindex="4"></textarea>
   </div>

   <div id="submit_comment_wrapper">
    <p style="display: none;"><input type="hidden" id="akismet_comment_nonce" name="akismet_comment_nonce" value="8ca9c990b7" /></p><p style="display: none;"><input type="hidden" id="ak_js" name="ak_js" value="195"/></p>    <input name="submit" type="submit" id="submit_comment" tabindex="5" value="发表评论" title="发表评论" alt="发表评论" />
   </div>
   <div id="input_hidden_field">
        <input type='hidden' name='comment_post_ID' value='5330' id='comment_post_ID' />
<input type='hidden' name='comment_parent' id='comment_parent' value='0' />
       </div>

  </form>

 </fieldset><!-- #comment-form-area END -->

</div><!-- #comment end -->   </div>

      <div id="previous_next_post_single">
    <div class="clearfix">
     <p id="previous_post"><a href="http://ifeve.com/server-socket-channel/" rel="prev">Java NIO系列教程（九） ServerSocketChannel</a></p>     <p id="next_post"><a href="http://ifeve.com/socket-channel/" rel="next">Java NIO系列教程（八） SocketChannel</a></p>    </div>
   </div>


  </div><!-- END #left_col -->

  <div id="container"></div>

    <div id="right_col">


<div class="side_box clearfix" id="side_meta_content">

  <ul id="social_link" class="clearfix">
    <li class="rss_button"><a class="target_blank" href="http://ifeve.com/feed/">rss</a></li>
       </ul>

  <div id="search_area">
    <form method="get" id="searchform" action="http://ifeve.com/">
   <div><input id="search_button" class="rollover" type="image" src="http://ifeve.com/wp-content/themes/flat/img/search_button.gif" alt="搜索一下" title="搜索一下" /></div>
   <div><input id="search_input" type="text" value="搜索一下" name="s" onfocus="if (this.value == '搜索一下') this.value = '';" onblur="if (this.value == '') this.value = '搜索一下';" /></div>
  </form>
   </div>

</div>


<div id="side_top">

 <div class="widget_text side_box widget_custom_html" id="custom_html-3">
<div class="textwidget custom-html-widget">

<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 首页－右上－2 -->
<ins class="adsbygoogle" style="display: inline-block; width: 336px; height: 280px;" data-ad-client="ca-pub-9394337417063147" data-ad-slot="8872369516"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>





<!-- <img src="http://ifeve.com/wp-content/uploads/2018/06/qrcode_for_gh_87611be4f171_258.jpg"/> -->

<!-- <studyio target="_blank" href="http://edu.bjlemon.com:81/index.html"><img src="http://ifeve.com/wp-content/uploads/2018/06/qrcode_for_gh_87611be4f171_258.jpg"/>
 --></div></div>
		<div class="side_box widget_recent_entries" id="recent-posts-2">
		<h3 class="side_title">近期文章</h3>
		<ul>
											<li>
					<a href="http://ifeve.com/%e8%af%91%e3%80%8athe-part-time-parliament%e3%80%8b-%e7%bb%88%e4%ba%8e%e8%af%bb%e6%87%82%e4%ba%86paxos%e5%8d%8f%e8%ae%ae%ef%bc%81/">译《The Part-Time Parliament》——终于读懂了Paxos协议！</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8aknative%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e7%bf%bb%e8%af%91%e9%82%80%e8%af%b7/">《knative官方文档》翻译邀请</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8anginx%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e5%a6%82%e4%bd%95%e5%ae%89%e8%a3%85nginx/">《Nginx官方文档》如何安装nginx</a>
									</li>
											<li>
					<a href="http://ifeve.com/juc1/">JUC包中的分而治之策略-为提高性能而生</a>
									</li>
											<li>
					<a href="http://ifeve.com/hyperledger-fabric-model/">《Hyperledger Fabric官方文档》Hyperledger Fabric Model</a>
									</li>
											<li>
					<a href="http://ifeve.com/hyperledger-fabric%e7%9a%84%e5%9f%ba%e6%9c%ac%e5%8a%9f%e8%83%bd/">《Hyperledger Fabric官方文档》Hyperledger Fabric的基本功能</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahyperledger-fabric%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e4%bb%80%e4%b9%88%e6%98%afhyperledger-fabric-2/">《Hyperledger Fabric官方文档》什么是Hyperledger Fabric?</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahyperledger-fabric%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e5%8c%ba%e5%9d%97%e9%93%be%e7%9a%84%e7%bd%91%e7%bb%9c/">《Hyperledger Fabric官方文档》区块链的网络</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e4%b8%ba%e8%87%aa%e5%b7%b1%e6%90%ad%e5%bb%ba%e4%b8%80%e4%b8%aa%e5%88%86%e5%b8%83%e5%bc%8f-im%e5%8d%b3%e6%97%b6%e9%80%9a%e8%ae%af-%e7%b3%bb%e7%bb%9f/">为自己搭建一个分布式 IM(即时通讯) 系统</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahyperledger-fabric%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e4%b8%ba%e4%bb%80%e4%b9%88%e5%8c%ba%e5%9d%97%e9%93%be%e7%9a%84%e7%94%a8%e5%a4%84%e8%bf%99%e4%b9%88%e5%b9%bf/">《Hyperledger Fabric官方文档》为什么区块链的用处这么广</a>
									</li>
											<li>
					<a href="http://ifeve.com/countdownlatch%e6%ba%90%e7%a0%81%e8%a7%a3%e6%9e%90/">CountDownLatch源码解析</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e5%9b%be%e8%a7%a3java%e5%b9%b6%e5%8f%91%e4%b8%8a/">图解java并发(上)</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahibernate%e5%bf%ab%e9%80%9f%e5%bc%80%e5%a7%8b-%e6%89%b9%e9%87%8f%e5%a4%84%e7%90%86%e3%80%8b/">《Hibernate快速开始 – 批量处理》</a>
									</li>
											<li>
					<a href="http://ifeve.com/java-nio%e7%b3%bb%e5%88%97%e6%95%99%e7%a8%8b%ef%bc%88%e5%8d%81%e5%85%ad%ef%bc%89-java-nio-files/">Java NIO系列教程（十六） Java NIO Files</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e7%ac%ac%e4%ba%8c%e7%ab%a0-%e8%87%aa%e5%8a%a8%e5%86%85%e5%ad%98%e7%ae%a1%e7%90%86%e6%9c%ba%e5%88%b6/">第二章. 自动内存管理机制</a>
									</li>
											<li>
					<a href="http://ifeve.com/java-%e8%af%bb%e5%86%99%e9%94%81%e7%9a%84%e5%ae%9e%e7%8e%b0%e5%8e%9f%e7%90%86/">Java&#8211;读写锁的实现原理</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e4%b8%80%e6%ac%a1-hashset-%e6%89%80%e5%bc%95%e8%b5%b7%e7%9a%84%e5%b9%b6%e5%8f%91%e9%97%ae%e9%a2%98/">一次 HashSet 所引起的并发问题</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahyperledger-fabric%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e4%bb%80%e4%b9%88%e6%98%afhyperledger-fabric/">《Hyperledger Fabric官方文档》什么是Hyperledger Fabric?</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e5%ae%9e%e9%99%85%e9%a1%b9%e7%9b%ae%e4%b8%ad%e8%bf%90%e7%94%a8%e8%b4%a3%e4%bb%bb%e9%93%be%e6%a8%a1%e5%bc%8f/">实际项目中运用责任链模式</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e4%bb%a3%e7%a0%81%e8%b5%b0%e6%9f%a5%e5%a6%82%e4%bd%95%e4%bf%9d%e8%af%81%e8%bd%af%e4%bb%b6%e8%b4%a8%e9%87%8f/">代码走查如何保证软件质量</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahyperledger-fabric%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e4%b9%8b%e5%85%b3%e9%94%ae%e6%a6%82%e5%bf%b5%e7%bf%bb%e8%af%91%e9%82%80%e8%af%b7/">《Hyperledger Fabric官方文档》之关键概念翻译邀请</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e5%be%ae%e6%9c%8d%e5%8a%a1%e9%9b%86%e6%88%90%e6%b5%8b%e8%af%95%e8%87%aa%e5%8a%a8%e5%8c%96%e6%8e%a2%e7%b4%a2/">微服务集成测试自动化探索</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e3%80%8ahyperledger-fabric%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3%e3%80%8b%e4%bb%8b%e7%bb%8d/">《Hyperledger Fabric官方文档》介绍</a>
									</li>
											<li>
					<a href="http://ifeve.com/tcp-window-scaling/">TCP 滑动窗口 与窗口缩放因子(Window Scaling)</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e4%b8%8d%e5%8f%af%e9%94%99%e8%bf%87%e7%9a%84cms%e5%ad%a6%e4%b9%a0%e7%ac%94%e8%ae%b0/">不可错过的CMS学习笔记</a>
									</li>
											<li>
					<a href="http://ifeve.com/network/">白话网络通讯</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e5%9f%ba%e4%ba%8eredis%e7%9a%84%e5%88%86%e5%b8%83%e5%bc%8f%e9%94%81/">基于redis的分布式锁</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e7%8c%ab%e7%8b%97%e9%98%9f%e5%88%97%e7%9a%84%e5%86%8d%e8%a7%a3/">猫狗队列的再解</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e5%a6%82%e4%bd%95%e6%88%90%e4%b8%ba%e4%b8%80%e4%bd%8d%e3%80%8c%e4%b8%8d%e9%82%a3%e4%b9%88%e5%b7%ae%e3%80%8d%e7%9a%84%e7%a8%8b%e5%ba%8f%e5%91%98/">如何成为一位「不那么差」的程序员</a>
									</li>
											<li>
					<a href="http://ifeve.com/%e6%b6%88%e6%81%af%e9%98%9f%e5%88%97%e4%ba%8c%e4%b8%89%e4%ba%8b/">消息队列二三事</a>
									</li>
					</ul>
		</div>
<div class="widget_text side_box widget_custom_html" id="custom_html-5">
<div class="textwidget custom-html-widget"><script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 首页－右上－3 -->
<ins class="adsbygoogle"
     style="display:inline-block;width:336px;height:280px"
     data-ad-client="ca-pub-9394337417063147"
     data-ad-slot="4721371513"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>

<a target="_blank" href="http://bombus.cn/"><img src="http://ifeve.com/wp-content/uploads/2019/01/WechatIMG5812.png"/>
</a></div></div>
<div class="side_box widget_views" id="views-4">
<h3 class="side_title">热门文章</h3>
<ul>
<li><a href="http://ifeve.com/pipe/"  title="Google Guava官方教程（中文版）">Google Guava官方教程（中文版）</a> 641,396 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO系列教程（一） Java NIO 概述">Java NIO系列教程（一） Java NIO 概述</a> 469,399 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java并发性和多线程介绍目录">Java并发性和多线程介绍目录</a> 339,189 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO 系列教程">Java NIO 系列教程</a> 333,480 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO系列教程（十二） Java NIO与IO">Java NIO系列教程（十二） Java NIO与IO</a> 267,404 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java8初体验（二）Stream语法详解">Java8初体验（二）Stream语法详解</a> 239,182 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO系列教程（六） Selector">Java NIO系列教程（六） Selector</a> 233,616 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO系列教程（三） Buffer">Java NIO系列教程（三） Buffer</a> 231,659 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO系列教程（二） Channel">Java NIO系列教程（二） Channel</a> 225,671 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="69道Spring面试题和答案">69道Spring面试题和答案</a> 186,363 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="《Storm入门》中文版">《Storm入门》中文版</a> 183,993 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Netty 5用户指南">Netty 5用户指南</a> 178,346 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="并发框架Disruptor译文">并发框架Disruptor译文</a> 169,071 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="面试题">面试题</a> 160,276 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java 7 并发编程指南中文版">Java 7 并发编程指南中文版</a> 147,025 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="Java NIO系列教程（八） SocketChannel">Java NIO系列教程（八） SocketChannel</a> 146,804 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="[Google Guava] 2.3-强大的集合工具类：ja...">[Google Guava] 2.3-强大的集合工具类：ja...</a> 144,969 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="[Google Guava] 3-缓存">[Google Guava] 3-缓存</a> 144,722 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="如何创建并运行java线程">如何创建并运行java线程</a> 137,243 人阅读 </li><li><a href="http://ifeve.com/pipe/"  title="聊聊并发（三）Java线程池的分析和使用">聊聊并发（三）Java线程池的分析和使用</a> 135,697 人阅读 </li></ul>
</div>
<div class="widget_text side_box widget_custom_html" id="custom_html-4">
<div class="textwidget custom-html-widget"><script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 首页－右边－4 -->
<ins class="adsbygoogle"
     style="display:inline-block;width:336px;height:280px"
     data-ad-client="ca-pub-9394337417063147"
     data-ad-slot="6198104715"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script></div></div>
</div>
<div id="side_middle" class="clearfix">
 <div id="side_left">
  <div class="side_box_short widget_categories" id="categories-2">
<h3 class="side_title">分类目录</h3>
		<ul>
	<li class="cat-item cat-item-415"><a href="http://ifeve.com/category/android/" >Android</a> (3)
</li>
	<li class="cat-item cat-item-1101"><a href="http://ifeve.com/category/architecture/" >Architecture</a> (2)
</li>
	<li class="cat-item cat-item-289"><a href="http://ifeve.com/category/c/" >C++</a> (12)
</li>
	<li class="cat-item cat-item-366"><a href="http://ifeve.com/category/cpu-2/" >CPU</a> (2)
</li>
	<li class="cat-item cat-item-73"><a href="http://ifeve.com/category/framework/" >Framework</a> (74)
<ul class='children'>
	<li class="cat-item cat-item-387"><a href="http://ifeve.com/category/framework/akka/" >akka</a> (20)
</li>
</ul>
</li>
	<li class="cat-item cat-item-458"><a href="http://ifeve.com/category/go/" >GO</a> (6)
</li>
	<li class="cat-item cat-item-617"><a href="http://ifeve.com/category/groovy/" >groovy</a> (6)
</li>
	<li class="cat-item cat-item-310"><a href="http://ifeve.com/category/guava-2/" >guava</a> (24)
</li>
	<li class="cat-item cat-item-69"><a href="http://ifeve.com/category/java/" >JAVA</a> (934)
</li>
	<li class="cat-item cat-item-127"><a href="http://ifeve.com/category/jvm/" >JVM</a> (47)
</li>
	<li class="cat-item cat-item-300"><a href="http://ifeve.com/category/linux/" >linux</a> (10)
</li>
	<li class="cat-item cat-item-1061"><a href="http://ifeve.com/category/microservices/" >microservices</a> (1)
</li>
	<li class="cat-item cat-item-280"><a href="http://ifeve.com/category/netty/" >Netty</a> (32)
</li>
	<li class="cat-item cat-item-1107"><a href="http://ifeve.com/category/python/" >Python</a> (2)
</li>
	<li class="cat-item cat-item-632"><a href="http://ifeve.com/category/react/" >react</a> (6)
</li>
	<li class="cat-item cat-item-623"><a href="http://ifeve.com/category/redis/" >redis</a> (25)
</li>
	<li class="cat-item cat-item-166"><a href="http://ifeve.com/category/scala/" >Scala</a> (11)
</li>
	<li class="cat-item cat-item-672"><a href="http://ifeve.com/category/spark/" >spark</a> (19)
</li>
	<li class="cat-item cat-item-1011"><a href="http://ifeve.com/category/spring/" >Spring</a> (23)
</li>
	<li class="cat-item cat-item-388"><a href="http://ifeve.com/category/storm/" >storm</a> (44)
</li>
	<li class="cat-item cat-item-620"><a href="http://ifeve.com/category/thinking/" >thinking</a> (3)
</li>
	<li class="cat-item cat-item-485"><a href="http://ifeve.com/category/velocity/" >Velocity</a> (10)
</li>
	<li class="cat-item cat-item-279"><a href="http://ifeve.com/category/web/" >Web</a> (18)
</li>
	<li class="cat-item cat-item-980"><a href="http://ifeve.com/category/zookeeper/" >zookeeper</a> (1)
</li>
	<li class="cat-item cat-item-148"><a href="http://ifeve.com/category/notice/" >公告</a> (5)
</li>
	<li class="cat-item cat-item-1103"><a href="http://ifeve.com/category/%e5%8c%ba%e5%9d%97%e9%93%be/" >区块链</a> (3)
</li>
	<li class="cat-item cat-item-378"><a href="http://ifeve.com/category/bigdata/" >大数据</a> (35)
</li>
	<li class="cat-item cat-item-138"><a href="http://ifeve.com/category/%e5%a5%bd%e6%96%87%e6%8e%a8%e8%8d%90/" >好文推荐</a> (34)
</li>
	<li class="cat-item cat-item-244"><a href="http://ifeve.com/category/%e5%b9%b6%e5%8f%91%e4%b9%a6%e7%b1%8d/" >并发书籍</a> (97)
</li>
	<li class="cat-item cat-item-29"><a href="http://ifeve.com/category/concurrency-translation/" title="发布国外并发编程相关文献译文。">并发译文</a> (416)
</li>
	<li class="cat-item cat-item-979"><a href="http://ifeve.com/category/%e6%84%9f%e6%82%9f/" >感悟</a> (5)
</li>
	<li class="cat-item cat-item-7"><a href="http://ifeve.com/category/ask/" title="可以在这里发表并发编程的问题。如何发表？用QQ登陆，然后点击“新建文章”，管理员审核后就可以发表。">技术问答</a> (12)
</li>
	<li class="cat-item cat-item-147"><a href="http://ifeve.com/category/%e6%95%8f%e6%8d%b7%e7%ae%a1%e7%90%86/" >敏捷管理</a> (6)
</li>
	<li class="cat-item cat-item-1"><a href="http://ifeve.com/category/talk-concurrent/" >本站原创</a> (89)
</li>
	<li class="cat-item cat-item-367"><a href="http://ifeve.com/category/%e6%9e%b6%e6%9e%84/" >架构</a> (38)
</li>
	<li class="cat-item cat-item-305"><a href="http://ifeve.com/category/%e6%b4%bb%e5%8a%a8/" >活动</a> (6)
</li>
	<li class="cat-item cat-item-414"><a href="http://ifeve.com/category/%e7%bd%91%e7%bb%9c/" >网络</a> (7)
</li>
	<li class="cat-item cat-item-1100"><a href="http://ifeve.com/category/%e9%9d%a2%e8%af%95/" >面试</a> (1)
</li>
		</ul>
</div>
 </div>
 <div id="side_right">
  <div class="side_box_short widget_tag_cloud" id="tag_cloud-4">
<h3 class="side_title">标签</h3>
<div class="tagcloud"><a href="http://ifeve.com/tag/actor/" class="tag-cloud-link tag-link-226 tag-link-position-1" style="font-size: 12.382608695652pt;" aria-label="actor (17个项目)">actor</a>
<a href="http://ifeve.com/tag/basic/" class="tag-cloud-link tag-link-213 tag-link-position-2" style="font-size: 11.408695652174pt;" aria-label="Basic (14个项目)">Basic</a>
<a href="http://ifeve.com/tag/classes/" class="tag-cloud-link tag-link-251 tag-link-position-3" style="font-size: 8.6086956521739pt;" aria-label="classes (8个项目)">classes</a>
<a href="http://ifeve.com/tag/collections/" class="tag-cloud-link tag-link-260 tag-link-position-4" style="font-size: 10.191304347826pt;" aria-label="collections (11个项目)">collections</a>
<a href="http://ifeve.com/tag/concurrency/" class="tag-cloud-link tag-link-111 tag-link-position-5" style="font-size: 20.052173913043pt;" aria-label="concurrency (77个项目)">concurrency</a>
<a href="http://ifeve.com/tag/concurrent/" class="tag-cloud-link tag-link-214 tag-link-position-6" style="font-size: 11.04347826087pt;" aria-label="Concurrent (13个项目)">Concurrent</a>
<a href="http://ifeve.com/tag/concurrent-data-structure/" class="tag-cloud-link tag-link-322 tag-link-position-7" style="font-size: 11.04347826087pt;" aria-label="concurrent data structure (13个项目)">concurrent data structure</a>
<a href="http://ifeve.com/tag/concurrenthashmap/" class="tag-cloud-link tag-link-61 tag-link-position-8" style="font-size: 8pt;" aria-label="ConcurrentHashMap (7个项目)">ConcurrentHashMap</a>
<a href="http://ifeve.com/tag/customizing/" class="tag-cloud-link tag-link-250 tag-link-position-9" style="font-size: 8.6086956521739pt;" aria-label="Customizing (8个项目)">Customizing</a>
<a href="http://ifeve.com/tag/executor/" class="tag-cloud-link tag-link-177 tag-link-position-10" style="font-size: 13.113043478261pt;" aria-label="Executor (20个项目)">Executor</a>
<a href="http://ifeve.com/tag/executor-framework/" class="tag-cloud-link tag-link-231 tag-link-position-11" style="font-size: 12.626086956522pt;" aria-label="Executor framework (18个项目)">Executor framework</a>
<a href="http://ifeve.com/tag/faq/" class="tag-cloud-link tag-link-39 tag-link-position-12" style="font-size: 11.04347826087pt;" aria-label="faq (13个项目)">faq</a>
<a href="http://ifeve.com/tag/fork/" class="tag-cloud-link tag-link-232 tag-link-position-13" style="font-size: 10.55652173913pt;" aria-label="fork (12个项目)">fork</a>
<a href="http://ifeve.com/tag/forkjoin/" class="tag-cloud-link tag-link-224 tag-link-position-14" style="font-size: 9.2173913043478pt;" aria-label="Fork/Join (9个项目)">Fork/Join</a>
<a href="http://ifeve.com/tag/fork-join/" class="tag-cloud-link tag-link-53 tag-link-position-15" style="font-size: 8.6086956521739pt;" aria-label="fork join (8个项目)">fork join</a>
<a href="http://ifeve.com/tag/framework/" class="tag-cloud-link tag-link-1018 tag-link-position-16" style="font-size: 14.208695652174pt;" aria-label="Framework (25个项目)">Framework</a>
<a href="http://ifeve.com/tag/functional-programming/" class="tag-cloud-link tag-link-460 tag-link-position-17" style="font-size: 9.704347826087pt;" aria-label="Functional Programming (10个项目)">Functional Programming</a>
<a href="http://ifeve.com/tag/guava/" class="tag-cloud-link tag-link-309 tag-link-position-18" style="font-size: 12.382608695652pt;" aria-label="Guava (17个项目)">Guava</a>
<a href="http://ifeve.com/tag/io/" class="tag-cloud-link tag-link-129 tag-link-position-19" style="font-size: 14.452173913043pt;" aria-label="IO (26个项目)">IO</a>
<a href="http://ifeve.com/tag/java/" class="tag-cloud-link tag-link-1016 tag-link-position-20" style="font-size: 22pt;" aria-label="JAVA (110个项目)">JAVA</a>
<a href="http://ifeve.com/tag/java8/" class="tag-cloud-link tag-link-342 tag-link-position-21" style="font-size: 11.04347826087pt;" aria-label="java8 (13个项目)">java8</a>
<a href="http://ifeve.com/tag/jmm/" class="tag-cloud-link tag-link-31 tag-link-position-22" style="font-size: 15.547826086957pt;" aria-label="jmm (32个项目)">jmm</a>
<a href="http://ifeve.com/tag/join/" class="tag-cloud-link tag-link-233 tag-link-position-23" style="font-size: 10.55652173913pt;" aria-label="join (12个项目)">join</a>
<a href="http://ifeve.com/tag/jvm/" class="tag-cloud-link tag-link-1019 tag-link-position-24" style="font-size: 13.35652173913pt;" aria-label="JVM (21个项目)">JVM</a>
<a href="http://ifeve.com/tag/lock/" class="tag-cloud-link tag-link-17 tag-link-position-25" style="font-size: 14.452173913043pt;" aria-label="lock (26个项目)">lock</a>
<a href="http://ifeve.com/tag/memory-barriers/" class="tag-cloud-link tag-link-75 tag-link-position-26" style="font-size: 9.704347826087pt;" aria-label="Memory Barriers (10个项目)">Memory Barriers</a>
<a href="http://ifeve.com/tag/netty/" class="tag-cloud-link tag-link-1027 tag-link-position-27" style="font-size: 13.6pt;" aria-label="Netty (22个项目)">Netty</a>
<a href="http://ifeve.com/tag/nio/" class="tag-cloud-link tag-link-1021 tag-link-position-28" style="font-size: 12.382608695652pt;" aria-label="NIO (17个项目)">NIO</a>
<a href="http://ifeve.com/tag/oauth-2-0/" class="tag-cloud-link tag-link-380 tag-link-position-29" style="font-size: 8.6086956521739pt;" aria-label="OAuth 2.0 (8个项目)">OAuth 2.0</a>
<a href="http://ifeve.com/tag/pattern-matching/" class="tag-cloud-link tag-link-167 tag-link-position-30" style="font-size: 8.6086956521739pt;" aria-label="pattern-matching (8个项目)">pattern-matching</a>
<a href="http://ifeve.com/tag/redis/" class="tag-cloud-link tag-link-624 tag-link-position-31" style="font-size: 8.6086956521739pt;" aria-label="redis (8个项目)">redis</a>
<a href="http://ifeve.com/tag/scala/" class="tag-cloud-link tag-link-1023 tag-link-position-32" style="font-size: 13.6pt;" aria-label="Scala (22个项目)">Scala</a>
<a href="http://ifeve.com/tag/service-mesh/" class="tag-cloud-link tag-link-1063 tag-link-position-33" style="font-size: 8.6086956521739pt;" aria-label="service mesh (8个项目)">service mesh</a>
<a href="http://ifeve.com/tag/slf4j/" class="tag-cloud-link tag-link-813 tag-link-position-34" style="font-size: 8.6086956521739pt;" aria-label="slf4j (8个项目)">slf4j</a>
<a href="http://ifeve.com/tag/spark/" class="tag-cloud-link tag-link-634 tag-link-position-35" style="font-size: 12.382608695652pt;" aria-label="spark (17个项目)">spark</a>
<a href="http://ifeve.com/tag/spark%e5%ae%98%e6%96%b9%e6%96%87%e6%a1%a3/" class="tag-cloud-link tag-link-635 tag-link-position-36" style="font-size: 12.382608695652pt;" aria-label="spark官方文档 (17个项目)">spark官方文档</a>
<a href="http://ifeve.com/tag/stm/" class="tag-cloud-link tag-link-199 tag-link-position-37" style="font-size: 10.55652173913pt;" aria-label="stm (12个项目)">stm</a>
<a href="http://ifeve.com/tag/storm/" class="tag-cloud-link tag-link-351 tag-link-position-38" style="font-size: 16.521739130435pt;" aria-label="Storm (39个项目)">Storm</a>
<a href="http://ifeve.com/tag/synchronization/" class="tag-cloud-link tag-link-216 tag-link-position-39" style="font-size: 11.652173913043pt;" aria-label="synchronization (15个项目)">synchronization</a>
<a href="http://ifeve.com/tag/synchronized/" class="tag-cloud-link tag-link-15 tag-link-position-40" style="font-size: 9.704347826087pt;" aria-label="Synchronized (10个项目)">Synchronized</a>
<a href="http://ifeve.com/tag/thread/" class="tag-cloud-link tag-link-11 tag-link-position-41" style="font-size: 17.130434782609pt;" aria-label="thread (44个项目)">thread</a>
<a href="http://ifeve.com/tag/tomcat/" class="tag-cloud-link tag-link-347 tag-link-position-42" style="font-size: 9.2173913043478pt;" aria-label="tomcat (9个项目)">tomcat</a>
<a href="http://ifeve.com/tag/volatile/" class="tag-cloud-link tag-link-25 tag-link-position-43" style="font-size: 11.408695652174pt;" aria-label="volatile (14个项目)">volatile</a>
<a href="http://ifeve.com/tag/%e5%a4%9a%e7%ba%bf%e7%a8%8b/" class="tag-cloud-link tag-link-197 tag-link-position-44" style="font-size: 10.191304347826pt;" aria-label="多线程 (11个项目)">多线程</a>
<a href="http://ifeve.com/tag/%e5%b9%b6%e5%8f%91%e8%af%91%e6%96%87%ef%bc%8cjava-%ef%bc%8cmaven/" class="tag-cloud-link tag-link-1034 tag-link-position-45" style="font-size: 16.15652173913pt;" aria-label="并发译文，Java ，Maven (36个项目)">并发译文，Java ，Maven</a></div>
</div>
 </div>
</div>
<div id="side_bottom">
 <div class="widget_text side_box widget_custom_html" id="custom_html-2">
<div class="textwidget custom-html-widget"><script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 摩天大楼 -->
<ins class="adsbygoogle"
     style="display:inline-block;width:300px;height:600px"
     data-ad-client="ca-pub-9394337417063147"
     data-ad-slot="6358703113"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>

<!--
<script type="text/javascript">
    /*自适应 创建于 June 30, 2016*/
    var cpro_id = "u2690738";

<script type="text/javascript" src="http://cpro.baidustatic.com/cpro/ui/i.js"></script>
 -->

<!-- CNZZ -->
<script src="http://s22.cnzz.com/stat.php?id=5003572&web_id=5003572&show=pic" language="JavaScript"></script></div></div>
</div>

  </div>

  <div id="footer">

<ul id="copyright">
    <li>版权所有 &copy;&nbsp; <a href="http://ifeve.com/">并发编程网 &#8211; ifeve.com</a></li>
    <li>ICP号: <a href="http://www.miibeian.gov.cn" target="_blank">浙ICP备12046809号</a></li>
 <li>
<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=33010602005873" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"/>浙公网安备 33010602005873号</a></li>
   </ul>



 </div>


 </div><!-- END #main_content -->

  <p id="return_top"><a href="#header">return top</a></p>

<script type='text/javascript' src='http://ifeve.com/wp-content/plugins/syntaxhighlighter/syntaxhighlighter2/scripts/shCore.js?ver=2.1.364'></script>
<script type='text/javascript' src='http://ifeve.com/wp-content/plugins/syntaxhighlighter/syntaxhighlighter2/scripts/shBrushJava.js?ver=2.1.364'></script>
<script type='text/javascript'>
	(function(){
		var corecss = document.createElement('link');
		var themecss = document.createElement('link');
		var corecssurl = "http://ifeve.com/wp-content/plugins/syntaxhighlighter/syntaxhighlighter2/styles/shCore.css?ver=2.1.364";
		if ( corecss.setAttribute ) {
				corecss.setAttribute( "rel", "stylesheet" );
				corecss.setAttribute( "type", "text/css" );
				corecss.setAttribute( "href", corecssurl );
		} else {
				corecss.rel = "stylesheet";
				corecss.href = corecssurl;
		}
		document.getElementsByTagName("head")[0].insertBefore( corecss, document.getElementById("syntaxhighlighteranchor") );
		var themecssurl = "http://ifeve.com/wp-content/plugins/syntaxhighlighter/syntaxhighlighter2/styles/shThemeEclipse.css?ver=2.1.364";
		if ( themecss.setAttribute ) {
				themecss.setAttribute( "rel", "stylesheet" );
				themecss.setAttribute( "type", "text/css" );
				themecss.setAttribute( "href", themecssurl );
		} else {
				themecss.rel = "stylesheet";
				themecss.href = themecssurl;
		}
		//document.getElementById("syntaxhighlighteranchor").appendChild(themecss);
		document.getElementsByTagName("head")[0].insertBefore( themecss, document.getElementById("syntaxhighlighteranchor") );
	})();
	SyntaxHighlighter.config.clipboardSwf = 'http://ifeve.com/wp-content/plugins/syntaxhighlighter/syntaxhighlighter2/scripts/clipboard.swf';
	SyntaxHighlighter.config.strings.expandSource = 'show source';
	SyntaxHighlighter.config.strings.viewSource = 'view source';
	SyntaxHighlighter.config.strings.copyToClipboard = 'copy to clipboard';
	SyntaxHighlighter.config.strings.copyToClipboardConfirmation = 'The code is in your clipboard now';
	SyntaxHighlighter.config.strings.print = 'print';
	SyntaxHighlighter.config.strings.help = '?';
	SyntaxHighlighter.config.strings.alert = 'SyntaxHighlighter\n\n';
	SyntaxHighlighter.config.strings.noBrush = 'Can\'t find brush for: ';
	SyntaxHighlighter.config.strings.brushNotHtmlScript = 'Brush wasn\'t configured for html-script option: ';
	SyntaxHighlighter.defaults['pad-line-numbers'] = true;
	SyntaxHighlighter.all();

	// Infinite scroll support
	if ( typeof( jQuery ) !== 'undefined' ) {
		jQuery( function( $ ) {
			$( document.body ).on( 'post-load', function() {
				SyntaxHighlighter.highlight();
			} );
		} );
	}
</script>
<link rel='stylesheet' id='yarppRelatedCss-css'  href='http://ifeve.com/wp-content/plugins/yet-another-related-posts-plugin/style/related.css?ver=5.0.3' type='text/css' media='all' />
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/jquery/ui/core.min.js?ver=1.11.4'></script>
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/jquery/ui/widget.min.js?ver=1.11.4'></script>
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/jquery/ui/tabs.min.js?ver=1.11.4'></script>
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/admin-bar.min.js?ver=5.0.3'></script>
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/comment-reply.min.js?ver=5.0.3'></script>
<script type='text/javascript'>
/* <![CDATA[ */
var ratingsL10n = {"plugin_url":"http:\/\/ifeve.com\/wp-content\/plugins\/wp-postratings","ajax_url":"http:\/\/ifeve.com\/wp-admin\/admin-ajax.php","text_wait":"Please rate only 1 item at a time.","image":"stars_crystal","image_ext":"gif","max":"5","show_loading":"1","show_fading":"1","custom":"0"};
var ratings_mouseover_image=new Image();ratings_mouseover_image.src="http://ifeve.com/wp-content/plugins/wp-postratings/images/stars_crystal/rating_over.gif";;
/* ]]> */
</script>
<script type='text/javascript' src='http://ifeve.com/wp-content/plugins/wp-postratings/js/postratings-js.js?ver=1.86.1'></script>
<script type='text/javascript'>
/* <![CDATA[ */
var viewsCacheL10n = {"admin_ajax_url":"http:\/\/ifeve.com\/wp-admin\/admin-ajax.php","post_id":"5330"};
/* ]]> */
</script>
<script type='text/javascript' src='http://ifeve.com/wp-content/plugins/wp-postviews/postviews-cache.js?ver=1.68'></script>
<script type='text/javascript' src='http://ifeve.com/wp-includes/js/wp-embed.min.js?ver=5.0.3'></script>
<script async="async" type='text/javascript' src='http://ifeve.com/wp-content/plugins/akismet/_inc/form.js?ver=4.1'></script>
		<div id="wpadminbar" class="nojq nojs">
							<a class="screen-reader-shortcut" href="#wp-toolbar" tabindex="1">跳至工具栏</a>
						<div class="quicklinks" id="wp-toolbar" role="navigation" aria-label="工具栏" tabindex="0">
				<ul id="wp-admin-bar-root-default" class="ab-top-menu">
		<li id="wp-admin-bar-wp-logo" class="menupop"><a class="ab-item" aria-haspopup="true" href="http://ifeve.com/wp-admin/about.php"><span class="ab-icon"></span><span class="screen-reader-text">关于WordPress</span></a><div class="ab-sub-wrapper"><ul id="wp-admin-bar-wp-logo-default" class="ab-submenu">
		<li id="wp-admin-bar-about"><a class="ab-item" href="http://ifeve.com/wp-admin/about.php">关于WordPress</a>		</li></ul><ul id="wp-admin-bar-wp-logo-external" class="ab-sub-secondary ab-submenu">
		<li id="wp-admin-bar-wporg"><a class="ab-item" href="https://cn.wordpress.org/">WordPress.org</a>		</li>
		<li id="wp-admin-bar-documentation"><a class="ab-item" href="https://codex.wordpress.org/">文档</a>		</li>
		<li id="wp-admin-bar-support-forums"><a class="ab-item" href="http://zh-cn.forums.wordpress.org/">支持论坛</a>		</li>
		<li id="wp-admin-bar-feedback"><a class="ab-item" href="http://zh-cn.forums.wordpress.org/forum/suggestions">反馈</a>		</li></ul></div>		</li>
		<li id="wp-admin-bar-site-name" class="menupop"><a class="ab-item" aria-haspopup="true" href="http://ifeve.com/wp-admin/">并发编程网 - ifeve.com</a><div class="ab-sub-wrapper"><ul id="wp-admin-bar-site-name-default" class="ab-submenu">
		<li id="wp-admin-bar-dashboard"><a class="ab-item" href="http://ifeve.com/wp-admin/">仪表盘</a>		</li></ul></div>		</li>
		<li id="wp-admin-bar-comments"><a class="ab-item" href="http://ifeve.com/wp-admin/edit-comments.php"><span class="ab-icon"></span><span class="ab-label awaiting-mod pending-count count-0" aria-hidden="true">0</span><span class="screen-reader-text">0条评论待审</span></a>		</li>
		<li id="wp-admin-bar-new-content" class="menupop"><a class="ab-item" aria-haspopup="true" href="http://ifeve.com/wp-admin/post-new.php"><span class="ab-icon"></span><span class="ab-label">新建</span></a><div class="ab-sub-wrapper"><ul id="wp-admin-bar-new-content-default" class="ab-submenu">
		<li id="wp-admin-bar-new-post"><a class="ab-item" href="http://ifeve.com/wp-admin/post-new.php">文章</a>		</li>
		<li id="wp-admin-bar-new-dwqa-question"><a class="ab-item" href="http://ifeve.com/wp-admin/post-new.php?post_type=dwqa-question">问题</a>		</li>
		<li id="wp-admin-bar-new-dwqa-answer"><a class="ab-item" href="http://ifeve.com/wp-admin/post-new.php?post_type=dwqa-answer">回答</a>		</li>
		<li id="wp-admin-bar-new-email"><a class="ab-item" href="http://ifeve.com/wp-admin/post-new.php?post_type=email">Email</a>		</li>
		<li id="wp-admin-bar-new-email_log"><a class="ab-item" href="http://ifeve.com/wp-admin/post-new.php?post_type=email_log">Email Log</a>		</li></ul></div>		</li></ul><ul id="wp-admin-bar-top-secondary" class="ab-top-secondary ab-top-menu">
		<li id="wp-admin-bar-search" class="admin-bar-search"><div class="ab-item ab-empty-item" tabindex="-1"><form action="http://ifeve.com/" method="get" id="adminbarsearch"><input class="adminbar-input" name="s" id="adminbar-search" type="text" value="" maxlength="150" /><label for="adminbar-search" class="screen-reader-text">搜索</label><input type="submit" class="adminbar-button" value="搜索"/></form></div>		</li>
		<li id="wp-admin-bar-my-account" class="menupop with-avatar"><a class="ab-item" aria-haspopup="true" href="http://ifeve.com/wp-admin/profile.php">嗨，<span class="display-name">yzz__</span><img alt='' src='http://gravatar.proxy.ustclug.org/avatar/6e126dfc1d97f936e53dbada8ab5b67c?s=26&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/6e126dfc1d97f936e53dbada8ab5b67c?s=52&#038;d=mm&#038;r=g 2x' class='avatar avatar-26 photo' height='26' width='26' /></a><div class="ab-sub-wrapper"><ul id="wp-admin-bar-user-actions" class="ab-submenu">
		<li id="wp-admin-bar-user-info"><a class="ab-item" tabindex="-1" href="http://ifeve.com/wp-admin/profile.php"><img alt='' src='http://gravatar.proxy.ustclug.org/avatar/6e126dfc1d97f936e53dbada8ab5b67c?s=64&#038;d=mm&#038;r=g' srcset='http://gravatar.proxy.ustclug.org/avatar/6e126dfc1d97f936e53dbada8ab5b67c?s=128&#038;d=mm&#038;r=g 2x' class='avatar avatar-64 photo' height='64' width='64' /><span class='display-name'>yzz__</span></a>		</li>
		<li id="wp-admin-bar-edit-profile"><a class="ab-item" href="http://ifeve.com/wp-admin/profile.php">编辑我的个人资料</a>		</li>
		<li id="wp-admin-bar-logout"><a class="ab-item" href="http://ifeve.com/wp-login.php?action=logout&#038;_wpnonce=64db622b5f">登出</a>		</li></ul></div>		</li></ul>			</div>
						<a class="screen-reader-shortcut" href="http://ifeve.com/wp-login.php?action=logout&#038;_wpnonce=64db622b5f">登出</a>
					</div>

		</body>
</html>

<script src="http://ajax.useso.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

<script src="http://coolshell.cn//wp-content/themes/inove/js/jquery.bpopup-0.8.0.min.js"></script>

<!-- Dynamic page generated in 0.932 seconds. -->
<!-- Page not cached by WP Super Cache. Check your settings page. Not caching requests by known users. (See Advanced Settings page) -->