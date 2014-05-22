/**
* @FileName: NodeEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-7 上午11:42:47
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

/**
 * @ClassName: NodeEntity
 * @Description: 树的节点对象
 * @author chenwenpeng
 * @date 2013-6-7 上午11:42:47
 *
 */
public class NodeEntity implements Serializable {
	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 885671922567646807L;
	//节点id
	private String id;
	//节点名字
	private String text;
    //节点是不是叶子
    private Boolean leaf;
    //节点的样式
    private String cls;
    //是否选中
    private Boolean checked = false;
	//是否打开
    private boolean expanded = true;


    public Boolean getChecked() {
    	return checked;
    }
    public void setChecked(Boolean checked) {
    	this.checked = checked;
    }
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}

}
