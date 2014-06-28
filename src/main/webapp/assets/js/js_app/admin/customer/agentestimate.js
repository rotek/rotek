//设置客户资料信息
Ext.ns("ROTEK.AGENTESTIMATE");
ROTEK.AGENTESTIMATE.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/AgentEstimateInfo/listAgentEstimateInfos",
		    dataList:[{
		          index:'id',
		          header:'评价ID',
		          width : 30,
				  align : 'center'
		      },{
		    	  index:'customer_mc',
		          header:'代理商名称',
		          width : 50,
		  		  align : 'center'
		      },{
		          index:'dlsxjpj',
		          header:'代理商星级评价',
		          renderer:function(value){
			    	  if(1==value){
			    		  return "<span style='color:black;'>文档</span>";
				      }else if(2==value){
			    		  return "<span style='color:black;'>演示稿</span>";
				      }else if(3==value){
			    		  return "<span style='color:black;'>图片</span>";
				      }else if(4==value){
			    		  return "<span style='color:black;'>视频</span>";
				      }else if(5==value){
				    	  return "<span style='color:black'>监测图</span>";
				      }else if(6==value){
				    	  return "<span style='color:black'>代理商证件</span>";
				      }
				  },
     	          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'dlsglxz',
		          header:'代理商管理细则',			 
		          width : 50,
		  		  align : 'center'
		      },{
		        index:'status',
		        header:'资料状态',
                width : 30,
		        align : 'center',
		        renderer:function(value){
		          if(1==value){
		            return "<span style='color:green;'>有效</span>";
		          }else{
		            return "<span style='color:red'>无效</span>";
		          }
		        }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/AgentEstimateInfo/addAgentEstimateInfo",
		detailUrl : basePath + "/admin/AgentEstimateInfo/getAgentEstimateInfoDetail",
		modifyUrl : basePath + "/admin/AgentEstimateInfo/modifyAgentEstimateInfo",
		dropUrl : basePath + "/admin/AgentEstimateInfo/deleteAgentEstimateInfo",
		listCustomersUrl : basePath + "/admin/AgentEstimateInfo/listCustomers"
	}
};