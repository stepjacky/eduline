$(function(){
	// instantiate the bloodhound suggestion engine
    var numbers = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.whitespace,
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    limit:10,
    remote:  "/sysuser/search?query=%QUERY"
    });

    // initialize the bloodhound suggestion engine
    numbers.initialize();

    var $input = $(".studentNo");
    $input.typeahead(
    		{
    			source:numbers.ttAdapter(),
    	        autoSelect: true,
    	        valueField:'value',
    	        textField:'text',
    	        minLength:4
    	        
    }); 
    $input.change(function() {
        var current = $input.typeahead("getActive");
       
        if (current) {
            // Some item from your model is active!
            if (current[this.valueField] == $input.val()) {
                // This means the exact match is found. Use toLowerCase() if you want case insensitive match.
                log("==");log(current);
            } else {
                // This means it is only a partial match, you can either add a new item 
                // or take the active if you don't want new items
            	 log("~=");log(current)
            }
            var data = {
            	student:current['value'],
            	studentName:current['text']
            }
            $input.val(data.student);            
        } else {
            
        }
    });
});