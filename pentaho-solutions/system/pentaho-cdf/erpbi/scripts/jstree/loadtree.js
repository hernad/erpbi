	function loadTree() 
	{
          /*
	    *#######################
	    *     West Tree
	    *#######################
	    */
        conf = {
            ui: {
                theme_name: "classic"
            },
            rules: {
                draggable: "none",
                drag_copy: false
            },
            callback: 
            {        
            	onselect: function()
            	//  Callback for opening a new tab for a tree item href 
            	{ 
            		var href = data.rslt.obj.children("a").attr("href");
            		window.open(href);            		
            	 	return true; 
            	}
            }
        };
        tree1 = $.tree_create();
        tree1.init($("#explorer"), $.extend({}, conf));
	}
