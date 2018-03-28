<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
		<title>优纤下单系统</title>

		<!-- CDN  -->
		<!-- Google Icon Font -->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
		<!-- JQuery  -->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<!--  Angular.js-->
		<!--<script src="http://apps.bdimg.com/libs/angular.js/1.4.6/angular.min.js"></script>-->

		<!-- local html  -->
		<link href="../../img/global/logo/icon_title_1.jpg" rel="shortcut icon" />

		<link href="../../css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="../../css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="../../js/materialize.js"></script>
		<script src="../../js/init.js"></script>

		<script src="../../js/vue.min.js"></script>

		<script src="../../js/init_tailorinfo.js"></script>

		<!--local jsp   -->
		<link href="<%=path %>/img/global/logo/icon_title_.jpg" rel="shortcut icon">

		<link href="<%=path %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection" />
		<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection" />

		<script src="<%=path %>/js/materialize.js"></script>
		<script src="<%=path %>/js/init.js"></script>

		<script src="<%=path %>/js/vue.min.js"></script>

		<script src="<%=path %>/js/init_tailorinfo.js"></script>

		<script type="application/javascript">
			var url_submit_mtm = "/CtrlCenter/LTYX/Test/SubmitMTMRelease.action";

			function submit_mtm() {

				state_loading();

				$.ajax({
					cache: true,
					type: "POST",
					url: url_submit_mtm,
					data: $('#mtmform').serialize(),
					async: true,
					error: function(request) {
						state_defult();
						Materialize.toast('无法连接到服务器', 1000);
					},
					success: function(data) {
						var resp = JSON.parse(data);
						if("0" == resp.ERRCODE && "succ" == resp.ERRDESC) {
							Materialize.toast(resp.data, 1000);
							state_finish(resp.data.analyzeResult, resp.data.html)
						} else {
							state_err(resp.data)
							Materialize.toast(resp.data, 1000);
						}
					}
				});
			}

			function state_defult() {
				$('#table_analyze').html("");
				$('#table_html').html("");
				$('#card_progress').hide();
				$('#card_analyze').hide();
				$('#card_html').hide();
				$('#submit').show();
				$('#clean').hide();
			}

			function state_loading() {
				$('#table_analyze').html("");
				$('#table_html').html("");
				$('#card_progress').show();
				$('#card_analyze').hide();
				$('#card_html').hide();
				$('#submit').hide();
				$('#clean').hide();
			}

			function state_err(analyze) {
				$('#table_analyze').html("" + analyze);
				$('#table_html').html("");
				$('#card_progress').hide();
				$('#card_analyze').show();
				$('#card_html').hide();
				$('#submit').show();
				$('#clean').show();
			}

			function state_finish(analyze, html) {
				$('#table_analyze').html("" + analyze);
				$('#table_html').append("" + html);
				$('#card_progress').hide();
				$('#card_analyze').show();
				$('#card_html').show();
				$('#submit').show();
				$('#clean').show();
			}

			$().ready(function() {
				state_defult();
			})
		</script>

	</head>

	<body>
		<nav class="top-nav teal">
			<div class="container">
				<div class="nav-wrapper">
					<a class="page-title">MTM提交工具</a>
				</div>
			</div>
		</nav>
		<div class="container">
			<div class="section">
				<div class="card-panel">
					<div class="row">
						<div class=" col s12 m12 l12">
							<form method="post" id="mtmform">
								<div class="row">
									<div>
										<div class="col s12 m8 l8">
											<div class="input-field">
												<input type="text" class="validate" name="url" value="tailor.tailogic.com/tailogic/constructor/choose-style/75">
												<label>URL</label>
											</div>
										</div>
										<div class="col s6 m2 l2">
											<div class="input-field">
												<select name="protocoltype">
													<option value="HTTPS">HTTPS</option>
													<option value="HTTP">HTTP</option>
												</select>
												<label>协议类型</label>
											</div>
										</div>

										<div class="col s6 m2 l2">
											<div class="input-field">
												<input type="password" class="validate" name="Cookie" value="has_js=1; SSESS254d6241dc85dcb04eee3d2ef73422a5=wrlR1ha6Lj4PCUxjkUxqcpJArwFerpG4RpiU9uABfE8">
												<label>Cookie</label>
											</div>
										</div>
									</div>
								</div>
								<div class="card-panel">
									<div class="row">
										<div class=" col s12 m12 l12">
											<div class="col s12 m12 l12">
												<div class="input-field">
													<select name="analyzetype">
														<option value="type_keywords">关键词模式</option>
													</select>
													<label>分析模式</label>
												</div>
											</div>
											<div class="col s4 m4 l4">
												<div class="input-field">
													<input type="text" class="validate" name="keyword" value="Login">
													<label>keyword_1</label>
												</div>
											</div>
											<div class="col s4 m4 l4">
												<div class="input-field">
													<input type="text" class="validate" name="keyword" value="succ">
													<label>keyword_2</label>
												</div>
											</div>
											<div class="col s4 m4 l4">
												<div class="input-field">
													<input type="text" class="validate" name="keyword" value="fail">
													<label>keyword_3</label>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="row">
						<div class="col s12 m12 l12 center">
							<a id="submit" class="teal col s12 m8 l6 offset-l3 offset-m2 btn" onclick="submit_mtm()">提交</a>
						</div>
						<div class="col s12 m12 l12 center">
							<a id="clean" class="red col s12 m8 l6 offset-l3 offset-m2 btn" onclick="state_defult()">清屏</a>
						</div>
					</div>
				</div>
				<div class="card-panel" id="card_progress" onclick="state_defult()">
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="progress">
								<div class="indeterminate"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-panel" id="card_analyze" onclick="state_finish()">
					<div class="row">
						<div class=" col s12 m12 l12">
							<p id="table_analyze">
							</p>
						</div>
					</div>
				</div>
				<div class="card-panel" id="card_html">
					<div class="row">
						<div class=" col s12 m12 l12">
							<p id="table_html">
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>