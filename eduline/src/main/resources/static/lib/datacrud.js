/**
 * 
 */
window.DataAdmin = {};
(function(window) {
	var dataAdmin = {
		options : {
			tbodyId : 'dataBody',			
			namespace : '',
			context : '/',
			addCallback:null,
			dataFormId : 'dataForm',
			dataTempId : 'dataTemp',
			selectors : {
				inputItem : 'button.inputItem',// 新建输入
				editItem : 'button.editItem',// 新建输入
				appendItem : 'button.appendItem',// 附加到前端
				removeItem : 'button.removeItem',// 后台删除
				earseItem : 'button.earseItem',// 前台删除
				persisteFormItem : 'button.persisteFormItem',// 提交前台
				persisteDataItem : 'button.persisteDataItem',// 提交前台
				pagerItem : 'ul.pagination  li a',// 页码导航
				queryItem : 'button.queryItem',// 查询
				jsonSource : 'select.jsonData',// json数据源
                toggleDisabled:'input:checkbox.toggleDisabled',//切换选择checkbox
                datepicker:".datepicker",
                datetimepicker:".datetimepicker"
                	
			},
			lineInfo : {
				single : true,
				endselector : 'tr.headLine'
			}

		},
		settings : {},
		init : function(opt) {
			$.extend(this.settings, this.options, opt);
		},
		paramprop : function(index, pname, pvalue) {
			return 'beans[' + index + '].' + pname + '=' + pvalue;
		},
		paramobj : function(index, pph) {
			if (!pph)
				return '';
			var sparam = [];
			for ( var kp in pph) {
				sparam.push(this.paramprop(index, kp, pph[kp]));
			}
			return sparam.join('&');
		},
		toParam : function() {
			var i = 0;
			var params = [];
			for ( var key in this.dataList) {
				params.push(this.paramobj(i++, this.dataList[key]));
			}
			// if(csrf)params.push(csrf);
			return params.join('&');
		},
		dataList : {},
		addItem : function(key, item) {
			this.dataList[key] = item;
		},
		removeItem : function(key) {
			delete this.dataList[key];
		},
		/***********************************************************************
		 * 导航监事件听器
		 **********************************************************************/
		addPagerListener : function(ajax) {
			var that = this;
			$(that.settings.selectors.pagerItem).off('click');
			$(that.settings.selectors.pagerItem).on('click', function() {
				var link = $(this).attr('link');
				if (link) {
					if (ajax) {
						doGet(link);
					} else {
						var p = parseQuery(link);
						doForm(p.url, p.query);
					}

				}
			});
		},
		addPagerNodeListener:function(id){
			var that = this;
			$(that.settings.selectors.pagerItem).off('click');
			$(that.settings.selectors.pagerItem).on('click', function() {
				var link = $(this).attr('link');
				if (link) {
					doAjaxNode(link,id);

				}
			});
		},
		/***********************************************************************
		 * 删除数据事件听器
		 **********************************************************************/
		addRemoveListener : function() {
			var that = this;

			$(that.settings.selectors.removeItem).off('click');
			$(that.settings.selectors.removeItem).on(
					'click',
					function() {
						var key = $(this).attr('key');
						var keyname = $(this).attr('keyname');
						var url = that.settings.context + that.settings.namespace
						+ '/remove/' + key;
						if(keyname){
							url = that.settings.context + that.settings.namespace
							+ '/remove/qc?query=' + keyname+"`"+key;
						}


                        layer.confirm('确定删除?', function(index){
                            //do something
							if(index==0){
                                $.post(url, function() {
                                    $(that).parent('td').parent('tr').remove();
                                    window.location.reload()
                                });
                            }
                            layer.close(index);
                        });
						
					});
		},
		/***********************************************************************
		 * 预备输入数据事件听器
		 **********************************************************************/
		addInputListener : function(inputarea) {
			var that = this;
			$(that.settings.selectors.inputItem).off('click');
			$(that.settings.selectors.inputItem).on(
					'click',
					function() {
						var url = that.settings.context
								+ that.settings.namespace + '/input';
						$("#" + inputarea).load(url)
					});
		},
		/***********************************************************************
		 * 查询数据事件听器
		 **********************************************************************/
		addQueryListener : function(form) {
			var that = this;
			$(that.settings.selectors.queryItem).off('click');
			$(that.settings.selectors.queryItem).on(
					'click',
					function() {
						var fthis = this;
						var fobj = $(fthis).parents('form');
						
						var url = that.settings.context
								+ that.settings.namespace + '/query/0';
						doQuery(fobj, url);
					});
		},
		/***********************************************************************
		 * 批量持久化数据事件听器
		 **********************************************************************/
		addPersisteDataListener : function() {
			var that = this;
			$(that.settings.selectors.persisteDataItem).bind(
					'click',
					function() {
						//var p = that.toParam();
						// log(that.dataList);
						
						var data = [];
						log(that.dataList);
						for (var d in that.dataList){
							var sdata=  {};
							for(var attname in that.dataList[d]){
								if(attname!='keyname'){
									sdata[attname] = that.dataList[d][attname];
								}
							}						
							data.push(sdata);
						}				
						
						if(data.length==0){
							notify("没有数据可以添加");
							return;
						}
						var pstr = "data=" + JSON.stringify(data);
						
						var url = that.settings.context
								+ that.settings.namespace + '/persisties';


                        layer.confirm('确定操作?', function(index){
                            //do something
							if(index==0){
                                $.post(url, pstr, function(data) {
                                    window.location.href=that.settings.context
                                        +

                                        that.settings.namespace + (that.settings.addCallback?that.settings.addCallback:'/pager/0?ajax=false');
                                });
							}
                            layer.close(index);
                        });

					});
		},
		/***********************************************************************
		 * 持久化表单FORM数据事件听器
		 **********************************************************************/
		addPersisteFormListener : function() {
			var that = this;
			$(that.settings.selectors.persisteFormItem).off('click');
			$(that.settings.selectors.persisteFormItem).on(
					'click',
					function() {
						var form = $(this).parents("form");
						var params = form.serializeArray();
						var sdata = {};
						for ( var i in params) {
							sdata[params[i].name]=params[i].value;
						}
						var data = "data="+JSON.stringify(sdata);
						var url = that.settings.context
									+ that.settings.namespace + '/persiste';

                        layer.confirm('确定操作?', function(index){
                            //do something
                            if(index==0){
                                $.post(url, data);
                            }
                            layer.close(index);
                        });
						
						
					});

		},
		/***********************************************************************
		 * 擦除前端数据事件听器
		 **********************************************************************/
		addEarseListener : function() {
			var that = this;
			$(window.document.body).off('click',
					that.settings.selectors.earseItem);
			$(window.document.body).on(
					'click',
					that.settings.selectors.earseItem,
					function() {
						
						var dthat =  this;


                        layer.confirm('is not?', function(index){
                            //do something
							if(index==0){
                                that.removeItem($(dthat).attr('keyname'));
                                if (!that.settings.lineInfo.single) {
                                    $(dthat).parent('td').parent('tr').nextUntil(
                                        that.settings.lineInfo.endselector)
                                        .remove();
                                }
                                $(dthat).parent('td').parent('tr').remove();
							}
                            layer.close(index);
                        });
						
					});
		},
		/***********************************************************************
		 * 附加前端数据事件听器
		 **********************************************************************/
		addAppendListener : function() {
			var that = this;
			$(that.settings.selectors.appendItem).off();
			$(that.settings.selectors.appendItem).on('click', function() {
				var fdata = $("#" + that.settings.dataFormId).serializeArray();
				var sdata = {};				
				for ( var i in fdata) {
					sdata[fdata[i].name] = fdata[i].value;					
				}
				var stringifiedCandidate = JSON.stringify(sdata);
				var keyval = $.md5(stringifiedCandidate);
				sdata['keyname'] = keyval;
				that.addItem(keyval, sdata);
				var data = {
					'list' : sdata
				};
				
				var html = template(that.settings.dataTempId, data);
				$('#' + that.settings.tbodyId).append(html);
				$("#" + that.settings.dataFormId)[0].reset();
			});
		},
		/***********************************************************************
		 * 附加前端数据事件听器
		 **********************************************************************/
		addEditListener : function() {
			var that = this;
			$(that.settings.selectors.editItem).off();
			$(that.settings.selectors.editItem).on(
					'click',
					function() {
						var key = $(this).attr('key');
						var url = that.settings.context
								+ that.settings.namespace + '/edit/' + key;
						window.location.href=url;
						//doForm(url);
					});
		},
		/***********************************************************************
		 * 为list添加json数据源
		 **********************************************************************/
		addJsonSourceListener : function(callback) {
			// log(this);
			var that = this;
			var selector = that.settings.selectors.jsonSource;
			var len = $(selector).size();
			
			for(var i=0;i<len;i++){
				var target = $(selector)[i];
				//log(target);
				(function(tp){
					setJsonData(tp,callback);
				})(target);
			}
					
		},
		/***********************************************************************
		 * 设置默认日期选择器行为
		 **********************************************************************/
		addDatePickerListener : function(callback) {
			
			$(this.settings.selectors.datepicker).datepicker({
				autoclose : true,
				language : 'zh-CN',
				format : 'yyyy-mm-dd'
			}

			).on('changeDate', function(event) {
				var that = this;
				if(callback&&$.isFunction(callback)){
					callback.call(this,event.date.getTime());
				}else{
					$(that).next("input:hidden").val(event.date.getTime());
				}
			});
		},
		/***********************************************************************
		 * 设置默认日期时间选择器行为
		**********************************************************************/
		addDatetimePickerListener:function(callback){
			var that = this;
			$(this.settings.selectors.datetimepicker).datetimepicker('remove');
			$(this.settings.selectors.datetimepicker).datetimepicker(
					  {
						  //format:'yyyy-mm-dd hh:ii',
						  //forceParse:true,
						  language:'zh-CN',
						  fontAwesome:true,
						  todayBtn:true,
						  autoclose:true,
						  todayHighlight:true
						 		  
					  }		
					).on('changeDate', function(ev){
						var oft = 8*3600*1000;
						if(callback&&$.isFunction(callback)){
							callback.call(that,ev.date.getTime()-oft);
						}else{
							$(this).next(':hidden').val(ev.date.getTime()-oft);
						}
						
						
				    });			
			
		},
		addTimerPickerListener:function(callback){
			var that = this;
			$(this.settings.selectors.datetimepicker).datetimepicker('remove');
			$(this.settings.selectors.datetimepicker).datetimepicker(
					  {
						  format:'hh:ii',
						  //forceParse:true,
						  language:'zh-CN',
						  fontAwesome:true,
						  todayBtn:true,
						  autoclose:true,
						  startView:'hour',
						  todayHighlight:true
						 		  
					  }		
					).on('changeDate', function(ev){
						var oft = 8*3600*1000;
						if(callback&&$.isFunction(callback)){
							callback.call(that,ev.date.getTime()-oft);
						}else{
							$(this).next(':hidden').val(ev.date.getTime()-oft);
						}
						
						
				    });	
		},
		/**
		 * 当该按钮被按下时禁用其前/后一个组件
		 * @param true previous field via next;
		 * **/
		addDisableListener:function(isprev){
			var that = this;
			
			$(that.settings.selectors.toggleDisabled).on('click',function(event){
				if(isprev){
					if($(this).prop('checked')){
						$(this).prev().prop('disabled',true);
					}else{
						$(this).prev().prop('disabled',false);
					}
				}else{
					if($(this).prop('checked')){
						$(this).next().prop('disabled',true);
					}else{
						$(this).next().prop('disabled',false);
					}
				}
				
			});
			//$(that.settings.selectors.toggleDisabled).trigger('click');
		}
	};
	/**
	 * 解析url后的查询字符串
	 * 
	 * @return 查询对象
	 */
	function parseQuery(url) {
		var p = url.indexOf('?');
		if (p == -1)
			return {
			'url':url,
			'query':{}
		};
		var str = url.substr(p + 1);
		var strs = str.split('&');
		var qdata = {};
		for (var i = 0; i < strs.length; i++) {
			var fs = strs[i].split('=');
			qdata[fs[0]] = decodeURIComponent(fs[1]);

		}
		var action = url.substr(0, p);
		return {
			'url' : action,
			'query' : qdata
		};
	}
	function doQuery(fobj, url) {
		 var fields = $(fobj).serializeArray();
         var param = [];
         //log(fields);
         for(var k in fields){
        	 if(!fields[k]['name'] || !fields[k]['value'])continue;
        	 param.push(fields[k]['name']+'`'+fields[k]['value']);
         }
         var query = param.join(';');
		 var qdata = {query:query};
		 doForm(url, qdata);
	}
	
	
	function setJsonData(tp,callback){
		
		var $target = $(tp);
		var keyUrl = $target.attr('key');
		//log(keyUrl);
		$.getJSON(keyUrl, function(data) {
			$target.empty();
			$target.append("<option value=''>请选择</option>");
			for ( var d in data) {
				$target.append(data[d].html);

			}

		});
		$target.off('change');
		$target.on(
				'change',
				function(event) {					
					$(this).next("input:hidden").val(
							$(this).find("option:selected").text());
                    if($.isFunction(callback))callback.call(this,event);
				});
	}
	
	window.DataAdmin = dataAdmin;
})(window);