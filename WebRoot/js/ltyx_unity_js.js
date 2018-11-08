var url_logout = "/CtrlCenter/LTYX/SCA/Logout.action";
var url_checklogin = "/CtrlCenter/LTYX/SCA/CheckLogin.action";

function logout() {
	location.href = url_logout;
}

function checkLoginState(funPrepare, funSucc, funFail) {
	funPrepare();
	$.ajax({
		cache: true,
		type: "POST",
		url: url_checklogin,
		data: "",
		async: true,
		error: function(request) {},
		success: function(data) {
			var resp = JSON.parse(data);
			if("1" == resp.ERRCODE && "fail" == resp.ERRDESC) {
				funFail();
				setTimeout(function() {
					window.open("/CtrlCenter/LTYX/SCA/ReLoginPage.action")
				}, 2000);
			} else {
				funSucc();
			}
		}
	});
}

function testVar() {
	var map = {};
	map['单独打板，单量单裁'] = 1;
	map['key2@'] = 2;

	console.log(map['单独打板，单量单裁']); //结果是1.
	console.log(map['key2@']); //结果是2.

	//如果遍历map
	for(var prop in map) {
		if(map.hasOwnProperty(prop)) {
			console.log('key is ' + prop + ' and value is' + map[prop]);
		}
	}
}

//-------------------------------------------------------------------------交期报价模块↓
//定义交期系统字典
var DeliveryTimeTable;
//初始化交期系统字典
function initDeliveryTimeTable() {
	DeliveryTimeTable = {
		"单独打板，单量单裁": {
			"1": {
				"6": {
					"基础款": "120",
					"复杂款": "140",
					"客供款": "170"
				},
				"5": {
					"基础款": "144",
					"复杂款": "168",
					"客供款": "204"
				},
				"4": {
					"基础款": "156",
					"复杂款": "182",
					"客供款": "221"
				},
				"3": {
					"基础款": "240",
					"复杂款": "280",
					"客供款": "340"
				}
			}
		},
		"标准码成衣": {
			"1": {
				"7": {
					"基础款": "105"
				}
			}
		},
		"团单": {
			"11-30": {
				"10-15": {
					"基础款": "84",
					"复杂款": "90",
					"客供款": "9999999"
				}
			},
			"31-100": {
				"10-15": {
					"基础款": "70",
					"复杂款": "75",
					"客供款": "9999999"
				}
			},
			"101-500": {
				"10-15": {
					"基础款": "65",
					"复杂款": "70",
					"客供款": "9999999"
				}
			},
			"501-1500": {
				"15-25": {
					"基础款": "55",
					"复杂款": "65",
					"客供款": "9999999"
				}
			}
		}
	};
}
//填充订单类型
function set_delivery_time_table_section_1() {
	var list_1 = ""
	for(var prop1 in DeliveryTimeTable) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}

	$("#delivery_time_table_section_1").html(list_1);
	$('select').material_select();
	set_delivery_time_table_section_2();
}
//填充可选件数区间
function set_delivery_time_table_section_2() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	var list_2 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				list_2 += "<option value=\"" + prop2 + "\">";
				list_2 += prop2;
				list_2 += "</option>";
			}
		}
	}

	$("#delivery_time_table_section_2").html(list_2);
	$('select').material_select();
	set_delivery_time_table_section_3();
}
//填充可选交期区间
function set_delivery_time_table_section_3() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	selected_2 = "" + $("#delivery_time_table_section_2 option:selected").text();
	var list_3 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				if(prop2 == selected_2) {
					for(var prop3 in DeliveryTimeTable[prop1][prop2]) {
						list_3_item = "";
						list_3_item += "<option value=\"" + prop3 + "\">";
						list_3_item += prop3;
						list_3_item += "</option>";
						list_3 = list_3_item + list_3;
					}
				}
			}
		}
	}
	$("#delivery_time_table_section_3").html(list_3);
	$('select').material_select();
	set_delivery_time_table_section_4();
}
//填充可选款式类型
function set_delivery_time_table_section_4() {
	selected_1 = "" + $("#delivery_time_table_section_1 option:selected").text();
	selected_2 = "" + $("#delivery_time_table_section_2 option:selected").text();
	selected_3 = "" + $("#delivery_time_table_section_3 option:selected").text();
	var list_4 = ""
	for(var prop1 in DeliveryTimeTable) {
		if(prop1 == selected_1) {
			for(var prop2 in DeliveryTimeTable[prop1]) {
				if(prop2 == selected_2) {
					for(var prop3 in DeliveryTimeTable[prop1][prop2]) {
						if(prop3 == selected_3) {
							for(var prop4 in DeliveryTimeTable[prop1][prop2][prop3]) {
								list_4 += "<option value=\"" + prop4 + "\">";
								list_4 += prop4;
								list_4 += "</option>";
							}
						}
					}
				}
			}
		}
	}
	$("#delivery_time_table_section_4").html(list_4);
	$('select').material_select();
}
//初始化交期系统
function use_DeliveryTime() {
	initDeliveryTimeTable();
	set_delivery_time_table_section_1();
	$("#delivery_time_table_section_1").bind("change", function() {
		set_delivery_time_table_section_2();
	});
	$("#delivery_time_table_section_2").bind("change", function() {
		set_delivery_time_table_section_3();
	});
	$("#delivery_time_table_section_3").bind("change", function() {
		set_delivery_time_table_section_4();
	});
}
//-------------------------------------------------------------------------交期报价模块↑

//-------------------------------------------------------------------------刺绣模块↓
//定义刺绣文字字典
var LZX_11_Char_Table;
//定义刺绣图片字典
var LZX_11_Pic_Table;
//初始化刺绣图片和文字字典
function initLZX11Table() {
	LZX_11_Char_Table = {
		'map': {
			'LZX-11-01': {
				'name': '英文中宋',
				'size': '1'
			},
			'LZX-11-02': {
				'name': '舒体',
				'size': '1'
			},
			'LZX-11-04': {
				'name': '皇家体',
				'size': '1'
			},
			'LZX-11-05': {
				'name': '手写体',
				'size': '1'
			},
			'LZX-11-06': {
				'name': '古圆体',
				'size': '1'
			},
			'LZX-11-08': {
				'name': '维体',
				'size': '1'
			},
			'LZX-11-09': {
				'name': '书写体',
				'size': '1'
			},
			'LZX-11-10': {
				'name': '哥特体',
				'size': '1'
			},
			'LZX-11-12': {
				'name': '卡曼体',
				'size': '1'
			},
			'LZX-11-13': {
				'name': '花体',
				'size': '1'
			},
			'LZX-11-14': {
				'name': '书信体',
				'size': '1'
			},
			'LZX-11-15': {
				'name': '巴洛克体',
				'size': '1'
			},
			'LZX-11-16': {
				'name': '英文行楷',
				'size': '1'
			},
			'LZX-11-17': {
				'name': '黑体',
				'size': '1'
			},
			'LZX-11-18': {
				'name': '隶属',
				'size': '1'
			},
			'LZX-11-19': {
				'name': '毛体',
				'size': '1'
			},
			'LZX-11-20': {
				'name': '草书',
				'size': '1'
			},
			'LZX-11-21': {
				'name': '中文行楷',
				'size': '1'
			},
			'LZX-11-22': {
				'name': '中文中宋',
				'size': '1'
			},
		},
		'size': {
			'1': {
				'YX-12-07': '0.7cm',
				'YX-12-08': '0.8cm',
				'YX-12-09': '0.9cm',
				'YX-12-10': '1.0cm',
				'YX-12-11': '1.1cm',
				'YX-12-12': '1.2cm',
				'YX-12-13': '1.3cm',
				'YX-12-14': '1.4cm',
				'YX-12-15': '1.5cm',
				'YX-12-16': '1.6cm',
				'YX-12-17': '1.7cm',
				'YX-12-18': '1.8cm',
				'YX-12-19': '1.9cm',
				'YX-12-20': '2.0cm',
				'YX-12-21': '2.1cm',
				'YX-12-22': '2.2cm',
				'YX-12-23': '2.3cm',
				'YX-12-24': '2.4cm',
				'YX-12-25': '2.5cm',
				'YX-12-26': '2.6cm',
				'YX-12-27': '2.7cm',
				'YX-12-28': '2.8cm',
				'YX-12-29': '2.9cm',
				'YX-12-30': '3.0cm',
			}
		}
	};
	LZX_11_Pic_Table = {
		'LZX-11-23': {
			'name': 'LZX-11-23',
			'map': {
				'01': '2',
				'02': '2',
				'03': '3',
				'04': '2',
				'05': '3',
				'06': '2',
				'07': '3',
				'08': '2',
				'09': '3',
				'10': '3',
				'11': '2',
				'12': '2',
				'13': '1',
				'14': '1',
				'15': '1',
				'16': '1',
				'17': '1',
				'18': '1',
				'19': '1',
				'20': '1',
				'21': '1',
				'22': '1',
				'23': '1',
				'24': '1',
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
				'2': {
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
				'3': {
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			}

		},
		'LZX-11-24': {
			'name': 'LZX-11-24',
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1',
				'06': '1',
				'07': '1',
				'08': '1',
				'09': '1',
				'10': '1',
				'11': '1',
				'12': '1',
				'13': '1',
				'14': '1',
				'15': '1',
				'16': '1',
				'17': '1',
				'18': '1',
				'19': '1',
				'20': '1',
				'21': '1',
				'22': '1',
				'23': '1',
				'24': '1',
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-25': {
			'name': 'LZX-11-25',
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1',
				'06': '1',
				'07': '1',
				'08': '1',
				'09': '1',
				'10': '1',
				'11': '1',
				'12': '1'
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-26': {
			'name': 'LZX-11-26',
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1'
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-27': {
			'name': 'LZX-11-27',
			'map': {
				'01': '1',
				'02': '1',
				'03': '1',
				'04': '1',
				'05': '1',
				'06': '1',
				'07': '1',
				'08': '1',
				'09': '1',
				'10': '1'
			},
			'size': {
				'1': {
					'YX-12-10': '1.0cm',
					'YX-12-11': '1.1cm',
					'YX-12-12': '1.2cm',
					'YX-12-13': '1.3cm',
					'YX-12-14': '1.4cm',
					'YX-12-15': '1.5cm',
					'YX-12-16': '1.6cm',
					'YX-12-17': '1.7cm',
					'YX-12-18': '1.8cm',
					'YX-12-19': '1.9cm',
					'YX-12-20': '2.0cm',
					'YX-12-21': '2.1cm',
					'YX-12-22': '2.2cm',
					'YX-12-23': '2.3cm',
					'YX-12-24': '2.4cm',
					'YX-12-25': '2.5cm',
					'YX-12-26': '2.6cm',
					'YX-12-27': '2.7cm',
					'YX-12-28': '2.8cm',
					'YX-12-29': '2.9cm',
					'YX-12-30': '3.0cm',
				},
			},
		},
		'LZX-11-FF': {
			'name': '客供图案',
			'map': {
				'客供图案': '1'
			},
			'size': {
				'1': {
					'LZX-12-FF': '客供大小'
				},
			},
		}
	};
}
//填充文字刺绣第一部分
function set_lzx11_char_section_1() {
	var list_1 = ""
	for(var prop1 in LZX_11_Char_Table['map']) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1 + " " + LZX_11_Char_Table['map'][prop1]['name'];
		list_1 += "</option>";
	}
	$("select[name$='LZX_11_CHAR_TYPE']").html(list_1);
	$('select').material_select();
	set_lzx11_char_section_2();
}
//填充文字刺绣第二部分
function set_lzx11_char_section_2() {
	selected_1 = "" + $("select[name$='LZX_11_CHAR_TYPE'] option:selected").val();
	selected_1_size = "" + LZX_11_Char_Table['map'][selected_1]['size'];
	var list_2 = ""
	for(var prop2 in LZX_11_Char_Table['size'][selected_1_size]) {
		list_2 += "<option value=\"" + prop2 + "\">";
		list_2 += LZX_11_Char_Table['size'][selected_1_size][prop2];
		list_2 += "</option>";
	}
	$("select[name$='LZX_11_CHAR_SIZE']").html(list_2);
	$('select').material_select();
}

//填充图片刺绣第一部分
function set_lzx11_pic_section_1() {
	var list_1 = ""
	for(var prop1 in LZX_11_Pic_Table) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += LZX_11_Pic_Table[prop1].name;
		list_1 += "</option>";
	}
	$("select[name$='LZX_11_PIC_TYPE']").html(list_1);
	$('select').material_select();
	set_lzx11_pic_section_2();
}
//填充图片刺绣第二部分
function set_lzx11_pic_section_2() {
	selected_1 = "" + $("select[name$='LZX_11_PIC_TYPE'] option:selected").val();
	var list_2 = ""
	for(var prop2 in LZX_11_Pic_Table[selected_1]['map']) {
		list_2 += "<option value=\"" + prop2 + "\">";
		list_2 += selected_1 + " " + prop2;
		list_2 += "</option>";
	}
	$("select[name$='LZX_11_PIC_NUM']").html(list_2);
	$('select').material_select();
	set_lzx11_pic_section_3();
}
//填充图片刺绣第三部分
function set_lzx11_pic_section_3() {
	selected_1 = "" + $("select[name$='LZX_11_PIC_TYPE'] option:selected").val();
	selected_2 = "" + $("select[name$='LZX_11_PIC_NUM'] option:selected").val();
	selected_2_size = "" + LZX_11_Pic_Table[selected_1]['map'][selected_2];
	var list_3 = ""
	for(var prop3 in LZX_11_Pic_Table[selected_1]['size'][selected_2_size]) {
		list_3 += "<option value=\"" + prop3 + "\">";
		list_3 += LZX_11_Pic_Table[selected_1]['size'][selected_2_size][prop3];
		list_3 += "</option>";
	}
	$("select[name$='LZX_11_PIC_SIZE']").html(list_3);
	$('select').material_select();
}
//初始化刺绣模块
function use_lzx11() {
	initLZX11Table();
	set_lzx11_char_section_1();
	set_lzx11_pic_section_1();
	$("select[name$='LZX_11_CHAR_TYPE']").bind("change", function() {
		set_lzx11_char_section_2();
	});
	$("select[name$='LZX_11_PIC_TYPE']").bind("change", function() {
		set_lzx11_pic_section_2();
	});
	$("select[name$='LZX_11_PIC_NUM']").bind("change", function() {
		set_lzx11_pic_section_3();
	});
	console.log("已启用刺绣字典");
}
//-------------------------------------------------------------------------刺绣模块↑

