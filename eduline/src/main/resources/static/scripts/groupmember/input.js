var users={};
$(function(){
   
	 var dataAdmin  =  window.DataAdmin;
	    dataAdmin.init(
	    {
	   	 		context:'/',       		
				namespace : 'groupmember',			
				dataFormId:  'groupmember_form'
	    }); 	   
	    dataAdmin.addJsonSourceListener(loadStudent);  
	    
	  $("button.persisteDataItem").on('click',function(){
		  
		  var datas  = $("#groupmember_form").serializeArray();
		  
		  var sdataz = [];
		  var groupId = guid();
		  for(var name in users){
			  if(!users[name])continue;
			  var sdata = {};
			  for(var k in datas){
				  sdata[datas[k].name]=datas[k].value;
			  }
			  sdata['student'] = name;
			  sdata['studentName'] = users[name];
			  sdata['groupId'] = groupId;
			  sdata['inyear'] = parseInt(name.substr(2,2))+2000;
		      sdataz.push(sdata);
		  }
		  var pstr = "data=" + JSON.stringify(sdataz);
		  $.post('/groupmember/persisties',pstr,function(){
			  window.location.href=window.location.href;
		  });
		  
	  });
	    
	    function loadStudent(event){
	    	if(this.name=='grade'){
	    	   var opt = $(this).find("option:selected");
	    	   var url = '/sysuser/datatable/0?ajax=true&query=userType`1;grade`'+opt.val();
	    	  
	    	   $.get(url,function(data){
	    		 $("#dataArea").empty();
	    		 $("#dataArea").append(data);
	    	   });
	    	   
	    	  
	    	}
	    }
});

function selectUser(self,username,nickname){
	if(!users[username]){
		users[username] = nickname;  
		$("#selectArea").append(template("checkTemp", {username:username,nickname:nickname}));
	}
	log(users);
   	
}

function unselectUser(self,username){
	users[username]=null;
	$(self).parent().parent().remove();
	log(users);
}