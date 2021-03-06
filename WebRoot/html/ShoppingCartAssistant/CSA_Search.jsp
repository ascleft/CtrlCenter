<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() +"://"+ request.getServerName() +":"+ request.getServerPort() + path +"/";
	
	String ec_user_id=(String) session.getAttribute("ec_user_id");
	String ec_user_name=(String) session.getAttribute("ec_user_name");
	String ec_user_rank=(String) session.getAttribute("ec_user_rank");
	
	String QRurl=(String) session.getAttribute("QRurl");

	String menulist=(String) session.getAttribute("menulist");
%>
<html>
	<!--
		
		作者:鸿安Adrian
		邮箱:ascleft@163.com
		时间:2019-01-16
		描述:购物车添加工具 SCA 3.0
		
		客户经理 即时库存联合查询
		
	-->

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no" />
		<title>定制商品创建工具</title>

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
			//开启菜单
			function OpenMenu() {
				$('#nav_menu').sideNav('show');
			}

			var url_search = "/CtrlCenter/LTYX/OpenAPI/UnityInventory.action";
			var ec_user_rank = parseInt("<%=ec_user_rank%>");

			var ajax_search;

			$().ready(function() {
				state_defult();
			})

			function searchStart(sortKeyWord) {

				state_loading();

				ajax_search =
					$.ajax({
						cache: true,
						type: "GET",
						url: url_search,
						data: $('#filterForm').serialize(),
						async: true,
						error: function(request) {
							state_error("无法连接服务器");
						},
						success: function(data) {
							var resp = JSON.parse(data);
							if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
								state_answer();
								var String_html = "";
								var listLength = 0;
								for(i = 0; i < resp.data.length; i++) {
									listLength += resp.data[i].list.length;
								}
								if(listLength == 0) {

									String_html += "<div class=\"col s12 m12 l12 red-text\">";
									String_html += "<h5>鲁泰仓库显示该面料无库存</h2>";
									String_html += "</div>";

								} else {

									String_html += "<table class=\"striped\">";
									String_html += "<thead>";
									String_html += "<tr>";
									String_html += "<th onclick=\"submitfilterform(\'UskinCode\')\">USKIN编码 </th>";
									if(0 <= ec_user_rank && ec_user_rank < 10) {
										String_html += "<th class=\"hide-on-small-only\">部门 </th>";
										String_html += "<th class=\"hide-on-small-only\" onclick=\"submitfilterform(\'LuthaiCode\')\">物料编码</th>";
									} else if(10 <= ec_user_rank && ec_user_rank < 20) {
										String_html += "<th class=\"hide-on-small-only\">部门 </th>";
										String_html += "<th class=\"hide-on-small-only\">物料编码(当前无权查看)</th>";
									} else {
										String_html += "<th class=\"hide-on-small-only\">部门 (当前无权查看)</th>";
										String_html += "<th class=\"hide-on-small-only\">物料编码(当前无权查看)</th>";
									}
									String_html += "<th onclick=\"submitfilterform(\'All\')\">库存 </th>";
									String_html += "<th onclick=\"submitfilterform(\'Available\')\">可用库存 </th>";
									String_html += "</tr>";
									String_html += "</thead>";
									String_html += "<tbody>";

									for(var i = 0; i < resp.data.length; i++) {

										for(var j = 0; j < resp.data[i].list.length; j++) {

											resp.data[i].list = jsonSort(resp.data[i].list, sortKeyWord, false);

											String_html += "<tr>";
											String_html += "<td>";
											if("MLCK040" == resp.data[i].list[j].Warehouse) {
												String_html += "<a>";
												if("" != resp.data[i].list[j].UskinCode) {
													if("客供" == resp.data[i].list[j].UskinCode) {
														String_html += "该面料无USKIN编码";
													} else {
														String_html += resp.data[i].list[j].UskinCode;
														String_html += "(客供仓库)";
													}
												} else {
													String_html += "该面料无USKIN编码";
												}
												String_html += "</a>";

												String_html += "<br/>";

												String_html += "<a ";
												String_html += "style=\"color: lightskyblue;\" ";
												String_html += "href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBCMan.action?code=";
												String_html += resp.data[i].list[j].LuthaiCode;
												String_html += "\">";
												String_html += "男装客供";
												String_html += "</a>";

												String_html += "<a>";
												String_html += " ";
												String_html += "</a>";

												String_html += "<a ";
												String_html += "style=\"color: lightpink;\" ";
												String_html += "href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBCWoman.action?code=";
												String_html += resp.data[i].list[j].LuthaiCode;
												String_html += "\">";
												String_html += "女装客供";
												String_html += "</a>";

											} else if("MLCK037" == resp.data[i].list[j].Warehouse) {
												String_html += "<a href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXMan.action?code=";
												String_html += resp.data[i].list[j].UskinCode;
												String_html += "\">";
												String_html += resp.data[i].list[j].UskinCode;
												String_html += "(报喜鸟仓库)";
												String_html += "</a>";

											} else {
												String_html += "<a href=\"/CtrlCenter/LTYX/SCA/Main/CustomShopAidePBYXMan.action?code=";
												String_html += resp.data[i].list[j].UskinCode;
												String_html += "\">";
												String_html += resp.data[i].list[j].UskinCode;
												String_html += "</a>";
											}
											String_html += "</td>";
											if(0 <= ec_user_rank && ec_user_rank < 10) {
												String_html += "<td class=\"hide-on-small-only\">";
												String_html += resp.data[i].list[j].Department;
												String_html += "</td>";
												String_html += "<td class=\"hide-on-small-only\">";
												String_html += resp.data[i].list[j].LuthaiCode;
												String_html += "</td>";
											} else if(10 <= ec_user_rank && ec_user_rank < 20) {
												String_html += "<td class=\"hide-on-small-only\">";
												String_html += resp.data[i].list[j].Department;
												String_html += "</td>";
												String_html += "<td class=\"hide-on-small-only\">";
												String_html += "*";
												String_html += "</td>";
											} else {
												String_html += "<td class=\"hide-on-small-only\">";
												String_html += "*";
												String_html += "</td>";
												String_html += "<td class=\"hide-on-small-only\">";
												String_html += "*";
												String_html += "</td>";
											}
											String_html += "<td>";
											String_html += resp.data[i].list[j].All;
											String_html += "</td>";
											String_html += "<td>";
											String_html += resp.data[i].list[j].Available;
											String_html += "</td>";
											String_html += "</tr>";
										}

									}

									String_html += "</tbody>";
									String_html += "</table>";

								}

								$("#ucs_table").html(String_html);
							} else {
								state_error(resp.data);
							}
						}
					});
			}

			function searchStop() {
				ajax_search.abort();
				state_defult();
			}

			function state_defult() {
				$("#ucs_tip_card").hide();
				$("#ucs_tip").html("");
				$("#ucs_progress_bar").hide();

				$("#ucs_table_card").hide();
				$("#ucs_table").html("");

				$("#btn_search_start").show();
				$("#btn_search_stop").hide();
			}

			function state_loading() {
				$("#ucs_tip_card").show();
				$("#ucs_tip").html("查询中，请稍候...");
				$("#ucs_progress_bar").show();

				$("#ucs_table_card").hide();
				$("#ucs_table").html("");

				$("#btn_search_start").hide();
				$("#btn_search_stop").show();
			}

			function state_answer() {
				$("#ucs_tip_card").hide();
				$("#ucs_tip").html("");
				$("#ucs_progress_bar").hide();

				$("#ucs_table_card").show();
				$("#ucs_table").html("");

				$("#btn_search_start").show();
				$("#btn_search_stop").hide();
			}

			function state_error(string_desc) {
				$("#ucs_tip_card").show();
				$("#ucs_tip").html(string_desc);
				$("#ucs_progress_bar").hide();

				$("#ucs_table_card").hide();
				$("#ucs_table").html("");

				$("#btn_search_start").show();
				$("#btn_search_stop").hide();
			}
		</script>

	</head>

	<body>

		<header>
			<nav class="teal" role="navigation">
				<div class="nav-wrapper container">
					<!-- 页面标题  -->
					<a id="logo-container " href="#" class="brand-logo white-text ">客户经理 即时库存查询</a>
					<!-- 导航菜单键（运动移动设备） -->
					<a href="#" data-activates="nav_menu_list " class="button-collapse ">
						<i class="material-icons white-text">menu</i>
					</a>
					<!-- 页面右上角菜单  （桌面设备）-->
					<ul id="nav_menu_mobile" class="right hide-on-med-and-down white-text">
						<li>
							<a class="white-text">当前用户:
								<%=ec_user_name%>
							</a>
						</li>
						<li>
							<a class="white-text" onclick="OpenMenu()">操作</a>
						</li>
					</ul>
					<!-- 菜单触发-->
					<a href="#" class="button-collapse" data-activates="nav_menu_list" id="nav_menu"></a>
					<!-- 菜单列表-->
					<ul id="nav_menu_list" class="side-nav teal-text">
						<div class="teal">
							<li>
								<a class=" white-text">您好:
									<%=ec_user_name%>
								</a>
							</li>
							<li>
								<a class=" white-text" onclick="logout()">注销 </a>
							</li>
						</div>
						<%=menulist%>
					</ul>
				</div>
			</nav>
		</header>

		<main>
			<div class="container">
				<div class="section">
					<!--控制器-->
					<div class="col s12 m12 l12">
						<div class="card-panel">
							<form method="post" id="filterForm" onkeydown="if(event.keyCode==13)return false;">
								<div class="row">
									<div style="display: none;">
										<input name="rank">
										<%=ec_user_rank%>
										</input>
									</div>
									
									<div class="col s12 m8 l8 red-text">
										<div class="input-field">
											<input type="text" class="validate" name="Code" value="" >
											<label>请输入USKIN编码 或 客供面料快递单号</label>
										</div>
									</div>

									<div class="col s6 m4 l4 teal-text input-field">
										<select name="CodeType">
											<option value="YX">USKIN编码</option>
											<option value="LT">鲁泰物料编码</option>
										</select><label>编码类型指定（仅作用于现货科）</label>
									</div>
									<div class="col s6 m4 l3 teal-text input-field">
										<select name="Department">
											<option value="All">联合查询</option>
											<option value="ERP600">现货科/零裁组</option>
											<option value="K3">制衣ERP</option>
										</select><label>数据源</label>
									</div>
									<div class="col s6 m4 l3 teal-text input-field ">
										<select name="Fuzzy">
											<option value="Y">模糊查询</option>
											<option value="N">精确查询</option>
										</select><label>查询模式（仅支持制衣ERP）</label>
									</div>
									<div class="col s6 m4 l3 teal-text input-field">
										<select name="Warehouse">
											<option value="LT">鲁泰仓库</option>
											<option value="ZN">智能制造</option>
											<option value="KG">客供仓库</option>
										</select><label>仓库（仅支持制衣ERP）</label>
									</div>

									<div class="col s12 m12 l3 white-text center ">
										<div class="input-field ">
											<a class="btn" onclick="searchStart('UskinCode')" id="btn_search_start">确定</a>
											<a class="btn" onclick="searchStop()" id="btn_search_stop">取消</a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!--进度板-->
					<div class="card-panel" id="ucs_tip_card">
						<div class="card-content grey-text">
							<div class="row">
								<p class="col s12 m12 l12" id="ucs_tip">tips</p>
								<div class="progress" id="ucs_progress_bar">
									<div class="indeterminate"></div>
								</div>
							</div>
						</div>
					</div>
					<!--结果板-->
					<div class="col s12 m12 l12">
						<div class="card-panel" id="ucs_table_card">
							<div class="card-content grey-text">
								<div class="row">
									<div id="ucs_table">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>

		<footer class="page-footer teal">
			<div class="container">
				<div class="row" style="display:; text-align:center">
				</div>
			</div>
			<div class="footer-copyright">
				<div class="container">Powered by ZhangChi 2019</div>
			</div>
			<div class="fixed-action-btn toolbar">
				<a class="btn-floating  teal darken-1">
					<i class="large material-icons">shopping_cart</i>
				</a>
				<ul>
					<li class="waves-effect waves-light">
						<a href="http://www.uskin.net.cn/index.php/cart.html">USKIN电脑版</a>
					</li>
					<li class="waves-effect waves-light">
						<a href="http://www.uskin.net.cn/index.php/wap/cart.html">USKIN手机版 </a>
					</li>
					<li class="waves-effect waves-light">
						<a href="http://www.utailor.com.cn/index.php/cart.html">君奕电脑版</a>
					</li>
					<li class="waves-effect waves-light">
						<a href="http://www.utailor.com.cn/index.php/wap/cart.html">君奕手机版</a>
					</li>
				</ul>
			</div>

		</footer>

	</body>

</html>