<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String SystemCore =(String) session.getAttribute("SystemCore");
	String SystemName =(String) session.getAttribute("SystemName");
	String SystemVersion=(String) session.getAttribute("SystemVersion");
%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>鸿安计算中心</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Vue.js-->
		<!--<script src="https://unpkg.com/vue/dist/vue.js"></script>-->
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../img/CodeMartrix/main/weisuomeng.jpg" rel="shortcut icon" />

		<link href="../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../js/materialize.js"></script>
		<script src="../js/init.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_1.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>
	</head>

	<body>

		<nav class="white" role="navigation">
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo ">
					<%=SystemCore%>
				</a>
				<ul class="right hide-on-med-and-down">
					<li>
						<a href="#"> </a>
					</li>
				</ul>
				<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i> </a>
			</div>
		</nav>

		<div id="index-banner" class="parallax-container">
			<div class="section no-pad-bot">
				<div class="container">
					<h1 class="header center light"><%=SystemName%></h1>
					<div class="row center">
						<h5 class="header col s12 light">眼光放得长远一些，看到的东西也会多一些,生活也就会过得更有意义一点</h5>
					</div>
				</div>
			</div>
			<div class="parallax">
				<img src="../img/global/wallpaper/bg_material_2.jpg">
			</div>
		</div>

		<div class="container">
			<div class="section">
				<div class="row">
					<div class="col s12 m12 l4">
						<div class="card hoverable">
							<div class="card-image">
								<img src="../img/seed7/card/card_m_tailor_1.jpg"> <span class="card-title "></span>
							</div>
							<div class="card-content ">
								<p>优纤衬衫订单信息采集系统</p>
							</div>
							<div class="card-action ">
								<a href="/CtrlCenter/LTYX/Tailor/TailorForm/Pro.action">进入</a>
							</div>
						</div>
					</div>

					<div class="col s6 m6 l4">
						<div class="card hoverable">
							<div class="card-image">
								<img src="../img/seed7/card/card_m_mobile_1.jpg"> <span class="card-title ">移动终端控制中心</span>
							</div>
							<div class="card-content">
								<p>手机网页及APP通用内容</p>
							</div>
							<div class=" card-action ">
								<a href="# ">进入</a>
							</div>
						</div>
					</div>
					<div class="col s6 m6 l4">
						<div class="card hoverable">
							<div class="card-image">
								<img src="../img/seed7/card/card_m_k3cloud_1.jpg"> <span class="card-title ">K3接口控制中心</span>
							</div>
							<div class="card-content ">
								<p>鲁泰库存接口对外开放内容</p>
							</div>
							<div class="card-action ">
								<a href="./K3/index.jsp">进入</a>
							</div>
						</div>
					</div>

				</div>

			</div>

		</div>
		<div class="parallax-container valign-wrapper ">
			<div class="section no-pad-bot ">
				<div class="container">
					<div class="row center ">
						<h1 class="header col s12 m12 l12 light">富强 民主 文明 和谐 自由 平等</h1>
					</div>
				</div>
			</div>
			<div class="parallax ">
				<img src="../img/global/wallpaper/bg_material_2.jpg" alt="Unsplashed background img 2 ">
			</div>
		</div>
		<div class="container">
			<div class="section ">

				<div class="row ">
					<div class="col s12 m12 l12 ">
						<div class="card hoverable">
							<div class="card-image">
								<img src="../img/seed7/card/card_l_utailor_1.jpg"> <span class="card-title ">鲁泰优纤集中营</span>
							</div>
							<div class="card-content ">
								<p>北京鲁泰优纤电子商务股份公司系鲁泰集团旗下控股子公司。鲁泰集团（SZ.000726）是全球规模最大，产业链最为齐全的高端衬衫面料及衬衫生产商，总部位于山东淄博，在中国最好的长绒棉产区新疆阿克苏拥有 15 万亩优质棉田，在缅甸、越南和柬埔寨等国家设有衬衫生产基地。年设计开发衬衫面料万余种，面料销售量超过 3 亿米。</p>
							</div>
							<div class="card-action ">
								<a href="http://www.utailor.com.cn/">UTAILOR君奕定制 </a>
								<a href="http://www.uskin.net.cn/">USKIN销售平台</a>
								<a href="http://m.utailor.com.cn/login.php">优纤内部系统</a>
								<a href="http://www.utailor.com.cn/index.php/shopadmin/">EC后台</a>
								<a href="http://ubs.uskin.net.cn/">UBS后台管理系统</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="parallax-container valign-wrapper ">
			<div class="section no-pad-bot ">
				<div class="container ">
					<div class="row center ">
						<h1 class="header col s12 light ">公正 法治 爱国 敬业 诚信 友善</h5>
					</div>
				</div>
			</div>
			<div class="parallax ">
				<img src="../img/global/wallpaper/bg_material_2.jpg" alt="Unsplashed background img 3 ">
			</div>
		</div>
		<div class="container">
			<div class="section ">

				<div class="row ">

					<div class="col s6 m6 l6">
						<div class="card hoverable">

							<div class="card-image">
								<img src="../img/global/main/pre_gif_2.gif"> <span class="card-title ">通用测试中心</span>
							</div>
							<div class="card-content">
								<p>此处对服务器压力较大，请谨慎使用</p>
							</div>
							<div class="card-action">
								<a href="# ">进入</a>
							</div>
						</div>
					</div>
					<div class="col s6 m6 l6 ">
						<div class="card hoverable">

							<div class="card-image">
								<img src="../img/global/main/pre_gif_1.gif" align="center">
							</div>
							<div class="card-content ">
								<p>娱乐一下总是好的</p>
							</div>
							<div class="card-action ">
								<a href="# ">This is a link</a>
							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
		<footer class="page-footer teal ">
			<div class="container ">
				<div class="row">
					<div class="col s12 m12 l12">
						<h5 class="white-text ">Coder's Bio</h5>
						<p class="grey-text text-lighten-4 ">我最喜欢的一个说法是：我们背后都跟着一个大滚筒，只要稍微停下，就有可能给绊得人仰马翻，因此，我们必须比它跑得更快，不断前进。当我们自认已掌握一切后，殊不知其实是倒退的开始。</p>
					</div>
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">
					Made by ZhangChi 2017 with Framework
					<a class="brown-text text-lighten-3 " href="http://materializecss.com ">Materialize</a>
				</div>
			</div>
		</footer>

	</body>

</html>