//-------------------------------------------------------------------------客供专项↓
//客供扣子
function use_pbc_kouzi() {
	$("#kouzi_div").hide();
	$("#kouzi").bind("change", function() {
		var selected_name = "" + $("#kouzi option:selected ").text();
		if(selected_name == "客供") {
			$("#kouzi_div").show();
			$("#kouzi_pbc").val("");
		} else {
			$("#kouzi_div").hide();
			$("#kouzi_pbc").val("");
		}
		Materialize.updateTextFields();
	});
	console.log("已启用客供扣子");
}
//启用客供主唛
function use_pbc_YX_08() {
	$("#YX_08_div").hide();
	$("#YX_08").bind("change", function() {
		var selected_name = "" + $("#YX_08 option:selected").text();
		if(selected_name == "客供") {
			$("#YX_08_div").show();
			$("#YX_08_pbc").val("");
		} else {
			$("#YX_08_div").hide();
			$("#YX_08_pbc").val("");
		}
		Materialize.updateTextFields();
	});
	console.log("已启用客供主唛");
}
//-------------------------------------------------------------------------客供专项↑

//-------------------------------------------------------------------------手动专项↓
//自定义配色部位
function use_custom_weizhi_peise() {
	$("#weizhi_peise_div").hide();
	$("#weizhi_peise").bind("change", function() {
		var selected_name = "" + $("#weizhi_peise option:selected").text();
		if(selected_name == "手动填写") {
			$("#weizhi_peise_div").show();
			$("#weizhi_peise_custom").val("");
		} else {
			$("#weizhi_peise_div").hide();
			$("#weizhi_peise_custom").val("");
		}
		Materialize.updateTextFields();
	});
	console.log("已启用手动填写配色部位");
}
//-------------------------------------------------------------------------手动专项↑

