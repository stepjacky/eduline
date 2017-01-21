$(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',       		
			namespace : 'userevent',			
			dataFormId:  'userevent_form'
    });  
    dataAdmin.addPersisteFormListener();   
    //dataAdmin.addRemoveListener();
    var calendar = $("#calendar").calendar({
		tmpl_cache : false,
		view:'week',
		modal : "#events-modal",
		tmpl_path : "/static/lib/bootstrap-calendar/tmpls/",
		events_source : '/userevent/myevents',
		position:{
			
		},
		onAfterEventsLoad : function(events) {
		},
		onAfterViewLoad : function(view) {
			$('.title').text(this.getTitle());
			$('.btn-group button').removeClass('active');
		}
	});
	$('.btn-group button[data-calendar-nav]').each(function() {
		var $this = $(this);
		$this.click(function() {
			calendar.navigate($this.data('calendar-nav'));
		});
	});
	$('.btn-group button[data-calendar-view]').each(function() {
		var $this = $(this);
		$this.click(function() {
			calendar.view($this.data('calendar-view'));
		});
	});
	
	$("input:radio.eventType").on('click',function(event){
		var key = $(event.currentTarget).val();
		if(key=='3'){
			loadDate();
			 
		}else{
			 loadTimer();
			
		}
	});
	 loadTimer();
	function loadTimer(){
		  $("#timeOccured").load('/static/tmps/userevent/timeOccured.html',function(){
		    	dataAdmin.addDatetimePickerListener();
		    	$('#repeatRange').empty();
		  });
	}
	function loadDate(){
		$('#repeatRange').load('/static/tmps/userevent/repeats.html',function(){
			 dataAdmin.addDatePickerListener();
			 $("#timeOccured").load('/static/tmps/userevent/timeOccured.html',function(){
			    	dataAdmin.addTimerPickerListener();
			 });
			
		});
	}
	
});