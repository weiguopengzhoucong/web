function hiddenOrShow(node, reference){
	switch(reference){
		case "parent":
			$(node).parent().toggle();
		  break;
		case "siblings":
			$(node).next().toggle();
		  break;
		case "children":
			$(node).children(":first").toggle();
		  break;
		default:
			$(reference).toggle();
	}
}

function changeStyle(node, style){
	$(node).siblings().removeClass(style);
	if($(node).hasClass(style))
		$(node).removeClass(style);
	else
		$(node).addClass(style);
}

function saveValue(node, target, parent){
	$(target).html($(node).html());
	hiddenOrShow(node, parent);
}