//-------------------------------------------------------------------------尺寸专项↓
//启用尺寸选择
function use_size() {

	size_table = [{
		'name_param': 'xiongwei',
		'name_ch': '胸围',
		'size_s': '6',
		'size_m': '4',
		'size_l': '3'
	}]

	var temp_table_1 = '';
	temp_table_1 += '<div class="card-panel">                                                                   ';
	temp_table_1 += '	<div class="row">                                                                       ';

	temp_table_1 += '		<p>';
	temp_table_1 += '			<input type="checkbox" class="filled-in" id="mode_measure_form" checked="checked" />';
	temp_table_1 += '			<label for="mode_measure_form">精简模式（仅显示成衣尺寸的必填内容）</label>';
	temp_table_1 += '		</p>';

	temp_table_1 += '		<div class="col s6 m4 l3" id="div_ling_wei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="ling_wei" value="">             ';
	temp_table_1 += '				<label>领围*</label>                                                        ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_xiong_wei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="xiong_wei" value="">            ';
	temp_table_1 += '				<label>胸围*</label>                                                        ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_yao_wei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="yao_wei" value="">              ';
	temp_table_1 += '				<label>腰围（中腰围）*</label>                                               ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_du_wei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="du_wei" value="">               ';
	temp_table_1 += '				<label>肚围</label>                                                         ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_dibian">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="dibian" value="">               ';
	temp_table_1 += '				<label>底边（臀围）*</label>                                                 ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_neiwaichuan">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<select id="neiwaichuan" name="neiwaichuan">                                ';
	temp_table_1 += '					<option value="YX-10-01">内穿</option>                                  ';
	temp_table_1 += '					<option value="YX-10-02">外穿</option>                                  ';
	temp_table_1 += '				</select><label>内外穿</label>                                              ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_houshen_chang_nei">                             ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="houshen_chang_nei" value="">    ';
	temp_table_1 += '				<label>后身长（内穿）*</label>                                               ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_houshen_chang_wai">                             ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="houshen_chang_wai" value="">    ';
	temp_table_1 += '				<label>后身长（外穿）*</label>                                               ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_xiutouchang_zuo">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="xiutouchang_zuo" value="">      ';
	temp_table_1 += '				<label>左袖头长（左腕围）*</label>                              ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_xiutouchang_you">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="xiutouchang_you" value="">      ';
	temp_table_1 += '				<label>右袖头长（右腕围）*</label>                              ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_xiu_fei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="xiu_fei" value="">              ';
	temp_table_1 += '				<label>袖肥（大臂围）*</label>                                               ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_xiuzhou_fei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="xiuzhou_fei" value="">          ';
	temp_table_1 += '				<label>袖肘肥（小臂围）</label>                                              ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_jian_kuan">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="jian_kuan" value="">            ';
	temp_table_1 += '				<label>肩宽*</label>                                                        ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_xiu_chang">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="xiu_chang" value="">            ';
	temp_table_1 += '				<label>长袖长*</label>                                         ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_duanxiu_chang">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="duanxiu_chang" value="">        ';
	temp_table_1 += '				<label>短袖长*</label>                                         ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_duanxiu_kouwei">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="duanxiu_kouwei" value="">       ';
	temp_table_1 += '				<label>短袖口围*</label>                                       ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_chest_height">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="chest_height" value="">         ';
	temp_table_1 += '				<label>胸高</label>                                                         ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_chest_distance">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="chest_distance" value="">       ';
	temp_table_1 += '				<label>胸距</label>                                                         ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_qianshen_chang">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="qianshen_chang" value="">       ';
	temp_table_1 += '				<label>前身长*</label>                                                      ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_qianxiong_kuan">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="qianxiong_kuan" value="">       ';
	temp_table_1 += '				<label>前胸宽</label>                                                       ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_houbei_kuan">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="houbei_kuan" value="">          ';
	temp_table_1 += '				<label>后背宽</label>                                                       ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_height">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="height" value="">               ';
	temp_table_1 += '				<label>身高</label>                                                         ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '		<div class="col s6 m4 l3" id="div_weight">                                                          ';
	temp_table_1 += '			<div class="input-field">                                                       ';
	temp_table_1 += '				<input type="number" class="validate" name="weight" value="">               ';
	temp_table_1 += '				<label>体重(kg)</label>                                                     ';
	temp_table_1 += '			</div>                                                                          ';
	temp_table_1 += '		</div>                                                                              ';
	temp_table_1 += '	</div>                                                                                  ';
	temp_table_1 += '</div>                                                                                     ';

	var temp_table_2 = '';
	temp_table_2 += '	<div class="card-panel">                                                              ';
	temp_table_2 += '		<div class="row">                                                                 ';
	temp_table_2 += '			<div class="col  s12 m12 l12 teal-text">                                      ';
	temp_table_2 += '				<p>特体选项</p>                                                           ';
	temp_table_2 += '			</div>                                                                        ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                        ';
	temp_table_2 += '				<select name="spc_b_sho_f" id="spc_b_sho_f">                              ';
	temp_table_2 += '					<option value="LZX-TT-55-11">正常</option>                            ';
	temp_table_2 += '					<option value="LZX-TT-55-12">轻微前冲肩</option>                       ';
	temp_table_2 += '					<option value="LZX-TT-55-13">严重前冲肩</option>                       ';
	temp_table_2 += '					<option value="LZX-TT-55-14">轻微后掰肩</option>                       ';
	temp_table_2 += '					<option value="LZX-TT-55-15">严重后掰肩</option>                       ';
	temp_table_2 += '				</select> <label>前冲后掰肩</label>                                        ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_sho_l" id="spc_b_sho_l">                               ';
	temp_table_2 += '					<option value="LZX-TT-56-16">正常</option>                              ';
	temp_table_2 += '					<option value="LZX-TT-56-17">轻微溜肩</option>                          ';
	temp_table_2 += '					<option value="LZX-TT-56-18">中度溜肩</option>                          ';
	temp_table_2 += '					<option value="LZX-TT-56-19">严重溜肩</option>                          ';
	temp_table_2 += '					<option value="LZX-TT-56-20">微耸肩</option>                            ';
	temp_table_2 += '					<option value="LZX-TT-56-21">中度耸肩</option>                          ';
	temp_table_2 += '					<option value="LZX-TT-56-22">严重耸肩</option>                          ';
	temp_table_2 += '				</select> <label>左肩型</label>                                            ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_sho_r" id="spc_b_sho_r">                               ';
	temp_table_2 += '					<option value="LZX-TT-57-23">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-57-24">轻微溜肩</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-57-25">中度溜肩</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-57-26">严重溜肩</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-57-27">微耸肩</option>                           ';
	temp_table_2 += '					<option value="LZX-TT-57-28">中度耸肩</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-57-29">严重耸肩</option>                         ';
	temp_table_2 += '				</select> <label>右肩型</label>                                            ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_spi_s" id="spc_b_spi_s">                               ';
	temp_table_2 += '					<option value="LZX-TT-58-30">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-58-31">后仰体0.5</option>                        ';
	temp_table_2 += '					<option value="LZX-TT-58-32">后仰体0.8</option>                        ';
	temp_table_2 += '					<option value="LZX-TT-58-33">后仰体1</option>                          ';
	temp_table_2 += '					<option value="LZX-TT-58-34">前弓体0.5</option>                        ';
	temp_table_2 += '					<option value="LZX-TT-58-35">前弓体0.8</option>                        ';
	temp_table_2 += '					<option value="LZX-TT-58-36">前弓体1</option>                          ';
	temp_table_2 += '				</select> <label>前弓后仰体</label>                                        ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_spi_h" id="spc_b_spi_h">                               ';
	temp_table_2 += '					<option value="LZX-TT-59-37">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-59-38">轻微驼背</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-59-39">中度驼背</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-59-40">严重驼背</option>                         ';
	temp_table_2 += '				</select> <label>驼背</label>                                              ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_che_n" id="spc_b_che_n">                               ';
	temp_table_2 += '					<option value="LZX-TT-60-41">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-60-42">轻微挺胸</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-60-43">中度挺胸</option>                         ';
	temp_table_2 += '					<option value="LZX-TT-60-44">严重挺胸</option>                         ';
	temp_table_2 += '				</select> <label>胸型</label>                                             ';
	temp_table_2 += '			</div>                                                                        ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                        ';
	temp_table_2 += '				<select name="spc_b_abd_n" id="spc_b_abd_n">                              ';
	temp_table_2 += '					<option value="LZX-TT-61-45">正常</option>                            ';
	temp_table_2 += '					<option value="LZX-TT-61-46">轻微凸肚（周圈胖）</option>               ';
	temp_table_2 += '					<option value="LZX-TT-61-47">中度凸肚（腹部、臀部前倾）</option>        ';
	temp_table_2 += '					<option value="LZX-TT-61-48">严重凸肚</option>                         ';
	temp_table_2 += '				</select> <label>肚型</label>                                              ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_sle_n" id="spc_b_sle_n">                               ';
	temp_table_2 += '					<option value="LZX-TT-62-49">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-62-50">轻微手臂靠后</option>                     ';
	temp_table_2 += '					<option value="LZX-TT-62-51">严重手臂靠后</option>                     ';
	temp_table_2 += '					<option value="LZX-TT-62-52">轻微手臂靠前</option>                     ';
	temp_table_2 += '					<option value="LZX-TT-62-53">严重手臂靠前</option>                     ';
	temp_table_2 += '				</select> <label>袖子臂型</label>                                          ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_muf_d" id="spc_b_muf_d">                               ';
	temp_table_2 += '					<option value="LZX-TT-63-54">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-63-55">袖窿深下挖0.5</option>                    ';
	temp_table_2 += '					<option value="LZX-TT-63-56">袖窿深下挖1</option>                      ';
	temp_table_2 += '					<option value="LZX-TT-63-57">袖窿深下挖1.5</option>                    ';
	temp_table_2 += '				</select> <label>袖窿深下挖</label>                                        ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '			<div class="input-field col s6 m4 l3">                                         ';
	temp_table_2 += '				<select name="spc_b_muf_u" id="spc_b_muf_u">                               ';
	temp_table_2 += '					<option value="LZX-TT-64-58">正常</option>                             ';
	temp_table_2 += '					<option value="LZX-TT-64-59">袖窿深上调0.5</option>                     ';
	temp_table_2 += '					<option value="LZX-TT-64-60">袖窿深上调1</option>                       ';
	temp_table_2 += '					<option value="LZX-TT-64-61">袖窿深上调1.5</option>                     ';
	temp_table_2 += '				</select> <label>袖窿深上调</label>                                         ';
	temp_table_2 += '			</div>                                                                         ';
	temp_table_2 += '		</div>                                                                             ';
	temp_table_2 += '	</div>                                                                                 ';

	var temp_table_3 = '';
	temp_table_3 += '<div class="card-panel">                                         ';
	temp_table_3 += '	<div class="row">                                             ';
	temp_table_3 += '		<div class="input-field col s12 m12 l12">                 ';
	temp_table_3 += '			<select name="size">                                  ';
	temp_table_3 += '				<option value="2176">男式衬衫紧身版38 </option>   ';
	temp_table_3 += '				<option value="2177">男式衬衫紧身版38.5</option>  ';
	temp_table_3 += '				<option value="2178">男式衬衫紧身版39 </option>   ';
	temp_table_3 += '				<option value="2179">男式衬衫紧身版39.5</option>  ';
	temp_table_3 += '				<option value="2180">男式衬衫紧身版40 </option>   ';
	temp_table_3 += '				<option value="2181">男式衬衫紧身版40.5</option>  ';
	temp_table_3 += '				<option value="2182">男式衬衫紧身版41 </option>   ';
	temp_table_3 += '				<option value="2183">男式衬衫紧身版41.5</option>  ';
	temp_table_3 += '				<option value="2184">男式衬衫紧身版42 </option>   ';
	temp_table_3 += '				<option value="2185">男式衬衫紧身版42.5</option>  ';
	temp_table_3 += '				<option value="2186">男式衬衫紧身版43 </option>   ';
	temp_table_3 += '				<option value="2187">男式衬衫紧身版43.5</option>  ';
	temp_table_3 += '				<option value="2188">男式衬衫紧身版44 </option>   ';
	temp_table_3 += '				<option value="2189">男式衬衫紧身版44.5</option>  ';
	temp_table_3 += '				<option value="2190">男式衬衫紧身版45 </option>   ';
	temp_table_3 += '				<option value="2191">男式衬衫紧身版45.5</option>  ';
	temp_table_3 += '				<option value="2192">男式衬衫紧身版46 </option>   ';
	temp_table_3 += '				<option value="2193">男式衬衫紧身版46.5</option>  ';
	temp_table_3 += '				<option value="2194">男式衬衫紧身版47 </option>   ';
	temp_table_3 += '				<option value="2276">男式衬衫修身版38 </option>   ';
	temp_table_3 += '				<option value="2277">男式衬衫修身版38.5</option>  ';
	temp_table_3 += '				<option value="2278">男式衬衫修身版39 </option>   ';
	temp_table_3 += '				<option value="2279">男式衬衫修身版39.5</option>  ';
	temp_table_3 += '				<option value="2280">男式衬衫修身版40 </option>   ';
	temp_table_3 += '				<option value="2281">男式衬衫修身版40.5</option>  ';
	temp_table_3 += '				<option value="2282">男式衬衫修身版41 </option>   ';
	temp_table_3 += '				<option value="2283">男式衬衫修身版41.5</option>  ';
	temp_table_3 += '				<option value="2284">男式衬衫修身版42 </option>   ';
	temp_table_3 += '				<option value="2285">男式衬衫修身版42.5</option>  ';
	temp_table_3 += '				<option value="2286">男式衬衫修身版43 </option>   ';
	temp_table_3 += '				<option value="2287">男式衬衫修身版43.5</option>  ';
	temp_table_3 += '				<option value="2288">男式衬衫修身版44 </option>   ';
	temp_table_3 += '				<option value="2289">男式衬衫修身版44.5</option>  ';
	temp_table_3 += '				<option value="2290">男式衬衫修身版45 </option>   ';
	temp_table_3 += '				<option value="2291">男式衬衫修身版45.5</option>  ';
	temp_table_3 += '				<option value="2292">男式衬衫修身版46 </option>   ';
	temp_table_3 += '				<option value="2293">男式衬衫修身版46.5</option>  ';
	temp_table_3 += '				<option value="2294">男式衬衫修身版47 </option>   ';
	temp_table_3 += '				<option value="2376">男式衬衫合身版38 </option>   ';
	temp_table_3 += '				<option value="2377">男式衬衫合身版38.5</option>  ';
	temp_table_3 += '				<option value="2378">男式衬衫合身版39 </option>   ';
	temp_table_3 += '				<option value="2379">男式衬衫合身版39.5</option>  ';
	temp_table_3 += '				<option value="2380">男式衬衫合身版40 </option>   ';
	temp_table_3 += '				<option value="2381">男式衬衫合身版40.5</option>  ';
	temp_table_3 += '				<option value="2382">男式衬衫合身版41 </option>   ';
	temp_table_3 += '				<option value="2383">男式衬衫合身版41.5</option>  ';
	temp_table_3 += '				<option value="2384">男式衬衫合身版42 </option>   ';
	temp_table_3 += '				<option value="2385">男式衬衫合身版42.5</option>  ';
	temp_table_3 += '				<option value="2386">男式衬衫合身版43 </option>   ';
	temp_table_3 += '				<option value="2387">男式衬衫合身版43.5</option>  ';
	temp_table_3 += '				<option value="2388">男式衬衫合身版44 </option>   ';
	temp_table_3 += '				<option value="2389">男式衬衫合身版44.5</option>  ';
	temp_table_3 += '				<option value="2390">男式衬衫合身版45 </option>   ';
	temp_table_3 += '				<option value="2391">男式衬衫合身版45.5</option>  ';
	temp_table_3 += '				<option value="2392">男式衬衫合身版46 </option>   ';
	temp_table_3 += '				<option value="2393">男式衬衫合身版46.5</option>  ';
	temp_table_3 += '				<option value="2394">男式衬衫合身版47 </option>   ';
	temp_table_3 += '				<option value="2476">男式衬衫宽松版38 </option>   ';
	temp_table_3 += '				<option value="2477">男式衬衫宽松版38.5</option>  ';
	temp_table_3 += '				<option value="2478">男式衬衫宽松版39 </option>   ';
	temp_table_3 += '				<option value="2479">男式衬衫宽松版39.5</option>  ';
	temp_table_3 += '				<option value="2480">男式衬衫宽松版40 </option>   ';
	temp_table_3 += '				<option value="2481">男式衬衫宽松版40.5</option>  ';
	temp_table_3 += '				<option value="2482">男式衬衫宽松版41 </option>   ';
	temp_table_3 += '				<option value="2483">男式衬衫宽松版41.5</option>  ';
	temp_table_3 += '				<option value="2484">男式衬衫宽松版42 </option>   ';
	temp_table_3 += '				<option value="2485">男式衬衫宽松版42.5</option>  ';
	temp_table_3 += '				<option value="2486">男式衬衫宽松版43 </option>   ';
	temp_table_3 += '				<option value="2487">男式衬衫宽松版43.5</option>  ';
	temp_table_3 += '				<option value="2488">男式衬衫宽松版44 </option>   ';
	temp_table_3 += '				<option value="2489">男式衬衫宽松版44.5</option>  ';
	temp_table_3 += '				<option value="2490">男式衬衫宽松版45 </option>   ';
	temp_table_3 += '				<option value="2491">男式衬衫宽松版45.5</option>  ';
	temp_table_3 += '				<option value="2492">男式衬衫宽松版46 </option>   ';
	temp_table_3 += '				<option value="2493">男式衬衫宽松版46.5</option>  ';
	temp_table_3 += '				<option value="2494">男式衬衫宽松版47 </option>   ';
	temp_table_3 += '			</select> <label>号衣尺码</label>                     ';
	temp_table_3 += '		</div>                                                    ';
	temp_table_3 += '	</div>                                                        ';
	temp_table_3 += '</div>                                                           ';

	var temp_table_4 = '';
	temp_table_4 += '<div class="card-panel">                                        ';
	temp_table_4 += '	<div class="row">                                            ';
	temp_table_4 += '		<div class="input-field col s12 m12 l12">                ';
	temp_table_4 += '			<select name="size">                                 ';
	temp_table_4 += '				<option value="2570">女式衬衫修身版35</option>   ';
	temp_table_4 += '				<option value="2571">女式衬衫修身版35.5</option> ';
	temp_table_4 += '				<option value="2572">女式衬衫修身版36</option>   ';
	temp_table_4 += '				<option value="2573">女式衬衫修身版36.5</option> ';
	temp_table_4 += '				<option value="2574">女式衬衫修身版37</option>   ';
	temp_table_4 += '				<option value="2575">女式衬衫修身版37.5</option> ';
	temp_table_4 += '				<option value="2576">女式衬衫修身版38</option>   ';
	temp_table_4 += '				<option value="2577">女式衬衫修身版38.5</option> ';
	temp_table_4 += '				<option value="2578">女式衬衫修身版39</option>   ';
	temp_table_4 += '				<option value="2579">女式衬衫修身版39.5</option> ';
	temp_table_4 += '				<option value="2580">女式衬衫修身版40</option>   ';
	temp_table_4 += '				<option value="2581">女式衬衫修身版40.5</option> ';
	temp_table_4 += '				<option value="2582">女式衬衫修身版41</option>   ';
	temp_table_4 += '				<option value="2583">女式衬衫修身版41.5</option> ';
	temp_table_4 += '				<option value="2584">女式衬衫修身版42</option>   ';
	temp_table_4 += '				<option value="2585">女式衬衫修身版42.5</option> ';
	temp_table_4 += '				<option value="2586">女式衬衫修身版43</option>   ';
	temp_table_4 += '				<option value="2587">女式衬衫修身版43.5</option> ';
	temp_table_4 += '				<option value="2588">女式衬衫修身版44</option>   ';
	temp_table_4 += '				<option value="2589">女式衬衫修身版44.5</option> ';
	temp_table_4 += '				<option value="2590">女式衬衫修身版45</option>   ';
	temp_table_4 += '				<option value="2670">女式衬衫合身版35</option>   ';
	temp_table_4 += '				<option value="2671">女式衬衫合身版35.5</option> ';
	temp_table_4 += '				<option value="2672">女式衬衫合身版36</option>   ';
	temp_table_4 += '				<option value="2673">女式衬衫合身版36.5</option> ';
	temp_table_4 += '				<option value="2674">女式衬衫合身版37</option>   ';
	temp_table_4 += '				<option value="2675">女式衬衫合身版37.5</option> ';
	temp_table_4 += '				<option value="2676">女式衬衫合身版38</option>   ';
	temp_table_4 += '				<option value="2677">女式衬衫合身版38.5</option> ';
	temp_table_4 += '				<option value="2678">女式衬衫合身版39</option>   ';
	temp_table_4 += '				<option value="2679">女式衬衫合身版39.5</option> ';
	temp_table_4 += '				<option value="2680">女式衬衫合身版40</option>   ';
	temp_table_4 += '				<option value="2681">女式衬衫合身版40.5</option> ';
	temp_table_4 += '				<option value="2682">女式衬衫合身版41</option>   ';
	temp_table_4 += '				<option value="2683">女式衬衫合身版41.5</option> ';
	temp_table_4 += '				<option value="2684">女式衬衫合身版42</option>   ';
	temp_table_4 += '				<option value="2685">女式衬衫合身版42.5</option> ';
	temp_table_4 += '				<option value="2686">女式衬衫合身版43</option>   ';
	temp_table_4 += '				<option value="2687">女式衬衫合身版43.5</option> ';
	temp_table_4 += '				<option value="2688">女式衬衫合身版44</option>   ';
	temp_table_4 += '				<option value="2689">女式衬衫合身版44.5</option> ';
	temp_table_4 += '				<option value="2690">女式衬衫合身版45</option>   ';
	temp_table_4 += '				<option value="2770">女式衬衫宽松版35</option>   ';
	temp_table_4 += '				<option value="2771">女式衬衫宽松版35.5</option> ';
	temp_table_4 += '				<option value="2772">女式衬衫宽松版36</option>   ';
	temp_table_4 += '				<option value="2773">女式衬衫宽松版36.5</option> ';
	temp_table_4 += '				<option value="2774">女式衬衫宽松版37</option>   ';
	temp_table_4 += '				<option value="2775">女式衬衫宽松版37.5</option> ';
	temp_table_4 += '				<option value="2776">女式衬衫宽松版38</option>   ';
	temp_table_4 += '				<option value="2777">女式衬衫宽松版38.5</option> ';
	temp_table_4 += '				<option value="2778">女式衬衫宽松版39</option>   ';
	temp_table_4 += '				<option value="2779">女式衬衫宽松版39.5</option> ';
	temp_table_4 += '				<option value="2780">女式衬衫宽松版40</option>   ';
	temp_table_4 += '				<option value="2781">女式衬衫宽松版40.5</option> ';
	temp_table_4 += '				<option value="2782">女式衬衫宽松版41</option>   ';
	temp_table_4 += '				<option value="2783">女式衬衫宽松版41.5</option> ';
	temp_table_4 += '				<option value="2784">女式衬衫宽松版42</option>   ';
	temp_table_4 += '				<option value="2785">女式衬衫宽松版42.5</option> ';
	temp_table_4 += '				<option value="2786">女式衬衫宽松版43</option>   ';
	temp_table_4 += '				<option value="2787">女式衬衫宽松版43.5</option> ';
	temp_table_4 += '				<option value="2788">女式衬衫宽松版44</option>   ';
	temp_table_4 += '				<option value="2789">女式衬衫宽松版44.5</option> ';
	temp_table_4 += '				<option value="2790">女式衬衫宽松版45</option>   ';
	temp_table_4 += '			</select> <label>号衣尺码</label>                    ';
	temp_table_4 += '		</div>                                                   ';
	temp_table_4 += '	</div>                                                       ';
	temp_table_4 += '</div>                                                          ';

	var temp_table_5 = '';
	temp_table_5 += '<div class="card-panel">                                         ';
	temp_table_5 += '	<div class="row">                                             ';
	temp_table_5 += '		<div class="input-field col s12 m12 l12">                 ';
	temp_table_5 += '			<select name="size">                                  ';
	temp_table_5 += '				<option value="2176">男式衬衫紧身版38 </option>   ';
	temp_table_5 += '				<option value="2177">男式衬衫紧身版38.5</option>  ';
	temp_table_5 += '				<option value="2178">男式衬衫紧身版39 </option>   ';
	temp_table_5 += '				<option value="2179">男式衬衫紧身版39.5</option>  ';
	temp_table_5 += '				<option value="2180">男式衬衫紧身版40 </option>   ';
	temp_table_5 += '				<option value="2181">男式衬衫紧身版40.5</option>  ';
	temp_table_5 += '				<option value="2182">男式衬衫紧身版41 </option>   ';
	temp_table_5 += '				<option value="2183">男式衬衫紧身版41.5</option>  ';
	temp_table_5 += '				<option value="2184">男式衬衫紧身版42 </option>   ';
	temp_table_5 += '				<option value="2185">男式衬衫紧身版42.5</option>  ';
	temp_table_5 += '				<option value="2186">男式衬衫紧身版43 </option>   ';
	temp_table_5 += '				<option value="2187">男式衬衫紧身版43.5</option>  ';
	temp_table_5 += '				<option value="2188">男式衬衫紧身版44 </option>   ';
	temp_table_5 += '				<option value="2189">男式衬衫紧身版44.5</option>  ';
	temp_table_5 += '				<option value="2190">男式衬衫紧身版45 </option>   ';
	temp_table_5 += '				<option value="2191">男式衬衫紧身版45.5</option>  ';
	temp_table_5 += '				<option value="2192">男式衬衫紧身版46 </option>   ';
	temp_table_5 += '				<option value="2193">男式衬衫紧身版46.5</option>  ';
	temp_table_5 += '				<option value="2194">男式衬衫紧身版47 </option>   ';
	temp_table_5 += '				<option value="2276">男式衬衫修身版38 </option>   ';
	temp_table_5 += '				<option value="2277">男式衬衫修身版38.5</option>  ';
	temp_table_5 += '				<option value="2278">男式衬衫修身版39 </option>   ';
	temp_table_5 += '				<option value="2279">男式衬衫修身版39.5</option>  ';
	temp_table_5 += '				<option value="2280">男式衬衫修身版40 </option>   ';
	temp_table_5 += '				<option value="2281">男式衬衫修身版40.5</option>  ';
	temp_table_5 += '				<option value="2282">男式衬衫修身版41 </option>   ';
	temp_table_5 += '				<option value="2283">男式衬衫修身版41.5</option>  ';
	temp_table_5 += '				<option value="2284">男式衬衫修身版42 </option>   ';
	temp_table_5 += '				<option value="2285">男式衬衫修身版42.5</option>  ';
	temp_table_5 += '				<option value="2286">男式衬衫修身版43 </option>   ';
	temp_table_5 += '				<option value="2287">男式衬衫修身版43.5</option>  ';
	temp_table_5 += '				<option value="2288">男式衬衫修身版44 </option>   ';
	temp_table_5 += '				<option value="2289">男式衬衫修身版44.5</option>  ';
	temp_table_5 += '				<option value="2290">男式衬衫修身版45 </option>   ';
	temp_table_5 += '				<option value="2291">男式衬衫修身版45.5</option>  ';
	temp_table_5 += '				<option value="2292">男式衬衫修身版46 </option>   ';
	temp_table_5 += '				<option value="2293">男式衬衫修身版46.5</option>  ';
	temp_table_5 += '				<option value="2294">男式衬衫修身版47 </option>   ';
	temp_table_5 += '				<option value="2376">男式衬衫合身版38 </option>   ';
	temp_table_5 += '				<option value="2377">男式衬衫合身版38.5</option>  ';
	temp_table_5 += '				<option value="2378">男式衬衫合身版39 </option>   ';
	temp_table_5 += '				<option value="2379">男式衬衫合身版39.5</option>  ';
	temp_table_5 += '				<option value="2380">男式衬衫合身版40 </option>   ';
	temp_table_5 += '				<option value="2381">男式衬衫合身版40.5</option>  ';
	temp_table_5 += '				<option value="2382">男式衬衫合身版41 </option>   ';
	temp_table_5 += '				<option value="2383">男式衬衫合身版41.5</option>  ';
	temp_table_5 += '				<option value="2384">男式衬衫合身版42 </option>   ';
	temp_table_5 += '				<option value="2385">男式衬衫合身版42.5</option>  ';
	temp_table_5 += '				<option value="2386">男式衬衫合身版43 </option>   ';
	temp_table_5 += '				<option value="2387">男式衬衫合身版43.5</option>  ';
	temp_table_5 += '				<option value="2388">男式衬衫合身版44 </option>   ';
	temp_table_5 += '				<option value="2389">男式衬衫合身版44.5</option>  ';
	temp_table_5 += '				<option value="2390">男式衬衫合身版45 </option>   ';
	temp_table_5 += '				<option value="2391">男式衬衫合身版45.5</option>  ';
	temp_table_5 += '				<option value="2392">男式衬衫合身版46 </option>   ';
	temp_table_5 += '				<option value="2393">男式衬衫合身版46.5</option>  ';
	temp_table_5 += '				<option value="2394">男式衬衫合身版47 </option>   ';
	temp_table_5 += '				<option value="2476">男式衬衫宽松版38 </option>   ';
	temp_table_5 += '				<option value="2477">男式衬衫宽松版38.5</option>  ';
	temp_table_5 += '				<option value="2478">男式衬衫宽松版39 </option>   ';
	temp_table_5 += '				<option value="2479">男式衬衫宽松版39.5</option>  ';
	temp_table_5 += '				<option value="2480">男式衬衫宽松版40 </option>   ';
	temp_table_5 += '				<option value="2481">男式衬衫宽松版40.5</option>  ';
	temp_table_5 += '				<option value="2482">男式衬衫宽松版41 </option>   ';
	temp_table_5 += '				<option value="2483">男式衬衫宽松版41.5</option>  ';
	temp_table_5 += '				<option value="2484">男式衬衫宽松版42 </option>   ';
	temp_table_5 += '				<option value="2485">男式衬衫宽松版42.5</option>  ';
	temp_table_5 += '				<option value="2486">男式衬衫宽松版43 </option>   ';
	temp_table_5 += '				<option value="2487">男式衬衫宽松版43.5</option>  ';
	temp_table_5 += '				<option value="2488">男式衬衫宽松版44 </option>   ';
	temp_table_5 += '				<option value="2489">男式衬衫宽松版44.5</option>  ';
	temp_table_5 += '				<option value="2490">男式衬衫宽松版45 </option>   ';
	temp_table_5 += '				<option value="2491">男式衬衫宽松版45.5</option>  ';
	temp_table_5 += '				<option value="2492">男式衬衫宽松版46 </option>   ';
	temp_table_5 += '				<option value="2493">男式衬衫宽松版46.5</option>  ';
	temp_table_5 += '				<option value="2494">男式衬衫宽松版47 </option>   ';
	temp_table_5 += '				<option value="2570">女式衬衫修身版35</option>   ';
	temp_table_5 += '				<option value="2571">女式衬衫修身版35.5</option> ';
	temp_table_5 += '				<option value="2572">女式衬衫修身版36</option>   ';
	temp_table_5 += '				<option value="2573">女式衬衫修身版36.5</option> ';
	temp_table_5 += '				<option value="2574">女式衬衫修身版37</option>   ';
	temp_table_5 += '				<option value="2575">女式衬衫修身版37.5</option> ';
	temp_table_5 += '				<option value="2576">女式衬衫修身版38</option>   ';
	temp_table_5 += '				<option value="2577">女式衬衫修身版38.5</option> ';
	temp_table_5 += '				<option value="2578">女式衬衫修身版39</option>   ';
	temp_table_5 += '				<option value="2579">女式衬衫修身版39.5</option> ';
	temp_table_5 += '				<option value="2580">女式衬衫修身版40</option>   ';
	temp_table_5 += '				<option value="2581">女式衬衫修身版40.5</option> ';
	temp_table_5 += '				<option value="2582">女式衬衫修身版41</option>   ';
	temp_table_5 += '				<option value="2583">女式衬衫修身版41.5</option> ';
	temp_table_5 += '				<option value="2584">女式衬衫修身版42</option>   ';
	temp_table_5 += '				<option value="2585">女式衬衫修身版42.5</option> ';
	temp_table_5 += '				<option value="2586">女式衬衫修身版43</option>   ';
	temp_table_5 += '				<option value="2587">女式衬衫修身版43.5</option> ';
	temp_table_5 += '				<option value="2588">女式衬衫修身版44</option>   ';
	temp_table_5 += '				<option value="2589">女式衬衫修身版44.5</option> ';
	temp_table_5 += '				<option value="2590">女式衬衫修身版45</option>   ';
	temp_table_5 += '				<option value="2670">女式衬衫合身版35</option>   ';
	temp_table_5 += '				<option value="2671">女式衬衫合身版35.5</option> ';
	temp_table_5 += '				<option value="2672">女式衬衫合身版36</option>   ';
	temp_table_5 += '				<option value="2673">女式衬衫合身版36.5</option> ';
	temp_table_5 += '				<option value="2674">女式衬衫合身版37</option>   ';
	temp_table_5 += '				<option value="2675">女式衬衫合身版37.5</option> ';
	temp_table_5 += '				<option value="2676">女式衬衫合身版38</option>   ';
	temp_table_5 += '				<option value="2677">女式衬衫合身版38.5</option> ';
	temp_table_5 += '				<option value="2678">女式衬衫合身版39</option>   ';
	temp_table_5 += '				<option value="2679">女式衬衫合身版39.5</option> ';
	temp_table_5 += '				<option value="2680">女式衬衫合身版40</option>   ';
	temp_table_5 += '				<option value="2681">女式衬衫合身版40.5</option> ';
	temp_table_5 += '				<option value="2682">女式衬衫合身版41</option>   ';
	temp_table_5 += '				<option value="2683">女式衬衫合身版41.5</option> ';
	temp_table_5 += '				<option value="2684">女式衬衫合身版42</option>   ';
	temp_table_5 += '				<option value="2685">女式衬衫合身版42.5</option> ';
	temp_table_5 += '				<option value="2686">女式衬衫合身版43</option>   ';
	temp_table_5 += '				<option value="2687">女式衬衫合身版43.5</option> ';
	temp_table_5 += '				<option value="2688">女式衬衫合身版44</option>   ';
	temp_table_5 += '				<option value="2689">女式衬衫合身版44.5</option> ';
	temp_table_5 += '				<option value="2690">女式衬衫合身版45</option>   ';
	temp_table_5 += '				<option value="2770">女式衬衫宽松版35</option>   ';
	temp_table_5 += '				<option value="2771">女式衬衫宽松版35.5</option> ';
	temp_table_5 += '				<option value="2772">女式衬衫宽松版36</option>   ';
	temp_table_5 += '				<option value="2773">女式衬衫宽松版36.5</option> ';
	temp_table_5 += '				<option value="2774">女式衬衫宽松版37</option>   ';
	temp_table_5 += '				<option value="2775">女式衬衫宽松版37.5</option> ';
	temp_table_5 += '				<option value="2776">女式衬衫宽松版38</option>   ';
	temp_table_5 += '				<option value="2777">女式衬衫宽松版38.5</option> ';
	temp_table_5 += '				<option value="2778">女式衬衫宽松版39</option>   ';
	temp_table_5 += '				<option value="2779">女式衬衫宽松版39.5</option> ';
	temp_table_5 += '				<option value="2780">女式衬衫宽松版40</option>   ';
	temp_table_5 += '				<option value="2781">女式衬衫宽松版40.5</option> ';
	temp_table_5 += '				<option value="2782">女式衬衫宽松版41</option>   ';
	temp_table_5 += '				<option value="2783">女式衬衫宽松版41.5</option> ';
	temp_table_5 += '				<option value="2784">女式衬衫宽松版42</option>   ';
	temp_table_5 += '				<option value="2785">女式衬衫宽松版42.5</option> ';
	temp_table_5 += '				<option value="2786">女式衬衫宽松版43</option>   ';
	temp_table_5 += '				<option value="2787">女式衬衫宽松版43.5</option> ';
	temp_table_5 += '				<option value="2788">女式衬衫宽松版44</option>   ';
	temp_table_5 += '				<option value="2789">女式衬衫宽松版44.5</option> ';
	temp_table_5 += '				<option value="2790">女式衬衫宽松版45</option>   ';
	temp_table_5 += '			</select> <label>号衣尺码</label>                    ';
	temp_table_5 += '		</div>                                                   ';
	temp_table_5 += '	</div>                                                       ';
	temp_table_5 += '</div>                                                          ';

	$("#measure_list").html(temp_table_1);
	console.log('已填充量体字段');
	$("#spc_b_list").html(temp_table_2);
	console.log('已填充特体字段');
	if($("#size_list").attr("sex") == 'man') {
		$("#size_list").html(temp_table_3);
		console.log('已填充男装号衣尺码列表');
	} else if($("#size_list").attr("sex") == 'woman') {
		$("#size_list").html(temp_table_4);
		console.log('已填充女装号衣尺码列表');
	} else if($("#size_list").attr("sex") == 'all') {
		$("#size_list").html(temp_table_5);
		console.log('已填充女装号衣尺码列表');
	} else {
		console.log('列表性别指定异常');
	}

	$("#measure_type").bind("change", function() {
		size_rule_base();
	});

	$("#neiwaichuan").bind("change", function() {
		size_rule_base();
	});

	$("#mode_measure_form").bind("change", function() {
		size_rule_base();
	});

	$("select[name$='tailor_type']").bind("change", function() {
		size_rule_base();
	});

	size_rule_base();

}

