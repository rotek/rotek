/** 取得一个节点的所有父节点 */
function getAllParentNodes(node){
	var parentNodes=[];
	if(node.parentNode && node.parentNode.id!='0'){
		parentNodes.push(node.parentNode);
		parentNodes = parentNodes.concat(getAllParentNodes(node.parentNode));
	}
	return parentNodes;
}
/** 取得一个节点的所有子节点 */
function getAllChildrenNodes(node){
	var children = [];
	if(!node.isLeaf()){//是目录
		//node.expand();
		for(var i=0;i<node.childNodes.length;i++){
			children.push(node.childNodes[i]);
			children = children.concat(getAllChildrenNodes(node.childNodes[i]));
		}
	 }
	return children;
}
/***有子节点没有被选中**/
//pNode.getUI().checkbox.indeterminate = true;
function childrenHasNotChecked(node)
{
	 var ret = false;
	 node.expand();
	 node.eachChild(function(child){
		if(child.getUI().checkbox.checked  == false || child.attributes.checked ==false ){
			ret = true;
			  return false;
		}
		if(child.isLeaf() ==false)
		{

			ret =childrenHasNotChecked(child);
			if(ret) return false;
		}
		return true;
	});
	 return ret;
}
/***有子节点被选中**/
function childrenHasChecked(node)
{

	 var ret = false;
	 node.expand();
	 node.eachChild(function(child){
		if(child.getUI().checkbox.checked || child.attributes.checked|| child.getUI().checkbox.indeterminate == true){
			ret = true;
			  return false;
		}
		if(child.isLeaf() ==false)
		{
			ret =childrenHasChecked(child);
			if(ret) return false;
		}
         return true;
	});
	 return ret;
}
function innerCheckAncestor(node, checked)
{

	node.attributes.checked = checked;
	/*if(node.ui.checkbox){
		node.ui.checkbox.checked = checked;
	}*/

	node.eachChild(function(child){
		child.expand();
		//child.ui.toggleCheck(checked);
		child.attributes.checked = checked;
		child.ui.checkbox.checked = checked;
		child.fireEvent('checkchange',child,checked);
		return true;

	});

	//获取所有父节点
	var root = node.getOwnerTree().getRootNode();
	var pNode = node.parentNode;
	var rootId = root.id;
	if(typeof rootId =='undefined')rootId ='0';
	if( !pNode )return;
	if( typeof (pNode.id) =='undefined')return;
	while(pNode.id !=rootId){//子节点选中，父节点也被选中
		 	if(childrenHasChecked(pNode) &&   childrenHasNotChecked(pNode))
	        {
	        	pNode.ui.checkbox.checked = checked;
        		pNode.attributes.checked = true;
        		pNode.getUI().checkbox.indeterminate = true; //半选中状态

	        }else
	        {
	        	pNode.ui.checkbox.checked = checked;
	         	pNode.attributes.checked = checked;
	         	pNode.getUI().checkbox.indeterminate = false; //选中状态
	        }
		 	var oldnode = pNode;
			pNode = pNode.parentNode;
			//alert(oldnode.text+"----"+oldnode.id +"---"+pNode.text+"----"+pNode.id);
		}
}
/**子节点选中，其祖上节点亦选中*/
 function checkAncestor(node, checked) {
		//获取所有子节点
		/*node.cascade(function(node){
		   node.expand();
		   node.attributes.checked = checked;
		   node.ui.checkbox.checked = checked;
		   return true;
	    });*/
 		node.expand();
		node.attributes.checked = checked;
		node.eachChild(function(child){
			child.ui.toggleCheck(checked);
			child.attributes.checked = checked;
			child.fireEvent('checkchange',child,checked);
		});
   		//获取所有父节点
	    var pNode = node.parentNode;
		while(pNode.id !="0"){//子节点选中，父节点也被选中
			if (checked || node.getOwnerTree().getChecked(id,pNode)== "") {
		         pNode.ui.checkbox.checked = checked;
		         pNode.attributes.checked = checked;
		    }else{
		    	var childNodes = getAllChildrenNodes(pNode);//获取被选中节点的所有子节点,若子节点还有被选中的，被选中的节点（即父节点）不勾掉
		    	for (var i = 0; i < childNodes.length; i++) {
				 	if (node.getOwnerTree().getChecked(id,childNodes[i])!= "") {
				   		pNode.ui.checkbox.checked = checked;
		         		pNode.attributes.checked = checked;
				   	}
				}
		    }
			pNode = pNode.parentNode;
		}
	    /*for(; pNode.id !="perRoot"; pNode = pNode.parentNode ){

	    }*/
}

/**只要有一个子节点不选中，其祖上节点一个也不选中*/
 function noCheckAncestor(node, checked) {
 		node.expand();
		node.attributes.checked = checked;
		node.eachChild(function(child){
			child.ui.toggleCheck(checked);
			child.attributes.checked = checked;
			child.fireEvent('checkchange',child,checked);
		});
   		//子节点全部选中，父节点也被选中,只要有一个子节点不选中，父节点不被选中
	    var pNode = node.parentNode;
		while(pNode.id !="0"){
			var childNodes = getAllChildrenNodes(pNode);//获取被选中节点的所有子节点
			if (checked) {
				if (!pNode.findChild("checked",false)) {//子节点全部选中，父节点选中
					 pNode.ui.checkbox.checked = checked;
		         	 pNode.attributes.checked = checked;
				}
			}else{//有一个不选中，父节点不选中
				 pNode.ui.checkbox.checked = checked;
		         pNode.attributes.checked = checked;
			}

			pNode = pNode.parentNode;
		}
}

/**移除节点前事件*/
function beforeRemove(tree,parentNode,node){
	if (parentNode) {
		parentNode.select();
	}else{
		tree.getRootNode().select();
	}
}
function checkMoreChilds(node)
{
		node.attributes.checked = true;
		node.ui.toggleCheck(true);

		if(node.isLeaf() ==false)
		{
			node.fireEvent('checkchange', node, true);
		}
}

