<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
%>
<html>
	<!--
		作者：ascleft@163.com
		 时间：2017-04-29
		 描述：
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>看到啥，评论啥</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<!--<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>-->
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->
		<!-- Vue.js  -->
		<!--<script src="../../js/vue.min.js"></script>-->

		<!-- YXN  -->
		<script src="../../js/jquery-3.2.1.min.js"></script>
		<script src="<%=path %>/js/jquery-3.2.1.min.js"></script>

		<!-- local html  -->
		<link href="../../css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>
		
		<script src="../../js/init_sca.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>
		
		<script src="<%=path %>/js/init_sca.js"></script>

		<script type="application/javascript">
			var url_get_get_random_word = "<%=path %>/Talk/GetRandomWord.action";
			var string_random_word = "";

			$().ready(function() {
				talk();
			})

			function talk() {
				$.ajax({
					cache: true,
					type: "POST",
					url: url_get_get_random_word,
					data: $('#talkForm').serialize(),
					async: false,
					error: function(request) {
						string_random_word = "无法与服务器建立通信";
						update();
						copy_words();
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("succ" == resp.ERRDESC) {
							string_random_word = resp.data;
						} else {
							string_random_word += "服务器通讯异常";
						}
						update();
						copy_words();
					}
				});
			}

			function update() {
				var talk = document.getElementById("random_word");
				talk.textContent = string_random_word;
				var bar = document.getElementById("progress_bar");
				bar.style.display = "none";
			}

			function copy_words() {
				document.getElementById("random_word").select();

				document.execCommand("copy", false, null);
			}

			//				Materialize.toast("已复制到剪贴板");

			//微信分享朋友圈
			function shareFriend() {
				WeixinJSBridge.invoke('sendAppMessage', {
					"appid": "",
					"img_url": "http://tva4.sinaimg.cn/crop.0.0.180.180.180/568e48a1jw1e8qgp5bmzyj2050050aa8.jpg",
					"img_width": "200",
					"img_height": "200",
					"link": "喵喵喵？",
					"desc": "刚开发的，让我看看你们都看到了什么",
					"title": "看到啥评论啥"
				}, function(res) {
					//_report('send_msg', res.err_msg);
				})
			}

			//微信分享好友
			function shareTimeline() {
				WeixinJSBridge.invoke('shareTimeline', {
					"img_url": imgUrl,
					"img_width": "200",
					"img_height": "200",
					"link": "喵喵喵？",
					"desc": "刚开发的，让我看看你们都看到了什么",
					"title": "看到啥评论啥"
				}, function(res) {
					//_report('timeline', res.err_msg);
				});
			}

			var dataForWeixin = {
				appId: "",
				MsgImg: "Christmas/201012189457639.gif", //显示图片
				TLImg: "Christmas/201012189457639.gif", //显示图片
				url: "Christmas/6.html?stra=!u738B!u4F1F", //跳转地址
				title: "将我的思念和祝福送给您,颐养源祝大家圣诞快乐", //标题内容
				desc: "将我的思念和祝福送给您,颐养源祝大家圣诞快乐", //描述内容
				fakeid: "",
				callback: function() {}
			};
		</script>

	</head>

	<body>
		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l12">
						<div class="card ">
							<div class="card-content grey-text">
								<p id="random_word">正在占卜......</p>
								<div class="progress" id="progress_bar">
									<div class="indeterminate"></div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<h5 class="center-align">				
						<img class="hoverable"
						src="http://pan.baidu.com/share/qrcode?w=150&h=150&url=http://111.198.72.177:8029/CtrlCenter/Talk/GetRandomWordPage.action"/>
					</h5>
				</div>
			</div>
		</div>
	</body>

</html>