function size_rule_base() {

	{
		var selected_name = $("#measure_type option:selected").text();
		if(selected_name == "成衣尺寸") {
			$("#measure_list").show();
			$("#spc_b_list").show();
			$("#size_list").hide();
			console.log("--->成衣尺寸")
		} else if(selected_name == "号衣尺码") {
			$("#measure_list").hide();
			$("#spc_b_list").hide();
			$("#size_list").show();
			console.log("--->号衣尺码")
		} else if(selected_name == "不新增尺寸信息") {
			$("#measure_list").hide();
			$("#spc_b_list").hide();
			$("#size_list").hide();
			console.log("--->不新增尺寸信息")
		} else {
			$("#measure_list").show();
			$("#size_list").show();
			$("#spc_b_list").show();
			console.log("--->尺寸选项异常")
		}
		Materialize.updateTextFields();
	}

	{
		//		var selected_name = $("#neiwaichuan option:selected").text();
		//		if(selected_name == "内穿") {
		//			$("#div_houshen_chang_nei").show();
		//			$("#div_houshen_chang_wai").hide();
		//		} else if(selected_name == "外穿") {
		//			$("#div_houshen_chang_nei").hide();
		//			$("#div_houshen_chang_wai").show();
		//		} else {
		//			$("#div_houshen_chang_nei").show();
		//			$("#div_houshen_chang_wai").show();
		//		}
		//		Materialize.updateTextFields();
	}

	{
		$("#mode_measure_form").hide();
		//		var selected_name = $("#mode_measure_form").is(':checked');
		//		if(selected_name) {
		//			//精简模式
		//			$("#div_du_wei").hide();
		//			$("#div_xiuzhou_fei").hide();
		//			$("#div_chest_height").hide();
		//			$("#div_chest_distance").hide();
		//			$("#div_qianxiong_kuan").hide();
		//			$("#div_houbei_kuan").hide();
		//			$("#div_height").hide();
		//			$("#div_weight").hide();
		//		} else {
		//			//完整模式
		//			$("#div_du_wei").show();
		//			$("#div_xiuzhou_fei").show();
		//			$("#div_chest_height").show();
		//			$("#div_chest_distance").show();
		//			$("#div_qianxiong_kuan").show();
		//			$("#div_houbei_kuan").show();
		//			$("#div_height").show();
		//			$("#div_weight").show();
		//		}
		//		Materialize.updateTextFields();
	}

	{
		var selected_name = $("select[name$='tailor_type'] option:selected").val();
		if(selected_name == "YX-00-02") {
			//长
			$("#div_xiu_chang").show();
			$("#div_xiutouchang_zuo").show();
			$("#div_xiutouchang_you").show();
			$("#div_duanxiu_chang").hide();
			$("#div_duanxiu_kouwei").hide();
			console.log('长袖模式');
		} else if(selected_name == "YX-00-01") {
			//短
			$("#div_xiu_chang").hide();
			$("#div_xiutouchang_zuo").hide();
			$("#div_xiutouchang_you").hide();
			$("#div_duanxiu_chang").show();
			$("#div_duanxiu_kouwei").show();
			console.log('短袖模式');
		} else {
			//长
			$("#div_xiu_chang").show();
			$("#div_xiutouchang_zuo").show();
			$("#div_xiutouchang_you").show();
			$("#div_duanxiu_chang").hide();
			$("#div_duanxiu_kouwei").hide();
			console.log('长短袖异常');
		} {
			$("#div_xiu_chang").show();
			$("#div_xiutouchang_zuo").show();
			$("#div_xiutouchang_you").show();
			$("#div_duanxiu_chang").show();
			$("#div_duanxiu_kouwei").show();
		}
		Materialize.updateTextFields();
	}

}

