/**
 * [ajaxError version 1.0.0]
 * 
 */

(function($) {
	jQuery.ajaxSettings.error = function(jqXhr, status, error) {
		var errorCode = jqXhr.status;
		if (true || errorCode == "500" || errorCode == "404") {
			if (jqXhr.responseText) {
				var exceptionMsg = jqXhr.responseText
						.replace(
								'<input id="breturn" class="button" type="button" value="返回" onclick="javascript:history.go(-1);" style=""/>',
								'<input id="close" class="button" type="button" value="关闭" onclick="closed();" style="background:#3daae9;"/>');
				top.$.jBox.open(exceptionMsg, "异常", 500, 480, {
							buttons : {}
						});
				return;
				var div = $("<div class='ajaxEP' style='filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#7F000000,endColorstr=#7F000000);'></div>")
						.css({
									"background" : "rgba(0,0,0,.6)",
									"position" : "absolute",
									"top" : "0",
									"left" : "0",
									"width" : "100%",
									"min-height" : "100%",
									"z-index" : 10000,
									"overflow" : "hidden"
								});
				var btn = $("<label>关闭</label>").click(function() {
							div.remove();
						}).css({
							"background" : "#ccc",
							"padding" : "5px 10px",
							"color" : "red",
							"cursor" : "pointer",
							"border-radius" : "3px",
							"position" : "fixed",
							"right" : "0"
						});
				div.append(btn);
				var div2 = $("<div style='background:#fff;margin:20px;border:5px solid #fff;'></div>");
				var id = "ajaxEP" + Math.floor(Math.random() * 100) + ""
						+ Math.floor(Math.random() * 99);
				var iframe = $("<iframe FRAMEBORDER=0 SCROLLING=no WIDTH='100%'HEIGHT='auto' id="
						+ id + "></iframe>")[0];
				div2.append(iframe);
				div.append(div2).append("<div style='clear:both;'></div>")
						.appendTo($("body"));

				// 延时操作 确保iframe刷新如dom节点
				setTimeout(function() {
							WinDoc = iframe.contentWindow
									? iframe.contentWindow.document
									: iframe.document;
							// func 2
							var html = WinDoc.getElementsByTagName('html');
							var doc = $(jqXhr.responseText);
							doc.each(function() {
										if ($(this).is("title")
												|| $(this).is("meta")
												|| $(this).is("script")
												|| $(this).is("link")) {
											WinDoc.getElementsByTagName('HEAD')
													.item(0)
													.appendChild($(this)[0]);
										} else {
											$("body", html).append(this);
										}
									});

							var height = $("body", html).height() + 50;
							$(html).height(height);
							$(iframe).prop("height", height);
						}, 10);
			}
		}
	}
})(jQuery);

(function() {
	var ajax = $.ajax;
	$.ajax = function(url, object) {
		var Url, Obj;
		if (object == undefined) {
			Obj = url;
		} else {
			Obj = object;
		}
		/*
		 * if(!Obj.dataType){ Obj.dataType = 'JSON'; }
		 */
		if (Obj.success && Obj.dataType && Obj.dataType.toLowerCase() != 'json') {
			var success = Obj.success;
			Obj.success = function() {
				var data = arguments[0];
				if (data && (typeof data).toLowerCase() == 'string'
						&& data.indexOf('defaultErrorAjaxMark') > -1) {
					jQuery.ajaxSettings.error.call(this, jqXhr, status, error);
				} else {
					success.apply(this, arguments);
				};
			};
		}
		if (Obj.error) {
			var errors = Obj.error;
			// console.info(Obj.error);
			Obj.error = function(jqXhr, status, error) {
				errors.call(this, jqXhr, status, error);
				jQuery.ajaxSettings.error.call(this, jqXhr, status, error);
			};
		}
		ajax(url, object);
	};
})(jQuery);
(function() {
	$.fn.button = function(Str) {
		Str = Str ? Str : '操作中···';
		if (Str == 'reset') {
			if ($(this).is('button')) {
				$(this).removeAttr("disabled");
				$(this).html($(this).attr('pre_value'));
				$(this).removeAttr('pre_value');
			}
			if ($(this).is("input[type='button']")) {
				$(this).removeAttr("disabled");
				$(this).val($(this).attr('pre_value'));
				$(this).removeAttr('pre_value');
			}
		} else {
			if ($(this).is('button')) {
				$(this).attr("disabled", "disabled");
				$(this).attr('pre_value', $(this).html());
				$(this).html(Str);
			}
			if ($(this).is("input[type='button']")) {
				$(this).attr("disabled", "disabled");
				$(this).attr('pre_value', $(this).val());
				$(this).val(Str);
			}
		}

	};

})();
