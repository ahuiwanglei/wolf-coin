package com.coin.shortline.entity.manger;




import com.coin.shortline.util.StringUtil;

import java.util.List;

public class MenuTree implements Comparable<MenuTree>{

	private String id;
	private String text;
	private boolean isexpand;
	private String icon;
	private String url;
	private boolean checkbox;
	private List<MenuTree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isIsexpand() {
		return isexpand;
	}

	public void setIsexpand(boolean isexpand) {
		this.isexpand = isexpand;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	@Override
	public int compareTo(MenuTree tree) {
		Integer c1 = new Integer(StringUtil.getInt(this.getId()));
		Integer c2 = new Integer(StringUtil.getInt(tree.getId()));
		return c1.compareTo(c2);
	}

	@Override
	public String toString() {
		return "MenuTree [id=" + id + ", text=" + text + ", isexpand=" + isexpand + ", icon=" + icon + ", url=" + url + ", checkbox=" + checkbox + ", children=" + children + "]";
	}

	 
	

}