//启用尺寸调整
function use_size_change() {
	$("#measure_list").show();
	$("#size_list").hide();
	$("#measure_type").bind("change", function() {
		var selected_name = $("#measure_type option:selected").text();
		if(selected_name == "号衣尺码") {
			$("#measure_list").hide();
			$("#size_list ").show();
		} else {
			$("#measure_list").show();
			$("#size_list").hide();
		}
		Materialize.updateTextFields();
	});
	console.log("已启用尺寸调整");
}
//-------------------------------------------------------------------------尺寸专项↑

//-------------------------------------------------------------------------款式工艺校验↓
//启用特殊款校验
function use_stylebase_check() {
	stylebase_check();
	$("#delivery_time_table_section_4").bind("change", function() {
		stylebase_check();
	});
	console.log("已启用特殊工艺校验");
}
//特殊款校验
function stylebase_check() {
	var selected_name = $("#delivery_time_table_section_4 option:selected").text();
	if(selected_name == "基础款") {
		$("option[stylebase='true']").removeAttr("disabled");
		//$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		$("option[stylebase='pbc']").attr("disabled", true);
	} else if(selected_name == "复杂款") {
		$("option[stylebase='true']").removeAttr("disabled");
		//$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		//$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		$("option[stylebase='pbc']").attr("disabled", true);
	} else if(selected_name == "客供款") {
		$("option[stylebase='true']").removeAttr("disabled");
		//$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		//$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		//$("option[stylebase='pbc']").attr("disabled", true);
	} else {
		$("option[stylebase='true']").removeAttr("disabled");
		$("option[stylebase='true']").attr("disabled", true);
		$("option[stylebase='false']").removeAttr("disabled");
		$("option[stylebase='false']").attr("disabled", true);
		$("option[stylebase='pbc']").removeAttr("disabled");
		$("option[stylebase='pbc']").attr("disabled", true);
	}
	$('select').material_select();
}
//-------------------------------------------------------------------------款式工艺校验↑

