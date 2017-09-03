 $(function(){
   
    var dataAdmin  =  window.DataAdmin;
    dataAdmin.init(
    {
    	    context:'/',       		
			namespace : 'violates',			
			dataFormId:  'violates_form'
    }); 
    
    dataAdmin.addPagerListener(); 
    dataAdmin.addRemoveListener();
    
 // instantiate the bloodhound suggestion engine
    var numbers = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.whitespace,
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    limit:10,
    remote:  "/sysuser/search?query=%QUERY"
    });

    // initialize the bloodhound suggestion engine
    numbers.initialize();

    var $input = $("#student");
    $input.typeahead(
    		{
    			source:numbers.ttAdapter(),
    	        autoSelect: true,
    	        valueField:'value',
    	        textField:'text',
    	        minLength:3
    	        
    }); 
    $input.change(function() {
        var current = $input.typeahead("getActive");
       
        if (current) {
            // Some item from your model is active!
            if (current[this.valueField] == $input.val()) {
                // This means the exact match is found. Use toLowerCase() if you want case insensitive match.
                log("==");log(current)
            } else {
                // This means it is only a partial match, you can either add a new item 
                // or take the active if you don't want new items
            	 log("~=");log(current)
            }
            var data = {
            	student:current['value'],
            	studentName:current['text']
            }
            var html = template('dataTemp',data);
            $("#dataArea").empty();
            $("#dataArea").html(html);
            dataAdmin.addDatePickerListener();
        } else {
            // Nothing is active so it is a new value (or maybe empty value)
        	 log("<>");
        	 log(current)
        }
    });
    
    $(document.body).on('click','button.submitViolate',updateScore);
    function updateScore(){
    	
    	var data = $("#violates_form").serializeArray();
	   	 var url  ='/violates/persiste';
	   	 var sdata = {};
	   	 for(var k in data){
	   		 sdata[data[k]['name']]=data[k]['value'];
	   	 }
	   	 var param = "data="+JSON.stringify(sdata);
	   	 $.post(url,param,function(){

             layer.confirm('确定跳转?', function(index){
                 //do something
				 if(index==1){
                     loadUrl('/violates/query/0');
				 }
                 layer.close(index);
             });

	   	 });
    	
    	
    	
    	
    }
    
    $("button.querybtn").on('click',function(event){
    	 var form = $(event.currentTarget).parent('form');
    	 
    	 var params = form.serializeArray();    	 
    	 var querys = [];    	
    	 for(var d in params){
    		querys.push(params[d].name+'`'+params[d].value);
    	 }    
    	
    	 var qstr = querys.join(';');
         $.post(form.attr('action'),{query:qstr},function (rst) {
             $('#contentbody').html(rst);
         })
    	 
    });

    $('.querybtn2').on('click',function (event) {
        var form = $(event.currentTarget).parent('form');
        var params = form.serialize();

        $.post(form.attr('action'),params,function (rst) {
            $('#contentbody').html(rst);
        })
    })


     $('.action-href-nav').on('click',function (event) {
         event.preventDefault();
         loadUrl($(this).attr('href'));
         return false;
     })

});
 
 