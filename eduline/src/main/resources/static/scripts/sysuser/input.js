$(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',       		
			namespace : 'sysuser',			
			dataFormId:  'sysuser_form'
    }); 

    dataAdmin.addPersisteFormListener();
    dataAdmin.addJsonSourceListener();  
    dataAdmin.addDatePickerListener();    
});

function checkusername(self){
	if(!self.value) return false;
	var url = '/sysuser/exists/'+self.value;
	
	$.get(url,function(data){
		log(data);
		if(data>0){
			$(self).next('span').html("该用户已经存在,请重新输入一个名字");	
		    self.value='';
		    self.focus();
		}else{
			$(self).next('span').html("该用户名可用,可以添加");
			 self.blur();
		}
	})
}