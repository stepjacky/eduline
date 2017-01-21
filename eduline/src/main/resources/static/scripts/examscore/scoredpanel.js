$(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
   	 		context:'/',       		
			namespace : 'examscore',			
			dataFormId:  'examscore_form'
    });  
  
   
   $('button.submitAll').on('click',function(event){
    	var pns = $(this).attr('key');
    	var scorearray = [];    	
    	for(var s in gminfo){
    		var d = updateItem(pns,s);
    		if(d)scorearray.push(d);
    	}
    	
    	var data = "data="+JSON.stringify(scorearray);    	
    	$.post('/examscore/persisties',data,function(res){
    		window.location.href=window.location.href;	  		
    	});
    });   
    
    function updateItem(pns,student){
    	var ctx = $("#"+pns);
    	var withform = (pns=='newscored');    	
    	var gm = gminfo[student];
    	var sdata  ={};
    	sdata['semester'] = gm['semester'];
		sdata['monthly'] = gm['monthly'];
    	if(withform){
    		var fields = $("#common_form").serializeArray();
    		for(var k in fields){
    			sdata[fields[k]['name']] = fields[k]['value'];
    		}
    	}
    	sdata['grade'] = gm['grade'];
    	sdata['gradeName'] = gm['gradeName'];
    	sdata['course'] = gm['course'];
    	sdata['courseName'] = gm['courseName'];
    	sdata['courseType'] = gm['courseType'];
    	sdata['student'] = gm['student'];
    	sdata['studentName'] = gm['studentName'];    	
    	sdata['groupId'] = gm['groupId'];
    	sdata['inyear']=gm['inyear'];    	 	
    	var rmkhost = $("textarea[key="+student+"]",ctx);    	
    	var rmkval =  rmkhost.val();
    	sdata['remark'] = rmkval;
    	var scoreHost = $("input[key='"+student+"']",ctx);
    	if(!scoreHost.val()) return false;
    	sdata['scoreValue'] = parseFloat(scoreHost.val());	
    	return sdata;
    }

    
});