//-------------------------------------------------------------------------委外商品报价模块↓
//定义交期系统字典
var SubcontractTable;
//初始化交期系统字典
function initSubcontractTable() {
	SubcontractTable = {
		'西服套装（高价位）': {
			'code': 'SUIT5000',
			'type': {
				'计算价格': {
					'fabric_length': '3',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'西服套装': {
			'code': 'SUIT2980',
			'type': {
				'一口价': {
					'USKIN西服套装': '2980'
				}
			}
		},
		'西装上衣': {
			'code': 'BLAZER',
			'type': {
				'一口价': {
					'USKIN西装上衣': '1788'
				},
				'计算价格': {
					'fabric_length': '3',
					'ratio': '0.6',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'西装西裤': {
			'code': 'PANTS',
			'type': {
				'一口价': {
					'USKIN西装西裤': '1192'
				},
				'计算价格': {
					'fabric_length': '3',
					'ratio': '0.4',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'马甲': {
			'code': 'VEST',
			'type': {
				'一口价': {
					'USKIN西装马甲': '890'
				},
				'计算价格': {
					'fabric_length': '3',
					'ratio': '0.3',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': '',
						'体验店价格': ''
					},
					'process_cost': {
						'西装机器粘合衬': '1500',
						'西装机器半毛衬': '2000',
						'西装机器全毛衬': '2500',
						'西装半手工全毛衬': '3500',
						'西装手工全毛衬': '6500'
					}
				}
			}
		},
		'牛仔裤（国产）': {
			'code': 'JEANS_H',
			'type': {
				'一口价': {
					'牛仔裤': '998'
				}
			}
		},
		'牛仔裤（进口）': {
			'code': 'JEANS_I',
			'type': {
				'一口价': {
					'牛仔裤': '1298'
				}
			}
		},
		'大衣': {
			'code': 'OVERCOAT',
			'type': {
				'一口价': {
					'USKIN大衣': '2680'
				},
				'计算价格': {
					'fabric_length': '2.4',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'3500': '',
						'2500': '',
						'2000': '',
						'1200': '',
						'800': ''
					},
					'process_cost': {
						'大衣/粘合衬': '1500',
						'大衣/半毛衬': '2000',
						'大衣/全毛衬': '2500'
					}
				}
			}
		},
		'风衣': {
			'code': 'DUSTCOAT',
			'type': {
				'计算价格': {
					'fabric_length': '2.4',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'800': ''
					},
					'process_cost': {
						'大衣/粘合衬': '1500',
						'大衣/半毛衬': '2000',
						'大衣/全毛衬': '2500'
					}
				}
			}
		},
		'休闲裤': {
			'code': 'SLACKS',
			'type': {
				'一口价': {
					'USKIN休闲裤': '998'
				},
				'计算价格': {
					'fabric_length': '3.0',
					'ratio': '0.4',
					'fabric_unit_cost': {
						'800': ''
					},
					'process_cost': {
						'粘合衬': '1500'
					}
				}
			}
		},
		'夹克': {
			'code': 'JACKET',
			'type': {
				'一口价': {
					'USKIN夹克': '1980'
				},
				'计算价格': {
					'fabric_length': '1.8',
					'ratio': '1.0',
					'fabric_unit_cost': {
						'2500': '',
						'2000': '',
						'1200': '',
						'900': '',
						'700': '',
						'600': '',
						'500': ''
					},
					'process_cost': {
						'粘合衬': '1500'
					}
				}
			}
		},
		'羊绒衫': {
			'code': 'CASHMERE',
			'type': {
				'一口价': {
					'USKIN羊绒衫（低价位）': '998',
					'USKIN羊绒衫（高价位）': '1298'
				}
			}
		}
	};
}
//填充产品列表
function set_subcontract_design_code_list() {
	var list_1 = "";
	for(var prop1 in SubcontractTable) {
		list_1 += "<option value=\"" + SubcontractTable[prop1].code + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}
	$("#subcontract_design_code").html(list_1);
	$('select').material_select();
	set_subcontract_price_type_list();
}
//填充定价模式列表
function set_subcontract_price_type_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	var list_2 = "";
	for(var prop1 in SubcontractTable) {
		if(prop1 == selected_1) {
			for(var prop2 in SubcontractTable[prop1].type) {
				list_2 += "<option>";
				list_2 += prop2;
				list_2 += "</option>";
			}
		}
	}
	$("#subcontract_price_type").html(list_2);
	$('select').material_select();
	dispose_subcontract_price_type();
}
//处理报价类型
function dispose_subcontract_price_type() {
	selected_1 = "" + $("#subcontract_price_type  option:selected").text();
	if("一口价" == selected_1) {
		set_subcontract_price_type_1();
		$("#subcontract_peice_final_div").show();
		$("#subcontract_fabric_unit_cost_div").hide();
		$("#subcontract_process_cost_div").hide();
		$("#subcontract_fabric_length_div").hide();
		$("#subcontract_ratio_div").hide();
	} else if("计算价格" == selected_1) {
		set_subcontract_price_type_2();
		$("#subcontract_peice_final_div").hide();
		$("#subcontract_fabric_unit_cost_div").show();
		$("#subcontract_process_cost_div").show();
		$("#subcontract_fabric_length_div").show();
		$("#subcontract_ratio_div").show();
	}
}
//填充一口价
function set_subcontract_price_type_1() {
	set_subcontract_peice_final_list();
}
//填充计算价格
function set_subcontract_price_type_2() {
	set_subcontract_process_cost_list();
	set_subcontract_fabric_unit_cost_list();
	set_subcontract_fabric_length();
	set_subcontract_ratio();
}
//填充一口价列表
function set_subcontract_peice_final_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	var list_1 = "";
	for(var prop1 in SubcontractTable[selected_1].type[selected_2]) {
		list_1 += "<option value=\"" + SubcontractTable[selected_1].type[selected_2][prop1] + "\">";
		list_1 += prop1 + " " + SubcontractTable[selected_1].type[selected_2][prop1];
		list_1 += "</option>";
	}
	$("#subcontract_peice_final").html(list_1);
	$('select').material_select();
}
//填充面料单价列表
function set_subcontract_fabric_unit_cost_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	var list_1 = "";
	for(var prop1 in SubcontractTable[selected_1].type[selected_2].fabric_unit_cost) {
		list_1 += "<option value=\"" + prop1 + "\">";
		list_1 += prop1;
		list_1 += "</option>";
	}
	$("#subcontract_fabric_unit_cost").html(list_1);
	$('select').material_select();
}
//填充加工费列表
function set_subcontract_process_cost_list() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	var list_1 = "";
	for(var prop1 in SubcontractTable[selected_1].type[selected_2].process_cost) {
		list_1 += "<option value=\"" + SubcontractTable[selected_1].type[selected_2].process_cost[prop1] + "\">";
		list_1 += prop1 + " " + SubcontractTable[selected_1].type[selected_2].process_cost[prop1];
		list_1 += "</option>";
	}
	$("#subcontract_process_cost").html(list_1);
	$('select').material_select();
}
//填充默认面料用量
function set_subcontract_fabric_length() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	$("#subcontract_fabric_length").val(SubcontractTable[selected_1].type[selected_2].fabric_length);
}
//填充辅助参数
function set_subcontract_ratio() {
	selected_1 = "" + $("#subcontract_design_code option:selected").text();
	selected_2 = "" + $("#subcontract_price_type  option:selected").text();
	$("#subcontract_ratio").val(SubcontractTable[selected_1].type[selected_2].ratio);
}
//启用其他商品报价
function use_SubcontractTable() {
	initSubcontractTable();
	set_subcontract_design_code_list();
	$("#subcontract_design_code").bind("change", function() {
		set_subcontract_price_type_list(); //
	});
	$("#subcontract_price_type").bind("change", function() {
		dispose_subcontract_price_type(); //
	});
	console.log("已启用商品报价");
}
//-------------------------------------------------------------------------委外商品报价模块↑

//-------------------------------------------------------------------------辅助JS↓
/*
 * @description    根据某个字段实现对json数组的排序
 * @param   array  要排序的json数组对象
 * @param   field  排序字段（此参数必须为字符串）
 * @param   reverse 是否倒序（默认为false）
 * @return  array  返回排序后的json数组
 */
function jsonSort(array, field, reverse) {
	//数组长度小于2 或 没有指定排序字段 或 不是json格式数据
	if(array.length < 2 || !field || typeof array[0] !== "object") return array;
	//数字类型排序
	if(typeof array[0][field] === "number") {
		array.sort(function(x, y) {
			return x[field] - y[field]
		});
	}
	//字符串类型排序
	if(typeof array[0][field] === "string") {
		array.sort(function(x, y) {
			return x[field].localeCompare(y[field])
		});
	}
	//倒序
	if(reverse) {
		array.reverse();
	}
	return array;
}
//-------------------------------------------------------------------------辅助JS↑

var Repair_Type_Table;
//初始化修改字典
function initRepairTypeTable() {
	Repair_Type_Table = {
		"01": {
			"name_c": "减三围（袖肥相应顺减）",
			"price_c": 30,
			"time_c": 5,
			"type_code": "LZX-XG-05",
			"name_f": "减三围",
			"price_f": 20,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、拆底边、拆侧缝、修整尺寸、重新缝制"
		},
		"02": {
			"name_c": "减衣长",
			"price_c": 20,
			"time_c": 2,
			"type_code": "LZX-XG-01",
			"name_f": "减衣长",
			"price_f": 15,
			"time_f": 1,
			"tips_f": "拆包装、烫叠包装、修底边、重新缝制"
		},
		"03": {
			"name_c": "减袖长（从袖口减）",
			"price_c": 30,
			"time_c": 3,
			"type_code": "LZX-XH-14",
			"name_f": "减袖长（袖口处）",
			"price_f": 25,
			"time_f": 2,
			"tips_f": "拆包装、烫叠包装、拆袖头、修袖长、拆袖祺、重新缝制"
		},
		"04": {
			"name_c": "加腰省（减腰围）",
			"price_c": 20,
			"time_c": 2,
			"type_code": "LZX-XG-02",
			"name_f": "加腰省",
			"price_f": 15,
			"time_f": 1,
			"tips_f": "拆包装、烫叠包装、定后腰省位、成品收后腰省不到底"
		},
		"05": {
			"name_c": "腰省（可能有针眼）",
			"price_c": 20,
			"time_c": 2,
			"type_code": "LZX-XG-02",
			"name_f": "加腰省",
			"price_f": 15,
			"time_f": 1,
			"tips_f": "拆包装、烫叠包装、定后腰省位、成品收后腰省不到底"
		},
		"06": {
			"name_c": "修领窝（不换领子）",
			"price_c": 25,
			"time_c": 4,
			"type_code": "LZX-XG-12",
			"name_f": "修领窝(不换领子)",
			"price_f": 20,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆领子、下挖后领窝、拆肩缝及接线、重新上领"
		},
		"07": {
			"name_c": "换领子+修领窝",
			"price_c": 45,
			"time_c": 4,
			"type_code": "LZX-XH-09",
			"name_f": "换领子+修领窝",
			"price_f": 40,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆领子、下挖后领窝、重新做外插领"
		},
		"08": {
			"name_c": "换口袋",
			"price_c": 25,
			"time_c": 3,
			"type_code": "LZX-XH-01",
			"name_f": "换口袋",
			"price_f": 19,
			"time_f": 2,
			"tips_f": "拆包装、烫叠包装、拆口袋、重新裁、做口袋"
		},
		"09": {
			"name_c": "换小袖头",
			"price_c": 30,
			"time_c": 3,
			"type_code": "LZX-XH-02",
			"name_f": "换小袖头（可含装饰线）",
			"price_f": 27,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆小袖头、重新裁、做袖头（装饰线）"
		},
		"10": {
			"name_c": "换礼服袖头",
			"price_c": 40,
			"time_c": 3,
			"type_code": "LZX-XH-03",
			"name_f": "换大袖头（可含装饰线）",
			"price_f": 32,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆大袖头、重新裁、做袖头（装饰线）"
		},
		"11": {
			"name_c": "换领子",
			"price_c": 40,
			"time_c": 4,
			"type_code": "LZX-XH-06",
			"name_f": "换领子（可含装饰线）",
			"price_f": 34,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆领子、重新裁做外插领（装饰线）、烫叠包装"
		},
		"12": {
			"name_c": "换领子+小袖头",
			"price_c": 50,
			"time_c": 4,
			"type_code": "LZX-XH-07",
			"name_f": "换领子+袖头",
			"price_f": 42,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆领子、拆六角小袖头、重新裁做外插领、做袖头，烫叠包装"
		},
		"13": {
			"name_c": "换领子+礼服袖头",
			"price_c": 50,
			"time_c": 4,
			"type_code": "LZX-XH-07",
			"name_f": "换领子+袖头",
			"price_f": 42,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆领子、拆六角小袖头、重新裁做外插领、做袖头，烫叠包装"
		},
		"14": {
			"name_c": "换领子+袖头（装饰线）",
			"price_c": 50,
			"time_c": 4,
			"type_code": "LZX-XH-08",
			"name_f": "换领子+袖头（装饰线）",
			"price_f": 45,
			"time_f": 3,
			"tips_f": "拆包装、烫叠包装、拆领子、拆六角小袖头、重新裁做外插领、做袖头、正常三周装饰线"
		},
		"15": {
			"name_c": "换纽扣（普通树脂扣或客供纽扣）",
			"price_c": 25,
			"time_c": 2,
			"type_code": "LZX-XG-09",
			"name_f": "换纽扣（客供纽扣）",
			"price_f": 22,
			"time_f": 1,
			"tips_f": "拆包装、烫叠包装、割扣全身15个、重新成品钉15个扣、全部绕扣"
		},
		"16": {
			"name_c": "换纽扣（贝壳扣）",
			"price_c": 45,
			"time_c": 2,
			"type_code": "LZX-XG-10",
			"name_f": "换纽扣（贝壳扣）",
			"price_f": 41,
			"time_f": 1,
			"tips_f": "拆包装、烫叠包装、割扣全身15个、重新成品钉15个扣、全部绕扣"
		},
		"17": {
			"name_c": "换袖子（短袖）",
			"price_c": 45,
			"time_c": 5,
			"type_code": "LZX-XH-04",
			"name_f": "换袖子(短袖）",
			"price_f": 29,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆底边、拆侧缝、拆袖明线袖暗线、重新裁剪缝制"
		},
		"18": {
			"name_c": "换袖子（长袖）",
			"price_c": 65,
			"time_c": 5,
			"type_code": "LZX-XH-05",
			"name_f": "换袖子(长袖）",
			"price_f": 42,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、拆底边、拆侧缝、拆袖明线袖暗线、重新裁剪缝制"
		},
		"18_1": {
			"name_c": "换袖子（长袖）【客供面料】",
			"price_c": 50,
			"time_c": 5,
			"type_code": "LZX-XH-05",
			"name_f": "换袖子(长袖）",
			"price_f": 42,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、拆底边、拆侧缝、拆袖明线袖暗线、重新裁剪缝制"
		},
		"19": {
			"name_c": "换门襟（只能换位夹门襟）",
			"price_c": 60,
			"time_c": 5,
			"type_code": "LZX-XH-10",
			"name_f": "换门襟",
			"price_f": 42,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆底边、拆门襟一线、修门襟、左贴改夹门襟"
		},
		"20": {
			"name_c": "换一个前片（前片破损）",
			"price_c": 85,
			"time_c": 5,
			"type_code": "LZX-XH-11",
			"name_f": "换一个前片",
			"price_f": 49,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆底边、拆左边身片工序、换左片+口袋、重新裁做左片+口袋"
		},
		"20_1": {
			"name_c": "换一个前片（前片破损）【客供面料】",
			"price_c": 70,
			"time_c": 5,
			"type_code": "LZX-XH-11",
			"name_f": "换一个前片",
			"price_f": 49,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆底边、拆左边身片工序、换左片+口袋、重新裁做左片+口袋"
		},
		"21": {
			"name_c": "换后片（不换过肩）",
			"price_c": 100,
			"time_c": 5,
			"type_code": "LZX-XH-12",
			"name_f": "换后片（不换过肩）",
			"price_f": 52,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆领子、拆袖头、拆底边、拆侧缝、拆袖暗线明线、拆肩缝、拆过肩明线暗线，裁后片，重新缝制"
		},
		"21_1": {
			"name_c": "换后片（不换过肩）【客供面料】",
			"price_c": 80,
			"time_c": 5,
			"type_code": "LZX-XH-12",
			"name_f": "换后片（不换过肩）",
			"price_f": 52,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆领子、拆袖头、拆底边、拆侧缝、拆袖暗线明线、拆肩缝、拆过肩明线暗线，裁后片，重新缝制"
		},
		"22": {
			"name_c": "减肩宽",
			"price_c": 35,
			"time_c": 5,
			"type_code": "LZX-XG-07",
			"name_f": "减肩宽",
			"price_f": 26,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、底边、侧缝、袖子、修肩宽、重新缝制"
		},
		"23": {
			"name_c": "袖山处减袖长",
			"price_c": 40,
			"time_c": 5,
			"type_code": "LZX-XG-18",
			"name_f": "袖山处减袖长",
			"price_f": 27,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、拆底边、拆侧缝、拆袖明线袖暗线、修袖山、重新缝制"
		},
		"24": {
			"name_c": "下挖袖笼（不换袖子）",
			"price_c": 35,
			"time_c": 5,
			"type_code": "LZX-XG-19",
			"name_f": "下挖袖笼（不换袖子）",
			"price_f": 31,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、底边、侧缝，下挖袖窿，修袖山，上袖两遍，重新缝制"
		},
		"25": {
			"name_c": "减前胸宽、后背宽（不换袖子）",
			"price_c": 35,
			"time_c": 5,
			"type_code": "LZX-XG-06",
			"name_f": "减前胸宽、后背宽(不换袖子)",
			"price_f": 33,
			"time_f": 5,
			"tips_f": "拆包装、烫叠包装、拆袖头、底边、侧缝、拆袖暗线明线，修前胸宽和后背宽，修袖山，上袖两遍，重新缝制"
		},
		"26": {
			"name_c": "长袖加袖袢",
			"price_c": 35,
			"time_c": 3,
			"type_code": "LZX-XH-13",
			"name_f": "长袖加袖袢",
			"price_f": 29,
			"time_f": 2,
			"tips_f": "拆包装、烫叠包装、裁做袖袢，缝制袖袢"
		},
		"27": {
			"name_c": "整烫、折叠",
			"price_c": 15,
			"time_c": 2,
			"type_code": "LZX-XG-20",
			"name_f": "整烫、折叠",
			"price_f": 12,
			"time_f": 1,
			"tips_f": "拆包装、烫叠包装"
		},
		"28": {
			"name_c": "加刺绣（需配合换部件）",
			"price_c": 8,
			"time_c": 2,
			"type_code": "LZX-XH-14",
			"name_f": "加刺绣（需配合换部件）",
			"price_f": 5,
			"time_f": 2,
			"tips_f": "加刺绣（需配合换部件）",
			"add_time": "20180928"
		}
	}
}
//初始化修改列表
function set_repair_type_table() {
	var list_1 = "";
	for(var prop1 in Repair_Type_Table) {
		list_1 += "<option value=\"" + Repair_Type_Table[prop1].type_code + "\">";
		list_1 += Repair_Type_Table[prop1].name_c;
		list_1 += "</option>";
	}
	$("#Type").html(list_1);
	$('select').material_select();
}
//修改列表变更事件
function add_repair_type_event() {
	var type_code = '';
	var name_c = '';
	var price_c = 0;
	var time_c = 0;
	var tips_c = '';
	var name_f = '';
	var price_f = 0;
	var time_f = 0;
	var tips_f = '';
	$("#Type option:selected").each(function() {
		for(var prop2 in Repair_Type_Table) {
			if($(this).val() == Repair_Type_Table[prop2].type_code && $(this).text() == Repair_Type_Table[prop2].name_c) {
				name_c += Repair_Type_Table[prop2].name_c;
				price_c += Repair_Type_Table[prop2].price_c;
				time_c >= Repair_Type_Table[prop2].time_c ? time_c = time_c : time_c = Repair_Type_Table[prop2].time_c;
				tips_c += Repair_Type_Table[prop2].tips_c;
				name_f += Repair_Type_Table[prop2].name_f;
				price_f += Repair_Type_Table[prop2].price_f;
				time_f >= Repair_Type_Table[prop2].time_f ? time_f = time_f : time_f = Repair_Type_Table[prop2].time_f;
				tips_f += Repair_Type_Table[prop2].tips_f;
			}
		}
	})
	$("#type_code").val(type_code);
	$("#name_c").val(name_c);
	$("#price_c").val(price_c);
	$("#time_c").val(time_c);
	$("#tips_c").val(tips_c);
	$("#name_f").val(name_f);
	$("#price_f").val(price_f);
	$("#time_f").val(time_f);
	$("#tips_f").val(tips_f);
	Materialize.updateTextFields();

	console.log('---start---');
	console.log('type_code: ' + type_code);
	console.log('name_c   : ' + name_c);
	console.log('price_c  : ' + price_c);
	console.log('time_c   : ' + time_c);
	console.log('tips_c   : ' + tips_c);
	console.log('name_f   : ' + name_f);
	console.log('price_f  : ' + price_f);
	console.log('time_f   : ' + time_f);
	console.log('tips_f   : ' + tips_f);
	console.log('---end---');
}
//启用修改列表
function use_SubcontractTable() {
	initRepairTypeTable();
	set_repair_type_table();
	$("#Type").bind("change", function() {
		add_repair_type_event(); //
	});
}